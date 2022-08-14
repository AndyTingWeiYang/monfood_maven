<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>商家歷史訂單查詢</title>
	<link rel="stylesheet" href="/monfood_maven/assets/css/jquery.dataTables.min.css">
	<link rel="stylesheet"
		href="https://cdnjs.cloudflare.com/ajax/libs/jquery-modal/0.9.1/jquery.modal.min.css" />


	<script src="/monfood_maven/assets/js/jQuery-3.6.0.js"></script>
	<script src="/monfood_maven/assets/js/jquery.dataTables.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-modal/0.9.1/jquery.modal.min.js"></script>
	<style>
		.mf-btn-center {
			width: 60%;
			margin: 0 auto;
		}
	</style>
</head>
<script>
$(document).ready(function () {
		$('#orderList').DataTable();
		
		$('#checkbtn').click(function() {		
			let orderVO= {
				orderId: $('#orderId').val(),
				userId: $('#userId').val()
			};
			
			// ajax pay load
			   $.ajax({
					url: 'ResFindOrderServlet',
					type: 'post',
					data: JSON.stringify(orderVO),
					dataType: 'json',
					success: function(data){
						const evalResult = eval(JSON.stringify(data.orderList));
						$('#orderList').DataTable({
							'data': evalResult,
							'columns': [
					            { 
					            	'data': 'userId',
					            	'defaultContent': '<i style="display: block" class="fa-solid fa-comment-slash text-center" ></i>'
					            },
					            { 
					            	'data': 'orderId',
					            	'defaultContent': '<i style="display: block" class="fa-solid fa-comment-slash text-center" ></i>'
					            },
					            { 
					            	'data': 'productName',
					            	'defaultContent':'<i style="display: block" class="fa-solid fa-comment-slash text-center" ></i>'
					            },
					            { 
					            	'data': 'amount',
					            	'defaultContent': '<i style="display: block" class="fa-solid fa-comment-slash text-center" ></i>'
					            },
					            { 
					            	'data': 'total',
					            	'defaultContent': '<i style="display: block" class="fa-solid fa-comment-slash text-center" ></i>'
					            },
					            { 
					            	'data': 'orderDone',
					            	'defaultContent': '<i style="display: block" class="fa-solid fa-comment-slash text-center" ></i>'
					            },
					            { 
					            	'data': 'note',
					            	'defaultContent': '<i style="display: block" class="fa-solid fa-comment-slash text-center" ></i>'
					            },
					            { 
					            	'data': 'resComment',
					            	'defaultContent': '<i style="display: block" class="fa-solid fa-comment-slash text-center" ></i>'
					            },
					            { 
					            	'data': 'orderStatus',
					            	'defaultContent': '<i class="fa-solid fa-comment-slash"></i>'
					            }
					       	 ],
					       	'destroy': true
						});
					}
				});
			});
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
                    <h3 style="display: inline-block;">訂單明細查詢</h3>
					<form class="jumbotron" method="post" action="ResFindOrderServlet">
						<div class="form-group row ">
							<div class="row col-md-6 col-sm-12 mb-3">
								<label for="orderId" class="col-form-label col-md-12 col-sm-12">訂單編號</label>
								<div class="col-md-12 col-sm-12">
									<input id="orderId" type="text" name="orderId" class="form-control">
								</div>
							</div>
							<div class="row col-md-6 col-sm-12 mb-3">
								<label for="userId" class="col-form-label col-md-12 col-sm-12">會員編號</label>
								<div class="col-md-12 col-sm-12">
									<input id="userId" type="text" name="userId" class="form-control">
								</div>
							</div>
						</div>
						<div class="d-flex justify-content-center">
							<button id="checkbtn" type="button"
								class="btn btn-outline-dark mf-bdr-15">查詢</button>
							<button type="reset" class="btn btn-outline-dark mf-bdr-15 ml-4">清除</button>
						</div>
					</form>
					<!-- DataTable -->
					<div class="container">
						<table id="orderList" class="display" style="width: 100%">
							<thead>
								<tr>
									<th>會員編號</th>
									<th>訂單編號</th>
									<th>商品名稱</th>
									<th>數量</th>
									<th>訂單總金額</th>
									<th>訂單完成時間</th>
									<th>訂單備註</th>
									<th>商家評論</th>
									<th>訂單狀態</th>
								</tr>
							</thead>
							<tbody></tbody>
							<tfoot>
								<tr>
									<th>會員編號</th>
									<th>訂單編號</th>
									<th>商品名稱</th>
									<th>數量</th>
									<th>訂單總金額</th>
									<th>訂單完成時間</th>
									<th>訂單備註</th>
									<th>商家評論</th>
									<th>訂單狀態</th>
								</tr>
							</tfoot>
						</table>
					</div>
				</div>
				<jsp:include page="resprofile-footer.jsp" />
			</div>
		</div>

	</div>
</body>

</html>