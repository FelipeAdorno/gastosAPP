function CategoriasController($scope, $location, $http) {
	
	$scope.urlPrefix = "/categorias";
	
	$scope.categoria = {};
	
	$scope.iniciar = function () {
	    Main.ativarMenu("menuCadastro");
	};
	
	$scope.listar = function(pagina) {
		var url = $scope.urlPrefix + "/listar/"+pagina;
        $http.get(url)
        .success(function (retorno) {
        	$scope.categoria = retorno.categorias;
        })
        .error(function() {
        	MensagemRetorno.tratarMensagemErroComunicacao();
        });
	};
	
    $scope.cadastrar = function (cadastroForm) {
        var url = $scope.urlPrefix + "/cadastrar";
        $http.post(url, JSON.stringify($scope.categoria))
        .success(function (retorno) {
        	MensagemRetorno.tratarMensagesRetorno(retorno);
        	$scope.categoria = {};
        })
        .error(function() {
        	MensagemRetorno.tratarMensagemErroComunicacao();
        });
    };
    
	$scope.excluir = function(categoria) {
		var config = {headers: {'Content-Type': 'json; charset=UTF-8'}};
		var url = $scope.urlPrefix + "/excluir/"+categoria.categoria;
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