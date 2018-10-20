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
<%@page import="java.sql.Timestamp" %>
<%@page import="java.lang.Math" %>
<%@page import="java.util.concurrent.TimeUnit" %>

<% if(!request.getSession().getAttribute("userLogin").equals("") && !request.getSession().getAttribute("userID").equals("")){ %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>HOME</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<style>
	body{
		margin:0;
		padding:0;
	}
	.glyphicon {  margin-bottom: 10px;margin-right: 10px;}

	small {
	display: block;
	line-height: 1.428571429;
	color: #999;
	}
</style>
</head>
<body>


<div class="container-fluids">

	<%@include file="page_elements/header.jsp" %>
	<% 
		DBUtil db = new DBUtil();
		int userID = db.getUserID((String)request.getSession().getAttribute("userLogin"));
		List<UserBean> lists = UserBean.getUser(userID);
		
	%>
	
	<div class="row">
			<div class="col-xs-12 col-sm-6 col-md-6">
            <div class="well well-sm">
                <div class="row">
                    <div class="col-sm-6 col-md-4">
                        <img src="http://placehold.it/380x500" alt="" class="img-rounded img-responsive" />
                    </div>
                    <div class="col-sm-6 col-md-8">
                        <h4><%=lists.get(0).getName() %></h4>
                        <small><cite title="address"><%=lists.get(0).getAddress() %>, <%=lists.get(0).getCity() %>, Kazakhstan <i class="glyphicon glyphicon-map-marker">
                        </i></cite></small>
                        <p>
                            <i class="glyphicon glyphicon-envelope"></i><%=lists.get(0).getEmail() %>
                            <br />
                            <i class="glyphicon glyphicon-phone"></i><%=lists.get(0).getPhoneNo() %>
                            
                        <!-- Split button -->
                        <div class="btn-group">
                            <%if(lists.get(0).isAdmin()==true){ %>
                            	<button type="button" class="btn btn-success btn-xs">
                            		Admin
                            	</button>
                            <%}%>
                            <%if(lists.get(0).isLibrarian()==true){ %>
                            	<button type="button" class="btn btn-success btn-xs">
                            		Librarian
                            	</button>
                            <%}%>
                            <%if(lists.get(0).isRightUser()==true){ %>
                            	<button type="button" class="btn btn-info btn-xs">
                            		Right user
                            	</button>
                            <%}%>
                            <%if(lists.get(0).isDeletedUser()==true){ %>
                            	<button type="button" class="btn btn-danger btn-xs">
                            		Deleted user
                            	</button>
                            <%} %>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-xs-12 col-sm-6 col-md-6">
			<%	
				BookIssueBean issue = new BookIssueBean();
				BookReturnBean returnn = new BookReturnBean();
				StockBean stocks = new StockBean();
				List<BookIssueBean> issuedbooks = BookIssueBean.getIssuedBooks();
				
				for(int j=0;j<issuedbooks.size();j++){
			%>
				<%if(issuedbooks.get(j).getUser_id()==(int)request.getSession().getAttribute("userID")){ %>
				<% 
					Timestamp time = new Timestamp(System.currentTimeMillis());
					long timestamp1 = time.getTime();
					long timestamp2 = System.currentTimeMillis();
					long diffInDays = TimeUnit.DAYS.convert(Math.abs(timestamp2 - timestamp2), TimeUnit.MILLISECONDS); 
			    %>
			    
				<% if(diffInDays>7) {%>
				<div class="row" >
				  		<div class="col-md-12" >
				            <div class="well well-sm" style="background-color:red; color:white;">
				                <div class="row">
				                    <div class="col-md-6" >
				                    	<h4>Please, return BOOK</h4>
				                        <h4><%=issuedbooks.get(j).getBook_name() %> - <%=stocks.getEdition(issuedbooks.get(j).getBook_id()) %> edition</h4>
				                        <small>Author: <cite title="address"><%=stocks.getAuthor_name(issuedbooks.get(j).getBook_id())%></cite></small><br>
				                        <small>Stock: <%=stocks.getStock(issuedbooks.get(j).getBook_id()) %></small>
				                    </div>
				                    <div class="col-md-5 col-md-offset-1">
				                        <div class="btn-group">
					                       <% if(issue.isIssued((int)request.getSession().getAttribute("userID"), issuedbooks.get(j).getBook_id())){ %>
					                        	<a type="button" class="btn btn-danger btn-xs">
			                            			issued
			                            		</a>
					                       <% }%>
					                       
					                      <% if(returnn.isReturned((int)request.getSession().getAttribute("userID"), issuedbooks.get(j).getBook_id())){ %>
					                        	<a type="button" class="btn btn-success btn-xs">
		                            				returned
		                            			</a>
			                       			<% }else{  %>
					                       		<a type="button" class="btn btn-danger btn-xs">
			                            			please return on time
			                            		</a>
					                       <% } %>
				                        </div>
				                    </div>
				                </div>
			            	</div>
		        		</div>
			        </div>
			     	<%}else{ %>
			     	<div class="row" >
				  		<div class="col-md-12" >
				            <div class="well well-sm" >
				                <div class="row">
				                    <div class="col-md-6" >
				                        <h4><%=issuedbooks.get(j).getBook_name() %> - <%=stocks.getEdition(issuedbooks.get(j).getBook_id()) %> edition</h4>
				                        <small>Author: <cite title="address"><%=stocks.getAuthor_name(issuedbooks.get(j).getBook_id())%></cite></small><br>
				                        <small>Stock: <%=stocks.getStock(issuedbooks.get(j).getBook_id()) %></small>
				                    </div>
				                    <div class="col-md-5 col-md-offset-1">
				                        <div class="btn-group">
					                       <% if(issue.isIssued((int)request.getSession().getAttribute("userID"), issuedbooks.get(j).getBook_id())){ %>
					                        	<a type="button" class="btn btn-danger btn-xs">
			                            			issued
			                            		</a>
					                       <% }%>
					                       
					                      <% if(returnn.isReturned((int)request.getSession().getAttribute("userID"), issuedbooks.get(j).getBook_id())){ %>
					                        	<a type="button" class="btn btn-success btn-xs">
		                            				returned
		                            			</a>
			                       			<% }else{  %>
					                       		<a type="button" class="btn btn-danger btn-xs">
			                            			please return on time
			                            		</a>
					                       <% } %>
				                        </div>
				                    </div>
				                </div>
			            	</div>
		        		</div>
			        </div>
			     	
			     	<%} %>
	        <% } %>
			<% } %>
		</div>
    </div>
	<%@include file="page_elements/footer.jsp" %>

</div>

</body>
</html>

<%} %>
