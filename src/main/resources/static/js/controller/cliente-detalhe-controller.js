appCliente.controller("clienteDetalheController", function($scope, $routeParams, $http) {
	$scope.cliente={};
	$http.get("cliente/"+$routeParams.clienteId).then(function(response) {
		$scope.cliente=response.data;
	}, function(response) {
		console.log(response);
		
	});
});