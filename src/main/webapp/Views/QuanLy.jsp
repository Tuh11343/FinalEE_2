<!DOCTYPE html>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0 , maximum-scale=1.0, user-scalable=0" />
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
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/Views/dest/vinh_index.css" />
        <link rel="stylesheet" href="${pageContext.request.contextPath}/Views/dest/vinh_setup.css"/>
    </head>

    <body class="homepage">
        <header class="header">
            <div class="container">
                <div class="header__logo">
                    <a href="#"><img class="img" src="image/3TV-logos.jpeg" alt="img-logo" /></a>
                </div>
                <nav class="hedaer__navbar">
                    <ul class="header__navbar-sitemenu">
                        <li class="item">
                            <a href="#" class="nameitem"
                               >Bộ Sưu Tập

                                <svg class="arrow" xmlns="http://www.w3.org/2000/svg" height="1em" viewBox="0 0 512 512">
                                <path
                                    d="M233.4 406.6c12.5 12.5 32.8 12.5 45.3 0l192-192c12.5-12.5 12.5-32.8 0-45.3s-32.8-12.5-45.3 0L256 338.7 86.6 169.4c-12.5-12.5-32.8-12.5-45.3 0s-12.5 32.8 0 45.3l192 192z"
                                    />
                                </svg>
                            </a>
                            <ul class="dropmenu">
                                <li><a href="#">Giày dép</a></li>
                                <li><a href="#">Quần Áo</a></li>
                                <li><a href="#">Phụ Kiện</a></li>
                                <li><a href="#">Balo</a></li>
                                <li><a href="#">Mắt Kính</a></li>
                            </ul>
                        </li>
                        <li class="item">
                            <a href="#" class="nameitem"
                               >Sản Phẩm

                                <svg class="arrow" class="arrow" xmlns="http://www.w3.org/2000/svg" height="1em" viewBox="0 0 512 512">
                                <path
                                    d="M233.4 406.6c12.5 12.5 32.8 12.5 45.3 0l192-192c12.5-12.5 12.5-32.8 0-45.3s-32.8-12.5-45.3 0L256 338.7 86.6 169.4c-12.5-12.5-32.8-12.5-45.3 0s-12.5 32.8 0 45.3l192 192z"
                                    />
                                </svg>
                            </a>
                            <ul class="dropmenu">
                                <li><a href="#">Giày dép</a></li>
                                <li><a href="#">Quần Áo</a></li>
                                <li><a href="#">Phụ Kiện</a></li>
                                <li><a href="#">Balo</a></li>
                                <li><a href="#">Mắt Kính</a></li>
                            </ul>
                        </li>
                        <li class="item">
                            <a href="#" class="nameitem"
                               >Liên Hệ

                                <svg class="arrow" xmlns="http://www.w3.org/2000/svg" height="1em" viewBox="0 0 512 512">
                                <path
                                    d="M233.4 406.6c12.5 12.5 32.8 12.5 45.3 0l192-192c12.5-12.5 12.5-32.8 0-45.3s-32.8-12.5-45.3 0L256 338.7 86.6 169.4c-12.5-12.5-32.8-12.5-45.3 0s-12.5 32.8 0 45.3l192 192z"
                                    />
                                </svg>
                            </a>
                            <ul class="dropmenu">
                                <li><a href="#">Giày dép</a></li>
                                <li><a href="#">Quần Áo</a></li>
                                <li><a href="#">Phụ Kiện</a></li>
                                <li><a href="#">Balo</a></li>
                                <li><a href="#">Mắt Kính</a></li>
                            </ul>
                        </li>
                    </ul>
                </nav>
                <div class="header__right">
                    <a href="#" class="header__right--icon">
                        <svg xmlns="http://www.w3.org/2000/svg" height="1em" viewBox="0 0 512 512">
                        <path
                            d="M416 208c0 45.9-14.9 88.3-40 122.7L502.6 457.4c12.5 12.5 12.5 32.8 0 45.3s-32.8 12.5-45.3 0L330.7 376c-34.4 25.2-76.8 40-122.7 40C93.1 416 0 322.9 0 208S93.1 0 208 0S416 93.1 416 208zM208 352a144 144 0 1 0 0-288 144 144 0 1 0 0 288z"
                            />
                        </svg>
                    </a>
                    <a href="#" class="header__right--icon">
                        <svg xmlns="http://www.w3.org/2000/svg" height="1em" viewBox="0 0 512 512">
                        <path
                            d="M256 288A144 144 0 1 0 256 0a144 144 0 1 0 0 288zm-94.7 32C72.2 320 0 392.2 0 481.3c0 17 13.8 30.7 30.7 30.7H481.3c17 0 30.7-13.8 30.7-30.7C512 392.2 439.8 320 350.7 320H161.3z"
                            />
                        </svg>
                    </a>
                    <a href="#" class="header__right--icon">
                        <svg xmlns="http://www.w3.org/2000/svg" height="1em" viewBox="0 0 448 512">
                        <path
                            d="M160 112c0-35.3 28.7-64 64-64s64 28.7 64 64v48H160V112zm-48 48H48c-26.5 0-48 21.5-48 48V416c0 53 43 96 96 96H352c53 0 96-43 96-96V208c0-26.5-21.5-48-48-48H336V112C336 50.1 285.9 0 224 0S112 50.1 112 112v48zm24 48a24 24 0 1 1 0 48 24 24 0 1 1 0-48zm152 24a24 24 0 1 1 48 0 24 24 0 1 1 -48 0z"
                            />
                        </svg>
                        <span class="number">1</span>
                    </a>
                </div>
            </div>
        </header>

        <!--<div class="container" >-->
        <main class='main'>
            <div class="left">
                <div class="accordion accordion-flush" id="accordionFlushExample">

                    <!--Account-->
                    <div class="accordion-item">
                        <h2 class="accordion-header" id="flush-headingOne">
                            <button class="accordion-button collapsed text" data-type="accountlist" type="button" data-bs-toggle="collapse" data-bs-target="#flush-collapseOne" aria-expanded="false" aria-controls="flush-collapseOne">
                                Quản lý tài khoản
                            </button>
                        </h2>
                        <div id="flush-collapseOne" class="accordion-collapse collapse" aria-labelledby="flush-headingOne" data-bs-parent="#accordionFlushExample">
                            <div class="accordion-body">Đây là nơi quản lý <code>tài khoản</code></div>
                        </div>
                    </div>

                    <!--Item-->
                    <div class="accordion-item">
                        <h2 class="accordion-header" id="flush-headingTwo">
                            <button class="accordion-button collapsed text" data-type="itemlist" type="button" data-bs-toggle="collapse" data-bs-target="#flush-collapseTwo" aria-expanded="false" aria-controls="flush-collapseTwo">
                                Quản lý sản phẩm
                            </button>
                        </h2>
                        <div id="flush-collapseTwo" class="accordion-collapse collapse" aria-labelledby="flush-headingTwo" data-bs-parent="#accordionFlushExample">
                            <div class="accordion-body">Đây là nơi quản lý <code>sản phẩm</code></div>
                        </div>
                    </div>

                    <!--Statistic-->
                    <div class="accordion-item">
                        <h2 class="accordion-header" id="flush-headingThree">
                            <button class="accordion-button collapsed text" data-type="statistics" type="button" data-bs-toggle="collapse" data-bs-target="#flush-collapseThree" aria-expanded="false" aria-controls="flush-collapseThree">
                                Quản lý thống kê
                            </button>
                        </h2>
                        <div id="flush-collapseThree" class="accordion-collapse collapse" aria-labelledby="flush-headingThree" data-bs-parent="#accordionFlushExample">
                            <div class="accordion-body">Đây là nơi quản lý <code>thống kê</code></div>
                        </div>
                    </div>
                </div>
            </div>


            <!--Center-->
            <div class="center">

                <!--Table Account-->
                <div class="account-data table-data" data-type="accountlist">
                    <h2 style="font-size:30px">Quản lý tài khoản</h2>

                    <!--Add Account Button-->
                    <div class="btnAdd">
                        <button class="btnHD btnAdd" id="account_addTrigger" style="margin-bottom: 4px;">Thêm</button>
                    </div>

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

                            <c:forEach items="${accountList}" var="account">
                                <tr>
                                    <td>${account.id}</td>
                                    <td>${account.permission_id}</td>
                                    <td>${account.customer_id}</td>
                                    <td>${account.name}</td>
                                    <td>${account.password}</td>
                                    <td>
                                        <div class="flex-center grpbtn">
                                            <form action="/AdminManagerServlet" method="post" onsubmit="handleDelete()">
                                                <button class="btnHD btnDel" type="submit">Xóa</button>
                                                <input type="hidden" name="accountID" value="${account.id}"/>
                                                <input type="hidden" name="action" value="account_btnDelete"/>
                                            </form>
                                            <button class="btnHD btnUpdateUser" id="account_updateTrigger"
                                                    data-customerID="${account.customer_id}" data-permissionID="${account.permission_id}" 
                                                    data-accountName="${account.name}" data-accountPassword="${account.password}"
                                                    >Sửa</button>
                                            <input type="hidden" name="accountID" value="${account.id}"/>
                                        </div>
                                    </td>
                                </tr>
                            </c:forEach>
                        </thead>
                    </table>

                    <!--Update Account-->
                    <div id="update-user" class="updatemodal flex-center">
                        <div class="update-modal">
                            <form class="form__update" action="/AdminManagerServlet" method="post">
                                <span class="close">&times;</span>
                                <h2 class="text-center" style="padding: 16px 0">CẬP NHẬT NGƯỜI DÙNG</h2>

                                <!--Account Name-->
                                <div class="form-grp">
                                    <label for="update_accountNameID">Tên tài khoản:</label>
                                    <input type="text" maxlength="100" id="update_accountNameID" name="update_accountName" value=""
                                           placeholder="Nhập vào tài khoản" />
                                </div>

                                <!--Account Password-->
                                <div class="form-grp">
                                    <label for="update_accountPasswordID">Mật khẩu:</label>
                                    <input type="password" maxlength="100" id="update_accountPasswordID" name="update_accountPassword" value=""
                                           placeholder="Nhập vào mật khẩu" />
                                </div>

                                <!--Account Permission-->
                                <div class="form-grp">
                                    <label for="update_label_permissionID">Permission:</label>
                                    <select id="update_label_permissionID" name="update_accountPermissionID">
                                        <c:forEach items="${permissionList}" var="permission">
                                            <option value="${permission.id}">${permission.name}</option>
                                        </c:forEach>
                                    </select>
                                </div>

                                <!--Account Customer-->
                                <div class="form-grp">
                                    <label for="update_label_customerID">Customer:</label>
                                    <select id="update_label_customerID" name="update_accountCustomerID">
                                        <c:forEach items="${customerList}" var="customer">
                                            <option value="${customer.id}">${customer.id} : ${customer.name}</option>
                                        </c:forEach>
                                    </select>

                                </div>

                                <!--Update Button-->
                                <div class="flex-center">
                                    <input type="hidden" name="action" value="account_btnUpdate"/>
                                    <button type="submit">Cập nhật</button>
                                </div>

                            </form>
                        </div>   
                    </div>

                    <!--Add Account-->
                    <div id="modal-add-account" class="addmodal flex-center">
                        <div class="add-modal">
                            <form class="form__add" action="/AdminManagerServlet" method="post">
                                <span class="close clsadduser">&times;</span>
                                <h2 class="text-center" style="padding: 16px 0">THÊM NGƯỜI DÙNG</h2>

                                <!--Account Name-->
                                <div class="form-grp">
                                    <label for="accountNameID">Tên tài khoản:</label>
                                    <input type="text" maxlength="100" id="accountNameID" name="add_accountName" value=""
                                           placeholder="Nhập vào tài khoản" />

                                    <!--Account Password-->
                                </div>
                                <div class="form-grp">
                                    <label for="accountPasswordID">Mật khẩu:</label>
                                    <input type="password" maxlength="100" id="accountPasswordID" name="add_accountPassword" value=""
                                           placeholder="Nhập vào mật khẩu" />

                                </div>

                                <!--Account Permission-->
                                <div class="form-grp">
                                    <label for="label_permissionID">Permission:</label>
                                    <select id="label_permissionID" name="add_accountPermissionID">
                                        <c:forEach items="${permissionList}" var="permission">
                                            <option value="${permission.id}">${permission.name}</option>
                                        </c:forEach>
                                    </select>

                                </div>

                                <!--Account Customer-->
                                <div class="form-grp">
                                    <label for="label_customerID">Customer:</label>
                                    <select id="label_customerID" name="add_accountCustomerID">
                                        <c:forEach items="${customerList}" var="customer">
                                            <option value="${customer.id}">${customer.id} : ${customer.name}</option>
                                        </c:forEach>
                                    </select>

                                </div>

                                <!--Add Button-->
                                <div class="flex-center">
                                    <button type="submit">Thêm</button>
                                    <input type="hidden" name="action" value="account_btnAdd"/>
                                </div>

                            </form>

                        </div>   
                    </div>

                </div>

                <!--Table Item-->
                <div class="product-data table-data" data-type="itemlist">
                    <h2 style="font-size:30px">Quản lý sản phẩm</h2>

                    <!--Add Item Button-->
                    <div class="btnAdd">
                        <button class="btnHD btnAdd" id="item_addTrigger" style="margin-bottom: 4px;">Thêm</button>
                    </div>

                    <!--Table Item-->
                    <table class="item-table bang">
                        <thead>
                            <tr>
                                <th>ID</th>
                                <th>Tên Sản Phẩm</th>
                                <th>Loại Sản Phẩm</th>
                                <th>Bộ Sưu Tập</th>
                                <th>Vật Liệu</th>
                                <th>Sản Phẩm Mới</th>
                                <th>Sản Phẩm Hot</th>
                                <th>Giá</th>
                                <th>Năm Sản Xuất</th>
                                <th>Action</th>
                            </tr>

                            <c:forEach items="${itemList}" var="item">
                                <tr>
                                    <td>${item.id}</td>
                                    <td>${item.name}</td>
                                    <td>${item.item_type_id}</td>
                                    <td>${item.item_collection_id}</td>
                                    <td>${item.item_material_id}</td>
                                    
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
                                            <form action="/AdminManagerServlet" method="post" onsubmit="handleDelete()">
                                                <button class="btnHD btnDel" type="submit">Xóa</button>
                                                <input type="hidden" name="itemID" value="${item.id}"/>
                                                <input type="hidden" name="action" value="item_btnDelete"/>
                                            </form>
                                            <button class="btnHD btnUpdateItem" 
                                                    data-itemID="${item.id}" data-itemName="${item.name}" 
                                                    data-itemTypeID="${item.item_type_id}" data-itemCollectionID="${item.item_collection_id}"
                                                    data-itemMaterialID="${item.item_material_id}" data-itemIsNew="${item.is_new}"
                                                    data-itemIsHot="${item.is_hot}" data-itemPrice="${item.price}"
                                                    data-itemYearProduce="${item.year_produce}"
                                                    >Sửa</button>
                                            <input type="hidden" name="itemID" value="${item.id}"/>
                                        </div>
                                    </td>
                                </tr>
                            </c:forEach>
                        </thead>
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
                                    <input type="text" maxlength="100" id="update_itemNameID" name="update_itemName" value=""
                                           placeholder="Nhập vào tên sản phẩm" />
                                </div>

                                <!--Item Type-->
                                <div class="form-grp">
                                    <label for="update_label_itemTypeID">Loại sản phẩm:</label>
                                    <select id="update_label_itemTypeID" name="update_itemTypeID">
                                        <c:forEach items="${itemTypeList}" var="itemType">
                                            <option value="${itemType.id}">${itemType.name}</option>
                                        </c:forEach>
                                    </select>
                                </div>

                                <!--Item Collection-->
                                <div class="form-grp">
                                    <label for="update_label_itemCollectionID">Bộ sưu tập:</label>
                                    <select id="update_label_itemCollectionID" name="update_itemCollectionID">
                                        <c:forEach items="${itemCollectionList}" var="itemCollection">
                                            <option value="${itemCollection.id}">${itemCollection.name}</option>
                                        </c:forEach>
                                    </select>
                                </div>

                                <!--Item Material-->
                                <div class="form-grp">
                                    <label for="update_label_itemMaterialID">Thành phần:</label>
                                    <select id="update_label_itemMaterialID" name="update_itemMaterialID">
                                        <c:forEach items="${itemMaterialList}" var="itemMaterial">
                                            <option value="${itemMaterial.id}">${itemMaterial.id} : ${itemMaterial.name}</option>
                                        </c:forEach>
                                    </select>
                                </div>

                                <!--Is New-->
                                <div class="form-grp">
                                    <label for="update_itemIsNewID">Sản phẩm mới:</label>
                                    <input type="checkbox" id="update_itemIsNewID" name="update_itemIsNew" value=""/>
                                </div>

                                <!--Is Hot-->
                                <div class="form-grp">
                                    <label for="update_itemIsHotID">Sản phẩm hot:</label>
                                    <input type="checkbox" id="update_itemIsHotID" name="update_itemIsHot" value=""/>
                                </div>

                                <!--Item Price-->
                                <div class="form-grp">
                                    <label for="update_itemPriceID">Giá sản phẩm:</label>
                                    <input type="number" id="update_itemPriceID" name="update_itemPrice" value=""
                                           placeholder="Nhập vào giá sản phẩm" />
                                </div>

                                <!--Item Year Produce-->
                                <div class="form-grp">
                                    <label for="update_itemYearProduceID">Năm sản xuất:</label>
                                    <input type="number" id="update_itemYearProduceID" name="update_itemYearProduce" value=""
                                           placeholder="Nhập vào năm sản xuất" />
                                </div>

                                <div class="flex-center">
                                    <input type="hidden" name="action" value="item_btnUpdate"/>
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
                                    <input type="text" maxlength="100" id="add_itemNameID" name="add_itemName" value=""
                                           placeholder="Nhập vào tên sản phẩm" />
                                </div>

                                <!--Item Type-->
                                <div class="form-grp">
                                    <label for="add_label_itemTypeID">Loại sản phẩm:</label>
                                    <select id="add_label_itemTypeID" name="add_itemTypeID">
                                        <c:forEach items="${itemTypeList}" var="itemType">
                                            <option value="${itemType.id}">${itemType.name}</option>
                                        </c:forEach>
                                    </select>
                                </div>

                                <!--Item Collection-->
                                <div class="form-grp">
                                    <label for="add_label_itemCollectionID">Bộ sưu tập:</label>
                                    <select id="add_label_itemCollectionID" name="add_itemCollectionID">
                                        <c:forEach items="${itemCollectionList}" var="itemCollection">
                                            <option value="${itemCollection.id}">${itemCollection.name}</option>
                                        </c:forEach>
                                    </select>
                                </div>

                                <!--Item Material-->
                                <div class="form-grp">
                                    <label for="add_label_itemMaterialID">Thành phần:</label>
                                    <select id="add_label_itemMaterialID" name="add_itemMaterialID">
                                        <c:forEach items="${itemMaterialList}" var="itemMaterial">
                                            <option value="${itemMaterial.id}">${itemMaterial.id} : ${itemMaterial.name}</option>
                                        </c:forEach>
                                    </select>
                                </div>

                                <!--Is New-->
                                <div class="form-grp">
                                    <label for="add_label_isNewID">Sản phẩm mới:</label>
                                    <input type="checkbox" id="add_label_isNewID" name="add_itemIsNew" value=""/>
                                </div>

                                <!--Is Hot-->
                                <div class="form-grp">
                                    <label for="add_label_isNewID">Sản phẩm hot:</label>
                                    <input type="checkbox" id="add_label_isHotID" name="add_itemIsHot" value=""/>
                                </div>

                                <!--Item Price-->
                                <div class="form-grp">
                                    <label for="add_itemPriceID">Giá sản phẩm:</label>
                                    <input type="number" id="add_itemPriceID" name="add_itemPrice" value=""
                                           placeholder="Nhập vào giá sản phẩm" />
                                </div>

                                <!--Item Year Produce-->
                                <div class="form-grp">
                                    <label for="add_itemYearProduceID">Năm sản xuất:</label>
                                    <input type="number" id="add_itemYearProduceID" name="add_itemYearProduce" value=""
                                           placeholder="Nhập vào năm sản xuất" />
                                </div>

                                <div class="flex-center">
                                    <button id="addproducts" type="submit" class="btn submit">Thêm</button>
                                </div>

                            </form>

                        </div>   
                    </div>
                </div>

            </div>




            <!--            <div class="product-data table-data" data-type="productlist">
                            <h2 style="font-size:30px;">Quản lý sản phẩm</h2>
                            
                            <div class="btnAdd" id="Addbtn">
                                <button class="btnHD btnAdd" id="AddProductsBtn" type="add">Thêm</button>
                            </div>
                            
                            <table class="product-table bang" >
                                <thead>
                                    <tr data-type="product">
                                        <th>Mã sản phẩm</th>
                                        <th>Tên sản phẩm</th>
                                        <th>Loại</th>
                                        <th>Giá sản phẩm</th>
                                        <th>Hình ảnh</th>
                                        <th>Mô tả</th>
                                        <th>Hành động</th>
                                    </tr>
                                    <tr data-type="product">
                                        <td data-type="product">1</td>
                                        <td data-type="product">Áo thun xanh dương</td>
                                        <td data-type="product">
                                            <select>
                                                <option value="">Loại sản phẩm</option>
                                                <option value="">Áo thun</option>
                                                <option value="">Áo khoác</option>                                
                                                <option value="">Áo ba lỗ</option>
            
                                            </select>
                                        </td>
                                        <td data-type="product">250000VND</td>
                                        <td data-type="product">
                                                                        <img class="img2" src="image/áo thun-1.jpg" alt="img-logo" />
                                            <input 
                                                type="file"
                                                class="imgproduct"
                                                name="imgproduct"
                                                accept="image/*"
                                                />
                                        </td>
                                        <td data-type="product">Chưa có</td>
                                        <td>
                                            <div class="flex-center grpbtn">
                                                <button class="btnHD btnDel" type="delete">Xóa</button>
                                                <button class="btnHD btnUpdateProducts" data-type="product" type="update">Sửa</button>
                                                <button class="btnHD btnSave" data-type="product" type="save">Lưu</button>
                                            </div>
                                        </td>
                                    </tr>
                                    <tr data-type="product">
                                        <td data-type="product">1</td>
                                        <td data-type="product">Áo thun xanh dương</td>
                                        <td data-type="product">
                                            <select>
                                                <option value="">Loại sản phẩm</option>
                                                <option value="">Áo thun</option>
                                                <option value="">Áo khoác</option>                                
                                                <option value="">Áo ba lỗ</option>
            
                                            </select>
                                        </td>
                                        <td data-type="product">250000VND</td>
                                        <td data-type="product">
                                                                        <img class="img2" src="image/áo thun-1.jpg" alt="img-logo" />
                                            <input 
                                                type="file"
                                                class="imgproduct"
                                                name="imgproduct"
                                                accept="image/*"
                                                />
                                        </td>
                                        <td data-type="product">Chưa có</td>
                                        <td>
                                            <div class="flex-center grpbtn">
                                                <button class="btnHD btnDel" type="delete">Xóa</button>
                                                <button class="btnHD btnUpdateProducts"  data-type="product" type="update">Sửa</button>
                                                <button class="btnHD btnSave" data-type="product" type="save">Lưu</button>
                                            </div>
                                        </td>
                                    </tr>
                                    <tr data-type="product">
                                        <td data-type="product">1</td>
                                        <td data-type="product">Áo thun xanh dương</td>
                                        <td data-type="product">
                                            <select>
                                                <option value="">Loại sản phẩm</option>
                                                <option value="">Áo thun</option>
                                                <option value="">Áo khoác</option>                                
                                                <option value="">Áo ba lỗ</option>
            
                                            </select>
                                        </td>
                                        <td>250000VND</td>
                                        <td data-type="product">
                                                                        <img class="img2" src="image/áo thun-1.jpg" alt="img-logo" />
                                            <input 
                                                type="file"
                                                class="imgproduct"
                                                name="imgproduct"
                                                accept="image/*"
                                                />
                                        </td>
                                        <td data-type="product">Chưa có</td>
                                        <td>
                                            <div class="flex-center grpbtn">
                                                <button class="btnHD btnDel" type="delete">Xóa</button>
                                                <button class="btnHD btnUpdateProducts" data-type="product" type="update">Sửa</button>
                                                <button class="btnHD btnSave"  data-type="product" type="save">Lưu</button>
                                            </div>
                                        </td>
                                    </tr>
                                    <tr data-type="product">
                                        <td data-type="product">1</td>
                                        <td data-type="product">Áo thun xanh dương</td>
                                        <td data-type="product">
                                            <select>
                                                <option value="">Loại sản phẩm</option>
                                                <option value="">Áo thun</option>
                                                <option value="">Áo khoác</option>                                
                                                <option value="">Áo ba lỗ</option>
            
                                            </select>
                                        </td>
                                        <td data-type="product">250000VND</td>
                                        <td data-type="product">
                                                                        <img class="img2" src="image/áo thun-1.jpg" alt="img-logo" />
                                            <input 
                                                type="file"
                                                class="imgproduct"
                                                name="imgproduct"
                                                accept="image/*"
                                                />
                                        </td>
                                        <td data-type="product">Chưa có</td>
                                        <td>
                                            <div class="flex-center grpbtn">
                                                <button class="btnHD btnDel" type="delete">Xóa</button>
                                                <button class="btnHD btnUpdateProducts"  data-type="product" type="update">Sửa</button>
                                                <button class="btnHD btnSave" data-type="product" type="save">Lưu</button>
                                            </div>
                                        </td>
                                    </tr>
                                    <tr data-type="product">
                                        <td data-type="product">1</td>
                                        <td data-type="product">Áo thun xanh dương</td>
                                        <td data-type="product">
                                            <select>
                                                <option value="">Loại sản phẩm</option>
                                                <option value="">Áo thun</option>
                                                <option value="">Áo khoác</option>                                
                                                <option value="">Áo ba lỗ</option>
            
                                            </select>
                                        </td>
                                        <td data-type="product">250000VND</td>
                                        <td data-type="product">
                                                                        <img class="img2" src="image/áo thun-1.jpg" alt="img-logo" />
                                            <input 
                                                type="file"
                                                class="imgproduct"
                                                name="imgproduct"
                                                accept="image/*"
                                                />
                                        </td>
                                        <td data-type="product">Chưa có</td>
                                        <td>
                                            <div class="flex-center grpbtn">
                                                <button class="btnHD btnDel" type="delete">Xóa</button>
                                                <button class="btnHD btnUpdateProducts"  data-type="product" type="update">Sửa</button>
                                                <button class="btnHD btnSave" data-type="product" type="save">Lưu</button>
                                            </div>
                                        </td>
                                    </tr>
                                </thead>
                            </table>
            
            
            
                            <div class="product-data table-data" data-type="statistics">
                                <h2>Thống kê sản phẩm</h2>
                                <table id="statsTable" class="product-table bang">
                                    <tr>
                                        <th>Sản phẩm</th>
                                        <th>Số lượng đã bán</th>
                                    </tr>
                                </table>
                                <canvas id="productChart"></canvas>
                                <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
                                <script src="main.js"></script>
            
                            </div>
                        </div>-->











        </div>
    </main>

</div>
<footer class="footer">
    <div class="container"></div>
</footer>

<!-- Javascript -->
<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
<script src="${pageContext.request.contextPath}/Views/dest/vinh_main.js"></script>
</body>
</html>
