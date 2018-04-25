/**
 * 评论管理js工具类
 * Created by Andy on 2017/3/15.
 */

var comment = {
    URL: {
        deleteComment: function() {
            return "/admin/comment/delete";
        },
        reviewComment: function() {
            return "/admin/comment/review";
        }
    },
    ToolBox: {
        deleteComment: function() {
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
                    $.post(comment.URL.deleteComment(), {ids: ids}, function (result) {
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
        formatBlogTitle: function(val) {
            if (!val) {
                return "<font color='red'>该博客已被删除！</font>";
            } else {
                return "<a target='_blank' href='/article/" + val.id + "/detail'>" + val.title + "</a>";
            }
        },
        formatState: function(val) {
            if (val === 0) {
                return "待审核";
            } else if (val === 1) {
                return "审核通过";
            } else if (val === 2) {
                return "审核未通过";
            }
        },
        commentReview: function(state) {
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
            $.messager.confirm("系统提示", "您确定要审核这<font color=red>" + selectedRows.length + "</font>条评论吗？", function (r) {
                if (r) {
                    $.post(comment.URL.reviewComment(), {
                        ids: ids,
                        state: state
                    }, function (result) {
                        if (result.success) {
                            $.messager.alert("系统提示", "提交成功！");
                            $("#dg").datagrid("reload");
                        } else {
                            $.messager.alert("系统提示", "提交失败！");
                        }
                    }, "json");
                }
            });
        }
    }
};

