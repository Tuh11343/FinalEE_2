<%@page contentType="text/html" pageEncoding="UTF-8" %> <%@ taglib
uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> <%@ taglib
uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <meta
      name="viewport"
      content="width=device-width, initial-scale=1.0 , maximum-scale=1.0, user-scalable=0"
    />
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
  <style>
    .loader-container {
      display: flex;
      align-items: center;
      justify-content: center;
      height: 100vh;
      background-color: #f1f1f1;
      position: fixed;
      top: 0;
      left: 0;
      width: 100%;
      z-index: 9999;
    }

    .loader {
      border: 16px solid var(--main-cl);
      border-top: 16px solid #ffffff;
      border-radius: 50%;
      width: 80px;
      height: 80px;
      animation: spin 1s linear infinite;
    }

    @keyframes spin {
      0% {
        transform: rotate(0deg);
      }
      100% {
        transform: rotate(360deg);
      }
    }

    /* Hide loader when content is loaded */
    .loader-container.loaded {
      display: none;
    }
  </style>
  <jsp:include page="component/Header.jsp" />
  <jsp:include page="component/ModalLogin.jsp" />

  <body class="forgetpasspage">
    <div class="loader-container">
      <div class="loader"></div>
    </div>
    <main class="main">
      <section class="forgetform">
        <div class="container">
          <div class="forgetform__panel">
            <h4 class="heading --h4">Quên mật khẩu</h4>
            <p style="margin-bottom: 10px">
              Hãy nhập mật khẩu chúng tôi sẽ gửi bạn mail xác nhận
            </p>
            <form class="formforget" onsubmit="return validateEmail()">
              <div class="form-gr">
                <span class="label" for="email">Email</span>
                <input
                  class="input-form"
                  type="text"
                  id="email-forget"
                  placeholder="Nhập email"
                  name="email-forget"
                />
                <p class="message-error error-emailforget"></p>
              </div>
              <button type="button" onclick="return validateEmail()" class="btn btnsend">
                Gửi
              </button>
            </form>
          </div>
        </div>
      </section>
    </main>

    <jsp:include page="component/Footer.jsp" />
    <script>
      document.addEventListener("DOMContentLoaded", function () {
        // Simulate loading delay (you can remove this in production)
        setTimeout(function () {
          // Add loaded class to hide the loader
          document.querySelector(".loader-container").classList.add("loaded");
        }, 2000); // Adjust the timeout based on your loading time
      });
    </script>
    <!--                <script type="text/javascript" src="dest/jsmain.min.js"></script>-->
    <!--                <script type="text/javascript" src="./dest/main.js"></script>-->
    <script src="${pageContext.request.contextPath}/Views/User/dest/jsmain.min.js"></script>
    <script src="${pageContext.request.contextPath}/Views/User/dest/main.js"></script>
    <script>
      function validateEmail() {
        let ipemail = document.getElementById("email-forget");
        var email = ipemail.value;
        const REGREX = {
          email:
            /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/,
        };
        let errObj = {};
        if (email == "" || email == null) {
          document.querySelector(".error-emailforget").innerHTML = "Vui lòng điền email";
          ipemail.classList.add("error");
          errObj[`email`] = 1;
        } else if (!REGREX.email.test(email)) {
          document.querySelector(".error-emailforget").innerHTML =
            "Vui lòng điền đúng dạng email";
          ipemail.classList.add("error");
          errObj[`email`] = 1;
        } else {
          document.querySelector(".error-emailforget").innerHTML = "";
          ipemail.classList.remove("error");
        }
        ipemail.addEventListener("focus", function () {
          document.querySelector(".error-emailforget").innerHTML = "";

          ipemail.classList.remove("error");
        });
        if (Object.keys(errObj).length > 0) {
          console.log("loi: chua gui dc mail :>> ", 0);
          return false;
        } else {
          console.log("test: da gui :>> ", 1);
          return true;
        }
      }

      let btn = document.querySelector(".btnsend");
      btn.addEventListener("click", function () {
        console.log("email :>> ", email.value);
      });
    </script>
  </body>
</html>
