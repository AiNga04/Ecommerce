<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ include file="/common/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <!-- Site meta -->
    <meta charset="utf-8">
    <meta name="viewport"
          content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" type="text/css" href="home.css"/>
    <link rel="stylesheet"
          href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css"/>
</head>
<body>
<div class="home">
    <c:if test="${orderMessage != null }">
        <div id="toast">
            <div class="toast toast--success">
                <div class="toast__icon">
                    <i class="fas fa-check-circle"></i>
                </div>
                <div class="toast__body">
                    <h3 class="toast__title">Success</h3>
                    <p class="toast__msg">${orderMessage }</p>
                </div>
                <a href="/order" class="btn btn-warning ms-2"
                   style="font-size: 12px !important">Xem đơn hàng</a>
                <div class="toast__close">
                    <i class="fas fa-times"></i>
                </div>
            </div>
        </div>
    </c:if>
    <div id="homeSlider" class="carousel slide" data-ride="carousel">
        <!--Indicators-->
        <ul class="carousel-indicators">
            <li data-target="#homeSlider" data-slide-to="0" class="active"></li>
            <li data-target="#homeSlider" data-slide-to="1"></li>
            <li data-target="#homeSlider" data-slide-to="2"></li>
        </ul>
        <!--SlideShow-->
        <div class="carousel-inner">
            <div class="carousel-item active c-item">
                <img class="c-img"
                     src="https://cdn.hoanghamobile.com/i/home/Uploads/2024/12/06/xa-kho-cuoi-tuan-web.jpg">
            </div>
            <div class="carousel-item c-item">
                <img class="c-img"
                     src="https://cdn.hoanghamobile.com/i/home/Uploads/2024/11/23/banner-iphone-16-series-01.jpg">
            </div>
            <div class="carousel-item c-item">
                <img class="c-img"
                     src="https://cdn.hoanghamobile.com/i/home/Uploads/2024/12/03/mac-mini-banner-website-desktop-1.png">
            </div>
            <div class="carousel-item c-item">
                <img class="c-img"
                     src="https://cdn.hoanghamobile.com/i/home/Uploads/2024/12/04/thu-cu-14t-9-990-u_638689063997142381.png">
            </div>
            <div class="carousel-item c-item">
                <img class="c-img"
                     src="https://cdn.hoanghamobile.com/i/home/Uploads/2024/12/06/a1-web_638690961923110012.jpg">
            </div>
        </div>

        <!--Controls-->
        <a class="carousel-control-prev" href="#homeSlider" data-slide="prev">
            <span class="carousel-control-prev-icon"></span>
        </a> <a class="carousel-control-next" href="#homeSlider"
                data-slide="next"> <span class="carousel-control-next-icon"></span>
    </a>
    </div>
    <div class="introduce">
        <div class="row">
            <div class="col">
                <div class="introduce-item">
                    <img class="img-introduce"
                         src="https://cdn-img-v2.mybota.vn/uploadv2/web/82/8269/product/2024/09/20/08/25/1726796702_apple-iphone-16-pro-finish-lineup-240909_big.jpg"/>
                </div>
            </div>
            <div class="col">
                <div class="w-100 d-flex align-items-center justify-content-center"
                     style="height: 100%">
                    <div class="introduce-item">
                        <div class="introduce-title">Câu chuyện thương hiệu</div>
                        <p class="home-desc">Được chế tác và chăm chút tỉ mỉ, mỗi chiếc điện thoại mang trong mình sự
                            kết tinh từ công nghệ tiên tiến, thiết kế tinh tế, và sự hiểu biết sâu sắc về nhu cầu người
                            dùng. Từ những linh kiện chất lượng cao, trải qua quy trình lắp ráp hiện đại, đến việc kiểm
                            định khắt khe, chúng tôi mang đến những sản phẩm không chỉ mạnh mẽ, bền bỉ mà còn hoàn hảo
                            trong từng chi tiết. Điện thoại của chúng tôi không chỉ là một thiết bị, mà còn là cầu nối
                            giữa bạn và thế giới, nơi công nghệ hòa quyện cùng sự sáng tạo, mở ra vô vàn cơ hội mới mỗi
                            ngày.</p>
