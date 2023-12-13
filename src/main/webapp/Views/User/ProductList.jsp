<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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

    <style>
        /* CSS Loading Here */
    </style>
    <link rel="stylesheet"
          href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css"/>

    <!--        <link rel="stylesheet" href="dest/style.min.css" />
<link rel="stylesheet" href="dest/fonts.css" />-->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/Views/User/dest/style.min.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/Views/User/dest/fonts.css"/>

</head>

<jsp:include page="component/Header.jsp"/>
<jsp:include page="component/ModalLogin.jsp"/>

<body class="productpage">
<main class="main">

    <section class="productsearch">
        <div class="container">
            <div class="productsearch__top">
                <svg xmlns="http://www.w3.org/2000/svg" height="1em" viewBox="0 0 576 512">
                    <style>
                        svg {
                            fill: #000000
                        }
                    </style>
                    <path
                            d="M575.8 255.5c0 18-15 32.1-32 32.1h-32l.7 160.2c0 2.7-.2 5.4-.5 8.1V472c0 22.1-17.9 40-40 40H456c-1.1 0-2.2 0-3.3-.1c-1.4 .1-2.8 .1-4.2 .1H416 392c-22.1 0-40-17.9-40-40V448 384c0-17.7-14.3-32-32-32H256c-17.7 0-32 14.3-32 32v64 24c0 22.1-17.9 40-40 40H160 128.1c-1.5 0-3-.1-4.5-.2c-1.2 .1-2.4 .2-3.6 .2H104c-22.1 0-40-17.9-40-40V360c0-.9 0-1.9 .1-2.8V287.6H32c-18 0-32-14-32-32.1c0-9 3-17 10-24L266.4 8c7-7 15-8 22-8s15 2 21 7L564.8 231.5c8 7 12 15 11 24z"/>
                </svg>
                <span> / Tìm Kiếm</span>
            </div>
            <form class="productsearch__form"
                  action="${pageContext.request.contextPath}/HeaderServlet" method="post">
                <input type="text" placeholder="Nhập sản phẩm bạn muốn tìm kiếm!!"
                       class="productsearch__form--iptnamep" name="searchInput">
                <button type="submit" class="btn productsearch__form--btn">Tìm kiếm</button>
                <input type="hidden" name="action" value="btnSearchClick">
            </form>
        </div>
    </section>
    <section class="scproduct pb">
        <div class="container">
            <div class="scproduct__top ">
                <h4 class="heading --h4">Sản phẩm các bạn tìm</h4>
                <div class="scproduct__top--sort">

                    <form action="${pageContext.request.contextPath}/ItemSearchServlet" method="get"
                          class="formSort">
                        <div class="sort-item rangeprice">
                            <div class="rangeprice-title">
                                <span>Giá</span>
                                <input type="hidden" id="rangeMin" name="rangeMin" value="">
                                <input type="hidden" id="rangeMax" name="rangeMax" value="">
                            </div>
                            <div class="rangeprice-slider">
                                <div id="price-slider"></div>
                            </div>
                        </div>

                        <div class="sort-item">
                            <input type="hidden" value="${requestScope.currentPage}" name="currentPage">
                            <label for="sort">Sắp xếp:</label>
                            <select id="sort" name="sort" class="sortinp">
                                <option value="az">Chữ cái A-Z</option>
                                <option value="za">Chữ cái Z-A</option>
                                <option value="priceAsc">Theo giá tăng</option>
                                <option value="priceDes">Theo giá giảm</option>
                            </select>
                            <input type="hidden" name="sortChange" value="action">
                        </div>
                        <button class="btn btn-main">Lọc</button>
                    </form>
                </div>
            </div>
            ${requestScope.searchInput}
            <div class="scproduct__list listproduct">
                <!-- 1 san pham -->

                <c:set var="itemCount" value="0"/>
                <c:forEach items="${requestScope.itemSearchList}" var="item" varStatus="index">


                    <div class="sale__product product">
                        <div class="carousel-img" data-type="account">

                            <c:set var="counter" value="0"/>
                            <c:forEach
                                    items="${requestScope.itemSearchList.get(itemCount).getImageList()}"
                                    var="image">
                                <c:if test="${counter < 2}">
                                    <a href="#" class="img"><img src="${image.image_url}"
                                                                 alt="ao-thun"/></a>
                                    <c:set var="counter" value="${counter+1}"/>
                                </c:if>
                            </c:forEach>

                        </div>
                        <div class="productInfo">
                            <h3 class="name"><a
                                    href="#">${requestScope.itemSearchList[itemCount].name}</a></h3>

                            <span class="price">
                                <c:choose>
                                    <c:when test="${item.price % 1 == 0}">
                                        <fmt:formatNumber type="number" value="${item.price}"
                                                          pattern="#,###"/> VNĐ
                                    </c:when>
                                    <c:otherwise>
                                        ${item.price} VNĐ
                                    </c:otherwise>
                                </c:choose>
                            </span>

                            <p class="desc">${item.description}</p>
                            <form action="${pageContext.request.contextPath}/ItemServlet" method="post">
                                <div class="btnactiongr">
                                    <button class="btn addcart">Add Cart
                                        <svg xmlns="http://www.w3.org/2000/svg" height="1em"
                                             viewBox="0 0 576 512">
                                            <path
                                                    d="M0 24C0 10.7 10.7 0 24 0H69.5c22 0 41.5 12.8 50.6 32h411c26.3 0 45.5 25 38.6 50.4l-41 152.3c-8.5 31.4-37 53.3-69.5 53.3H170.7l5.4 28.5c2.2 11.3 12.1 19.5 23.6 19.5H488c13.3 0 24 10.7 24 24s-10.7 24-24 24H199.7c-34.6 0-64.3-24.6-70.7-58.5L77.4 54.5c-.7-3.8-4-6.5-7.9-6.5H24C10.7 48 0 37.3 0 24zM128 464a48 48 0 1 1 96 0 48 48 0 1 1 -96 0zm336-48a48 48 0 1 1 0 96 48 48 0 1 1 0-96zM252 160c0 11 9 20 20 20h44v44c0 11 9 20 20 20s20-9 20-20V180h44c11 0 20-9 20-20s-9-20-20-20H356V96c0-11-9-20-20-20s-20 9-20 20v44H272c-11 0-20 9-20 20z"/>
                                        </svg>
                                        <input type="hidden" name="action" value="itemClick">
                                        <input type="hidden" name="itemClickID" value="${item.id}">

                                    </button>

                                    <button class="btn buy">
                                        Buy
                                        <svg xmlns="http://www.w3.org/2000/svg" height="1em"
                                             viewBox="0 0 576 512">
                                            <path
                                                    d="M0 112.5V422.3c0 18 10.1 35 27 41.3c87 32.5 174 10.3 261-11.9c79.8-20.3 159.6-40.7 239.3-18.9c23 6.3 48.7-9.5 48.7-33.4V89.7c0-18-10.1-35-27-41.3C462 15.9 375 38.1 288 60.3C208.2 80.6 128.4 100.9 48.7 79.1C25.6 72.8 0 88.6 0 112.5zM288 352c-44.2 0-80-43-80-96s35.8-96 80-96s80 43 80 96s-35.8 96-80 96zM64 352c35.3 0 64 28.7 64 64H64V352zm64-208c0 35.3-28.7 64-64 64V144h64zM512 304v64H448c0-35.3 28.7-64 64-64zM448 96h64v64c-35.3 0-64-28.7-64-64z"/>
                                        </svg>
                                    </button>
                                </div>
                            </form>

                        </div>
                    </div>
                    <c:set var="itemCount" value="${itemCount+1}"/>


                </c:forEach>

                <!-- end product -->
            </div>
            <div class="scproduct__panigation">
                <c:forEach items="${requestScope.pageList}" var="page" varStatus="status">
                    <a class="scproduct__panigation--item"
                       onclick="submitForm('form_pageClick${page}')">${page}</a>

                    <form id="form_pageClick${page}"
                          action="${pageContext.request.contextPath}/ItemSearchServlet" method="get">
                        <input type="hidden" name="sort" value="${requestScope.sort}">
                        <input type="hidden" name="currentPage" value="${page}">
                        <input type="hidden" name="priceMin" value="">
                        <input type="hidden" name="priceMax" value="">

                        <c:if test="${not empty requestScope.itemCollectionID}">
                            <input type="hidden" name="itemCollectionID"
                                   value="${requestScope.itemCollectionID}"/>
                        </c:if>
                        <c:if test="${not empty requestScope.itemTypeID}">
                            <input type="hidden" name="itemTypeID" value="${requestScope.itemTypeID}"/>
                        </c:if>
                        <c:if test="${not empty requestScope.itemMaterialID}">
                            <input type="hidden" name="itemMaterialID"
                                   value="${requestScope.itemMaterialID}"/>
                        </c:if>
                        <c:if test="${not empty requestScope.itemName}">
                            <input type="hidden" name="itemName" value="${requestScope.itemName}"/>
                        </c:if>
                    </form>

                </c:forEach>
            </div>
        </div>
    </section>

