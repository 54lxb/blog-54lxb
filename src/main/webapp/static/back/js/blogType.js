/**
 * 博客类型工具类
 * Created by Andy on 2017/3/15.
 */
var url;

var blogType = {

    URL: {
        delete: function () {
            return "/admin/blogType/delete";
        },
        add: function () {
            return "/admin/blogType/save"
        },
        update: function (id) {
            return "/admin/blogType/save?id=" + id;
        },
        list: function () {
            return "/admin/blogType/list"
        }
    },
    Tool: {
        closeBlogTypeDialog: function () {
            $("#dlg").dialog("close");
            $("#typeName").val("");
            $("#orderNo").val("");
        },
        openBlogTypeAddDialog: function () {
            $("#dlg").dialog("open").dialog("setTitle", "添加博客类别信息");
            url = blogType.URL.add();
        },
        openBlogTypeModifyDialog: function () {
            var selectedRows = $("#dg").datagrid("getSelections");
            if (selectedRows.length !== 1) {
                $.messager.alert("系统提示", "请选择一条要编辑的数据！");
                return;
            }
            var row = selectedRows[0];
            $("#dlg").dialog("open").dialog("setTitle", "编辑博客类别信息");
            $("#fm").form("load", row);
            url = blogType.URL.update(row.id);
        },
        saveBlogType: function () {
            $("#fm").form("submit", {
                url: url,
                onSubmit: function () {
                    return $(this).form("validate");
                },
                success: function (data) {
                    var result = eval('(' + data + ')');
                    if (result.success) {
                        $.messager.alert("系统提示", "保存成功！");
                        blogType.Tool.resetValue();
                        $("#dlg").dialog("close");
                        $("#dg").datagrid("reload");
                    } else {
                        $.messager.alert("系统提示", "保存失败！");
                    }
                }
            });
        },
        deleteBlogType: function () {
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
            $.messager.confirm("系统提示", "您确定要删除这<font color=red>" + selectedRows.length + "</font>条数据吗？", function (r) {
                if (r) {
                    $.post(blogType.URL.delete(), {ids: ids}, function (data) {
                        if (data.success) {
                            if (data.exist) {
                                $.messager.alert("系统提示", data.exist);
                            } else {
                                $.messager.alert("系统提示", "数据已成功删除！");
                            }
                            $("#dg").datagrid("reload");
                        } else {
                            $.messager.alert("系统提示", "数据删除失败！");
                        }
                    }, "json");
                }
            });
        },
        resetValue: function() {
            $("#typeName").val("");
            $("#orderNo").val("");
        }
    }
};
