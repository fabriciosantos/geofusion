app.factory('surveyFactory', function($http){
	
	var surveyFactory = {};	
	var url = '/geofusion/rest/survey';
     
    surveyFactory.verify = function(key) {
    	var promisse = $http.get(url+"/"+key).success(function (response, data) {
    		return data;
         }).error(function (data, status) {
         	return status;        	
        });    	
    	return promisse;
    };
    
    surveyFactory.save = function(key, survey){
    	var promisse = $http.post(url+"/"+key, survey).
    	success(function (response, data){
    		return data;
    	}).error(function (data, status){
    		return status
    	});
    	return promisse;
    };    
    return surveyFactory;
})