<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.io.*,java.util.*" %>
<%@page import="java.util.ArrayList" %>
<%@page import="java.util.List" %>
<%@page import="classes.UserBean" %>
<%@page import="classes.DBUtil" %>
<%@page import="classes.UserBean" %>
<%@page import="classes.StockBean" %>
<%@page import="classes.BookIssueBean" %>
<%@page import="classes.BookReturnBean" %>

<% if(!request.getSession().getAttribute("userLogin").equals("") && !request.getSession().getAttribute("userID").equals("")){ %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Posts</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
</head>
<body>



<div class="container-fluids">
	  <%@include file="../page_elements/header.jsp" %>
		
      <div class="row row-offcanvas row-offcanvas-right">
        <div class="col-md-3 col-md-offset-1">
        	
			<%@include file = "page_elements/postAddForm.jsp" %>	
        </div>
        <div class="col-md-7 col-md-offset-1">
        	
        	<%@include file="page_elements/postListForm.jsp" %>  	
        </div>        
      </div>

      <%@include file="page_elements/footer.jsp" %>
</div>



</body>
</html>

<% } %>