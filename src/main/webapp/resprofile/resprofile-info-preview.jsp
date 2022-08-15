<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>商家資訊</title>
    <link rel="stylesheet" href="<c:url value='/assets/vendors/jquery.fancybox-1.3.4/fancybox/jquery.fancybox-1.3.4.css' />">
	

    <script src="<c:url value='/assets/js/jquery-1.8.3.js' />"></script>
   <script src="<c:url value='/assets/vendors/jquery.fancybox-1.3.4/fancybox/jquery.fancybox-1.3.4.js' />"></script>
    

	 <style>
	 	
	     span {
	         font-size: small;
	     }
	     
	     #fancybox-loading {
	     	z-index: 1;
	     }
	     #fancybox-overlay {
	     	z-index: 2;
	     }
	     
	     #fancybox-wrap {
	     	z-index: 3;
	     }
	 </style>

</head>
<script>
$(document).ready(function () {
function init(){
	$('#btnUpdate').fancybox({
		type: 'ajax',
		overlayColor: '#000',
		overlayOpcity: 0.8
	});
	
	
	let resIDSp =sessionStorage.setItem("resId" ,$('#resIDSp').text());


}

$('#btnCancel2').click(function(){
	$("#fancybox-close").trigger('click'); 
});


init();
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
                    <h3 style="display: inline-block;">餐廳資訊</h3>
                    <form class="jumbotron" method="post" action="">
                        <div class="form-group row">
                        	<div class="row">
                        		<div class="col-sm-12">
                        		     <div class="resID">會員編號 : <span id="resIDSp">${resMap.resID}</span></div>
								<div>商家會員圖片預覽</div> 
							</div>
                        	</div>
                            <div class="col-sm-6 "> 
								<img id="resPhotoDiv" width="180px" src="ResPhotoPreviewServlet?resID=${sessionScope.resID}" />
                            </div>
                            <div class="col-sm-6 d-flex" style="justify-content: end;">
                            </div>
                            <div class="col-sm-6">
                                <label for="ownerName" class="col-sm-6 col-form-label">聯絡人姓名</label>
                                <div id="ownerNameDiv" class="col-sm-12 form-control ">${resMap.ownerName} </div>
                            </div>
                              <div class="col-sm-6">
                                <label for="ownerTel" class="col-sm-12 col-form-label">連絡電話</label>
                                <div id="ownerTelDiv" class="col-sm-12 form-control " >${resMap.ownerTel}</div>
                            </div>
                             
                            <div class="col-sm-6">
                                <label for="resName" class="col-sm-6 col-form-label">餐廳名稱</label>
                                <div id="resNameDiv" class="col-sm-12 form-control ">${resMap.resName}</div>
                            </div>
                          
                            <div class="col-sm-6">
                                <label for="uniFormNum" class="col-sm-12 col-form-label">統一編號</label>
                                <div id="resAccountDiv" class="col-sm-12 form-control ">${resMap.resAccount}</div>
                            </div>
                            <div class="col-sm-6">
                                <label for="resPhone" class="col-sm-6 col-form-label">餐廳電話</label>
                                <div id="resTelDiv" class="col-sm-12 form-control ">${resMap.resTel}</div>
                            </div>
                            <div class="col-sm-6">
                                <label for="resCategory" class="col-sm-6 col-form-label">餐廳類型</label>
                                <div id="resCategoryNameDiv" class="col-sm-12 form-control">${resMap.resCategoryName}</div>
                            </div>
                            <div class="col-sm-12">
                                <label for="bzAdd" class="col-sm-12 col-form-label">營業地址</label>
                                <div id="bzLocationDiv" class="col-sm-12 form-control">${resMap.bzLocation}</div>
                            </div>
                            
                        </div>
                    </form>
                    <div class="d-flex justify-content-end mt-5">
                        <a id="btnUpdate" href="ResInfoPreviewOldData">
                        	<button type="button" class="btn btn-outline-dark mf-bdr-15 ml-2">更新</button>
                        </a>
<!--                         <button type="button" id="btnCancel2" class="btn btn-outline-dark mf-bdr-15 ml-2">取消 -->
<!--                         </button> -->
                    </div>

                </div>
                <jsp:include page="resprofile-footer.jsp" />
            </div>
        </div>
    </div>
</body>

</html>