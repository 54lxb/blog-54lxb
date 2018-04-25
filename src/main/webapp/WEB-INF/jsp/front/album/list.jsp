<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <title>相册展示-个人博客</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/static/front/album/css/album.css"/>
</head>
<body>
<div class="data_list">
    <div class="data_list_title">
        <div id="grid-gallery" class="grid-gallery">
            <section class="grid-wrap">
                <ul class="grid">
                    <li class="grid-sizer"></li>
                    <li>
                        <figure>
                            <img src="${pageContext.request.contextPath}/static/front/album/image/01.jpg"/>
                            <figcaption><h3>世界很大，我想去看看！</h3></figcaption>
                        </figure>
                    </li>
                    <li>
                        <figure>
                            <img src="${pageContext.request.contextPath}/static/front/album/image/02.jpg"/>
                            <figcaption><h3>理想很丰满，现实很骨感！</h3></figcaption>
                        </figure>
                    </li>
                    <li>
                        <figure>
                            <img src="${pageContext.request.contextPath}/static/front/album/image/03.jpg"/>
                            <figcaption><h3>时过境迁,物是人非!</h3></figcaption>
                        </figure>
                    </li>
                    <li>
                        <figure>
                            <img src="${pageContext.request.contextPath}/static/front/album/image/04.jpg"/>
                            <figcaption><h3>世界很大，我想去看看！</h3></figcaption>
                        </figure>
                    </li>
                </ul>
            </section>
            <!-- // grid-wrap -->
            <section class="slideshow">
                <ul>
                    <li>
                        <figure>
                            <figcaption>
                                <h3>世界很大，我想去看看！</h3>
                                <p>
                                    我喜欢雪的美，却不会深究它的来处，只是喜欢简单地看着它从天而落，在飘，似舞。更不会计较它最后归于何处，是融化成水流入泥沟？还是直接寒化为冰？融于无形。就像是人生，我们不会计较起点和终点，只会在意路途中的风景和故事。不寻何处来，不往迷处走，就是我们必须谨记的，也务必做到的。</p>
                            </figcaption>
                            <img src="${pageContext.request.contextPath}/static/front/album/image/01.jpg"/>
                        </figure>
                    </li>
                    <li>
                        <figure>
                            <figcaption>
                                <h3>理想很丰满，现实很骨感！</h3>
                                <p>
                                    人生啊，你太匆匆，我想要的，你没有答应。我不想要的，都在我门外等。这旅途啊，你也太无情，我停留的一霎，乍一转身，便是天涯。</p>
                            </figcaption>
                            <img src="${pageContext.request.contextPath}/static/front/album/image/02.jpg"/>
                        </figure>
                    </li>
                    <li>
                        <figure>
                            <figcaption>
                                <h3>时过境迁,物是人非!</h3>
                                <p>
                                    我会在某个风雨敲窗的夜晚，忽然想起多年前的玩伴，如今不知你过得怎样了。也会想起逝去的亲人，曾经的一幕幕，都在心里流转，人生本就是没来由的。一些事不论你愿不愿意，它就突然窜上心扉，让我们唏嘘慨叹。</p>
                            </figcaption>
                            <img src="${pageContext.request.contextPath}/static/front/album/image/03.jpg"/>
                        </figure>
                    </li>
                    <li>
                        <figure>
                            <figcaption>
                                <h3>时光迅疾，世事万变。</h3>
                                <p>
                                    我们经历的许多故事不过是时光撒下的谎言，但这些谎言却让我们追逐一生，也追忆了一生，无怨无悔。若把苦涩尝遍，总会苦尽来甘。</p>
                            </figcaption>
                            <img src="${pageContext.request.contextPath}/static/front/album/image/04.jpg"/>
                        </figure>
                    </li>
                </ul>
                <nav>
                    <span class="icon nav-prev"></span>
                    <span class="icon nav-next"></span>
                    <span class="icon nav-close"></span>
                </nav>
            </section>
        </div>
    </div>
</div>
<script src="${pageContext.request.contextPath }/static/front/album/script/modernizr.custom.js"></script>
<script src="${pageContext.request.contextPath }/static/front/album/script/imagesloaded.pkgd.min.js"></script>
<script src="${pageContext.request.contextPath }/static/front/album/script/masonry.pkgd.min.js"></script>
<script src="${pageContext.request.contextPath }/static/front/album/script/classie.js"></script>
<script src="${pageContext.request.contextPath }/static/front/album/script/cbpGridGallery.js"></script>
<script type="text/javascript">
    new CBPGridGallery(document.getElementById('grid-gallery'));
</script>
</body>
</html>