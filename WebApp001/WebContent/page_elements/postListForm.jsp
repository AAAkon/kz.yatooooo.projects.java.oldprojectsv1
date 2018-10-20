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
		  <div class="col-md-10" style="background-color:#ffd9b3; margin-top:10px; border-radius:5px; border-bottom:5px solid #ff8000
		  ;">
		  	<h2><span style="font-size:18px;"> posted</span> <%=lists.get(i).getUsername() %></h2><hr>
		    <h4>
		    	<%=lists.get(i).getTitle() %>
		    	<%if(lists.get(i).getUser_id()==(int)request.getSession().getAttribute("userID")){ %>
		    		<a href="post?action=delete_post&post_id=<%=lists.get(i).getId() %>" class="pull-right" style="color:red;">X</a>	
		    	<%} %>
		    </h4>
		    <p style="padding-bottom:40px; overflow:hidden;">
		    	<i><%=lists.get(i).getContent() %></i>
		    </p>
		    
		    <p class="pull-right" style="font-size:12px; overflow:hidden;">
		    	<%=lists.get(i).getDate() %>
		    </p>
		    <p>
		    	<a class="btn btn-secondary" href="post?action=post_details&post_id=<%=lists.get(i).getId()%>" role="button">View details »</a>
		    </p>
		  </div><!--/span-->
		</div><!--/row-->
<%
	}
%>


		
		

