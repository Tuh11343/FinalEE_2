<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib
        uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib
        uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8"/>
    <meta
            name="viewport"
            content="width=device-width, initial-scale=1.0 , maximum-scale=1.0, user-scalable=0"
    />
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <title>Bán Áo</title>

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

    <!--<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css" />-->
    <link
            href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
            rel="stylesheet"
            integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
            crossorigin="anonymous"
    />
    <link
            rel="stylesheet"
            href="${pageContext.request.contextPath}/Views/Admin/vinh_index.css"
    />
    <link
            rel="stylesheet"
            href="${pageContext.request.contextPath}/Views/Admin/vinh_setup.css"
    />
</head>

<body class="homepage">
<header class="header">
    <div class="container-fluid">
        <div class="header__left">
            <div class="logo">
                <img
                        src="${pageContext.request.contextPath}/Views/Admin/asset/img/logo.png"
                />
            </div>
        </div>
        <div class="header__center">
            <h1 class="title --h1">Cửa hàng 3TV</h1>
        </div>
        <div class="header__right">
            <div class="header__right--account">
                <c:set value="-1" var="accountIndex"/>
                <c:forEach
                        items="${requestScope.accountList}"
                        var="account"
                        varStatus="status"
                >
                    <c:if test="${account.id eq cookie.signInAccountID.value}">
                        <c:set var="accountIndex" value="${status.index}"/>
                    </c:if>
                </c:forEach>
                <span class="nameaccount"
                >${requestScope.accountList[accountIndex].name}</span
                >
                <svg xmlns="http://www.w3.org/2000/svg" height="1em" viewBox="0 0 448 512">
                    <!--! Font Awesome Free 6.4.2 by @fontawesome - https://fontawesome.com License - https://fontawesome.com/license (Commercial License) Copyright 2023 Fonticons, Inc. -->
                    <path
                            d="M0 96C0 78.3 14.3 64 32 64H416c17.7 0 32 14.3 32 32s-14.3 32-32 32H32C14.3 128 0 113.7 0 96zM0 256c0-17.7 14.3-32 32-32H416c17.7 0 32 14.3 32 32s-14.3 32-32 32H32c-17.7 0-32-14.3-32-32zM448 416c0 17.7-14.3 32-32 32H32c-17.7 0-32-14.3-32-32s14.3-32 32-32H416c17.7 0 32 14.3 32 32z"
                    />
                </svg>
                <div class="accdopdown">
                    <form
                            id="form-signOut"
                            action="${pageContext.request.contextPath}/HeaderServlet"
                            method="post"
                    >
                        <a href="#" onclick="submitForm('form-signOut')"
                        >Đăng xuất
                            <svg
                                    xmlns="http://www.w3.org/2000/svg"
                                    height="1em"
                                    viewBox="0 0 512 512"
                            >
                                <!--! Font Awesome Free 6.4.2 by @fontawesome - https://fontawesome.com License - https://fontawesome.com/license (Commercial License) Copyright 2023 Fonticons, Inc. -->
                                <path
                                        d="M377.9 105.9L500.7 228.7c7.2 7.2 11.3 17.1 11.3 27.3s-4.1 20.1-11.3 27.3L377.9 406.1c-6.4 6.4-15 9.9-24 9.9c-18.7 0-33.9-15.2-33.9-33.9l0-62.1-128 0c-17.7 0-32-14.3-32-32l0-64c0-17.7 14.3-32 32-32l128 0 0-62.1c0-18.7 15.2-33.9 33.9-33.9c9 0 17.6 3.6 24 9.9zM160 96L96 96c-17.7 0-32 14.3-32 32l0 256c0 17.7 14.3 32 32 32l64 0c17.7 0 32 14.3 32 32s-14.3 32-32 32l-64 0c-53 0-96-43-96-96L0 128C0 75 43 32 96 32l64 0c17.7 0 32 14.3 32 32s-14.3 32-32 32z"
                                />
                            </svg>
                        </a>
                        <input type="hidden" name="action" value="signOutClick"/>
                    </form>
                </div>
            </div>
        </div>
    </div>
