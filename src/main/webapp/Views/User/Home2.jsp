<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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

        <!--        <link rel="stylesheet" href="dest/style.min.css" />
                <link rel="stylesheet" href="dest/fonts.css" />-->
        <link rel="stylesheet" href="${pageContext.request.contextPath}/Views/dest/style.min.css" />
        <link rel="stylesheet" href="${pageContext.request.contextPath}/Views/dest/fonts.css" />

    </head>
    <body class="homepage">
        <header class="header">
            <div class="container">
                <div class="header__left">
                    <div class="header__logo">
                        <a href="#"><img class="img" src="../img/logo.png" alt="img-logo" /></a>
                    </div>
                    <nav class="hedaer__navbar">
                        <ul class="header__navbar-sitemenu">
                            <li class="item">
                                <a href="#" class="nameitem">Trang chủ</a>
                            </li>
                            <li class="item">
                                <a href="#" class="nameitem">Bộ Sưu Tập

                                    <svg class="arrow"  xmlns="http://www.w3.org/2000/svg" height="1em" viewBox="0 0 512 512">
                                    <path
                                        d="M233.4 406.6c12.5 12.5 32.8 12.5 45.3 0l192-192c12.5-12.5 12.5-32.8 0-45.3s-32.8-12.5-45.3 0L256 338.7 86.6 169.4c-12.5-12.5-32.8-12.5-45.3 0s-12.5 32.8 0 45.3l192 192z" />
                                    </svg>
                                </a>
                                <ul class="dropmenu">
                                    <c:forEach items="${itemCollectionList}" var="itemCollection">
                                        <li>
                                            <a onclick="itemCollectionClick(`form_itemCollection`)" >${itemCollection.name}</a>
                                            <form id="form_itemCollection" action="/ItemServlet" method="post">
                                                <input type="hidden" value="${itemCollection.name}"  name="itemType"/>
                                                <input type="hidden" value="itemCollectionClick" name="action"/>
                                            </form>
                                        </li>
                                    </c:forEach>

                                </ul>
                            </li>
                            <li class="item">
                                <a href="#" class="nameitem">Giới thiệu
                                </a>
                            </li>
                            <li class="item">
                                <a href="#" class="nameitem">Contact
                                </a>
                            </li>
                            <li class="item">
                                <a href="#" class="nameitem">
                                    sản phẩm
                                    <svg  class="arrow" xmlns="http://www.w3.org/2000/svg" height="1em" viewBox="0 0 512 512">
                                    <path
                                        d="M233.4 406.6c12.5 12.5 32.8 12.5 45.3 0l192-192c12.5-12.5 12.5-32.8 0-45.3s-32.8-12.5-45.3 0L256 338.7 86.6 169.4c-12.5-12.5-32.8-12.5-45.3 0s-12.5 32.8 0 45.3l192 192z" />
                                    </svg>
                                </a>
                                <ul class="dropmenu sortmenu">
                                    <li><a href="productlist.html">Giày dép</a></li>
                                    <li><a href="productlist.html">Quần Áo</a></li>
                                    <li><a href="productlist.html">Phụ Kiện</a></li>
                                    <li><a href="productlist.html">Balo</a></li>
                                    <li><a href="productlist.html">Mắt Kính</a></li>
                                    <li><a href="productlist.html">Balo</a></li>
                                    <li><a href="productlist.html">Mắt Kính</a></li>
                                    <li><a href="productlist.html">Phụ Kiện</a></li>
                                    <li><a href="productlist.html">Quần Áo</a></li>
                                    <li><a href="productlist.html">Giày dép</a></li>

                                </ul>
                            </li>
                        </ul>
                    </nav>
                </div>

                <div class="header__right">
                    <div class="header__right--search">
                        <form class="header__search--form search--product" action="/ItemServlet" method="post">
                            <div class="form_group">
                                <input type="text" placeholder="Nhập sản phẩm bạn cần tìm!!" class="inputfeild" name="btnSearch_data"/>
                            </div>
                            <button class="btn__search" value="btnSearchClick" name="action">
                                <svg class="icon icon_search" xmlns="http://www.w3.org/2000/svg" height="1em" viewBox="0 0 512 512">
                                <path
                                    d="M416 208c0 45.9-14.9 88.3-40 122.7L502.6 457.4c12.5 12.5 12.5 32.8 0 45.3s-32.8 12.5-45.3 0L330.7 376c-34.4 25.2-76.8 40-122.7 40C93.1 416 0 322.9 0 208S93.1 0 208 0S416 93.1 416 208zM208 352a144 144 0 1 0 0-288 144 144 0 1 0 0 288z" />
                                </svg>
                            </button>
                        </form>
                    </div>

                    <a href="#" class="header__right--icon btnshow_search">
                        <svg class="icon icon_search" xmlns="http://www.w3.org/2000/svg" height="1em" viewBox="0 0 512 512">
                        <path
                            d="M416 208c0 45.9-14.9 88.3-40 122.7L502.6 457.4c12.5 12.5 12.5 32.8 0 45.3s-32.8 12.5-45.3 0L330.7 376c-34.4 25.2-76.8 40-122.7 40C93.1 416 0 322.9 0 208S93.1 0 208 0S416 93.1 416 208zM208 352a144 144 0 1 0 0-288 144 144 0 1 0 0 288z" />
                        </svg>
                        <svg class="icon icon_close" xmlns="http://www.w3.org/2000/svg" height="1em" viewBox="0 0 384 512">
                        <path
                            d="M342.6 150.6c12.5-12.5 12.5-32.8 0-45.3s-32.8-12.5-45.3 0L192 210.7 86.6 105.4c-12.5-12.5-32.8-12.5-45.3 0s-12.5 32.8 0 45.3L146.7 256 41.4 361.4c-12.5 12.5-12.5 32.8 0 45.3s32.8 12.5 45.3 0L192 301.3 297.4 406.6c12.5 12.5 32.8 12.5 45.3 0s12.5-32.8 0-45.3L237.3 256 342.6 150.6z" />
                        </svg>
                    </a>
                    <a href="#" class="header__right--icon">
                        <svg xmlns="http://www.w3.org/2000/svg" height="1em" viewBox="0 0 512 512">
                        <path
                            d="M256 288A144 144 0 1 0 256 0a144 144 0 1 0 0 288zm-94.7 32C72.2 320 0 392.2 0 481.3c0 17 13.8 30.7 30.7 30.7H481.3c17 0 30.7-13.8 30.7-30.7C512 392.2 439.8 320 350.7 320H161.3z" />
                        </svg>
                    </a>
                    <a href="#" class="header__right--icon">
                        <svg xmlns="http://www.w3.org/2000/svg" height="1em" viewBox="0 0 448 512">
                        <path
                            d="M160 112c0-35.3 28.7-64 64-64s64 28.7 64 64v48H160V112zm-48 48H48c-26.5 0-48 21.5-48 48V416c0 53 43 96 96 96H352c53 0 96-43 96-96V208c0-26.5-21.5-48-48-48H336V112C336 50.1 285.9 0 224 0S112 50.1 112 112v48zm24 48a24 24 0 1 1 0 48 24 24 0 1 1 0-48zm152 24a24 24 0 1 1 48 0 24 24 0 1 1 -48 0z" />
                        </svg>
                        <span class="number">1</span>
                    </a>
                </div>
            </div>
        </header>

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
                            d="M575.8 255.5c0 18-15 32.1-32 32.1h-32l.7 160.2c0 2.7-.2 5.4-.5 8.1V472c0 22.1-17.9 40-40 40H456c-1.1 0-2.2 0-3.3-.1c-1.4 .1-2.8 .1-4.2 .1H416 392c-22.1 0-40-17.9-40-40V448 384c0-17.7-14.3-32-32-32H256c-17.7 0-32 14.3-32 32v64 24c0 22.1-17.9 40-40 40H160 128.1c-1.5 0-3-.1-4.5-.2c-1.2 .1-2.4 .2-3.6 .2H104c-22.1 0-40-17.9-40-40V360c0-.9 0-1.9 .1-2.8V287.6H32c-18 0-32-14-32-32.1c0-9 3-17 10-24L266.4 8c7-7 15-8 22-8s15 2 21 7L564.8 231.5c8 7 12 15 11 24z" />
                        </svg>
                        <span> / Tìm Kiếm</span>
                    </div>
                    <form class="productsearch__form">
                        <input type="text" placeholder="Nhập sản phẩm bạn muốn tìm kiếm!!" class="productsearch__form--iptnamep">
                        <button type="submit" class="btn productsearch__form--btn">Tìm kiếm</button>
                    </form>
                </div>
            </section>
            <section class="scproduct pb">
                <div class="container">
                    <div class="heading --h4">Các sản phẩm bạn tìm</div>
                    <div class="scproduct__list listproduct">
                        <!-- 1 san pham -->
                        <div class="scproduct__list--item product">
                            <div class="carousel-img" data-type="account">
                                <a href="#" class="img"><img src="../img/ao/ao-1-a.jpg" alt="ao-thun" /></a>
                                <a href="#" class="img"><img src="../img/ao/ao-1-b.jpg" alt="ao-thun" /></a>
                            </div>
                            <div class="productInfo">
                                <h3 class="name"><a href="#">Áo Thun</a></h3>
                                <span class="price">350000vnd</span>
                                <p class="desc">Vải cotton hay còn gọi là cotton 100%, được làm từ sợi bông thiên nhiên. Đây là chất liệu
                                    phổ biến nhất được chọn làm áo thun bởi vì áo thun</p>
                                <div class="btnactiongr">
                                    <button class="btn addcart">Add Cart <svg xmlns="http://www.w3.org/2000/svg" height="1em"
                                                                              viewBox="0 0 576 512">

                                        <path
                                            d="M0 24C0 10.7 10.7 0 24 0H69.5c22 0 41.5 12.8 50.6 32h411c26.3 0 45.5 25 38.6 50.4l-41 152.3c-8.5 31.4-37 53.3-69.5 53.3H170.7l5.4 28.5c2.2 11.3 12.1 19.5 23.6 19.5H488c13.3 0 24 10.7 24 24s-10.7 24-24 24H199.7c-34.6 0-64.3-24.6-70.7-58.5L77.4 54.5c-.7-3.8-4-6.5-7.9-6.5H24C10.7 48 0 37.3 0 24zM128 464a48 48 0 1 1 96 0 48 48 0 1 1 -96 0zm336-48a48 48 0 1 1 0 96 48 48 0 1 1 0-96zM252 160c0 11 9 20 20 20h44v44c0 11 9 20 20 20s20-9 20-20V180h44c11 0 20-9 20-20s-9-20-20-20H356V96c0-11-9-20-20-20s-20 9-20 20v44H272c-11 0-20 9-20 20z" />
                                        </svg></button>
                                    <button class="btn buy">
                                        Buy
                                        <svg xmlns="http://www.w3.org/2000/svg" height="1em" viewBox="0 0 576 512">

                                        <path
                                            d="M0 112.5V422.3c0 18 10.1 35 27 41.3c87 32.5 174 10.3 261-11.9c79.8-20.3 159.6-40.7 239.3-18.9c23 6.3 48.7-9.5 48.7-33.4V89.7c0-18-10.1-35-27-41.3C462 15.9 375 38.1 288 60.3C208.2 80.6 128.4 100.9 48.7 79.1C25.6 72.8 0 88.6 0 112.5zM288 352c-44.2 0-80-43-80-96s35.8-96 80-96s80 43 80 96s-35.8 96-80 96zM64 352c35.3 0 64 28.7 64 64H64V352zm64-208c0 35.3-28.7 64-64 64V144h64zM512 304v64H448c0-35.3 28.7-64 64-64zM448 96h64v64c-35.3 0-64-28.7-64-64z" />
                                        </svg>
                                    </button>
                                </div>
                            </div>
                        </div>
                        <!-- end product -->

                        <!-- 1 san pham -->
                        <div class="scproduct__list--item product">
                            <div class="carousel-img" data-type="account">
                                <a href="#" class="img"><img src="../img/ao/ao-1-a.jpg" alt="ao-thun" /></a>
                                <a href="#" class="img"><img src="../img/ao/ao-1-b.jpg" alt="ao-thun" /></a>
                            </div>
                            <div class="productInfo">
                                <h3 class="name"><a href="#">Áo Thun</a></h3>
                                <span class="price">350000vnd</span>
                                <p class="desc">Vải cotton hay còn gọi là cotton 100%, được làm từ sợi bông thiên nhiên. Đây là chất liệu
                                    phổ biến nhất được chọn làm áo thun bởi vì áo thun</p>
                                <div class="btnactiongr">
                                    <button class="btn addcart">Add Cart <svg xmlns="http://www.w3.org/2000/svg" height="1em"
                                                                              viewBox="0 0 576 512">

                                        <path
                                            d="M0 24C0 10.7 10.7 0 24 0H69.5c22 0 41.5 12.8 50.6 32h411c26.3 0 45.5 25 38.6 50.4l-41 152.3c-8.5 31.4-37 53.3-69.5 53.3H170.7l5.4 28.5c2.2 11.3 12.1 19.5 23.6 19.5H488c13.3 0 24 10.7 24 24s-10.7 24-24 24H199.7c-34.6 0-64.3-24.6-70.7-58.5L77.4 54.5c-.7-3.8-4-6.5-7.9-6.5H24C10.7 48 0 37.3 0 24zM128 464a48 48 0 1 1 96 0 48 48 0 1 1 -96 0zm336-48a48 48 0 1 1 0 96 48 48 0 1 1 0-96zM252 160c0 11 9 20 20 20h44v44c0 11 9 20 20 20s20-9 20-20V180h44c11 0 20-9 20-20s-9-20-20-20H356V96c0-11-9-20-20-20s-20 9-20 20v44H272c-11 0-20 9-20 20z" />
                                        </svg></button>
                                    <button class="btn buy">
                                        Buy
                                        <svg xmlns="http://www.w3.org/2000/svg" height="1em" viewBox="0 0 576 512">

                                        <path
                                            d="M0 112.5V422.3c0 18 10.1 35 27 41.3c87 32.5 174 10.3 261-11.9c79.8-20.3 159.6-40.7 239.3-18.9c23 6.3 48.7-9.5 48.7-33.4V89.7c0-18-10.1-35-27-41.3C462 15.9 375 38.1 288 60.3C208.2 80.6 128.4 100.9 48.7 79.1C25.6 72.8 0 88.6 0 112.5zM288 352c-44.2 0-80-43-80-96s35.8-96 80-96s80 43 80 96s-35.8 96-80 96zM64 352c35.3 0 64 28.7 64 64H64V352zm64-208c0 35.3-28.7 64-64 64V144h64zM512 304v64H448c0-35.3 28.7-64 64-64zM448 96h64v64c-35.3 0-64-28.7-64-64z" />
                                        </svg>
                                    </button>
                                </div>
                            </div>
                        </div>
                        <!-- end product -->

                        <!-- 1 san pham -->
                        <div class="scproduct__list--item product">
                            <div class="carousel-img" data-type="account">
                                <a href="#" class="img"><img src="../img/ao/ao-1-a.jpg" alt="ao-thun" /></a>
                                <a href="#" class="img"><img src="../img/ao/ao-1-b.jpg" alt="ao-thun" /></a>
                            </div>
                            <div class="productInfo">
                                <h3 class="name"><a href="#">Áo Thun</a></h3>
                                <span class="price">350000vnd</span>
                                <p class="desc">Vải cotton hay còn gọi là cotton 100%, được làm từ sợi bông thiên nhiên. Đây là chất liệu
                                    phổ biến nhất được chọn làm áo thun bởi vì áo thun</p>
                                <div class="btnactiongr">
                                    <button class="btn addcart">Add Cart <svg xmlns="http://www.w3.org/2000/svg" height="1em"
                                                                              viewBox="0 0 576 512">

                                        <path
                                            d="M0 24C0 10.7 10.7 0 24 0H69.5c22 0 41.5 12.8 50.6 32h411c26.3 0 45.5 25 38.6 50.4l-41 152.3c-8.5 31.4-37 53.3-69.5 53.3H170.7l5.4 28.5c2.2 11.3 12.1 19.5 23.6 19.5H488c13.3 0 24 10.7 24 24s-10.7 24-24 24H199.7c-34.6 0-64.3-24.6-70.7-58.5L77.4 54.5c-.7-3.8-4-6.5-7.9-6.5H24C10.7 48 0 37.3 0 24zM128 464a48 48 0 1 1 96 0 48 48 0 1 1 -96 0zm336-48a48 48 0 1 1 0 96 48 48 0 1 1 0-96zM252 160c0 11 9 20 20 20h44v44c0 11 9 20 20 20s20-9 20-20V180h44c11 0 20-9 20-20s-9-20-20-20H356V96c0-11-9-20-20-20s-20 9-20 20v44H272c-11 0-20 9-20 20z" />
                                        </svg></button>
                                    <button class="btn buy">
                                        Buy
                                        <svg xmlns="http://www.w3.org/2000/svg" height="1em" viewBox="0 0 576 512">

                                        <path
                                            d="M0 112.5V422.3c0 18 10.1 35 27 41.3c87 32.5 174 10.3 261-11.9c79.8-20.3 159.6-40.7 239.3-18.9c23 6.3 48.7-9.5 48.7-33.4V89.7c0-18-10.1-35-27-41.3C462 15.9 375 38.1 288 60.3C208.2 80.6 128.4 100.9 48.7 79.1C25.6 72.8 0 88.6 0 112.5zM288 352c-44.2 0-80-43-80-96s35.8-96 80-96s80 43 80 96s-35.8 96-80 96zM64 352c35.3 0 64 28.7 64 64H64V352zm64-208c0 35.3-28.7 64-64 64V144h64zM512 304v64H448c0-35.3 28.7-64 64-64zM448 96h64v64c-35.3 0-64-28.7-64-64z" />
                                        </svg>
                                    </button>
                                </div>
                            </div>
                        </div>
                        <!-- end product -->
                        <!-- 1 san pham -->
                        <div class="scproduct__list--item product">
                            <div class="carousel-img" data-type="account">
                                <a href="#" class="img"><img src="../img/ao/ao-1-a.jpg" alt="ao-thun" /></a>
                                <a href="#" class="img"><img src="../img/ao/ao-1-b.jpg" alt="ao-thun" /></a>
                            </div>
                            <div class="productInfo">
                                <h3 class="name"><a href="#">Áo Thun</a></h3>
                                <span class="price">350000vnd</span>
                                <p class="desc">Vải cotton hay còn gọi là cotton 100%, được làm từ sợi bông thiên nhiên. Đây là chất liệu
                                    phổ biến nhất được chọn làm áo thun bởi vì áo thun</p>
                                <div class="btnactiongr">
                                    <button class="btn addcart">Add Cart <svg xmlns="http://www.w3.org/2000/svg" height="1em"
                                                                              viewBox="0 0 576 512">

                                        <path
                                            d="M0 24C0 10.7 10.7 0 24 0H69.5c22 0 41.5 12.8 50.6 32h411c26.3 0 45.5 25 38.6 50.4l-41 152.3c-8.5 31.4-37 53.3-69.5 53.3H170.7l5.4 28.5c2.2 11.3 12.1 19.5 23.6 19.5H488c13.3 0 24 10.7 24 24s-10.7 24-24 24H199.7c-34.6 0-64.3-24.6-70.7-58.5L77.4 54.5c-.7-3.8-4-6.5-7.9-6.5H24C10.7 48 0 37.3 0 24zM128 464a48 48 0 1 1 96 0 48 48 0 1 1 -96 0zm336-48a48 48 0 1 1 0 96 48 48 0 1 1 0-96zM252 160c0 11 9 20 20 20h44v44c0 11 9 20 20 20s20-9 20-20V180h44c11 0 20-9 20-20s-9-20-20-20H356V96c0-11-9-20-20-20s-20 9-20 20v44H272c-11 0-20 9-20 20z" />
                                        </svg></button>
                                    <button class="btn buy">
                                        Buy
                                        <svg xmlns="http://www.w3.org/2000/svg" height="1em" viewBox="0 0 576 512">

                                        <path
                                            d="M0 112.5V422.3c0 18 10.1 35 27 41.3c87 32.5 174 10.3 261-11.9c79.8-20.3 159.6-40.7 239.3-18.9c23 6.3 48.7-9.5 48.7-33.4V89.7c0-18-10.1-35-27-41.3C462 15.9 375 38.1 288 60.3C208.2 80.6 128.4 100.9 48.7 79.1C25.6 72.8 0 88.6 0 112.5zM288 352c-44.2 0-80-43-80-96s35.8-96 80-96s80 43 80 96s-35.8 96-80 96zM64 352c35.3 0 64 28.7 64 64H64V352zm64-208c0 35.3-28.7 64-64 64V144h64zM512 304v64H448c0-35.3 28.7-64 64-64zM448 96h64v64c-35.3 0-64-28.7-64-64z" />
                                        </svg>
                                    </button>
                                </div>
                            </div>
                        </div>
                        <!-- end product -->

                    </div>
                </div>
            </section>

        </main>


        <!--        <main class="main">
                    <section class="hero">
                        <div class="container">
                            <img src="./img/sale.jpg" alt="">
                        </div>
                    </section>
                    <section class="sale">
                        <div class="container">
                            <div class="sale__slider">
                                <img src="./img/banner/banner.jpg" alt="banner-product" />
                            </div>
                            <div class="sale__right">
                                <h4 class="heading --h4">sale giá tốt</h4>
                                <div class="sale__right--list">
                                    <div class="sale__product product">
                                        <div class="carousel-img" data-type="account">
                                            <a href="#" class="img"><img src="./img/ao/ao-1-a.jpg" alt="ao-thun" /></a>
                                            <a href="#" class="img"><img src="./img/ao/ao-1-b.jpg" alt="ao-thun" /></a>
                                        </div>
                                        <div class="productInfo">
                                            <h3 class="name"><a href="#">Áo Thun</a></h3>
                                            <span class="price">350000vnd</span>
                                            <p class="desc">Vải cotton hay còn gọi là cotton 100%, được làm từ sợi bông thiên nhiên. Đây là chất
                                                liệu
                                                phổ biến nhất được chọn làm áo thun bởi vì áo thun</p>
                                            <div class="btnactiongr">
                                                <button class="btn addcart">Add Cart <svg xmlns="http://www.w3.org/2000/svg" height="1em"
                                                                                          viewBox="0 0 576 512">
        
                                                    <path
                                                        d="M0 24C0 10.7 10.7 0 24 0H69.5c22 0 41.5 12.8 50.6 32h411c26.3 0 45.5 25 38.6 50.4l-41 152.3c-8.5 31.4-37 53.3-69.5 53.3H170.7l5.4 28.5c2.2 11.3 12.1 19.5 23.6 19.5H488c13.3 0 24 10.7 24 24s-10.7 24-24 24H199.7c-34.6 0-64.3-24.6-70.7-58.5L77.4 54.5c-.7-3.8-4-6.5-7.9-6.5H24C10.7 48 0 37.3 0 24zM128 464a48 48 0 1 1 96 0 48 48 0 1 1 -96 0zm336-48a48 48 0 1 1 0 96 48 48 0 1 1 0-96zM252 160c0 11 9 20 20 20h44v44c0 11 9 20 20 20s20-9 20-20V180h44c11 0 20-9 20-20s-9-20-20-20H356V96c0-11-9-20-20-20s-20 9-20 20v44H272c-11 0-20 9-20 20z" />
                                                    </svg></button>
                                                <button class="btn buy">
                                                    Buy
                                                    <svg xmlns="http://www.w3.org/2000/svg" height="1em" viewBox="0 0 576 512">
        
                                                    <path
                                                        d="M0 112.5V422.3c0 18 10.1 35 27 41.3c87 32.5 174 10.3 261-11.9c79.8-20.3 159.6-40.7 239.3-18.9c23 6.3 48.7-9.5 48.7-33.4V89.7c0-18-10.1-35-27-41.3C462 15.9 375 38.1 288 60.3C208.2 80.6 128.4 100.9 48.7 79.1C25.6 72.8 0 88.6 0 112.5zM288 352c-44.2 0-80-43-80-96s35.8-96 80-96s80 43 80 96s-35.8 96-80 96zM64 352c35.3 0 64 28.7 64 64H64V352zm64-208c0 35.3-28.7 64-64 64V144h64zM512 304v64H448c0-35.3 28.7-64 64-64zM448 96h64v64c-35.3 0-64-28.7-64-64z" />
                                                    </svg>
                                                </button>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="sale__product product">
                                        <div class="carousel-img" data-type="account">
                                            <a href="#" class="img"><img src="./img/ao/ao-1-a.jpg" alt="ao-thun" /></a>
                                            <a href="#" class="img"><img src="./img/ao/ao-1-b.jpg" alt="ao-thun" /></a>
                                        </div>
                                        <div class="productInfo">
                                            <h3 class="name"><a href="#">Áo Thun</a></h3>
                                            <span class="price">350000vnd</span>
                                            <p class="desc">Vải cotton hay còn gọi là cotton 100%, được làm từ sợi bông thiên nhiên. Đây là chất
                                                liệu
                                                phổ biến nhất được chọn làm áo thun bởi vì áo thun</p>
                                            <div class="btnactiongr">
                                                <button class="btn addcart">Add Cart <svg xmlns="http://www.w3.org/2000/svg" height="1em"
                                                                                          viewBox="0 0 576 512">
        
                                                    <path
                                                        d="M0 24C0 10.7 10.7 0 24 0H69.5c22 0 41.5 12.8 50.6 32h411c26.3 0 45.5 25 38.6 50.4l-41 152.3c-8.5 31.4-37 53.3-69.5 53.3H170.7l5.4 28.5c2.2 11.3 12.1 19.5 23.6 19.5H488c13.3 0 24 10.7 24 24s-10.7 24-24 24H199.7c-34.6 0-64.3-24.6-70.7-58.5L77.4 54.5c-.7-3.8-4-6.5-7.9-6.5H24C10.7 48 0 37.3 0 24zM128 464a48 48 0 1 1 96 0 48 48 0 1 1 -96 0zm336-48a48 48 0 1 1 0 96 48 48 0 1 1 0-96zM252 160c0 11 9 20 20 20h44v44c0 11 9 20 20 20s20-9 20-20V180h44c11 0 20-9 20-20s-9-20-20-20H356V96c0-11-9-20-20-20s-20 9-20 20v44H272c-11 0-20 9-20 20z" />
                                                    </svg></button>
                                                <button class="btn buy">
                                                    Buy
                                                    <svg xmlns="http://www.w3.org/2000/svg" height="1em" viewBox="0 0 576 512">
        
                                                    <path
                                                        d="M0 112.5V422.3c0 18 10.1 35 27 41.3c87 32.5 174 10.3 261-11.9c79.8-20.3 159.6-40.7 239.3-18.9c23 6.3 48.7-9.5 48.7-33.4V89.7c0-18-10.1-35-27-41.3C462 15.9 375 38.1 288 60.3C208.2 80.6 128.4 100.9 48.7 79.1C25.6 72.8 0 88.6 0 112.5zM288 352c-44.2 0-80-43-80-96s35.8-96 80-96s80 43 80 96s-35.8 96-80 96zM64 352c35.3 0 64 28.7 64 64H64V352zm64-208c0 35.3-28.7 64-64 64V144h64zM512 304v64H448c0-35.3 28.7-64 64-64zM448 96h64v64c-35.3 0-64-28.7-64-64z" />
                                                    </svg>
                                                </button>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="sale__product product">
                                        <div class="carousel-img" data-type="account">
                                            <a href="#" class="img"><img src="./img/ao/ao-1-a.jpg" alt="ao-thun" /></a>
                                            <a href="#" class="img"><img src="./img/ao/ao-1-b.jpg" alt="ao-thun" /></a>
                                        </div>
                                        <div class="productInfo">
                                            <h3 class="name"><a href="#">Áo Thun</a></h3>
                                            <span class="price">350000vnd</span>
                                            <p class="desc">Vải cotton hay còn gọi là cotton 100%, được làm từ sợi bông thiên nhiên. Đây là chất
                                                liệu
                                                phổ biến nhất được chọn làm áo thun bởi vì áo thun</p>
                                            <div class="btnactiongr">
                                                <button class="btn addcart">Add Cart <svg xmlns="http://www.w3.org/2000/svg" height="1em"
                                                                                          viewBox="0 0 576 512">
        
                                                    <path
                                                        d="M0 24C0 10.7 10.7 0 24 0H69.5c22 0 41.5 12.8 50.6 32h411c26.3 0 45.5 25 38.6 50.4l-41 152.3c-8.5 31.4-37 53.3-69.5 53.3H170.7l5.4 28.5c2.2 11.3 12.1 19.5 23.6 19.5H488c13.3 0 24 10.7 24 24s-10.7 24-24 24H199.7c-34.6 0-64.3-24.6-70.7-58.5L77.4 54.5c-.7-3.8-4-6.5-7.9-6.5H24C10.7 48 0 37.3 0 24zM128 464a48 48 0 1 1 96 0 48 48 0 1 1 -96 0zm336-48a48 48 0 1 1 0 96 48 48 0 1 1 0-96zM252 160c0 11 9 20 20 20h44v44c0 11 9 20 20 20s20-9 20-20V180h44c11 0 20-9 20-20s-9-20-20-20H356V96c0-11-9-20-20-20s-20 9-20 20v44H272c-11 0-20 9-20 20z" />
                                                    </svg></button>
                                                <button class="btn buy">
                                                    Buy
                                                    <svg xmlns="http://www.w3.org/2000/svg" height="1em" viewBox="0 0 576 512">
        
                                                    <path
                                                        d="M0 112.5V422.3c0 18 10.1 35 27 41.3c87 32.5 174 10.3 261-11.9c79.8-20.3 159.6-40.7 239.3-18.9c23 6.3 48.7-9.5 48.7-33.4V89.7c0-18-10.1-35-27-41.3C462 15.9 375 38.1 288 60.3C208.2 80.6 128.4 100.9 48.7 79.1C25.6 72.8 0 88.6 0 112.5zM288 352c-44.2 0-80-43-80-96s35.8-96 80-96s80 43 80 96s-35.8 96-80 96zM64 352c35.3 0 64 28.7 64 64H64V352zm64-208c0 35.3-28.7 64-64 64V144h64zM512 304v64H448c0-35.3 28.7-64 64-64zM448 96h64v64c-35.3 0-64-28.7-64-64z" />
                                                    </svg>
                                                </button>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="sale__product product">
                                        <div class="carousel-img" data-type="account">
                                            <a href="#" class="img"><img src="./img/ao/ao-1-a.jpg" alt="ao-thun" /></a>
                                            <a href="#" class="img"><img src="./img/ao/ao-1-b.jpg" alt="ao-thun" /></a>
                                        </div>
                                        <div class="productInfo">
                                            <h3 class="name"><a href="#">Áo Thun</a></h3>
                                            <span class="price">350000vnd</span>
                                            <p class="desc">Vải cotton hay còn gọi là cotton 100%, được làm từ sợi bông thiên nhiên. Đây là chất
                                                liệu
                                                phổ biến nhất được chọn làm áo thun bởi vì áo thun</p>
                                            <div class="btnactiongr">
                                                <button class="btn addcart">Add Cart <svg xmlns="http://www.w3.org/2000/svg" height="1em"
                                                                                          viewBox="0 0 576 512">
        
                                                    <path
                                                        d="M0 24C0 10.7 10.7 0 24 0H69.5c22 0 41.5 12.8 50.6 32h411c26.3 0 45.5 25 38.6 50.4l-41 152.3c-8.5 31.4-37 53.3-69.5 53.3H170.7l5.4 28.5c2.2 11.3 12.1 19.5 23.6 19.5H488c13.3 0 24 10.7 24 24s-10.7 24-24 24H199.7c-34.6 0-64.3-24.6-70.7-58.5L77.4 54.5c-.7-3.8-4-6.5-7.9-6.5H24C10.7 48 0 37.3 0 24zM128 464a48 48 0 1 1 96 0 48 48 0 1 1 -96 0zm336-48a48 48 0 1 1 0 96 48 48 0 1 1 0-96zM252 160c0 11 9 20 20 20h44v44c0 11 9 20 20 20s20-9 20-20V180h44c11 0 20-9 20-20s-9-20-20-20H356V96c0-11-9-20-20-20s-20 9-20 20v44H272c-11 0-20 9-20 20z" />
                                                    </svg></button>
                                                <button class="btn buy">
                                                    Buy
                                                    <svg xmlns="http://www.w3.org/2000/svg" height="1em" viewBox="0 0 576 512">
        
                                                    <path
                                                        d="M0 112.5V422.3c0 18 10.1 35 27 41.3c87 32.5 174 10.3 261-11.9c79.8-20.3 159.6-40.7 239.3-18.9c23 6.3 48.7-9.5 48.7-33.4V89.7c0-18-10.1-35-27-41.3C462 15.9 375 38.1 288 60.3C208.2 80.6 128.4 100.9 48.7 79.1C25.6 72.8 0 88.6 0 112.5zM288 352c-44.2 0-80-43-80-96s35.8-96 80-96s80 43 80 96s-35.8 96-80 96zM64 352c35.3 0 64 28.7 64 64H64V352zm64-208c0 35.3-28.7 64-64 64V144h64zM512 304v64H448c0-35.3 28.7-64 64-64zM448 96h64v64c-35.3 0-64-28.7-64-64z" />
                                                    </svg>
                                                </button>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </section>
                    <section class="ao pb">
                        <div class="container">
                            <div class="heading --h4">Các sản phẩm áo</div>
                            <div class="ao__list listproduct">
                                <div class="ao__list--item product">
                                    <div class="carousel-img" data-type="account">
                                        <a href="#" class="img"><img src="./img/ao/ao-1-a.jpg" alt="ao-thun" /></a>
                                        <a href="#" class="img"><img src="./img/ao/ao-1-b.jpg" alt="ao-thun" /></a>
                                    </div>
                                    <div class="productInfo">
                                        <h3 class="name"><a href="#">Áo Thun</a></h3>
                                        <span class="price">350000vnd</span>
                                        <p class="desc">Vải cotton hay còn gọi là cotton 100%, được làm từ sợi bông thiên nhiên. Đây là chất liệu
                                            phổ biến nhất được chọn làm áo thun bởi vì áo thun</p>
                                        <div class="btnactiongr">
                                            <button class="btn addcart">Add Cart <svg xmlns="http://www.w3.org/2000/svg" height="1em"
                                                                                      viewBox="0 0 576 512">
        
                                                <path
                                                    d="M0 24C0 10.7 10.7 0 24 0H69.5c22 0 41.5 12.8 50.6 32h411c26.3 0 45.5 25 38.6 50.4l-41 152.3c-8.5 31.4-37 53.3-69.5 53.3H170.7l5.4 28.5c2.2 11.3 12.1 19.5 23.6 19.5H488c13.3 0 24 10.7 24 24s-10.7 24-24 24H199.7c-34.6 0-64.3-24.6-70.7-58.5L77.4 54.5c-.7-3.8-4-6.5-7.9-6.5H24C10.7 48 0 37.3 0 24zM128 464a48 48 0 1 1 96 0 48 48 0 1 1 -96 0zm336-48a48 48 0 1 1 0 96 48 48 0 1 1 0-96zM252 160c0 11 9 20 20 20h44v44c0 11 9 20 20 20s20-9 20-20V180h44c11 0 20-9 20-20s-9-20-20-20H356V96c0-11-9-20-20-20s-20 9-20 20v44H272c-11 0-20 9-20 20z" />
                                                </svg></button>
                                            <button class="btn buy">
                                                Buy
                                                <svg xmlns="http://www.w3.org/2000/svg" height="1em" viewBox="0 0 576 512">
        
                                                <path
                                                    d="M0 112.5V422.3c0 18 10.1 35 27 41.3c87 32.5 174 10.3 261-11.9c79.8-20.3 159.6-40.7 239.3-18.9c23 6.3 48.7-9.5 48.7-33.4V89.7c0-18-10.1-35-27-41.3C462 15.9 375 38.1 288 60.3C208.2 80.6 128.4 100.9 48.7 79.1C25.6 72.8 0 88.6 0 112.5zM288 352c-44.2 0-80-43-80-96s35.8-96 80-96s80 43 80 96s-35.8 96-80 96zM64 352c35.3 0 64 28.7 64 64H64V352zm64-208c0 35.3-28.7 64-64 64V144h64zM512 304v64H448c0-35.3 28.7-64 64-64zM448 96h64v64c-35.3 0-64-28.7-64-64z" />
                                                </svg>
                                            </button>
                                        </div>
                                    </div>
                                </div>
                                <div class="ao__list--item product">
                                    <div class="carousel-img" data-type="account">
                                        <a href="#" class="img"><img src="./img/ao/ao-1-a.jpg" alt="ao-thun" /></a>
                                        <a href="#" class="img"><img src="./img/ao/ao-1-b.jpg" alt="ao-thun" /></a>
                                    </div>
                                    <div class="productInfo">
                                        <h3 class="name"><a href="#">Áo Thun</a></h3>
                                        <span class="price">350000vnd</span>
                                        <p class="desc">Vải cotton hay còn gọi là cotton 100%, được làm từ sợi bông thiên nhiên. Đây là chất liệu
                                            phổ biến nhất được chọn làm áo thun bởi vì áo thun</p>
                                        <div class="btnactiongr">
                                            <button class="btn addcart">Add Cart <svg xmlns="http://www.w3.org/2000/svg" height="1em"
                                                                                      viewBox="0 0 576 512">
        
                                                <path
                                                    d="M0 24C0 10.7 10.7 0 24 0H69.5c22 0 41.5 12.8 50.6 32h411c26.3 0 45.5 25 38.6 50.4l-41 152.3c-8.5 31.4-37 53.3-69.5 53.3H170.7l5.4 28.5c2.2 11.3 12.1 19.5 23.6 19.5H488c13.3 0 24 10.7 24 24s-10.7 24-24 24H199.7c-34.6 0-64.3-24.6-70.7-58.5L77.4 54.5c-.7-3.8-4-6.5-7.9-6.5H24C10.7 48 0 37.3 0 24zM128 464a48 48 0 1 1 96 0 48 48 0 1 1 -96 0zm336-48a48 48 0 1 1 0 96 48 48 0 1 1 0-96zM252 160c0 11 9 20 20 20h44v44c0 11 9 20 20 20s20-9 20-20V180h44c11 0 20-9 20-20s-9-20-20-20H356V96c0-11-9-20-20-20s-20 9-20 20v44H272c-11 0-20 9-20 20z" />
                                                </svg></button>
                                            <button class="btn buy">
                                                Buy
                                                <svg xmlns="http://www.w3.org/2000/svg" height="1em" viewBox="0 0 576 512">
        
                                                <path
                                                    d="M0 112.5V422.3c0 18 10.1 35 27 41.3c87 32.5 174 10.3 261-11.9c79.8-20.3 159.6-40.7 239.3-18.9c23 6.3 48.7-9.5 48.7-33.4V89.7c0-18-10.1-35-27-41.3C462 15.9 375 38.1 288 60.3C208.2 80.6 128.4 100.9 48.7 79.1C25.6 72.8 0 88.6 0 112.5zM288 352c-44.2 0-80-43-80-96s35.8-96 80-96s80 43 80 96s-35.8 96-80 96zM64 352c35.3 0 64 28.7 64 64H64V352zm64-208c0 35.3-28.7 64-64 64V144h64zM512 304v64H448c0-35.3 28.7-64 64-64zM448 96h64v64c-35.3 0-64-28.7-64-64z" />
                                                </svg>
                                            </button>
                                        </div>
                                    </div>
                                </div>
                                <div class="ao__list--item product">
                                    <div class="carousel-img" data-type="account">
                                        <a href="#" class="img"><img src="./img/ao/ao-1-a.jpg" alt="ao-thun" /></a>
                                        <a href="#" class="img"><img src="./img/ao/ao-1-b.jpg" alt="ao-thun" /></a>
                                    </div>
                                    <div class="productInfo">
                                        <h3 class="name"><a href="#">Áo Thun</a></h3>
                                        <span class="price">350000vnd</span>
                                        <p class="desc">Vải cotton hay còn gọi là cotton 100%, được làm từ sợi bông thiên nhiên. Đây là chất liệu
                                            phổ biến nhất được chọn làm áo thun bởi vì áo thun</p>
                                        <div class="btnactiongr">
                                            <button class="btn addcart">Add Cart <svg xmlns="http://www.w3.org/2000/svg" height="1em"
                                                                                      viewBox="0 0 576 512">
        
                                                <path
                                                    d="M0 24C0 10.7 10.7 0 24 0H69.5c22 0 41.5 12.8 50.6 32h411c26.3 0 45.5 25 38.6 50.4l-41 152.3c-8.5 31.4-37 53.3-69.5 53.3H170.7l5.4 28.5c2.2 11.3 12.1 19.5 23.6 19.5H488c13.3 0 24 10.7 24 24s-10.7 24-24 24H199.7c-34.6 0-64.3-24.6-70.7-58.5L77.4 54.5c-.7-3.8-4-6.5-7.9-6.5H24C10.7 48 0 37.3 0 24zM128 464a48 48 0 1 1 96 0 48 48 0 1 1 -96 0zm336-48a48 48 0 1 1 0 96 48 48 0 1 1 0-96zM252 160c0 11 9 20 20 20h44v44c0 11 9 20 20 20s20-9 20-20V180h44c11 0 20-9 20-20s-9-20-20-20H356V96c0-11-9-20-20-20s-20 9-20 20v44H272c-11 0-20 9-20 20z" />
                                                </svg></button>
                                            <button class="btn buy">
                                                Buy
                                                <svg xmlns="http://www.w3.org/2000/svg" height="1em" viewBox="0 0 576 512">
        
                                                <path
                                                    d="M0 112.5V422.3c0 18 10.1 35 27 41.3c87 32.5 174 10.3 261-11.9c79.8-20.3 159.6-40.7 239.3-18.9c23 6.3 48.7-9.5 48.7-33.4V89.7c0-18-10.1-35-27-41.3C462 15.9 375 38.1 288 60.3C208.2 80.6 128.4 100.9 48.7 79.1C25.6 72.8 0 88.6 0 112.5zM288 352c-44.2 0-80-43-80-96s35.8-96 80-96s80 43 80 96s-35.8 96-80 96zM64 352c35.3 0 64 28.7 64 64H64V352zm64-208c0 35.3-28.7 64-64 64V144h64zM512 304v64H448c0-35.3 28.7-64 64-64zM448 96h64v64c-35.3 0-64-28.7-64-64z" />
                                                </svg>
                                            </button>
                                        </div>
                                    </div>
                                </div>
                                <div class="ao__list--item product">
                                    <div class="carousel-img" data-type="account">
                                        <a href="#" class="img"><img src="./img/ao/ao-1-a.jpg" alt="ao-thun" /></a>
                                        <a href="#" class="img"><img src="./img/ao/ao-1-b.jpg" alt="ao-thun" /></a>
                                    </div>
                                    <div class="productInfo">
                                        <h3 class="name"><a href="#">Áo Thun</a></h3>
                                        <span class="price">350000vnd</span>
                                        <p class="desc">Vải cotton hay còn gọi là cotton 100%, được làm từ sợi bông thiên nhiên. Đây là chất liệu
                                            phổ biến nhất được chọn làm áo thun bởi vì áo thun</p>
                                        <div class="btnactiongr">
                                            <button class="btn addcart">Add Cart <svg xmlns="http://www.w3.org/2000/svg" height="1em"
                                                                                      viewBox="0 0 576 512">
        
                                                <path
                                                    d="M0 24C0 10.7 10.7 0 24 0H69.5c22 0 41.5 12.8 50.6 32h411c26.3 0 45.5 25 38.6 50.4l-41 152.3c-8.5 31.4-37 53.3-69.5 53.3H170.7l5.4 28.5c2.2 11.3 12.1 19.5 23.6 19.5H488c13.3 0 24 10.7 24 24s-10.7 24-24 24H199.7c-34.6 0-64.3-24.6-70.7-58.5L77.4 54.5c-.7-3.8-4-6.5-7.9-6.5H24C10.7 48 0 37.3 0 24zM128 464a48 48 0 1 1 96 0 48 48 0 1 1 -96 0zm336-48a48 48 0 1 1 0 96 48 48 0 1 1 0-96zM252 160c0 11 9 20 20 20h44v44c0 11 9 20 20 20s20-9 20-20V180h44c11 0 20-9 20-20s-9-20-20-20H356V96c0-11-9-20-20-20s-20 9-20 20v44H272c-11 0-20 9-20 20z" />
                                                </svg></button>
                                            <button class="btn buy">
                                                Buy
                                                <svg xmlns="http://www.w3.org/2000/svg" height="1em" viewBox="0 0 576 512">
        
                                                <path
                                                    d="M0 112.5V422.3c0 18 10.1 35 27 41.3c87 32.5 174 10.3 261-11.9c79.8-20.3 159.6-40.7 239.3-18.9c23 6.3 48.7-9.5 48.7-33.4V89.7c0-18-10.1-35-27-41.3C462 15.9 375 38.1 288 60.3C208.2 80.6 128.4 100.9 48.7 79.1C25.6 72.8 0 88.6 0 112.5zM288 352c-44.2 0-80-43-80-96s35.8-96 80-96s80 43 80 96s-35.8 96-80 96zM64 352c35.3 0 64 28.7 64 64H64V352zm64-208c0 35.3-28.7 64-64 64V144h64zM512 304v64H448c0-35.3 28.7-64 64-64zM448 96h64v64c-35.3 0-64-28.7-64-64z" />
                                                </svg>
                                            </button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </section>
                     <div class="hot__productlist listproduct">
                      <div class="hot__productlist--item product">
                        <div class="carousel-img" data-type="account">
                          <a href="#" class="img"><img src="./img/ao/ao-1-a.jpg" alt="ao-thun" /></a>
                          <a href="#" class="img"><img src="./img/ao/ao-1-b.jpg" alt="ao-thun" /></a>
                        </div>
                        <div class="productInfo">
                          <h3 class="name"><a href="#">Áo Thun</a></h3>
                          <span class="price">350000vnd</span>
                          <p class="desc">Vải cotton hay còn gọi là cotton 100%, được làm từ sợi bông thiên nhiên. Đây là chất liệu
                            phổ biến nhất được chọn làm áo thun bởi vì áo thun</p>
                          <div class="btnactiongr">
                            <button class="btn addcart">Add Cart <svg xmlns="http://www.w3.org/2000/svg" height="1em"
                                viewBox="0 0 576 512">
                                
                                <path
                                  d="M0 24C0 10.7 10.7 0 24 0H69.5c22 0 41.5 12.8 50.6 32h411c26.3 0 45.5 25 38.6 50.4l-41 152.3c-8.5 31.4-37 53.3-69.5 53.3H170.7l5.4 28.5c2.2 11.3 12.1 19.5 23.6 19.5H488c13.3 0 24 10.7 24 24s-10.7 24-24 24H199.7c-34.6 0-64.3-24.6-70.7-58.5L77.4 54.5c-.7-3.8-4-6.5-7.9-6.5H24C10.7 48 0 37.3 0 24zM128 464a48 48 0 1 1 96 0 48 48 0 1 1 -96 0zm336-48a48 48 0 1 1 0 96 48 48 0 1 1 0-96zM252 160c0 11 9 20 20 20h44v44c0 11 9 20 20 20s20-9 20-20V180h44c11 0 20-9 20-20s-9-20-20-20H356V96c0-11-9-20-20-20s-20 9-20 20v44H272c-11 0-20 9-20 20z" />
                              </svg></button>
                            <button class="btn buy">
                              Buy
                              <svg xmlns="http://www.w3.org/2000/svg" height="1em"
                                viewBox="0 0 576 512">
                             
                                <path
                                  d="M0 112.5V422.3c0 18 10.1 35 27 41.3c87 32.5 174 10.3 261-11.9c79.8-20.3 159.6-40.7 239.3-18.9c23 6.3 48.7-9.5 48.7-33.4V89.7c0-18-10.1-35-27-41.3C462 15.9 375 38.1 288 60.3C208.2 80.6 128.4 100.9 48.7 79.1C25.6 72.8 0 88.6 0 112.5zM288 352c-44.2 0-80-43-80-96s35.8-96 80-96s80 43 80 96s-35.8 96-80 96zM64 352c35.3 0 64 28.7 64 64H64V352zm64-208c0 35.3-28.7 64-64 64V144h64zM512 304v64H448c0-35.3 28.7-64 64-64zM448 96h64v64c-35.3 0-64-28.7-64-64z" />
                              </svg>
                            </button>
                          </div>
                        </div>
                      </div>
                      <div class="hot__productlist--item product">
                        <div class="carousel-img" data-type="account">
                          <a href="#" class="img"><img src="./img/ao/ao-1-a.jpg" alt="ao-thun" /></a>
                          <a href="#" class="img"><img src="./img/ao/ao-1-b.jpg" alt="ao-thun" /></a>
                        </div>
                        <div class="productInfo">
                          <h3 class="name"><a href="#">Áo Thun</a></h3>
                          <span class="price">350000vnd</span>
                          <p class="desc">Vải cotton hay còn gọi là cotton 100%, được làm từ sợi bông thiên nhiên. Đây là chất liệu
                            phổ biến nhất được chọn làm áo thun bởi vì áo thun</p>
                          <div class="btnactiongr">
                            <button class="btn addcart">Add Cart <svg xmlns="http://www.w3.org/2000/svg" height="1em"
                                viewBox="0 0 576 512">
                                
                                <path
                                  d="M0 24C0 10.7 10.7 0 24 0H69.5c22 0 41.5 12.8 50.6 32h411c26.3 0 45.5 25 38.6 50.4l-41 152.3c-8.5 31.4-37 53.3-69.5 53.3H170.7l5.4 28.5c2.2 11.3 12.1 19.5 23.6 19.5H488c13.3 0 24 10.7 24 24s-10.7 24-24 24H199.7c-34.6 0-64.3-24.6-70.7-58.5L77.4 54.5c-.7-3.8-4-6.5-7.9-6.5H24C10.7 48 0 37.3 0 24zM128 464a48 48 0 1 1 96 0 48 48 0 1 1 -96 0zm336-48a48 48 0 1 1 0 96 48 48 0 1 1 0-96zM252 160c0 11 9 20 20 20h44v44c0 11 9 20 20 20s20-9 20-20V180h44c11 0 20-9 20-20s-9-20-20-20H356V96c0-11-9-20-20-20s-20 9-20 20v44H272c-11 0-20 9-20 20z" />
                              </svg></button>
                            <button class="btn buy">
                              Buy
                              <svg xmlns="http://www.w3.org/2000/svg" height="1em"
                                viewBox="0 0 576 512">
                             
                                <path
                                  d="M0 112.5V422.3c0 18 10.1 35 27 41.3c87 32.5 174 10.3 261-11.9c79.8-20.3 159.6-40.7 239.3-18.9c23 6.3 48.7-9.5 48.7-33.4V89.7c0-18-10.1-35-27-41.3C462 15.9 375 38.1 288 60.3C208.2 80.6 128.4 100.9 48.7 79.1C25.6 72.8 0 88.6 0 112.5zM288 352c-44.2 0-80-43-80-96s35.8-96 80-96s80 43 80 96s-35.8 96-80 96zM64 352c35.3 0 64 28.7 64 64H64V352zm64-208c0 35.3-28.7 64-64 64V144h64zM512 304v64H448c0-35.3 28.7-64 64-64zM448 96h64v64c-35.3 0-64-28.7-64-64z" />
                              </svg>
                            </button>
                          </div>
                        </div>
                      </div>
                      <div class="hot__productlist--item product">
                        <div class="carousel-img" data-type="account">
                          <a href="#" class="img"><img src="./img/ao/ao-1-a.jpg" alt="ao-thun" /></a>
                          <a href="#" class="img"><img src="./img/ao/ao-1-b.jpg" alt="ao-thun" /></a>
                        </div>
                        <div class="productInfo">
                          <h3 class="name"><a href="#">Áo Thun</a></h3>
                          <span class="price">350000vnd</span>
                          <p class="desc">Vải cotton hay còn gọi là cotton 100%, được làm từ sợi bông thiên nhiên. Đây là chất liệu
                            phổ biến nhất được chọn làm áo thun bởi vì áo thun</p>
                          <div class="btnactiongr">
                            <button class="btn addcart">Add Cart <svg xmlns="http://www.w3.org/2000/svg" height="1em"
                                viewBox="0 0 576 512">
                                
                                <path
                                  d="M0 24C0 10.7 10.7 0 24 0H69.5c22 0 41.5 12.8 50.6 32h411c26.3 0 45.5 25 38.6 50.4l-41 152.3c-8.5 31.4-37 53.3-69.5 53.3H170.7l5.4 28.5c2.2 11.3 12.1 19.5 23.6 19.5H488c13.3 0 24 10.7 24 24s-10.7 24-24 24H199.7c-34.6 0-64.3-24.6-70.7-58.5L77.4 54.5c-.7-3.8-4-6.5-7.9-6.5H24C10.7 48 0 37.3 0 24zM128 464a48 48 0 1 1 96 0 48 48 0 1 1 -96 0zm336-48a48 48 0 1 1 0 96 48 48 0 1 1 0-96zM252 160c0 11 9 20 20 20h44v44c0 11 9 20 20 20s20-9 20-20V180h44c11 0 20-9 20-20s-9-20-20-20H356V96c0-11-9-20-20-20s-20 9-20 20v44H272c-11 0-20 9-20 20z" />
                              </svg></button>
                            <button class="btn buy">
                              Buy
                              <svg xmlns="http://www.w3.org/2000/svg" height="1em"
                                viewBox="0 0 576 512">
                             
                                <path
                                  d="M0 112.5V422.3c0 18 10.1 35 27 41.3c87 32.5 174 10.3 261-11.9c79.8-20.3 159.6-40.7 239.3-18.9c23 6.3 48.7-9.5 48.7-33.4V89.7c0-18-10.1-35-27-41.3C462 15.9 375 38.1 288 60.3C208.2 80.6 128.4 100.9 48.7 79.1C25.6 72.8 0 88.6 0 112.5zM288 352c-44.2 0-80-43-80-96s35.8-96 80-96s80 43 80 96s-35.8 96-80 96zM64 352c35.3 0 64 28.7 64 64H64V352zm64-208c0 35.3-28.7 64-64 64V144h64zM512 304v64H448c0-35.3 28.7-64 64-64zM448 96h64v64c-35.3 0-64-28.7-64-64z" />
                              </svg>
                            </button>
                          </div>
                        </div>
                      </div>
                      <div class="hot__productlist--item product">
                        <div class="carousel-img" data-type="account">
                          <a href="#" class="img"><img src="./img/ao/ao-1-a.jpg" alt="ao-thun" /></a>
                          <a href="#" class="img"><img src="./img/ao/ao-1-b.jpg" alt="ao-thun" /></a>
                        </div>
                        <div class="productInfo">
                          <h3 class="name"><a href="#">Áo Thun</a></h3>
                          <span class="price">350000vnd</span>
                          <p class="desc">Vải cotton hay còn gọi là cotton 100%, được làm từ sợi bông thiên nhiên. Đây là chất liệu
                            phổ biến nhất được chọn làm áo thun bởi vì áo thun</p>
                          <div class="btnactiongr">
                            <button class="btn addcart">Add Cart <svg xmlns="http://www.w3.org/2000/svg" height="1em"
                                viewBox="0 0 576 512">
                                
                                <path
                                  d="M0 24C0 10.7 10.7 0 24 0H69.5c22 0 41.5 12.8 50.6 32h411c26.3 0 45.5 25 38.6 50.4l-41 152.3c-8.5 31.4-37 53.3-69.5 53.3H170.7l5.4 28.5c2.2 11.3 12.1 19.5 23.6 19.5H488c13.3 0 24 10.7 24 24s-10.7 24-24 24H199.7c-34.6 0-64.3-24.6-70.7-58.5L77.4 54.5c-.7-3.8-4-6.5-7.9-6.5H24C10.7 48 0 37.3 0 24zM128 464a48 48 0 1 1 96 0 48 48 0 1 1 -96 0zm336-48a48 48 0 1 1 0 96 48 48 0 1 1 0-96zM252 160c0 11 9 20 20 20h44v44c0 11 9 20 20 20s20-9 20-20V180h44c11 0 20-9 20-20s-9-20-20-20H356V96c0-11-9-20-20-20s-20 9-20 20v44H272c-11 0-20 9-20 20z" />
                              </svg></button>
                            <button class="btn buy">
                              Buy
                              <svg xmlns="http://www.w3.org/2000/svg" height="1em"
                                viewBox="0 0 576 512">
                             
                                <path
                                  d="M0 112.5V422.3c0 18 10.1 35 27 41.3c87 32.5 174 10.3 261-11.9c79.8-20.3 159.6-40.7 239.3-18.9c23 6.3 48.7-9.5 48.7-33.4V89.7c0-18-10.1-35-27-41.3C462 15.9 375 38.1 288 60.3C208.2 80.6 128.4 100.9 48.7 79.1C25.6 72.8 0 88.6 0 112.5zM288 352c-44.2 0-80-43-80-96s35.8-96 80-96s80 43 80 96s-35.8 96-80 96zM64 352c35.3 0 64 28.7 64 64H64V352zm64-208c0 35.3-28.7 64-64 64V144h64zM512 304v64H448c0-35.3 28.7-64 64-64zM448 96h64v64c-35.3 0-64-28.7-64-64z" />
                              </svg>
                            </button>
                          </div>
                        </div>
                      </div>
                      
                    </div> 
                </main>-->
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
                            <a  href="tel:0961290556">0961290556</a>
                        </div>
                        <div class="contact-gr">
                            <span class="label">Email</span>
                            <a  href="mailto:Example@gmail.com">Example@gmail.com</a>

                        </div>
                        <div class="contact-gr">
                            <span class="label">Địa chỉ</span>
                            <a  href="mailto:Example@gmail.com">273 an dương vương, phường 1 quận 5, TPHCM</a>

                        </div>
                        <div class="contact-gr">
                            <span class="label">Thứ 2 - Thứ 7</span>
                            <a  >8h50 - 21h00</a>
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
                        <a  href="mailto:Example@gmail.com">273 an dương vương, phường 1 quận 5, TPHCM</a>

                    </div>

                </div>
        </footer>


        <!--                <script type="text/javascript" src="dest/jsmain.min.js"></script>-->
        <!--                <script type="text/javascript" src="./dest/main.js"></script>-->
        <script src="${pageContext.request.contextPath}/Views/dest/jsmain.min.js"></script>
        <script src="${pageContext.request.contextPath}/Views/dest/main.js" ></script>


    </body>
</html>
