<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>

    <head>
        <meta charset="UTF-8" />
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

        <link rel="stylesheet" href="dest/style.min.css" />
        <link rel="stylesheet" href="dest/fonts.css" />
    </head>
    <body class="homepage">
        <form action="/ItemServlet" method="post">
            <h1>Product List</h1>
            <table>
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Email</th>
                </tr>
                <c:forEach items="${itemList}" var="item">
                    <tr>
                        <td>${item.id}</td>
                        <td>${item.name}</td>
                    </tr>
                </c:forEach>
            </table>
            <br>
            <input type="submit" value="Nhan de load">
        </form>


        <form action="/AddItemServlet" method="post">
            Name:<input name="name" type="text"><br>
            Item Type ID:<input name="itemTypeID" type="text"><br>
            Is New:<input name="isNew" type="checkbox"><br>
            Is Hot:<input name="isHot" type="checkbox"><br>
            Price:<input name="price" type="text"><br>
            Amount:<input name="amount" type="text"><br>
            Year Produce:<input name="yearProduce" type="text"><br>
            <button>Thêm</button>
        </form>

    </body>
</html>
