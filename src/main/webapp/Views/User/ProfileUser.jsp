<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0 , maximum-scale=1.0, user-scalable=0"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <title>Bán Giày</title>

    <meta name="title" content="Kiet Rider"/>
    <meta name="description" content=""/>
    <meta name="keyword" content=""/>

    <link rel="canonical" href="https://cfdcircle.vn"/>
    <link rel="icon" href="favicon.ico"/>

    <!-- Meta Tag Facebook -->
    <meta property="og:locale" content="en"/>
    <meta property="og:type" content="article"/>
    <meta property="og:title" content="Kiet Rider"/>
    <meta property="og:description" content=""/>
    <meta property="og:url" content=""/>
    <meta property="og:site_name" content="Kiet Rider"/>
    <meta property="og:image" content=""/>

    <!-- Meta Tag Twitter -->
    <meta name="twitter:card" content=""/>
    <meta name="twitter:site" content=""/>
    <meta name="twitter:title" content=""/>
    <meta name="twitter:description" content=""/>
    <meta name="twitter:image" content=""/>

    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css"/>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/Views/User/dest/style.min.css/"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/Views/User/dest/fonts.css"/>
</head>

<body class="profilepage">

<jsp:include page="component/Header.jsp"/>
<jsp:include page="component/ModalLogin.jsp"/>

<main class="main">
    <section class="warp">
        <div class="container">
            <div class="warp__order">
                <h3 class="title --h4">Đơn hàng đã mua</h3>
                <div class="warp__order--list">
                    <div class="order--item">
                        <div class="btns ">
                            <div class="order__info">
                                <span class="order__id">1</span>
                                <p class="order__number">Tổng số sản phẩm: <span>1</span></p>
                                <p class="order__date">Ngày đặt hàng: <span>11/01/2002</span></p>
                                <p class="order__totalprice">Tổng tiền: <span>100.000VND</span></p>
                            </div>
                            <div class="iconarrow">
                                <svg xmlns="http://www.w3.org/2000/svg" height="1em" viewBox="0 0 512 512">
                                    <path
                                            d="M233.4 406.6c12.5 12.5 32.8 12.5 45.3 0l192-192c12.5-12.5 12.5-32.8 0-45.3s-32.8-12.5-45.3 0L256 338.7 86.6 169.4c-12.5-12.5-32.8-12.5-45.3 0s-12.5 32.8 0 45.3l192 192z" />
                                </svg>
                            </div>
                        </div>
                        <div class="order--products">
                            <div class="products--item">
                                <span class="number__product">1</span>
                                <img src="./img/ao/ao-1-a.jpg" alt="ao-yame">
                                <p class="name__product">Tên: <span>ao thun mau do ba le asd</span></p>
                                <p class="color__product">Màu: <span>Trắng đỏ</span></p>
                                <p class="price__product">Giá: <span>25.000VND</span></p>
                                <p>Số lượng: <span>4</span></p>
                            </div>
                            <div class="products--item">
                                <span class="number__product">2</span>
                                <img src="./img/ao/ao-1-a.jpg" alt="ao-yame">
                                <p class="name__product">Tên: <span>ao thun mau do ba le asd</span></p>
                                <p class="color__product">Màu: <span>Trắng đỏ</span></p>
                                <p class="price__product">Giá: <span>300.000VND</span></p>
                                <p>Số lượng: <span>4</span></p>
                            </div>
                        </div>
                    </div>
                    <div class="order--item">
                        <div class="btns">
                            <div class="order__info">
                                <span class="order__id">1</span>
                                <p class="order__number">Tổng số sản phẩm: <span>1</span></p>
                                <p class="order__date">Ngày đặt hàng: <span>11/01/2002</span></p>
                                <p class="order__totalprice">Tổng tiền: <span>100.000VND</span></p>
                            </div>
                            <div class="iconarrow">
                                <svg xmlns="http://www.w3.org/2000/svg" height="1em" viewBox="0 0 512 512">
                                    <path
                                            d="M233.4 406.6c12.5 12.5 32.8 12.5 45.3 0l192-192c12.5-12.5 12.5-32.8 0-45.3s-32.8-12.5-45.3 0L256 338.7 86.6 169.4c-12.5-12.5-32.8-12.5-45.3 0s-12.5 32.8 0 45.3l192 192z" />
                                </svg>
                            </div>
                        </div>
                        <div class="order--products">
                            <div class="products--item">
                                <span class="number__product">1</span>
                                <img src="./img/ao/ao-1-a.jpg" alt="ao-yame">
                                <p class="name__product">Tên: <span>ao thun mau do ba le asd</span></p>
                                <p class="color__product">Màu: <span>Trắng đỏ</span></p>
                                <p class="price__product">Giá: <span>25.000VND</span></p>
                                <p>Số lượng: <span>4</span></p>
                            </div>
                            <div class="products--item">
                                <span class="number__product">2</span>
                                <img src="./img/ao/ao-1-a.jpg" alt="ao-yame">
                                <p class="name__product">Tên: <span>ao thun mau do ba le asd</span></p>
                                <p class="color__product">Màu: <span>Trắng đỏ</span></p>
                                <p class="price__product">Giá: <span>300.000VND</span></p>
                                <p>Số lượng: <span>4</span></p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="warp__profile">
                <h3 class="title --h4">Thông tin tài khoản</h3>
                <form action="" class="warp__profile--form">
                    <div class="box-form">
                        <div class="form-gr">
                            <span class="label" for="name-regis">Họ và tên</span>
                            <input class="input-form" type="text" name="name" id="name-regis"
                                   placeholder="Nhập Họ và tên" value="Đỗ Ngọc Lương Tuấn">
                            <p class="message-error error-name"></p>
                        </div>
                        <div class="form-gr">
                            <span class="label" for="phone-regis">Số điện thoại</span>
                            <input class="input-form" type="text" name="phone" id="phone-regis"
                                   placeholder="Nhập số điện thoại" value="0908773513">
                            <p class="message-error error-name"></p>
                        </div>
                    </div>
                    <div class="form-gr">
                        <span class="label" for="email-regis">Email</span>
                        <input class="input-form" type="text" name="email" id="email-regis"
                               placeholder="Nhập Email" value="dotuan2k2@gmail.com">
                        <p class="message-error error-name"></p>
                    </div>
                    <div class="form-gr">
                        <span class="label" for="address-regis">Địa chỉ</span>
                        <input class="input-form" type="text" name="address" id="address-regis"
                               placeholder="Nhập địa chỉ" value="266/108/27 P8 Tôn Đản Q4">
                        <p class="message-error error-name"></p>
                    </div>
                    <div class="form-gr">
                        <span class="label" for="password-regis">Mật khẩu</span>
                        <input class="input-form" type="password" name="password" id="password-regis"
                               placeholder="Nhập địa chỉ" value="">
                        <p class="message-error error-name"></p>
                    </div>
                    <button type="submit" class="btn btn-yellow">Cập Nhật </button>
                </form>
            </div>
        </div>
    </section>
