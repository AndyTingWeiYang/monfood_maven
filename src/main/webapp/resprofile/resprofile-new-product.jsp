<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">

<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>新增商品</title>
	<link rel="stylesheet" href="<c:url value='/assets/vendors/file-input/css/bootstrap-icons.min.css' />"
		crossorigin="anonymous">
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css">
	<link href="<c:url value='/assets/vendors/file-input/css/fileinput.min.css' />" media="all" rel="stylesheet"
		type="text/css" />

	<script src="<c:url value='/assets/js/jQuery-3.6.0.js' />"></script>
	<script src="<c:url value='/assets/js/bootstrap.js' />"></script>
	<script src="<c:url value='/assets/vendors/file-input/buffer.min.js' />" type="text/javascript"></script>
	<script src="<c:url value='/assets/vendors/file-input/fileinput.min.js' />" type="text/javascript"></script>
	<script src="<c:url value='/assets/vendors/file-input/piexif.min.js' />" type="text/javascript"></script>
	<script src="<c:url value='/assets/vendors/file-input/sortable.min.js' />" type="text/javascript"></script>
	<script src="<c:url value='/assets/vendors/file-input/bootstrap.bundle.min.js' />"
		crossorigin="anonymous"></script>
	<script src="<c:url value='/assets/vendors/file-input/theme.min.js' />"></script>
	<script src="<c:url value='/assets/vendors/file-input/zh-TW.js' />"></script>
	<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
</head>
<script>
	$(document).ready(function () {
		<c:if test="${not empty result}">
			const result = ${result};
			if(result) {
				swal("新增成功", "產品資訊已更新", "success");
			} else {
				swal("新增失敗", "請確認是否輸入正確", "error");
			}
		</c:if>
		
		$.ajax({
			url : 'PrievewProductResServlet',
			type : 'post',
			dataType : 'JSON',
			data:{},
			success : function(data) {
				console.log(data);
				// 對應到 input 的內容
				$('#resCategory').val(data.resCategoryName);
			}
		});
		
		function fileUpload() {
			$('#productPic').fileinput({
				theme: 'fa6',
				language: 'zh-TW'
			});
		}
		
		fileUpload();
	});
</script>

<body>
	<div class="container-scroller">
		<div>
			<jsp:include page="resprofile-sidebar.jsp" />
		</div>
		<div class="container-fluid page-body-wrapper">
			<jsp:include page="resprofile-header.jsp" />
			<div class="main-panel">
				<div class="mf-content-wrapper">

					<!-- 表單區塊 -->
					 <span class="monfood-title mr-1"></span>
                    <h3 style="display: inline-block;">新增商品</h3>
					<form method="post" action="NewProductServlet" enctype="multipart/form-data">
						<div class="jumbotron form-group row">
							<!-- 並排兩格區塊:餐廳資訊 -->
							
							<div class="col-sm-6 ">
								<label for="resName" class="col-sm-12 col-form-label">餐廳名稱</label>
								<div>
									<input id="resName" type="text" name="resName" class="form-control"
										value="${sessionScope.resName}" readonly>
								</div>
							</div>
							<div class="col-sm-6">
								<label for="resCategory" class="col-sm-12 col-form-label">餐廳類別</label>
								<div>
									<input id="resCategory" type="text" name="resCategory" class="form-control" readonly>
								</div>
							</div>

							<div class="col-sm-6">
								<label for="productName" class="col-sm-12 col-form-label">商品名稱</label>
								<div>
									<input id="productName" type="text" name="productName" class="form-control"
										placeholder="請輸入商品名稱"><span id="productNameSp" style="color:red;">${errorMsg.productName}</span>
								</div>
							</div>
							<div class="col-sm-6">
								<label for="productPrice" class="col-sm-12 col-form-label">商品價格</label>
								<div>
									<input id="productPrice" type="text" name="productPrice"
										class="form-control" placeholder="請輸入商品價格">
										<span id="productPriceSp" style="color:red;">${errorMsg.productPrice}</span>
								</div>
							</div>
							<div class="col-sm-6">
								<label for="productKcal" class="col-sm-12 col-form-label">商品熱量</label>
								<div>
									<input id="productKcal" type="text" name="productKcal" class="form-control"
										placeholder="請輸入商品熱量 (份/kcal)">
										<span id="productKcalSp" style="color:red;">${errorMsg.productKcal}</span>
								</div>
							</div>
							<div class="col-sm-6 ">
								<label for="stock" class="col-sm-12 col-form-label">商品庫存</label>
								<div>
									<input id="stock" type="text" name="stock" class="form-control"
										placeholder="請輸入數量">
										<span id="stockSp" style="color:red;">${errorMsg.stock}</span>
								</div>
							</div>
							<div class="col-sm-6">
								<label for="productStatus" class="col-sm-12 col-form-label">商品狀態</label>
								<select id="productStatus" name="productStatus" class="form-select"
									aria-label="Default select example">
									<option>請選擇</option>
									<option value="1">上架</option>
									<option value="2">售完</option>
									<option value="3">下架</option>
								</select>
							</div>
						</div>
						<div class="file-loading">
							<input id="productPic" name="productPic" type="file" multiple accept="image">
						</div>
						<div class="d-flex justify-content-end mt-3">
							<button id="btnCheck" type="submit" class="btn btn-outline-dark mf-bdr-15">確定</button>
							<button type="reset" class="btn btn-outline-dark mf-bdr-15 ml-2">取消</button>
						</div>
					</form>
				</div>
				<jsp:include page="resprofile-footer.jsp" />
			</div>
		</div>

	</div>
</body>

</html>