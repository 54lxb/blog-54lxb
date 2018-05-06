<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<c:set var="baseURL" value="${pageContext.request.contextPath}"/>
<link rel="stylesheet" href="${baseURL}/static/frame/ueditor-1.4.3/third-party/SyntaxHighlighter/shCoreDefault.css">
<link rel="stylesheet" href="${baseURL}/static/frame/layer-2.4/skin/layer.css">
<link rel="stylesheet" href="${baseURL}/static/frame/editormd-1.5.0/css/editormd.css">
<style type="text/css">
    .diggit {
        position: fixed;
        border-radius: 5px;
        padding: 20px;
        right: 40px;
        bottom: 10px;
        border: solid 1px #ddd;
        z-index: 200;
        box-shadow: 0 0 16px 2px #ddd;
        width: 140px;
        height: 240px;
        background: #fff url(/static/front/blog/image/172356420975864.gif) no-repeat 16px 0;
        background-size: 120px 120px;
        text-align: center;
        cursor: pointer;
        margin-top: 2px;
        padding-top: 5px;
    }

    #div_digg .diggnum {
        line-height: 1.5em!important;
    }
    .diggnum {
        display: inline-block;
        background: url(/static/front/blog/image/180024347697620.gif) no-repeat;
        width: 100px;
        height: 100px;
        background-size: 200px 100px;
        margin-top: 120px;
        font-size: 40px;
        color: #6DA47D;
        font-family: 'Microsoft Yahei';
    }
    .ui.basic.orange.label {
        background-color: #FFF!important;
        color: #F2711C!important;
        border-color: #F2711C!important;
    }
    .ui.basic.label {
        background: #FFF;
        border: 1px solid rgba(34,36,38,.15);
        color: rgba(0,0,0,.87);
        box-shadow: none;
    }
    .ui.orange.label, .ui.orange.labels .label {
        background-color: #F2711C!important;
        border-color: #F2711C!important;
        color: #FFF!important;
    }
    a.ui.label {
        cursor: pointer;
    }
    .ui.label, .ui.labels .label {
        font-size: .85714286rem;
    }
    .ui.label {
        display: inline-block;
        line-height: 1;
        vertical-align: baseline;
        margin: 0 .14285714em;
        background-color: #E8E8E8;
        background-image: none;
        padding: .5833em .833em;
        color: rgba(0,0,0,.6);
        text-transform: none;
        font-weight: 700;
        border: 0 solid transparent;
        border-radius: .28571429rem;
        -webkit-transition: background .1s ease;
        transition: background .1s ease;
    }
    .ui.segments:not(.horizontal)>.segment:last-child {
        top: 0;
        bottom: 0;
        margin-top: 0;
        margin-bottom: 0;
        border-radius: 0 0 .28571429rem .28571429rem;
    }
    .ui.segment, .ui.segments .segment {
        font-size: 1rem;
    }
    .ui.secondary.segment {
        background: #F3F4F5;
        color: rgba(0,0,0,.6);
    }
    .ui.segments>.segment {
        top: 0;
        bottom: 0;
        border-radius: 0;
        margin: 0;
        width: auto;
        box-shadow: none;
        border: none;
        border-top: 1px solid rgba(34,36,38,.15);
    }
    .ui.segment:last-child {
        margin-bottom: 0;
    }

    .ui.segment {
        position: relative;
        background: #FFF;
        box-shadow: 0 1px 2px 0 rgba(34,36,38,.15);
        margin: 1rem 0;
        padding: 1em;
        border-radius: .28571429rem;
        border: 1px solid rgba(34,36,38,.15);
    }

</style>

