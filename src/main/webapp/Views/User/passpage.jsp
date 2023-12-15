<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib
        uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib
        uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta
            name="viewport"
            content="width=device-width, initial-scale=1.0 , maximum-scale=1.0, user-scalable=0"
    />
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <title>Bán Giày</title>

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
    <link
            rel="stylesheet"
            href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css"
    />
    <link
            rel="stylesheet"
            href="${pageContext.request.contextPath}/Views/User/dest/style.min.css"
    />
    <link
            rel="stylesheet"
            href="${pageContext.request.contextPath}/Views/User/dest/fonts.css"
    />
</head>

<jsp:include page="component/Header.jsp"/>
<jsp:include page="component/ModalLogin.jsp"/>

<body class="forgetpasspage">
<main class="main">
    <section class="forgetform">
        <div class="container">
            <div class="forgetform__panel">
                <h4 class="heading --h4">Nhập mật khẩu của bạn</h4>
                <p style="margin-bottom: 10px">Nhập lại mật khẩu bạn thay đổi</p>
                <form class="formforget" onsubmit="return validateEmail()">
                    <div class="form-gr">
                        <label class="label" for="password">Mật khẩu</label>
                        <input
                                class="input-form"
                                type="password"
                                id="password-forget"
                                placeholder="Nhập mật khẩu"
                                name="password-forget"
                                value=""
                        />
                        <span
                                class="show-password-btn"
                                onclick="togglePasswordVisibility('password-forget')"
                        ><svg
                                xmlns="http://www.w3.org/2000/svg"
                                height="16"
                                width="18"
                                viewBox="0 0 576 512"
                        >
                    <path
                            d="M288 32c-80.8 0-145.5 36.8-192.6 80.6C48.6 156 17.3 208 2.5 243.7c-3.3 7.9-3.3 16.7 0 24.6C17.3 304 48.6 356 95.4 399.4C142.5 443.2 207.2 480 288 480s145.5-36.8 192.6-80.6c46.8-43.5 78.1-95.4 93-131.1c3.3-7.9 3.3-16.7 0-24.6c-14.9-35.7-46.2-87.7-93-131.1C433.5 68.8 368.8 32 288 32zM144 256a144 144 0 1 1 288 0 144 144 0 1 1 -288 0zm144-64c0 35.3-28.7 64-64 64c-7.1 0-13.9-1.2-20.3-3.3c-5.5-1.8-11.9 1.6-11.7 7.4c.3 6.9 1.3 13.8 3.2 20.7c13.7 51.2 66.4 81.6 117.6 67.9s81.6-66.4 67.9-117.6c-11.1-41.5-47.8-69.4-88.6-71.1c-5.8-.2-9.2 6.1-7.4 11.7c2.1 6.4 3.3 13.2 3.3 20.3z"
                    /></svg
                        ></span>
                        <p class="message-error error-passwordforget"></p>
                    </div>
                    <div class="form-gr">
                        <label class="label" for="password">Nhập lại mật khẩu</label>
                        <input
                                class="input-form"
                                type="password"
                                id="repassword-forget"
                                placeholder="Nhập mật khẩu"
                                name="repassword-forget"
                                value=""
                        />
                        <span
                                class="show-password-btn"
                                onclick="togglePasswordVisibility('repassword-forget')"
                        ><svg
                                xmlns="http://www.w3.org/2000/svg"
                                height="16"
                                width="18"
                                viewBox="0 0 576 512"
                        >
                    <path
                            d="M288 32c-80.8 0-145.5 36.8-192.6 80.6C48.6 156 17.3 208 2.5 243.7c-3.3 7.9-3.3 16.7 0 24.6C17.3 304 48.6 356 95.4 399.4C142.5 443.2 207.2 480 288 480s145.5-36.8 192.6-80.6c46.8-43.5 78.1-95.4 93-131.1c3.3-7.9 3.3-16.7 0-24.6c-14.9-35.7-46.2-87.7-93-131.1C433.5 68.8 368.8 32 288 32zM144 256a144 144 0 1 1 288 0 144 144 0 1 1 -288 0zm144-64c0 35.3-28.7 64-64 64c-7.1 0-13.9-1.2-20.3-3.3c-5.5-1.8-11.9 1.6-11.7 7.4c.3 6.9 1.3 13.8 3.2 20.7c13.7 51.2 66.4 81.6 117.6 67.9s81.6-66.4 67.9-117.6c-11.1-41.5-47.8-69.4-88.6-71.1c-5.8-.2-9.2 6.1-7.4 11.7c2.1 6.4 3.3 13.2 3.3 20.3z"
                    /></svg
                        ></span>
                        <p class="message-error error-repasswordforget"></p>
                    </div>
                    <button
                            type="button"
                            onclick="return validatePassword()"
                            class="btn btnsend"
                    >
                        Gửi
                    </button>
                    <input type="hidden" value="${requestScope.accountID}" name="accountID" id="accountID"/>
                </form>
            </div>
        </div>
    </section>