<%--                        <a href="https://homitatea.com/about/cau-chuyen-thuong-hieu.html"--%>
                        <a href="/info"
                           class="btn-explore">Khám phá</a>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <section>
        <div class="ads">
            <a href="#!" target="_top"><img
                    src="https://raw.githubusercontent.com/AiNga04/image/refs/heads/main/blackFriday.gif"
                    style="width: 100%"/></a>
        </div>
    </section>

    <section class="quick__advertise">
        <div class="quick-sales">
            <div class="cardcard">
                <a href="#!" target="_blank">
                    <img src="https://static2-images.vnncdn.net/files/publish/2022/10/5/iphone-ifold-1081.gif"
                         title="iPhone gập" alt="iPhone gập"/>
                </a>
            </div>
            <div class="cardcard">
                <a href="#!" target="_blank">
                    <img src="https://github.com/AiNga04/image/blob/main/s23-ultra.gif?raw=true"
                         title="Galaxy S23 Ultra" alt="Galaxy S23 Ultra"/>
                </a>
            </div>
            <div class="cardcard">
                <a href="#!" target="_blank">
                    <img src="https://www.icegif.com/wp-content/uploads/2023/01/icegif-1907.gif"
                         title="Galaxy S23 Ultra" alt="Galaxy S23 Ultra"/>
                </a>
            </div>
            <div class="cardcard">
                <a href="#!" target="_blank">
                    <img src="https://static-images.vnncdn.net/files/publish/2023/5/28/iphone-15-ultra-14.gif"
                         title="Galaxy S23 Ultra" alt="Galaxy S23 Ultra"/>
                </a>
            </div>
        </div>
    </section>

    <div class="overlay" id="advertisement">
        <div class="popup">
            <!-- Nút đóng -->
            <button class="close-btn" onclick="closePopup()">×</button>
            <!-- Hình ảnh quảng cáo -->
            <img src="https://hoanghamobile.com/Uploads/2024/12/05/xiaomi-redmi-note-13-popup-desktop.jpg"
                alt="Quảng cáo Xiaomi">
        </div>
    </div>

    <div class="products">
        <p class="products-title">Sản phẩm bán chạy</p>
        <div class="row gx-1">
            <c:forEach var="milkTea" items="${list1}">
                <div class="col col-1-2">
                    <a href="/product_detail/${milkTea.idMilkTea}"
                       class="d-block card outstanding-item"> <span
                            class="outstanding-title">BEST SELLER</span>
                        <div class="img-container">
                            <c:url
                                    value="/home/image/${milkTea.image != null ? milkTea.image : null }"
                                    var="imgUrl"/>
                            <img src="${imgUrl}" class="card-image card-img-top"/>
                        </div>
                        <div class="card-body">
                            <h5 class="card-title">${milkTea.name}</h5>
                            <p class="card-price">${milkTea.cost}đ</p>
                        </div>
                    </a>
                </div>
            </c:forEach>
        </div>
    </div>
    <div class="products pb-4">
        <p class="products-title">Sản phẩm cửa hàng</p>
        <div class="row gx-1">
            <c:forEach var="milkTea" items="${list2}">
                <div class="col col-1-2">
                    <a href="/product_detail/${milkTea.idMilkTea}" class="card">
                        <div class="img-container">
                            <c:url
                                    value="/home/image/${milkTea.image != null ? milkTea.image : null }"
                                    var="imgUrl"/>
                            <img src="${imgUrl}" class="card-image card-img-top"/>
                        </div>
                        <div class="card-body">
                            <h5 class="card-title">${milkTea.name}</h5>
                            <p class="card-price">${milkTea.cost}đ</p>
                        </div>
                    </a>
                </div>
            </c:forEach>
        </div>
    </div>
</div>
<script>
    function closePopup() {
        document.getElementById('advertisement').style.display = 'none';
    }
    window.onload = function () {
        document.getElementById('advertisement').style.display = 'flex';
    };
</script>
<script type="text/javascript"
        src='<c:url value="/user/js/toast.js" />'></script>
</body>
</html>
