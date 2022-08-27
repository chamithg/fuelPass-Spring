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
<h1 class="ms-5 mt-2 mb-1 ps-5 text-info">Edit Vehicle</h1>
<div class="d-flex justify-content-between">
<div class="w-50 p-4 ms-5 card">
<form:form  action="/process/vehicle/update/${currentVehicle.id}" method="post" modelAttribute="currentVehicle">
<input type="hidden" name="_method" value="put">
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
        <form:input path="type" class="form-control" readonly="true"/>
    </p>
    <p>* in order to change vehicle type, you need to delete this and create a new vehicle.</p>
     <p>
        <form:input type="hidden"  path="quota" class="form-control"/>
    </p>
     <p>
        <form:input type="hidden" path="balance" class="form-control"/>
    </p>
   
    <br>      
    <form:input path="user" type="hidden" value="${userId}" class="form-control"/>
   
    <input type="submit" style="width:200px;" class="btn btn-primary" value="Update Vehicle"/>
</form:form>


<br/>

<br/>
<br/>
<div class="d-flex justify-content-between">
<a class="btn btn-warning" style="width:100px;" href="/homeConsumer"> cancel</a>
<a class="btn btn-danger" style="width:200px;" href="/process/vehicle/delete/${currentVehicle.id}"> Delete vehicle</a>
</div>

</div>
</div>

</body>
</html>