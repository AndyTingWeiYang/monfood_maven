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
  <script src="<c:url value='/assets/js/admin-res-reception-js/resReception-order-record.js'/>"></script>
 

</head>

<body>
  <div class="container-scroller">
  <div><jsp:include page="resReception-sidebar.jsp" /></div>
    <div class="container-fluid page-body-wrapper">
    <jsp:include page="resReception-header.jsp" />
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
        <jsp:include page="resReception-footer.jsp" />
      </div>

    </div>

  </div>

  
  
</body>

</html>