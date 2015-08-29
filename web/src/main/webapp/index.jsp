<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<title>User Management Task</title>
		
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<meta name="description" content="Small web application that manages user accounts in a database">
   		<meta name="author" content="Daniel Velev" >
		
		<!--  errors are not readable in the minified version 
		<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.4/angular.min.js"></script> -->
		
		<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.4/angular.js"></script>
		<script src="https://code.jquery.com/jquery-2.1.4.min.js"></script>
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
		<script src="./js/app.js"></script>
		
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
		
		<style>
			body { padding-top: 40px;}
		</style>
		
	</head>
	<body data-ng-app="userManagerApp" data-ang-controller="userManagerController">
	    
		<header>
			<div class="navbar navbar-fixed-top">
				<div class="navbar-header">
					<p>		
						User Management
					</p>
				</div>
			</div>
		</header>
		
		<div class="container">
			
			<div class="row">
				 <div data-ng-repeat="user in users">
				 	<span>
				    	{{user.name}}
				    </span>
				 </div>
			</div>
			
			<div class="row">
				<div class="col-xs-4">
					<p>Section 1</p>
				</div>
				<div class="col-xs-4">
					<p>Section 2</p>
				</div>
				<div class="col-xs-4">
					<p>Section 3</p>
				</div>
			</div>
		</div>
		
		<footer>
			<div class="container">
				<ul class="nav navbar-nav navbar-right">
					<li><p class="text-muted">Daniel Velev @2015</p></li>
				</ul>
			</div>
		</footer>

	</body>
</html>