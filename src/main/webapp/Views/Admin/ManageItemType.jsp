<%@page contentType="text/html" pageEncoding="UTF-8" %> <%@ taglib
uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> <%@ taglib
uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <meta
      name="viewport"
      content="width=device-width, initial-scale=1.0 , maximum-scale=1.0, user-scalable=0"
    />
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
    <title>Bán Áo</title>

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
            <c:set value="-1" var="accountIndex" />
            <c:forEach
              items="${requestScope.accountList}"
              var="account"
              varStatus="status"
            >
              <c:if test="${account.id eq cookie.signInAccountID.value}">
                <c:set var="accountIndex" value="${status.index}" />
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
                <input type="hidden" name="action" value="signOutClick" />
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
          <!--Account-->
          <div class="accordion-item">
            <h2 class="accordion-header" id="flush-headingOne">
              <button
                class="accordion-button collapsed text"
                data-type="accountList"
                type="button"
                data-bs-toggle="collapse"
                data-bs-target="#flush-collapseOne"
                aria-expanded="false"
                aria-controls="flush-collapseOne"
              >
                Quản lý tài khoản
              </button>
            </h2>
            <div
              id="flush-collapseOne"
              class="accordion-collapse collapse"
              aria-labelledby="flush-headingOne"
              data-bs-parent="#accordionFlushExample"
            >
              <div class="accordion-body">Đây là nơi quản lý <code>tài khoản</code></div>
            </div>
          </div>

          <!--Item-->
          <div class="accordion-item">
            <h2 class="accordion-header" id="flush-headingTwo">
              <button
                class="accordion-button collapsed text"
                data-type="itemList"
                type="button"
                data-bs-toggle="collapse"
                data-bs-target="#flush-collapseTwo"
                aria-expanded="false"
                aria-controls="flush-collapseTwo"
              >
                Quản lý sản phẩm
              </button>
            </h2>
            <div
              id="flush-collapseTwo"
              class="accordion-collapse collapse"
              aria-labelledby="flush-headingTwo"
              data-bs-parent="#accordionFlushExample"
            >
              <div class="accordion-body">Đây là nơi quản lý <code>sản phẩm</code></div>
            </div>
          </div>

          <!--Statistic-->
          <div class="accordion-item">
            <h2 class="accordion-header" id="flush-headingThree">
              <button
                class="accordion-button collapsed text"
                data-type="statistics"
                type="button"
                data-bs-toggle="collapse"
                data-bs-target="#flush-collapseThree"
                aria-expanded="false"
                aria-controls="flush-collapseThree"
              >
                Quản lý thống kê
              </button>
            </h2>
            <div
              id="flush-collapseThree"
              class="accordion-collapse collapse"
              aria-labelledby="flush-headingThree"
              data-bs-parent="#accordionFlushExample"
            >
              <div class="accordion-body">Đây là nơi quản lý <code>thống kê</code></div>
            </div>
          </div>

          <!--Customer-->
          <div class="accordion-item">
            <h2 class="accordion-header" id="flush-headingFour">
              <button
                class="accordion-button collapsed text"
                data-type="customerList"
                type="button"
                data-bs-toggle="collapse"
                data-bs-target="#flush-collapseFour"
                aria-expanded="false"
                aria-controls="flush-collapseFour"
              >
                Quản lý khách hàng
              </button>
            </h2>
            <div
              id="flush-collapseFour"
              class="accordion-collapse collapse"
              aria-labelledby="flush-headingFour"
              data-bs-parent="#accordionFlushExample"
            >
              <div class="accordion-body">Đây là nơi quản lý <code>khách hàng</code></div>
            </div>
          </div>

          <!--Coupon-->
          <div class="accordion-item">
            <h2 class="accordion-header" id="flush-headingFive">
              <button
                class="accordion-button collapsed text"
                data-type="discountCardList"
                type="button"
                data-bs-toggle="collapse"
                data-bs-target="#flush-collapseFive"
                aria-expanded="false"
                aria-controls="flush-collapseFive"
              >
                Quản lý mã giảm giá
              </button>
            </h2>
            <div
              id="flush-collapseFive"
              class="accordion-collapse collapse"
              aria-labelledby="flush-headingFive"
              data-bs-parent="#accordionFlushExample"
            >
              <div class="accordion-body">
                Đây là nơi quản lý <code>mã giảm giá</code>
              </div>
            </div>
          </div>

          <!--Item collection-->
          <div class="accordion-item">
            <h2 class="accordion-header" id="flush-headingSix">
              <button
                class="accordion-button collapsed text"
                data-type="itemCollectionList"
                type="button"
                data-bs-toggle="collapse"
                data-bs-target="#flush-collapseSix"
                aria-expanded="false"
                aria-controls="flush-collapseSix"
              >
                Quản lý bộ sưu tập
              </button>
            </h2>
            <div
              id="flush-collapseSix"
              class="accordion-collapse collapse"
              aria-labelledby="flush-headingSix"
              data-bs-parent="#accordionFlushExample"
            >
              <div class="accordion-body">Đây là nơi quản lý <code>bộ sưu tập</code></div>
            </div>
          </div>

          <!--Item image-->
          <div class="accordion-item">
            <h2 class="accordion-header" id="flush-headingSeven">
              <button
                class="accordion-button collapsed text"
                data-type="itemImageList"
                type="button"
                data-bs-toggle="collapse"
                data-bs-target="#flush-collapseSeven"
                aria-expanded="false"
                aria-controls="flush-collapseSeven"
              >
                Quản lý hình ảnh
              </button>
            </h2>
            <div
              id="flush-collapseSeven"
              class="accordion-collapse collapse"
              aria-labelledby="flush-headingSeven"
              data-bs-parent="#accordionFlushExample"
            >
              <div class="accordion-body">
                Đây là nơi quản lý <code> hình ảnh của sản phẩm</code>
              </div>
            </div>
          </div>

          <!--Item Material-->
          <div class="accordion-item">
            <h2 class="accordion-header" id="flush-headingEight">
              <button
                class="accordion-button collapsed text"
                data-type="itemMaterialList"
                type="button"
                data-bs-toggle="collapse"
                data-bs-target="#flush-collapseEight"
                aria-expanded="false"
                aria-controls="flush-collapseEight"
              >
                Quản lý nguyên liệu
              </button>
            </h2>
            <div
              id="flush-collapseEight"
              class="accordion-collapse collapse"
              aria-labelledby="flush-headingEight"
              data-bs-parent="#accordionFlushExample"
            >
              <div class="accordion-body">
                Đây là nơi quản lý <code> nguyên liệu của sản phẩm</code>
              </div>
            </div>
          </div>

          <!--Item Order-->
          <div class="accordion-item">
            <h2 class="accordion-header" id="flush-headingNine">
              <button
                class="accordion-button collapsed text"
                data-type="orderList"
                type="button"
                data-bs-toggle="collapse"
                data-bs-target="#flush-collapseNine"
                aria-expanded="false"
                aria-controls="flush-collapseNine"
              >
                Quản lý hóa đơn
              </button>
            </h2>
            <div
              id="flush-collapseNine"
              class="accordion-collapse collapse"
              aria-labelledby="flush-headingNine"
              data-bs-parent="#accordionFlushExample"
            >
              <div class="accordion-body">Đây là nơi quản lý <code> hóa đơn</code></div>
            </div>
          </div>

          <!--Item Order Detail-->
          <div class="accordion-item">
            <h2 class="accordion-header" id="flush-headingTen">
              <button
                class="accordion-button collapsed text"
                data-type="orderDetailList"
                type="button"
                data-bs-toggle="collapse"
                data-bs-target="#flush-collapseTen"
                aria-expanded="false"
                aria-controls="flush-collapseTen"
              >
                Quản lý chi tiết hóa đơn
              </button>
            </h2>
            <div
              id="flush-collapseTen"
              class="accordion-collapse collapse"
              aria-labelledby="flush-headingTen"
              data-bs-parent="#accordionFlushExample"
            >
              <div class="accordion-body">
                Đây là nơi quản lý <code> chi tiết hóa đơn</code>
              </div>
            </div>
          </div>

          <!--Item Type-->
          <div class="accordion-item">
            <h2 class="accordion-header" id="flush-headingEleven">
              <button
                class="accordion-button collapsed text"
                data-type="itemTypeList"
                type="button"
                data-bs-toggle="collapse"
                data-bs-target="#flush-collapseEleven"
                aria-expanded="false"
                aria-controls="flush-collapseEleven"
              >
                Quản lý kiểu sản phẩm
              </button>
            </h2>
            <div
              id="flush-collapseEleven"
              class="accordion-collapse collapse"
              aria-labelledby="flush-headingEleven"
              data-bs-parent="#accordionFlushExample"
            >
              <div class="accordion-body">
                Đây là nơi quản lý <code> loại sản phẩm</code>
              </div>
            </div>
          </div>

          <!--Permission-->
          <div class="accordion-item">
            <h2 class="accordion-header" id="flush-headingTwelve">
              <button
                class="accordion-button collapsed text"
                data-type="permissionList"
                type="button"
                data-bs-toggle="collapse"
                data-bs-target="#flush-collapseTwelve"
                aria-expanded="false"
                aria-controls="flush-collapseTwelve"
              >
                Quản lý phân quyền
              </button>
            </h2>
            <div
              id="flush-collapseTwelve"
              class="accordion-collapse collapse"
              aria-labelledby="flush-headingTwelve"
              data-bs-parent="#accordionFlushExample"
            >
              <div class="accordion-body">
                Đây là nơi quản lý <code> phân quyền</code>
              </div>
            </div>
          </div>

          <!--Sale-->
          <div class="accordion-item">
            <h2 class="accordion-header" id="flush-headingThirdteen">
              <button
                class="accordion-button collapsed text"
                data-type="saleList"
                type="button"
                data-bs-toggle="collapse"
                data-bs-target="#flush-collapseThirdteen"
                aria-expanded="false"
                aria-controls="flush-collapseThirdteen"
              >
                Quản lý giảm giá
              </button>
            </h2>
            <div
              id="flush-collapseThirdteen"
              class="accordion-collapse collapse"
              aria-labelledby="flush-headingThirdteen"
              data-bs-parent="#accordionFlushExample"
            >
              <div class="accordion-body">Đây là nơi quản lý <code> giảm giá</code></div>
            </div>
          </div>

          <!--Stock item-->
          <div class="accordion-item">
            <h2 class="accordion-header" id="flush-headingFourteen">
              <button
                class="accordion-button collapsed text"
                data-type="stockItemList"
                type="button"
                data-bs-toggle="collapse"
                data-bs-target="#flush-collapseFourteen"
                aria-expanded="false"
                aria-controls="flush-collapseFourteen"
              >
                Quản lý màu sắc kích cỡ + số lượng
              </button>
            </h2>
            <div
              id="flush-collapseFourteen"
              class="accordion-collapse collapse"
              aria-labelledby="flush-headingFourteen"
              data-bs-parent="#accordionFlushExample"
            >
              <div class="accordion-body">
                Đây là nơi quản lý
                <code> quản lý màu sắc kích cỡ + số lượng</code>
              </div>
            </div>
          </div>

          <!--Order Status-->
          <div class="accordion-item">
            <h2 class="accordion-header" id="flush-headingFifteen">
              <button
                class="accordion-button collapsed text"
                data-type="orderStatusList"
                type="button"
                data-bs-toggle="collapse"
                data-bs-target="#flush-collapseFifteen"
                aria-expanded="false"
                aria-controls="flush-collapseFifteen"
              >
                Quản lý xác nhận đơn hàng
              </button>
            </h2>
            <div
              id="flush-collapseFifteen"
              class="accordion-collapse collapse"
              aria-labelledby="flush-headingFifteen"
              data-bs-parent="#accordionFlushExample"
            >
              <div class="accordion-body">
                Đây là nơi quản lý
                <code> xác nhận đơn hàng</code>
              </div>
            </div>
          </div>
        </div>
      </aside>

      <!--Center-->
      <div class="center">
        <div class="itemtype-data table-data" data-type="itemTypeList">
          <div class="header-table">
            <!--Add Item Type Button-->
            <div class="AddItemType">
              <button
                class="btnHD btnAdd"
                id="itemType_addTrigger"
                style="margin-bottom: 4px"
              >
                Thêm
              </button>
              <a
                class="btnHD btnExcel"
                style="margin-left: 5px; margin-bottom: 4px"
                onclick="itemTypeToExcel()"
              >
                Xuất Excel
              </a>
            </div>

            <h2 style="font-size: 30px">Quản lý kiểu sản phẩm</h2>
            <form
              action="${pageContext.request.contextPath}/ManageAccountServlet"
              method="post"
            >
              <div class="sorttable">
                <div class="sort-search">
                  <button class="btnHD btngreen btnsearchbox">Tìm kiếm</button>
                  <div class="inputsearch">
                    <select
                      class="selecttype"
                      name="itemTypeSearchType"
                      id="itemTypeSearchType"
                    >
                      <option value="id">ID</option>
                      <option value="name">Name</option>
                    </select>
                    <input
                      type="text"
                      name="itemTypeInputSearch"
                      id="itemTypeInputSearch"
                    />
                    <a class="btnHD btnsearch">Tìm</a>
                  </div>
                </div>
                <div class="sort-box">
                  <label for="">Sắp xếp theo:</label>
                  <select name="itemTypeSortType" id="itemTypeSortType">
                    <option value="az">A-Z</option>
                    <option value="za">Z-A</option>
                  </select>
                </div>
              </div>
              <input type="hidden" name="action" value="searchAndSortItemType" />
            </form>
          </div>

          <!--Table Item Type-->
          <table class="item-table bang">
            <thead>
              <tr>
                <th>ID</th>
                <th>Tên Loại Sản Phẩm</th>
                <th>Action</th>
              </tr>
            </thead>
            <tbody>
              <c:forEach items="${requestScope.itemTypeList}" var="itemType">
                <tr>
                  <td>${itemType.id}</td>
                  <td>${itemType.name}</td>
                  <td>
                    <div class="flex-center grpbtn">
                      <a class="btnHD btnDel" type="submit">Xóa</a>
                      <button
                        class="btnHD btnUpdateItemType"
                        data-itemTypeID="${itemType.id}"
                        data-itemTypeName="${itemType.name}"
                      >
                        Sửa
                      </button>
                    </div>
                  </td>
                </tr>
              </c:forEach>
            </tbody>
          </table>

          <!--Update Item Type Modal-->
          <div id="update-itemType" class="modal-update flex-center">
            <div class="update-modal">
              <span class="close clsUpdateItemType">&times;</span>

              <h2 class="text-center" style="padding: 16px 0">Cập Nhật Loại Sản Phẩm</h2>
              <form
                action="${pageContext.request.contextPath}/ManageItemTypeServlet"
                method="post"
              >
                <div class="form-grp">
                  <input type="hidden" id="update_itemTypeID" name="update_itemTypeID" />
                </div>

                <!--Item Type Name-->
                <div class="form-grp">
                  <label for="update_itemTypeName">Tên Loại Sản Phẩm:</label>
                  <input
                    type="text"
                    maxlength="100"
                    id="update_itemTypeName"
                    name="update_itemTypeName"
                    value=""
                    placeholder="Nhập vào tên loại sản phẩm"
                  />
                </div>

                <div class="flex-center">
                  <a id="updateItemType" class="btn submit">Cập nhật</a>
                </div>
                <input type="hidden" value="updateItemType" name="action" />
              </form>
            </div>
          </div>

          <!--Add Item Type Modal-->
          <div id="add-itemType" class="modal-add flex-center modal__addItemType">
            <div class="add-modal">
              <span class="close clsAddItemType">&times;</span>
              <h2 class="text-center" style="padding: 16px 0">Thêm Loại Sản Phẩm</h2>
              <form
                action="${pageContext.request.contextPath}/ManageItemTypeServlet"
                method="post"
              >
                <!--Item Type Name-->
                <div class="form-grp">
                  <label for="add_itemTypeName">Tên Loại Sản Phẩm:</label>
                  <input
                    type="text"
                    maxlength="100"
                    id="add_itemTypeName"
                    name="add_itemTypeName"
                    value=""
                    placeholder="Nhập vào tên loại sản phẩm"
                  />
                </div>

                <div class="flex-center">
                  <a id="addItemType" type="submit" class="btn submit">Thêm</a>
                </div>
                <input type="hidden" value="addItemType" name="action" />
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
    <script src="${pageContext.request.contextPath}/Views/JS_Temp/account_js.js"></script>

    <script>
      const adminManagerContextPath = "${pageContext.request.contextPath}";
    </script>
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
    <script src="${pageContext.request.contextPath}/Views/User/dest/tuh.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/Views/User/dest/admin.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/xlsx/0.17.1/xlsx.full.min.js"></script>
  </body>
</html>