</header>
<!--<div class="container" >-->
<main class="main">
    <aside class="left">
        <div class="accordion" id="accordionFlushExample">

            <c:if test="${requestScope.signInAccount.permission.level eq 1}">
                <a href="${pageContext.request.contextPath}/ManageAccountServlet">Quản lý tài khoản</a>
                <a href="${pageContext.request.contextPath}/ManageStatisticServlet">Quản lý thống kê</a>
                <a href="${pageContext.request.contextPath}/ManagePermissionServlet">Quản lý quyền tài khoản</a>
                <a href="${pageContext.request.contextPath}/ManageDiscountCardServlet">Quản lý mã giảm giá khách
                    hàng</a>
                <a href="${pageContext.request.contextPath}/ManageOrderDetailServlet">Quản lý chi tiết hóa đơn</a>
                <a href="${pageContext.request.contextPath}/ManageSaleServlet">Quản lý khuyến mãi sản phẩm</a>
                <a href="${pageContext.request.contextPath}/ManageOrderStatusServlet">Quản lý tình trạng đơn hàng</a>
                <a href="${pageContext.request.contextPath}/ManageCartServlet">Quản lý giỏ hàng</a>

            </c:if>


            <a href="${pageContext.request.contextPath}/ManageItemServlet">Quản lý sản phẩm</a>

            <a href="${pageContext.request.contextPath}/ManageCustomerServlet">Quản lý khách hàng</a>

            <a href="${pageContext.request.contextPath}/ManageItemCollectionServlet">Quản lý bộ sưu tập</a>

            <a href="${pageContext.request.contextPath}/ManageItemImageServlet">Quản lý hình ảnh sản phẩm</a>

            <a href="${pageContext.request.contextPath}/ManageItemMaterialServlet">Quản lý vật liệu sản phẩm</a>

            <a href="${pageContext.request.contextPath}/ManageOrderServlet">Quản lý hóa đơn</a>

            <a href="${pageContext.request.contextPath}/ManageItemTypeServlet">Quản lý loại sản phẩm</a>

            <a href="${pageContext.request.contextPath}/ManageStockItemServlet">Quản lý thông tin sản phẩm</a>

        </div>
    </aside>

    <!--Center-->
    <div class="center">
        <!--Stock Item-->
        <div class="stockitem-data table-data active" data-type="stockItemList">
            <div class="header-table">
                <!--Add StockItem Button-->
                <div class="AddStockItem listbtn">
                    <button
                            class="btnHD btnAdd"
                            id="stockItem_addTrigger"
                            style="margin-bottom: 4px"
                    >
                        Thêm
                    </button>
                    <a
                            class="btnHD btnExcel"
                            style="margin-left: 5px; margin-bottom: 4px"
                            onclick="stockItemToExcel()"
                    >
                        Xuất Excel
                    </a>
                    <form>
                        <button
                                class="btnHD btnload"
                                style="margin-left: 5px; margin-bottom: 4px" onclick="refreshStockItem()"
                        >
                            Refresh
                        </button>
                        <input type="hidden" name="action" value="refreshStockItem"/>
                    </form>
                </div>
                <h2 style="font-size: 30px">Quản lý màu sắc kích cỡ số lượng</h2>
                <form
                        action="${pageContext.request.contextPath}/ManageStockItemServlet"
                        method="post" onsubmit="return searchAndSortStockItem()"
                >
                    <div class="sorttable">
                        <div class="sort-search">
                            <a class="btnHD btngreen btnsearchbox">Tìm kiếm</a>
                            <div class="inputsearch">
                                <select
                                        class="selecttype"
                                        name="stockItemSearchType"
                                        id="stockItemSearchType"
                                >
                                    <option value="id">ID</option>
                                    <option value="itemID">ID sản phẩm</option>
                                </select>
                                <input
                                        type="text"
                                        name="stockItemInputSearch"
                                        id="stockItemInputSearch"
                                />
                                <button class="btnHD btnsearch">Tìm</button>
                            </div>
                        </div>
                        <div class="sort-box">
                            <label for="stockItemSortType">Sắp xếp theo:</label>
                            <select name="stockItemSortType" id="stockItemSortType">
                                <option value="az">A-Z</option>
                                <option value="za">Z-A</option>
                            </select>
                        </div>
                    </div>
                    <input type="hidden" name="action" value="searchAndSortStockItem"/>
                </form>
            </div>

            <!--Table StockItem-->
            <table class="item-table bang table">
                <thead>
                <tr>
                    <th scope="col">ID</th>
                    <th scope="col">ID Sản Phẩm</th>
                    <th scope="col">Màu</th>
                    <th scope="col">Size</th>
                    <th scope="col">Số Lượng</th>
                    <th scope="col">Action</th>
                </tr>
                </thead>
                <tbody id="tableStockItem">
                <c:forEach items="${requestScope.stockItemList}" var="stockItem">
                    <c:if test="${not empty stockItem}">

                        <tr>
                            <td>${stockItem.id}</td>
                            <td>${stockItem.item.id}</td>
                            <td>${stockItem.color}</td>
                            <td>${stockItem.size}</td>
                            <td>${stockItem.amount}</td>
                            <td>
                                <div class="flex-center grpbtn">

                                    <button class="btnHD btnDel" onclick="deleteStockItem(${stockItem.id})">Xóa</button>

                                    <a
                                            class="btnHD btnUpdateStockItem"
                                            data-stockItemID="${stockItem.id}"
                                            data-stockItemItemID="${stockItem.item.id}"
                                            data-stockItemColor="${stockItem.color}"
                                            data-stockItemSize="${stockItem.size}"
                                            data-stockItemAmount="${stockItem.amount}"
                                    >
                                        Sửa
                                    </a>
                                </div>
                            </td>
                        </tr>

                    </c:if>
                </c:forEach>
                </tbody>
            </table>

            <!--Update StockItem Modal-->
            <div id="update-stockItem" class="modal-update flex-center">
                <div class="update-modal">
                    <span class="close clsUpdateStockItem">&times;</span>

                    <h2 class="text-center" style="padding: 16px 0">
                        Cập Nhật Số Lượng Hàng Hóa
                    </h2>
                    <form>

                        <!--StockItem Item ID-->
                        <div class="form-grp">
                            <input
                                    type="hidden"
                                    id="update_stockItemID"
                                    name="update_stockItemID"
                            />
                        </div>

                        <div class="form-grp">
                            <label for="update_stockItemItemID">Sản Phẩm:</label>
                            <select id="update_stockItemItemID" name="update_stockItemItemID">
                                <c:forEach items="${requestScope.itemList}" var="item">
                                    <option value="${item.id}">${item.id} : ${item.name}</option>
                                </c:forEach>
                            </select>
                        </div>

                        <!--StockItem Color-->
                        <div class="form-grp">
                            <label for="update_stockItemColor">Màu:</label>
                            <input
                                    type="text"
                                    maxlength="100"
                                    id="update_stockItemColor"
                                    name="update_stockItemColor"
                                    value=""
                                    placeholder="Nhập vào màu sản phẩm"
                            />
                        </div>

                        <!--StockItem Size-->
                        <div class="form-grp">
                            <label for="update_stockItemSize">Size:</label>
                            <input
                                    type="text"
                                    maxlength="100"
                                    id="update_stockItemSize"
                                    name="update_stockItemSize"
                                    value=""
                                    placeholder="Nhập vào kích cỡ sản phẩm"
                            />
                        </div>

                        <!--StockItem Amount-->
                        <div class="form-grp">
                            <label for="update_stockItemAmount">Số Lượng:</label>
                            <input
                                    type="number"
                                    maxlength="100"
                                    id="update_stockItemAmount"
                                    name="update_stockItemAmount"
                                    value=""
                                    placeholder="Nhập vào số lượng sản phẩm"/>
                        </div>

                        <div class="flex-center">
                            <a id="updateStockItem" class="btn submit" onclick="updateStockItem()">Cập nhật</a>
                        </div>

                        <input type="hidden" name="action" value="updateStockItem"/>
                    </form>
                </div>
            </div>

            <!--Add StockItem Modal-->
            <div id="add-stockItem" class="modal-add flex-center modal__addStockItem">
                <div class="add-modal">
                    <span class="close clsAddStockItem">&times;</span>
                    <h2 class="text-center" style="padding: 16px 0">Thêm Số Lượng Hàng Hóa</h2>
                    <form>
                        <!--StockItem Item ID-->
                        <div class="form-grp">
                            <label for="add_stockItemItemID">Sản Phẩm:</label>
                            <select id="add_stockItemItemID" name="add_stockItemItemID">
                                <c:forEach items="${requestScope.itemList}" var="item">
                                    <option value="${item.id}">${item.id} : ${item.name}</option>
                                </c:forEach>
                            </select>
                        </div>

                        <!--StockItem Color-->
                        <div class="form-grp">
                            <label for="add_stockItemColor">Màu:</label>
                            <input
                                    type="text"
                                    maxlength="100"
                                    id="add_stockItemColor"
                                    name="add_stockItemColor"
                                    value=""
                                    placeholder="Nhập vào màu sản phẩm"
                            />
                        </div>

                        <!--StockItem Size-->
                        <div class="form-grp">
                            <label for="add_stockItemSize">Size:</label>
                            <input
                                    type="text"
                                    maxlength="100"
                                    id="add_stockItemSize"
                                    name="add_stockItemSize"
                                    value=""
                                    placeholder="Nhập vào kích cỡ sản phẩm"
                            />
                        </div>

                        <!--StockItem Amount-->
                        <div class="form-grp">
                            <label for="add_stockItemAmount">Số Lượng:</label>
                            <input
                                    type="number"
                                    maxlength="100"
                                    id="add_stockItemAmount"
                                    name="add_stockItemAmount"
                                    value=""
                                    placeholder="Nhập vào số lượng sản phẩm"
                            />
                        </div>

                        <div class="flex-center">
                            <a id="addStockItem" class="btn submit" onclick="addStockItem()">Thêm</a>
                        </div>
                        <input type="hidden" name="action" value="addStockItem"/>
                    </form>
                </div>
            </div>
        </div>
    </div>
</main>

<footer class="footer">
    <div class="container"></div>
</footer>

<!-- Javascript -->
<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
<script
        src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
        crossorigin="anonymous"
></script>
<script src="${pageContext.request.contextPath}/Views/JS_Temp/stockitem_js.js"></script>

<script>
    const adminManagerContextPath = "${pageContext.request.contextPath}";
</script>
<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
<script src="${pageContext.request.contextPath}/Views/User/dest/tuh.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/xlsx/0.17.1/xlsx.full.min.js"></script>
</body>
</html>
