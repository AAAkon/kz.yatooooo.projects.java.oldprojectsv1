<% if(request.getSession().getAttribute("userLogin")!=null){ %>
<form action="addPost" method="post">
	<div class="form-group">
	  <label for="usr">Title:</label>
	  <input type="text" class="form-control" id="usr" name="title">
	</div>
	<div class="form-group">
	  <label for="content">Content:</label>
	  <textarea class="form-control" rows="3" id="content" name="content"></textarea>
	</div>
	<button type="submit" class="btn btn-primary btn-sm">Create</button>	
</form>
<hr>
<%	} %>