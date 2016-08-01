 	
app.controller('TodoController', ['$scope', '$http', function($scope, $http) {
	
	var url = '/geofusion/rest/user/';
	$scope.user = {};
	
	$scope.cleanFields = function(){
		$scope.user.email = "";
	}
	
    $scope.save = function() {
    	$http.post(url, user).
        success(function (data, status, headers, config) {
        	window.location.href = "thank.jsp";
        	cleanFields();
        }).
        error(function (data, status) {
        	(status == 500) ? $window.alert("Email ja cadastrado") : $window.location.href = "user.jsp";        	
        });
    	
    };

    
    $scope.getEdit = function(user) {
    	$http.get('/geofusion/rest/user/').
        success(function (data, status, headers, config) {
        	
        }).
        error(function (data, status) {

        });
    };

    $scope.clearField = function() {
    }       

}]);
