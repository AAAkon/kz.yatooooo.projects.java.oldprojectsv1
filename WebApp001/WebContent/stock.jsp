<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page session="true" %>
<%@page import= "java.io.*" %>		
<%@page import= "java.lang.*" %>
<%@page import="java.sql.PreparedStatement" %>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.SQLException" %>
<%@page import="java.sql.Statement" %>
<%@page import="java.util.ArrayList" %>
<%@page import="java.util.Date"%>
<%@page import="java.util.List" %>
<%@page import="classes.UserBean" %>
<%@page import="classes.StockBean" %>
<%@page import="classes.BookIssueBean" %>
<%@page import="classes.BookReturnBean" %>

<%if(!request.getSession().getAttribute("userLogin").equals("") && !request.getSession().getAttribute("userID").equals("")){ %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Stock</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container-fluids">
<%@include file="../page_elements/header.jsp" %>
	<div class="row">
	
	<% if(!request.getSession().getAttribute("userLogin").equals("") && !request.getSession().getAttribute("userID").equals("") && request.getSession().getAttribute("who").equals("admin")){ %>
	
		<div class="col-md-2 col-md-offset-1">
			
			<form action="BookServlet?action=add" method="post">
				<div class="form-group">
				  <label for="usr">Book name:</label>
				  <input type="text" class="form-control" id="book_name" name="book_name" required>
				</div>
				<div class="form-group">
				  <label for="usr">Author name:</label>
				  <input type="text" class="form-control" id="author_name" name="author_name" required>
				</div>
				<div class="form-group">
				  <label for="usr">Edition(number):</label>
				  <input type="number" class="form-control" id="edition" name="edition" required>
				</div>
				<div class="form-group">
				  <label for="usr">Stock(number):</label>
				  <input type="number" class="form-control" id="stock" name="stock" required>
				</div>
				<div class="form-group">
				  <label for="usr">Copies(number):</label>
				  <input type="number" class="form-control" id="stock" name="copies" required>
				</div>
				<button type="submit" class="btn btn-primary btn-sm">add book</button>	
			</form>
			<hr>
			
		</div>
		<div class="col-md-4 col-md-offset-1">
				<h3 style="margin:10px 0px;padding:10px; background-color:#ffffcc;">key word: <%=request.getParameter("key") %></h3>
			<%	
				List<StockBean> lists;
				
				if(request.getParameter("key")!=null && !request.getParameter("key").equals("")){
					lists = StockBean.getBooks(request.getParameter("key"));
				}else{
					lists = StockBean.getBooks();
				}
					
				
				
				for(int i=0;i<lists.size();i++){
			%>
				
				<div class="row">
				  		<div class="col-xs-12 col-sm-12 col-md-12">
				            <div class="well well-sm">
				                <div class="row">
				                    <div class="col-sm-6 col-md-6">
				                        <h4><%=lists.get(i).getBook_name() %> - <%=lists.get(i).getEdition() %> edition</h4>
				                        <small>Author: <cite title="address"><%=lists.get(0).getAuthor_name()%></cite></small><br>
				                        <small>Stock: <%=lists.get(0).getStock()%></small>
				                        
				                    </div>
				                    <div class="col-sm-3 col-md-3 col-md-offset-3">
				                        <div class="btn-group">
			                            	<%if(lists.get(i).isDeleted()==false){ %>
			                            	returned
			                            		<a href="BookServlet?action=delete&book_id=<%=lists.get(i).getBook_id() %>" type="button" class="btn btn-danger btn-xs">
			                            			delete
			                            		</a>
			                            	<%}else{ %>
			                            	deleted
			                            		<a href="BookServlet?action=return&book_id=<%=lists.get(i).getBook_id() %>" type="button" class="btn btn-info btn-xs">
			                            			return
			                            		</a>
			                            	<%} %>
				                        </div>
				                    </div>
				                </div>
			            	</div>
		        		</div>
			        </div>
			<% }%>
		</div>
			
		<div class="col-md-3 col-md-offset-1">
		<form class="navbar-form navbar-left" action="BookServlet" method="get">
            <div class="input-group">
              <input type="hidden" name="action" value="search">
              <input type="text" class="form-control" placeholder="Search" name="key">
              <div class="input-group-btn">
                <button class="btn btn-default" type="submit">
                  <i class="glyphicon glyphicon-search"></i>
                </button>
              </div>
            </div>
          </form>
		  
		</div>
		<%}else if(!request.getSession().getAttribute("userLogin").equals("") && !request.getSession().getAttribute("userID").equals("") && request.getSession().getAttribute("who").equals("user")){ %>
		<div class="col-md-2 col-md-offset-1">
		
		</div>
		<div class="col-md-4 col-md-offset-1">
		<h3 style="margin:10px 0px;padding:10px; background-color:#ffffcc;">key word: <%=request.getParameter("key") %></h3>
		
			<%	
				BookIssueBean issue = new BookIssueBean();
				BookReturnBean returnn = new BookReturnBean();
				StockBean stock = new StockBean();
				List<StockBean> lists;
				if(request.getParameter("key")!=null && !request.getParameter("key").equals("")){
					lists = StockBean.getBooks(request.getParameter("key"));
				}else{
					lists = StockBean.getBooks();
				}
				for(int i=0;i<lists.size();i++){
			%>
			<%if(lists.get(i).isDeleted()==false){ %>
			
				<div class="row">
				  		<div class="col-md-12">
				            <div class="well well-sm">
				                <div class="row">
				                    <div class="col-md-6">
				                        <h4><%=lists.get(i).getBook_name() %> - <%=lists.get(i).getEdition() %> edition</h4>
				                        <small>Author: <cite title="address"><%=lists.get(0).getAuthor_name()%></cite></small><br>
				                        <small>Stock: <%=lists.get(0).getStock()%></small>
				                    </div>
				                    <div class="col-md-5 col-md-offset-1">
				                        <div class="btn-group">
										<%if(stock.GetCopies(lists.get(i).getBook_id())<=0){ %>
										
										
										<%}else{ %>
					                       <% if(issue.isIssued((int)(request.getSession().getAttribute("userID")), lists.get(i).getBook_id()) && returnn.isReturned((int)(request.getSession().getAttribute("userID")), lists.get(i).getBook_id())){ %>
					                        	<a href="BookServlet?action=issued&book_id=<%=lists.get(i).getBook_id() %>&book_name=<%=lists.get(i).getBook_name() %>&user_id=<%=(int)request.getSession().getAttribute("userID") %>" type="button" class="btn btn-success btn-xs">
			                            			issue
			                            		</a>
					                       <% }else if(issue.isIssued((int)(request.getSession().getAttribute("userID")), lists.get(i).getBook_id())){ %>
			                            		<a href="#" type="button" class="btn btn-danger btn-xs">
			                            			issued
			                            		</a>
			                            		<a href="#" type="button" class="btn btn-danger btn-xs">
			                            			please return on time
			                            		</a>
			                            		
			                            	<% }else{ %>
			                            		
			                            		<a href="BookServlet?action=issued&book_id=<%=lists.get(i).getBook_id() %>&book_name=<%=lists.get(i).getBook_name() %>&user_id=<%=(int)request.getSession().getAttribute("userID") %>" type="button" class="btn btn-success btn-xs">
			                            			issue
			                            		</a>
			                            	
			                            	<% } %>
			                            <%} %>
				                        </div>
				                    </div>
				                </div>
			            	</div>
		        		</div>
			        </div>
	        <% } %>
			<% } %>
		</div>
			
		<div class="col-md-3 col-md-offset-1">
		<form class="navbar-form navbar-left" action="BookServlet" method="get">
            <div class="input-group">
              <input type="hidden" name="action" value="search">
              <input type="text" class="form-control" placeholder="Search" name="key">
              <div class="input-group-btn">
                <button class="btn btn-default" type="submit">
                  <i class="glyphicon glyphicon-search"></i>
                </button>
              </div>
            </div>
          </form>
		  
		</div>
		<%}%>
		
		
	</div>
	<%@include file="page_elements/footer.jsp" %>
</div>
</body>
</html>

<% } %>	