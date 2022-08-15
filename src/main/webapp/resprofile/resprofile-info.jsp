<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>商家資訊</title>
    <link rel="stylesheet" href="<c:url value='/assets/vendors/file-input/css/bootstrap-icons.min.css' />"
        crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css">
    <link href="<c:url value='/assets/vendors/file-input/css/fileinput.min.css' />" media="all" rel="stylesheet"
        type="text/css" />

<%--     <script src="<c:url value='/assets/js/jQuery-3.6.0.js' />"></script> --%>
    <script src="<c:url value='/assets/js/resprofile-js/twzipcode.js' />"></script>
    <script src="<c:url value='/assets/vendors/file-input/buffer.min.js' />" type="text/javascript"></script>
    <script src="<c:url value='/assets/vendors/file-input/piexif.min.js' />" type="text/javascript"></script>
    <script src="<c:url value='/assets/vendors/file-input/sortable.min.js' />" type="text/javascript"></script>
    <script src="<c:url value='/assets/vendors/file-input/bootstrap.bundle.min.js' />"></script>
    <script src="<c:url value='/assets/vendors/file-input/fileinput.min.js' />"></script>
    <script src="<c:url value='/assets/vendors/file-input/theme.min.js' />"></script>
    <script src="<c:url value='/assets/vendors/file-input/zh-TW.js' />"></script>
    <script src="<c:url value='/assets/js/resprofile-js/resprofile-info.js' />"></script>
    <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>

	 <style>
	     span {
	         font-size: small;
	     }
	 </style>

</head>
<script>
 $(document).ready(function () {
 	
     let twzipcode;
     // 取得地方區域
     function init() {
         twzipcode = new TWzipcode('#twzipcode');
         $('#ownerName').on('blur', checkInputOwnerName);
         $('#resPhone').on('blur', checkInputResPhone);
         $('#bzAdd').on('blur', checkInputBzAdd);
         
         <c:if test="${not empty resVO.zipCode}">
			twzipcode.set(${resVO.zipCode});
			<c:if test="${not empty resVO.bzLocation}" >
				const countryDistrict = twzipcode.get('county') + twzipcode.get('district');
				const bzLocation= '${resVO.bzLocation}';
				$('#bzAdd').val(bzLocation.substring(countryDistrict.length));
			</c:if>
         </c:if>
     }
     
     // 上傳圖片
     function fileUpload() {
         $('#resFile').fileinput({
             theme: 'fa6',
             language: 'zh-TW'
         });
     }
     init();
     fileUpload();
     
     $('#btnCancel1').click(function(){
    		$("#fancybox-close").trigger('click'); 
    	});
     
 	 // 上傳圖片
     let files = [];
     $('#resFile').change(function (e) {
         files = Array.from(e.target.files);
     });

     // ajax FormData
     $('#btnCheck').click(function () {
    	
         let formData = new FormData();
         formData.append('ownerName', $('#ownerName').val());
         formData.append('resPhone', $('#resPhone').val());
         formData.append('resCategory', $('#resCategory').val());
         formData.append('country', twzipcode.get('county'));
         formData.append('district', twzipcode.get('district'));
         formData.append('zipcode', twzipcode.get('zipcode'));
         formData.append('bzAdd', $('#bzAdd').val());
         formData.append('resFile', (files.length !== 0) ? files[0] : null);

         $.ajax({
             url: 'ResInfoUpdateServlet',
             type: 'post',
             data: formData,
             contentType: false,
             processData: false,
             dataType: 'JSON',
             success: function (data) {
                console.log(data);
                // success 傳遞的參數去呼叫後端的key
            	const resMap = data.resMap;
            	 
                 // 成功回傳
                 if(resMap) {
                	  // 清空商家 src 圖片
                     $('#resPhoto').attr('src', '');
                     // 更新商家 src 連結
                     $('#resPhoto').attr('src', '/monfood_maven/resprofile/ResPhotoPreviewServlet?resID=' + data.resID + '&time=' + new Date().getTime());
                     
                     // 將剛剛更新成功的資料帶回 resprofile-info-preview.jsp
    	             $('#ownerNameDiv').text(resMap.ownerName);
    	             $('#ownerTel').text(resMap.ownerTel);
    	             $('#resTelDiv').text(resMap.resTel);
    	             $('#bzLocationDiv').text(resMap.bzLocation);
    	             $('#resCategoryNameDiv').text(resMap.resCategoryName);
                     
                     $('#resPhotoDiv').attr('src', '');
                     $('#resPhotoDiv').attr('src', '/monfood_maven/resprofile/ResPhotoPreviewServlet?resID=' + data.resID + '&time=' + new Date().getTime());
                   
                    swal('新增成功', '餐廳資訊已更新', 'success');
                    $("#fancybox-close").trigger('click');
                 }
               
                // 檢核錯誤訊息
                const errorMsg = data.errorMsg;
	          	if(errorMsg) {
	          		$('#checkOwnerNameSp').text(errorMsg.ownerName);
	          		$('#checkResPhoneSp').text(errorMsg.resPhone);
	          		$('#checkBzAddSp').text(errorMsg.bzLocation);
	          		
	          		swal('新增失敗', '請確認是否有空格', 'error');
                }
      
             },error: function(){
            	$("#fancybox-close").trigger('click'); 
             	swal('新增失敗', '請確認是否輸入錯誤', 'error');
             }
         });
     });
 }); 