</main>

<jsp:include page="component/Footer.jsp"/>

<script>

    var priceSlider = document.getElementById('price-slider');
    console.log();
    // Check if #price-slider elem is exists if not return
    // to prevent error logs
    // if (priceSlider == null) return;

    noUiSlider.create(priceSlider, {
        start: [0, 750],
        connect: true,
        step: 10000,
        margin: 20000,
        range: {
            'min': ${requestScope.minPrice},
            'max': ${requestScope.maxPrice}
        },
        tooltips: true,
    });

    // Update Price Range
    priceSlider.noUiSlider.on('update', function (values, handle) {
        console.log('value :>> ', values[0]);
        //lay gai tri tien o day

        document.getElementById("rangeMin").value = values[0];
        document.getElementById("rangeMax").value = values[1];


    });

    // Update Price Range
    // priceSlider.noUiSlider.on('update', function (values, handle) {
    //     $('#filter-price-range').text(values.join(' - '));
    // });
</script>

<!--                <script type="text/javascript" src="dest/jsmain.min.js"></script>-->
<!--                <script type="text/javascript" src="./dest/main.js"></script>-->
<script src="${pageContext.request.contextPath}/Views/User/dest/jsmain.min.js"></script>
<script src="${pageContext.request.contextPath}/Views/User/dest/wNumb.js"></script>
<script src="${pageContext.request.contextPath}/Views/User/dest/nouislider.min.js"></script>

<script src="${pageContext.request.contextPath}/Views/User/dest/main.js"></script>

</body>

</html>