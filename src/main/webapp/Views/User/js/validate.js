function validate() {
  const REGREX = {
    email:
      /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/,
    phone: /(84|0[3|5|7|8|9])+([0-9]{8})\b/,
    pass: /^(?=.*[A-Z])(?=.*[a-z])(?=.*\d).{8,}$/,
  };
  let errObj = {};
  const ipName = document.querySelector(".form-gr #name-regis"),
    ipEmail = document.querySelector(".form-gr #email-regis"),
    ipPhone = document.querySelector(".form-gr #phone-regis"),
    ipAddress = document.querySelector(".form-gr #address-regis"),
    ipPass = document.querySelector(".form-gr #password-regis"),
    ipRePass = document.querySelector(".form-gr #re-password");
  let name = ipName.value,
    email = ipEmail.value,
    phone = ipPhone.value,
    address = ipAddress.value,
    pass = ipPass.value,
    rePass = ipRePass.value;
  //kiem tra name
  if (name === "" || name == null) {
    document.querySelector(".error-name").innerHTML = "Vui lòng điền tên";
    ipName.classList.add("error");
    errObj[`name`] = 1;
  } else {
    document.querySelector(".error-name").innerHTML = "";
    ipName.classList.remove("error");
  }
  //kiem tra address
  if (address == "" || address == null) {
    document.querySelector(".error-address").innerHTML = "Vui lòng điền địa chỉ";
    ipAddress.classList.add("error");
    errObj[`address`] = 1;
  } else {
    document.querySelector(".error-address").innerHTML = "";
    ipAddress.classList.remove("error");
  }
  //kiem tra email

  if (email == "" || email == null) {
    document.querySelector(".error-email").innerHTML = "Vui lòng điền email";
    ipEmail.classList.add("error");
    errObj[`email`] = 1;
  } else if (!REGREX.email.test(email)) {
    document.querySelector(".error-email").innerHTML = "Vui lòng điền đúng dạng email";
    ipEmail.classList.add("error");
    errObj[`email`] = 1;
  } else {
    document.querySelector(".error-email").innerHTML = "";
    ipEmail.classList.remove("error");
  }
  //kiem tra phone

  if (phone == "" || phone == null) {
    document.querySelector(".error-phone").innerHTML = "Vui lòng điền số điện thoại";
    ipPhone.classList.add("error");
    errObj[`phone`] = 1;
  } else if (!REGREX.phone.test(phone)) {
    document.querySelector(".error-phone").innerHTML =
      "Vui lòng điền đúng dạng số điện thoại";
    ipPhone.classList.add("error");
    errObj[`phone`] = 1;
  } else {
    document.querySelector(".error-phone").innerHTML = "";
    ipPhone.classList.remove("error");
  }
  //kiem tra pass

  if (pass == "" || pass == null) {
    document.querySelector(".error-password").innerHTML = "Vui lòng điền mật khẩu";
    ipPass.classList.add("error");
    errObj[`pass`] = 1;
  } else if (!REGREX.pass.test(pass)) {
    document.querySelector(".error-password").innerHTML =
      "Vui lòng điền đúng dạng mật khẩu";
    ipPass.classList.add("error");
    errObj[`pass`] = 1;
  } else {
    document.querySelector(".error-password").innerHTML = "";
    ipPass.classList.remove("error");
  }
  //kiem tra rePass
  if (rePass == "" || rePass == null) {
    document.querySelector(".error-repassword").innerHTML = "Vui lòng xác nhận mật khẩu";
    errObj[`rePass`] = 1;
  } else if (pass !== rePass) {
    document.querySelector(".error-repassword").innerHTML =
      "Xác nhận mật khẩu không đúng";
    ipRePass.classList.add("error");
    errObj[`rePass`] = 1;
  } else {
    document.querySelector(".error-repassword").innerHTML = "";
    ipRePass.classList.remove("error");
  }
  if (Object.keys(errObj).length > 0) {
    return false;
  } else {
    signUpAjaxRequest();

    return false;
  }
}
