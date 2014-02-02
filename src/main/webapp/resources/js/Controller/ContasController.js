function ContasController($scope, $location, $http) {
	
	$scope.urlPrefixFormaPagamento = "/squamataGastos/formasPagamento";
	
	$scope.urlPrefixCategoria = "/squamataGastos/categorias";
	
	$scope.urlPrefix = "/squamataGastos/contas";
	
	$scope.conta = {};
	
	$scope.busca = {};
	
	$scope.formasPagamento = {};
	
	$scope.categorias = {};
	
	$scope.iniciar = function () {
	    Main.ativarMenu("menuCadastro");
	    $scope.iniciarFiltro();
	};
	
	$scope.iniciarFiltro = function() {
		var dataAtual = new Date();
		$('#mes option:eq('+dataAtual.getMonth()+')').prop('selected', true);
		$('#ano option[value="'+dataAtual.getFullYear()+'"]').prop('selected', true);
		$scope.busca.ano = dataAtual.getFullYear();
		$scope.busca.mes = dataAtual.getMonth();
	};
	
	$scope.carregarInformacoes = function() {
		$scope.carregarFormaPagamento();
		$scope.carregarCategorias();
	};
	
	$scope.filtrar = function(pagina) {
		var url = $scope.urlPrefix + "/buscar/"+pagina+"/"+$scope.busca.mes+"/"+$scope.busca.ano;
        $http.get(url)
        .success(function (retorno) {
        	$scope.conta = retorno.contas;
        })
        .error(function() {
        	MensagemRetorno.tratarMensagemErroComunicacao();
        });
	};
	
	$scope.listar = function(pagina) {
		var url = $scope.urlPrefix + "/listar/"+pagina;
        $http.get(url)
        .success(function (retorno) {
        	$scope.conta = retorno.contas;
        })
        .error(function() {
        	MensagemRetorno.tratarMensagemErroComunicacao();
        });
	};
	
    $scope.cadastrar = function () {
        var url = $scope.urlPrefix + "/cadastrar";
        var config = {headers: {'Content-Type': 'application/json; charset=UTF-8'}};
        $http.post(url, JSON.stringify($scope.conta),config)
        .success(function (retorno) {
        	MensagemRetorno.tratarMensagesRetorno(retorno);
        	$scope.conta = {};
        })
        .error(function() {
        	MensagemRetorno.tratarMensagemErroComunicacao();
        });
    };
	
	$scope.carregarFormaPagamento = function() {
		var url = $scope.urlPrefixFormaPagamento + "/listarTudo";
        $http.get(url)
        .success(function (retorno) {
        	$scope.formasPagamento = retorno.formasPagamento;
        })
        .error(function() {
        	MensagemRetorno.tratarMensagemErroComunicacao();
        });
	};
	
	$scope.carregarCategorias = function() {
		var url = $scope.urlPrefixCategoria + "/listarTudo";
        $http.get(url)
        .success(function (retorno) {
        	$scope.categorias = retorno.categorias;
        })
        .error(function() {
        	MensagemRetorno.tratarMensagemErroComunicacao();
        });
	};    
    $scope.iniciar();
    
};