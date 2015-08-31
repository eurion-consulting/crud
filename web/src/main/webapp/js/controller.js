app.controller('userManagerController', ['$scope', 'UserService', function ($scope, UserService) {
 
	$scope.sortColumn = "name";
	$scope.sortReverse = false;
	$scope.searchTerm = "";
 
	var users = UserService.query(function() {
	    console.log(users);
	}); //query() returns all the users
	
	$scope.users = users;
	$scope.user = UserService.get({id: 1});
	//update
	//create
 	$scope.update = function(){
 		var response = $scope.user.$save();
 		console.debug("Response for Save operation: " + response);
 	}
 	
}]);

/* see if second controller is needed
app.controller('tableController', ['$scope', function ($scope) {
	
	$scope.sortColumn = "name";
	$scope.sortReverse = false;
	$scope.searchTerm = "";

}]);*/