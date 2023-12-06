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
                if (data.accountPermission === 1) {
                    window.location.href=contextPath+"/AdminManagerServlet";
                }else{
                    location.reload();
                }

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
                //hiển thị thông báo
            } else if (data.passwordIncorrect === 1) {
                console.log("Password not correct");
            }
        },
    });
}

function productsSoldByMonthAjaxRequest(selectedDateID, action, tableID) {
    var selectedDate = document.getElementById(selectedDateID).value;

    // Tạo đối tượng Date từ giá trị ngày
    var date = new Date(selectedDate);

    // Lấy tháng và năm
    var month = date.getMonth() + 1; // Tháng bắt đầu từ 0 nên cần cộng thêm 1
    var year = date.getFullYear();

    console.log("Month" + month + " " + "year:" + year);

    $.ajax({
        type: "POST",
        url: adminManagerContextPath + "/AdminManagerServlet",
        data: {
            action: action,
            month: month,
            year: year,
        },
        headers: {
            "X-Requested-With": "XMLHttpRequest"
        },
        success: function (data) {
            $("#" + tableID).find("tr:gt(0)").remove();

            switch (tableID) {
                case 'productsTable': {
                    for (var i = 0; i < data.length; i++) {
                        var row = "<tr><td>" + data[i].itemID + "</td><td>" + data[i].productsSold + "</td></tr>";
                        $("#" + tableID).append(row);
                    }
                    break;
                }
                case 'productsSoldByYearTable': {
                    for (var i = 0; i < data.length; i++) {
                        var row = "<tr><td>" + data[i].itemID + "</td><td>" + data[i].productsSold + "</td></tr>";
                        $("#" + tableID).append(row);
                    }
                    break;
                }
                case 'totalProductsSoldByMonthTable': {
                    for (var i = 0; i < data.length; i++) {
                        var row = "<tr><td>" + data[i].itemID + "</td><td>" + data[i].productsSold + "</td></tr>";
                        $("#" + tableID).append(row);
                    }
                    break;
                }
                case 'totalProductsSoldByYearTable': {
                    for (var i = 0; i < data.length; i++) {
                        var row = "<tr><td>" + data[i].itemID + "</td><td>" + data[i].productsSold + "</td></tr>";
                        $("#" + tableID).append(row);
                    }
                    break;
                }
                case 'recentFiveMonthRevenueTable': {
                    for (var i = 0; i < data.length; i++) {
                        var row = "<tr><td>" + data[i].month + "</td><td>" + data[i].revenue + "</td></tr>";
                        $("#" + tableID).append(row);
                    }
                    break;
                }
                case 'monthRevenueTable': {
                    for (var i = 0; i < data.length; i++) {
                        var row = "<tr><td>" + data[i].month + "</td><td>" + data[i].revenue + "</td></tr>";
                        $("#" + tableID).append(row);
                    }
                    break;
                }
            }


        },
        error: function (error) {
            console.log("error:" + error);
        }
    });
}


function selectTedtable() {
    const selectIndex = document.getElementById("type-select"),
        tableType = document.querySelectorAll('.typetable');
    console.log(selectIndex.value);
    tableType.forEach(function (item) {

        let itemtable = item.getAttribute(`data-type`);
        if (selectIndex.value === itemtable) {
            tableType.forEach(function (item) {
                item.style.display = 'none';
            })
            item.style.display = 'block';
        }
    })
}


