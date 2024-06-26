<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@include file="../layout/header.jsp"%>

<div class="container">
	<h2>Stacked form</h2>
	<form>
		<input type="hidden" id="id" value="${principal.user.id }"/>
		<div class="form-group">
			<label for="username">Username</label>
			<input type="email" value="${principal.user.username }" class="form-control" placeholder="Enter Username" name="text" id="username"  readonly>
		</div>

<c:if test="${empty principal.user.oauth }">
			<div class="form-group">
			<label for="email">Email address</label> <input type="email"
				class="form-control" id="email" placeholder="Enter email"
				name="email" value="${principal.user.email }">
		</div> 

		<div class="form-group">
			<label for="pwd">Password</label> <input type="password"
				class="form-control" id="password" placeholder="Enter password"
				name="password" >
		</div>
</c:if>

<c:if test="${not empty principal.user.oauth }">

			<div class="form-group">
			<label for="email">Email address</label> <input type="email"
				class="form-control" id="email" placeholder="Enter email"
				name="email" value="${principal.user.email }" readonly="readonly">
		</div> 

		<div class="form-group">
			<label for="pwd">Password</label> <input type="password"
				class="form-control" id="password" placeholder="Enter password"
				name="password"  readonly="readonly">
		</div>
</c:if>


	</form>

	<button id="btn-update" class="btn btn-primary">수정하기</button>

</div>

<script src="/js/user.js"> </script>
<%@include file="../layout/footer.jsp"%>

</body>



