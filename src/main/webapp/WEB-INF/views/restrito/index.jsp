<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<!DOCTYPE html>
<html>
<head>
<meta name="decorator" content="admin" />
<title>Home</title>
<script
	src="<c:url value='/resources/js/Controller/GastosController.js' />"></script>
</head>
<body>
	<div class="row" ng-controller="GastosController">

		<div class="page-header">
			<h1>
				Bem vindo! <small>${usuarioSessaoVO.nome}</small>
			</h1>
		</div>
		<div class="col-xs-12 col-md-12">
			<div class="row form">
				<div class="alert alert-dismissable nao-visivel">
					<button type="button" class="close" data-dismiss="alert"
						aria-hidden="true">&times;</button>
					<div class="mensagens"></div>
				</div>
			</div>
		</div>
<div class="col-xs-12 col-md-12">
			<div class="row">
				<div class="col-xs-4 col-md-4">
					<div class="panel panel-default">
						<div class="panel-heading">
							<i class="fa fa-bar-chart-o fa-fw"></i> Gastos dessa semana
						</div>
						<!-- /.panel-heading -->
						<div class="panel-body">
							aaa
						</div>
						<!-- /.panel-body -->
					</div>
				</div>
				<div class="col-xs-4 col-md-4">
					<div class="panel panel-default">
						<div class="panel-heading">
							<i class="fa fa-bar-chart-o fa-fw"></i> Gastos desse mês
						</div>
						<!-- /.panel-heading -->
						<div class="panel-body">
							aaa
						</div>
						<!-- /.panel-body -->
					</div>
				</div>
				<div class="col-xs-4 col-md-4">
					<div class="panel panel-default">
						<div class="panel-heading">
							<i class="fa fa-bar-chart-o fa-fw"></i> Total Geral
						</div>
						<!-- /.panel-heading -->
						<div class="panel-body">
							aaa
						</div>
						<!-- /.panel-body -->
					</div>
				</div>
			</div>
		</div>
		<div class="col-xs-12 col-md-12">
			<div class="row">
				<div class="col-xs-4 col-md-4">
					<div class="panel panel-default">
						<div class="panel-heading">
							<i class="fa fa-bar-chart-o fa-fw"></i> Ultimas dividas pagas
						</div>
						<!-- /.panel-heading -->
						<div class="panel-body">
							aaa
						</div>
						<!-- /.panel-body -->
					</div>
				</div>
				<div class="col-xs-4 col-md-4">
					<div class="panel panel-default">
						<div class="panel-heading">
							<i class="fa fa-bar-chart-o fa-fw"></i> Próximas dividas a pagar
						</div>
						<!-- /.panel-heading -->
						<div class="panel-body">
							aaa
						</div>
						<!-- /.panel-body -->
					</div>
				</div>
				<div class="col-xs-4 col-md-4">
					<div class="panel panel-default">
						<div class="panel-heading">
							<i class="fa fa-bar-chart-o fa-fw"></i> Suas dividas parceladas
						</div>
						<!-- /.panel-heading -->
						<div class="panel-body">
							aaa
						</div>
						<!-- /.panel-body -->
					</div>
				</div>
			</div>
		</div>
		<div class="col-xs-12 col-md-12">
			<div class="row">
				<div class="col-xs-8 col-md-8">
					<div class="panel panel-default">
						<div class="panel-heading">
							<i class="fa fa-bar-chart-o fa-fw"></i> Comparativo dos ultimos meses
						</div>
						<!-- /.panel-heading -->
						<div class="panel-body">
							aaa
						</div>
						<!-- /.panel-body -->
					</div>
				</div>
				<div class="col-xs-4 col-md-4">
					<div class="panel panel-default">
						<div class="panel-heading">
							<i class="fa fa-bar-chart-o fa-fw"></i> Seus gastos esse ano
						</div>
						<!-- /.panel-heading -->
						<div class="panel-body">
							aaa
						</div>
						<!-- /.panel-body -->
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