<div class="data_list">
    <div class="data_list_title">
        <img src="${baseURL}/static/common/images/blog_show_icon.png"/>博客信息
    </div>
    <div>
        <div class="blog_title"><h3><strong>${blog.title }</strong></h3></div>
        <div class="blog_info">
            发布时间：『 <fmt:formatDate value="${blog.releaseDate }" type="date" pattern="yyyy-MM-dd HH:mm"/>』
            &nbsp;&nbsp;
            博客类别：${blog.blogType.typeName}&nbsp;&nbsp;阅读(${blog.clickHit})
            评论(${blog.replyHit})
        </div>
        <div id="wordsView">
            <textarea style="display:none;" name="editormd-markdown-doc">${blog.content }</textarea>
        </div>
        <div class="ui secondary segment"><b>关键字：</b>
            &nbsp;&nbsp;
            <c:choose>
                <c:when test="${keyWords == null}">
                    &nbsp;&nbsp;博主太懒，还没添加关键字！
                </c:when>
                <c:otherwise>
                    <c:forEach var="keyword" items="${keyWords }">
                        &nbsp;&nbsp;
                        <a class="ui basic orange label" href="${baseURL}/article/search?keyword=${keyword}" target="_blank">${keyword }</a>
                        &nbsp;&nbsp;
                    </c:forEach>
                </c:otherwise>
            </c:choose>
        </div>
        <div class="blog_lastAndNextPage">
            ${pageCode }
        </div>
    </div>
</div>
<div class="data_list">
    <div class="data_list_title">
        <img src="${baseURL}/static/common/images/comment_icon.png"/>
        评论信息
        <c:if test="${commentList.size()>10}">
            <a href="javascript:showOtherComment()" style="float: right;padding-right: 40px;">显示所有评论</a>
        </c:if>
    </div>
    <div class="commentDatas">
        <c:choose>
            <c:when test="${commentList.size()==0}">
               快来抢沙发吧！
            </c:when>
            <c:otherwise>
                <c:forEach var="comment" items="${commentList }" varStatus="status">
                    <c:choose>
                        <c:when test="${status.index < 10 }">
                            <div class="comment">
                                <span class="comment-author">
                                    <font>${status.index+1 }楼&nbsp;&nbsp;&nbsp;&nbsp;${comment.userIp }  </font>
                                    [&nbsp;<fmt:formatDate value="${comment.commentDate }" type="date" pattern="yyyy-MM-dd HH:mm"/>&nbsp;]
                                </span>
                                <div class="comment-content">${comment.content }&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</div>
                            </div>
                        </c:when>
                        <c:otherwise>
                            <div class="otherComment">
                                <div class="comment">
                                    <span class="comment-author">
                                        <font>${status.index+1 }楼&nbsp;&nbsp;&nbsp;&nbsp;${comment.userIp }  </font>
                                        [&nbsp;<fmt:formatDate value="${comment.commentDate }" type="date" pattern="yyyy-MM-dd HH:mm"/>&nbsp;]
                                    </span>
                                    <div class="comment-content">${comment.content }&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</div>
                                </div>
                            </div>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
            </c:otherwise>
        </c:choose>
    </div>
</div>

<div class="data_list">
    <div class="data_list_title">
        <img src="${baseURL}/static/common/images/publish_comment_icon.png"/> 发表评论
    </div>
    <div class="publish_comment">
        <div id="editor-md" class="content">
            <textarea class="editormd-markdown-textarea" title="" style="display:none;"></textarea>
        </div>
        <div class="passcode">
            <label for="passcode">验证码：</label>
            <input type="text" value="" name="passcode" id="passcode" size="10"/>
            &nbsp;
            <img onclick="loadImage(this);" title="换一张试试" name="passcode" align="absmiddle"
                 style="cursor: pointer;" src="${baseURL}/passcode" width="60" height="20" border="1"/>
        </div>
        <div class="publishButton">
            <button class="btn btn-primary" type="button" onclick="submitData()">发表评论</button>
        </div>
    </div>
</div>

<div class="diggit" onclick="shareAnyWhere()">
    <span class="diggnum" id="digg_count">520</span>
</div>

