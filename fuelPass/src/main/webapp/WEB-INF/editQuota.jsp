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
<h1 class="ms-5 mt-2 mb-1 ps-5 text-info">Enhance Fuel Quota</h1>
<div class="">

<h4 class="ms-5 mt-5 mb-1 ps-5 text-light">* If the given quota is not enough, you can enhance it here.</h4>
<h4 class="ms-5 mt-2 mb-1 ps-5 text-light">* before continue, make sure to collect below documents/data.</h4>
<h4 class="ms-5 mt-2 mb-1 ps-5 text-light">- A document that mentioned your vehicles Odometer reading in a previous specific date (service record,smog test result...).</h4>
<h4 class="ms-5 mt-2 mb-1 ps-5 text-light">- Fuel economy of your vehicle.</h4>
<h4 class="ms-5 mt-2 mb-5 ps-5 text-light">- Mileage as at today.</h4>

<div class="w-75 p-4 ms-5 card ">
<div class="d-flex justify-content-around">
<div>
<form style="width:500px;" action="/process/quota/enhance/${currentVehicle.id} " method="post" >
    <p>
        <label >Document Type</label>
        <br>
        <input  name="type" required="true"  class="form-control"/>
    </p>
     <p>
        <label >Document Date:</label>
        <br>
        <input  type="date" name="date" required="true" class="form-control"/>
    </p>
     <p>
        <label >mileage mentioned in document:</label>
        <br>
        <input  type="number" name="odo" required="true" class="form-control"/>
    </p>
     <p>
        <label >current date:</label>
        <br>
        <input  type="date" name="today" required="true" class="form-control"/>
    </p>
     <p>
        <label >current Mileage:</label>
        <br>
        <input  type="number" name="curr" required="true" class="form-control"/>
    </p>
     <p>
        <label  >Vehicle's fuel economy(mpg):</label>
        <br>
        <input  type="number" name="eco" required="true" class="form-control"/>
      
    </p>
    <input type="submit" value="Calculate"/>
    </form>

</div> 
<div>

<br/>
<br/>
<c:if test="${newQuota!=null}">
<h4>* You have driven  <span><c:out value ="${driven}"/></span> miles within <span><c:out value ="${numDays}"/></span> days.</h4>
<h4>* You are averaging <c:out value ="${dailyAvgMiles}"/> miles per day.</h4>
<br>
<h3>* Suggested new weekly fuel quota is <c:out value ="${newQuota}"/> gallons.</h3>
<br>
<form:form  action="/process/vehicle/update/${currentVehicle.id}" method="post" modelAttribute="currentVehicle">
<input type="hidden" name="_method" value="put">
    <p>
        <form:input type="hidden" path="make" class="form-control"/>
    </p>
     <p>
        <form:input type="hidden" path="model" class="form-control"/>
    </p>
     <p>
        <form:input type="hidden" path="color" class="form-control"/>
    </p>
     <p>
        <form:input type="hidden" path="plate" class="form-control"/>
    </p>
    <p>
        <form:input type="hidden" path="type" class="form-control" readonly="true"/>
    </p>
    <p>
        <form:input type="hidden" path="balance" value="${newQuota-usedFuel}" class="form-control"/>
    </p>
   
     <p>
        <form:input type="hidden"  path="quota" value="${newQuota}" class="form-control"/>
    </p>
     
    <br>      
    <form:input path="user" type="hidden" value="${userId}" class="form-control"/>
   
    <input type="submit" style="width:200px;" class="btn btn-primary" value="Accept Quota"/>
</form:form> 
<br/>
</c:if>
<a class="btn btn-warning" style="width:200px;" href="/homeConsumer"> Ignore changes</a>

</div>

</div>
</div>
</div>

</body>
</html>