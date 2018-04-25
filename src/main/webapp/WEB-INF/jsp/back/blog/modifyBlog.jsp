<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="baseURL" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>修改博客页面</title>
    <link rel="stylesheet" type="text/css" href="${baseURL}/static/frame/jquery-easyui-1.3.3/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="${baseURL}/static/frame/jquery-easyui-1.3.3/themes/icon.css">
    <link rel="stylesheet" href="${baseURL}/static/frame/editormd-1.5.0/css/editormd.css">
</head>
<body style="margin: 10px">
<div id="p" class="easyui-panel" title="修改博客" style="padding: 10px">
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
                <div id="editormd" class="content">
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
                <a href="javascript:blog.ToolBox.submitData('${id}')" class="easyui-linkbutton"
                   data-options="iconCls:'icon-submit'">发布博客</a>
            </td>
        </tr>
    </table>
</div>
<script type="text/javascript" src="${baseURL}/static/frame/jquery-easyui-1.3.3/jquery.min.js"></script>
<script type="text/javascript" src="${baseURL}/static/frame/jquery-easyui-1.3.3/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${baseURL}/static/frame/jquery-easyui-1.3.3/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="${baseURL}/static/frame/editormd-1.5.0/js/editormd.min.js"></script>
<script type="text/javascript" src="${baseURL}/static/back/js/blogManage.js"></script>
<script type="text/javascript">
    //实例化编辑器
    $(function() {
        var editor = editormd({
            id:"editormd", //编辑(格式化)的ID
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
            dialogLockScreen : false,
            lineNumbers: false,
            codeFold : true,
            autoFocus: false,
            emoji: true,
            tex : true,                   // 开启科学公式TeX语言支持，默认关闭
            flowChart : true,             // 开启流程图支持，默认关闭
            sequenceDiagram : true,       // 开启时序/序列图支持，默认关闭,
            imageUpload : true,
            imageFormats : ["jpg", "jpeg", "gif", "png", "bmp", "webp"],
            imageUploadURL : "/admin/article/imageUpload",
            onload : function() {
                $.ajax({
                    url: blog.URL.findById("${id}"),
                    method: "get",
                    async: false,
                    success: function (result) {
                        console.log(result)
                        console.log(result.blogType)
                        $("#title").val(result.title);
                        $("#keyWord").val(result.keyWord);
                        $("#blogTypeId").combobox("setValue", result.blogType);
                        $('#editormd').html(result.content);
                        editor.setContent(result.content)
                    }
                });
            }
        });
    });

</script>
</body>
</html>

