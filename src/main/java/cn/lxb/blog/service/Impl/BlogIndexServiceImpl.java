package cn.lxb.blog.service.Impl;

import cn.lxb.blog.entity.Blog;
import cn.lxb.blog.constant.BlogConstant;
import cn.lxb.blog.exception.BlogException;
import cn.lxb.blog.service.BlogIndexService;
import cn.lxb.blog.utils.DateUtil;
import cn.lxb.blog.utils.StringUtil;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.cn.smart.SmartChineseAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.*;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.*;
import org.apache.lucene.search.highlight.*;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.springframework.stereotype.Service;

import java.io.StringReader;
import java.nio.file.Paths;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * 博客索引管理
 *
 * @author 54LXB.
 * @since 2018-03-04.
 */
@Service
public class BlogIndexServiceImpl implements BlogIndexService {

    private Directory dir = null;

    /**
     * TODO 获取IndexWriter实例
     */
    private IndexWriter getWriter() throws Exception {
        dir = FSDirectory.open(Paths.get(BlogConstant.LUCENE_PATH));
        SmartChineseAnalyzer analyzer = new SmartChineseAnalyzer();
        IndexWriterConfig iwc = new IndexWriterConfig(analyzer);
        return new IndexWriter(dir, iwc);
    }

    // TODO 读取索引文件
    private IndexReader getIndexReader() throws Exception {
        dir = FSDirectory.open(Paths.get(BlogConstant.LUCENE_PATH));// 打开目录
        IndexReader reader;
        try {
            reader = DirectoryReader.open(dir);// 进行读取
        } catch (IndexNotFoundException e) {
            throw new BlogException("系统出错，找不到索引文件!");
        }
        return reader;
    }

    @Override
    public void addIndex(Blog blog) throws Exception {
        IndexWriter writer = getWriter();
        Document doc = new Document();
        doc.add(new StringField("id", String.valueOf(blog.getId()), StringField.Store.YES));
        doc.add(new TextField("title", blog.getTitle(), TextField.Store.YES));
        doc.add(new StringField("releaseDate", DateUtil.formatDate(new Date(), "yyyy-MM-dd"), StringField.Store.YES));
        doc.add(new TextField("content", blog.getContentNoTag(), TextField.Store.YES));
        writer.addDocument(doc);
        writer.commit();
        writer.close();
    }

    @Override
    public void updateIndex(Blog blog) throws Exception {
        IndexWriter writer = getWriter();
        Document doc = new Document();
        doc.add(new StringField("id", String.valueOf(blog.getId()), StringField.Store.YES));
        doc.add(new TextField("title", blog.getTitle(), TextField.Store.YES));
        doc.add(new StringField("releaseDate", DateUtil.formatDate(new Date(), "yyyy-MM-dd"), StringField.Store.YES));
        doc.add(new TextField("content", blog.getContentNoTag(), TextField.Store.YES));
        writer.updateDocument(new Term("id", String.valueOf(blog.getId())), doc);
        writer.commit();
        writer.close();
    }

    @Override
    public void deleteIndex(String blogId) throws Exception {
        IndexWriter writer = getWriter();
        writer.deleteDocuments(new Term("id", blogId));
        writer.forceMergeDeletes(); // 强制删除
        writer.commit();
        writer.close();
    }

    @Override
    public List<Blog> searchBlog(String keyword) throws Exception {

        IndexSearcher is = new IndexSearcher(getIndexReader());// 索引查询器
        BooleanQuery.Builder booleanQuery = new BooleanQuery.Builder();

        SmartChineseAnalyzer analyzer = new SmartChineseAnalyzer();// 智能汉语分词器
        QueryParser parser = new QueryParser("title", analyzer);// 在哪查询，第一个参数为查询的Document，在Indexer中创建了
        Query query = parser.parse(keyword);// 对字段进行解析后返回给查询
        QueryParser parser2 = new QueryParser("content", analyzer);// 在哪查询，第一个参数为查询的Document，在Indexer中创建了
        Query query2 = parser2.parse(keyword);// 对字段进行解析后返回给查询
        booleanQuery.add(query, BooleanClause.Occur.SHOULD);
        booleanQuery.add(query2, BooleanClause.Occur.SHOULD);

        TopDocs hits = is.search(booleanQuery.build(), 100);// 开始查询，100代表前100条数据；返回一个文档
        QueryScorer scorer = new QueryScorer(query);
        Fragmenter fragmenter = new SimpleSpanFragmenter(scorer);
        SimpleHTMLFormatter simpleHTMLFormatter = new SimpleHTMLFormatter("<b><font color='red'>", "</font></b>");
        Highlighter highlighter = new Highlighter(simpleHTMLFormatter, scorer);
        highlighter.setTextFragmenter(fragmenter);
        List<Blog> blogList = new LinkedList<Blog>();

        for (ScoreDoc scoreDoc : hits.scoreDocs) {
            Document doc = is.doc(scoreDoc.doc);// 根据文档的标识获取文档
            Blog blog = new Blog();
            blog.setId(Integer.parseInt(doc.get(("id"))));
            blog.setReleaseDateStr(doc.get(("releaseDate")));
            String title = doc.get("title");
            String content = StringEscapeUtils.escapeHtml(doc.get("content"));

            if (title != null) {
                TokenStream tokenStream = analyzer.tokenStream("title", new StringReader(title));
                String hTitle = highlighter.getBestFragment(tokenStream, title);
                if (StringUtil.isEmpty(hTitle)) {
                    blog.setTitle(title);
                } else {
                    blog.setTitle(hTitle);
                }
            }

            if (content != null) {
                TokenStream tokenStream = analyzer.tokenStream("content", new StringReader(content));
                String hContent = highlighter.getBestFragment(tokenStream, content);
                if (StringUtil.isEmpty(hContent)) {
                    if (content.length() <= 200) {
                        blog.setContent(content);
                    } else {
                        blog.setContent(content.substring(0, 200));
                    }
                } else {
                    blog.setContent(hContent);
                }
            }
            blogList.add(blog);
        }
        return blogList;
    }
}