</script>

<body>
    <div class="container-scroller">
        <div class="container-fluid page-body-wrapper">
            <div class="main-panel">
                <div class="mf-content-wrapper">

                    <!-- 表單區塊 -->
                    <span class="monfood-title mr-1"></span>
                    <h3 style="display: inline-block;">餐廳資訊更新</h3>
                    <form class="jumbotron" method="post" action="">
                        <div class="form-group row">
                            <div class="col-sm-6 ">
                                <span class="resID " name="resID">
                                    會員編號 : ${sessionScope.resID}
                                </span>
                            </div>
                            <div class="col-sm-6 d-flex" style="justify-content: end;">
                            </div>
                            <div class="col-sm-6">
                                <label for="resName" class="col-sm-6 col-form-label">餐廳名稱</label>
                                <input class="col-sm-12 form-control " id="resName" name="resName"
                                    value="${sessionScope.resName}" readonly>
                            </div>
                            <div class="col-sm-6">
                                <label for="ownerTel" class="col-sm-12 col-form-label">連絡電話</label>
                                <div>
                                    <input readonly class="col-sm-12 form-control " id="ownerTel"
                                        name="ownerTel" value="${sessionScope.ownerTel}">
                                </div>
                            </div>
                            <div class="col-sm-6">
                                <label for="uniFormNum" class="col-sm-12 col-form-label">統一編號</label>
                                <div>
                                    <input readonly class="col-sm-12 form-control " id="uniFormNum"
                                        name="uniFormNum" value="${sessionScope.resAccount}">
                                </div>
                            </div>

                            <div class="col-sm-6">
                                <label for="ownerName" class="col-sm-6 col-form-label">聯絡人姓名</label>
                                <input class="col-sm-12 form-control " id="ownerName" name="ownerName"
                                    placeholder="請輸入聯絡人姓名" value="${resVO.ownerName}"><span id="checkOwnerNameSp" style="color:red;"></span>
                            </div>
                            <div class="col-sm-6">
                                <label for="resPhone" class="col-sm-6 col-form-label">餐廳電話</label>
                                <input class="col-sm-12 form-control " id="resPhone" name="resPhone"
                                    placeholder="請輸入餐廳電話" value="${resVO.resTel}"><span id="checkResPhoneSp" style="color:red;"></span>
                                    
                            </div>
                            <div class="col-sm-6">
                                <label for="resCategory" class="col-sm-6 col-form-label">餐廳類型</label>
                                <select id="resCategory" name="resCategory" class="form-select"
                                    aria-label="Default select example">
                                    <option>請選擇</option>
                                    <option value="1" ${(resVO.resCategory==1)?'selected':'' }>台式</option>
                                    <option value="2" ${(resVO.resCategory==2)?'selected':'' }>日式</option>
                                    <option value="3" ${(resVO.resCategory==3)?'selected':'' }>泰式</option>
                                    <option value="4" ${(resVO.resCategory==4)?'selected':'' }>美式</option>
                                    <option value="5" ${(resVO.resCategory==5)?'selected':'' }>韓式</option>
                                    <option value="6" ${(resVO.resCategory==6)?'selected':'' }>飲料咖啡</option>
                                    <option value="7" ${(resVO.resCategory==7)?'selected':'' }>越式</option>
                                </select>

                            </div>
                            <div class="col-sm-12 mb-3">
                                <label for="bzAdd" class="col-sm-12 col-form-label">營業地址</label>
                                <div id="twzipcode" class="d-flex " style="justify-content: space-between;">
                                    <div data-role="zipcode" data-css="form-control">
                                    </div>
                                    <div id="country" data-role="county" data-css="form-control">
                                    </div>
                                    <div id="district" class="ml-1 mr-1" data-role="district"
                                        data-css="form-control">
                                    </div>
                                    <div class="col-sm-9" style="padding: 0;">
                                        <input id="bzAdd" type="text" class="form-control " name="bzAdd"
                                            placeholder="請輸入詳細地址"><span id="checkBzAddSp" style="color:red;" value=""></span>
                                    </div>
                                </div>
                            </div>
                            <div class="file-loading">
                                <input id="resFile" name="resFile" type="file" multiple accept="image">
                            </div>
                            <div class="col-sm-12 d-flex mt-3" style="justify-content: end;">
                            </div>
                        </div>
                    </form>
                    <div class="d-flex justify-content-end mt-5">
                        <button type="button" id="btnCheck" class="btn btn-outline-dark mf-bdr-15">確定</button>
                        <button type="button" id="btnCancel1" class="btn btn-outline-dark mf-bdr-15 ml-2">取消
                        </button>
                    </div>

                </div>
            </div>
        </div>
    </div>
</body>

</html>