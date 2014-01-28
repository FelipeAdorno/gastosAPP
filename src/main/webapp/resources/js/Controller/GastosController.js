function GastosController($scope, $location, $http) {
	
	$scope.urlPrefix = "/squamataGastos/";
	
	$scope.usuario = {};
	
	$scope.iniciar = function () {
	    Main.ativarMenu("menuHome");
	};
    
    $scope.iniciar();
    
};