app.factory('userFactory', function($http){
	
	var userFactory = {};	
	var url = '/geofusion/rest/user';
		
    userFactory.save = function(user) {
    	var promisse = $http.post(url, user).success(function (response, data) {
        	return response.data;
        }).error(function (data, status) {
            return status;
        });
    	return promisse;
    };
    
    return userFactory;
})