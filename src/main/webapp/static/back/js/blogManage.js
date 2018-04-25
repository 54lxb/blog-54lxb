/**
 * 增删改查博客管理
 * Created by Andy on 2017/3/15.
 */
var blog = {

    URL: {
        queryList: function () {
            return "/admin/list"
        },
        queryOne: function (id) {
            return "/article/" + id + "/detail";
        },
        saveBlog: function () {
            return "/admin/article/save";
        },
        updateBlog: function(id) {
            return "/admin/article/"+id+"/update";
        },
        deleteCheck: function () {
            return "/admin/article/delete";
        },
        findById: function(id) {
            return "/admin/article/" + id + "/detail";
        },
        modifyBlog: function (id) {
            return "article/" + id + "/modify";
        }
    },
    ToolBox: {

        formatBlogType: function (val) {
            return val.typeName;
        },
        formatTitle: function(val, row) {
            var url = blog.URL.queryOne(row.id);
            return "<a target='_blank' href='"+url+"'>" + val + "</a>"
        },
        searchBlog: function() {
            $("#dg").datagrid('load', {
                "title": $("#s_title").val()
            });
        },
        deleteBlog: function () {
            var selectedRows = $("#dg").datagrid("getSelections");
            if (selectedRows.length === 0) {
                $.messager.alert("系统提示", "请选择要删除的数据！");
                return;
            }
            var strIds = [];
            for (var i = 0; i < selectedRows.length; i++) {
                strIds.push(selectedRows[i].id);
            }
            var ids = strIds.join(",");
            var message = "您确定要删除这<font color='red'>" + selectedRows.length + "</font>条数据吗？";
            $.messager.confirm("系统提示", message, function (sure) {
                if (sure) {
                    $.post(blog.URL.deleteCheck(), {ids: ids}, function (result) {
                        if (result.success) {
                            $.messager.alert("系统提示", "数据已成功删除！");
                            $("#dg").datagrid("reload");
                        } else {
                            $.messager.alert("系统提示", "数据删除失败！");
                        }
                    }, "json");
                }
            });
        },
        openBlogModifyTab: function () {
            var selectedRows = $("#dg").datagrid("getSelections");
            if (selectedRows.length !== 1) {
                $.messager.alert("系统提示", "请选择一个要修改的博客！");
                return;
            }
            var row = selectedRows[0];
            window.parent.main.ToolBox.openTab('修改博客', row.id,'modifyBlog', 'icon-writeblog');
        },
        submitData: function (id) {
            var title = $("#title").val();
            var blogTypeId = $("#blogTypeId").combobox("getValue");
            var content = UE.getEditor('editor').getContent();
            var keyWord = $("#keyWord").val();

            if (title === null || title === '') {
                alert("请输入标题！");
            } else if (blogTypeId === null || blogTypeId === '') {
                alert("请选择博客类别！");
            } else if (content === null || content === '') {
                alert("请输入内容！");
            } else {
                if (id === null || id === '') {
                    $.post(blog.URL.saveBlog(), {
                        'title':title,
                        'blogType.id':blogTypeId,
                        'content':content,
                        'contentNoTag':UE.getEditor('editor').getContentTxt(),
                        'summary':UE.getEditor('editor').getContentTxt().substr(0,155),
                        'keyWord':keyWord
                    }, function(result){
                        if(result.success){
                            alert("博客发布成功！");
                            blog.ToolBox.resetValue();
                        } else{
                            alert("博客发布失败！");
                        }
                    },"json");
                } else {
                    $.post(blog.URL.updateBlog(id), {
                        'id': id,
                        'title': title,
                        'blogType.id': blogTypeId,
                        'content': content,
                        'contentNoTag': UE.getEditor('editor').getContentTxt(),
                        'summary': UE.getEditor('editor').getContentTxt().substr(0, 155),
                        'keyWord': keyWord
                    }, function (result) {
                        if (result.success) {
                            alert("博客修改成功！");
                            blog.ToolBox.resetValue();
                        } else {
                            alert("博客修改失败！");
                        }
                    }, "json");
                }

            }
        },
        resetValue: function () {
            $("#title").val("");
            $("#blogTypeId").combobox("setValue", "");
            UE.getEditor('editor').setContent("");
            $("#keyWord").val("");
        }
    }
};