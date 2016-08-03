var app = angular.module('Main', []);

app.controller('surveyController', ['$scope', 'surveyFactory', function($scope, surveyFactory) {
		
	$scope.key = location.search.split("?");
	
	$scope.user = {"id":"", "name":"", "email":"", "compositeKey": $scope.key[1]};
	$scope.survey = {"id":"","question1":"","question2":"","question3":"","date":"", "user":$scope.user};
	
	$scope.verify = function(){
		surveyFactory.verify($scope.key[1]).then(function (data){
			location.href = "congratulations.html";
			return data;
		}).catch(function(data){
			(data.status == 400) ? alert("O email já respondeu a pesquisa.") : alert("Cadastre o seu email novamente.");
			location.href = "index.html";
		});
	};
	
	$scope.save = function() {
		surveyFactory.save($scope.key[1], $scope.survey).then(function (data) {
			return data;
		}).catch(function (data) {
			(data.status == 500) ? alert("Responda novamente a pesquisa."):null;
			(data.status == 400) ? alert("O email já respondeu a pesquisa.") :null;
	    });
	};
	
   $scope.verify();    
}]);

