/**
 * 后台主界面js
 * Created by Andy on 2017/3/15.
 */
var url;

var main = {

    URL: {
        modifyPassword: function(id) {
            return "/admin/blogger/modifyPassword?id=" + id;
        },
        refreshSystem: function() {
            return "/admin/system/refreshSystem";
        },
        logout: function () {
            return "/admin/blogger/logout";
        },
        getContent: function(name,value) {
            return "<iframe frameborder=0 scrolling='auto' style='width:100%;height:100%' src='/admin/" + name + "/" + value + "'></iframe>";
        }
    },
    ToolBox: {
        openTab: function(text, name, value, iconCls) {
            var Tables = $("#tabs");
            if (Tables.tabs("exists", text)) {
                Tables.tabs("select", text);
            } else {
                Tables.tabs("add", {
                    title: text,
                    iconCls: iconCls,
                    closable: true,
                    content: main.URL.getContent(name, value)
                });
            }
        },
        openPasswordModifyDialog: function (id) {
            $("#dlg").dialog("open").dialog("setTitle", "修改密码");
            url = main.URL.modifyPassword(id);
        },
        modifyPassword: function() {
            $("#fm").form("submit", {
                url: url,
                onSubmit: function () {
                    var newPassword = $("#newPassword").val();
                    var newPassword2 = $("#newPassword2").val();
                    if (!$(this).form("validate")) {
                        return false;
                    }
                    if (newPassword !== newPassword2) {
                        $.messager.alert("系统提示", "确认密码输入错误！");
                        return false;
                    }
                    return true;
                },
                success: function (result) {
                    var data = eval('(' + result + ')');
                    if (data.success) {
                        $.messager.alert("系统提示", "密码修改成功，下一次登录生效！");
                        main.ToolBox.resetValue();
                        $("#dlg").dialog("close");
                    } else {
                        $.messager.alert("系统提示", "密码修改失败！");
                    }
                }
            });

        },
        closePasswordModifyDialog: function() {
            main.ToolBox.resetValue();
            $("#dlg").dialog("close");
        },
        resetValue: function() {
            $("#oldPassword").val("");
            $("#newPassword").val("");
            $("#newPassword2").val("");
        },
        logout: function() {
            $.messager.confirm("系统提示", "您确定要退出系统吗？", function (r) {
                if (r) {
                    window.location.href = main.URL.logout();
                }
            });
        },
        refreshSystem: function() {
            $.post(main.URL.refreshSystem(), {}, function (result) {
                if (result.success) {
                    $.messager.alert("系统提示", "已成功刷新系统缓存！");
                } else {
                    $.messager.alert("系统提示", "刷新系统缓存失败！");
                }
            }, "json");
        }

    }
};
