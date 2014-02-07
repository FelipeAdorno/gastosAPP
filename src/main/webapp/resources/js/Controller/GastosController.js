function GastosController($scope, $location, $http) {
	
	$scope.urlPrefix = "/squamataGastos/gastos";
	
	$scope.usuario = {};
	
	$scope.contasSemana = {};
	
	$scope.totalMes = {};
	
	$scope.totalGeral = {};
	
	$scope.iniciar = function () {
	    Main.ativarMenu("menuHome");
	};
	
	
	$scope.buscarContasSemana = function() {
		var url = $scope.urlPrefix + "/buscarContasSemana";
        $http.get(url)
        .success(function (retorno) {
        	$scope.contasSemana = retorno.contas;
        })
        .error(function() {
        	MensagemRetorno.tratarMensagemErroComunicacao();
        });
	};
	
	$scope.buscarTotalMes = function() {
		var url = $scope.urlPrefix + "/totalMes";
        $http.get(url)
        .success(function (retorno) {
        	$scope.totalMes = retorno;
        })
        .error(function() {
        	MensagemRetorno.tratarMensagemErroComunicacao();
        });
	};
    
	$scope.buscarTotalGeral = function() {
		var url = $scope.urlPrefix + "/totalDividas";
        $http.get(url)
        .success(function (retorno) {
        	$scope.totalGeral = retorno;
        })
        .error(function() {
        	MensagemRetorno.tratarMensagemErroComunicacao();
        });
	};
	
    $scope.iniciar();
    
};