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
<div class="row-fluid" ng-controller="ContasController" data-ng-init="carregarInformacoes();">
	
	<div class="page-header">
		<h1>Cadastrar Contas</h1>
	</div>
	
	<div class="col-xs-12 col-md-12">
		<ul class="breadcrumb">
			<li class="active"><span class="glyphicon glyphicon-file">&nbsp;</span>Novo</li>
			<li><span class="glyphicon glyphicon-list-alt">&nbsp;</span><a href="<c:url value='/contas/listar'/>">Listar</a></li>
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
	
	<div class="col-xs-12 col-md-12">
	
		<div class="row form">
        	<div class="alert alert-dismissable nao-visivel">
        		 <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
        		 <div class = "mensagens"></div>
        	</div>
	    </div>  
	
		<form method="post" class="form-group" ng-submit="cadastrar();">
		 	<div class="form-group">
				<div class="row">
					<div class="col-xs-12 col-md-12">
						<input type="checkbox" ng-model="conta.paga" />  A conta já está paga? 
					</div>
				</div>
			</div> 
			<div class="form-group">
				<label>Descrição</label>
				<input type="text" class="form-control" ng-model="conta.descricao"  required autofocus />
			</div>
			<div class="form-group">
				<div class="row">
					<div class="col-xs-4 col-md-4">
						<div class="form-group">
							<label>Vencimento</label>
							<div class="input-group date" id="date" data-date="new Date();" data-date-format="dd/mm/yyyy">
							  <input class="form-control" size="16" type="text" ng-model="conta.vencimento" required/>
							  <span class="input-group-addon add-on"><i class="glyphicon glyphicon-calendar"></i></span>
							</div>
						</div> 
					</div>
					<div class="col-xs-4 col-md-4">
						<div class="form-group">
							<label>Parcela atual</label>
							<div class="input-group spinner" data-trigger="spinner" id="spinner">
								<input type="text" data-rule="quantity" class="form-control" ng-model="conta.numeroParcela" required>
								<div class="input-group-addon add-on"> 
									<a href="#" class="spin-up" data-spin="up"><span class="icon-dropup"></span></a> 
									<a href="#" class="spin-down" data-spin="down"><span class="icon-dropdown"></span></a> 
								</div>
							</div>
						</div>
					</div>
					<div class="col-xs-4 col-md-4">
						<div class="form-group">
							<label>Total de parcelas</label>
							<div class="input-group spinner" data-trigger="spinner" id="spinner">
								<input type="text" data-rule="quantity" class="form-control" ng-model="conta.totalParcelas" required>
								<div class="input-group-addon add-on"> 
									<a href="#" class="spin-up" data-spin="up"><span class="icon-dropup"></span></a> 
									<a href="#" class="spin-down" data-spin="down"><span class="icon-dropdown"></span></a> 
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="form-group">
				<div class="row">
					<div class="col-xs-4 col-md-4">
						<div class="form-group">
							<label>Categoria</label>
							<select class="form-control" ng-model="conta.categoria" required>
							  <option ng-repeat="categoria in categorias" value="{{categoria.categoria}}">{{categoria.categoria}}</option>
							</select>
						</div>
					</div>
					<div class="col-xs-4 col-md-4">
						<div class="form-group">
							<label>Froma Pagamento</label>
							<select class="form-control" ng-model="conta.formaPagamento" required>
							  <option ng-repeat="formaPagamento in formasPagamento" value="{{formaPagamento.formaPagamento}}">{{formaPagamento.formaPagamento}}</option>
							</select>
						</div>
					</div>
					<div class="col-xs-4 col-md-4">
						<div class="form-group">
							<label>Valor</label>
							<div class="input-group input-group">
  								<span class="input-group-addon">R$</span>
								<input type="text" class="form-control monetario" ng-model="conta.valor" required data-affixes-stay="true" data-symbol="R$ " data-thousands="." data-decimal="," >
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="form-group">
	           	<div>
	          			<button class="btn btn-primary" type="submit">Cadastrar</button>
	         			<button class="btn btn-default" type="reset">Cancelar</button>
	   			</div>
	   		</div>			
         </form>
	</div>

</div>
</body>
</html>
