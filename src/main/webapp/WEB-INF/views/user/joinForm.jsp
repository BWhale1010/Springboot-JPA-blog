<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@include file="../layout/header.jsp"%>

<div class="container">
	<h2>Stacked form</h2>
	<form action="/action_page.php">
	
		<div class="form-group">
			<label for="username">Username</label> <input type="email"
				class="form-control" placeholder="Enter Username" name="text"
				id="username">
		</div>
		
		<div class="form-group">
			<label for="email">Email address</label> <input type="email"
				class="form-control" id="email" placeholder="Enter email"
				name="email">
		</div>
		
		<div class="form-group">
			<label for="pwd">Password</label> <input type="password"
				class="form-control" id="pwd" placeholder="Enter password"
				name="password">
		</div>
		
		<button type="submit" class="btn btn-primary">회원가입</button>
		
	</form>

</div>

<%@include file="../layout/footer.jsp"%>

</body>



