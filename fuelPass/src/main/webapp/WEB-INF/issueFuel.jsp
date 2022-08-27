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
<body class="m-5 p-5">
<h1 class="text-info">Issue Fuel</h1>

<form class="w-25" action ="/homeFilling/retrieveCar" method="post">
<br/>
<br/>
<label class="text-lg text-white"for="plate">Enter Vehicle license plate:</label>
<br/>
<input id="plate" name="plate" type="text" class="form-control"/>
<br/>
<input class="btn btn-warning btn-lg" type="submit" value="Retrieve Data"/>
<br/>
<h4 class="text-danger"><c:out value="${error}"/></h4>


</form>
<c:if test="${vehicle!=null}">

<div class="d-flex justify-content-center m-5 p-5">
<div class="card d-flex justify-content-center">
<div class="d-flex justify-content-center" >
<div class="data p-5">
<br/>
<h3>License Plate: <span class="text-info"><c:out value="${vehicle.getPlate()}"/></span>  </h3>
<br/>
<h3>Make : <span class="text-info"><c:out value="${vehicle.getMake()}"/></span> </h3>
<h3>Model : <span class="text-info"><c:out value="${vehicle.getModel()}"/></span> </h3>
<h3>Type : <span class="text-info"><c:out value="${vehicle.getType()}"/></span> </h3>
<h3>Color : <span class="text-info"><c:out value="${vehicle.getColor()}"/></span> </h3>
<br/>
<h3>Total fuel Allocation : <span class="text-warning"><c:out value="${vehicle.getQuota()}"/></span> </h3>
<br/>
<h3>Available Balance : <span class="text-danger"><c:out value="${vehicle.getBalance()}"/></span> </h3>
<br/>

</div>
<div class="issueForm p-5">
<form:form class="w-75" action="/process/receipt/create" method="post" modelAttribute="receipt">
 	<p class="pt-5">
        <form:label path="amount">fuel amount (this should not exceed available balance)</form:label>
        <br>
        <form:errors path="amount" class="text-danger"/>
        <br>
        <form:input path="amount" class="form-control" type="number"/>
    </p>
 	<p>
     
        <form:errors path="vehicle" class="text-danger"/>
        <br>
        <form:input type="hidden" id="vehicle" value="${vehicle.getId()}" path="vehicle" class="form-control"/>
    </p>
     <p>
        
        <form:errors path="station" class="text-danger"/>
        <br>
        <form:input type="hidden" id="station" path="station" value="${stationId}" class="form-control"/>
    </p>
    
    <input type="submit" class="btn btn-primary" value="Confirm Tansaction"/>


</form:form>
</div>
</div>
</div>
</div>

</c:if>


</body>
</html>