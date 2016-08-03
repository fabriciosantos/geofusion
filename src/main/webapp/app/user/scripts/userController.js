var app = angular.module('Main', []);

app.controller('userController', ['$scope', 'userFactory', function($scope, userFactory) {
	
	$scope.user = {id:'', name:'', email:'', compositeKey:''};
	
	$scope.cleanFields = function(){
		$scope.user.email = "";
	}
		
	$scope.save = function() {
		userFactory.save($scope.user).then(function (data) {
		 	$scope.cleanFields();
       		location.href = "thank.html";
	     }).catch(function (data) {
	    	 (data.status == 400) ? alert("Email já cadastrado.") : alert("Por favor digite novamento o seu email.");
	     });
	};	
}]);
