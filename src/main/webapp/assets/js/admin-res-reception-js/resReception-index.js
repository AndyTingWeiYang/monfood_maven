   // window.addEventListener("load", function(event) {
    //     let page1 = document.getElementsByClassName('nav-link');
    //     page1[0].classList.add("focusS");

    // });

$(function () {

 /*=================================================*/   
        $(".tp1").timepicker({
            timeFormat: 'HH:mm',
            interval: 15
        });
/*==================================================*/
        $("#submit").click(function(){
            const check=$("input[name='Checkbox']:checked").length;
                if(check != 0){
                    alert("修改成功");
                    return true;
                }else{
                 alert("請選擇營業周期!");
                    return false;
                    }
            }
        );
});
/*===================================================*/

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
/*======================================================*/