</main>

<jsp:include page="component/Footer.jsp"/>

<!--                <script type="text/javascript" src="dest/jsmain.min.js"></script>-->
<!--                <script type="text/javascript" src="./dest/main.js"></script>-->
<script src="${pageContext.request.contextPath}/Views/User/dest/jsmain.min.js"></script>
<script src="${pageContext.request.contextPath}/Views/User/dest/main.js"></script>
<script>
    function togglePasswordVisibility(inputId) {
        const passwordInput = document.getElementById(inputId);
        passwordInput.type = passwordInput.type === "password" ? "text" : "password";
    }

    function validatePassword() {
        let pass = document.getElementById("password-forget"),
            confirmpass = document.getElementById("repassword-forget");
        console.log("confrim :>> ", confirmpass);
        var password = pass.value,
            repassword = confirmpass.value;
        const REGREX = {
            pass: /^(?=.*[A-Z])(?=.*[a-z])(?=.*\d).{8,}$/,
        };
        let errObj = {};

        if (email == "" || email == null) {
            document.querySelector(".error-passwordforget").innerHTML =
                "Vui lòng điền mật khẩu";
            pass.classList.add("error");
            errObj[`password`] = 1;
        } else if (!REGREX.pass.test(password)) {
            document.querySelector(".error-passwordforget").innerHTML =
                "Tối thiểu 8 ký tự và viết hoa chữ cái đầu";
            pass.classList.add("error");
            errObj[`password`] = 1;
        } else {
            document.querySelector(".error-passwordforget").innerHTML = "";
            pass.classList.remove("error");
        }
        if (password != repassword) {
            document.querySelector(".error-repasswordforget").innerHTML =
                "Xác nhân mật khẩu không đúng";
            confirmpass.classList.add("error");
            errObj[`password`] = 1;
        }
        pass.addEventListener("focus", function () {
            document.querySelector(".error-passwordforget").innerHTML = "";
            pass.classList.remove("error");
        });
        confirmpass.addEventListener("focus", function () {
            document.querySelector(".error-repasswordforget").innerHTML = "";
            confirmpass.classList.remove("error");
        });
        if (Object.keys(errObj).length > 0) {
            console.log("loi: chua gui dc mail :>> ", 0);
            return false;
        } else {
            console.log("test: da gui :>> ", 1);
            updatePasswordAjax();
            return true;
        }
    }

    function updatePasswordAjax() {
        let accountID=document.getElementById("accountID").value;
        let pass=document.getElementById("password-forget").value;
        $.ajax({
            type: "POST",
            url: contextPath + "/MailTest",
            data: {
                action: "forgetPass",
                password: pass,
                accountID:accountID,
            },
            headers: {
                "X-Requested-With": "XMLHttpRequest",
            },
            success: function (data) {
                let dataString=JSON.parse(data);
                if (dataString.success === true) {
                    alert("Thay đổi mật khẩu thành công");
                    location.href = contextPath + "/ItemServlet";
                } else {
                    console.log(data);
                    alert("Có lỗi xảy ra");
                }
            },
            error: function (error) {
                console.log("error :>> ", error);
            },
        });
    }

    let btn = document.querySelector(".btnsend");
    btn.addEventListener("click", function () {
        // console.log("email :>> ", email.value);
    });
</script>
</body>
</html>
