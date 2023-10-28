<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<!DOCTYPE html>
<html lang="en">
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

    <title>Document</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/Views/dest/style.min.css" />

    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/Views/dest/fonts.css" />


</head>
<body>
<header class="header">
    <div class="container">
        <div class="header__left">
            <div class="header__logo">
                <a href="#"><img class="img" src="./img/logo.png" alt="img-logo" /></a>
            </div>
            <nav class="hedaer__navbar">
                <ul class="header__navbar-sitemenu">
                    <li class="item">
                        <a href="#" class="nameitem">Trang chủ</a>
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
                            <li><a href="productlist.html">Giày dép</a></li>
                            <li><a href="productlist.html">Quần Áo</a></li>
                            <li><a href="productlist.html">Phụ Kiện</a></li>
                            <li><a href="productlist.html">Balo</a></li>
                            <li><a href="productlist.html">Mắt Kính</a></li>
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
                            <svg class="arrow" xmlns="http://www.w3.org/2000/svg" height="1em"
                                 viewBox="0 0 512 512">
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
                <form class="header__search--form search--product">
                    <div class="form_group">
                        <input type="text" placeholder="Nhập sản phẩm bạn cần tìm!!" class="inputfeild" />
                    </div>
                    <button class="btn__search">
                        <svg class="icon icon_search" xmlns="http://www.w3.org/2000/svg" height="1em"
                             viewBox="0 0 512 512">
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
            <a href="#" class="header__right--icon loginicon">
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

<!-- ------Container------ -->
<main>
    <section class="productdetail">
        <div class="container">
            <div class="productItem">
                <div class="productImage">
                    <img
                            class="image"
                            src="${pageContext.request.contextPath}/Views/img/ao/ao-1-a.jpg"
                            alt="" />
                </div>
                <div class="productInfo">
                <h3
                        class="productName --h4"

                >Giày Tây Lười Da Phụ Kiện Nguyên Bản A4 2022
                </h3>
                    <p class="productID" >
                        Mã số: #0021024
                    </p>
                    <h5
                            class="sale"
                            >
                        <img

                                src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAACAAAAAgCAYAAABzenr0AAAACXBIWXMAAADsAAAA7AF5KHG9AAAAGXRFWHRTb2Z0d2FyZQB3d3cuaW5rc2NhcGUub3Jnm+48GgAAA8xJREFUWIXFl11oHGUUhp8zM7sbo2kTijbBakqMRYlooYYEBUWKZhPTdLO4UEVbEBWpBQsFIVrtNjZgrJbSitSfChWpaKm5iEEbL2wF0SsllILoTSuktqakTUzs7szsHC82aSbZnexuUuK5+n7O977PzJxvvhlYZDht8Ycm2hLVC11vLMbcjsYbPU8HI55b/78AINoDRByRs0sOoImEqcojAOJx55IDMDZWBlgAIrpLk8kFaS0YQAYHJ4FzAAIPp38e+kBBlgwAQISea214zmmNHygVYlEAoab7DgucnO6r6jY7Gu+ZZ8niAOzHYuvS0c5vpvuSTHoZdM/sLO2yW+LPXHcATSRuUEO+QFjlHy9LXTmVkyv6nrYmbr6uAM545gngDpQ6v7hTvnxdnvRlNu4LxehaxQKoaPtUs9z23MF0NPa2CJXqyet585VHgYL1UDQAKrWIZtvCWpCjqsHpAnXFyJZQhLqs+FwAMtONVGusNdXWuaYkAE0kTH9fhIslAozMiMlrkpHeogHS0fib6fHMwVlAMFySvRJSkIn1HSsFmhBtn2yJ1xQEyJ7t+oqgT+r6zhUzF8E/JQEIa+2Wzl9CIesnsrVmWWQPL3/kFKGlmU1AGCFsW/KxdnRstlNmLUqsJIApCJipVBG9HzjqT8m5A6La5OvEbNscx+A0UA0gt9ZgNDdCeXmuYTiMrK4N5FHRlXPH8tSAVgUJGA80ETr4DubTmwgd2o9ULp+ZjESwundibnw8EABPcrTzAMhk0Hoz1o576BOcbTvQs38iDzbPmO9+FRwb9/2PggGEscIAyh9B6/XSKMa99yB3rUFuXwUXR3zmDm53LzhOsL9ozk7KBTDk1yCBzOEjUFWJ1bUD7/sf8E6fKdocAM8YyoGaO6AbNpTbjnUBqJhXrIQrnwo3bNq3yMDAZf9g7i7o7/9XVT4r2nzPXqytzxPu/5LQ/l6kOqfQs6Hy9VzzvAAAjmoS5UpB8+5ejOZGpOFunBdfxvvtd8xn836LZEB25pvIC1Ax2Pe3CNsLmeM4cNON6Ohl9PwFGD6f//0A+yInjp8pGgAg/G3fEWCvf8zavhWupnB3v3XtmXunfkRWVBH69EPMLU/hHeubKzUQrrC6gnzm/YJVEDsa3wX6BiBSU42OjICbmZ0YCiH1dejwXzA+7p/pC6eszXLy2MSCAKYj3RqLoXIAuK2YfGBCVZKRE1/tE/9hsFAAyG7PtGO9JLAFaAhIOwd8Hg6570p//6VidEv+kwFItXTUC2aDoqsNg6uKMaquDJV9dzzwLRoU/wGxqGF2wiMubwAAAABJRU5ErkJggg=="
                                alt="" />
                        SALE DỌN KHO LÊN HÀNG MỚI
                    </h5>
                    <h6 class="price" >
                        Giá gốc:
                        <span
                               >
                    797,000 </span
                        >đ
                    </h6>
                    <h5
                            class="salePrice"
