<%-- 
    Document   : TestJSP
    Created on : Oct 14, 2023, 6:56:19 AM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<style>
    .pagination {
        display: inline-block;
    }

    .pagination a {
        color: black;
        float: left;
        padding: 8px 16px;
        text-decoration: none;
        transition: background-color .3s;
    }

    .pagination a.active {
        background-color: #4CAF50;
        color: white;
    }

    .pagination a:hover:not(.active) {background-color: #ddd;}
</style>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
    <div class="pagination">
        <a href="#" class="page" id="prev">&laquo;</a>
        <a href="#" class="page">1</a>
        <a href="#" class="page active">2</a>
        <a href="#" class="page">3</a>
        <a href="#" class="page">4</a>
        <a href="#" class="page">5</a>
        <a href="#" class="page">6</a>
        <a href="#" class="page">7</a>
        <a href="#" class="page">8</a>

        <a href="#" class="page" id="next">&raquo;</a>
    </div>

    <script>
        var pages = document.getElementsByClassName('page');
        var prev = document.getElementById('prev');
        var next = document.getElementById('next');

        for(var i = 0; i < pages.length; i++) {
            pages[i].addEventListener('click', function() {
                var current = document.getElementsByClassName('active');
                current[0].className = current[0].className.replace(' active', '');
                this.className += ' active';
                updatePagination();
            });
        }

        prev.addEventListener('click', function() {
            var current = document.getElementsByClassName('active');
            var currentPage = parseInt(current[0].textContent);
            if(currentPage > 1) {
                current[0].className = current[0].className.replace(' active', '');
                pages[currentPage - 1].className += ' active';
                updatePagination();
            }
        });

        next.addEventListener('click', function() {
            var current = document.getElementsByClassName('active');
            var currentPage = parseInt(current[0].textContent);
            if(currentPage < pages.length - 2) {
                current[0].className = current[0].className.replace(' active', '');
                pages[currentPage + 1].className += ' active';
                updatePagination();
            }
        });

        function updatePagination() {
            var current = document.getElementsByClassName('active');
            var currentPage = parseInt(current[0].textContent);
            if(currentPage > 5) {
                pages[1].textContent = '...';
                for(var j = 2; j < 6; j++) {
                    pages[j].textContent = (currentPage - 5 + j).toString();
                }
                prev.style.display = 'inline';  // Show the left «
            } else {
                pages[1].textContent = '1';
                for(var j = 2; j < 6; j++) {
                    pages[j].textContent = j.toString();
                }
                prev.style.display = 'none';  // Hide the left «
            }
        }
    </script>
    </body>
</html>
