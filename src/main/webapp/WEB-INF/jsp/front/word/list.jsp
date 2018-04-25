<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
    <style type="text/css">

        * {
            word-break: break-all;
        }

        .say-board {
            width: 100%;
            border: 2px solid rgba(119,195,218,0.3);
            border-radius: 10px;
            padding: 1%;
            margin-top: 10px;
            display: inline-block;
            clear: both;
        }

        .head {
            width: 13%;
            float: left;
        }

        .content {
            margin: 2%;
            width: 65%;
            text-indent: 35px;
            float: left;
        }

        .other {
            width: 13%;
            margin-top: 2.5%;
            float: left;
        }

        .date-time {
            display: block;
            width: 90px;
            height: 25px;
            line-height: 25px;
            background: #ee5a5a;
            border-radius: 5px;
            text-align: center;
            color: #fff;
            font-size: 1.2em;
        }
    </style>
</head>
<body>
    <div class="data_list">
        <div class="data_list_title">
            <div class="say-board">
                <div class="head">
                    <img src="${pageContext.request.contextPath }/static/front/word/image/01.jpg" class="img-circle">
                </div>
                <div class="content">
                    <p>有时候缺的就是破釜沉舟的勇气，把自己逼一逼，才知道自己有多大潜力！</p>
                </div>
                <div class="other">
                    <p class="author">作者：刘小宝</p>
                    <p class="date-time">2017-03-18</p>
                </div>
            </div>
            <div class="say-board">
                <div class="head">
                    <img src="${pageContext.request.contextPath }/static/front/word/image/02.jpg" class="img-circle">
                </div>
                <div class="content"><p>在这世界上，不管你做得在怎么好，总会有人不会认同你的做法。只有无视他们的存在，才会走得更远。</p></div>
                <div class="other">
                    <p class="author">作者：刘小宝</p>
                    <p class="date-time">2017-03-18</p>
                </div>
            </div>
        </div>
    </div>
</body>
</html>
