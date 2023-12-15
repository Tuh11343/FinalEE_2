<%-- Created by IntelliJ IDEA. User: ADMIN Date: 10/28/2023 Time: 10:13 AM To change this
template use File | Settings | File Templates. --%> <%@ page
        contentType="text/html;charset=UTF-8" language="java" %>
<div class="modal fade">
  <div class="modal-dialog">
    <button class="btn-close">X Đóng</button>
    <div class="form-box active">
      <div class="form-tab">
        <span class="tab-item active" data-type="login-from">Đăng nhập</span>
        <span class="tab-item" data-type="form-register">Đăng ký</span>
      </div>
      <div class="from-content">
        <%--Log In--%>
        <form class="form-pane login-from active" data-type="login-from">
          <div class="form-gr">
            <span class="label" for="email">Email</span>
            <input
                    class="input-form"
                    type="email"
                    id="email"
                    placeholder="Nhập email"
                    name="signInName"
                    required
            />
          </div>
          <div class="form-gr">
            <span class="label" for="password">Mật khẩu</span>
            <input
                    class="input-form"
                    type="password"
                    id="password"
                    placeholder="Nhập mật khẩu"
                    name="signInPassword"
                    required
            />
          </div>

          <a class="btn btn-submit" onclick="logInAjaxRequest()">Đăng nhập</a>
          <a href="#" style="float: right; color: var(--main-cl)" class="fogetpass"
          >Quên mật khẩu ?</a
          >
          <input type="hidden" name="action" value="signInClick" />
        </form>

        <%--Sign Up--%>
        <form
                onsubmit="return validate()"
                class="form-pane register-from"
                data-type="form-register"
        >
          <div class="form-gr">
            <span class="label" for="name-regis">Họ và tên</span>
            <input
                    class="input-form"
                    type="text"
                    name="signUpName"
                    id="name-regis"
                    placeholder="Nhập Họ và tên"
            />
            <p class="message-error error-name"></p>
          </div>
          <div class="form-gr">
            <span class="label" for="email-regis">Email</span>
            <input
                    class="input-form"
                    type="email"
                    id="email-regis"
                    name="signUpEmail"
                    placeholder="Nhập email"
            />
            <p class="message-error error-email"></p>
          </div>
          <div class="form-gr">
            <span class="label" for="phone-regis">Số điện thoại</span>
            <input
                    class="input-form"
                    type="phone"
                    id="phone-regis"
                    name="signUpPhoneNumber"
                    placeholder="Nhập Số điện thoại"
            />
            <p class="message-error error-phone"></p>
          </div>
          <div class="form-gr">
            <span class="label" for="address-regis">Địa chỉ</span>
            <input
                    class="input-form"
                    type="text"
                    id="address-regis"
                    name="signUpAddress"
                    placeholder="Nhập Địa chỉ"
            />
            <p class="message-error error-address"></p>
          </div>
          <div class="form-gr">
            <span class="label" for="password-resgis">Mật khẩu</span>
            <input
                    class="input-form"
                    type="password"
                    id="password-regis"
                    name="signUpPassword"
                    placeholder="Nhập mật khẩu"
            />
            <p class="message-error error-password"></p>
          </div>
          <div class="form-gr">
            <span class="label" for="re-password">Nhập lại mật khẩu</span>
            <input
                    class="input-form"
                    type="password"
                    id="re-password"
                    name="signUpRePassword"
                    placeholder="Nhập lại mật khẩu"
            />
            <p class="message-error error-repassword"></p>
          </div>
          <button type="submit" class="btn btn-submit">Đăng ký</button>
        </form>
      </div>
    </div>

    <div class="form-box">
      <div class="from-content">
        <form class="form-pane active" onsubmit="return validateEmail()">
          <h4 class="heading --h4" style="text-align: center">Quên mật khẩu</h4>
          <p style="margin-bottom: 10px">
            Hãy nhập mail chúng tôi sẽ gửi vào mail xác nhận
          </p>
          <div class="formforget">
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
            <button type="button" style="float: right" class="btn btn-green btnpre">
              Quay lại đăng nhập
            </button>
          </div>
        </form>
      </div>
    </div>

  </div>
  <div class="overlay"></div>
</div>

