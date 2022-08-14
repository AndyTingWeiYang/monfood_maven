<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link rel="stylesheet" href="<c:url value='/assets/vendors/mdi/css/materialdesignicons.min.css' />">
    <link rel="stylesheet" href="<c:url value='/assets/css/res-reception-css/style.css' />">
    <link rel="stylesheet" href="<c:url value='/assets/css/res-reception-css/bootstrap.css' />">
    <link rel="stylesheet" href="<c:url value="/assets/css/res-reception-css/monfood-skeleton.css" />">
    <link rel="stylesheet" href="<c:url value="/assets/css/res-reception-css/resReception-meal-preparation.css" />">
    <link rel="shortcut icon" href="<c:url value='/assets/images/favicon.png' />" />
    <script src="<c:url value='/assets/js/bootstrap.js' />"></script>
    <script src="<c:url value='/assets/js/off-canvas.js' />"></script>
    <script src="<c:url value='/assets/js/hoverable-collapse.js' />"></script>
    <script src="<c:url value='/assets/js/misc.js' />"></script>
    <script src="<c:url value='/assets/js/settings.js' />"></script>
    <script src="<c:url value='/assets/js/todolist.js' />"></script>
</head>

<body>
    <nav class="navbar p-0 fixed-top d-flex flex-row mf-navbar">
        <div class="navbar-menu-wrapper flex-grow d-flex align-items-stretch">
            <ul class="navbar-nav w-100 d-flex justify-content-center">
                <span class="logo">Mon</span>
                <span class="logo2">Food</span>
            </ul>
            <ul class="navbar-nav navbar-nav-right">
                <div class="navbar-profile">
                    <span class="nav-item menu-items">
                        <a class="nav-link" href="<c:url value='/resprofile/ResInfoPreviewServlet'/>">
                            <span class="menu-title align-item-center">後臺設定</span>
                        </a>
                    </span>
                </div>
            </ul>
            <!-- 手機畫面漢堡選單 -->
            <button class="navbar-toggler navbar-toggler-right d-lg-none align-self-center" type="button"
                data-toggle="offcanvas">
                <span class="mdi mdi-format-line-spacing"></span>
            </button>
        </div>
    </nav>
</body>

</html>