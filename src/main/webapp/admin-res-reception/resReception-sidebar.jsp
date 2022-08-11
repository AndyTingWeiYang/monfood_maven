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
</head>

<body>
    <nav class="sidebar sidebar-offcanvas mf-sidebar" id="sidebar">       
        <ul class="nav">
            <li class="nav-item menu-items">
                <div class="switch_demo">
                    <input type="checkbox" name="switch_demo" class="switch_demo-checkbox" id="switch_demo" >
                    <label class="switch_demo-label" for="switch_demo">
                        <span class="switch_demo-inner"></span>
                        <span class="switch_demo-switch"></span>
                    </label>
                </div>
            </li>
<!--             <li class="nav-item menu-items"> -->
<!--                 <a class="nav-link" data-toggle="collapse" href="resReception-new-order.jsp" aria-expanded="false" -->
<!--                     aria-controls="ui-basic"> -->
<!--                         <i class="mdi mdi-48px mdi-new-box"></i> -->
<!--                     <span class="menu-title">新訂單</span> -->
<!--                     <span class="badge bg-primary rounded-pill">6</span> -->
<!--                 </a> -->
<!--             </li> -->
            <li class="nav-item menu-items">
                <a class="nav-link" data-toggle="collapse" href="resReception-meal-preparation.jsp" aria-expanded="false"
                    aria-controls="ui-basic">
                        <i class="mdi mdi-48px mdi-fire"></i>
                    <span class="menu-title">備餐中</span>
                </a>
            </li>
            <li class="nav-item menu-items">
                <a class="nav-link" data-toggle="collapse" href="resReception-Take-away-meal.jsp" aria-expanded="false"
                    aria-controls="ui-basic">
                        <i class="mdi mdi-48px mdi-motorbike"></i>
                    <span class="menu-title">可取餐</span>
                </a>
            </li>
            <li class="nav-item menu-items">
                <a class="nav-link" href="resReception-order-record.jsp">
                        <i class="mdi mdi-48px mdi-library-books"></i>
                    <!-- </span> -->
                    <span class="menu-title">訂單紀錄</span>
                </a>
            </li>
            <li class="nav-item menu-items">
                <a class="nav-link" href="resReception-index.jsp">
                        <i class="mdi mdi-48px mdi-settings"></i>                  
                    <span class="menu-title">設定</span>
                </a>
            </li>
        </ul>
    </nav>
 
</body>

</html>