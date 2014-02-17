function FormasPagamentoController($scope, $location, $http) {
	
	$scope.urlPrefix = "/formasPagamento";
	
	$scope.formaPagamento = {};
	
	$scope.iniciar = function () {
	    Main.ativarMenu("menuCadastro");
	};
	
	$scope.listar = function(pagina) {
		var url = $scope.urlPrefix + "/listar/"+pagina;
        $http.get(url)
        .success(function (retorno) {
        	$scope.formaPagamento = retorno.formasPagamento;
        })
        .error(function() {
        	MensagemRetorno.tratarMensagemErroComunicacao();
        });
	};
	
    $scope.cadastrar = function (cadastroForm) {
        var url = $scope.urlPrefix + "/cadastrar";
        $http.post(url, JSON.stringify($scope.formaPagamento))
        .success(function (retorno) {
        	MensagemRetorno.tratarMensagesRetorno(retorno);
        	$scope.formaPagamento = {};
        })
        .error(function() {
        	MensagemRetorno.tratarMensagemErroComunicacao();
        });
    };
    
	$scope.excluir = function(formaPagamento) {
		var config = {headers: {'Content-Type':'application/x-www-form-urlencoded; charset=UTF-8'}};
		var url = $scope.urlPrefix + "/excluir/"+formaPagamento.formaPagamento;
		$http.get(url, config)
        .success(function (retorno) {
        	MensagemRetorno.tratarMensagesRetorno(retorno);
        	$scope.formaPagamento = {};
        	$scope.listar(0);
        })
        .error(function() {
        	MensagemRetorno.tratarMensagemErroComunicacao();
        });
	};
    
    $scope.iniciar();
    
};