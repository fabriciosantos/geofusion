var app = angular.module('Main', []);

app.controller('TodoControllerUser', ['$scope', '$http', function($scope, $http) {
	
	var url = 'http://localhost:8080/geofusion/rest/user/';
	$scope.user = {"id":"", "name":"", "email":"", "compositeKey":""};
	
	$scope.cleanFields = function(){
		$scope.user.email = "";
	}
	
    $scope.save = function(pUser) {
    	var promisse =  $http.post(url, pUser).
        success(function () {
        	console.log(user);
        	cleanFields();
        }).
        error(function (data, status) {
        	 return status;        	
        });
    	return promisse;
    };
}]);
