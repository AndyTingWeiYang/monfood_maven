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
  <script src="<c:url value='/assets/js/admin-res-reception-js/resReception-Take-away-meal.js' />"></script>
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
<!--             <input type="text" readonly name="resAccount" class="form-control" id="resAccount"> -->
          </div>

          <!-- AccordionBlock Start -->
          <div id="accordionBlock"></div>
          <!-- AccordionBlock End -->
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
                                    <button id="taken" type="submit" class="btn btn-secondary" data-bs-target="#exampleModalToggle3" data-bs-toggle="modal" data-bs-dismiss="modal">YES</button>
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

            <!-- modal2 -->
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
        <!-- modal2 -->

</body>

</html>