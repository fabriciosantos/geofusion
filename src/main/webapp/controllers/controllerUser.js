var app = angular.module('Main', []);

app.controller('TodoControllerUser', ['$scope', '$http', function($scope, $http) {
	
	var url = 'http://localhost:8080/geofusion/rest/user/';
	$scope.user = {"id":"", "name":"", "email":"", "compositeKey":""};
	
	$scope.cleanFields = function(){
		$scope.user.email = "";
	}
	
    $scope.save = function() {
    	$http.post(url, $scope.user).
        success(function () {
        	$scope.cleanFields();
        	 location.href="geofusion/thank.jsp";
        }).
        error(function (data, status) {
   
        	 return status;        	
        });    	
    };
}]);
