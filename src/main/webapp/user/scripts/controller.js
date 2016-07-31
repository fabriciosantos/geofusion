 	
app.controller('TodoController', ['$scope', '$http', '$modal', function($scope, $http, $modal) {
	
	$scope.user = [];
		
    $scope.save = function() {
    	$http.post('/geofusion/rest/user/').
        success(function (data, status, headers, config) {
        	
        }).
        error(function (data, status) {
        
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
