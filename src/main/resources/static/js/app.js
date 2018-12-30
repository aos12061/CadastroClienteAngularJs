//Criação do modulo principal da aplicação
var appCliente = angular.module("appCliente", ['ngRoute']);

appCliente.config(function($routeProvider, $locationProvider) {
	$routeProvider
	.when("/clientes", {
		templateUrl :'view/cliente.html', 
		controller :'clienteController'
	}).when("/cliente/:clienteId", {
		templateUrl :'view/cliente-detalhe.html', 
		controller :'clienteDetalheController'
	}).when("/cidades", {
		templateUrl :'view/cidade.html', 
		controller :'cidadeController'
	}).when("/estados", {
		templateUrl :'view/estado.html', 
		controller :'estadoController'
	}).when("/login", {
		templateUrl :'view/login.html', 
		controller :'loginController'
	}).otherwise({
		redirectTo :'/'
	});
	
	$locationProvider.html5Mode(true);
});