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
<%@page import="classes.PostBean" %>
<%@page import="classes.PostLikeBean" %>
<%@page import="classes.PostDislikeBean" %>
<%@page import="classes.CommentBean" %>

<% if(!request.getSession().getAttribute("userLogin").equals("") && !request.getSession().getAttribute("userID").equals("")){ %>
<%
	List<PostBean> lists = PostBean.getPostById(Integer.parseInt(request.getParameter("post_id")));
	PostLikeBean postLike = new PostLikeBean();
	PostDislikeBean postDislike = new PostDislikeBean();
	int userID = (int)request.getSession().getAttribute("userID");
	List<CommentBean> commentlist = CommentBean.getCommentByPostId(lists.get(0).getId());
	DBUtil db = new DBUtil();
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Posts</title>
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
		  <div class="col-md-6 col-md-offset-1" style="background-color:#ffd9b3; margin-top:10px; margin-bottom:20px; border-radius:5px; border-bottom:5px solid #ff8000
		  ;">
		  	<h2><span style="font-size:18px;"> posted</span> <%=lists.get(0).getUsername() %></h2><hr>
		    <h4>
		    	<%=lists.get(0).getTitle() %>
		    	<%if(lists.get(0).getUser_id()==(int)request.getSession().getAttribute("userID")){ %>
		    		<a href="post?action=delete_post&post_id=<%=lists.get(0).getId() %>" class="pull-right" style="color:red;">X</a>	
		    	<%} %>	
		    </h4>
		    <p style="padding-bottom:40px; overflow:hidden;">
		    	<i><%=lists.get(0).getContent() %></i>
		    </p>
		    
		    <p class="pull-right" style="font-size:12px; overflow:hidden;margin-bottom:20px;">posted at: <i><%=lists.get(0).getDate() %></i></p>
		    <p style="clear:right; margin-bottom:20px; padding:20px;">
		    	 <span class="pull-right">
		    	 <%if(postLike.isLikeClicked(lists.get(0).getId(),userID)==true){ %>
		    	 	<span style="color:green;"><a href="post?action=delete_like&post_id=<%=lists.get(0).getId() %>" style="margin-right:8px;"><i id="like" class="glyphicon glyphicon-thumbs-up" style="color:green;"></i></a><%=lists.get(0).getLike() %></span>
		    	 <%}else{ %>
		    	 	<span style="color:grey;"><a href="post?action=add_like&post_id=<%=lists.get(0).getId() %>" style="margin-right:8px;"><i id="like" class="glyphicon glyphicon-thumbs-up" style="color:grey;"></i></a><%=lists.get(0).getLike() %>
		    	 <%} %>
		    	 <%if(postDislike.isDislikeClicked(lists.get(0).getId(),userID)==true){ %>
		    	 	<span style="color:red;"><a href="post?action=delete_dislike&post_id=<%=lists.get(0).getId() %>" style="margin-left:30px;margin-right:8px;"><i id="dislike" class="glyphicon glyphicon-thumbs-down" style="color:red;"></i></a><%=lists.get(0).getDislike() %></span>
		    	 <%}else{ %>
		    	 	<span style="color:grey;"><a href="post?action=add_dislike&post_id=<%=lists.get(0).getId() %>" style="margin-left:30px;margin-right:8px;"><i id="dislike" class="glyphicon glyphicon-thumbs-down" style="color:grey;"></i></a><%=lists.get(0).getDislike() %></span>
		    	 <%} %>
                 </span>
                 
		    </p>
        </div>      
      </div>
      <div class="row">
      	<div class="col-md-10 col-md-offset-2">
		 	<form action="comment?action=add_comment" method="post" id="usrform">
		 		<input type="hidden" name="post_id" value="<%=lists.get(0).getId() %>" required>
				<input class="col-md-5	" type="text" name="text" placeholder="comment..." required>
				<input type="submit" class="btn btn-info btn-xs" style="margin-left:5px; padding-bottom:5px; ">
			</form>      		
      	</div>
      </div>
      <div class="row" style="margin-top:30px;">
      	
		 	<%for(int i=0;i<commentlist.size();i++){ %>
		 	<div class="col-md-5 col-md-offset-2" style="padding:20px 0px;margin-bottom:10px; background-color:#ffffcc;">
	 			<form action="comment?action=delete_comment" method="post" id="usrform">
			 		<input type="hidden" name="post_id" value="<%=lists.get(0).getId() %>" required>
			 		<input type="hidden" name="comment_id" value="<%=commentlist.get(i).getId() %>">
			 		
			 		
					<div class="col-md-10" style="word-wrap:break-word; font-weight:bold;">commented: <%=db.getName(commentlist.get(i).getUser_id()) %></div>
					<div class="col-md-10" style="word-wrap:break-word;"><%=commentlist.get(i).getText() %></div>
					<div class="col-md-1 col-md-offset-1 pull-right"><input type="submit" class="btn btn-default btn-xs" style="color:red;" value="X"></div>
				</form> 
			</div>
		 	<%} %>    
		 	
      </div>

      <%@include file="page_elements/footer.jsp" %>
</div>



</body>
</html>


<script>
function add_like() {
    
        var Status = $(this).val();
        $.ajax({
            url: 'Ajax/StatusUpdate.php',
            data: {
                text: $("textarea[name=Status]").val(),
                Status: Status
            },
            dataType : 'json'
        });
};
</script>
<% } %>