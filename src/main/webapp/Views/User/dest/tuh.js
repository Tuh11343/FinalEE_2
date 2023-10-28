// Lưu thông tin đặt hàng vào cookie
function luuThongTinDatHang(itemID,amount,itemColor,itemSize,total) {
    const thongTinDatHang = {
        itemID: itemID,
        amount: 1,
        itemColor: itemColor,
        itemSize: itemSize,
        total: total,
    };

    /*let itemCount=document.getElementById("itemCount");*/
    const jsonThongTinDatHang = JSON.stringify(thongTinDatHang);

    document.cookie = "thongTinDatHang=" + jsonThongTinDatHang;
}

// Lấy thông tin đặt hàng từ cookie
function layThongTinDatHang() {
    const cookies = document.cookie.split(";"); // Tách các cookie thành mảng

    for (let i = 0; i < cookies.length; i++) {
        let cookie = cookies[i].trim();

        if (cookie.startsWith("thongTinDatHang=")) {
            // Lấy giá trị của cookie
            let cookieValue = cookie.substring("thongTinDatHang=".length, cookie.length);

            // Giải mã JSON
            let thongTinDatHang = JSON.parse(cookieValue);

            // In thông tin đặt hàng
            console.log("Thông tin đặt hàng:");
            console.log("itemID:", thongTinDatHang.itemID);
            console.log("amount:", thongTinDatHang.amount);
            console.log("itemColor:", thongTinDatHang.itemColor);
            console.log("itemSize:", thongTinDatHang.itemSize);
            console.log("total:", thongTinDatHang.total);

            return thongTinDatHang;
        }
    }

    return null; // Trả về null nếu không tìm thấy cookie
}

// Gọi hàm lưu thông tin đặt hàng
luuThongTinDatHang();

// Gọi hàm lấy thông tin đặt hàng
layThongTinDatHang();