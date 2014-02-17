function ContasController($scope, $location, $http) {
	
	$scope.urlPrefixFormaPagamento = "/formasPagamento";
	
	$scope.urlPrefixCategoria = "/categorias";
	
	$scope.urlPrefix = "/contas";
	
	$scope.contas = {};
	
	$scope.totalMes = {};
	
	$scope.contasAtrasadas = {};
	
	$scope.totalAtrasado = {};
	
	$scope.totalGeral = {};
	
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
		$scope.buscarContasMes(pagina);
		$scope.buscarContasAtrasadas(pagina);
		$scope.calcularValorTotalContasMes();
	};
	
	$scope.buscarContasMes = function(pagina) {
		var url = $scope.urlPrefix + "/buscar/"+pagina+"/"+$scope.busca.mes+"/"+$scope.busca.ano;
        $http.get(url)
        .success(function (retorno) {
        	$scope.contas = retorno.contas;
        	$scope.totalMes = retorno.totalContaVO;
        }).error(function() {
        	MensagemRetorno.tratarMensagemErroComunicacao();
        });
	};
	
	$scope.buscarContasAtrasadas = function(pagina) {
		var url = $scope.urlPrefix + "/buscarContasEmAtraso/"+pagina+"/"+$scope.busca.mes+"/"+$scope.busca.ano;
        $http.get(url).success(function (retorno) {
        	$scope.contasAtrasadas = retorno.contas;
        	$scope.totalAtrasado = retorno.totalContaVO;
        }).error(function() {
        	MensagemRetorno.tratarMensagemErroComunicacao();
        });
	};
	
	$scope.calcularValorTotalContasMes = function() {
		var url = $scope.urlPrefix + "/calcularValorTotalContasMes/"+$scope.busca.mes+"/"+$scope.busca.ano;
        $http.get(url).success(function (retorno) {
        	$scope.totalGeral = retorno;
        }).error(function() {
        	MensagemRetorno.tratarMensagemErroComunicacao();
        });
	};
	
	$scope.listar = function(pagina) {
		var url = $scope.urlPrefix + "/listar/"+pagina;
        $http.get(url)
        .success(function (retorno) {
        	$scope.conta = retorno.contas;
        }).error(function() {
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
        }).error(function() {
        	MensagemRetorno.tratarMensagemErroComunicacao();
        });
    };
	
	$scope.carregarFormaPagamento = function() {
		var url = $scope.urlPrefixFormaPagamento + "/listarTudo";
        $http.get(url)
        .success(function (retorno) {
        	$scope.formasPagamento = retorno.formasPagamento;
        }).error(function() {
        	MensagemRetorno.tratarMensagemErroComunicacao();
        });
	};
	
	$scope.carregarCategorias = function() {
		var url = $scope.urlPrefixCategoria + "/listarTudo";
        $http.get(url)
        .success(function (retorno) {
        	$scope.categorias = retorno.categorias;
        }).error(function() {
        	MensagemRetorno.tratarMensagemErroComunicacao();
        });
	};    
    $scope.iniciar();
    
};