<% if(request.getSession().getAttribute("userLogin")!=null){ %>
<form action="post?action=add_post" method="post">
	<h1>Post form:</h1><br>
	<div class="form-group">
	  <label for="usr">Title:</label>
	  <input type="text" class="form-control" id="usr" name="title" required>
	</div>
	<div class="form-group">
	  <label for="content">Content:</label>
	  <textarea class="form-control" rows="3" id="content" name="content" required></textarea>
	</div>
	<button type="submit" class="btn btn-primary btn-sm">add post</button>	
</form>
<hr>
<%	} %>