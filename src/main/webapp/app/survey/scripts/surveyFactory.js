app.factory('surveyFactory', function($http){
	
	var surveyFactory = {};	
	var urlUser = '/geofusion/rest/user';
	var urlSurvey = '/geofision/rest/survey';
	
    surveyFactory.updateUser = function(user) {
    	var promisse = $http.put(urlUser, user).success(function (response, data) {
    		return {status:204};
        }).error(function (data, status) {
            return status;
        });
    	return promisse;
    };
    
    surveyFactory.verify = function(key) {
    	var promisse = $http.get(urlSurvey+"/"+key).success(function (response, data) {
    		return data;
         }).error(function (data, status) {
        	 alert(key);
        	return status;        	
        });    	
    	return promisse;
    };
    
    surveyFactory.saveSurvey = function(survey){
    	$http.post(urlSurvey, survey).
    	success(function (response, data){
    		return data;
    	}).error(function (data, status){
    		return status
    	});
    	return promisse;
    };
    
    return surveyFactory;
})