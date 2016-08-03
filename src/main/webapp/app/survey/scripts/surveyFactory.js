app.factory('surveyFactory', function($http){
	
	var surveyFactory = {};	
	var urlUser = '/geofusion/rest/user';
	var urlSurvey = '/geofusion/rest/survey';
	
    surveyFactory.updateUser = function(key, user) {
    	var promisse = $http.put(urlUser+"/"+key, user).success(function (response, data) {
    		return {status:204};
        }).error(function (data, status) {
            return status;
        });
    	return promisse;
    };
    
    surveyFactory.verify = function(key) {
    	var promisse = $http.get(urlSurvey+"/"+key).success(function (response, data) {
    		alert(response);
    		return data;
         }).error(function (data, status) {
         	return status;        	
        });    	
    	return promisse;
    };
    
    surveyFactory.save = function(key, survey){
    	var promisse = $http.post(urlSurvey+"/"+key, survey).
    	success(function (response, data){
    		return data;
    	}).error(function (data, status){
    		return status
    	});
    	return promisse;
    };    
    return surveyFactory;
})