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
<script src="/webjars/jquery/jquery.min.js"></script>
<script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
</head>
<body class= "m-5 p-5">
<h1 class="text-info">Hello <c:out value="${name}"/>,  </h1>

<br/>

<br/>
<br/>

<h3 class="text-light mb-5">Vehicles</h3>



<table class="table table-striped table-dark mb-5">
  <thead>
    <tr>
      <th class="p-2" scope="col">Make</th>
      <th class="p-2" scope="col">Model</th>
      <th class="p-2" scope="col">Type</th>
      <th class="p-2" scope="col">License Plate</th>
      <th class="p-2" scope="col">Color</th>
      <th class="p-2" scope="col">Allocated Weekly Fuel Quota (gallons)</th>
      <th class="p-2" scope="col">Remaining Balance (gallons)</th>
      <th class="p-2" scope="col">Actions</th>
      
      
    </tr>
  </thead>
  <tbody>
  <c:forEach items="${vehicles}" var="vehicle">
  <tr>
      <td class="p-2"><c:out value="${vehicle.make}"/></td>
      <td class="p-2"><c:out value="${vehicle.model}"/></td>
      <td class="p-2"><c:out value="${vehicle.type}"/></td>
      <td class="p-2"><c:out value="${vehicle.plate}"/></td>
      <td class="p-2"><c:out value="${vehicle.color}"/></td>
      <td class="p-2" style="width: 200px;"><c:out value="${vehicle.quota}"/></td>
      <td class="p-2" style="width: 200px;"><c:out value="${vehicle.balance}"/></td>
      <td class="p-2" ><a href="/homeConsumer/viewVehicle/${vehicle.id }">View</a><br/>
      <a href="/homeConsumer/editVehicle/${vehicle.id }">Edit</a><br/>
      <a href="/homeConsumer/editQuota/${vehicle.id }">Enhance quota</a></td>
    </tr>
  </c:forEach>
    <tr>
  </tbody>
</table>
<a class="btn btn-secondary mb-5" href="homeConsumer/addVehicle">Add Vehicle +</a>
<br/>
<a class="btn btn-warning" href="/process/consumer/logout">Log Out</a>



</body>
</html>