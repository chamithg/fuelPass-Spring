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

<h1 class="text-info text-center" >Welcome to National Fuel Pass System</h1>
<br/>
<br/>
<br/>
<div>
<h2 class="text-light text-center" >Who are You?</h2>
<div class="d-flex justify-content-center">
<a href="/fillingStation" style="width:250px;" class="btn btn-warning btn-lg m-5">Filling Station Manager</a>
<a href="/consumer" style="width:250px;" class="btn btn-info btn-lg m-5">Consumer</a>
</div>
</div>
</body>
</html>