<% if(request.getSession().getAttribute("userLogin")!=null){%>
<div id="nav">
  <div class="navbar navbar-inverse navbar-fixed-top" data-spy="affix" data-offset-top="100">
      <div class="container">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" href="page?page=home"><img class="img-responsive logo" src="http://mobilution.io/wp-content/uploads/2015/10/library-icon.png" alt=""></a>
        </div>
        <div id="navbar" class="collapse navbar-collapse">
		
          <ul class="nav navbar-nav navbar-right">
           <li><a href="page?page=home">Home</a></li>
          <% if(!request.getSession().getAttribute("userLogin").equals("") && !request.getSession().getAttribute("userID").equals("") && request.getSession().getAttribute("who").equals("admin")){ %>
	      <li><a href="page?page=users">Users</a></li>
	      <%} %>
            <li><a href="page?page=stock">Stock</a></li>
            <li><a href="page?page=posts">Posts</a></li>
            <li><a href="logout"><span class="glyphicon glyphicon-log-out"></span> Logout</a></li>
          </ul>
        </div>
        <!--/.nav-collapse -->
      </div>
      <!--/.contatiner -->
</div>
</div>
<% }else{ %>
<div id="nav">
  <div class="navbar navbar-inverse navbar-fixed-top" data-spy="affix" data-offset-top="100">
      <div class="container">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" href="page?page=index"><img class="img-responsive logo" src="http://mobilution.io/wp-content/uploads/2015/10/library-icon.png" alt=""></a>
        </div>
        <div id="navbar" class="collapse navbar-collapse">

          <ul class="nav navbar-nav navbar-right">
            <li><a href="page?page=registration">Registration</a></li>
            <li><a href="page?page=login">Login</a></li>
          </ul>
        </div>
        <!--/.nav-collapse -->
      </div>
      <!--/.contatiner -->
</div>
</div>
<% } %>


<style>
html,body {
    height:100%;
    padding-top:70px;
}


section {
  height:calc(90% - 50px);
}

.affix {
  padding:0px;
  -webkit-transition:padding 0.2s linear;
  -moz-transition:padding 0.2s linear;  
  -o-transition:padding 0.2s linear;         
  transition:padding 0.2s linear;  

}

.affix-top {
  padding-top:15px;
  padding-bottom:15px;
  -webkit-transition:padding 0.5s linear;
  -moz-transition:padding 0.5s linear;  
  -o-transition:padding 0.5s linear;         
  transition:padding 0.5s linear;  
}

.logo{
	width:90px;
}
</style>

<script>
$(window).scroll(function() {
	if ($(document).scrollTop() > 150) {
        alert('hi');
	$('.logo').height(200);

	}
	else {
	$('.logo').height(100);
	});
</script>
