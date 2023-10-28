$(document).ready(function () {
    /* =====================SearchBoxHeader========================*/
    function showBoxSearch() {
        const btnShow = document.querySelector(".btnshow_search"),
                searchModal = document.querySelector(".header__right--search");
        btnShow.addEventListener("click", function (e) {
            e.preventDefault();
            searchModal.classList.toggle("active");
            btnShow.classList.toggle("active");
        });
    }
    showBoxSearch();
    /* =====================SliderProduct========================*/

    function productSlider() {
        var elem = document.querySelectorAll(".carousel-img");
        elem.forEach(function (item) {
            var flkty = new Flickity(item, {
                // options
                cellAlign: "left",
                contain: true,
                imagesLoaded: true,
                prevNextButtons: false,
                imagesLoaded: true,
                // wrapAround: true,
            });
            item.addEventListener("mouseout", function () {
                flkty.previous();
            });
            item.addEventListener("mouseover", function () {
                flkty.next();
            });
        });
    }

    productSlider();
});

function itemCollectionClick(itemId) {
    var form = document.getElementById("form_itemCollection");
    form.submit();
}



