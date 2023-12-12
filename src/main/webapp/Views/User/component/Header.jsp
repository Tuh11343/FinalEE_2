<%@page contentType="text/html" pageEncoding="UTF-8" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
        <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


            <header class="header">
                <div class="container">
                    <div class="header__left">
                        <div class="header__logo">
                            <a href="#"><img class="img"
                                    src="${pageContext.request.contextPath}/Views/User/img/logo.png"
                                    alt="img-logo" /></a>
                        </div>
                        <nav class="hedaer__navbar">
                            <ul class="header__navbar-sitemenu">
                                <li class="item">
                                    <a href="#" class="nameitem" onclick="submitForm('form_home')">Trang chủ</a>
                                    <form id="form_home" action="${pageContext.request.contextPath}/HeaderServlet"
                                        method="post">
                                        <input type="hidden" value="homeClick" name="action" />
                                    </form>
                                </li>
                                <li class="item">
                                    <a href="#" class="nameitem">Bộ Sưu Tập
                                        <svg class="arrow" xmlns="http://www.w3.org/2000/svg" height="1em"
                                            viewBox="0 0 512 512">
                                            <path
                                                d="M233.4 406.6c12.5 12.5 32.8 12.5 45.3 0l192-192c12.5-12.5 12.5-32.8 0-45.3s-32.8-12.5-45.3 0L256 338.7 86.6 169.4c-12.5-12.5-32.8-12.5-45.3 0s-12.5 32.8 0 45.3l192 192z" />
                                        </svg>
                                    </a>
                                    <ul class="dropmenu">
                                        <c:forEach items="${requestScope.itemCollectionList}" var="itemCollection"
                                            varStatus="status">
                                            <li>
                                                <a
                                                    onclick="submitForm('form_itemCollection${status.index}')">${itemCollection.name}</a>
                                                <form id="form_itemCollection${status.index}"
                                                    action="${pageContext.request.contextPath}/HeaderServlet"
                                                    method="post">
                                                    <input type="hidden" value="${itemCollection.id}"
                                                        name="itemCollectionID" />
                                                    <input type="hidden" value="itemCollectionClick" name="action" />
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
                                    <a href="#" class="nameitem">
                                        sản phẩm
                                        <svg class="arrow" xmlns="http://www.w3.org/2000/svg" height="1em"
                                            viewBox="0 0 512 512">
                                            <path
                                                d="M233.4 406.6c12.5 12.5 32.8 12.5 45.3 0l192-192c12.5-12.5 12.5-32.8 0-45.3s-32.8-12.5-45.3 0L256 338.7 86.6 169.4c-12.5-12.5-32.8-12.5-45.3 0s-12.5 32.8 0 45.3l192 192z" />
                                        </svg>
                                    </a>
                                    <ul class="dropmenu sortmenu">
                                        <c:forEach items="${requestScope.itemTypeList}" var="itemType"
                                            varStatus="status">
                                            <li>
                                                <a
                                                    onclick="submitForm('form_itemType${status.index}')">${itemType.name}</a>
                                                <form id="form_itemType${status.index}"
                                                    action="${pageContext.request.contextPath}/HeaderServlet"
                                                    method="post">
                                                    <input type="hidden" value="${itemType.id}" name="itemTypeID" />
                                                    <input type="hidden" value="itemTypeClick" name="action" />
                                                </form>
                                            </li>
                                        </c:forEach>
                                    </ul>
                                </li>
                            </ul>
                        </nav>
                    </div>


                    <div class="header__right">
                        <div class="header__right--search">
                            <form class="header__search--form search--product"
                                action="${pageContext.request.contextPath}/HeaderServlet" method="post">
                                <div class="form_group">
                                    <input type="text" placeholder="Nhập sản phẩm bạn cần tìm!!" class="inputfeild"
                                        name="searchInput" />
                                    <button type="submit" class="btn productsearch__form--btn">Tìm kiếm</button>
                                </div>
                                <button class="btn__search">
                                    <svg class="icon icon_search" xmlns="http://www.w3.org/2000/svg" height="1em"
                                        viewBox="0 0 512 512">
                                        <path
                                            d="M416 208c0 45.9-14.9 88.3-40 122.7L502.6 457.4c12.5 12.5 12.5 32.8 0 45.3s-32.8 12.5-45.3 0L330.7 376c-34.4 25.2-76.8 40-122.7 40C93.1 416 0 322.9 0 208S93.1 0 208 0S416 93.1 416 208zM208 352a144 144 0 1 0 0-288 144 144 0 1 0 0 288z" />
                                    </svg>
                                </button>
                                <input type="hidden" name="action" value="btnSearchClick">
                            </form>
                        </div>
                        <a href="#" class="header__right--icon btnshow_search">
                            <svg class="icon icon_search" xmlns="http://www.w3.org/2000/svg" height="1em"
                                viewBox="0 0 512 512">
                                <path
                                    d="M416 208c0 45.9-14.9 88.3-40 122.7L502.6 457.4c12.5 12.5 12.5 32.8 0 45.3s-32.8 12.5-45.3 0L330.7 376c-34.4 25.2-76.8 40-122.7 40C93.1 416 0 322.9 0 208S93.1 0 208 0S416 93.1 416 208zM208 352a144 144 0 1 0 0-288 144 144 0 1 0 0 288z" />
                            </svg>
                            <svg class="icon icon_close" xmlns="http://www.w3.org/2000/svg" height="1em"
                                viewBox="0 0 384 512">
                                <path
                                    d="M342.6 150.6c12.5-12.5 12.5-32.8 0-45.3s-32.8-12.5-45.3 0L192 210.7 86.6 105.4c-12.5-12.5-32.8-12.5-45.3 0s-12.5 32.8 0 45.3L146.7 256 41.4 361.4c-12.5 12.5-12.5 32.8 0 45.3s32.8 12.5 45.3 0L192 301.3 297.4 406.6c12.5 12.5 32.8 12.5 45.3 0s12.5-32.8 0-45.3L237.3 256 342.6 150.6z" />
                            </svg>
                        </a>

                        <!--//Nút đăng nhập-->
                        <c:if test="${empty cookie.signInAccountID}">
                            <a href="#" class="header__right--icon btn-user " onclick="handleModal">
                                <svg xmlns="http://www.w3.org/2000/svg" height="1em" viewBox="0 0 512 512">
                                    <path
                                        d="M256 288A144 144 0 1 0 256 0a144 144 0 1 0 0 288zm-94.7 32C72.2 320 0 392.2 0 481.3c0 17 13.8 30.7 30.7 30.7H481.3c17 0 30.7-13.8 30.7-30.7C512 392.2 439.8 320 350.7 320H161.3z" />
                                </svg>
                                Đăng Nhập
                            </a>
                        </c:if>

                        <!--            //đăng nhập hiện ra cái này-->
                        <c:if test="${not empty cookie.signInAccountID}">

                            <c:set value="-1" var="accountIndex" />
                            <c:forEach items="${requestScope.accountList}" var="account" varStatus="status">
                                <c:if test="${account.id eq cookie.signInAccountID.value}">
                                    <c:set var="accountIndex" value="${status.index}" />
                                </c:if>
                            </c:forEach>

                            <div class="header__right--icon   user-login btn-dropdown">
                                <a href="#" class="btn-dropdown"
                                    style="display: flex; align-items: center;gap: 5px;">${requestScope.accountList[accountIndex].customer.name}
                                    <svg xmlns="http://www.w3.org/2000/svg" height="1em" viewBox="0 0 448 512">
                                        <path
                                            d="M0 96C0 78.3 14.3 64 32 64H416c17.7 0 32 14.3 32 32s-14.3 32-32 32H32C14.3 128 0 113.7 0 96zM0 256c0-17.7 14.3-32 32-32H416c17.7 0 32 14.3 32 32s-14.3 32-32 32H32c-17.7 0-32-14.3-32-32zM448 416c0 17.7-14.3 32-32 32H32c-17.7 0-32-14.3-32-32s14.3-32 32-32H416c17.7 0 32 14.3 32 32z" />
                                    </svg>
                                </a>
                                <div class="user-dropdown">

                                    <form id="form_account" action="${pageContext.request.contextPath}/HeaderServlet"
                                        method="post">
                                        <a class="dropmenu--item" onclick="submitForm('form_account')">Tài khoản
                                            <svg xmlns="http://www.w3.org/2000/svg" height="1em" viewBox="0 0 448 512">
                                                <path
                                                    d="M224 256A128 128 0 1 0 224 0a128 128 0 1 0 0 256zm-45.7 48C79.8 304 0 383.8 0 482.3C0 498.7 13.3 512 29.7 512H418.3c16.4 0 29.7-13.3 29.7-29.7C448 383.8 368.2 304 269.7 304H178.3z" />
                                            </svg>
                                        </a>
                                        <input type="hidden" value="accountClick" name="action">
                                    </form>

                                    <form id="form_order" action="${pageContext.request.contextPath}/HeaderServlet"
                                        method="post">
                                        <a class="dropmenu--item" onclick="submitForm('form_order')">Đơn hàng
                                            <svg xmlns="http://www.w3.org/2000/svg" height="1em" viewBox="0 0 384 512">
                                                <path
                                                    d="M192 0c-41.8 0-77.4 26.7-90.5 64H64C28.7 64 0 92.7 0 128V448c0 35.3 28.7 64 64 64H320c35.3 0 64-28.7 64-64V128c0-35.3-28.7-64-64-64H282.5C269.4 26.7 233.8 0 192 0zm0 64a32 32 0 1 1 0 64 32 32 0 1 1 0-64zM72 272a24 24 0 1 1 48 0 24 24 0 1 1 -48 0zm104-16H304c8.8 0 16 7.2 16 16s-7.2 16-16 16H176c-8.8 0-16-7.2-16-16s7.2-16 16-16zM72 368a24 24 0 1 1 48 0 24 24 0 1 1 -48 0zm88 0c0-8.8 7.2-16 16-16H304c8.8 0 16 7.2 16 16s-7.2 16-16 16H176c-8.8 0-16-7.2-16-16z" />
                                            </svg>

                                        </a>
                                        <input type="hidden" value="orderClick" name="action">
                                    </form>

                                    <form id="form_signOut" action="${pageContext.request.contextPath}/HeaderServlet"
                                        method="post">
                                        <a class="dropmenu--item" onclick="submitForm('form_signOut')">Đăng xuất

                                            <svg xmlns="http://www.w3.org/2000/svg" height="1em" viewBox="0 0 512 512">
                                                <path
                                                    d="M502.6 278.6c12.5-12.5 12.5-32.8 0-45.3l-128-128c-12.5-12.5-32.8-12.5-45.3 0s-12.5 32.8 0 45.3L402.7 224 192 224c-17.7 0-32 14.3-32 32s14.3 32 32 32l210.7 0-73.4 73.4c-12.5 12.5-12.5 32.8 0 45.3s32.8 12.5 45.3 0l128-128zM160 96c17.7 0 32-14.3 32-32s-14.3-32-32-32L96 32C43 32 0 75 0 128L0 384c0 53 43 96 96 96l64 0c17.7 0 32-14.3 32-32s-14.3-32-32-32l-64 0c-17.7 0-32-14.3-32-32l0-256c0-17.7 14.3-32 32-32l64 0z" />
                                            </svg>

                                        </a>
                                        <input type="hidden" value="signOutClick" name="action">
                                    </form>
                                </div>
                            </div>

                        </c:if>

                        <c:if test="${not empty cookie.signInAccountID}">

                            <a href="#" onclick="submitForm('form_cart')" class="header__right--icon">
                                <svg xmlns="http://www.w3.org/2000/svg" height="1em" viewBox="0 0 448 512">
                                    <path
                                            d="M160 112c0-35.3 28.7-64 64-64s64 28.7 64 64v48H160V112zm-48 48H48c-26.5 0-48 21.5-48 48V416c0 53 43 96 96 96H352c53 0 96-43 96-96V208c0-26.5-21.5-48-48-48H336V112C336 50.1 285.9 0 224 0S112 50.1 112 112v48zm24 48a24 24 0 1 1 0 48 24 24 0 1 1 0-48zm152 24a24 24 0 1 1 48 0 24 24 0 1 1 -48 0z" />
                                </svg>
                                <form id="form_cart" action="${pageContext.request.contextPath}/HeaderServlet"
                                      method="post">
                                    <input type="hidden" value="cartClick" name="action" />
                                </form>
                                Giỏ Hàng
                            </a>

                        </c:if>

                        <c:if test="${empty cookie.signInAccountID}">

                            <a href="#" onclick="submitForm('form_cart_notLogIn')" class="header__right--icon">
                                <svg xmlns="http://www.w3.org/2000/svg" height="1em" viewBox="0 0 448 512">
                                    <path
                                        d="M160 112c0-35.3 28.7-64 64-64s64 28.7 64 64v48H160V112zm-48 48H48c-26.5 0-48 21.5-48 48V416c0 53 43 96 96 96H352c53 0 96-43 96-96V208c0-26.5-21.5-48-48-48H336V112C336 50.1 285.9 0 224 0S112 50.1 112 112v48zm24 48a24 24 0 1 1 0 48 24 24 0 1 1 0-48zm152 24a24 24 0 1 1 48 0 24 24 0 1 1 -48 0z" />
                                </svg>
                                <form id="form_cart_notLogIn" action="${pageContext.request.contextPath}/HeaderServlet"
                                      method="post">
                                    <input type="hidden" value="cartClick" name="action" />
                                </form>
                                Giỏ Hàng
                            </a>

                        </c:if>

                    </div>
                </div>
            </header>

            <script src="${pageContext.request.contextPath}/Views/User/dest/tuh.js"></script>
            <script src="${pageContext.request.contextPath}/Views/User/dest/nouislider.min.js"></script>