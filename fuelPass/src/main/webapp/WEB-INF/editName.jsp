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
<h1>Edit aName</h1>
<div class="d-flex justify-content-between">
<div class="w-75">
<form:form class="w-50" action="/process/update/${currentName.id} " method="POST" modelAttribute="currentName">
	<input name ="_method" type="hidden" value="put"/>
    <p>
        <form:label path="name">New Name</form:label>
        <br>
        <form:errors path="name" class="text-danger"/>
        <br>
        <form:input path="name" class="form-control" readonly="true"/>
    </p>
    
    <p>
        <form:label path="gender">Typical Gender: </form:label>
        <br>
        <form:errors path="gender" class="text-danger"/>
        <br>
        <form:select path="gender" class="form-control">
      
        <option value="">--Select option--</option>
        <option value="Neutral">Neutral</option>
        <option value="Male">Male</option>
        <option value="Female">Female</option>
        </form:select>
    </p>
    
    <p>
        <form:label path="origin">Origin:</form:label>
        <br>
        <form:errors path="origin" class="text-danger"/>
        <br>
        <form:input path="origin" class="form-control"/>
    </p>
    
    
    <p>
        <form:label path="meaning">Meaning</form:label>
        <br>
        <form:errors path="meaning" class="text-danger"/>
        <br>
        <form:textarea path="meaning" class="form-control"/>
    </p>
    
    <form:input path="user" type="hidden" value="${userId}" class="form-control"/>
   
    <input type="submit" class="btn btn-primary" value="Update"/>
</form:form>

<a href="/home"> cancel</a>

<br/>

<c:if test="${currentName.getUser().id == userId }">
<a href="/process/delete/${currentName.id}"> Delete Name</a>

</c:if>



</div>
</div>



</body>
</html>