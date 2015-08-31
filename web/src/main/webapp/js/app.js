var app = angular.module('userManagerApp', ['ngResource']);

app.config(['$httpProvider', function ($httpProvider) {
    
	$httpProvider.interceptors.push(function ($q) {
		return {
		    'responseError': function (response) {
		        // global error handler 
		    	console.debug(response);
		    	return $q.reject(response);
		       
		    }
		};
	})
}]);



