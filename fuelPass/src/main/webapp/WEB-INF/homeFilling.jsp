<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isErrorPage="true" %>   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
<!-- YOUR own local CSS -->
<link rel="stylesheet" href="/css/style.css"/>
<!-- For any Bootstrap that uses JS or jQuery-->
<script type="text/javascript" src="/js/app.js"></script>
<script src="/webjars/jquery/jquery.min.js"></script>
<script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
</head>
<body class= "m-5 p-5" >
<h1 class="text-info">Hello, welcome to the home page of <span class="text-warning"><c:out value="${name}"/></span>  filling station!</h1>

<br/>

<br/>

<h2 class="text-light">Current fuel Stock: <span id="updatedStock" class="text-warning"><c:out value="${station.getStock()-issued}"/></span> gal.</h2>
<br/>
<br/>
<a class= "btn btn-warning btn-lg text-danger" href="/homeFilling/issueFuel">Issue Fuel</a>
<br/>
<br/>



<h3 class="text-info">Transactions</h3>

<table class="table table-light table-striped">
  <thead>
    <tr>
      <th scope="col">Date and Time</th>
      <th scope="col">Make</th>
      <th scope="col">Model</th>
      <th scope="col">Type</th>
      <th scope="col">License Plate</th>
      <th scope="col">Issued fuel quantity (gal)</th>
    </tr>
  </thead>
  <tbody>
 <c:forEach items="${receipts}" var="receipt">
  <tr>
  	  <td style="width:350px"><c:out value="${receipt.getCreatedAt().toString().substring(0, 19)}"/></td>
  	  <td><c:out value="${receipt.getVehicle().make}"/></td>
  	  <td><c:out value="${receipt.getVehicle().model}"/></td>
  	  <td><c:out value="${receipt.getVehicle().type}"/></td>
      <td><c:out value="${receipt.getVehicle().plate}"/></td>
      <td style="width:200px"><c:out value="${receipt.getAmount()}"/></td>
    </tr>
  </c:forEach> 
    <tr>
  </tbody>
</table>

<br/>
<a class="btn btn-lg btn-warning" href="/process/filling/logout">Log Out</a>
<br/>

</body>
</html>