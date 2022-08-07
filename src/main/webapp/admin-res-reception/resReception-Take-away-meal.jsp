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
	
  <script src="<c:url value='/assets/js/jQuery-3.6.0.js'/>"></script>
  <link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/timepicker/1.3.5/jquery.timepicker.min.css">
  <script src="//cdnjs.cloudflare.com/ajax/libs/timepicker/1.3.5/jquery.timepicker.min.js"></script>
  <link rel="stylesheet" href="<c:url value="/assets/css/res-reception-css/resReception-meal-preparation.css" />">
</head>

<body>
  <div class="container-scroller">
  	<div><jsp:include page="resReception-sidebar.jsp"></jsp:include></div>
    <div class="container-fluid page-body-wrapper">
    <jsp:include page="resReception-header.jsp"></jsp:include>
      <div class="main-panel">
        <div class="mf-content-wrapper" id="mf-res">

          <!-- 表單區塊 -->
          <label for="resAccount" class="col-sm-12 col-form-label"></label>
          <div class="col-sm-12">
            <input type="text" readonly name="resAccount" class="form-control" id="resAccount">
          </div>

          <div class="accordion" id="accordionExample">
            <div class="accordion-item">
              <h2 class="accordion-header" id="headingOne">
                <button class="accordion-button" type="button" data-bs-toggle="collapse" data-bs-target="#collapseOne"
                  aria-expanded="true" aria-controls="collapseOne">
                  請點擊查看訂單內容
                </button>
              </h2>
              <div id="collapseOne" class="accordion-collapse collapse" aria-labelledby="headingOne"
                data-bs-parent="#accordionExample">
                <div class="accordion-body">
                  <div class="mf-order-order-test">
                    <div>
                      <div>訂購會員:<span>01603</span></div>
                      <div>訂單編號:<span>0603</span></div>
                      <div class="mf-order-model">訂單管理模式</div>
                    </div>
                    <div>
                      <div><span>TibaMe牛肉麵</span>(外送)</div>
                    </div>
                    <div>
                      <div>訂購日期:<span>2022-06-04</span></div>
                      <div>訂購時間:<span>12:20</span></div>
                    </div>
                  </div>
                  <hr>
                  <div class="mf-order-test1 ">
                    <div class="list-group " >
                      <ul class="list-group list-group-horizontal ">
                        <li class="list-group-item flex-balance"><span>招牌紅燒牛肉麵(細)X1</span></li>
                        <li class="list-group-item flex-balance"><span>NT$140.00</span></li>
                        <li class="list-group-item flex-balance -off"><input type="checkbox" name="" id="">已售完</li>
                      </ul>
                      <ul class="list-group list-group-horizontal">
                        <li class="list-group-item flex-balance"><span>加大X1</span></li>
                        <li class="list-group-item flex-balance"><span>NT$10.00</span></li>
                        <li class="list-group-item flex-balance"><input type="checkbox" name="" id="">已售完</li>
                      </ul>
                      <ul class="list-group list-group-horizontal">
                        <li class="list-group-item flex-balance"><span>滷蛋X2</span></li>
                        <li class="list-group-item flex-balance"><span>NT$20.00</span></li>
                        <li class="list-group-item flex-balance"><input type="checkbox" name="" id="">已售完</li>
                      </ul>
                      <ul class="list-group list-group-horizontal">
                        <li class="list-group-item flex-balance"><span>燙青菜X1</span></li>
                        <li class="list-group-item flex-balance"><span>NT$30.00</span></li>
                        <li class="list-group-item flex-balance"><input type="checkbox" name="" id="">已售完</li>
                      </ul>
                      <div>
                        <ul class="mf-order-notes">
                          <li>訂單備註:</li>
                          <div class="mf-order-notes-1">
                            <li><span>麵要特辣</span></li>
                            <li><span>燙青菜不要加醬油</span></li>
                          </div>
                        </ul>
                      </div>
                      <div class="mf-order-delay">訂單延遲:
                        <input type="Radio" class="btn-check" name="shop_status" id="secondary-outlined-17"
                          autocomplete="off">
                        <label class="btn btn-outline-secondary" for="secondary-outlined-17">+10分鐘</label>

                        <input type="Radio" class="btn-check" name="shop_status" id="danger-outlined-18"
                          autocomplete="off">
                        <label class="btn btn-outline-secondary" for="danger-outlined-18">+20分鐘</label>
                        <input type="Radio" class="btn-check" name="shop_status" id="danger-outlined-19"
                          autocomplete="off">
                        <label class="btn btn-outline-secondary" for="danger-outlined-19">+30分鐘</label>
                      </div>
                      <div class="mf-order-noies-2">
                        <button type="button" class="btn btn-secondary">返回</button>
                        <span>注意!! 點選已取餐系統自動將訂單移入訂單紀錄</span>
                      </div>
                    </div>
                    <div>
                      <div class="mf-order-test1-2">
                        <ul class="mf-order-test1-1-1">
                          <li>統一編號:<span>24708053</span></li>
                          <li class="mf-order-test1-3">是否需要餐具:<span>否</span> </li>
                          <li>總額:<span>NT$ 200.00</span></li>
                          <li>距離取餐時間:<span>7:00</span></li>
                        </ul>
                      </div>
                      <div class="mf-order-test2-1">
                        <p>訂單狀態:</p>
                        <div class="mf-order-model-1"><span>外送員媒合中</span></div>
                      </div>
                      <div>
                        <button type="button" class="btn btn-secondary" data-bs-toggle="modal" href="#exampleModalToggle" role="button">已取餐</button>
                      </div>
                      <div class="mf-order-test2-2">
                        <button type="button" class="btn btn-secondary">修改</button>
                        <button button type="button" class="btn btn-secondary">取消訂單</button>
                      </div>
                    </div>
                  </div>     
                </div>
              </div>
            </div>

            <br>


          </div>


        </div>
        <jsp:include page="resReception-footer.jsp"></jsp:include>
      </div>

    </div>

  </div>
     <!-- modal1 -->
             <div class="modal fade" id="exampleModalToggle" aria-hidden="true" aria-labelledby="exampleModalToggleLabel"
             tabindex="-1">
             <div class="modal-dialog modal-dialog-centered">
                 <div class="modal-content">
                     <div class="modal-header">
                         <h5 class="modal-title" id="exampleModalToggleLabel">
                             <span class="logo">Mon</span><span class="logo2">Food</span>
                         </h5>
                     </div>
                     <div class="modal-body">
                         <div class="container">
                             
                             <div class="row">
                                 <div class="col d-flex justify-content-center">
                                     <div style="margin: 20px 0 70px 0 ;"><h4>外送員已取餐並移入訂單紀錄</h4></div>
                                 </div>
                             </div>
                             <div class="row">
                                 <div class="col d-flex justify-content-center">
                                    <button type="submit" class="btn btn-secondary" data-bs-target="#exampleModalToggle3" data-bs-toggle="modal" data-bs-dismiss="modal">YES</button>
                                 </div>
                                 <div class="col d-flex justify-content-center">
                                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal" aria-label="Close" >NO</button>
                                 </div>
                             </div>
                          
                         </div>
                     </div>
                     <div class="modal-footer"></div>
                 </div>
             </div>
         </div>
         <!-- modal1 -->

            <!-- modal3 -->
            <div class="modal fade" id="exampleModalToggle3" aria-hidden="true" aria-labelledby="exampleModalToggleLabel3"
            tabindex="-1">
            <div class="modal-dialog modal-dialog-centered">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalToggleLabel3">
                            <span class="logo">Mon</span><span class="logo2">Food</span>
                        </h5>
                    </div>
                    <div class="modal-body">
                        <div class="container">
                            <div class="row justify-content-center">
                                <svg xmlns="http://www.w3.org/2000/svg" width="100" height="100" fill="currentColor"
                                    class="bi bi-check2-circle" viewBox="0 0 16 16">
                                    <path
                                        d="M2.5 8a5.5 5.5 0 0 1 8.25-4.764.5.5 0 0 0 .5-.866A6.5 6.5 0 1 0 14.5 8a.5.5 0 0 0-1 0 5.5 5.5 0 1 1-11 0z" />
                                    <path
                                        d="M15.354 3.354a.5.5 0 0 0-.708-.708L8 9.293 5.354 6.646a.5.5 0 1 0-.708.708l3 3a.5.5 0 0 0 .708 0l7-7z" />
                                </svg>
                            </div>
                            <div class="row text-center">
                                <h2>已經成功移入訂單紀錄!</h2>
                            </div>
                        </div>
                    </div>
        
                </div>
            </div>
        </div>
        <!-- modal3 -->

</body>

</html>