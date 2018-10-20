<%@page import="java.io.*" %>

<%@page import="java.sql.PreparedStatement" %>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.SQLException" %>
<%@page import="java.sql.Statement" %>
<%@page import="java.util.ArrayList" %>
<%@page import="java.util.Date"%>
<%@page import="java.util.List" %>
<%@page import="classes.PostBean" %>

<%	
	List<PostBean> lists = PostBean.getPost();
	for(int i=0;i<lists.size();i++){
		System.out.println(lists.get(i).getTitle());
%>
		<div class="row">
		  <div class="col-6 col-lg-4">
		    <h2><%=lists.get(i).getTitle() %></h2>
		    <p><%=lists.get(i).getContent() %></p>
		    <p><a class="btn btn-secondary" href="#" role="button">View details »</a></p>
		  </div><!--/span-->
		</div><!--/row-->
<%
	}
%>


		
		

