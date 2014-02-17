function UsuarioController($scope, $location, $http) {
	
	$scope.urlPrefix = "/";
	
	$scope.usuario = {};
	
	$scope.iniciar = function () {
	    
	};
    $scope.cadastrar = function (cadastroForm) {
        var url = $scope.urlPrefix + "/cadastrar";
        $http.post(url, JSON.stringify($scope.usuario))
        .success(function (retorno) {
        	MensagemRetorno.tratarMensagesRetorno(retorno);
        	$("#confSenha").val("");
        	$scope.usuario = {};
        })
        .error(function() {
        	MensagemRetorno.tratarMensagemErroComunicacao();
        });
    };
    
    $scope.iniciar();
    
};