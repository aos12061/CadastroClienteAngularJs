appCliente.controller("loginController", function($scope, $http) {
	
	$scope.usuario={};
	$scope.toke="";
	
	$scope.autenticar = function() {
		$http.post("/autenticar", $scope.usuario).then(function(response) {
			$scope.token=response.data.token;
			console.log("Sucesso" + response);
			localStorage.setItem("userToken", response.data.token);
			
		}, function(response) {
			console.log("Falha" + response);
		});
		console.log("Chamou autenticar")
	}
	
});