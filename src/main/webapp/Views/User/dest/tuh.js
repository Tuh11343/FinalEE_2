function submitForm(formId) {
    var form = document.getElementById(formId);
    form.submit();
}

function logInAjaxRequest() {
    var email = document.getElementById("email").value;
    var password = document.getElementById("password").value;
    $.ajax({
        type: "POST",
        url: contextPath + "/HeaderServlet",
        data: {
            action: "signIn",
            signInName: email,
            signInPassword: password,
        },
        headers: {
            "X-Requested-With": "XMLHttpRequest"
        },
        success: function (data) {
            console.log(1);
            if (data.success === 1) {
                document.cookie = "signInAccountID=" + data.accountID + "; path=/"
                location.reload();
            } else if (data.success === 0) {

            }
        },
    });
}

function signUpAjaxRequest() {
    const customerName = document.getElementById("name-regis").value;
    const email = document.getElementById("email-regis").value;
    const phoneNumber = document.getElementById("phone-regis").value;
    const address = document.getElementById("address-regis").value;
    const password = document.getElementById("password-regis").value;
    const rePassword = document.getElementById("re-password").value;

    $.ajax({
        type: "POST",
        url: contextPath + "/HeaderServlet",
        data: {
            action: "signUp",
            email: email,
            customerName: customerName,
            phoneNumber: phoneNumber,
            address: address,
            password: password,
            rePassword: rePassword,
        },
        headers: {
            "X-Requested-With": "XMLHttpRequest"
        },
        success: function (data) {
            if (data.systemError === 1) {
                console.log("System Error");
            } else if (data.success === 1) {
                //Announce
                location.reload();
            } else if (data.passwordIncorrect === 1) {
                console.log("Password not correct");
            }
        },
    });
}
