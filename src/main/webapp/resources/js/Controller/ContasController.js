function ContasController($scope, $location, $http) {
	
	$scope.urlPrefix = "/squamataGastos/";
	
	$scope.usuario = {};
	
	$scope.iniciar = function () {
	    Main.ativarMenu("menuCadastro");
	};
    
    $scope.iniciar();
    
};