</main>
<footer class="footer">
    <div class="container">
        <div class="footer__left">
            <span class="footer__left--title">về chúng tôi</span>
            <h3 class="name --h3">3TV là thương hiệu mới thành lập</h3>
            <p class="footer__left--desc ">
                © Bản quyền thuộc về 3TV
            </p>
        </div>
        <div class="footer__right">
            <div class="footer__right--contact">
                <h5 class="title --h5">Liên hệ</h5>
                <div class="contact-gr">
                    <span class="label">Điện thoại</span>
                    <a href="tel:0961290556">0961290556</a>
                </div>
                <div class="contact-gr">
                    <span class="label">Email</span>
                    <a href="mailto:Example@gmail.com">Example@gmail.com</a>

                </div>
                <div class="contact-gr">
                    <span class="label">Địa chỉ</span>
                    <a href="mailto:Example@gmail.com">273 an dương vương, phường 1 quận 5, TPHCM</a>

                </div>
                <div class="contact-gr">
                    <span class="label">Thứ 2 - Thứ 7</span>
                    <a>8h50 - 21h00</a>
                </div>
            </div>

            <div class="footer__right--suport">
                <h5 class="title --h5">Liên hệ</h5>
                <a href="">tài khoản</a>
                <a href="">chính sách đổi tả</a>
                <a href="">chính sách bảo mật</a>
                <a href="">hướng dẫn mua hàng</a>
            </div>
            <div class="footer__right--store">
                <h5 class="title --h5">hệ thống cửa hàng</h5>
                <span class="label">Địa chỉ</span>
                <a href="mailto:Example@gmail.com">273 an dương vương, phường 1 quận 5, TPHCM</a>

            </div>

        </div>
    </div>
</footer>

<!-- Javascript -->
<script src="${pageContext.request.contextPath}/Views/User/dest/jsmain.min.js"></script>
<script src="${pageContext.request.contextPath}/Views/User/dest/main.js"></script>
</body>

</html>