>                        Giá Sale:
                        <span

                        >557,900</span
                        >
                        đ (Tiết kiệm -239,100 đ)
                    </h5>
                    <p class="textAmount" >
                        số lượng có hạn
                    </p>
                    <div class="amount">
                        <div class="remainAmount">60%</div>
                    </div>
                    <table class="productVariants">
                        <tbody>
                        <tr>
                            <td>Đen, 40</td>
                            <td class="needHover" style="text-align: right"><b>20</b> CH còn</td>
                            <td style="text-align: right">
                                <a class=" select-buy" href="">Chọn mua <i class="fa fa-plus-circle" ></i></a>
                            </td>
                        </tr>
                        <tr>
                            <td>Đen, 41</td>
                            <td class="needHover" style="text-align: right"><b>14</b> CH còn</td>
                            <td style="text-align: right">
                                <a class=" select-buy" href="">Chọn mua <i class="fa fa-plus-circle" ></i></a>
                            </td>
                        </tr>
                        <tr>
                            <td>Đen, 42</td>
                            <td class="needHover" style="text-align: right"><b>19</b> CH còn</td>
                            <td style="text-align: right">
                               <a class=" select-buy" href="">Chọn mua <i class="fa fa-plus-circle" ></i></a>
                            </td>
                        </tr>
                        <tr>
                            <td>Đen, 43</td>
                            <td class="needHover" style="text-align: right"><b>15</b> CH còn</td>
                            <td style="text-align: right">
                                <a class=" select-buy" href="">Chọn mua <i class="fa fa-plus-circle" ></i></a>
                            </td>
                        </tr>
                        </tbody>
                    </table>
                    <div class="tutorials">
                        <button class="btn">Hướng dẫn chọn size</button>
                        <button class="btn">Xem mô tả sản phẩm</button>
                    </div>
                </div>
            </div>
            <div class="productInfoImage">
                <div class="itemImage">
                    <img
                            src="assets/1.jpg"
                            alt="" />
                </div>
                <div class="itemImage">
                    <img src="assets/2.jpg" alt="" />
                </div>
                <div class="itemImage">
                    <img
                            src="assets/3.jpg"
                            alt="" />
                </div>
                <div class="itemImage">
                    <img src="assets/4.jpg" alt="" />
                </div>
                <div class="itemImage"><img src="assets/5.jpg" alt="" /></div>
                <div class="itemImage"><img src="assets/6.jpg" alt="" /></div>
            </div>
        </div>
    </section>
</main>


<!-- Footer -->
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
                <a href="">chính sách đổi trả</a>
                <a href="">chính sách bảo mật</a>
                <a href="">hướng dẫn mua hàng</a>
            </div>
            <div class="footer__right--store">
                <h5 class="title --h5">hệ thống cửa hàng</h5>
                <span class="label">Địa chỉ</span>
                <a  href="mailto:Example@gmail.com">273 an dương vương, phường 1 quận 5, TPHCM</a>

            </div>

        </div>
    </div>

</footer>

<script src="${pageContext.request.contextPath}/Views/dest/jsmain.min.js"></script>
<script src="${pageContext.request.contextPath}/Views/dest/main.js" ></script>
</body>
</html>

