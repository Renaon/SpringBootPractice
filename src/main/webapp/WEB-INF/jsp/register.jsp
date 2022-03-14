<%@ include file="header.jsp" %>

<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <title>Registration</title>
</head>

<div class="container-fluid">

 <div class="col-md-4 offset-md-4">
 <div class="form-container">
 <div class="form-icon"><i class="fa fa-user"></i></div>
 <h3 class="title">Registration</h3>
<form:form action="register_success" modelAttribute="user" class="form-horizontal">
	<div class="form-group">
		<form:input path="login" type='text' class="form-control" placeholder="login"/>
	</div
    <br>
    <form:input path="password" type='text' class="form-control" placeholder="password"/>
    <br>
    <input type="submit" value="Register" class="btn btn-default"/>
</form:form>
 </div>
 </div>

</div>
</html>


<%@ include file="footer.jsp" %>
