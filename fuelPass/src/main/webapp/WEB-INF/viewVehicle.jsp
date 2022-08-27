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
<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/3.5.1/chart.min.js"></script>
<script src="/webjars/jquery/jquery.min.js"></script>
<script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
</head>
<body class= "m-5 p-5" onload="drawChart()">
<div class="d-flex justify-content-between">
<div class="card p-5 w-25">
<h1 class="text-info"><c:out value="${vehicle.plate}"/></h1>
<br/>
<h3>Make : <span class="text-info"><c:out value="${vehicle.getMake()}"/></span> </h3>
<h3>Model : <span class="text-info"><c:out value="${vehicle.getModel()}"/></span> </h3>
<h3>Type : <span class="text-info"><c:out value="${vehicle.getType()}"/></span> </h3>
<h3>Color : <span class="text-info"><c:out value="${vehicle.getColor()}"/></span> </h3>
<br/>
<h3>fuel quota(week) : <span  id="quota" class="text-warning"><c:out value="${vehicle.getQuota()}"/></span> gal.</h3>
<br/>
<h3>Available Balance : <span id="balance"  class="text-danger"><c:out value="${vehicle.getBalance()}"/></span> gal.</h3>
<br/>
<canvas
   id="myChart1"
   style="max-width: 300px; max-height: 300px"
   class="mb-2 mt-2 pie_chart"
></canvas>
<a class="btn btn-warning mt-2" href="/homeConsumer">Go back</a>
</div>
<div class="w-75 ms-5"> 
<h3 class="text-info">Transactions</h3>

<table class="table table-light table-striped">
  <thead>
    <tr>
      <th scope="col">Date and Time</th>
      <th scope="col">Filling Station </th>
      <th scope="col">Address</th>
      <th scope="col">Received fuel quantity (gal)</th>
    </tr>
  </thead>
  <tbody>
 <c:forEach items="${receipts}" var="receipt">
  <tr>
  	  <td><c:out value="${receipt.getCreatedAt().toString().substring(0, 19)}"/></td>
  	  <td><c:out value="${receipt.getStation().name}"/></td>
  	  <td><c:out value="${receipt.getStation().address}"/></td>
      <td><c:out value="${receipt.getAmount()}"/></td>
    </tr>
  </c:forEach> 
    <tr>
  </tbody>
</table>
  <input id="text" type="hidden" 
            value="${vehicle.plate}" style="Width:20%"
            onblur='generateBarCode();' /> 

    <!--   <img id='barcode' 
            src="https://api.qrserver.com/v1/create-qr-code/?data=HelloWorld&amp;size=100x100" 
            alt="" 
            title="HELLO" 
            width="50" 
            height="50" /> -->
</div>
</div>
<script src="https://cdn.jsdelivr.net/npm/chart.js@3.8.0/dist/chart.min.js"></script>
</body>
</html>