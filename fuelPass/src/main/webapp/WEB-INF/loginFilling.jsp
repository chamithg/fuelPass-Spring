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
<body class="m-5 p-5">
<a class="btn btn-warning ms-5" href="/">back to home..</a>
<h1 class="text-info text-center">Login/Register as a filling station manager</h1>
<div class="d-flex justify-content-center">
<div class="m-5 p-5 card" style="width:30vw;">
<h3>Register</h3>
<form:form class="w-100" action="/process/filling/registration" method="post" modelAttribute="newStation">
   
    
    <p>
        <form:label path="name">Name</form:label>
        <br>
        <form:errors path="name" class="text-danger"/>
        <br>
        <form:input path="name" class="form-control"/>
    </p>
    
    <p>
        <form:label path="email">Email</form:label>
        <br>
        <form:errors path="email" class="text-danger"/>
        <br>
        <form:input path="email" class="form-control"/>
    </p>
    <p>
        <form:label path="address">Address</form:label>
        <br>
        <form:errors path="address" class="text-danger"/>
        <br>
        <form:input path="address" class="form-control"/>
    </p>
    <p>
        <form:label path="registration">Registration Number</form:label>
        <br>
        <form:errors path="registration" class="text-danger"/>
        <br>
        <form:input path="registration" class="form-control"/>
    </p>
    
    <p>
        <form:label path="stock">Current Stock (gallons)</form:label>
        <br>
        <form:errors path="stock" class="text-danger"/>
        <br>
        <form:input path="stock" class="form-control"/>
    </p>
    <p>
        <form:label path="password">Password</form:label>
        <br>
        <form:errors path="password" class="text-danger"/>    
        <br> 
        <form:input path="password" type="password"  class="form-control"/>
    </p>  
    <p>
        <form:label path="confirmPassword">Confirm Password</form:label>
        <br>
        <form:errors path="confirmPassword" class="text-danger"/>
        <br>
        <form:input path="confirmPassword" type="password" class="form-control"/>
    </p>  
    <input type="submit" class="btn btn-primary" value="Register"/>
</form:form>
</div>
<div class="m-5 p-5 card" style="width:30vw">
<h3>Log in</h3>
<form:form  action="/process/filling/login" method="POST" modelAttribute="loginStation" >
    <p>
        <form:label path="email">Email</form:label>
        <br>
        <form:errors path="email" class="text-danger"/>
        <br>
        <form:input path="email" class="form-control"/>
    </p>
    <p>
        <form:label path="password">Password</form:label>
        <br>
        <form:errors path="password" class="text-danger"/>    
        <br> 
        <form:input path="password"  type="password" class="form-control"/>
    </p>  
    <input type="submit" class="btn btn-primary" value="Login"/>
</form:form>

</div>
</div>



</body>
</html>