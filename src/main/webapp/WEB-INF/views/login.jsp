<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<meta name="decorator" content="login-theme" />
<title>Home</title>
<style>
body {
	padding-top: 8%;
	padding-bottom: 40px;
	background-color: #eee;
}

.form-signin {
	max-width: 330px;
	padding: 15px;
	margin: 0 auto;
}

.form-signin .form-signin-heading,.form-signin .checkbox {
	margin-bottom: 10px;
}

.form-signin .checkbox {
	font-weight: normal;
}

.form-signin .form-control {
	position: relative;
	font-size: 16px;
	height: auto;
	padding: 10px;
	-webkit-box-sizing: border-box;
	-moz-box-sizing: border-box;
	box-sizing: border-box;
}

.form-signin .form-control:focus {
	z-index: 2;
}

.form-signin input[type="text"] {
	margin-bottom: -1px;
	border-bottom-left-radius: 0;
	border-bottom-right-radius: 0;
}

.form-signin input[type="password"] {
	margin-bottom: 10px;
	border-top-left-radius: 0;
	border-top-right-radius: 0;
}
</style>
<script src="<c:url value='/resources/js/Controller/LoginController.js' />"></script>
</head>
<body>
	<form class="form-signin" role="form" action="<c:url value='/j_spring_security_check'/>" method="post" ng-controller="LoginController">
		<h2 class="form-signin-heading">Efetuar Login</h2>
		 <div ng-class="{'nao-visivel': displayLoginError == true, 'nao-visivel': displayLoginError == false}">
		 	<br>
            <div class="alert alert-danger">Erro ao efetuar login!</div>
        </div>
		<input type="text" name="j_username" id="j_username" class="form-control" placeholder="Nome de usuário" required autofocus >
		<input type="password" name="j_password" id="j_password" class="form-control" placeholder="Senha" required> 
		<label class="checkbox"><input type="checkbox" value="remember-me">Continuar Logado</label>
		<button class="btn btn-lg btn-primary btn-block" type="submit">Entrar</button><br />
		<div style="text-align: center;">
		<span>Ainda não é cadastrado? Clique <a href="<c:url value='cadastro'  />">aqui.</a></span>
		</div> 
	</form> 
</body>
</html>