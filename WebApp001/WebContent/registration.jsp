<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Registration</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
	


<div class="container-fluids" >

	<%@include file="page_elements/header.jsp" %>
	<div class="row" style="padding-bottom:40px;">
	<div class="col-md-5 col-md-offset-3">
	<div class="form-container">
		<h1>Registration Form:</h1><br>
		<form action="registration" method="post">
			<div class="form-group">
		    	<label for="name">Name:</label>
		    	<input type="text" class="form-control" name="name">
		 	</div>
		 	<div class="form-group">
		    	<label for="city">City:</label>
		    	<input type="text" class="form-control" name="city">
		  	</div>
		  	<div class="form-group">
		    	<label for="address">Address:</label>
		    	<input type="text" class="form-control" name="address">
		  	</div>
		  	<div class="form-group">
		    	<label for="phoneNo">Phone no:</label>
		    	<input type="text" class="form-control" name="phoneNo" maxlength="11">
		  	</div>
		  	<div class="form-group">
		    	<label for="email">Email address:</label>
		    	<input type="email" class="form-control" name="email">
		  	</div>
		  	<div class="form-group">
		    	<label for="login">Username:</label>
		    	<input type="text" class="form-control" name="login">
		  	</div>
		  <div class="form-group">
		    <label for="pwd">Password:</label>
		    <input type="password" class="form-control" name="password">
		  </div>
		  <div class="form-group">
		    <label for="pwd">Confirm password:</label>
		    <input type="password" class="form-control" name="password2">
		  </div>
		  <button type="submit" class="btn btn-primary">Sing up</button>
		</form>
	</div>
	</div>
	</div>
	<%@include file="page_elements/footer.jsp" %>

</div>

</body>
</html>