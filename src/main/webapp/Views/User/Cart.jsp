<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0 , maximum-scale=1.0, user-scalable=0" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
    <title>Bán Giày</title>

    <meta name="title" content="Kiet Rider" />
    <meta name="description" content="" />
    <meta name="keyword" content="" />

    <link rel="canonical" href="https://cfdcircle.vn" />
    <link rel="icon" href="favicon.ico" />

    <!-- Meta Tag Facebook -->
    <meta property="og:locale" content="en" />
    <meta property="og:type" content="article" />
    <meta property="og:title" content="Kiet Rider" />
    <meta property="og:description" content="" />
    <meta property="og:url" content="" />
    <meta property="og:site_name" content="Kiet Rider" />
    <meta property="og:image" content="" />

    <!-- Meta Tag Twitter -->
    <meta name="twitter:card" content="" />
    <meta name="twitter:site" content="" />
    <meta name="twitter:title" content="" />
    <meta name="twitter:description" content="" />
    <meta name="twitter:image" content="" />

    <style>
        /* CSS Loading Here */
    </style>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css" />

    <link rel="stylesheet" href="${pageContext.request.contextPath}/Views/User/dest/style.min.css/"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/Views/User/dest/fonts.css" />
</head>

<body class="cartpage">
<jsp:include page="component/Header.jsp"/>
<main class="main">
    <section class="sclocation">
        <div class="container">
            <div class="sclocation__top location-path">
                <a href=""><svg xmlns="http://www.w3.org/2000/svg" height="1em" viewBox="0 0 576 512">
                    <style>
                        svg {
                            fill: #000000
                        }
                    </style>
                    <path
                            d="M575.8 255.5c0 18-15 32.1-32 32.1h-32l.7 160.2c0 2.7-.2 5.4-.5 8.1V472c0 22.1-17.9 40-40 40H456c-1.1 0-2.2 0-3.3-.1c-1.4 .1-2.8 .1-4.2 .1H416 392c-22.1 0-40-17.9-40-40V448 384c0-17.7-14.3-32-32-32H256c-17.7 0-32 14.3-32 32v64 24c0 22.1-17.9 40-40 40H160 128.1c-1.5 0-3-.1-4.5-.2c-1.2 .1-2.4 .2-3.6 .2H104c-22.1 0-40-17.9-40-40V360c0-.9 0-1.9 .1-2.8V287.6H32c-18 0-32-14-32-32.1c0-9 3-17 10-24L266.4 8c7-7 15-8 22-8s15 2 21 7L564.8 231.5c8 7 12 15 11 24z" />
                </svg></a>
                <span> / Thông tin giỏ hàng cảu bạn</span>
            </div>
        </div>
    </section>

    <section class="cart">
        <div class="container">
            <div class="cart__detail">
                <div class="cart__detail--top">
                    <h2 class="title --h4">chi tiết đơn hàng</h2>
                    <span class="total">Tổng tiền: <strong>150.000 VNĐ</strong></span>
                </div>
                <div class="cart__detail--list">
                    <div class="cart__item">
                        <div class="gr--img">
                            <div class="img">
                                <img src="./img/ao/ao-1-a.jpg" alt="">
                            </div>
                            <button class="btn btn-del">Xóa</button>
                        </div>
                        <div class="gr--info">
                            <div class="info-top">
                                <h4 class="name">ao thun</h4>
                                <p class="qty">
                                    <span>Số lượng <strong> 1 </strong> * 310,650 VNĐ</span>
                                    <span class="discount-price">(Tiếm kiệm: 16,350)</span>
                                </p>
                                <span class="discount-info">Giảm thẻ VIP: 5%</span>
                            </div>
                            <div class="total-item">
                                <span>= 272,650 VNĐ</span>
                            </div>
                        </div>
                    </div>
                    <div class="cart__item">
                        <div class="gr--img">
                            <div class="img">
                                <img src="./img/ao/ao-1-a.jpg" alt="">
                            </div>
                            <button class="btn btn-del">Xóa</button>
                        </div>
                        <div class="gr--info">
                            <div class="info-top">
                                <h4 class="name">ao thun</h4>
                                <p class="qty">
                                    <span>Số lượng <strong> 1 </strong> * 310,650 VNĐ</span>
                                    <span class="discount-price">(Tiếm kiệm: 16,350)</span>
                                </p>
                                <span class="discount-info">Giảm thẻ VIP: 5%</span>
                            </div>
                            <div class="total-item">
                                <span>= 272,650 VNĐ</span>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="cart__detail--shipping">
                    <p class="text">Giao hàng: <strong> Miễn phí (-19,000) </strong></p>
                </div>

            </div>
            <div class="cart__formgr">
                <h2 class="title --h5">Người nhân / Mua hàng</h2>
                <form class="cart__formgr--form">
                    <div class="form-gr">
                        <label for="fullname">Họ và Tên</label>
                        <input type="text" name="fullname" id="fullname" placeholder="Họ Tên">
                    </div>
                    <div class="form-gr">
                        <label for="phone">Điện thoại liên lạc</label>
                        <input type="text" name="phone" id="phone" placeholder="Số điện thoại">
                    </div>
                    <div class="form-gr">
                        <label for="address">Nhân hàng tại nhà/ công ty/ bưu điện</label>
                        <input type="text" name="address" id="address" placeholder="Điạ chỉ nhận hàng">
                    </div>
                    <div class="form-gr">
                        <label for="note">Ghi chú</label>
                        <textarea type="text" name="note" id="note" placeholder="Ghi chú"></textarea>
                    </div>
                    <div class="form-gr">
                        <label for="note">Giảm giá </label>
                        <select name="discount-code" id="discount-code">
                            <option value="10%">Giảm giá 10%</option>
                            <option value="20%">Giảm giá 20%</option>
                            <option value="30%">Giảm giá 30%</option>
                        </select>
                    </div>
                    <button class="btn ">Đặt hàng</button>
                </form>
                <button class="btn btn-gray cart__formgr--btnadd">Thêm sản phẩm khác</button>

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

<jsp:include page="component/ModalLogin.jsp"/>

<!-- Javascript -->
<script src="${pageContext.request.contextPath}/Views/User/dest/jsmain.min.js"></script>
<script src="${pageContext.request.contextPath}/Views/User/dest/main.js" ></script>
</body>

</html>