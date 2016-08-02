var app = angular.module('Main', []);

app.controller('surveyController', ['$scope', 'surveyFactory', function($scope, surveyFactory) {
		
	$scope.user = {"id":"", "name":"", "email":"", "compositeKey":""};
	$scope.survey = {"id":"","question1":"","question2":"","question3":"","date":""};
	
	$scope.key = location.search.split("?");

	$scope.cleanFields = function(){
		$scope.user.name = "";
		$scope.survey.question1 = "";
		$scope.survey.question2 = "";
		$scope.survey.question3 = "";		
	}
	
	$scope.updateUser = function() {
		surveyFactory.updateUser($scope.user).then(function (data) {
			return data;
		}).catch(function (data) {
	         alert(data);
	    });
	};
	
	$scope.verify = function(){
		surveyFactory.verify($scope.key).then(function (data){
			return data;
		}).catch(function(data){
			(data.status == 404) ? alert("Email ja respondei a pesquisa.") : alert("Cadastre o email denovo.");
			alert(data);
			location.href = "index.html";
		});
	};
	
	$scope.saveSurvey = function() {
		surveyFactory.safeSurvey($scope.survey).then(function (data) {
			return data;
		}).catch(function (data) {
			(data.status == 400) ? alert("Email ja cadastrado") : alert("Chave não condiz com email.");
	    });
	};
	
   $scope.verify();
    
}]);

