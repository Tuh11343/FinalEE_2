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
        <!--Item-->
        <div class="product-data table-data active" data-type="itemList">
            <div class="header-table">
                <div class="AddProduct listbtn">
                    <button
                            class="btnHD btnAdd"
                            id="item_addTrigger"
                            style="margin-bottom: 4px"
                    >
                        Thêm
                    </button>
                    <a
                            class="btnHD btnExcel"
                            style="margin-left: 5px; margin-bottom: 4px"
                            onclick="itemToExcel()"
                    >
                        Xuất Excel
                    </a>
                    <form>
                        <button
                                class="btnHD btnload"
                                style="margin-left: 5px; margin-bottom: 4px" onclick="refreshItem()"
                        >
                            Refresh
                        </button>
                        <input type="hidden" name="action" value="refreshItem"/>
                    </form>
                </div>
                <h2 style="font-size: 30px">Quản lý sản phẩm</h2>
                <form
                        action="${pageContext.request.contextPath}/ManageItemServlet"
                        method="post" onsubmit="return searchAndSortItem()"
                >
                    <div class="sorttable">
                        <div class="sort-search">
                            <a class="btnHD btngreen btnsearchbox">Tìm kiếm</a>
                            <div class="inputsearch">
                                <select class="selecttype" name="itemSearchType" id="itemSearchType">
                                    <option value="id">ID</option>
                                    <option value="itemColor">Màu sản phẩm</option>
                                    <option value="lowerPrice">Giá nhỏ hơn</option>
                                    <option value="higherPrice">Giá lớn hơn</option>
                                </select>
                                <input type="text" name="itemInputSearch" id="itemInputSearch"/>
                                <button class="btnHD btnsearch">Tìm</button>
                            </div>
                        </div>

                        <input type="hidden" name="action" value="searchAndSortItem"/>

                        <div class="sort-box">
                            <label for="itemSortType">Sắp xếp theo:</label>
                            <select id="itemSortType" name="itemSortType">
                                <option value="az">A-Z</option>
                                <option value="za">Z-A</option>
                            </select>
                        </div>
                    </div>
                </form>
            </div>

            <!--Table Item-->
            <table class="item-table bang table">
                <thead>
                <tr>
                    <th scope="col">ID</th>
                    <th scope="col">Tên Sản Phẩm</th>
                    <th scope="col">Loại Sản Phẩm</th>
                    <th scope="col">Bộ Sưu Tập</th>
                    <th scope="col">Vật Liệu</th>
                    <th scope="col">Sản Phẩm Mới</th>
                    <th scope="col">Sản Phẩm Hot</th>
                    <th scope="col">Giá</th>
                    <th scope="col">Năm Sản Xuất</th>
                    <th scope="col">Thông tin sản phẩm</th>
                    <th scope="col">Action</th>
                </tr>
                </thead>
                <tbody id="tableItem">
                <c:forEach items="${requestScope.itemList}" var="item">

                    <c:if test="${not empty item}">

                        <tr>
                            <td>${item.id}</td>
                            <td>${item.name}</td>
                            <td>${item.itemType.id}</td>
                            <td>${item.itemCollection.id}</td>
                            <td>${item.itemMaterial.id}</td>

                            <c:choose>
                                <c:when test="${item.is_new==1}">
                                    <td>Mới</td>
                                </c:when>
                                <c:otherwise>
                                    <td></td>
                                </c:otherwise>
                            </c:choose>

                            <c:choose>
                                <c:when test="${item.is_hot==1}">
                                    <td>Hot</td>
                                </c:when>
                                <c:otherwise>
                                    <td></td>
                                </c:otherwise>
                            </c:choose>

                            <td>${item.price}</td>
                            <td>${item.year_produce}</td>
                            <td>${item.description}</td>
                            <td>
                                <div class="flex-center grpbtn">

                                    <button class="btnHD btnDel" onclick="deleteItem(${item.id})">Xóa</button>

                                    <a
                                            class="btnHD btnUpdateItem"
                                            data-itemID="${item.id}"
                                            data-itemName="${item.name}"
                                            data-itemItemTypeID="${item.itemType.id}"
                                            data-itemItemCollectionID="${item.itemCollection.id}"
                                            data-itemItemMaterialID="${item.itemMaterial.id}"
                                            data-itemIsNew="${item.is_new}"
                                            data-itemIsHot="${item.is_hot}"
                                            data-itemPrice="${item.price}"
                                            data-itemYearProduce="${item.year_produce}"
                                            data-itemDescription="${item.description}"
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

            <!--Update Item-->
            <div id="update-products" class="modal-update flex-center">
                <div class="update-modal">
                    <span class="close clsupdateproduct">&times;</span>

                    <h2 class="text-center" style="padding: 16px 0">Cập nhật sản phẩm</h2>
                    <form>
                        <!--Item ID-->
                        <div class="form-grp">
                            <input type="hidden" id="update_itemID" name="update_itemID"/>
                        </div>

                        <!--Item Name-->
                        <div class="form-grp">
                            <label for="update_itemName">Tên sản phẩm:</label>
                            <input
                                    type="text"
                                    maxlength="100"
                                    id="update_itemName"
                                    name="update_itemName"
                                    value=""
                                    placeholder="Nhập vào tên sản phẩm"
                                    required
                            />
                        </div>

                        <!--Item Type-->
                        <div class="form-grp">
                            <label for="update_itemItemTypeID">Loại sản phẩm:</label>
                            <select id="update_itemItemTypeID" name="update_itemItemTypeID">
                                <c:forEach items="${requestScope.itemTypeList}" var="itemType">
                                    <option value="${itemType.id}">${itemType.name}</option>
                                </c:forEach>
                            </select>
                        </div>

                        <!--Item Collection-->
                        <div class="form-grp">
                            <label for="update_itemItemCollectionID">Bộ sưu tập:</label>
                            <select
                                    id="update_itemItemCollectionID"
                                    name="update_itemItemCollectionID"
                            >
                                <c:forEach
                                        items="${requestScope.itemCollectionList}"
                                        var="itemCollection"
                                >
                                    <option value="${itemCollection.id}">${itemCollection.name}</option>
                                </c:forEach>
                            </select>
                        </div>

                        <!--Item Material-->
                        <div class="form-grp">
                            <label for="update_itemItemMaterialID">Thành phần:</label>
                            <select id="update_itemItemMaterialID" name="update_itemMaterialID">
                                <c:forEach
                                        items="${requestScope.itemMaterialList}"
                                        var="itemMaterial"
                                >
                                    <option value="${itemMaterial.id}">
                                            ${itemMaterial.id} : ${itemMaterial.name}
                                    </option>
                                </c:forEach>
                            </select>
                        </div>

                        <!--Is New-->
                        <div class="form-grp">
                            <label for="update_itemIsNew">Sản phẩm mới:</label>
                            <input
                                    type="checkbox"
                                    id="update_itemIsNew"
                                    name="update_itemIsNew"
                                    value=""
                            />
                        </div>

                        <!--Is Hot-->
                        <div class="form-grp">
                            <label for="update_itemIsHot">Sản phẩm hot:</label>
                            <input
                                    type="checkbox"
                                    id="update_itemIsHot"
                                    name="update_itemIsHot"
                                    value=""
                            />
                        </div>

                        <!--Item Price-->
                        <div class="form-grp">
                            <label for="update_itemPrice">Giá sản phẩm:</label>
                            <input
                                    type="number"
                                    id="update_itemPrice"
                                    name="update_itemPrice"
                                    value=""
                                    placeholder="Nhập vào giá sản phẩm"
                                    required
                            />
                        </div>

                        <!--Item Year Produce-->
                        <div class="form-grp">
                            <label for="update_itemYearProduce">Năm sản xuất:</label>
                            <input
                                    type="number"
                                    id="update_itemYearProduce"
                                    name="update_itemYearProduce"
                                    value=""
                                    placeholder="Nhập vào năm sản xuất"
                                    required
                            />
                        </div>

                        <!--Item Description-->
                        <div class="form-grp">
                            <label for="update_itemDescription">Thông tin sản phẩm:</label>
                            <input
                                    type="text"
                                    maxlength="100"
                                    id="update_itemDescription"
                                    name="update_itemDescription"
                                    value=""
                                    placeholder="Nhập vào thông tin sản phẩm"
                                    required
                            />
                        </div>

                        <div class="flex-center">
                            <a id="updateItem" class="btnHD btnAdd submit" onclick="updateItem()">Cập nhật</a>
                        </div>
                        <input type="hidden" value="updateItem" name="action"/>
                    </form>
                </div>
            </div>

            <!--Add Item-->
            <div id="add-products" class="modal-add flex-center modal__addproduct">
                <div class="add-modal">
                    <span class="close clsaddproduct">&times;</span>
                    <h2 class="text-center" style="padding: 16px 0">THÊM SẢN PHẨM</h2>
                    <form>
                        <!--Item Name-->
                        <div class="form-grp">
                            <label for="add_itemName">Tên sản phẩm:</label>
                            <input
                                    type="text"
                                    maxlength="100"
                                    id="add_itemName"
                                    name="add_itemName"
                                    value=""
                                    placeholder="Nhập vào tên sản phẩm"
                                    required
                            />
                        </div>

                        <!--Item Type-->
                        <div class="form-grp">
                            <label for="add_itemItemTypeID">Loại sản phẩm:</label>
                            <select id="add_itemItemTypeID" name="add_itemTypeID">
                                <c:forEach items="${requestScope.itemTypeList}" var="itemType">
                                    <option value="${itemType.id}">${itemType.name}</option>
                                </c:forEach>
                            </select>
                        </div>

                        <!--Item Collection-->
                        <div class="form-grp">
                            <label for="add_itemItemCollectionID">Bộ sưu tập:</label>
                            <select id="add_itemItemCollectionID" name="add_itemCollectionID">
                                <c:forEach
                                        items="${requestScope.itemCollectionList}"
                                        var="itemCollection"
                                >
                                    <option value="${itemCollection.id}">${itemCollection.name}</option>
                                </c:forEach>
                            </select>
                        </div>

                        <!--Item Material-->
                        <div class="form-grp">
                            <label for="add_itemItemMaterialID">Thành phần:</label>
                            <select id="add_itemItemMaterialID" name="add_itemMaterialID">
                                <c:forEach
                                        items="${requestScope.itemMaterialList}"
                                        var="itemMaterial"
                                >
                                    <option value="${itemMaterial.id}">
                                            ${itemMaterial.id} : ${itemMaterial.name}
                                    </option>
                                </c:forEach>
                            </select>
                        </div>

                        <!--Is New-->
                        <div class="form-grp">
                            <label for="add_itemIsNew">Sản phẩm mới:</label>
                            <input
                                    type="checkbox"
                                    id="add_itemIsNew"
                                    name="add_itemIsNew"
                                    value=""
                            />
                        </div>

                        <!--Is Hot-->
                        <div class="form-grp">
                            <label for="add_itemIsHot">Sản phẩm hot:</label>
                            <input
                                    type="checkbox"
                                    id="add_itemIsHot"
                                    name="add_itemIsHot"
                                    value=""
                            />
                        </div>

                        <!--Item Price-->
                        <div class="form-grp">
                            <label for="add_itemPrice">Giá sản phẩm:</label>
                            <input
                                    type="number"
                                    id="add_itemPrice"
                                    name="add_itemPrice"
                                    value=""
                                    placeholder="Nhập vào giá sản phẩm"
                                    required
                            />
                        </div>

                        <!--Item Year Produce-->
                        <div class="form-grp">
                            <label for="add_itemYearProduce">Năm sản xuất:</label>
                            <input
                                    type="number"
                                    id="add_itemYearProduce"
                                    name="add_itemYearProduce"
                                    value=""
                                    placeholder="Nhập vào năm sản xuất"
                                    required
                            />
                        </div>

                        <!--Item Description-->
                        <div class="form-grp">
                            <label for="add_itemDescription">Thông tin sản phẩm:</label>
                            <input
                                    type="text"
                                    maxlength="100"
                                    id="add_itemDescription"
                                    name="add_itemDescription"
                                    value=""
                                    placeholder="Nhập vào thông tin sản phẩm"
                                    required
                            />
                        </div>

                        <div class="flex-center">
                            <a id="addItem" class="btnHD btnAdd submit" onclick="addItem()">Thêm</a>
                        </div>
                        <input type="hidden" value="addItem" name="action"/>
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
<script src="${pageContext.request.contextPath}/Views/JS_Temp/item_js.js"></script>

<script>
    const adminManagerContextPath = "${pageContext.request.contextPath}";
</script>
<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
<script src="${pageContext.request.contextPath}/Views/User/dest/tuh.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/xlsx/0.17.1/xlsx.full.min.js"></script>
</body>
</html>
