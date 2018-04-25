/**
 * 友情链接管理js工具类
 * Created by Andy on 2017/3/15.
 */
var url;

var link = {

    URL: {
        saveLink: function () {
            return "/admin/link/save";
        },
        deleteLink: function () {
            return "/admin/link/delete";
        },
        modifyLink: function (id) {
            return "/admin/link/save?id=" + id;
        }
    },
    TOolBox: {
        deleteLink: function () {
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
                    $.post(link.URL.deleteLink(), {ids: ids}, function (result) {
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
        openLinkAddDialog: function () {
            $("#dlg").dialog("open").dialog("setTitle", "添加友情链接信息");
            url = link.URL.saveLink();
        },
        openLinkModifyDialog: function () {
            var selectedRows = $("#dg").datagrid("getSelections");
            if (selectedRows.length !== 1) {
                $.messager.alert("系统提示", "请选择一条要编辑的数据！");
                return;
            }
            var row = selectedRows[0];
            $("#dlg").dialog("open").dialog("setTitle", "编辑友情链接信息");
            $("#fm").form("load", row);
            url = link.URL.modifyLink(row.id);
        },
        saveLink: function () {
            $("#fm").form("submit", {
                url: url,
                onSubmit: function () {
                    return $(this).form("validate");
                },
                success: function (result) {
                    var data = eval('(' + result + ')');
                    if (data.success) {
                        $.messager.alert("系统提示", "保存成功！");
                        link.TOolBox.resetValue();
                        $("#dlg").dialog("close");
                        $("#dg").datagrid("reload");
                    } else {
                        $.messager.alert("系统提示", "保存失败！");
                    }
                }
            });
        },
        resetValue: function () {
            $("#linkName").val("");
            $("#linkUrl").val("");
            $("#orderNo").val("");
        },
        closeLinkDialog: function () {
            $("#dlg").dialog("close");
            link.TOolBox.resetValue();
        }
    }

};

