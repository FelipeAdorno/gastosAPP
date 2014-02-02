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
<div class="row-fluid" ng-controller="CategoriasController" data-ng-init="listar(0);">
	
		
	<div class="page-header">
		<h1>Listagem de Categorias</h1>
	</div>
	
	<div class="col-xs-12 col-md-12">
		<ul class="breadcrumb">
			<li class="active"><span class="glyphicon glyphicon-file">&nbsp;</span><a href="<c:url value='/categorias/'/>">Novo</a></li>
			<li class="active"><span class="glyphicon glyphicon-list-alt">&nbsp;</span>Listar</li>
		</ul>
	</div>
	
	<div class="col-xs-10 col-md-10">
		<div class="row form">
        	<div class="alert alert-dismissable nao-visivel">
        		 <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
        		 <div class = "mensagens"></div>
        	</div>
	    </div> 
	</div> 
	
	<div class="col-xs-8 col-md-8">
		<div class="table-responsive">
			<table class="table table-bordered table-striped">
				<thead>
					<tr>
						<th class="text-center" width="80%">Categoria</th>
						<th class="text-center" width="10%">Editar</th>
						<th class="text-center" width="10%">Excluir</th>
					</tr>
				</thead>
				<tbody>
					<tr ng-repeat="categoria in categoria">
						<td>{{categoria.categoria}}</td>
						<td class="text-center">
							<span class="glyphicon glyphicon-edit"></span>
						</td>
						<td class="text-center">
							<a href="" ng-click="excluir(categoria)">
								<span class="glyphicon glyphicon-trash"></span>
							</a>
						</td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
</div>
</body>
</html>
