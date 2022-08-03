<%@page import="com.model.del.DelVO"%>
<%@page import="com.model.del.DelService"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.model.del.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
    DelService delSvc = new DelService();
    List<DelVO> list = delSvc.getAll();
    pageContext.setAttribute("list", list);
%>


<html>
<head>
<title>所有員工資料 - listAllEmp.jsp</title>

<style>
  table#table-1 {
	background-color: #CCCCFF;
    border: 2px solid black;
    text-align: center;
  }
  table#table-1 h4 {
    color: red;
    display: block;
    margin-bottom: 1px;
  }
  h4 {
    color: blue;
    display: inline;
  }
</style>

<style>
  table {
	width: 800px;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
  }
  table, th, td {
    border: 1px solid #CCCCFF;
  }
  th, td {
    padding: 5px;
    text-align: center;
  }
</style>

</head>
<body bgcolor='white'>

<table id="table-1">
	<tr><td>
		 <h3>所有員工資料 - listAllEmp.jsp</h3>
		 <h4><a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<th>外送員編號</th>
		<th>外送員姓名</th>
		<th>帳號</th>
		<th>密碼</th>
		<th>TEL</th>
		<th>生日</th>
		<th>車牌</th>
		<th>狀態</th>
		<th>更新時間</th>
		<th>身分證</th>
		<th>行照</th>
		<th>駕照</th>
		<th>良民證</th>
		<th>強制險</th>
		<th>銀行帳戶</th>
		<th>銀行名稱</th>
		<th>銀行代碼</th>
		<th>銀行帳號</th>
		<th>修改</th>
		<th>刪除</th>
	</tr>
	<%@ include file="page01.file" %> 
	<c:forEach var="delVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>" >
		
		<tr>
			<td>${delVO.delID}</td>
			<td>${delVO.delName}</td>
			<td>${delVO.delAccount}</td>
			<td>${delVO.delPassword}</td>
			<td>${delVO.delTel}</td>
			<td><fmt:formatDate value="${delVO.delBirthday}" pattern="yyyy-MM-dd HH:mm:ss.SSS"/></td>
			<td>${delVO.platenumber}</td>
			<td>${delVO.status}</td>
			<td><fmt:formatDate value="${delVO.updateTime}" pattern="yyyy-MM-dd HH:mm:ss.SSS"/></td>
			<td><img src="${pageContext.request.contextPath}/DBGifReader2?id=2"></td>
			<td><img src="${pageContext.request.contextPath}/DBGifReader2?id=2"></td>
			<td><img src="${pageContext.request.contextPath}/DBGifReader2?id=2"></td>
			<td><img src="${pageContext.request.contextPath}/DBGifReader2?id=2"></td>
			<td><img src="${pageContext.request.contextPath}/DBGifReader2?id=2"></td>
			<td>${delVO.delAccountName}</td>
			<td>${delVO.delBankname}</td>
			<td>${delVO.delBankcode}</td>
			<td>${delVO.delBankaccount}</td>
			
		</tr>
	</c:forEach>
</table>
<%@ include file="page02.file" %>

</body>
</html>