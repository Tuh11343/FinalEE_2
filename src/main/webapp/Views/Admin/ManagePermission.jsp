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
        <!--Permission-->
        <div class="permission-data table-data active" data-type="permissionList">
            <div class="header-table">
                <!--Add Permission-->
                <div class="AddPermission listbtn">
                    <button
                            class="btnHD btnAdd"
                            id="permission_addTrigger"
                            style="margin-bottom: 4px"
                    >
                        Thêm
                    </button>
                    <button
                            class="btnHD btnExcel"
                            style="margin-left: 5px; margin-bottom: 4px"
                            onclick="permissionToExcel()"
                    >
                        Xuất Excel
                    </button>
                    <form>
                        <button
                                class="btnHD btnload"
                                style="margin-left: 5px; margin-bottom: 4px"
                                onclick="refreshPermission()"
                        >
                            Refresh
                        </button>
                        <input type="hidden" name="action" value="refreshPermission"/>
                    </form>
                </div>
                <h2 style="font-size: 30px">Quản lý phân quyền</h2>
                <form
                        action="${pageContext.request.contextPath}/ManagePermissionServlet"
                        method="post" onsubmit="return searchAndSortPermission()"
                >
                    <div class="sorttable">
                        <div class="sort-search">
                            <a class="btnHD btngreen btnsearchbox">Tìm kiếm</a>
                            <div class="inputsearch">
                                <select
                                        class="selecttype"
                                        name="permissionSearchType"
                                        id="permissionSearchType"
                                >
                                    <option value="id">ID</option>
                                    <option value="name">Name</option>
                                </select>
                                <input
                                        type="text"
                                        id="permissionInputSearch"
                                        name="permissionInputSearch"
                                />
                                <button class="btnHD btnsearch">Tìm</button>
                            </div>
                        </div>
                        <div class="sort-box">
                            <label for="permissionSortType">Sắp xếp theo:</label>
                            <select name="permissionSortType" id="permissionSortType">
                                <option value="az">A-Z</option>
                                <option value="za">Z-A</option>
                            </select>
                        </div>
                    </div>
                    <input type="hidden" name="action" value="searchAndSortPermission"/>
                </form>
            </div>

            <!--Table Permission-->
            <table class="item-table bang">
                <thead>
                <tr>
                    <th>ID</th>
                    <th>Tên Quyền</th>
                    <th>Mức độ</th>
                    <th>Action</th>
                </tr>
                </thead>
                <tbody id="tablePermission">
                <c:forEach items="${requestScope.permissionList}" var="permission">
                    <c:if test="${not empty permission}">

                        <tr>
                            <td>${permission.id}</td>
                            <td>${permission.name}</td>
                            <td>${permission.level}</td>
                            <td>
                                <div class="flex-center grpbtn">

                                    <button class="btnHD btnDel" type="submit"
                                            onclick="deletePermission(${permission.id})">Xóa
                                    </button>

                                    <a
                                            class="btnHD btnUpdatePermission"
                                            data-permissionID="${permission.id}"
                                            data-permissionName="${permission.name}"
                                            data-permissionLevel="${permission.level}"
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

            <!--Update Permission Modal-->
            <div id="update-permission" class="modal-update flex-center">
                <div class="update-modal">
                    <span class="close clsUpdatePermission">&times;</span>

                    <h2 class="text-center" style="padding: 16px 0">Cập Nhật Quyền</h2>
                    <form>
                        <div class="form-grp">
                            <input
                                    type="hidden"
                                    id="update_permissionID"
                                    name="update_permissionID"
                            />
                        </div>
                        <!--Permission Name-->
                        <div class="form-grp">
                            <label for="update_permissionName">Tên Quyền:</label>
                            <input
                                    type="text"
                                    maxlength="100"
                                    id="update_permissionName"
                                    name="update_permissionName"
                                    value=""
                                    placeholder="Nhập vào tên quyền"
                            />
                        </div>

                        <!--Permission Level-->
                        <div class="form-grp">
                            <label for="update_permissionName">Mức Độ Truy Cập:</label>
                            <input
                                    type="number"
                                    maxlength="100"
                                    id="update_permissionLevel"
                                    name="update_permissionLevel"
                                    value=""
                                    placeholder="Nhập vào mức độ truy cập"
                            />
                        </div>

                        <div class="flex-center">
                            <a id="updatePermission" class="btnHD btnAdd submit" onclick="updatePermission()">Cập
                                nhật</a>
                        </div>
                        <input type="hidden" value="updatePermission" name="action"/>
                    </form>
                </div>
            </div>

            <!--Add Permission Modal-->
            <div id="add-permission" class="modal-add flex-center modal__addPermission">
                <div class="add-modal">
                    <span class="close clsAddPermission">&times;</span>
                    <h2 class="text-center" style="padding: 16px 0">Thêm Quyền</h2>
                    <form>
                        <!--Permission Name-->
                        <div class="form-grp">
                            <label for="add_permissionName">Tên Quyền:</label>
                            <input
                                    type="text"
                                    maxlength="100"
                                    id="add_permissionName"
                                    name="add_permissionName"
                                    value=""
                                    placeholder="Nhập vào tên quyền"
                            />
                        </div>

                        <!--Permission Level-->
                        <div class="form-grp">
                            <label for="add_permissionLevel">Mức Độ Truy Cập:</label>
                            <input
                                    type="number"
                                    maxlength="100"
                                    id="add_permissionLevel"
                                    name="add_permissionLevel"
                                    value=""
                                    placeholder="Nhập vào mức độ truy cập"
                            />
                        </div>

                        <div class="flex-center">
                            <a id="addPermission" class="btnHD btnAdd submit" onclick="addPermission()"> Thêm </a>
                        </div>
                        <input type="hidden" value="addPermission" name="action"/>
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
<script src="${pageContext.request.contextPath}/Views/JS_Temp/permission_js.js"></script>

<script>
    const adminManagerContextPath = "${pageContext.request.contextPath}";
</script>
<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
<script src="${pageContext.request.contextPath}/Views/User/dest/tuh.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/xlsx/0.17.1/xlsx.full.min.js"></script>
</body>
</html>