<style>
  .modal {
    width: 100vw;
    height: 100vh;
    position: fixed;
    top: 0;
    left: 0;
    overflow: hidden;
    z-index: 955;
    display: flex;
    align-items: center;
    justify-content: center;
    visibility: hidden;
    pointer-events: none;
    transition: var(--t);
    -webkit-transition: var(--t);
    -moz-transition: var(--t);
    -ms-transition: var(--t);
    -o-transition: var(--t);
    opacity: 0;
  }

  .modal.show {
    visibility: visible;
    pointer-events: auto;
    opacity: 1;
    transition: var(--t);
    -webkit-transition: var(--t);
    -moz-transition: var(--t);
    -ms-transition: var(--t);
    -o-transition: var(--t);
  }

  .modal.show .modal-dialog {
    margin-top: 0px;
    transition: var(--t);
    -webkit-transition: var(--t);
    -moz-transition: var(--t);
    -ms-transition: var(--t);
    -o-transition: var(--t);
    visibility: visible;
    pointer-events: auto;
    opacity: 1;
  }

  .modal .overlay {
    background: rgba(0, 0, 0, 0.9);
    width: 100%;
    height: 100%;
    position: absolute;
    top: 0;
    left: 0;
    cursor: pointer;
  }

  .modal .modal-dialog {
    min-width: 500px;
    background: var(--white-cl);
    border-radius: var(--brus);
    overflow: hidden;
    padding: 40px 30px;
    position: relative;
    z-index: 998;
    margin-top: -50px;
    visibility: hidden;
    pointer-events: none;
    opacity: 0;
    transition: var(--t);
    -webkit-transition: var(--t);
    -moz-transition: var(--t);
    -ms-transition: var(--t);
    -o-transition: var(--t);
  }

  .modal .modal-dialog .btn-close {
    background: var(--main-cl);
    border-radius: 1px;
    position: absolute;
    top: 0;
    right: 0;
    padding: 5px;
    cursor: pointer;
    transition: var(--t);
    -webkit-transition: var(--t);
    -moz-transition: var(--t);
    -ms-transition: var(--t);
    -o-transition: var(--t);
  }

  .modal .modal-dialog .btn-close:hover {
    background: var(--grayop-cl);
    transition: var(--t);
    -webkit-transition: var(--t);
    -moz-transition: var(--t);
    -ms-transition: var(--t);
    -o-transition: var(--t);
    color: var(--white-cl);
  }
  .modal .modal-dialog .form-box {
    display: none;
  }
  .modal .modal-dialog .form-box.active {
    display: block;
  }
  .modal .modal-dialog .form-box .form-tab {
    display: flex;
    align-items: center;
    justify-content: center;
    border-bottom: 1px solid #27272732;
  }

  .modal .modal-dialog .form-box .form-tab .tab-item {
    font-family: pb;
    font-size: var(--fs-h5);
    line-height: var(--lh-h5);
    color: #000;
    text-transform: capitalize;
    width: 50%;
    text-align: center;
    padding-bottom: 5px;
    cursor: pointer;
    transition: var(--t);
    -webkit-transition: var(--t);
    -moz-transition: var(--t);
    -ms-transition: var(--t);
    -o-transition: var(--t);
  }

  .modal .modal-dialog .form-box .form-tab .tab-item:hover {
    opacity: 0.7;
  }

  .modal .modal-dialog .form-box .form-tab .tab-item.active {
    transition: var(--t);
    -webkit-transition: var(--t);
    -moz-transition: var(--t);
    -ms-transition: var(--t);
    -o-transition: var(--t);
    color: var(--yellow-cl);
    border-bottom: 1px solid var(--yellow-cl);
  }

  .modal .modal-dialog .form-box .from-content {
    padding-top: 20px;
  }

  .modal .modal-dialog .form-box .from-content .form-pane {
    display: none;
    transition: var(--t);
    -webkit-transition: var(--t);
    -moz-transition: var(--t);
    -ms-transition: var(--t);
    -o-transition: var(--t);
  }

  .modal .modal-dialog .form-box .from-content .form-pane.active {
    display: block;
    transition: var(--t);
    -webkit-transition: var(--t);
    -moz-transition: var(--t);
    -ms-transition: var(--t);
    -o-transition: var(--t);
  }

  .modal .modal-dialog .form-box .from-content .form-pane .form-gr {
    display: flex;
    justify-content: space-between;
    flex-direction: column;
    margin-bottom: 17px;
    position: relative;
  }

  .modal .modal-dialog .form-box .from-content .form-pane .form-gr .label {
    text-transform: capitalize;
    margin-bottom: 15px;
  }

  .modal .modal-dialog .form-box .from-content .form-pane .form-gr input {
    width: 100%;
    border-radius: 3px;
    border: 1px solid var(--grayop-cl);
    padding: 5px;
    outline: none;
  }

  .modal .modal-dialog .form-box .from-content .form-pane .form-gr input.error {
    border: 1px solid red;
  }

  .modal .modal-dialog .form-box .from-content .form-pane .form-gr .message-error {
    position: absolute;
    right: 0;
    font-size: 12px;
    color: red;
    bottom: -18px;
  }
</style>

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
      updatePassAjax();
      return true;
    }
  }

  function updatePassAjax(){
    let email=document.getElementById("email-forget").value;
    $.ajax({
      type: "POST",
      url: contextPath + "/HeaderServlet",
      data: {
        action: "forgetPass",
        email:email,
      },
      headers: {
        "X-Requested-With": "XMLHttpRequest",
      },
      success: function (data) {
        if (data.success === 1) {
          alert("Gửi đến mail thành công");
          location.reload();
        }else{

        }
      },
      error: function (error) {
        console.log("error :>> ", error);
      },
    });
  }

</script>
<script>
  const contextPath = "${pageContext.request.contextPath}";
</script>

<script src="${pageContext.request.contextPath}/Views/User/js/validate.js"></script>
<script src="${pageContext.request.contextPath}/Views/User/dest/tuh.js"></script>