<script type="text/javascript" src="${baseURL}/static/frame/bootstrap-3.3.7/js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="${baseURL}/static/frame/layer-2.4/layer.js"></script>
<script src="${baseURL}/static/frame/editormd-1.5.0/lib/marked.min.js"></script>
<script src="${baseURL}/static/frame/editormd-1.5.0/lib/prettify.min.js"></script>
<script src="${baseURL}/static/frame/editormd-1.5.0/lib/raphael.min.js"></script>
<script src="${baseURL}/static/frame/editormd-1.5.0/lib/underscore.min.js"></script>
<script src="${baseURL}/static/frame/editormd-1.5.0/lib/sequence-diagram.min.js"></script>
<script src="${baseURL}/static/frame/editormd-1.5.0/lib/flowchart.min.js"></script>
<script src="${baseURL}/static/frame/editormd-1.5.0/lib/jquery.flowchart.min.js"></script>
<script type="text/javascript" src="${baseURL}/static/frame/editormd-1.5.0/js/editormd.min.js"></script>
<script type="text/javascript">
    $(function () {
        editormd.markdownToHTML("wordsView", {
            htmlDecode      : "style,script,iframe",  // you can filter tags decode
            emoji           : true,
            taskList        : true,
            tex             : true,  // 默认不解析
            flowChart       : true,  // 默认不解析
            sequenceDiagram : true  // 默认不解析
        });
    });

    var editor = editormd({
        id:"editor-md", //编辑(格式化)的ID
        path : "${baseURL}/static/frame/editormd-1.5.0/lib/",
        width: "100%",
        height: 300,
        watch: false,
        toolbarIcons: function () {
            return ["undo", "redo", "clear","|", "list-ul", "list-ol", "hr",
                "|", "code", "code-block", "table", "datetime","emoji", "||","watch", "preview"]
        },
        placeholder: "骚年哟，既然都来了，为何不吐槽几句呢 (σﾟ∀ﾟ)σ ...",
        saveHTMLToTextarea: true, //开启保存HTML文件
        dialogLockScreen : false,
        lineNumbers: false,
        autoFocus: false,
        emoji: true
    });

    // 检查回答的字数
    function checkAnswerParams(){
        return editor.getMarkdown().trim().length === 0;
    }


    var YEAR = 1000 * 60 * 60 * 24 * 365;
    var MONTH = 1000 * 60 * 60 * 24 * 30;
    var DAY = 1000 * 60 * 60 * 24;
    var HOUR = 1000 * 60 * 60;
    var MINUTE = 1000 * 60;

    // 时间转化
    function fmtDate(inputTime) {
        var date = new Date(inputTime);
        var now = new Date();
        var between = now.getTime() - date.getTime();
        if (between > YEAR){
            return parseInt((between - YEAR) / YEAR + 1) + "年前";
        }
        if (between > MONTH){
            return parseInt((between - MONTH) / MONTH + 1) + "月前";
        }
        if (between > DAY){
            return parseInt((between - DAY) / DAY + 1) + "天前";
        }
        if (between > HOUR){
            return parseInt((between - HOUR) / HOUR + 1) + "小时前";
        }
        if (between > MINUTE){
            return parseInt((between - MINUTE) / MINUTE + 1) + "分钟前";
        }
        return "刚刚";
    }

    function initCommentDate(dom,date) {
        document.getElementById(dom).innerHTML(fmtDate(date));
    }

    function loadImage(image) {
        image.src = image.src + "?" + Math.random();
    }

    function submitData() {
        var content = editor.getHTML();
        var passcode = $("#passcode").val();
        if (!content) {
            layer.alert("请输入评论内容！");
            return false;
        } else if (!passcode) {
            layer.alert("请填写验证码！");
            return false;
        } else {
            $.post("${baseURL}/comment/save", {
                'content': content,
                'passcode': passcode,
                'blog.id': '${requestScope.blog.id}'
            }, function (result) {
                if (result.success) {
                    setTimeout(function () {
                        layer.msg("评论已提交成功，审核通过后显示！");
                    }, 1000);
                    window.location.reload();
                } else {
                    layer.alert(result.errorInfo);
                    return false;
                }
            }, "json");
        }
    }

    function showOtherComment() {
        $('.otherComment').show();
    }

    function shareAnyWhere() {
        layer.msg("亲，该功能还在测试中哦！");
    }

</script>