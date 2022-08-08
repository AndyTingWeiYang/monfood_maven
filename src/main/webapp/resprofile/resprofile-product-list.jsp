<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>商品列表</title>
    <link rel="stylesheet" href="<c:url value='/assets/css/jquery.dataTables.min.css' />">
    <script src="<c:url value='/assets/js/jQuery-3.6.0.js' />"></script>
    <script src="<c:url value='/assets/js/jquery.dataTables.min.js' />"></script>
    <style>
        .mf-btn-center {
            width: 60%;
            margin: 0 auto;
        }
    </style>
</head>
<script>
$(document).ready(function () {
    	function init() {
            $('#prductList').DataTable();
        }

        init();
    });
</script>

<body>
    <div class="container-scroller">
    	<div><jsp:include page="resprofile-sidebar.jsp" /></div>
        <div class="container-fluid page-body-wrapper">
        	<jsp:include page="resprofile-header.jsp" />
            <div class="main-panel">
                <div class="mf-content-wrapper">
                    <!-- 表單區塊 -->
                      <span class="monfood-title mr-1"></span>
                    <h3 style="display: inline-block;">商品列表</h3>
                    <form class="jumbotron" method="post" action="ProductListServlet">
                        <div class="form-group row ">
	                        <div class="col-sm-6 ">
	                                <span class="resID " name="resID">
	                                    會員編號 : ${sessionScope.resID}
	                                </span>
	                            </div>
                            <div class="col-sm-6 d-flex" style="justify-content: end;">
                            </div>
                            <div class="row col-md-6 col-sm-12 mb-3">
                                <label for="productID" class="col-form-label col-md-12 col-sm-12">商品編號</label>
                                <div class="col-md-12 col-sm-12">
                                    <input id="productID" type="text" name="productID" class="form-control">
                                    <span id="checkProductID" style="color: red; font-size: small;" >${errorMsg.productIdError}</span>
                                </div>
                            </div>

                            <div class="row col-md-6 col-sm-12 mb-3">
                                <label for="productName" class="col-form-label col-md-12 col-sm-12">商品名稱</label>
                                <div class="col-md-12 col-sm-12">
                                    <input id="productName" type="text" name="productName" class="form-control">
                                    <span id="checkProductNameID" style="color: red; font-size: small;" >${errorMsg.productNameError}</span>
                                </div>
                            </div>

                            <div class="row col-md-12 col-sm-12 mb-3">
                                <label for="mintPrice" class="col-form-label col-md-12 col-sm-12">價格範圍</label>
                                <div class="row col-md-12 col-sm-12 justify-content-center">
                                    <div class="col-md-5">
                                        <input id="mintPrice" type="text" name="minPrice" class="form-control"
                                            placeholder="請輸入最小金額">
                                            <span id="checkMinPrice" style="color: red; font-size: small;" >${errorMsg.minPriceError}</span>
                                            <span id="rangeError" style="color: red; font-size: small;" >${errorMsg.rangeError}</span>
                                    </div>
                                    ~
                                    <div class="col-md-5">
                                        <input id="maxtPrice" type="text" name="maxPrice" class="form-control"
                                            placeholder="請輸入最大金額"><span id="checkMaxPrice" style="color: red; font-size: small;" >${errorMsg.maxPriceError}</span>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="d-flex justify-content-center">
                            <button type="submit" class="btn btn-outline-dark mf-bdr-15">查詢</button>
                            <button type="reset" class="btn btn-outline-dark mf-bdr-15 ml-4">清除</button>
                        </div>
                    </form>
                    <!-- DataTable -->
                    <div class="container">
                        <table id="prductList" class="display" style="width:100%">
                            <thead>
                                <tr>
                                    <th>商品ID</th>
                                    <th>商品名稱</th>
                                    <th>商品單價</th>
                                    <th>數量</th>
                                    <th>商品照片</th>
                                    <th>狀態</th>
                                    <th></th>
                                </tr>
                            </thead>
                            <tbody>
                            	<c:forEach var="product" items="${productList}" begin="0" step="1" varStatus="i">                            	
	                                <tr>
	                                    <td>${product.productID}</td>
	                                    <td>${product.productName}</td>
	                                    <td>${product.productPrice}</td>
	                                    <td>${product.stock}</td>
	                                    <td><img style="width: 120px; height: 80px" src="ProductPicServlet?productID=${product.productID}" /></td>
	                                    <td>${product.productStatus}</td>
	                                    <td>
	                                        <div class="mf-btn-center">
	                                        	<a class="btn btn-outline-secondary btn-sm mb-1" href="<%=request.getContextPath()%>/resprofile/PreviewProductServlet?productID=${product.productID}"  id="manual-ajax">
	                                            	修改
                                                </a>
	                                        </div>
	                                    </td>
	                                </tr>
                            	</c:forEach>
                            </tbody>
                            <tfoot>
                                <tr>
                                    <th>商品ID</th>
                                    <th>商品名稱</th>
                                    <th>商品單價</th>
                                    <th>數量</th>
                                    <th>商品照片</th>
                                    <th>狀態</th>
                                    <th></th>
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