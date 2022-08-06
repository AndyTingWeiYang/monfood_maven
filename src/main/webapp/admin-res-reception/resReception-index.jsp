<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>MonFood-Demo</title>
    <script src="<c:url value='/assets/js/jQuery-3.6.0.js' />"></script>
    <link rel="stylesheet" href="<c:url value='/assets/css/res-reception-css/timepicker.css' />">
    <script src="<c:url value='/assets/js/admin-res-reception-js/timepicker.js' />"></script>

</head>

<script>
// $(function() {
// 	var dataMap = ;
// 	//選中每個value與陣列中value相同的checkbox
// 	$.each(dataMap, function (i,item) {
// 		$("input[name=bzWeekTime][value="+item+"]").attr("checked","checked");		
// 	});
// });


$(document).ready(function() {
	$.ajax({
		url: 'FindResByIdServlet',
		method: 'post',
		dataType: 'json',
		success: function(data) {
			// 回傳成功
			console.log(data);
			const resVO = data.resVO;
			
			// bzOpenHours: "12:15:00 上午"
			// bzCloseHours: "12:30:00 上午"
			// TODO: 套件塞值無效(以解決) 少了#抓id (但顯示是12小時制...無言) 
			$('#bzOpenHours').val(resVO.bzOpenHours);
			$('#bzCloseHours').val(resVO.bzCloseHours);
			
			const bzWeekTime = resVO.bzWeekTime.split(',');
			$.each(bzWeekTime, function(index, element) {
				$("input[name=bzWeekTime][value=" + element + "]").attr("checked","checked");	        
		       
			});
			
		}
	});
});

	
</script>
<body>
    <div class="container-scroller">
    	<div><jsp:include page="resReception-sidebar.jsp" /></div>
        <div class="container-fluid page-body-wrapper">
        	<jsp:include page="resReception-header.jsp" />
            <div class="main-panel">
                <div class="mf-content-wrapper" id="mf-res-setting">

                    <!-- 表單區塊 -->
					<form method="post" action="UpdateResReceptionServlet">
                    <label for="resAccount" class="col-sm-12 col-form-label"></label>
                    <div class="col-sm-12">
                        <input type="text" readonly name="resAccount" class="form-control" id="resAccount">
                    </div>
                    <div>營業時間設定(系統預設星期一到星期五 上午8:00到下午17:00)</div>
                    <div class="">星期設定:
                        <input type="checkbox" class="btn-check" name="bzWeekTime" value="日" id="btn-check-outlined-7" autocomplete="off">
                        <label class="btn btn-outline-secondary" for="btn-check-outlined-7">日</label>

                        <input type="checkbox" class="btn-check" name="bzWeekTime" value="一" id="btn-check-outlined-1" autocomplete="off">
                        <label class="btn btn-outline-secondary" for="btn-check-outlined-1">一</label>

                        <input type="checkbox" class="btn-check" name="bzWeekTime" value="二" id="btn-check-outlined-2" autocomplete="off">
                        <label class="btn btn-outline-secondary" for="btn-check-outlined-2">二</label>

                        <input type="checkbox" class="btn-check" name="bzWeekTime" value="三" id="btn-check-outlined-3" autocomplete="off">
                        <label class="btn btn-outline-secondary" for="btn-check-outlined-3">三</label>

                        <input type="checkbox" class="btn-check" name="bzWeekTime" value="四" id="btn-check-outlined-4" autocomplete="off">
                        <label class="btn btn-outline-secondary" for="btn-check-outlined-4">四</label>

                        <input type="checkbox" class="btn-check" name="bzWeekTime" value="五" id="btn-check-outlined-5" autocomplete="off">
                        <label class="btn btn-outline-secondary" for="btn-check-outlined-5">五</label>

                        <input type="checkbox" class="btn-check" name="bzWeekTime" value="六" id="btn-check-outlined-6" autocomplete="off">
                        <label class="btn btn-outline-secondary" for="btn-check-outlined-6">六</label>
                    </div>

                    <div>
                        <div class="timeSet">
                            <div>營業時段設定:</div>
                            <div class="input-group bootstrap-timepicker timepicker inp-mf">
                                <h3>上午:</h3>
                                <input type="text" class="form-control input-small tp1" id="bzOpenHours" name="bzOpenHours" value="" required="required">
                                <span class="input-group-addon"><i class="glyphicon glyphicon-time"></i></span>
                            </div>
                            <div class="input-group bootstrap-timepicker timepicker inp-mf">
                                <h3>下午:</h3>
                                <input type="text" class="form-control input-small tp1" id="bzCloseHours" name="bzCloseHours" value="" required="required" >
                                <span class="input-group-addon"><i class="glyphicon glyphicon-time"></i></span>
                            </div>
                        </div>
                    </div>
                    <br>
                    <div>營業模式設定</div>
                    <br>
                    <div>
                        <input type="radio" class="btn-check" name="shop_status" value="0" id="secondary-outlined-9" checked
                            autocomplete="off">
                        <label class="btn btn-outline-secondary" for="secondary-outlined-9">預設正常</label>

                        <input type="radio" class="btn-check" name="shop_status" value="1" id="danger-outlined-16"
                            autocomplete="off">
                        <label class="btn btn-outline-danger" for="danger-outlined-16">忙碌模式</label>
                        <input type="radio" class="btn-check" name="shop_status" value="2" id="danger-outlined-8"
                            autocomplete="off">
                        <label class="btn btn-outline-danger" for="danger-outlined-8">暫停接單</label>
                    </div>
                    <br>
                    <div>備餐時間設定(系統預設20分鐘)</div>
                    <br>
                    <div class="">
                        <input type="radio" class="btn-check" name="prepare_time" id="secondary-outlined-15" checked
                            autocomplete="off">
                        <label class="btn btn-outline-secondary" for="secondary-outlined-15">20分鐘</label>

                        <input type="radio" class="btn-check" name="prepare_time" id="danger-outlined-11"
                            autocomplete="off">
                        <label class="btn btn-outline-danger" for="danger-outlined-11">25分鐘</label>

                        <input type="radio" class="btn-check" name="prepare_time" id="danger-outlined-12"
                            autocomplete="off">
                        <label class="btn btn-outline-danger" for="danger-outlined-12">30分鐘</label>

                        <input type="radio" class="btn-check" name="prepare_time" id="danger-outlined-13"
                            autocomplete="off">
                        <label class="btn btn-outline-danger" for="danger-outlined-13">35分鐘</label>

                        <input type="radio" class="btn-check" name="prepare_time" id="danger-outlined-14"
                            autocomplete="off">
                        <label class="btn btn-outline-danger" for="danger-outlined-14">40分鐘</label>
						<input type="hidden" name="action" value="update" />
                    </div>
                    <br>
                    <div class="grid">
                        <div class="g-col-6">
                            <button type="submit" id="submit" class="btn btn-secondary mx-1">儲存</button>
                            <button type="button" class="btn btn-secondary mx-1">取消</button>
                            <button type="reset" class="btn btn-secondary mx-1">回復原廠設定</button>
                        </div>
                    </div>
					</form>
                </div>
			
				<jsp:include page="resReception-footer.jsp" />
            </div>
        </div>

    </div>
    <script>

    $(function () {
        $(".tp1").timepicker({
            timeFormat: 'HH:mm:ss',
            interval: 15
        });
        
        $("#submit").click(function(){
            const check=$("input[name='bzWeekTime']:checked").length;
            if(check != 0){
                alert("修改成功");
                return true;
            }else{
                alert("請選擇營業星期!");
                return false;
            	}
        	}

       	);
        
        
        
        
        
	});


    </script>
</body>

</html>