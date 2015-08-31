app.factory('UserService', ['$resource', function($resource) {
  return $resource('/web/users/:id');
}]);