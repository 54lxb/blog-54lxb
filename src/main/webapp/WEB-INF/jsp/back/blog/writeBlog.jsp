<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="baseURL" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>博主写博客页面</title>
    <link rel="stylesheet" type="text/css" href="${baseURL}/static/frame/jquery-easyui-1.3.3/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="${baseURL}/static/frame/jquery-easyui-1.3.3/themes/icon.css">
    <link rel="stylesheet" href="${baseURL}/static/frame/editormd-1.5.0/css/editormd.css">
</head>
<body style="margin: 10px">
<div id="p" class="easyui-panel" title="编写博客" style="padding: 10px">
    <table cellspacing="20px" width="100%">
        <tr>
            <td width="80px"><label for="title">博客标题：</label></td>
            <td><input type="text" id="title" name="title" style="width: 400px;"/></td>
        </tr>
        <tr>
            <td><label for="blogTypeId">所属类别：</label></td>
            <td>
                <select class="easyui-combobox" style="width: 154px" id="blogTypeId" name="blogType.id" editable="false" panelHeight="auto">
                    <option value="">请选择博客类别...</option>
                    <c:forEach var="blogType" items="${blogTypeCountList }">
                        <option value="${blogType.id }">${blogType.typeName }</option>
                    </c:forEach>
                </select>
            </td>
        </tr>
        <tr>
            <td valign="top">博客内容：</td>
            <td>
                <div id="editor-md" class="content">
                    <textarea class="editormd-markdown-textarea" title="" style="display:none;"></textarea>
                </div>
            </td>
        </tr>
        <tr>
            <td><label for="keyWord">关键字：</label></td>
            <td><input type="text" id="keyWord" name="keyWord" style="width: 400px;"/>&nbsp;(多个关键字中间用空格隔开)</td>
        </tr>
        <tr>
            <td></td>
            <td>
                <a href="javascript:saveBlog(null)" class="easyui-linkbutton" data-options="iconCls:'icon-submit'">发布博客</a>
            </td>
        </tr>
    </table>
</div>
<script type="text/javascript" src="${baseURL}/static/frame/jquery-easyui-1.3.3/jquery.min.js"></script>
<script type="text/javascript" src="${baseURL}/static/frame/editormd-1.5.0/js/editormd.min.js"></script>
<script type="text/javascript" src="${baseURL}/static/frame/jquery-easyui-1.3.3/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${baseURL}/static/frame/jquery-easyui-1.3.3/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="${baseURL}/static/frame/layer-2.4/layer.js"></script>
<script type="text/javascript" src="${baseURL}/static/back/js/blogManage.js"></script>
<script type="text/javascript">
    var editor;
    //实例化编辑器
    $(function() {
        editor = editormd({
            id:"editor-md", //编辑(格式化)的ID
            path : "${baseURL}/static/frame/editormd-1.5.0/lib/",
            width: "100%",
            height: $(window).height(),
            watch: false,
            toolbarIcons: function () {
                return ["bold", "italic", "hr", "ucwords","uppercase", "lowercase", "undo", "redo", "clear","|", "list-ul", "list-ol", "hr",
                    "|","link", "reference-link", "image", "code", "code-block", "table", "datetime","emoji", "pagebreak", "search", "||","watch", "preview"]
            },
            placeholder: "骚年哟，既然都来了，为何不吐槽几句呢 (σﾟ∀ﾟ)σ ...",
            saveHTMLToTextarea: true, //开启保存HTML到 Textarea
            dialogLockScreen : false,//设置弹出层对话框不锁屏，全局通用，默认为true
            dialogShowMask : false,//设置弹出层对话框显示透明遮罩层，全局通用，默认为true
            dialogDraggable : false,//设置弹出层对话框不可拖动，全局通用，默认为true
            dialogMaskOpacity : 0.1, //设置透明遮罩层的透明度，全局通用，默认值为0.1
            dialogMaskBgColor : "#fff",//设置透明遮罩层的背景颜色，全局通用，默认为#fff
            lineNumbers: false,
            codeFold : true,
            autoFocus: false,
            emoji: true,
            tex : true,                   // 开启科学公式TeX语言支持，默认关闭
            flowChart : true,             // 开启流程图支持，默认关闭
            sequenceDiagram : true,       // 开启时序/序列图支持，默认关闭,
            imageUpload : true,
            imageFormats : ["jpg", "jpeg", "gif", "png", "bmp", "webp"],
            imageUploadURL : "/admin/upload/imageUpload"
        });
    });


    function saveBlog(id) {

        var title = $("#title").val();
        var blogTypeId = $("#blogTypeId").combobox("getValue");
        var content = editor.getMarkdown();
        var contentNoTag = editor.getMarkdown();
        var summary = editor.getHTML().substring(0,200);
        var keyWord = $("#keyWord").val();

        if (title === null || title === '') {
            layer.alert("请输入标题！");
            return false;
        } else if (blogTypeId === null || blogTypeId === '') {
            layer.alert("请选择博客类别！");
        } else if (content === null || content === '') {
            layer.alert("请输入内容！");
            return false;
        } else {
            if (id === null || id === '') {
                $.post(blog.URL.saveBlog(), {
                    'title':title,
                    'blogType.id':blogTypeId,
                    'content':content,
                    'contentNoTag':contentNoTag,
                    'summary':summary,
                    'keyWord':keyWord
                }, function(result){
                    if(result.success){
                        layer.msg("博客发布成功！");
                        resetValue();
                    } else{
                        layer.alert("博客发布失败！");
                    }
                },"json");
            } else {
                $.post(blog.URL.updateBlog(id), {
                    'id': id,
                    'title': title,
                    'blogType.id': blogTypeId,
                    'content': content,
                    'contentNoTag':contentNoTag,
                    'summary':summary,
                    'keyWord': keyWord
                }, function (result) {
                    if (result.success) {
                        layer.msg("博客修改成功！");
                        resetValue();
                    } else {
                        layer.alert("博客修改失败！");
                    }
                }, "json");
            }

        }
    }

    function resetValue() {
        $("#title").val("");
        $("#blogTypeId").combobox("setValue", "");
        editor.setMarkdown("");
        $("#keyWord").val("");
    }

</script>
</body>
</html>