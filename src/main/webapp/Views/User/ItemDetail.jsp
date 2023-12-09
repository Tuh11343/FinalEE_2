<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib
        uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt"
           uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8"/>
    <meta
            name="viewport"
            content="width=device-width, initial-scale=1.0 , maximum-scale=1.0, user-scalable=0"
    />
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

    <title>Document</title>
    <link
            rel="stylesheet"
            href="${pageContext.request.contextPath}/Views/User/dest/style.min.css"
    />
    <link
            rel="stylesheet"
            href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css"
    />
    <link
            rel="stylesheet"
            href="${pageContext.request.contextPath}/Views/User/dest/fonts.css"
    />
</head>
<jsp:include page="component/Header.jsp"/>
<jsp:include page="component/ModalLogin.jsp"/>

<body>
<!-- ------Container------ -->
<main>
    <section class="productdetail">
        <div class="container">
            <div class="productItem">
                <div class="productImage">
                    <c:set var="itemImageCount" value="0"/>
                    <c:forEach items="${requestScope.itemClick.imageList}" var="image">
                        <c:if test="${itemImageCount < 1}">
                            <a href="#" class="img"
                            ><img src="${image.image_url}" alt="ao-thun"
                            /></a>
                            <c:set var="itemImageCount" value="${itemImageCount+1}"/>
                        </c:if>
                    </c:forEach>
                </div>
                <div class="productInfo">
                    <h3 class="productName --h4">${requestScope.itemClick.name}</h3>
                    <p class="productID">Mã số: ${requestScope.itemClick.id}</p>
                    <c:set var="isSale" value="false"/>
                    <c:if test="${requestScope.itemClick.sale.on_sale eq 1}">
                        <h5 class="sale">
                            <img
                                    src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAACAAAAAgCAYAAABzenr0AAAACXBIWXMAAADsAAAA7AF5KHG9AAAAGXRFWHRTb2Z0d2FyZQB3d3cuaW5rc2NhcGUub3Jnm+48GgAAA8xJREFUWIXFl11oHGUUhp8zM7sbo2kTijbBakqMRYlooYYEBUWKZhPTdLO4UEVbEBWpBQsFIVrtNjZgrJbSitSfChWpaKm5iEEbL2wF0SsllILoTSuktqakTUzs7szsHC82aSbZnexuUuK5+n7O977PzJxvvhlYZDht8Ycm2hLVC11vLMbcjsYbPU8HI55b/78AINoDRByRs0sOoImEqcojAOJx55IDMDZWBlgAIrpLk8kFaS0YQAYHJ4FzAAIPp38e+kBBlgwAQISea214zmmNHygVYlEAoab7DgucnO6r6jY7Gu+ZZ8niAOzHYuvS0c5vpvuSTHoZdM/sLO2yW+LPXHcATSRuUEO+QFjlHy9LXTmVkyv6nrYmbr6uAM545gngDpQ6v7hTvnxdnvRlNu4LxehaxQKoaPtUs9z23MF0NPa2CJXqyet585VHgYL1UDQAKrWIZtvCWpCjqsHpAnXFyJZQhLqs+FwAMtONVGusNdXWuaYkAE0kTH9fhIslAozMiMlrkpHeogHS0fib6fHMwVlAMFySvRJSkIn1HSsFmhBtn2yJ1xQEyJ7t+oqgT+r6zhUzF8E/JQEIa+2Wzl9CIesnsrVmWWQPL3/kFKGlmU1AGCFsW/KxdnRstlNmLUqsJIApCJipVBG9HzjqT8m5A6La5OvEbNscx+A0UA0gt9ZgNDdCeXmuYTiMrK4N5FHRlXPH8tSAVgUJGA80ETr4DubTmwgd2o9ULp+ZjESwundibnw8EABPcrTzAMhk0Hoz1o576BOcbTvQs38iDzbPmO9+FRwb9/2PggGEscIAyh9B6/XSKMa99yB3rUFuXwUXR3zmDm53LzhOsL9ozk7KBTDk1yCBzOEjUFWJ1bUD7/sf8E6fKdocAM8YyoGaO6AbNpTbjnUBqJhXrIQrnwo3bNq3yMDAZf9g7i7o7/9XVT4r2nzPXqytzxPu/5LQ/l6kOqfQs6Hy9VzzvAAAjmoS5UpB8+5ejOZGpOFunBdfxvvtd8xn836LZEB25pvIC1Ax2Pe3CNsLmeM4cNON6Ohl9PwFGD6f//0A+yInjp8pGgAg/G3fEWCvf8zavhWupnB3v3XtmXunfkRWVBH69EPMLU/hHeubKzUQrrC6gnzm/YJVEDsa3wX6BiBSU42OjICbmZ0YCiH1dejwXzA+7p/pC6eszXLy2MSCAKYj3RqLoXIAuK2YfGBCVZKRE1/tE/9hsFAAyG7PtGO9JLAFaAhIOwd8Hg6570p//6VidEv+kwFItXTUC2aDoqsNg6uKMaquDJV9dzzwLRoU/wGxqGF2wiMubwAAAABJRU5ErkJggg=="
                                    alt=""
                            />
                            SALE DỌN KHO LÊN HÀNG MỚI
                        </h5>
                        <c:set var="isSale" value="true"/>
                    </c:if>

                    <c:if test="${isSale eq true}">
                        <c:set
                                var="salePercentage"
                                value="${requestScope.itemClick.sale.sale_percentage}"
                        />
                        <fmt:formatNumber
                                value="${requestScope.itemClick.price}"
                                pattern="#,###"
                                var="formattedNumber"
                        />
                        <fmt:formatNumber
                                value="${requestScope.itemClick.price * (1 - (salePercentage/100))}"
                                pattern="#,###"
                                var="salePrice"
                        />
                        <fmt:formatNumber
                                value="${requestScope.itemClick.price * (salePercentage/100)}"
                                pattern="#,###"
                                var="savedAmount"
                        />
                        <h6 class="price">Giá gốc: <span>${formattedNumber}</span> đ</h6>
                        <h5 class="salePrice">
                            Giá Sale:
                            <span>${salePrice}</span>
                            đ (Tiết kiệm ${savedAmount})
                        </h5>
                    </c:if>

                    <c:if test="${isSale eq false}">
                        <fmt:formatNumber
                                value="${requestScope.itemClick.price}"
                                pattern="#,###"
                                var="formattedNumber"
                        />
                        <h6 class="salePrice">Giá: <span>${formattedNumber}</span> đ</h6>
                    </c:if>

                    <p class="textAmount">Số lượng có hạn</p>

                    <div class="amount">
                        <div class="remainAmount">60%</div>
                    </div>
                    <table class="productVariants">
                        <button onclick="initData()">Click me</button>
                        <tbody>
                        <c:forEach
                                items="${requestScope.itemClick.stockItemList}"
                                var="stockItem"
                        >
                            <tr>
                                <script>
                                    // Lấy dữ liệu từ LocalStorage và chuyển đổi thành đối tượng JavaScript
                                    var cartData = JSON.parse(localStorage.getItem('cart'));
                                </script>

                                <td>${stockItem.color}, ${stockItem.size}</td>
                                <td class="needHover" style="text-align: right">
                                    <b>${stockItem.amount}</b> CH còn ${cart}
                                </td>


                                <td style="text-align: right">
                                    <c:if test="${stockItem.amount > 0}">

                                        <a class="select-buy" id="select-buy-${stockItem.id}"
                                           onclick="sendAjaxRequest('${stockItem.id}')">Chọn mua
                                            <i class="fa fa-plus-circle"></i>
                                        </a>

                                    </c:if>

                                    <c:if test="${stockItem.amount <=0}">

                                        <a class="select-buy">Hết hàng
                                            <i class="fa fa-plus-circle" style="visibility: hidden"></i>
                                        </a>

                                    </c:if>

                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                        <script>
                            function sendAjaxRequest(stockItemID) {
                                $.ajax({
                                    type: "POST",
                                    url: "${pageContext.request.contextPath}/ItemDetailServlet",
                                    data: {
                                        stockItemID: stockItemID,
                                    },
                                    headers: {
                                        "X-Requested-With": "XMLHttpRequest",
                                    },
                                    success: function (data) {
                                        // Check for the boolean property
                                        console.log(data);
                                        if (data.logIn === false) {

                                            let cart = data.cart;
                                            console.log("cart" + JSON.parse(cart));
                                            let cartList = JSON.parse(localStorage.getItem("YOUR_CART"));
                                            if (cartList != null) {
                                                console.log("Ok Not Null");
                                                let index = cartList.findIndex(item => {
                                                    console.log(item.stockItem.id === cart.stockItem.id);
                                                    return item.stockItem.id === cart.stockItem.id;
                                                });
                                                if (index !== -1) {
                                                    console.log("Tang so luong");
                                                    cartList[index].amount += 1;
                                                } else {

                                                    cartList.push(cart);
                                                }
                                                localStorage.setItem("YOUR_CART", JSON.stringify(cartList));
                                            } else {
                                                console.log("Create new CartList");
                                                cartList = [];
                                                cartList.push(cart);
                                                localStorage.setItem("YOUR_CART", JSON.stringify(cartList));
                                            }

                                        } else {
                                            // Handle the case where 'notLogIn' is false
                                            alert("Logged in");
                                        }
                                        alert("thêm thành công"); //bao them thanh cong
                                    },
                                    error: function (error) {
                                        console.log(error);
                                    }

                                });
                            }
                        </script>
                    </table>
                    <div class="tutorials">
                        <!-- <button onclick="HideShowSize()">Hướng dẫn chọn size</button> -->
                        <button onclick="HideShowDes()">Xem mô tả sản phẩm</button>
                    </div>
                    <div class="btnShow">
                        <div id="btnShowSize" class="Size" style="display: none">
                            <div class="weight--height">
                                <input
                                        class="weight"
                                        type="text"
                                        name="txtCanNang"
                                        placeholder="Cân nặng"
                                />
                                <div><span>Kg</span></div>
                                <input
                                        class="height"
                                        type="text"
                                        name="txtChieuCao"
                                        placeholder="Chiều cao"
                                />
                                <div><span>Cm</span></div>
                                <button>Tìm</button>
                            </div>
                        </div>

                        <%--Item Description--%>
                        <div id="btnShowDes" class="Description" style="display: none">
                            <h6 style="font-weight: bold; margin-bottom: 10px">Mô tả sản phẩm</h6>
                            ${requestScope.itemClick.description}
                        </div>
                    </div>
                </div>
            </div>

            <div class="productInfoImage">
                <c:forEach
                        items="${requestScope.itemClick.imageList}"
                        var="image"
                        varStatus="status"
                >
                    <c:if test="${status.index ne 0}">
                        <div class="itemImage">
                            <img src="${image.image_url}" alt=""/>
                        </div>
                    </c:if>
                </c:forEach>
            </div>
        </div>
    </section>
</main>

<!-- Footer -->
<jsp:include page="component/Footer.jsp"/>

<script src="${pageContext.request.contextPath}/Views/User/dest/jsmain.min.js"></script>
<script src="${pageContext.request.contextPath}/Views/User/dest/main.js"></script>
<script src="${pageContext.request.contextPath}/Views/User/dest/tuh.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</body>
</html>