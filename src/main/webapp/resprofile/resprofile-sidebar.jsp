<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
			<li class="nav-item nav-category"><a href="#"> <span
					class="nav-link"> <a
						href="<c:url value='/admin-res-reception/resReception-meal-preparation.jsp' />">
							<img width="16px" height="16px"
							src="<c:url value='/assets/images/arrow-left-solid.svg' />">
							<span class="ml-3">返回前台</span>
					</a>
				</span>
			</a></li>
			<li class="nav-item profile">
				<div class="profile-desc">
					<div class="profile-pic">
						<div class="count-indicator">
							<img id="resPhoto" name="resPhoto" width="80px" height="50px"
								class="rounded-circle" 
								src="<c:url value='/resprofile/ResPhotoPreviewServlet?resID=${sessionScope.resID}' />"
								alt="">
						</div>
						<div class="profile-name">
							<h5 class="mb-0 font-weight-normal mf-username">${sessionScope.resName}</h5>
						</div>
					</div>

				</div>
			</li>
			<li class="nav-item nav-category"><span class="nav-link">商家帳號</span>
			</li>
			<li class="nav-item menu-items"><a class="nav-link"
				data-toggle="collapse" href="ResInfoPreviewServlet"
				aria-expanded="false" aria-controls="ui-basic"> <span
					class="menu-icon"> <i class="mdi mdi-laptop"></i>
				</span> <span class="menu-title">餐廳資訊</span>
			</a></li>
			<li class="nav-item menu-items"><a class="nav-link"
				href="resprofile-product-list.jsp"> <span class="menu-icon">
						<i class="mdi mdi-playlist-play"></i>
				</span> <span class="menu-title">商品列表</span>
			</a></li>
			<li class="nav-item menu-items">
			<a id="newProduct" class="nav-link" href="resprofile-new-product.jsp"> 
			<span class="menu-icon"> <i class="mdi mdi-plus-circle"></i>
				</span> <span class="menu-title">新增商品</span>
			</a></li>
<!-- 			<li class="nav-item menu-items"><a class="nav-link" -->
<!-- 				href="resprofile-mailbox.jsp"> <span class="menu-icon">  -->
<!-- 				<i -->
<!-- 						class="mdi mdi-email-open-outline"></i> -->
<!-- 				</span> <span class="menu-title">收件匣</span> -->
<!-- 			</a></li> -->
			<li class="nav-item menu-items"><a class="nav-link"
				href="resprofile-order-history.jsp"> <span class="menu-icon">
						<i class="mdi mdi-playlist-play"></i>
				</span> <span class="menu-title">訂單明細查詢</span>
			</a></li>
		</ul>
	</nav>
</body>

</html>