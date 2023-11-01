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
      const handleModal = () => {
        const btn_close = document.querySelector(".btn-close"),
          modal = document.querySelector(".modal"),
          btn_open = document.querySelector(".btn-user"),
          overlay = document.querySelector(".overlay"),
          tabs = document.querySelectorAll(".tab-item"),
          forms = document.querySelectorAll(".form-pane");
        console.log("forms", forms);
        btn_open.addEventListener("click", (e) => {
          e?.preventDefault();
          console.log(1);
          modal.classList.add("show");
        });
        function removeShow() {
          modal.classList.remove("show");
        }
        function removeActive() {
          tabs.forEach((tab) => {
            tab.classList.remove("active");
          });
          forms.forEach((form) => {
            form.classList.remove("active");
          });
        }
        btn_close.addEventListener("click", () => {
          removeShow();
        });
        overlay.addEventListener("click", () => {
          removeShow();
        });
        tabs.forEach((tab) => {
          tab.addEventListener("click", () => {
            removeActive();
            tab.classList.add("active");
            let typebtn = tab.getAttribute("data-type");

            forms.forEach((form) => {
              if (form.getAttribute("data-type") == typebtn) {
                form.classList.add("active");
              }
            });
          });
        });
      };
      handleModal();
});


