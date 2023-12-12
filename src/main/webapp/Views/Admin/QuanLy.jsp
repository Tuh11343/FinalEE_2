<%@page contentType="text/html" pageEncoding="UTF-8" %> <%@ taglib
uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
                <code> các kiểu màu sắc , kích cỡ,số lượng sản phẩm</code>
              </div>
            </div>
          </div>
        </div>
      </aside>

      <!--Center-->
      <div class="center">
        <!-- Thống kê -->
        <div class="product-data table-data active" data-type="statistics">
          <div class="header-table">
            <div class="AddProduct">
              <button
                class="btnHD btnAdd"
                id="item_addTrigger"
                style="margin-bottom: 4px"
              >
                Thêm
              </button>
            </div>
            <h2 style="font-size: 30px">Quản lý sản phẩm</h2>
            <div class="sorttable">
              <div class="sort-search">
                <button class="btnHD btngreen btnsearchbox">Tìm kiếm</button>
                <div class="inputsearch">
                  <input type="text" value="" />
                  <button class="btnHD btnsearch">Tìm</button>
                </div>
              </div>
              <div class="sort-box">
                <label for="">Sắp xếp theo:</label>
                <select name="" id="">
                  <option value="" selected>Z-A</option>
                  <option value="">A-Z</option>
                  <option value="">Giá tăng</option>
                  <option value="">Giá giảm</option>
                  <option value="">Mã sản phẩm</option>
                </select>
              </div>
            </div>
          </div>
          <div class="form-grp">
            <label for="type-select">Loại sản phẩm:</label>
            <select id="type-select" name="typestatistics" onchange="selectTedtable()">
              <option value="">Chọn hình thức</option>
              <option value="productsSoldByMonth">5 Sản phẩm bán chạy nhất tháng</option>
              <option value="productsSoldByYear">5 Sản phẩm bán chạy nhất năm</option>
              <option value="totalProductsSoldByMonth">
                Các sản phẩm bán chạy nhất tháng
              </option>
              <option value="totalProductsSoldByYear">
                Các sản phẩm bán chạy nhất năm
              </option>
              <option value="recentFiveMonthRevenue">Doanh thu 5 tháng gần nhất</option>
              <option value="monthRevenue">Doanh thu theo tháng</option>
            </select>
          </div>

          <div id="table-container">
            <%--5 sản phẩm bán chạy nhất tháng--%>
            <div
              id="productsSoldByMonthTable"
              style="display: none"
              class="typetable"
              data-type="productsSoldByMonth"
            >
              <!-- Table for productsSoldByMonth data -->
              <label for="soldByMonth">Chọn tháng:</label>
              <input
                id="soldByMonth"
                type="date"
                value=""
                onchange="productsSoldByMonthAjaxRequest('soldByMonth','productsSoldByMonth','productsTable')"
              />

              <table id="productsTable" border="1">
                <tr>
                  <th>ID Sản Phẩm</th>
                  <th>Số lượng sản phẩm</th>
                </tr>

                <!-- Add rows dynamically based on data -->
              </table>
            </div>

            <%--5 sản phẩm bán chạy nhất năm--%>
            <div class="typetable" style="display: none" data-type="productsSoldByYear">
              <!-- Table for aothun data -->

              <label for="soldByYear">Chọn tháng:</label>
              <input
                id="soldByYear"
                type="date"
                onchange="productsSoldByMonthAjaxRequest('soldByYear','productsSoldByYear','productsSoldByYearTable')"
              />

              <table id="productsSoldByYearTable" border="1">
                <tr>
                  <th>ID Sản Phẩm</th>
                  <th>Số lượng sản phẩm</th>
                </tr>
                <!-- Add rows dynamically based on data -->
              </table>
            </div>

            <%--Tổng sản phẩm bán ra theo tháng--%>
            <div
              class="typetable"
              style="display: none"
              data-type="totalProductsSoldByMonth"
            >
              <label for="thirdDatePicker">Chọn tháng:</label>
              <input
                id="thirdDatePicker"
                type="date"
                onchange="productsSoldByMonthAjaxRequest('threeDatePicker','totalProductsSoldByMonth','totalProductsSoldByMonthTable')"
              />

              <table id="totalProductsSoldByMonthTable" border="1">
                <tr>
                  <th>ID Sản Phẩm</th>
                  <th>Số lượng sản phẩm</th>
                </tr>
              </table>
            </div>

            <%--Tổng sản phẩm bán ra theo năm--%>
            <div
              class="typetable"
              style="display: none"
              data-type="totalProductsSoldByYear"
            >
              <label for="fourthDatePicker">Chọn tháng:</label>
              <input
                id="fourthDatePicker"
                type="date"
                onchange="productsSoldByMonthAjaxRequest('fourthDatePicker','totalProductsSoldByYear','totalProductsSoldByYearTable')"
              />

              <table id="totalProductsSoldByYearTable" border="1">
                <tr>
                  <th>ID Sản Phẩm</th>
                  <th>Số lượng sản phẩm</th>
                </tr>
              </table>
            </div>

            <%--Tổng doanh thu 5 tháng gần nhất--%>
            <div
              class="typetable"
              style="display: none"
              data-type="recentFiveMonthRevenue"
            >
              <label for="fifthDatePicker">Chọn tháng:</label>
              <input
                id="fifthDatePicker"
                type="date"
                onchange="productsSoldByMonthAjaxRequest('fifthDatePicker','recentFiveMonthRevenue','recentFiveMonthRevenueTable')"
              />

              <table id="recentFiveMonthRevenueTable" border="1">
                <tr>
                  <th>Tháng</th>
                  <th>Doanh thu</th>
                </tr>
              </table>
            </div>

            <%--Doanh thu theo tháng--%>
            <div class="typetable" style="display: none" data-type="monthRevenue">
              <label for="sixthDatePicker">Chọn tháng:</label>
              <input
                id="sixthDatePicker"
                type="date"
                onchange="productsSoldByMonthAjaxRequest('sixthDatePicker','monthRevenue','monthRevenueTable')"
              />

              <table id="monthRevenueTable" border="1">
                <tr>
                  <th>Tháng</th>
                  <th>Doanh thu</th>
                </tr>
              </table>
            </div>
          </div>

          <div id="chart-container" class="hidden">
            <!-- Chart will be displayed here -->
            <canvas id="myChart"></canvas>
          </div>

          <%--
          <table id="statsTable" class="product-table bang">
            <tr>
              <th>Sản phẩm</th>
              <th>Số lượng đã bán</th>
            </tr>
          </table>
          <canvas id="productChart"></canvas>--%>
        </div>

        <!--Account-->
        <div class="account-data table-data" data-type="accountList">
          <div class="header-table">
            <div class="AddUser">
              <button
                class="btnHD btnAdd"
                id="account_addTrigger"
                style="margin-bottom: 4px"
              >
                Thêm
              </button>
            </div>
            <h2 style="font-size: 30px">Quản lý sản phẩm</h2>
            <div class="sorttable">
              <div class="sort-search">
                <button class="btnHD btngreen btnsearchbox">Tìm kiếm</button>
                <div class="inputsearch">
                  <input type="text" value="" />
                  <button class="btnHD btnsearch">Tìm</button>
                </div>
              </div>
              <div class="sort-box">
                <label for="">Sắp xếp theo:</label>
                <select name="" id="">
                  <option value="" selected>Z-A</option>
                  <option value="">A-Z</option>
                  <option value="">Giá tăng</option>
                  <option value="">Giá giảm</option>
                  <option value="">Mã sản phẩm</option>
                </select>
              </div>
            </div>
          </div>

          <!--Add Account Button-->

          <!--Account Table-->
          <table class="account-table bang">
            <thead>
              <tr>
                <th>ID</th>
                <th>Permission ID</th>
                <th>Customer ID</th>
                <th>Name</th>
                <th>Password</th>
                <th>Action</th>
              </tr>
            </thead>
            <tbody>
              <c:forEach items="${requestScope.accountList}" var="account">
                <tr>
                  <td>${account.id}</td>
                  <td>${account.permission.id}</td>
                  <td>${account.customer.id}</td>
                  <td>${account.name}</td>
                  <td>${account.password}</td>
                  <td>
                    <div class="flex-center grpbtn">
                      <form
                        action="${pageContext.request.contextPath}/AdminManagerServlet"
                        method="post"
                        onsubmit="handleDelete()"
                      >
                        <button class="btnHD btnDel" type="submit">Xóa</button>
                        <input type="hidden" name="accountID" value="${account.id}" />
                        <input type="hidden" name="action" value="account_btnDelete" />
                      </form>
                      <button
                        class="btnHD btnUpdateUser"
                        id="account_updateTrigger"
                        data-customerID="${account.customer.id}"
                        data-permissionID="${account.permission.id}"
                        data-accountName="${account.name}"
                        data-accountPassword="${account.password}"
                      >
                        Sửa
                      </button>
                      <input type="hidden" name="accountID" value="${account.id}" />
                    </div>
                  </td>
                </tr>
              </c:forEach>
            </tbody>
          </table>

          <!--Update Account-->
          <div id="update-user" class="updatemodal flex-center">
            <div class="update-modal">
              <form
                class="form__update"
                action="${pageContext.request.contextPath}/AdminManagerServlet"
                method="post"
              >
                <span class="close">&times;</span>
                <h2 class="text-center" style="padding: 16px 0">CẬP NHẬT NGƯỜI DÙNG</h2>

                <!--Account Name-->
                <div class="form-grp">
                  <label for="update_accountNameID">Tên tài khoản:</label>
                  <input
                    type="text"
                    maxlength="100"
                    id="update_accountNameID"
                    name="update_accountName"
                    value=""
                    placeholder="Nhập vào tài khoản"
                  />
                </div>

                <!--Account Password-->
                <div class="form-grp">
                  <label for="update_accountPasswordID">Mật khẩu:</label>
                  <input
                    type="password"
                    maxlength="100"
                    id="update_accountPasswordID"
                    name="update_accountPassword"
                    value=""
                    placeholder="Nhập vào mật khẩu"
                  />
                </div>

                <!--Account Permission-->
                <div class="form-grp">
                  <label for="update_label_permissionID">Permission:</label>
                  <select
                    id="update_label_permissionID"
                    name="update_accountPermissionID"
                  >
                    <c:forEach items="${requestScope.permissionList}" var="permission">
                      <option value="${permission.id}">${permission.name}</option>
                    </c:forEach>
                  </select>
                </div>

                <!--Account Customer-->
                <div class="form-grp">
                  <label for="update_label_customerID">Customer:</label>
                  <select id="update_label_customerID" name="update_accountCustomerID">
                    <c:forEach items="${requestScope.customerList}" var="customer">
                      <option value="${customer.id}">
                        ${customer.id} : ${customer.name}
                      </option>
                    </c:forEach>
                  </select>
                </div>

                <!--Update Button-->
                <div class="flex-center">
                  <input type="hidden" name="action" value="account_btnUpdate" />
                  <button type="submit">Cập nhật</button>
                </div>
              </form>
            </div>
          </div>

          <!--Add Account-->
          <div id="modal-add-account" class="addmodal flex-center">
            <div class="add-modal">
              <form
                class="form__add"
                action="${pageContext.request.contextPath}/AdminManagerServlet"
                method="post"
              >
                <span class="close clsadduser">&times;</span>
                <h2 class="text-center" style="padding: 16px 0">THÊM NGƯỜI DÙNG</h2>

                <!--Account Name-->
                <div class="form-grp">
                  <label for="accountNameID">Tên tài khoản:</label>
                  <input
                    type="text"
                    maxlength="100"
                    id="accountNameID"
                    name="add_accountName"
                    value=""
                    placeholder="Nhập vào tài khoản"
                  />

                  <!--Account Password-->
                </div>
                <div class="form-grp">
                  <label for="accountPasswordID">Mật khẩu:</label>
                  <input
                    type="password"
                    maxlength="100"
                    id="accountPasswordID"
                    name="add_accountPassword"
                    value=""
                    placeholder="Nhập vào mật khẩu"
                  />
                </div>

                <!--Account Permission-->
                <div class="form-grp">
                  <label for="label_permissionID">Permission:</label>
                  <select id="label_permissionID" name="add_accountPermissionID">
                    <c:forEach items="${requestScope.permissionList}" var="permission">
                      <option value="${permission.id}">${permission.name}</option>
                    </c:forEach>
                  </select>
                </div>

                <!--Account Customer-->
                <div class="form-grp">
                  <label for="label_customerID">Customer:</label>
                  <select id="label_customerID" name="add_accountCustomerID">
                    <c:forEach items="${requestScope.customerList}" var="customer">
                      <option value="${customer.id}">
                        ${customer.id} : ${customer.name}
                      </option>
                    </c:forEach>
                  </select>
                </div>

                <!--Add Button-->
                <div class="flex-center">
                  <button type="submit">Thêm</button>
                  <input type="hidden" name="action" value="account_btnAdd" />
                </div>
              </form>
            </div>
          </div>
        </div>

        <!--Item-->
        <div class="product-data table-data" data-type="itemList">
          <div class="header-table">
            <div class="AddProduct">
              <button
                class="btnHD btnAdd"
                id="item_addTrigger"
                style="margin-bottom: 4px"
              >
                Thêm
              </button>
            </div>
            <h2 style="font-size: 30px">Quản lý sản phẩm</h2>
            <div class="sorttable">
              <div class="sort-search">
                <button class="btnHD btngreen btnsearchbox">Tìm kiếm</button>
                <div class="inputsearch">
                  <input type="text" value="" />
                  <button class="btnHD btnsearch">Tìm</button>
                </div>
              </div>
              <div class="sort-box">
                <label for="">Sắp xếp theo:</label>
                <select name="" id="">
                  <option value="" selected>Z-A</option>
                  <option value="">A-Z</option>
                  <option value="">Giá tăng</option>
                  <option value="">Giá giảm</option>
                  <option value="">Mã sản phẩm</option>
                </select>
              </div>
            </div>
          </div>

          <!--Add Item Button-->

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
                <th scope="col">Action</th>
              </tr>
            </thead>
            <tbody>
              <c:forEach items="${requestScope.itemList}" var="item">
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
                  <td>
                    <div class="flex-center grpbtn">
                      <form
                        action="${pageContext.request.contextPath}/AdminManagerServlet"
                        method="post"
                        onsubmit="handleDelete()"
                      >
                        <button class="btnHD btnDel" type="submit">Xóa</button>
                        <input type="hidden" name="itemID" value="${item.id}" />
                        <input type="hidden" name="action" value="item_btnDelete" />
                      </form>
                      <button
                        class="btnHD btnUpdateItem"
                        data-itemID="${item.id}"
                        data-itemName="${item.name}"
                        data-itemTypeID="${item.itemType.id}"
                        data-itemCollectionID="${item.itemCollection.id}"
                        data-itemMaterialID="${item.itemMaterial.id}"
                        data-itemIsNew="${item.is_new}"
                        data-itemIsHot="${item.is_hot}"
                        data-itemPrice="${item.price}"
                        data-itemYearProduce="${item.year_produce}"
                      >
                        Sửa
                      </button>
                      <input type="hidden" name="itemID" value="${item.id}" />
                    </div>
                  </td>
                </tr>
              </c:forEach>
            </tbody>
          </table>

          <!--Update Item-->
          <div id="update-products" class="modal-update flex-center">
            <div class="update-modal">
              <form class="form__update" action="#" method="post">
                <span class="close clsupdateproduct">&times;</span>

                <h2 class="text-center" style="padding: 16px 0">Cập nhật sản phẩm</h2>

                <!--Item Name-->
                <div class="form-grp">
                  <label for="update_itemNameID">Tên sản phẩm:</label>
                  <input
                    type="text"
                    maxlength="100"
                    id="update_itemNameID"
                    name="update_itemName"
                    value=""
                    placeholder="Nhập vào tên sản phẩm"
                  />
                </div>

                <!--Item Type-->
                <div class="form-grp">
                  <label for="update_label_itemTypeID">Loại sản phẩm:</label>
                  <select id="update_label_itemTypeID" name="update_itemTypeID">
                    <c:forEach items="${requestScope.itemTypeList}" var="itemType">
                      <option value="${itemType.id}">${itemType.name}</option>
                    </c:forEach>
                  </select>
                </div>

                <!--Item Collection-->
                <div class="form-grp">
                  <label for="update_label_itemCollectionID">Bộ sưu tập:</label>
                  <select
                    id="update_label_itemCollectionID"
                    name="update_itemCollectionID"
                  >
                    <option value="">Không</option>
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
                  <label for="update_label_itemMaterialID">Thành phần:</label>
                  <select id="update_label_itemMaterialID" name="update_itemMaterialID">
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
                  <label for="update_itemIsNewID">Sản phẩm mới:</label>
                  <input
                    type="checkbox"
                    id="update_itemIsNewID"
                    name="update_itemIsNew"
                    value=""
                  />
                </div>

                <!--Is Hot-->
                <div class="form-grp">
                  <label for="update_itemIsHotID">Sản phẩm hot:</label>
                  <input
                    type="checkbox"
                    id="update_itemIsHotID"
                    name="update_itemIsHot"
                    value=""
                  />
                </div>

                <!--Item Price-->
                <div class="form-grp">
                  <label for="update_itemPriceID">Giá sản phẩm:</label>
                  <input
                    type="number"
                    id="update_itemPriceID"
                    name="update_itemPrice"
                    value=""
                    placeholder="Nhập vào giá sản phẩm"
                  />
                </div>

                <!--Item Year Produce-->
                <div class="form-grp">
                  <label for="update_itemYearProduceID">Năm sản xuất:</label>
                  <input
                    type="number"
                    id="update_itemYearProduceID"
                    name="update_itemYearProduce"
                    value=""
                    placeholder="Nhập vào năm sản xuất"
                  />
                </div>

                <div class="flex-center">
                  <input type="hidden" name="action" value="item_btnUpdate" />
                  <button type="submit">Cập nhật</button>
                </div>
              </form>
            </div>
          </div>

          <!--Add Item-->
          <div id="add-products" class="modal-add flex-center modal__addproduct">
            <div class="add-modal">
              <form class="form__add" action="#" method="post">
                <span class="close clsaddproduct">&times;</span>
                <h2 class="text-center" style="padding: 16px 0">THÊM SẢN PHẨM</h2>

                <!--Item Name-->
                <div class="form-grp">
                  <label for="add_itemNameID">Tên sản phẩm:</label>
                  <input
                    type="text"
                    maxlength="100"
                    id="add_itemNameID"
                    name="add_itemName"
                    value=""
                    placeholder="Nhập vào tên sản phẩm"
                  />
                </div>

                <!--Item Type-->
                <div class="form-grp">
                  <label for="add_label_itemTypeID">Loại sản phẩm:</label>
                  <select id="add_label_itemTypeID" name="add_itemTypeID">
                    <c:forEach items="${requestScope.itemTypeList}" var="itemType">
                      <option value="${itemType.id}">${itemType.name}</option>
                    </c:forEach>
                  </select>
                </div>

                <!--Item Collection-->
                <div class="form-grp">
                  <label for="add_label_itemCollectionID">Bộ sưu tập:</label>
                  <select id="add_label_itemCollectionID" name="add_itemCollectionID">
                    <option value="">Không</option>
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
                  <label for="add_label_itemMaterialID">Thành phần:</label>
                  <select id="add_label_itemMaterialID" name="add_itemMaterialID">
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
                  <label for="add_label_isNewID">Sản phẩm mới:</label>
                  <input
                    type="checkbox"
                    id="add_label_isNewID"
                    name="add_itemIsNew"
                    value=""
                  />
                </div>

                <!--Is Hot-->
                <div class="form-grp">
                  <label for="add_label_isNewID">Sản phẩm hot:</label>
                  <input
                    type="checkbox"
                    id="add_label_isHotID"
                    name="add_itemIsHot"
                    value=""
                  />
                </div>

                <!--Item Price-->
                <div class="form-grp">
                  <label for="add_itemPriceID">Giá sản phẩm:</label>
                  <input
                    type="number"
                    id="add_itemPriceID"
                    name="add_itemPrice"
                    value=""
                    placeholder="Nhập vào giá sản phẩm"
                  />
                </div>

                <!--Item Year Produce-->
                <div class="form-grp">
                  <label for="add_itemYearProduceID">Năm sản xuất:</label>
                  <input
                    type="number"
                    id="add_itemYearProduceID"
                    name="add_itemYearProduce"
                    value=""
                    placeholder="Nhập vào năm sản xuất"
                  />
                </div>

                <div class="flex-center">
                  <button id="addproducts" type="submit" class="btn submit">Thêm</button>
                </div>
              </form>
            </div>
          </div>
        </div>

        <!--Customer-->
        <div class="customer-data table-data" data-type="customerList">
          <div class="header-table">
            <!--Add Customer Button-->
            <div class="AddCustomer">
              <button
                class="btnHD btnAdd"
                id="customer_addTrigger"
                style="margin-bottom: 4px"
              >
                Thêm
              </button>
            </div>
            <h2 style="font-size: 30px">Quản lý sản phẩm</h2>
            <div class="sorttable">
              <div class="sort-search">
                <button class="btnHD btngreen btnsearchbox">Tìm kiếm</button>
                <div class="inputsearch">
                  <input type="text" value="" />
                  <button class="btnHD btnsearch">Tìm</button>
                </div>
              </div>
              <div class="sort-box">
                <label for="">Sắp xếp theo:</label>
                <select name="" id="">
                  <option value="" selected>Z-A</option>
                  <option value="">A-Z</option>
                  <option value="">Giá tăng</option>
                  <option value="">Giá giảm</option>
                  <option value="">Mã sản phẩm</option>
                </select>
              </div>
            </div>
          </div>

          <!--Table Customer-->
          <table class="customer-table bang">
            <thead>
              <tr>
                <th>ID</th>
                <th>Tên Khách Hàng</th>
                <th>Số Điện Thoại</th>
                <th>Email</th>
                <th>Địa Chỉ</th>
                <th>Action</th>
              </tr>
            </thead>
            <tbody>
              <c:forEach items="${requestScope.customerList}" var="customer">
                <tr>
                  <td>${customer.id}</td>
                  <td>${customer.name}</td>
                  <td>${customer.phone_number}</td>
                  <td>${customer.email}</td>
                  <td>${customer.address}</td>
                  <td>
                    <div class="flex-center grpbtn">
                      <form
                        action="${pageContext.request.contextPath}/AdminManagerServlet"
                        method="post"
                        onsubmit="handleDelete()"
                      >
                        <button class="btnHD btnDel" type="submit">Xóa</button>
                        <input type="hidden" name="customerID" value="${customer.id}" />
                        <input type="hidden" name="action" value="customer_btnDelete" />
                      </form>
                      <button
                        class="btnHD btnUpdateCustomer"
                        data-customerID="${customer.id}"
                        data-customerName="${customer.name}"
                        data-customerPhoneNumber="${customer.phone_number}"
                        data-customerEmail="${customer.email}"
                        data-customerAddress="${customer.address}"
                      >
                        Sửa
                      </button>
                      <input type="hidden" name="customerID" value="${customer.id}" />
                    </div>
                  </td>
                </tr>
              </c:forEach>
            </tbody>
          </table>

          <!--Update Customer Modal-->
          <div id="update-customers" class="modal-update flex-center">
            <div class="update-modal">
              <form class="form__update" action="#" method="post">
                <span class="close clsUpdateCustomer">&times;</span>

                <h2 class="text-center" style="padding: 16px 0">Cập Nhật Khách Hàng</h2>

                <!--Customer Name-->
                <div class="form-grp">
                  <label for="update_customerName">Tên khách hàng:</label>
                  <input
                    type="text"
                    maxlength="100"
                    id="update_customerName"
                    name="update_customerName"
                    value=""
                    placeholder="Nhập vào tên khách hàng"
                  />
                </div>

                <!--Customer Phone Number-->
                <div class="form-grp">
                  <label for="update_customerPhoneNumber">Số điện thoại:</label>
                  <input
                    type="number"
                    maxlength="12"
                    id="update_customerPhoneNumber"
                    name="update_customerPhoneNumber"
                    value=""
                    placeholder="Nhập vào số điện thoại"
                  />
                </div>

                <!--Customer Email-->
                <div class="form-grp">
                  <label for="update_customerEmail">Email:</label>
                  <input
                    type="email"
                    maxlength="40"
                    id="update_customerEmail"
                    name="update_customerEmail"
                    value=""
                    placeholder="Nhập vào email"
                  />
                </div>

                <!--Customer Address-->
                <div class="form-grp">
                  <label for="update_customerAddress">Địa chỉ:</label>
                  <input
                    type="text"
                    maxlength="100"
                    id="update_customerAddress"
                    name="update_customerAddress"
                    value=""
                    placeholder="Nhập vào tên địa chỉ"
                  />
                </div>

                <div class="flex-center">
                  <input type="hidden" name="action" value="customer_btnUpdate" />
                  <button type="submit">Cập nhật</button>
                </div>
              </form>
            </div>
          </div>

          <!--Add Customer Modal-->
          <div id="add-customer" class="modal-add flex-center modal__addCustomer">
            <div class="add-modal">
              <form class="form__add" action="#" method="post">
                <span class="close clsAddCustomer">&times;</span>
                <h2 class="text-center" style="padding: 16px 0">Thêm Khách Hàng</h2>

                <!--Customer Name-->
                <div class="form-grp">
                  <label for="add_customerNameID">Tên khách hàng:</label>
                  <input
                    type="text"
                    maxlength="100"
                    id="add_customerNameID"
                    name="add_customerName"
                    value=""
                    placeholder="Nhập vào tên khách hàng"
                  />
                </div>

                <!--Customer Phone Number-->
                <div class="form-grp">
                  <label for="add_customerPhoneNumberID">Số điện thoại:</label>
                  <input
                    type="number"
                    maxlength="12"
                    id="add_customerPhoneNumberID"
                    name="add_customerPhoneNumber"
                    value=""
                    placeholder="Nhập vào số điện thoại"
                  />
                </div>

                <!--Customer Email-->
                <div class="form-grp">
                  <label for="add_customerEmailID">Email:</label>
                  <input
                    type="email"
                    maxlength="40"
                    id="add_customerEmailID"
                    name="add_customerEmail"
                    value=""
                    placeholder="Nhập vào email"
                  />
                </div>

                <!--Customer Address-->
                <div class="form-grp">
                  <label for="add_customerAddressID">Địa chỉ:</label>
                  <input
                    type="text"
                    maxlength="100"
                    id="add_customerAddressID"
                    name="add_customerAddress"
                    value=""
                    placeholder="Nhập vào tên địa chỉ"
                  />
                </div>

                <div class="flex-center">
                  <button id="addCustomer" type="submit" class="btn submit">Thêm</button>
                </div>
              </form>
            </div>
          </div>
        </div>

        <!--Discount Card-->
        <div class="discount-data table-data" data-type="discountCardList">
          <div class="header-table">
            <!--Add Discount Card Button-->
            <div class="AddDiscountCard">
              <button
                class="btnHD btnAdd"
                id="discountCard_addTrigger"
                style="margin-bottom: 4px"
              >
                Thêm
              </button>
            </div>
            <h2 style="font-size: 30px">Quản lý sản phẩm</h2>
            <div class="sorttable">
              <div class="sort-search">
                <button class="btnHD btngreen btnsearchbox">Tìm kiếm</button>
                <div class="inputsearch">
                  <input type="text" value="" />
                  <button class="btnHD btnsearch">Tìm</button>
                </div>
              </div>
              <div class="sort-box">
                <label for="">Sắp xếp theo:</label>
                <select name="" id="">
                  <option value="" selected>Z-A</option>
                  <option value="">A-Z</option>
                  <option value="">Giá tăng</option>
                  <option value="">Giá giảm</option>
                  <option value="">Mã sản phẩm</option>
                </select>
              </div>
            </div>
          </div>

          <!--Table Discount Card-->
          <table class="item-table bang">
            <thead>
              <tr>
                <th>ID</th>
                <th>ID Khách Hàng</th>
                <th>Tên Thẻ</th>
                <th>Phần Trăm Giảm</th>
                <th>Action</th>
              </tr>
            </thead>
            <tbody>
              <c:forEach items="${requestScope.discountCardList}" var="discountCard">
                <tr>
                  <td>${discountCard.id}</td>
                  <td>${discountCard.customer.id}</td>
                  <td>${discountCard.name}</td>
                  <td>${discountCard.discount_percentage}</td>
                  <td>
                    <div class="flex-center grpbtn">
                      <form
                        action="${pageContext.request.contextPath}/AdminManagerServlet"
                        method="post"
                        onsubmit="handleDelete()"
                      >
                        <button class="btnHD btnDel" type="submit">Xóa</button>
                        <input
                          type="hidden"
                          name="discountCardID"
                          value="${discountCard.id}"
                        />
                        <input
                          type="hidden"
                          name="action"
                          value="discountCard_btnDelete"
                        />
                      </form>
                      <button
                        class="btnHD btnUpdateDiscountCard"
                        data-discountCardID="${discountCard.id}"
                        data-discountCardCustomerID="${discountCard.customer.id}"
                        data-discountCardName="${discountCard.name}"
                        data-discountCardPercentage="${discountCard.discount_percentage}"
                      >
                        Sửa
                      </button>
                      <input
                        type="hidden"
                        name="discountCardID"
                        value="${discountCard.id}"
                      />
                    </div>
                  </td>
                </tr>
              </c:forEach>
            </tbody>
          </table>

          <!--Update Discount Card Modal-->
          <div id="update-discountCard" class="modal-update flex-center">
            <div class="update-modal">
              <form class="form__update" action="#" method="post">
                <span class="close clsUpdateDiscountCard">&times;</span>

                <h2 class="text-center" style="padding: 16px 0">
                  Cập Nhật Thẻ Khuyến Mãi
                </h2>

                <!--Customer ID-->
                <div class="form-grp">
                  <label for="update_discountCardCustomerID">Khách Hàng:</label>
                  <select
                    id="update_discountCardCustomerID"
                    name="update_discountCardCustomerID"
                  >
                    <c:forEach items="${requestScope.customerList}" var="customer">
                      <option value="${customer.id}">
                        ${customer.id} : ${customer.name}
                      </option>
                    </c:forEach>
                  </select>
                </div>

                <!--Discount Card Name-->
                <div class="form-grp">
                  <label for="update_discountCardName">Tên Thẻ:</label>
                  <input
                    type="text"
                    maxlength="100"
                    id="update_discountCardName"
                    name="update_discountCardName"
                    value=""
                    placeholder="Nhập vào tên thẻ"
                  />
                </div>

                <!--Discount Card Discount Percentage-->
                <div class="form-grp">
                  <label for="update_discountCardPercentage">Phần Trăm Giảm:</label>
                  <input
                    type="number"
                    maxlength="1000"
                    id="update_discountCardPercentage"
                    name="update_discountCardPercentage"
                    value=""
                    placeholder="Nhập vào phần trăm giảm"
                  />
                </div>

                <div class="flex-center">
                  <input type="hidden" name="action" value="discountCard_btnUpdate" />
                  <button type="submit">Cập nhật</button>
                </div>
              </form>
            </div>
          </div>

          <!--Add Discount Card Modal-->
          <div id="add-discountCard" class="modal-add flex-center modal__addDiscountCard">
            <div class="add-modal">
              <form class="form__add" action="#" method="post">
                <span class="close clsAddDiscountCard">&times;</span>
                <h2 class="text-center" style="padding: 16px 0">Thêm Thẻ Khuyến Mãi</h2>

                <!--Customer ID-->
                <div class="form-grp">
                  <label for="add_discountCardCustomerID">Khách Hàng:</label>
                  <select
                    id="add_discountCardCustomerID"
                    name="add_discountCardCustomerID"
                  >
                    <c:forEach items="${requestScope.customerList}" var="customer">
                      <option value="${customer.id}">
                        ${customer.id} : ${customer.name}
                      </option>
                    </c:forEach>
                  </select>
                </div>

                <!--Discount Card Name-->
                <div class="form-grp">
                  <label for="add_discountCardName">Tên Thẻ:</label>
                  <input
                    type="text"
                    maxlength="100"
                    id="add_discountCardName"
                    name="add_discountCardName"
                    value=""
                    placeholder="Nhập vào tên thẻ"
                  />
                </div>

                <!--Discount Card Discount Percentage-->
                <div class="form-grp">
                  <label for="add_discountCardPercentage">Phần Trăm Giảm:</label>
                  <input
                    type="number"
                    maxlength="1000"
                    id="add_discountCardPercentage"
                    name="add_discountCardPercentage"
                    value=""
                    placeholder="Nhập vào phần trăm giảm"
                  />
                </div>

                <div class="flex-center">
                  <button id="addDiscountCard" type="submit" class="btn submit">
                    Thêm
                  </button>
                </div>
              </form>
            </div>
          </div>
        </div>

        <!--Item Collection-->
        <div class="collection-data table-data" data-type="itemCollectionList">
          <!--Add Discount Card Button-->
          <div class="header-table">
            <div class="AddItemCollection">
              <button
                class="btnHD btnAdd"
                id="itemCollection_addTrigger"
                style="margin-bottom: 4px"
              >
                Thêm
              </button>
            </div>
            <h2 style="font-size: 30px">Quản lý sản phẩm</h2>
            <div class="sorttable">
              <div class="sort-search">
                <button class="btnHD btngreen btnsearchbox">Tìm kiếm</button>
                <div class="inputsearch">
                  <input type="text" value="" />
                  <button class="btnHD btnsearch">Tìm</button>
                </div>
              </div>
              <div class="sort-box">
                <label for="">Sắp xếp theo:</label>
                <select name="" id="">
                  <option value="" selected>Z-A</option>
                  <option value="">A-Z</option>
                  <option value="">Giá tăng</option>
                  <option value="">Giá giảm</option>
                  <option value="">Mã sản phẩm</option>
                </select>
              </div>
            </div>
          </div>

          <!--Add Item Collection Button-->

          <!--Table Item Collection-->
          <table class="item-table bang">
            <thead>
              <tr>
                <th>ID</th>
                <th>Tên Bộ Sưu Tập</th>
                <th>Action</th>
              </tr>
            </thead>
            <tbody>
              <c:forEach items="${requestScope.itemCollectionList}" var="itemCollection">
                <tr>
                  <td>${itemCollection.id}</td>
                  <td>${itemCollection.name}</td>
                  <td>
                    <div class="flex-center grpbtn">
                      <form
                        action="${pageContext.request.contextPath}/AdminManagerServlet"
                        method="post"
                        onsubmit="handleDelete()"
                      >
                        <button class="btnHD btnDel" type="submit">Xóa</button>
                        <input
                          type="hidden"
                          name="itemCollectionID"
                          value="${itemCollection.id}"
                        />
                        <input
                          type="hidden"
                          name="action"
                          value="itemCollection_btnDelete"
                        />
                      </form>
                      <button
                        class="btnHD btnUpdateItemCollection"
                        data-itemCollectionID="${itemCollection.id}"
                        data-itemCollectionName="${itemCollection.name}"
                      >
                        Sửa
                      </button>
                      <input
                        type="hidden"
                        name="itemCollectionID"
                        value="${itemCollection.id}"
                      />
                    </div>
                  </td>
                </tr>
              </c:forEach>
            </tbody>
          </table>

          <!--Update Item Collection Modal-->
          <div id="update-itemCollection" class="modal-update flex-center">
            <div class="update-modal">
              <form class="form__update" action="#" method="post">
                <span class="close clsUpdateItemCollection">&times;</span>

                <h2 class="text-center" style="padding: 16px 0">Cập Nhật Bộ Sưu Tập</h2>

                <!--Item Collection Name-->
                <div class="form-grp">
                  <label for="update_itemCollectionName">Tên Bộ Sưu Tập:</label>
                  <input
                    type="text"
                    maxlength="100"
                    id="update_itemCollectionName"
                    name="update_itemCollectionName"
                    value=""
                    placeholder="Nhập vào tên bộ sưu tập"
                  />
                </div>

                <div class="flex-center">
                  <input type="hidden" name="action" value="itemCollection_btnUpdate" />
                  <button type="submit">Cập nhật</button>
                </div>
              </form>
            </div>
          </div>

          <!--Add Item Collection Modal-->
          <div
            id="add-itemCollection"
            class="modal-add flex-center modal__addItemCollection"
          >
            <div class="add-modal">
              <form class="form__add" action="#" method="post">
                <span class="close clsAddItemCollection">&times;</span>
                <h2 class="text-center" style="padding: 16px 0">Thêm Bộ Sưu Tập</h2>

                <!--Item Collection Name-->
                <div class="form-grp">
                  <label for="add_itemCollectionName">Tên Bộ Sưu Tập:</label>
                  <input
                    type="text"
                    maxlength="100"
                    id="add_itemCollectionName"
                    name="add_itemCollectionName"
                    value=""
                    placeholder="Nhập vào tên bộ sưu tập"
                  />
                </div>

                <div class="flex-center">
                  <button id="addItemCollection" type="submit" class="btn submit">
                    Thêm
                  </button>
                </div>
              </form>
            </div>
          </div>
        </div>

        <!--Item Image-->
        <div class="image-data table-data" data-type="itemImageList">
          <div class="header-table">
            <!--Add Item Image Button-->
            <div class="AddItemImage">
              <button
                class="btnHD btnAdd"
                id="itemImage_addTrigger"
                style="margin-bottom: 4px"
              >
                Thêm
              </button>
            </div>
            <h2 style="font-size: 30px">Quản lý sản phẩm</h2>
            <div class="sorttable">
              <div class="sort-search">
                <button class="btnHD btngreen btnsearchbox">Tìm kiếm</button>
                <div class="inputsearch">
                  <input type="text" value="" />
                  <button class="btnHD btnsearch">Tìm</button>
                </div>
              </div>
              <div class="sort-box">
                <label for="">Sắp xếp theo:</label>
                <select name="" id="">
                  <option value="" selected>Z-A</option>
                  <option value="">A-Z</option>
                  <option value="">Giá tăng</option>
                  <option value="">Giá giảm</option>
                  <option value="">Mã sản phẩm</option>
                </select>
              </div>
            </div>
          </div>

          <!--Table Item Image-->
          <table class="item-table bang">
            <thead>
              <tr>
                <th>ID</th>
                <th>ID Sản Phẩm</th>
                <th>URL Hình Ảnh</th>
                <th>Action</th>
              </tr>
            </thead>
            <tbody>
              <c:forEach items="${requestScope.imageList}" var="itemImage">
                <tr>
                  <td>${itemImage.id}</td>
                  <td>${itemImage.item.id}</td>
                  <td>${itemImage.image_url}</td>
                  <td>
                    <div class="flex-center grpbtn">
                      <form
                        action="${pageContext.request.contextPath}/AdminManagerServlet"
                        method="post"
                        onsubmit="handleDelete()"
                      >
                        <button class="btnHD btnDel" type="submit">Xóa</button>
                        <input type="hidden" name="itemImageID" value="${itemImage.id}" />
                        <input type="hidden" name="action" value="itemImage_btnDelete" />
                      </form>
                      <button
                        class="btnHD btnUpdateItemImage"
                        data-itemImageID="${itemImage.id}"
                        data-itemImageItemID="${itemImage.item.id}"
                        data-itemImageURL="${itemImage.image_url}"
                      >
                        Sửa
                      </button>
                      <input type="hidden" name="itemImageID" value="${itemImage.id}" />
                    </div>
                  </td>
                </tr>
              </c:forEach>
            </tbody>
          </table>

          <!--Update Item Image Modal-->
          <div id="update-itemImage" class="modal-update flex-center">
            <div class="update-modal">
              <form class="form__update" action="#" method="post">
                <span class="close clsUpdateItemImage">&times;</span>

                <h2 class="text-center" style="padding: 16px 0">
                  Cập Nhật Hình Ảnh Sản Phẩm
                </h2>

                <!--Item ID-->
                <div class="form-grp">
                  <label for="update_itemImageItemID">Sản Phẩm:</label>
                  <select id="update_itemImageItemID" name="update_itemImageItemID">
                    <c:forEach items="${requestScope.itemList}" var="item">
                      <option value="${item.id}">${item.id} : ${item.name}</option>
                    </c:forEach>
                  </select>
                </div>

                <!--Item Image URL-->
                <div class="form-grp">
                  <label for="update_itemImageURL">URL Hình Ảnh:</label>
                  <input
                    type="text"
                    maxlength="100"
                    id="update_itemImageURL"
                    name="update_itemImageURL"
                    value=""
                    placeholder="Nhập vào đường dẫn hình ảnh"
                  />
                </div>

                <div class="flex-center">
                  <input type="hidden" name="action" value="itemImage_btnUpdate" />
                  <button type="submit">Cập nhật</button>
                </div>
              </form>
            </div>
          </div>

          <!--Add Item Image Modal-->
          <div id="add-itemImage" class="modal-add flex-center modal__addItemImage">
            <div class="add-modal">
              <form class="form__add" action="#" method="post">
                <span class="close clsAddItemImage">&times;</span>
                <h2 class="text-center" style="padding: 16px 0">
                  Thêm Hình Ảnh Sản Phẩm
                </h2>

                <!--Item ID-->
                <div class="form-grp">
                  <label for="add_itemImageItemID">Sản Phẩm:</label>
                  <select id="add_itemImageItemID" name="add_itemImageItemID">
                    <c:forEach items="${requestScope.itemList}" var="item">
                      <option value="${item.id}">${item.id} : ${item.name}</option>
                    </c:forEach>
                  </select>
                </div>

                <!--Item Image URL-->
                <div class="form-grp">
                  <label for="add_itemImageURL">URL Hình Ảnh:</label>
                  <input
                    type="text"
                    maxlength="100"
                    id="add_itemImageURL"
                    name="add_itemImageURL"
                    value=""
                    placeholder="Nhập vào đường dẫn hình ảnh"
                  />
                </div>

                <div class="flex-center">
                  <button id="addItemImage" type="submit" class="btn submit">Thêm</button>
                </div>
              </form>
            </div>
          </div>
        </div>

        <!--Item Material-->
        <div class="product-data table-data" data-type="itemMaterialList">
          <div class="header-table">
            <!--Add Item Image Button-->
            <div class="AddItemMaterial">
              <button
                class="btnHD btnAdd"
                id="itemMaterial_addTrigger"
                style="margin-bottom: 4px"
              >
                Thêm
              </button>
            </div>
            <h2 style="font-size: 30px">Quản lý sản phẩm</h2>
            <div class="sorttable">
              <div class="sort-search">
                <button class="btnHD btngreen btnsearchbox">Tìm kiếm</button>
                <div class="inputsearch">
                  <input type="text" value="" />
                  <button class="btnHD btnsearch">Tìm</button>
                </div>
              </div>
              <div class="sort-box">
                <label for="">Sắp xếp theo:</label>
                <select name="" id="">
                  <option value="" selected>Z-A</option>
                  <option value="">A-Z</option>
                  <option value="">Giá tăng</option>
                  <option value="">Giá giảm</option>
                  <option value="">Mã sản phẩm</option>
                </select>
              </div>
            </div>
          </div>

          <!--Table Item Image-->
          <table class="item-table bang">
            <thead>
              <tr>
                <th>ID</th>
                <th>Tên Vật Liệu</th>
                <th>Action</th>
              </tr>
            </thead>
            <tbody>
              <c:forEach items="${requestScope.itemMaterialList}" var="itemMaterial">
                <tr>
                  <td>${itemMaterial.id}</td>
                  <td>${itemMaterial.name}</td>
                  <td>
                    <div class="flex-center grpbtn">
                      <form
                        action="${pageContext.request.contextPath}/AdminManagerServlet"
                        method="post"
                        onsubmit="handleDelete()"
                      >
                        <button class="btnHD btnDel" type="submit">Xóa</button>
                        <input
                          type="hidden"
                          name="itemMaterialID"
                          value="${itemMaterial.id}"
                        />
                        <input
                          type="hidden"
                          name="action"
                          value="itemMaterial_btnDelete"
                        />
                      </form>
                      <button
                        class="btnHD btnUpdateItemMaterial"
                        data-itemMaterialID="${itemMaterial.id}"
                        data-itemMaterialName="${itemMaterial.name}"
                      >
                        Sửa
                      </button>
                      <input
                        type="hidden"
                        name="itemMaterialID"
                        value="${itemMaterial.id}"
                      />
                    </div>
                  </td>
                </tr>
              </c:forEach>
            </tbody>
          </table>

          <!--Update Item Material Modal-->
          <div id="update-itemMaterial" class="modal-update flex-center">
            <div class="update-modal">
              <form class="form__update" action="#" method="post">
                <span class="close clsUpdateItemMaterial">&times;</span>

                <h2 class="text-center" style="padding: 16px 0">
                  Cập Nhật Vật Liệu Sản Phẩm
                </h2>

                <!--Item Material Name-->
                <div class="form-grp">
                  <label for="update_itemMaterialName">Tên Vật Liệu:</label>
                  <input
                    type="text"
                    maxlength="100"
                    id="update_itemMaterialName"
                    name="update_itemMaterialName"
                    value=""
                    placeholder="Nhập vào tên vật liệu sản phẩm"
                  />
                </div>

                <div class="flex-center">
                  <input type="hidden" name="action" value="itemMaterial_btnUpdate" />
                  <button type="submit">Cập Nhật</button>
                </div>
              </form>
            </div>
          </div>

          <!--Add Item Image Modal-->
          <div id="add-itemMaterial" class="modal-add flex-center modal__addItemMaterial">
            <div class="add-modal">
              <form class="form__add" action="#" method="post">
                <span class="close clsAddItemMaterial">&times;</span>
                <h2 class="text-center" style="padding: 16px 0">
                  Thêm Vật Liệu Sản Phẩm
                </h2>

                <!--Item Material Name-->
                <div class="form-grp">
                  <label for="add_itemMaterialName">Tên Vật Liệu:</label>
                  <input
                    type="text"
                    maxlength="100"
                    id="add_itemMaterialName"
                    name="add_itemMaterialName"
                    value=""
                    placeholder="Nhập vào tên vật liệu sản phẩm"
                  />
                </div>

                <div class="flex-center">
                  <button id="addItemMaterial" type="submit" class="btn submit">
                    Thêm
                  </button>
                </div>
              </form>
            </div>
          </div>
        </div>

        <!--Order-->
        <div class="order-data table-data" data-type="orderList">
          <div class="header-table">
            <div class="AddOrder">
              <button
                class="btnHD btnAdd"
                id="order_addTrigger"
                style="margin-bottom: 4px"
              >
                Thêm
              </button>
            </div>
            <h2 style="font-size: 30px">Quản lý sản phẩm</h2>
            <div class="sorttable">
              <div class="sort-search">
                <button class="btnHD btngreen btnsearchbox">Tìm kiếm</button>
                <div class="inputsearch">
                  <input type="text" value="" />
                  <button class="btnHD btnsearch">Tìm</button>
                </div>
              </div>
              <div class="sort-box">
                <label for="">Sắp xếp theo:</label>
                <select name="" id="">
                  <option value="" selected>Z-A</option>
                  <option value="">A-Z</option>
                  <option value="">Giá tăng</option>
                  <option value="">Giá giảm</option>
                  <option value="">Mã sản phẩm</option>
                </select>
              </div>
            </div>
          </div>

          <!--Add Order Button-->

          <!--Table Order-->
          <table class="item-table bang">
            <thead>
              <tr>
                <th>ID</th>
                <th>ID Khách Hàng</th>
                <th>ID Thẻ Khuyến Mãi</th>
                <th>Tổng Tiền</th>
                <th>Ngày Mua</th>
                <th>Tình Trạng Đơn Hàng</th>
                <th>Địa Chỉ</th>
                <th>Ghi Chú</th>
                <th>Action</th>
              </tr>
            </thead>
            <tbody>
              <c:forEach items="${requestScope.orderList}" var="order">
                <tr>
                  <td>${order.id}</td>
                  <td>${order.customer.id}</td>
                  <td>${order.discountCard.id}</td>
                  <td>${order.total}</td>
                  <td>${order.date_purchase}</td>
                  <td>${order.order_status}</td>
                  <td>${order.address}</td>
                  <td>${order.note}</td>
                  <td>
                    <div class="flex-center grpbtn">
                      <form
                        action="${pageContext.request.contextPath}/AdminManagerServlet"
                        method="post"
                        onsubmit="handleDelete()"
                      >
                        <button class="btnHD btnDel" type="submit">Xóa</button>
                        <input type="hidden" name="orderID" value="${order.id}" />
                        <input type="hidden" name="action" value="order_btnDelete" />
                      </form>
                      <button
                        class="btnHD btnUpdateOrder"
                        data-orderID="${order.id}"
                        data-orderCustomerID="${order.customer.id}"
                        data-orderTotal="${order.total}"
                        data-orderDatePurchase="${order.date_purchase}"
                        data-orderDiscountCardID="${order.discountCard.id}"
                        data-orderStatus="${order.order_status}"
                        data-orderAddress="${order.address}"
                        data-orderNote="${order.note}"
                      >
                        Sửa
                      </button>
                      <input type="hidden" name="orderID" value="${order.id}" />
                    </div>
                  </td>
                </tr>
              </c:forEach>
            </tbody>
          </table>

          <!--Update Order Modal-->
          <div id="update-order" class="modal-update flex-center">
            <div class="update-modal">
              <form class="form__update" action="#" method="post">
                <span class="close clsUpdateOrder">&times;</span>

                <h2 class="text-center" style="padding: 16px 0">Cập Nhật Hóa Đơn</h2>

                <!--Order Customer ID-->
                <div class="form-grp">
                  <label for="update_orderCustomerID">Khách Hàng:</label>
                  <select id="update_orderCustomerID" name="update_orderCustomerID">
                    <c:forEach items="${requestScope.customerList}" var="customer">
                      <option value="${customer.id}">
                        ${customer.id} : ${customer.name}
                      </option>
                    </c:forEach>
                  </select>
                </div>

                <!--Order Discount Card ID-->
                <div class="form-grp">
                  <label for="update_orderDiscountCardID">Thẻ Khuyến Mãi:</label>
                  <select
                    id="update_orderDiscountCardID"
                    name="update_orderDiscountCardID"
                  >
                    <c:forEach
                      items="${requestScope.discountCardList}"
                      var="discountCard"
                    >
                      <option value="${discountCard.id}">
                        ${discountCard.id} : ${discountCard.name}
                      </option>
                    </c:forEach>
                  </select>
                </div>

                <!--Order Total-->
                <div class="form-grp">
                  <label for="update_orderTotal">Tổng Tiền:</label>
                  <input
                    type="number"
                    maxlength="100"
                    id="update_orderTotal"
                    name="update_orderTotal"
                    value=""
                    placeholder="Nhập vào giá trị hóa đơn"
                    disabled
                  />
                </div>

                <!--Order Date Purchase-->
                <div class="form-grp">
                  <label for="update_orderDatePurchase">Ngày Mua:</label>
                  <input
                    type="date"
                    maxlength="100"
                    id="update_orderDatePurchase"
                    name="update_orderDatePurchase"
                    value=""
                    placeholder="Nhập vào ngày mua"
                  />
                </div>

                <!--Order Status-->
                <div class="form-grp">
                  <label for="update_orderStatus">Tình trạng đơn hàng:</label>
                  <select id="update_orderStatus" name="update_orderStatus">
                    <option value="${0}">0 : Đã đặt đơn</option>
                    <option value="${1}">1 : Đang chuẩn bị hàng</option>
                    <option value="${2}">2 : Đơn hàng đã hủy</option>
                    <option value="${3}">3 : Đang giao hàng</option>
                    <option value="${4}">4 : Đơn hàng thành công</option>
                  </select>
                </div>

                <!--Order Address-->
                <div class="form-grp">
                  <label for="update_orderAddress">Địa chỉ đặt hàng:</label>
                  <input
                    type="text"
                    maxlength="100"
                    id="update_orderAddress"
                    name="update_orderAddress"
                    value=""
                    placeholder="Nhập vào địa chỉ giao hàng"
                  />
                </div>

                <!--Order Note-->
                <div class="form-grp">
                  <label for="update_orderNote">Ngày Mua:</label>
                  <input
                    type="date"
                    maxlength="100"
                    id="update_orderNote"
                    name="update_orderNote"
                    value=""
                    placeholder="Nhập vào ghi chú"
                  />
                </div>

                <div class="flex-center">
                  <input type="hidden" name="action" value="order_btnUpdate" />
                  <button type="submit">Cập Nhật</button>
                </div>
              </form>
            </div>
          </div>

          <!--Add Order Modal-->
          <div id="add-order" class="modal-add flex-center modal__addOrder">
            <div class="add-modal">
              <form class="form__add" action="#" method="post">
                <span class="close clsAddOrder">&times;</span>
                <h2 class="text-center" style="padding: 16px 0">Thêm Hóa Đơn</h2>

                <!--Order Customer ID-->
                <div class="form-grp">
                  <label for="add_orderCustomerID">Khách Hàng:</label>
                  <select id="add_orderCustomerID" name="add_orderCustomerID">
                    <c:forEach items="${requestScope.customerList}" var="customer">
                      <option value="${customer.id}">
                        ${customer.id} : ${customer.name}
                      </option>
                    </c:forEach>
                  </select>
                </div>

                <!--Order Discount Card ID-->
                <div class="form-grp">
                  <label for="add_orderDiscountCardID">Thẻ Khuyến Mãi:</label>
                  <select id="add_orderDiscountCardID" name="add_add_orderDiscountCardID">
                    <c:forEach
                      items="${requestScope.discountCardList}"
                      var="discountCard"
                    >
                      <option value="${discountCard.id}">
                        ${discountCard.id} : ${discountCard.name}
                      </option>
                    </c:forEach>
                  </select>
                </div>

                <!--Order Total-->
                <div class="form-grp">
                  <label for="add_orderTotal">Tổng Tiền:</label>
                  <input
                    type="number"
                    maxlength="100"
                    id="add_orderTotal"
                    name="add_orderTotal"
                    value=""
                    placeholder="Nhập vào giá trị hóa đơn"
                    disabled
                  />
                </div>

                <!--Order Date Purchase-->
                <div class="form-grp">
                  <label for="add_orderDatePurchase">Ngày Mua:</label>
                  <input
                    type="date"
                    maxlength="100"
                    id="add_orderDatePurchase"
                    name="add_orderDatePurchase"
                    value=""
                    placeholder="Nhập vào ngày mua"
                  />
                </div>

                <!--Order Status-->
                <div class="form-grp">
                  <label for="add_orderStatus">Tình trạng đơn hàng:</label>
                  <select id="add_orderStatus" name="add_orderStatus">
                    <option value="${0}">0 : Đã đặt đơn</option>
                    <option value="${1}">1 : Đang chuẩn bị hàng</option>
                    <option value="${2}">2 : Đơn hàng đã hủy</option>
                    <option value="${3}">3 : Đang giao hàng</option>
                    <option value="${4}">4 : Đơn hàng thành công</option>
                  </select>
                </div>

                <!--Order Address-->
                <div class="form-grp">
                  <label for="add_orderAddress">Địa chỉ đặt hàng:</label>
                  <input
                    type="text"
                    maxlength="100"
                    id="add_orderAddress"
                    name="add_orderAddress"
                    value=""
                    placeholder="Nhập vào địa chỉ giao hàng"
                  />
                </div>

                <!--Order Note-->
                <div class="form-grp">
                  <label for="add_orderNote">Ngày Mua:</label>
                  <input
                    type="date"
                    maxlength="100"
                    id="add_orderNote"
                    name="add_orderNote"
                    value=""
                    placeholder="Nhập vào ghi chú"
                  />
                </div>

                <div class="flex-center">
                  <button id="addOrder" type="submit" class="btn submit">Thêm</button>
                </div>
              </form>
            </div>
          </div>
        </div>

        <!--Order Detail-->
        <div class="orderdetail-data table-data" data-type="orderDetailList">
          <div class="header-table">
            <div class="AddOrderDetailDetail">
              <button
                class="btnHD btnAdd"
                id="orderDetail_addTrigger"
                style="margin-bottom: 4px"
              >
                Thêm
              </button>
            </div>
            <h2 style="font-size: 30px">Quản lý sản phẩm</h2>
            <div class="sorttable">
              <div class="sort-search">
                <button class="btnHD btngreen btnsearchbox">Tìm kiếm</button>
                <div class="inputsearch">
                  <input type="text" value="" />
                  <button class="btnHD btnsearch">Tìm</button>
                </div>
              </div>
              <div class="sort-box">
                <label for="">Sắp xếp theo:</label>
                <select name="" id="">
                  <option value="" selected>Z-A</option>
                  <option value="">A-Z</option>
                  <option value="">Giá tăng</option>
                  <option value="">Giá giảm</option>
                  <option value="">Mã sản phẩm</option>
                </select>
              </div>
            </div>
          </div>

          <!--Add OrderDetail Button-->
          <div class="AddOrderDetailDetail">
            <button
              class="btnHD btnAdd"
              id="orderDetail_addTrigger"
              style="margin-bottom: 4px"
            >
              Thêm
            </button>
          </div>

          <!--Table OrderDetail-->
          <table class="item-table bang">
            <thead>
              <tr>
                <th>ID</th>
                <th>ID Hóa Đơn</th>
                <th>ID Sản Phẩm</th>
                <th>Số Lượng</th>
                <th>Màu</th>
                <th>Size</th>
                <th>Tổng Tiền</th>
                <th>Action</th>
              </tr>
            </thead>
            <tbody>
              <c:forEach items="${requestScope.orderDetailList}" var="orderDetail">
                <tr>
                  <td>${orderDetail.id}</td>
                  <td>${orderDetail.order.id}</td>
                  <td>${orderDetail.item.id}</td>
                  <td>${orderDetail.amount}</td>
                  <td>${orderDetail.item_color}</td>
                  <td>${orderDetail.item_size}</td>
                  <td>${orderDetail.total}</td>
                  <td>
                    <div class="flex-center grpbtn">
                      <form
                        action="${pageContext.request.contextPath}/AdminManagerServlet"
                        method="post"
                        onsubmit="handleDelete()"
                      >
                        <button class="btnHD btnDel" type="submit">Xóa</button>
                        <input
                          type="hidden"
                          name="orderDetailID"
                          value="${orderDetail.id}"
                        />
                        <input
                          type="hidden"
                          name="action"
                          value="orderDetail_btnDelete"
                        />
                      </form>
                      <button
                        class="btnHD btnUpdateOrderDetail"
                        data-orderDetailID="${orderDetail.id}"
                        data-orderDetailOrderID="${orderDetail.order.id}"
                        data-orderDetailItemID="${orderDetail.item.id}"
                        data-orderDetailAmount="${orderDetail.amount}"
                        data-orderDetailItemColor="${orderDetail.item_color}"
                        data-orderDetailItemSize="${orderDetail.item_size}"
                        data-orderDetailTotal="${orderDetail.total}"
                      >
                        Sửa
                      </button>
                      <input
                        type="hidden"
                        name="orderDetailID"
                        value="${orderDetail.id}"
                      />
                    </div>
                  </td>
                </tr>
              </c:forEach>
            </tbody>
          </table>

          <!--Update OrderDetail Modal-->
          <div id="update-orderDetail" class="modal-update flex-center">
            <div class="update-modal">
              <form class="form__update" action="#" method="post">
                <span class="close clsUpdateOrderDetail">&times;</span>

                <h2 class="text-center" style="padding: 16px 0">
                  Cập Nhật Chi Tiết Hóa Đơn
                </h2>

                <!--OrderDetail Order ID-->
                <div class="form-grp">
                  <label for="update_orderDetailOrderID">Hóa Đơn:</label>
                  <select id="update_orderDetailOrderID" name="update_orderDetailOrderID">
                    <c:forEach items="${requestScope.orderList}" var="order">
                      <option value="${order.id}">${order.id}</option>
                    </c:forEach>
                  </select>
                </div>

                <!--OrderDetail Item ID-->
                <div class="form-grp">
                  <label for="update_orderDetailItemID">Sản Phẩm:</label>
                  <select
                    id="update_orderDetailItemID"
                    name="update_update_orderDetailItemID"
                  >
                    <c:forEach items="${requestScope.itemList}" var="item">
                      <option value="${item.id}">${item.id} : ${item.name}</option>
                    </c:forEach>
                  </select>
                </div>

                <!--OrderDetail Amount-->
                <div class="form-grp">
                  <label for="update_orderDetailAmount">Số lượng:</label>
                  <input
                    type="number"
                    maxlength="100"
                    id="update_orderDetailAmount"
                    name="update_orderDetailAmount"
                    value=""
                    placeholder="Nhập vào số lượng sản phẩm"
                  />
                </div>

                <!--OrderDetail Item Color-->
                <div class="form-grp">
                  <label for="update_orderDetailItemColor">Màu:</label>
                  <select
                    id="update_orderDetailItemColor"
                    name="update_orderDetailItemColor"
                  >
                    <c:forEach items="${requestScope.stockItemList}" var="stockItem">
                      <option value="${stockItem.color}">${stockItem.color}</option>
                    </c:forEach>
                  </select>
                </div>

                <!--OrderDetail Item Size-->
                <div class="form-grp">
                  <label for="update_orderDetailItemSize">Màu:</label>
                  <select
                    id="update_orderDetailItemSize"
                    name="update_orderDetailItemSize"
                  >
                    <c:forEach items="${requestScope.stockItemList}" var="stockItem">
                      <option value="${stockItem.size}">${stockItem.size}</option>
                    </c:forEach>
                  </select>
                </div>

                <!--Order Total-->
                <div class="form-grp">
                  <label for="update_orderDetailTotal">Tổng Tiền:</label>
                  <input
                    type="number"
                    maxlength="100"
                    id="update_orderDetailTotal"
                    name="update_orderDetailTotal"
                    value=""
                    placeholder="Nhập vào giá trị chi tiết hóa đơn"
                    disabled
                  />
                </div>

                <div class="flex-center">
                  <input type="hidden" name="action" value="orderDetail_btnUpdate" />
                  <button type="submit">Cập Nhật</button>
                </div>
              </form>
            </div>
          </div>

          <!--Add Order Detail Modal-->
          <div id="add-orderDetail" class="modal-add flex-center modal__addOrderDetail">
            <div class="add-modal">
              <form class="form__add" action="#" method="post">
                <span class="close clsAddOrderDetail">&times;</span>
                <h2 class="text-center" style="padding: 16px 0">Thêm Chi Tiết Hóa Đơn</h2>

                <!--OrderDetail Order ID-->
                <div class="form-grp">
                  <label for="add_orderDetailOrderID">Hóa Đơn:</label>
                  <select id="add_orderDetailOrderID" name="add_orderDetailOrderID">
                    <c:forEach items="${requestScope.orderList}" var="order">
                      <option value="${order.id}">${order.id}</option>
                    </c:forEach>
                  </select>
                </div>

                <!--OrderDetail Item ID-->
                <div class="form-grp">
                  <label for="add_orderDetailItemID">Sản Phẩm:</label>
                  <select id="add_orderDetailItemID" name="add_add_orderDetailItemID">
                    <c:forEach items="${requestScope.itemList}" var="item">
                      <option value="${item.id}">${item.id} : ${item.name}</option>
                    </c:forEach>
                  </select>
                </div>

                <!--OrderDetail Amount-->
                <div class="form-grp">
                  <label for="add_orderDetailAmount">Số lượng:</label>
                  <input
                    type="number"
                    maxlength="100"
                    id="add_orderDetailAmount"
                    name="add_orderDetailAmount"
                    value=""
                    placeholder="Nhập vào số lượng sản phẩm"
                  />
                </div>

                <!--OrderDetail Item Color-->
                <div class="form-grp">
                  <label for="add_orderDetailItemColor">Màu:</label>
                  <select id="add_orderDetailItemColor" name="add_orderDetailItemColor">
                    <c:forEach items="${requestScope.stockItemList}" var="stockItem">
                      <option value="${stockItem.color}">${stockItem.color}</option>
                    </c:forEach>
                  </select>
                </div>

                <!--OrderDetail Item Size-->
                <div class="form-grp">
                  <label for="add_orderDetailItemSize">Màu:</label>
                  <select id="add_orderDetailItemSize" name="add_orderDetailItemSize">
                    <c:forEach items="${requestScope.stockItemList}" var="stockItem">
                      <option value="${stockItem.size}">${stockItem.size}</option>
                    </c:forEach>
                  </select>
                </div>

                <!--Order Total-->
                <div class="form-grp">
                  <label for="add_orderDetailTotal">Tổng Tiền:</label>
                  <input
                    type="number"
                    maxlength="100"
                    id="add_orderDetailTotal"
                    name="add_orderDetailTotal"
                    value=""
                    placeholder="Nhập vào giá trị chi tiết hóa đơn"
                    disabled
                  />
                </div>

                <div class="flex-center">
                  <button id="addOrderDetail" type="submit" class="btn submit">
                    Thêm
                  </button>
                </div>
              </form>
            </div>
          </div>
        </div>

        <!--Item Type-->
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
            </div>

            <h2 style="font-size: 30px">Quản lý sản phẩm</h2>
            <div class="sorttable">
              <div class="sort-search">
                <button class="btnHD btngreen btnsearchbox">Tìm kiếm</button>
                <div class="inputsearch">
                  <input type="text" value="" />
                  <button class="btnHD btnsearch">Tìm</button>
                </div>
              </div>
              <div class="sort-box">
                <label for="">Sắp xếp theo:</label>
                <select name="" id="">
                  <option value="" selected>Z-A</option>
                  <option value="">A-Z</option>
                  <option value="">Giá tăng</option>
                  <option value="">Giá giảm</option>
                  <option value="">Mã sản phẩm</option>
                </select>
              </div>
            </div>
          </div>

          <!--Table Item Image-->
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
                      <form
                        action="${pageContext.request.contextPath}/AdminManagerServlet"
                        method="post"
                        onsubmit="handleDelete()"
                      >
                        <button class="btnHD btnDel" type="submit">Xóa</button>
                        <input type="hidden" name="itemTypeID" value="${itemType.id}" />
                        <input type="hidden" name="action" value="itemType_btnDelete" />
                      </form>
                      <button
                        class="btnHD btnUpdateItemType"
                        data-itemTypeID="${itemType.id}"
                        data-itemTypeName="${itemType.name}"
                      >
                        Sửa
                      </button>
                      <input type="hidden" name="itemTypeID" value="${itemType.id}" />
                    </div>
                  </td>
                </tr>
              </c:forEach>
            </tbody>
          </table>

          <!--Update Item Type Modal-->
          <div id="update-itemType" class="modal-update flex-center">
            <div class="update-modal">
              <form class="form__update" action="#" method="post">
                <span class="close clsUpdateItemType">&times;</span>

                <h2 class="text-center" style="padding: 16px 0">
                  Cập Nhật Loại Sản Phẩm
                </h2>

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
                  <input type="hidden" name="action" value="itemType_btnUpdate" />
                  <button type="submit">Cập Nhật</button>
                </div>
              </form>
            </div>
          </div>

          <!--Add Item Type Modal-->
          <div id="add-itemType" class="modal-add flex-center modal__addItemType">
            <div class="add-modal">
              <form class="form__add" action="#" method="post">
                <span class="close clsAddItemType">&times;</span>
                <h2 class="text-center" style="padding: 16px 0">Thêm Loại Sản Phẩm</h2>

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
                  <button id="addItemType" type="submit" class="btn submit">Thêm</button>
                </div>
              </form>
            </div>
          </div>
        </div>

        <!--Permission-->
        <div class="permission-data table-data" data-type="permissionList">
          <div class="header-table">
            <!--Add Permission-->
            <div class="AddPermission">
              <button
                class="btnHD btnAdd"
                id="permission_addTrigger"
                style="margin-bottom: 4px"
              >
                Thêm
              </button>
            </div>
            <h2 style="font-size: 30px">Quản lý sản phẩm</h2>
            <div class="sorttable">
              <div class="sort-search">
                <button class="btnHD btngreen btnsearchbox">Tìm kiếm</button>
                <div class="inputsearch">
                  <input type="text" value="" />
                  <button class="btnHD btnsearch">Tìm</button>
                </div>
              </div>
              <div class="sort-box">
                <label for="">Sắp xếp theo:</label>
                <select name="" id="">
                  <option value="" selected>Z-A</option>
                  <option value="">A-Z</option>
                  <option value="">Giá tăng</option>
                  <option value="">Giá giảm</option>
                  <option value="">Mã sản phẩm</option>
                </select>
              </div>
            </div>
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
            <tbody>
              <c:forEach items="${requestScope.permissionList}" var="permission">
                <tr>
                  <td>${permission.id}</td>
                  <td>${permission.name}</td>
                  <td>${permission.level}</td>
                  <td>
                    <div class="flex-center grpbtn">
                      <form
                        action="${pageContext.request.contextPath}/AdminManagerServlet"
                        method="post"
                        onsubmit="handleDelete()"
                      >
                        <button class="btnHD btnDel" type="submit">Xóa</button>
                        <input
                          type="hidden"
                          name="permissionID"
                          value="${permission.id}"
                        />
                        <input type="hidden" name="action" value="permission_btnDelete" />
                      </form>
                      <button
                        class="btnHD btnUpdatePermission"
                        data-permissionID="${permission.id}"
                        data-permissionName="${permission.name}"
                        data-permissionLevel="${permission.level}"
                      >
                        Sửa
                      </button>
                      <input type="hidden" name="permissionID" value="${permission.id}" />
                    </div>
                  </td>
                </tr>
              </c:forEach>
            </tbody>
          </table>

          <!--Update Permission Modal-->
          <div id="update-permission" class="modal-update flex-center">
            <div class="update-modal">
              <form class="form__update" action="#" method="post">
                <span class="close clsUpdatePermission">&times;</span>

                <h2 class="text-center" style="padding: 16px 0">Cập Nhật Quyền</h2>

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
                    name="update_permissionName"
                    value=""
                    placeholder="Nhập vào mức độ truy cập"
                  />
                </div>

                <div class="flex-center">
                  <input type="hidden" name="action" value="permission_btnUpdate" />
                  <button type="submit">Cập Nhật</button>
                </div>
              </form>
            </div>
          </div>

          <!--Add Permission Modal-->
          <div id="add-permission" class="modal-add flex-center modal__addPermission">
            <div class="add-modal">
              <form class="form__add" action="#" method="post">
                <span class="close clsAddPermission">&times;</span>
                <h2 class="text-center" style="padding: 16px 0">Thêm Quyền</h2>

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
                  <label for="add_permissionName">Mức Độ Truy Cập:</label>
                  <input
                    type="number"
                    maxlength="100"
                    id="add_permissionLevel"
                    name="add_permissionName"
                    value=""
                    placeholder="Nhập vào mức độ truy cập"
                  />
                </div>

                <div class="flex-center">
                  <button id="addPermission" type="submit" class="btn submit">
                    Thêm
                  </button>
                </div>
              </form>
            </div>
          </div>
        </div>

        <!--Sale-->
        <div class="saleproduct-data table-data" data-type="saleList">
          <div class="header-table">
            <!--Add Sale Button-->
            <div class="AddSale">
              <button
                class="btnHD btnAdd"
                id="sale_addTrigger"
                style="margin-bottom: 4px"
              >
                Thêm
              </button>
            </div>
            <h2 style="font-size: 30px">Quản lý sản phẩm</h2>
            <div class="sorttable">
              <div class="sort-search">
                <button class="btnHD btngreen btnsearchbox">Tìm kiếm</button>
                <div class="inputsearch">
                  <input type="text" value="" />
                  <button class="btnHD btnsearch">Tìm</button>
                </div>
              </div>
              <div class="sort-box">
                <label for="">Sắp xếp theo:</label>
                <select name="" id="">
                  <option value="" selected>Z-A</option>
                  <option value="">A-Z</option>
                  <option value="">Giá tăng</option>
                  <option value="">Giá giảm</option>
                  <option value="">Mã sản phẩm</option>
                </select>
              </div>
            </div>
          </div>

          <!--Table Sale-->
          <table class="sale-table bang">
            <thead>
              <tr>
                <th>ID</th>
                <th>ID Sản Phẩm</th>
                <th>Tên Sale</th>
                <th>Đang Sale</th>
                <th>Phần trăm giảm</th>
                <th>Action</th>
              </tr>
            </thead>
            <tbody>
              <c:forEach items="${requestScope.saleList}" var="sale">
                <tr>
                  <td>${sale.id}</td>
                  <td>${sale.item.id}</td>
                  <td>${sale.name}</td>
                  <td>${sale.on_sale}</td>
                  <td>${sale.sale_percentage}</td>
                  <td>
                    <div class="flex-center grpbtn">
                      <form
                        action="${pageContext.request.contextPath}/AdminManagerServlet"
                        method="post"
                        onsubmit="handleDelete()"
                      >
                        <button class="btnHD btnDel" type="submit">Xóa</button>
                        <input type="hidden" name="saleID" value="${sale.id}" />
                        <input type="hidden" name="action" value="sale_btnDelete" />
                      </form>
                      <button
                        class="btnHD btnUpdateSale"
                        data-saleID="${sale.id}"
                        data-saleItemID="${sale.item.id}"
                        data-saleName="${sale.name}"
                        data-saleOnSale="${sale.on_sale}"
                        data-salePercentage="${sale.sale_percentage}"
                      >
                        Sửa
                      </button>
                      <input type="hidden" name="saleID" value="${sale.id}" />
                    </div>
                  </td>
                </tr>
              </c:forEach>
            </tbody>
          </table>

          <!--Update Sale Modal-->
          <div id="update-sale" class="modal-update flex-center">
            <div class="update-modal">
              <form class="form__update" action="#" method="post">
                <span class="close clsUpdateSale">&times;</span>

                <h2 class="text-center" style="padding: 16px 0">
                  Cập Nhật Sản Phẩm Sale
                </h2>

                <!--Sale Item ID-->
                <div class="form-grp">
                  <label for="update_saleItemID">Sản Phẩm:</label>
                  <select id="update_saleItemID" name="update_saleItemID">
                    <c:forEach items="${requestScope.itemList}" var="item">
                      <option value="${item.id}">${item.id} : ${item.name}</option>
                    </c:forEach>
                  </select>
                </div>

                <!--Sale Name-->
                <div class="form-grp">
                  <label for="update_saleName">Tên Sale:</label>
                  <input
                    type="text"
                    maxlength="100"
                    id="update_saleName"
                    name="update_saleName"
                    value=""
                    placeholder="Nhập vào tên sale"
                  />
                </div>

                <!--Sale On Sale-->
                <div class="form-grp">
                  <label for="update_saleOnSale">Đang Sale:</label>
                  <input
                    type="checkbox"
                    maxlength="100"
                    id="update_saleOnSale"
                    name="update_saleOnSale"
                    value=""
                    placeholder="Có Đang Sale"
                  />
                </div>

                <!--Sale Percentage-->
                <div class="form-grp">
                  <label for="update_salePercentage">Phần Trăm Sale:</label>
                  <input
                    type="number"
                    maxlength="100"
                    id="update_salePercentage"
                    name="update_salePercentage"
                    value=""
                    placeholder="Nhập vào phần trăm giảm"
                  />
                </div>

                <div class="flex-center">
                  <input type="hidden" name="action" value="sale_btnUpdate" />
                  <button type="submit">Cập Nhật</button>
                </div>
              </form>
            </div>
          </div>

          <!--Add Sale Modal-->
          <div id="add-sale" class="modal-add flex-center modal__addSale">
            <div class="add-modal">
              <form class="form__add" action="#" method="post">
                <span class="close clsAddSale">&times;</span>
                <h2 class="text-center" style="padding: 16px 0">Thêm Sản Phẩm Sale</h2>

                <!--Sale Item ID-->
                <div class="form-grp">
                  <label for="add_saleItemID">Sản Phẩm:</label>
                  <select id="add_saleItemID" name="add_saleItemID">
                    <c:forEach items="${requestScope.itemList}" var="item">
                      <option value="${item.id}">${item.id} : ${item.name}</option>
                    </c:forEach>
                  </select>
                </div>

                <!--Sale Name-->
                <div class="form-grp">
                  <label for="add_saleName">Tên Sale:</label>
                  <input
                    type="text"
                    maxlength="100"
                    id="add_saleName"
                    name="add_saleName"
                    value=""
                    placeholder="Nhập vào tên sale"
                  />
                </div>

                <!--Sale On Sale-->
                <div class="form-grp">
                  <label for="add_saleOnSale">Đang Sale:</label>
                  <input
                    type="checkbox"
                    maxlength="100"
                    id="add_saleOnSale"
                    name="add_saleOnSale"
                    value=""
                    placeholder="Có Đang Sale"
                  />
                </div>

                <!--Sale Percentage-->
                <div class="form-grp">
                  <label for="add_salePercentage">Phần Trăm Sale:</label>
                  <input
                    type="number"
                    maxlength="100"
                    id="add_salePercentage"
                    name="add_salePercentage"
                    value=""
                    placeholder="Nhập vào phần trăm giảm"
                  />
                </div>

                <div class="flex-center">
                  <button id="addSale" type="submit" class="btn submit">Thêm</button>
                </div>
              </form>
            </div>
          </div>
        </div>

        <!--Stock Item-->
        <div class="stockitem-data table-data" data-type="stockItemList">
          <div class="header-table">
            <!--Add StockItem Button-->
            <div class="AddStockItem">
              <button
                class="btnHD btnAdd"
                id="stockItem_addTrigger"
                style="margin-bottom: 4px"
              >
                Thêm
              </button>
            </div>
            <h2 style="font-size: 30px">Quản lý sản phẩm</h2>
            <div class="sorttable">
              <div class="sort-search">
                <button class="btnHD btngreen btnsearchbox">Tìm kiếm</button>
                <div class="inputsearch">
                  <input type="text" value="" />
                  <button class="btnHD btnsearch">Tìm</button>
                </div>
              </div>
              <div class="sort-box">
                <label for="">Sắp xếp theo:</label>
                <select name="" id="">
                  <option value="" selected>Z-A</option>
                  <option value="">A-Z</option>
                  <option value="">Giá tăng</option>
                  <option value="">Giá giảm</option>
                  <option value="">Mã sản phẩm</option>
                </select>
              </div>
            </div>
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
            <tbody>
              <c:forEach items="${requestScope.stockItemList}" var="stockItem">
                <tr>
                  <td scope="row">${stockItem.id}</td>
                  <td>${stockItem.item.id}</td>
                  <td>${stockItem.color}</td>
                  <td>${stockItem.size}</td>
                  <td>${stockItem.amount}</td>
                  <td>
                    <div class="flex-center grpbtn">
                      <form
                        action="${pageContext.request.contextPath}/AdminManagerServlet"
                        method="post"
                        onsubmit="handleDelete()"
                      >
                        <button class="btnHD btnDel" type="submit">Xóa</button>
                        <input type="hidden" name="stockItemID" value="${stockItem.id}" />
                        <input type="hidden" name="action" value="stockItem_btnDelete" />
                      </form>
                      <button
                        class="btnHD btnUpdateStockItem"
                        data-stockItemID="${stockItem.id}"
                        data-stockItemItemID="${stockItem.item.id}"
                        data-stockItemColor="${stockItem.color}"
                        data-stockItemSize="${stockItem.size}"
                        data-stockItemAmount="${stockItem.amount}"
                      >
                        Sửa
                      </button>
                      <input type="hidden" name="stockItemID" value="${stockItem.id}" />
                    </div>
                  </td>
                </tr>
              </c:forEach>
            </tbody>
          </table>

          <!--Update StockItem Modal-->
          <div id="update-stockItem" class="modal-update flex-center">
            <div class="update-modal">
              <form class="form__update" action="#" method="post">
                <span class="close clsUpdateStockItem">&times;</span>

                <h2 class="text-center" style="padding: 16px 0">
                  Cập Nhật Số Lượng Hàng Hóa
                </h2>

                <!--StockItem Item ID-->
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
                    placeholder="Nhập vào số lượng sản phẩm"
                  />
                </div>

                <div class="flex-center">
                  <input type="hidden" name="action" value="stockItem_btnUpdate" />
                  <button type="submit">Cập Nhật</button>
                </div>
              </form>
            </div>
          </div>

          <!--Add StockItem Modal-->
          <div id="add-stockItem" class="modal-add flex-center modal__addStockItem">
            <div class="add-modal">
              <form class="form__add" action="#" method="post">
                <span class="close clsAddStockItem">&times;</span>
                <h2 class="text-center" style="padding: 16px 0">
                  Thêm Số Lượng Hàng Hóa
                </h2>

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
                  <button id="addStockItem" type="submit" class="btn submit">Thêm</button>
                </div>
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
    <script src="${pageContext.request.contextPath}/Views/Admin/vinh_main.js"></script>

    <script>
      function test() {
        $.ajax({
          type: "POST",
          url: contextPath + "/StatisticServlet",
          data: {
            action: "test",
          },
          headers: {
            "X-Requested-With": "XMLHttpRequest",
          },
          success: function (data) {
            console.log(1);
            if (data.success === 1) {
              for (var i = 0; i < data.length; i++) {
                console.log(data[0][0]);
              }
              const productData = [
                { product: "Áo thun xanh dương", sales: 100 },
                { product: "Áo ba lỗ trắng", sales: 200 },
                { product: "Áo khoác dù chống nước", sales: 150 },
              ];
              // Populate table
              const table = document.getElementById("statsTable");
              productData.forEach((data) => {
                const row = table.insertRow(-1);
                const cell1 = row.insertCell(0);
                const cell2 = row.insertCell(1);
                cell1.textContent = data.product;
                cell2.textContent = data.sales;
              });
              // Create chart
              const ctx = document.getElementById("productChart").getContext("2d");
              new Chart(ctx, {
                type: "bar",
                data: {
                  labels: productData.map((data) => data.product),
                  datasets: [
                    {
                      label: "Sales",
                      data: productData.map((data) => data.sales),
                      backgroundColor: "rgba(75, 192, 192, 0.2)",
                      borderColor: "rgba(75, 192, 192, 1)",
                      borderWidth: 1,
                    },
                  ],
                },
                options: {
                  scales: {
                    y: {
                      beginAtZero: true,
                    },
                  },
                },
              });
            } else if (data.success === 0) {
            }
          },
        });
      }
    </script>

    <script>
      const adminManagerContextPath = "${pageContext.request.contextPath}";
    </script>
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
    <script src="${pageContext.request.contextPath}/Views/User/dest/tuh.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  </body>
</html>
