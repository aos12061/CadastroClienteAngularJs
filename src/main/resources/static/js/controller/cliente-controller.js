//Criação de controlers
appCliente.controller("clienteController", function($scope, $http) {

	$scope.nome = "Isabella";
	$scope.sobreNome = "Vieira Miranda Santos";
	$scope.clientes=[];
	$scope.cliente={};
	
	carregarClientes = function() {
		
		token = localStorage.getItem("userToken");
		
		$http.defaults.headers.common.Authorization = 'Bearer ' + token;
		
		$http({method:'GET', url:'/admin/clientes'})
		.then(function successCallBack(response) {
			$scope.clientes=response.data;
			
			//console.log(response.data);
			//console.log(response.status);		
			
		}, function errorCallBack(response) {
			console.log(response.data);
			console.log(response.status);
			
		});
	};

	$scope.salvarCliente = function() {
		
		if ($scope.frmCliente.$valid) {
		
			$http({method:'POST', url:'/admin/clientes', data:$scope.cliente})
			.then(function successCallBack(response) {
				//$scope.clientes.push(response.data);
				carregarClientes();
				$scope.cancelarAlteracaoCliente();
				
				//$scope.frmCliente.$setPristine(true);
				//console.log(response.data);
				//console.status(response.status);		
				
			}, function errorCallBack(response) {
				console.log(response.data);
				console.log(response.status);
				
			});
		} else {
			window.alert("Favor preencher o campo Nome");
		}
	};

	$scope.excluirCliente = function(cliente) {
		$http({method:'DELETE', url:'/admin/clientes/'+cliente.id})
		.then(function successCallBack(response) {
			
			i = $scope.clientes.indexOf(cliente);	
			$scope.clientes.splice(i, 1);
			
		}, function errorCallBack(response) {
			console.log(response.data);
			console.log(response.status);
			
		});
	};
	
	$scope.alterarCliente = function(cli) {
		$scope.cliente = angular.copy(cli);
	};
	
	$scope.cancelarAlteracaoCliente = function() {
		$scope.cliente={};
	}
	
	carregarClientes();
	
});