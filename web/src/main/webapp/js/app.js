var userManagerModule = angular.module('userManagerApp', []);

userManagerModule.controller('userManagerController', function ($scope, $http) {
 
	//get all users and display initially
	$http.get($resource('users')).
	    	
		success(function(response) {
			$scope.users = response;
			console.debug( response);
		}).
		error(function(data, obj){
			console.debug(data);
		});
 
	
});

userManagerModule.config(['$httpProvider', function ($httpProvider) {
    
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



