<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Login</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<style>
	div.form-container{
		width: 400px;
		margin: 0 auto;
	}
</style>

</head>
<body>



<div class="container">

	<%@include file="page_elements/header.jsp" %>
	
	<div class="form-container">
		<h1>Login Form:</h1><br>
		<form action="login" method="post">
		  	<div class="form-group">
		    	<label for="login">Login:</label>
		    	<input type="text" class="form-control" name="login">
		  	</div>
		  <div class="form-group">
		    <label for="pwd">Password:</label>
		    <input type="password" class="form-control" name="password">
		  </div>
		  <button type="submit" class="btn btn-success">Sing in</button>
		</form>
	</div>
	
	<%@include file="page_elements/footer.jsp" %>
	
</div>

</body>
</html>