<%@page language="java" contentType="text/html; charset=UTF-8" %>
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


<% if(!request.getSession().getAttribute("userLogin").equals("") && !request.getSession().getAttribute("userID").equals("") && request.getSession().getAttribute("who").equals("admin")){ %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>Users</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container-fluids">
	
	  <%@include file="../page_elements/header.jsp" %>
        
			
					<div class="row">
					            <%	
							List<UserBean> lists = UserBean.getUsers();
							for(int i=0;i<lists.size();i++){
						%>
						
			
				  		<div class="col-xs-12 col-sm-12 col-md-12">
				            <div class="well well-sm">
			
				                <div class="row">
				                    <div class="col-sm-6 col-md-6">
				                        <h4><%=lists.get(i).getName() %></h4>
				                        <small><cite title="address"><%=lists.get(0).getAddress() %>, <%=lists.get(0).getCity() %>, Kazakhstan <i class="glyphicon glyphicon-map-marker">
				                        </i></cite></small>
				                        
				                    </div>
				                    <div class="col-sm-6 col-md-6">
				                        <div class="btn-group">
				                            <%if(lists.get(i).isAdmin()==true){ %>
				                            	<a href="AddPriveligies?type=deleteFromAdmin&id=<%=lists.get(i).getId() %>" type="button" class="btn btn-success btn-xs">
				                            		ADMIN
				                            	</a>
				                            <%}else{ %>
				                            	<a href="AddPriveligies?type=addToAdmin&id=<%=lists.get(i).getId() %>" type="button" class="btn btn-default btn-xs">
				                            		ADMIN
				                            	</a>
				                            <%}%>
				                            <%if(lists.get(i).isLibrarian()==true){ %>
				                            	<a href="AddPriveligies?type=deleteFromLibrarian&id=<%=lists.get(i).getId() %>" type="button" class="btn btn-success btn-xs">
				                            		Librarian
				                            	</a>
				                            <%}else{ %>
				                            	<a href="AddPriveligies?type=addToLibrarian&id=<%=lists.get(i).getId() %>" type="button" class="btn btn-default btn-xs">
				                            		Librarian
				                            	</a>
				                            <%}%>
				                            <%if(lists.get(i).isRightUser()==true){ %>
				                            	<a href="AddPriveligies?type=wrongUser&id=<%=lists.get(i).getId() %>" type="button" class="btn btn-info btn-xs">
				                            		Right user
				                            	</a>
				                            <%}else{ %>
				                            	<a href="AddPriveligies?type=rightUser&id=<%=lists.get(i).getId() %>" type="button" class="btn btn-default btn-xs">
				                            		Right user
				                            	</a>
				                            <%}%>
				                            <%if(lists.get(i).isDeletedUser()==true){ %>
				                            	<a href="AddPriveligies?type=add&id=<%=lists.get(i).getId() %>" type="button" class="btn btn-danger btn-xs">
				                            		Deteled
				                            	</a>
				                            <%} else{ %>
				                            	<a href="AddPriveligies?type=delete&id=<%=lists.get(i).getId() %>" type="button" class="btn btn-default btn-xs">
				                            		Returned
				                            	</a>
				                            <%} %>
				                        </div>
				                    </div>
				                </div>
			            	</div>
		        		</div>
		        		<div class="col-md-4 col-md-offset-6">
		
							<%	
								BookIssueBean issue = new BookIssueBean();
								BookReturnBean returnn = new BookReturnBean();
								StockBean stocks = new StockBean();
								List<BookIssueBean> issuedbooks = BookIssueBean.getIssuedBooks();
								
								for(int j=0;j<issuedbooks.size();j++){
							%>
								<%if(issuedbooks.get(j).getUser_id()==lists.get(i).getId()){ %>
							
								<div class="row">
								  		<div class="col-md-12">
								            <div class="well well-sm">
								                <div class="row">
								                    <div class="col-md-6">
								                        <h4><%=issuedbooks.get(j).getBook_name() %> - <%=stocks.getEdition(issuedbooks.get(j).getBook_id()) %> edition</h4>
								                        <small>Author: <cite title="address"><%=stocks.getAuthor_name(issuedbooks.get(j).getBook_id())%></cite></small><br>
								                        <small>Stock: <%=stocks.getStock(issuedbooks.get(j).getBook_id()) %></small>
								                    </div>
								                    <div class="col-md-5 col-md-offset-1">
								                        <div class="btn-group">
									                       <% if(issue.isIssued(lists.get(i).getId(), issuedbooks.get(j).getBook_id())){ %>
									                        	<a href="" type="button" class="btn btn-success btn-xs">
							                            			issued
							                            		</a>
									                       <% }%>
									                       
									                      <% if(returnn.isReturned(lists.get(i).getId(), issuedbooks.get(j).getBook_id())){ %>
									                        	<a href="" type="button" class="btn btn-success btn-xs">
						                            				returned
						                            			</a>
							                       			<% }else{  %>
									                       		<a href="BookServlet?action=returned&book_id=<%=issuedbooks.get(j).getBook_id() %>&book_name=<%=issuedbooks.get(j).getBook_name() %>&user_id=<%=lists.get(i).getId() %>
									                        	" type="button" class="btn btn-danger btn-xs">
							                            			make returned
							                            		</a>
									                       <% } %>
								                        </div>
								                    </div>
								                </div>
							            	</div>
						        		</div>
							        </div>
					        <% } %>
							<% } %>
						</div>
		        		<%
							}
						%>
			        </div>
				
          	
      <div class="row">
      	<%@include file="page_elements/footer.jsp" %>
      </div>
    
</div>
</body>
</html>
<% } %>