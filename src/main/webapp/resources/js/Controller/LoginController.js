function LoginController($scope, $location, $http) {
	
	$scope.urlPrefix = "/squamata/";
	
	$scope.usuario = {};
	
	$scope.iniciar = function () {
	    var url = "" + $location.$$absUrl;
	    $scope.displayLoginError = (url.indexOf("error") >= 0);
	};

    $scope.iniciar();
    
};