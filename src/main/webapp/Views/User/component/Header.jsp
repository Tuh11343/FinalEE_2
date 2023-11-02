<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
                                <path d="M233.4 406.6c12.5 12.5 32.8 12.5 45.3 0l192-192c12.5-12.5 12.5-32.8 0-45.3s-32.8-12.5-45.3 0L256 338.7 86.6 169.4c-12.5-12.5-32.8-12.5-45.3 0s-12.5 32.8 0 45.3l192 192z" />
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
                                <path d="M233.4 406.6c12.5 12.5 32.8 12.5 45.3 0l192-192c12.5-12.5 12.5-32.8 0-45.3s-32.8-12.5-45.3 0L256 338.7 86.6 169.4c-12.5-12.5-32.8-12.5-45.3 0s-12.5 32.8 0 45.3l192 192z" />
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
                            <path d="M416 208c0 45.9-14.9 88.3-40 122.7L502.6 457.4c12.5 12.5 12.5 32.8 0 45.3s-32.8 12.5-45.3 0L330.7 376c-34.4 25.2-76.8 40-122.7 40C93.1 416 0 322.9 0 208S93.1 0 208 0S416 93.1 416 208zM208 352a144 144 0 1 0 0-288 144 144 0 1 0 0 288z" />
                        </svg>
                    </button>
                </form>
            </div>
            <a href="#" class="header__right--icon btnshow_search">
                <svg class="icon icon_search" xmlns="http://www.w3.org/2000/svg" height="1em" viewBox="0 0 512 512">
                    <path d="M416 208c0 45.9-14.9 88.3-40 122.7L502.6 457.4c12.5 12.5 12.5 32.8 0 45.3s-32.8 12.5-45.3 0L330.7 376c-34.4 25.2-76.8 40-122.7 40C93.1 416 0 322.9 0 208S93.1 0 208 0S416 93.1 416 208zM208 352a144 144 0 1 0 0-288 144 144 0 1 0 0 288z" />
                </svg>
                <svg class="icon icon_close" xmlns="http://www.w3.org/2000/svg" height="1em" viewBox="0 0 384 512">
                    <path
                            d="M342.6 150.6c12.5-12.5 12.5-32.8 0-45.3s-32.8-12.5-45.3 0L192 210.7 86.6 105.4c-12.5-12.5-32.8-12.5-45.3 0s-12.5 32.8 0 45.3L146.7 256 41.4 361.4c-12.5 12.5-12.5 32.8 0 45.3s32.8 12.5 45.3 0L192 301.3 297.4 406.6c12.5 12.5 32.8 12.5 45.3 0s12.5-32.8 0-45.3L237.3 256 342.6 150.6z" />
                </svg>
            </a>
            <a href="#" class="header__right--icon  btn-user">
                <!--                <svg xmlns="http://www.w3.org/2000/svg" height="1em" viewBox="0 0 512 512">-->
                <!--                    <path-->
                <!--                            d="M256 288A144 144 0 1 0 256 0a144 144 0 1 0 0 288zm-94.7 32C72.2 320 0 392.2 0 481.3c0 17 13.8 30.7 30.7 30.7H481.3c17 0 30.7-13.8 30.7-30.7C512 392.2 439.8 320 350.7 320H161.3z" />-->
                <!--                </svg>-->
                Đăng Nhập
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