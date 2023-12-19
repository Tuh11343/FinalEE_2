<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8"/>
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0 , maximum-scale=1.0, user-scalable=0"/>
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

    <link rel="stylesheet"
          href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css"/>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/Views/User/dest/style.min.css/"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/Views/User/dest/fonts.css"/>
</head>
<style>
    .loader-container {
        display: flex;
        align-items: center;
        justify-content: center;
        height: 100vh;
        background-color: #f1f1f1;
        position: fixed;
        top: 0;
        left: 0;
        width: 100%;
        z-index: 9999;
    }

    .loader {
        border: 16px solid var(--main-cl);
        border-top: 16px solid #ffffff;
        border-radius: 50%;
        width: 80px;
        height: 80px;
        animation: spin 1s linear infinite;
    }

    @keyframes spin {
        0% {
            transform: rotate(0deg);
        }
        100% {
            transform: rotate(360deg);
        }
    }

    /* Hide loader when content is loaded */
    .loader-container.loaded {
        display: none;
    }
</style>
<body class="profilepage">
<div class="loader-container">
    <div class="loader"></div>
</div>
<jsp:include page="component/Header.jsp"/>
<jsp:include page="component/ModalLogin.jsp"/>

<main class="main">
    <section class="warp">
        <div class="container">
            <div class="warp__order">
                <h3 class="title --h4">Đơn hàng đã mua</h3>

                <div class="warp__order--list">

                    <c:forEach var="order" items="${requestScope.customerOrderList}" varStatus="status">

                        <div class="order--item">
                            <div class="btns ">

                                <div class="order__info">
                                    <span class="order__id">1</span>
                                    <p class="order__number">Tổng số sản phẩm:
                                        <span>${order.totalItemAmount()}</span>
                                    </p>
                                    <p class="order__date">Ngày đặt hàng:

                                        <span><fmt:formatDate value="${order.date_purchase}"
                                                              pattern="yyyy-MM-dd"/></span>

                                            <fmt:formatNumber value="${order.total}" pattern="#,###"
                                                              var="formattedTotal"/>
                                    <p class="order__totalprice">Tổng tiền: <span>${formattedTotal}</span></p>
                                    <p>Trạng thái:<span>${order.order_status.name}</span></p>

                                </div>

                                <div class="iconarrow">
                                    <svg xmlns="http://www.w3.org/2000/svg" height="1em"
                                         viewBox="0 0 512 512">
                                        <path
                                                d="M233.4 406.6c12.5 12.5 32.8 12.5 45.3 0l192-192c12.5-12.5 12.5-32.8 0-45.3s-32.8-12.5-45.3 0L256 338.7 86.6 169.4c-12.5-12.5-32.8-12.5-45.3 0s-12.5 32.8 0 45.3l192 192z"/>
                                    </svg>
                                </div>
                            </div>
                            <div class="order--products">
                                <c:forEach items="${order.orderDetailList}" var="orderDetail"
                                           varStatus="status">

                                    <c:set var="isSale" value="false"/>
                                    <c:if test="${orderDetail.stockItem.item.sale.on_sale eq 1}">
                                        <c:set var="isSale" value="true"/>
                                    </c:if>


                                    <div class="products--item">
                                        <span class="number__product">${status.index+1}</span>
                                        <img src="${orderDetail.stockItem.item.imageList.get(5).image_url}"
                                             alt="ao-yame">
                                        <p class="name__product">Tên:
                                            <span>${orderDetail.stockItem.item.name}</span></p>
                                        <p class="color__product">Màu:
                                            <span>${orderDetail.stockItem.color}</span></p>

                                        <c:if test="${isSale eq true}">
                                            <c:set var="salePrice"
                                                   value="${orderDetail.stockItem.item.price * (1-orderDetail.stockItem.item.sale.sale_percentage/100)}"/>
                                            <fmt:formatNumber value="${salePrice}" pattern="#,###" var="formattedSalePrice"/>
                                            <p class="price__product">Giá: <span>${formattedSalePrice}</span></p>
                                        </c:if>
                                        <c:if test="${isSale eq false}">
                                            <fmt:formatNumber value="${orderDetail.stockItem.item.price}"
                                                              pattern="#,###" var="formattedNumber"/>
                                            <p class="price__product">Giá:
                                                <span>${formattedNumber}</span></p>
                                        </c:if>

                                        <p>Số lượng: <span>${orderDetail.amount}</span></p>
                                    </div>


                                </c:forEach>
                            </div>

                        </div>

                    </c:forEach>

                </div>
            </div>
            <div class="warp__profile">
                <h3 class="title --h4">Thông tin tài khoản</h3>
                <form class="warp__profile--form" action="${pageContext.request.contextPath}/ProfileUserServlet"
                      method="post">
                    <div class="box-form">
                        <div class="form-gr">
                            <span class="label" for="name-regis">Họ và tên</span>
                            <input class="input-form" type="text" name="name" id="name-regis"
                                   placeholder="Nhập Họ và tên" value="${requestScope.signInCustomer.name}">
                            <p class="message-error error-name"></p>
                        </div>
                        <div class="form-gr">
                            <span class="label" for="phone-regis">Số điện thoại</span>
                            <input class="input-form" type="text" name="phoneNumber" id="phone-regis"
                                   placeholder="Nhập số điện thoại"
                                   value="${requestScope.signInCustomer.phone_number}">
                            <p class="message-error error-name"></p>
                        </div>
                    </div>
                    <div class="form-gr">
                        <span class="label" for="email-regis">Email</span>
                        <input class="input-form" type="email" name="email" id="email-regis"
                               placeholder="Nhập Email" value="${requestScope.signInCustomer.email}">
                        <p class="message-error error-name"></p>
                    </div>
                    <div class="form-gr">
                        <span class="label" for="address-regis">Địa chỉ</span>
                        <input class="input-form" type="text" name="address" id="address-regis"
                               placeholder="Nhập địa chỉ" value="${requestScope.signInCustomer.address}">
                        <p class="message-error error-name"></p>
                    </div>
                    <div class="form-gr">
                        <span class="label" for="password-regis">Mật khẩu</span>
                        <input class="input-form" type="password" name="password" id="password-regis"
                               placeholder="Nhập mật khẩu" value="${requestScope.signInAccount.password}">
                        <p class="message-error error-name"></p>
                    </div>
                    <button type="submit" class="btn btn-yellow">Cập Nhật</button>
                    <input type="hidden" value="updateCustomerAndAccount" name="action">

                    <input type="hidden" name="accountID" value="${requestScope.signInAccount.id}">
                    <input type="hidden" name="customerID" value="${requestScope.signInCustomer.id}">


                </form>
            </div>
        </div>
    </section>
</main>
<jsp:include page="component/Footer.jsp"/>
<script>

    document.addEventListener("DOMContentLoaded", function () {
        // Simulate loading delay (you can remove this in production)
        setTimeout(function () {
            // Add loaded class to hide the loader
            document.querySelector('.loader-container').classList.add('loaded');
        }, 2000); // Adjust the timeout based on your loading time
    });


</script>
<!-- Javascript -->
<script src="${pageContext.request.contextPath}/Views/User/dest/jsmain.min.js"></script>
<script src="${pageContext.request.contextPath}/Views/User/dest/main.js"></script>
</body>

</html>