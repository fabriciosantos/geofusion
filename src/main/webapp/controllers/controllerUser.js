var app = angular.module('Main', []);

app.controller('TodoControllerUser', ['$scope', '$http', function($scope, $http) {
	
	var url = 'http://localhost:8080/geofusion/rest/user/';
	$scope.user = {};
	
	$scope.cleanFields = function(){
		$scope.user.email = "";
	}
	
    $scope.save = function() {
    	$http.post(url, $scope.user).
        success(function (data, status, headers, config) {
        	window.location.replace("thank.jsp");
        	cleanFields();
        }).
        error(function (data, status) {
        	(status == 500) ? $window.alert("Email ja cadastrado") : $window.location.href = "user.jsp";        	
        });
    	
    };
}]);
