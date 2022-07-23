<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>商品列表</title>
    <link rel="stylesheet" href="<c:url value='/assets/css/jquery.dataTables.min.css' />">
<!--     <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jquery-modal/0.9.1/jquery.modal.min.css" /> -->

    <script src="<c:url value='/assets/js/jQuery-3.6.0.js' />"></script>
    <script src="<c:url value='/assets/js/jquery.dataTables.min.js' />"></script>
<!--     <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-modal/0.9.1/jquery.modal.min.js"></script> -->
    <style>
        .mf-btn-center {
            width: 60%;
            margin: 0 auto;
        }
    </style>
</head>
<script>
    document.addEventListener('DOMContentLoaded', function () {
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
                    <form class="jumbotron" method="post" action="">
                        <div class="form-group row ">
                            <div class="row col-md-6 col-sm-12 mb-3">
                                <label for="productId" class="col-form-label col-md-12 col-sm-12">商品編號</label>
                                <div class="col-md-12 col-sm-12">
                                    <input id="productId" type="text" name="productId" class="form-control">
                                </div>
                            </div>
                            <div class="row col-md-6 col-sm-12 mb-3">
                                <label for="resCategory" class="col-form-label col-md-12 col-sm-12">類別查詢</label>
                                <div class="col-md-12 col-sm-12">
                                    <select id="resCategory" name="resCategory" class="form-select"
                                        aria-label="Default select example">
                                        <option selected>請選擇</option>
                                        <option value="1">台式</option>
                                        <option value="2">美式</option>
                                        <option value="3">日式</option>
                                        <option value="4">韓式</option>
                                    </select>
                                </div>
                            </div>

                            <div class="row col-md-6 col-sm-12 mb-3">
                                <label for="productName" class="col-form-label col-md-12 col-sm-12">商品名稱</label>
                                <div class="col-md-12 col-sm-12">
                                    <input id="productName" type="text" name="productName" class="form-control">
                                </div>
                            </div>

                            <div class="row col-md-6 col-sm-12 mb-3">
                                <label for="mintPrice" class="col-form-label col-md-12 col-sm-12">價格範圍</label>
                                <div class="row col-md-12 col-sm-12 justify-content-center">
                                    <div class="col-md-5">
                                        <input id="mintPrice" type="text" name="minPrice" class="form-control"
                                            placeholder="請輸入最小金額">
                                    </div>
                                    ~
                                    <div class="col-md-5">
                                        <input id="maxtPrice" type="text" name="maxPrice" class="form-control"
                                            placeholder="請輸入最大金額">
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="d-flex justify-content-center">
                            <button type="button" class="btn btn-outline-dark mf-bdr-15">查詢</button>
                            <button type="button" class="btn btn-outline-dark mf-bdr-15 ml-4">清除</button>
                        </div>
                    </form>
                    <!-- DataTable -->
                    <div class="container">
                        <table id="prductList" class="display" style="width:100%">
                            <thead>
                                <tr>
                                    <th>商品ID</th>
                                    <th>商品名稱</th>
                                    <th>餐廳類別</th>
                                    <th>商品單價</th>
                                    <th>數量</th>
                                    <th>商品照片</th>
                                    <th>狀態</th>
                                    <th></th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <td>123</td>
                                    <td>勁辣雞腿堡</td>
                                    <td>美式</td>
                                    <td>75</td>
                                    <td>99</td>
                                    <td>照片</td>
                                    <td>上架中</td>
                                    <td>
                                        <div class="mf-btn-center">
                                            <button class="btn btn-outline-secondary btn-sm mb-1">
                                                <a href="resprofile-account.html" id="manual-ajax">修改</a>
                                            </button>
                                            <button class="btn btn-outline-secondary btn-sm">取消</button>
                                        </div>
                                    </td>
                                </tr>
                                <tr>
                                    <td>124</td>
                                    <td>勁辣雞腿堡</td>
                                    <td>美式</td>
                                    <td>75</td>
                                    <td>99</td>
                                    <td>照片</td>
                                    <td>上架中</td>
                                    <td>
                                        <div class="mf-btn-center">
                                            <button class="btn btn-outline-secondary btn-sm mb-1">
                                                <a href="/resprofile-account.html" rel="modal:open">修改</a>
                                            </button>
                                            <button class="btn btn-outline-secondary btn-sm">取消</button>
                                        </div>
                                    </td>
                                </tr>
                                <tr>
                                    <td>125</td>
                                    <td>勁辣雞腿堡</td>
                                    <td>美式</td>
                                    <td>75</td>
                                    <td>99</td>
                                    <td>照片</td>
                                    <td>上架中</td>
                                    <td>
                                        <div class="mf-btn-center">
                                            <button class="btn btn-outline-secondary btn-sm mb-1">修改</button>
                                            <button class="btn btn-outline-secondary btn-sm">取消</button>
                                        </div>
                                    </td>
                                </tr>
                                <tr>
                                    <td>126</td>
                                    <td>勁辣雞腿堡</td>
                                    <td>美式</td>
                                    <td>75</td>
                                    <td>99</td>
                                    <td>照片</td>
                                    <td>上架中</td>
                                    <td>
                                        <div class="mf-btn-center">
                                            <button class="btn btn-outline-secondary btn-sm mb-1">修改</button>
                                            <button class="btn btn-outline-secondary btn-sm">取消</button>
                                        </div>
                                    </td>
                                </tr>
                                <tr>
                                    <td>127</td>
                                    <td>勁辣雞腿堡</td>
                                    <td>美式</td>
                                    <td>75</td>
                                    <td>99</td>
                                    <td>照片</td>
                                    <td>上架中</td>
                                    <td>
                                        <div class="mf-btn-center">
                                            <button class="btn btn-outline-secondary btn-sm mb-1">修改</button>
                                            <button class="btn btn-outline-secondary btn-sm">取消</button>
                                        </div>
                                    </td>
                                </tr>
                                <tr>
                                    <td>128</td>
                                    <td>勁辣雞腿堡</td>
                                    <td>美式</td>
                                    <td>75</td>
                                    <td>99</td>
                                    <td>照片</td>
                                    <td>上架中</td>
                                    <td>
                                        <div class="mf-btn-center">
                                            <button class="btn btn-outline-secondary btn-sm mb-1">修改</button>
                                            <button class="btn btn-outline-secondary btn-sm">取消</button>
                                        </div>
                                    </td>
                                </tr>
                                <tr>
                                    <td>129</td>
                                    <td>勁辣雞腿堡</td>
                                    <td>美式</td>
                                    <td>75</td>
                                    <td>99</td>
                                    <td>照片</td>
                                    <td>上架中</td>
                                    <td>
                                        <div class="mf-btn-center">
                                            <button class="btn btn-outline-secondary btn-sm mb-1">修改</button>
                                            <button class="btn btn-outline-secondary btn-sm">取消</button>
                                        </div>
                                    </td>
                                </tr>
                                <tr>
                                    <td>130</td>
                                    <td>勁辣雞腿堡</td>
                                    <td>美式</td>
                                    <td>75</td>
                                    <td>99</td>
                                    <td>照片</td>
                                    <td>上架中</td>
                                    <td>
                                        <div class="mf-btn-center">
                                            <button class="btn btn-outline-secondary btn-sm mb-1">修改</button>
                                            <button class="btn btn-outline-secondary btn-sm">取消</button>
                                        </div>
                                    </td>
                                </tr>
                                <tr>
                                    <td>131</td>
                                    <td>勁辣雞腿堡</td>
                                    <td>美式</td>
                                    <td>75</td>
                                    <td>99</td>
                                    <td>照片</td>
                                    <td>上架中</td>
                                    <td>
                                        <div class="mf-btn-center">
                                            <button class="btn btn-outline-secondary btn-sm mb-1">修改</button>
                                            <button class="btn btn-outline-secondary btn-sm">取消</button>
                                        </div>
                                    </td>
                                </tr>
                                <tr>
                                    <td>132</td>
                                    <td>勁辣雞腿堡</td>
                                    <td>美式</td>
                                    <td>75</td>
                                    <td>99</td>
                                    <td>照片</td>
                                    <td>上架中</td>
                                    <td>
                                        <div class="mf-btn-center">
                                            <button class="btn btn-outline-secondary btn-sm mb-1">修改</button>
                                            <button class="btn btn-outline-secondary btn-sm">取消</button>
                                        </div>
                                    </td>
                                </tr>
                                <tr>
                                    <td>133</td>
                                    <td>勁辣雞腿堡</td>
                                    <td>美式</td>
                                    <td>75</td>
                                    <td>99</td>
                                    <td>照片</td>
                                    <td>上架中</td>
                                    <td>
                                        <div class="mf-btn-center">
                                            <button class="btn btn-outline-secondary btn-sm mb-1">修改</button>
                                            <button class="btn btn-outline-secondary btn-sm">取消</button>
                                        </div>
                                    </td>
                                </tr>
                                <tr>
                                    <td>134</td>
                                    <td>勁辣雞腿堡</td>
                                    <td>美式</td>
                                    <td>75</td>
                                    <td>99</td>
                                    <td>照片</td>
                                    <td>上架中</td>
                                    <td>
                                        <div class="mf-btn-center">
                                            <button class="btn btn-outline-secondary btn-sm mb-1">修改</button>
                                            <button class="btn btn-outline-secondary btn-sm">取消</button>
                                        </div>
                                    </td>
                                </tr>
                                <tr>
                                    <td>135</td>
                                    <td>勁辣雞腿堡</td>
                                    <td>美式</td>
                                    <td>75</td>
                                    <td>99</td>
                                    <td>照片</td>
                                    <td>上架中</td>
                                    <td>
                                        <div class="mf-btn-center">
                                            <button class="btn btn-outline-secondary btn-sm mb-1">修改</button>
                                            <button class="btn btn-outline-secondary btn-sm">取消</button>
                                        </div>
                                    </td>
                                </tr>
                            </tbody>
                            <tfoot>
                                <tr>
                                    <th>商品ID</th>
                                    <th>商品名稱</th>
                                    <th>餐廳類別</th>
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