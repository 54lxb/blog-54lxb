<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<c:set var="baseURL" value="${pageContext.request.contextPath}"/>
<style type="text/css">
    .progress {
        height: 20px;
        background: #ebebeb;
        border-left: 1px solid transparent;
        border-right: 1px solid transparent;
        border-radius: 10px;
    }

    .progress > span {
        position: relative;
        float: left;
        margin: 0 -1px;
        min-width: 30px;
        height: 18px;
        line-height: 16px;
        text-align: right;
        background: #cccccc;
        border: 1px solid;
        border-color: #bfbfbf #b3b3b3 #9e9e9e;
        border-radius: 10px;
        background-image: -webkit-linear-gradient(top, #f0f0f0 0%, #dbdbdb 70%, #cccccc 100%);
        background-image: -moz-linear-gradient(top, #f0f0f0 0%, #dbdbdb 70%, #cccccc 100%);
        background-image: -o-linear-gradient(top, #f0f0f0 0%, #dbdbdb 70%, #cccccc 100%);
        background-image: linear-gradient(to bottom, #f0f0f0 0%, #dbdbdb 70%, #cccccc 100%);
        -webkit-box-shadow: inset 0 1px rgba(255, 255, 255, 0.3), 0 1px 2px rgba(0, 0, 0, 0.2);
        box-shadow: inset 0 1px rgba(255, 255, 255, 0.3), 0 1px 2px rgba(0, 0, 0, 0.2);
    }

    .progress > span > span {
        padding: 0 8px;
        font-size: 11px;
        font-weight: bold;
        background: none;
        color: #404040;
        color: rgba(0, 0, 0, 0.7);
        text-shadow: 0 1px rgba(255, 255, 255, 0.4);
    }

    .progress > span:before {
        content: '';
        position: absolute;
        top: 0;
        bottom: 0;
        left: 0;
        right: 0;
        z-index: 1;
        height: 18px;
        border-radius: 10px;
    }

    .progress .green {
        background: #85c440;
        border-color: #78b337 #6ba031 #568128;
        background-image: -webkit-linear-gradient(top, #b7dc8e 0%, #99ce5f 70%, #85c440 100%);
        background-image: -moz-linear-gradient(top, #b7dc8e 0%, #99ce5f 70%, #85c440 100%);
        background-image: -o-linear-gradient(top, #b7dc8e 0%, #99ce5f 70%, #85c440 100%);
        background-image: linear-gradient(to bottom, #b7dc8e 0%, #99ce5f 70%, #85c440 100%);
    }

    .progress .red {
        background: #db3a27;
        border-color: #c73321 #b12d1e #8e2418;
        background-image: -webkit-linear-gradient(top, #ea8a7e 0%, #e15a4a 70%, #db3a27 100%);
        background-image: -moz-linear-gradient(top, #ea8a7e 0%, #e15a4a 70%, #db3a27 100%);
        background-image: -o-linear-gradient(top, #ea8a7e 0%, #e15a4a 70%, #db3a27 100%);
        background-image: linear-gradient(to bottom, #ea8a7e 0%, #e15a4a 70%, #db3a27 100%);
    }

    .progress .orange {
        background: #f2b63c;
        border-color: #f0ad24 #eba310 #c5880d;
        background-image: -webkit-linear-gradient(top, #f8da9c 0%, #f5c462 70%, #f2b63c 100%);
        background-image: -moz-linear-gradient(top, #f8da9c 0%, #f5c462 70%, #f2b63c 100%);
        background-image: -o-linear-gradient(top, #f8da9c 0%, #f5c462 70%, #f2b63c 100%);
        background-image: linear-gradient(to bottom, #f8da9c 0%, #f5c462 70%, #f2b63c 100%);
    }

    .progress .blue {
        background: #5aaadb;
        border-color: #459fd6 #3094d2 #277db2;
        background-image: -webkit-linear-gradient(top, #aed5ed 0%, #7bbbe2 70%, #5aaadb 100%);
        background-image: -moz-linear-gradient(top, #aed5ed 0%, #7bbbe2 70%, #5aaadb 100%);
        background-image: -o-linear-gradient(top, #aed5ed 0%, #7bbbe2 70%, #5aaadb 100%);
        background-image: linear-gradient(to bottom, #aed5ed 0%, #7bbbe2 70%, #5aaadb 100%);
    }


</style>
<div class="data_list">
    <div class="data_list_title">
        <img src="${baseURL}/static/common/images/about_icon.png"/>
        关于博主
    </div>
    <div style="padding: 30px">
        ${applicationScope.blogger.proFile }
    </div>
    <section class="skills">
        <hr>
        <div class="data_list_title">
            <img src="${baseURL}/static/common/images/skills.png" width="2%"/>
            个人技能
        </div>

        <div class="progress" style="margin-top: 3%">
            <span class="green" style="width: 50%;"><span>JS</span></span>
        </div>

        <div class="progress">
            <span style="width: 40%;"><span>HTML</span></span>
        </div>

        <div class="progress">
            <span class="orange" style="width: 20%;"><span>CSS</span></span>
        </div>

        <div class="progress">
            <span class="red" style="width: 60%;"><span>Linux</span></span>
        </div>

        <div class="progress">
            <span class="blue" style="width: 70%;"><span>Java</span></span>
        </div>

        <div class="progress">
            <span class="green" style="width: 45%;"><span>Shiro</span></span>
        </div>

        <div class="progress">
            <span style="width: 10%;"><span>微信小程序</span></span>
        </div>

        <div class="progress">
            <span class="orange" style="width: 65%;"><span>SpringMVC</span></span>
        </div>

        <div class="progress">
            <span class="red" style="width: 60%;"><span>Spring</span></span>
        </div>

        <div class="progress">
            <span class="blue" style="width: 50%;"><span>MyBatis</span></span>
        </div>

        <div class="progress">
            <span style="width: 30%;"><span>Lucene</span></span>
        </div>

        <div class="progress">
            <span class="green" style="width: 30%;"><span>Bootstrap</span></span>
        </div>

        <div class="progress">
            <span style="width: 30%;"><span>EasyUi</span></span>
        </div>
    </section>
    <hr>
    <section style="text-align: center">
        <object type="application/x-shockwave-flash" style="outline:none; width: 30%"
                data="${baseURL }/static/common/swf/hamster.swf">
            <param name="movie" value="swf/hamster.swf">
            <param name="AllowScriptAccess" value="always">
            <param name="wmode" value="opaque">
        </object>
        <object type="application/x-shockwave-flash" style="outline: none; width: 30%"
                data="${baseURL }/static/common/swf/hamster.swf">
            <param name="movie" value="swf/hamster.swf">
            <param name="AllowScriptAccess" value="always">
            <param name="wmode" value="opaque">
        </object>
        <object type="application/x-shockwave-flash" style="outline:none;width: 30%"
                data="${baseURL }/static/common/swf/hamster.swf">
            <param name="movie" value="swf/hamster.swf">
            <param name="AllowScriptAccess" value="always">
            <param name="wmode" value="opaque">
        </object>
    </section>
</div>