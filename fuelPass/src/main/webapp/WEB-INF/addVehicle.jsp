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
<body class= "ms-5 ps-5">
<h1 class="ms-5 mt-2 mb-1 ps-5 text-info">Add Vehicle</h1>
<div class="d-flex justify-content-between">
<div class="w-50 p-4 ms-5 card">
<form:form  action="/process/vehicle/create" method="post" modelAttribute="newVehicle">
    <p>
        <form:label path="make">Make</form:label>
        <br>
        <form:errors path="make" class="text-danger"/>
        <br>
        <form:input path="make" class="form-control"/>
    </p>
     <p>
        <form:label path="model">Model</form:label>
        <br>
        <form:errors path="model" class="text-danger"/>
        <br>
        <form:input path="model" class="form-control"/>
    </p>
     <p>
        <form:label path="color">Color</form:label>
        <br>
        <form:errors path="color" class="text-danger"/>
        <br>
        <form:input path="color" class="form-control"/>
    </p>
     <p>
        <form:label path="plate">License Plate</form:label>
        <br>
        <form:errors path="plate" class="text-danger"/>
        <br>
        <form:input path="plate" class="form-control"/>
    </p>
    <p>
        <form:label path="type">Type:</form:label>
        <br>
        <form:errors path="type" class="text-danger"/>
        <br>
        <form:select path="type" onChange="decideQuota();" id="type" class="form-control">
        <option value="">--Select option--</option>
        <option value="car">Motor Car</option>
        <option value="cycle">Motor Cycle</option>
        <option value="suv">SUV</option>
        <option value="truck">Truck</option>
        <option value="hybrid">Hybrid Motor Vehicles</option>
        </form:select>
    </p>
     <p>
        <form:input type="hidden" id="quota" path="quota" class="form-control"/>
    </p>
     <p>
        <form:input type="hidden" id="balance" path="balance" class="form-control"/>
    </p>
    <br>
    <h3 class="text-info">Your default weekly fuel allocation is <span class="text-danger" id="quotaDisplay">--</span> gallons.</h3>
    <br/>
    <h4>This was decided based on your vehicle type.</h4>
     <br/>
    <h4>If you not satisfied with it you can change it later once your vehicle is registered.</h4>
    <br/>
    
        
    <form:input path="user" type="hidden" value="${userId}" class="form-control"/>
   
    <input type="submit" class="btn btn-primary" value="Register Vehicle"/>
</form:form>


<br/>
<a class="btn btn-danger" style="width:100px;" href="/homeConsumer"> cancel</a>


</div>
</div>

</body>
</html>