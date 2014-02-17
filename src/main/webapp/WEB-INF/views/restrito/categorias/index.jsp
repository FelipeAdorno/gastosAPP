<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<!DOCTYPE html>
<html>
<head>
	<meta name="decorator" content="admin" /> 
	<title>Home</title>
	<script src="<c:url value='/resources/js/Controller/CategoriasController.js' />"></script>
</head>
<body>
<br />
<div class="row-fluid" ng-controller="CategoriasController">

	
	<div class="page-header">
		<h1>Cadastrar Categorias</h1>
	</div>
	<div class="col-xs-12 col-md-12">
		<ul class="breadcrumb">
			<li class="active"><span class="glyphicon glyphicon-file">&nbsp;</span>Novo</li>
			<li><span class="glyphicon glyphicon-list-alt">&nbsp;</span><a href="<c:url value='/categorias/listar'/>">Listar</a></li>
		</ul>
	</div>
	
	<div class="col-xs-10 col-md-10">
	
		<div class="row form">
        	<div class="alert alert-dismissable nao-visivel">
        		 <button type="button" class="close">&times;</button>
        		 <div class = "mensagens"></div>
        	</div>
	    </div>  
	
		<form method="post" class="form-group" ng-submit="cadastrar();">
			<div class="form-group">
				<label>Descrição</label>
				<input type="text" class="form-control" ng-model="categoria.categoria"  required autofocus/>
			</div>
           	<div class="form-group">
          			<button class="btn btn-primary" type="submit">Cadastrar</button>
         			<button class="btn btn-default" type="reset">Cancelar</button>
   			</div>			
           </form>
           <br />
	</div>
</div>
</body>
</html>
