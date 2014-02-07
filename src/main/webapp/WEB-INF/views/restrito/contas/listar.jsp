<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<!DOCTYPE html>
<html>
<head>
	<meta name="decorator" content="admin" /> 
	<title>Home</title>
	<script src="<c:url value='/resources/js/Controller/ContasController.js' />"></script>
</head>
<body>
<br />
<div class="row-fluid" ng-controller="ContasController" data-ng-init="filtrar(0);">
		
	<div class="page-header">
		<h1>Listagem de Contas<h1>
	</div>
	
	<div class="col-xs-12 col-md-12">
		<ul class="breadcrumb">
			<li class="active"><span class="glyphicon glyphicon-file">&nbsp;</span><a href="<c:url value='/contas/'/>">Novo</a></li>
			<li class="active"><span class="glyphicon glyphicon-list-alt">&nbsp;</span>Listar</li>
		</ul>
	</div>
	
	<div class="col-xs-12 col-md-12">
		<div class="row form">
        	<div class="alert alert-dismissable nao-visivel">
        		 <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
        		 <div class = "mensagens"></div>
        	</div>
	    </div> 
	</div> 
	<div class="col-xs-6 col-md-6">
		<div class="row">
			<div class="col-xs-3 col-md-3">
				<select id="mes" class="form-control" ng-model="busca.mes" required>
				   <option value="">selecione</option>
				   <option value="0">Janeiro</option>
				   <option value="1">Fevereiro</option>
				   <option value="2">Março</option>
				   <option value="3">Abril</option>
				   <option value="4">Maio</option>
				   <option value="5">Junho</option>
				   <option value="6">Julho</option>
				   <option value="7">Agosto</option>
				   <option value="8">Setembro</option>
				   <option value="9">Outubro</option>
				   <option value="10">Novembro</option>
				   <option value="11">Dezembro</option>
				</select>
			</div>
			<div class="col-xs-3 col-md-3">
				<select id="ano" class="form-control" ng-model="busca.ano" required>
				   <option value="">selecione</option>
				   <option value="2013">2013</option>
				   <option value="2014">2014</option>
				   <option value="2015">2015</option>
				   <option value="2016">2016</option>
				   <option value="2017">2017</option>
				   <option value="2018">2018</option>
				   <option value="2019">2019</option>
				   <option value="2020">2020</option>
				   <option value="2021">2021</option>
				   <option value="2022">2022</option>
				   <option value="2023">2023</option>
				   <option value="2024">2024</option>
				</select>
			</div>
			<div class="col-xs-1 col-md-1">
				<button class="btn btn-primary" ng-click="filtrar(0);">Filtrar</button>
			</div>
		</div>
	</div>
	
	<div class="col-xs-12 col-md-12">
		<div class="table-responsive">
			<table class="table table-bordered">
				<thead>
					<tr>
						<th class="text-center" width="20%">Conta</th>
						<th class="text-center" width="10%">Valor(R$)</th>
						<th class="text-center" width="10%">Vencimento</th>
						<th class="text-center" width="10%">Parcela</th>
						<th class="text-center" width="10%">Nº Parcelas</th>
						<th class="text-center" width="10%">Categoria</th>
						<th class="text-center" width="10%">Forma Pagamento</th>
						<th class="text-center" width="10%">Editar</th>
						<th class="text-center" width="10%">Excluir</th>
					</tr>
				</thead>
				<tbody>
					<tr ng-repeat="conta in conta" ng-class="({{conta.paga}} == true) ? 'paga' : 'naoPaga'">
						<td>{{conta.descricao}}</td>
						<td class="text-center">{{conta.valor | currency:"R$" }}</td>
						<td class="text-center">{{conta.vencimento | date:'dd-MM-yyyy'}}</td>
						<td class="text-center">{{conta.numeroParcela}}</td>
						<td class="text-center">{{conta.totalParcelas}}</td>
						<td>{{conta.categoria.categoria}}</td>
						<td>{{conta.formaPagamento.formaPagamento}}</td>
						<td class="text-center">
							<span class="glyphicon glyphicon-edit"></span>
						</td>
						<td class="text-center">
							<a href="" ng-click="excluir(forma)">
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
