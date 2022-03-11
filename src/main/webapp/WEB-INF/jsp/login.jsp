<%@ include file="header.jsp" %>

<div class="container-fluid">

 <div class="col-md-4 offset-md-4">
 <div class="form-container">
 <div class="form-icon"><i class="fa fa-user"></i></div>
 <h3 class="title">Login</h3>
 <form class="form-horizontal" modelAttribute="user" action="postlogin">
     <div class="form-group">
         <label>email</label>
         <input class="form-control" type="email" placeholder="email address">
     </div>
     <div class="form-group">
         <label>password</label>
         <input class="form-control" type="password" placeholder="password">
     </div>
     <button type="button" class="btn btn-default" action="login">Login</button>
 </form>
 </div>
 </div>

</div>