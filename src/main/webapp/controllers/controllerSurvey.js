var app = angular.module('Main', []); 	
app.controller('TodoControllerSurvey', ['$scope', '$http', function($scope, $http) {
	
	var urlUser = '/geofusion/rest/user/';
	var urlSurvey = '/geofugion/rest/survey/';
	
	$scope.user = {};
	
	$scope.key = location.search.split("?");
	
	
	$scope.cleanFields = function(){
		$scope.user.name = "";
		$scope.survey.question1 = "";
		$scope.survey.question2 = "";
		$scope.survey.question3 = "";		
	}
	
	$rootScope.verify = function(){
		$http.get(urlSurvey + $scope.key).
		sucess(function (data, status, headers, config){
		}).
		error(function(){
			(status == 400) ? alert("Email ja cadastrado") : location.href = "user.jsp"; 
		})
	}
	
    $scope.save = function() {
    	$http.post(urlUser, user).
        success(function (data, status, headers, config) {
        	saveSurvey();
        	cleanFields();
        }).
        error(function (data, status) {
        	(status == 500) ? alert("Email ja cadastrado") : location.href = "user.jsp";        	
        });
    	
    };
    
    $scope.saveSurvey = function(){
    	$http.post(urlSurvey, survey).
    	success(function (data, status, headers, config){
    		location.href = "geofusion/congratulations.jsp";
    	}).error(function (data, status){
    		location.href = "user.jsp";
    	})
    }

}]);

//run
app.run(function() {
	verify();

});
