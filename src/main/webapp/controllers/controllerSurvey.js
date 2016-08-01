var app = angular.module('Main', []); 	
app.controller('TodoControllerSurvey', ['$scope', '$http', function($scope, $http) {
	
	var urlUser = '/geofusion/rest/user/';
	var urlSurvey = '/geofugion/rest/survey/';
	
	$scope.user = {};
	
	var vkey = location.search.split("?");
	
	
	$scope.cleanFields = function(){
		$scope.user.name = "";
		$scope.survey.question1 = "";
		$scope.survey.question2 = "";
		$scope.survey.question3 = "";		
	}
	
	$scope.verify = function(key){
		$http.get(urlUser + key).
		sucess(function (data, status, headers, config){
		}).
		error(function(){
			(status == 500) ? $window.alert("Email ja cadastrado") : $window.location.href = "user.jsp"; 
		})
	}
	
    $scope.save = function() {
    	$http.post(urlUser, user).
        success(function (data, status, headers, config) {
        	saveSurvey();
        	cleanFields();
        }).
        error(function (data, status) {
        	(status == 500) ? $window.alert("Email ja cadastrado") : $window.location.href = "user.jsp";        	
        });
    	
    };
    
    $scope.saveSurvey = function(){
    	$http.post(urlSurvey, survey).
    	success(function (data, status, headers, config){
    		window.location.href = "congratulations.jsp";
    	}).error(function (data, status){
    		window.location.href = "user.jsp";
    	})
    }

}]);

//run
app.run(function($rootScope, translationService) {
	verify(key);

});
