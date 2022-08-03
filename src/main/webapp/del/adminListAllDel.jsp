<%@page import="com.model.del.DelVO"%>
<%@page import="com.model.del.DelService"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.model.del.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
DelService delSvc = new DelService();
List<DelVO> list = delSvc.getAll();
pageContext.setAttribute("list", list);
%>


<html>
<head>
<title>MonFood</title>


</head>
<body bgcolor='white'>

	<script src="https://www.w3schools.com/lib/w3.js"></script>
	<script src="assets/js/jQuery-3.6.0.js"></script>
	<div class="container-scroller">

		<!-- ===== sidebar start ===== -->
		<!-- TODO: 未來 JSP <jsp:include page="sidebar.jsp" /> 取代 -->
		<div w3-include-html="adminSidebar.html"></div>
		<!-- ===== sidebar end ===== -->

		<div class="container-fluid page-body-wrapper">
			<!-- ===== header start ===== -->
			<!-- TODO: 未來 JSP <jsp:include page="header.jsp" /> 取代 -->
			<div w3-include-html="adminHeader.html"></div>
			<!-- ===== header end ===== -->

			<div class="main-panel">
				<!-- ===== 內容客製化區塊 start ===== -->
				<div class="mf-content-wrapper mt-5">
					<!-- 表單區塊 -->
					<!-- bw ZONE					 -->



					<tr>

						<td>${delVO.platenumber}</td>
						<td>${delVO.status}</td>
						<td><fmt:formatDate value="${delVO.updateTime}"
								pattern="yyyy-MM-dd HH:mm:ss.SSS" /></td>
						<td><img
							src="${pageContext.request.contextPath}/DBGifReader2?id=2"></td>
						<td><img
							src="${pageContext.request.contextPath}/DBGifReader2?id=2"></td>
						<td><img
							src="${pageContext.request.contextPath}/DBGifReader2?id=2"></td>
						<td><img
							src="${pageContext.request.contextPath}/DBGifReader2?id=2"></td>
						<td><img
							src="${pageContext.request.contextPath}/DBGifReader2?id=2"></td>
						<td>${delVO.delAccountName}</td>
						<td>${delVO.delBankname}</td>
						<td>${delVO.delBankcode}</td>
						<td>${delVO.delBankaccount}</td>

					</tr>

					</table>
<%-- 					<%@ include file="page02.file"%> --%>








					<!-- BW zone -->
					<div class="row result">
						<div class="col-12">
							<div class="card">
								<div class="card-header" style="background-color: #e9ecef;">
									<h3 class="card-title" style="color: black;">查詢結果</h3>
								</div>
								<!-- /.card-header -->
								<div class="card-body table-responsive p-0"
									style="height: 300px;">
									<table class="table table-head-fixed text-nowrap">
										<thead>
											<tr>
												<th>ID</th>
												<th>Name</th>
												<th>Account</th>
												<th>Password</th>
												<th>TEL</th>
												<th>Birthday</th>
											</tr>
										</thead>
<%-- 										<%@ include file="page01.file"%> --%>
										<c:forEach var="delVO" items="${list}" >
											<tbody>
												<tr>
													<td>${delVO.delID}</td>
													<td>${delVO.delName}</td>
													<td>${delVO.delAccount}</td>
													<td><span class="tag tag-success">${delVO.delPassword}</span></td>
													<td>${delVO.delTel}</td>
													<td><fmt:formatDate value="${delVO.delBirthday}"
															pattern="yyyy-MM-dd HH:mm:ss.SSS" /></td>
												</tr>
												<tr>
													<td>219</td>
													<td>Alexander Pierce</td>
													<td>11-7-2014</td>
													<td><span class="tag tag-warning">Pending</span></td>
													<td>Bacon ipsum dolor sit amet salami venison chicken
														flank fatback doner.</td>
												</tr>
												<tr>
													<td>657</td>
													<td>Bob Doe</td>
													<td>11-7-2014</td>
													<td><span class="tag tag-primary">Approved</span></td>
													<td>Bacon ipsum dolor sit amet salami venison chicken
														flank fatback doner.</td>
												</tr>
												<tr>
													<td>175</td>
													<td>Mike Doe</td>
													<td>11-7-2014</td>
													<td><span class="tag tag-danger">Denied</span></td>
													<td>Bacon ipsum dolor sit amet salami venison chicken
														flank fatback doner.</td>
												</tr>
												<tr>
													<td>134</td>
													<td>Jim Doe</td>
													<td>11-7-2014</td>
													<td><span class="tag tag-success">Approved</span></td>
													<td>Bacon ipsum dolor sit amet salami venison chicken
														flank fatback doner.</td>
												</tr>
												<tr>
													<td>494</td>
													<td>Victoria Doe</td>
													<td>11-7-2014</td>
													<td><span class="tag tag-warning">Pending</span></td>
													<td>Bacon ipsum dolor sit amet salami venison chicken
														flank fatback doner.</td>
												</tr>
												<tr>
													<td>832</td>
													<td>Michael Doe</td>
													<td>11-7-2014</td>
													<td><span class="tag tag-primary">Approved</span></td>
													<td>Bacon ipsum dolor sit amet salami venison chicken
														flank fatback doner.</td>
												</tr>
												<tr>
													<td>982</td>
													<td>Rocky Doe</td>
													<td>11-7-2014</td>
													<td><span class="tag tag-danger">Denied</span></td>
													<td>Bacon ipsum dolor sit amet salami venison chicken
														flank fatback doner.</td>
												</tr>
												<tr>
													<td>982</td>
													<td>Rocky Doe</td>
													<td>11-7-2014</td>
													<td><span class="tag tag-danger">Denied</span></td>
													<td>Bacon ipsum dolor sit amet salami venison chicken
														flank fatback doner.</td>
												</tr>
												<tr>
													<td>982</td>
													<td>Rocky Doe</td>
													<td>11-7-2014</td>
													<td><span class="tag tag-danger">Denied</span></td>
													<td>Bacon ipsum dolor sit amet salami venison chicken
														flank fatback doner.</td>
												</tr>
											</tbody>
										</c:forEach>
									</table>
<%-- 									<%@ include file="page02.file"%> --%>
								</div>
								<!-- /.card-body -->
							</div>
							<!-- /.card -->
						</div>
					</div>
				</div>
				<!-- ===== 內容客製化區塊 ===== -->


				<!-- ===== footer start ===== -->
				<!-- TODO: 未來 JSP <jsp:include page="footer.jsp" /> 取代 -->
				<div w3-include-html="adminFooter.html"></div>
				<!-- ===== footer end ===== -->
			</div>
		</div>
	</div>
	<script>
		// <jsp:include page="head.jsp" /> 即可移除以下 <script>
		document.addEventListener('DOMContentLoaded', function() {
			w3.includeHTML(function() {
				var scriptObj = document
						.querySelectorAll('script[src^="assets"]');

				for (var i = 0; i < scriptObj.length; i++) {
					var scriptElement = document.createElement('script');
					scriptElement.src = scriptObj[i].getAttribute('src');

					document.head.appendChild(scriptElement);
				}
			});
		});
	</script>





</body>
</html>