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
		<script src="http://cdnjs.cloudflare.com/ajax/libs/angular.js/1.2.16/angular-resource.min.js"></script>
		<script src="js/app.js"></script>
		<script src="js/service.js"></script>
		<script src="js/controller.js"></script>
		
		<!-- CSS -->
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
		<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css">
		
		<style>
			body { padding-top: 50px; padding-bottom: 50px;}
			table tr th {cursor: pointer;}
		</style>
		
	</head>
	<body data-spy="scroll" data-target=".navbar" data-offset="50" data-ng-app="userManagerApp" data-ng-controller="userManagerController">
	    
		<header>
			<nav class="navbar navbar-default navbar-fixed-top">
			  	<div class="container">
					<!-- Brand and toggle get grouped for better mobile display -->
					<div class="navbar-header">
						<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#main-menu" aria-expanded="false">
							<span class="sr-only">Toggle navigation</span>
							<span class="icon-bar"></span>
							<span class="icon-bar"></span>
							<span class="icon-bar"></span>
						</button>
						<a class="navbar-brand" href="#">
							<!-- <img class="img-responsive" src="img/_logo.jpg" alt="Users" title="Global User Membership" /> -->
						</a>
					</div>
	
					<!-- Collect the nav links, forms, and other content for toggling -->
					<div class="collapse navbar-collapse" id="main-menu">
					  	<ul class="nav navbar-nav">
							<li><a href="#demo">Demo</a></li>
							<li><a href="#design">Design</a></li>
							<li><a href="#about">About</a></li>
					  	</ul>
					</div><!-- /.navbar-collapse -->
					
			  	</div><!-- /.container-->
			</nav>
		</header>
		
		<section class="container" role="main" id="demo">
			<h3>Manage Users</h3>
			<p class="text-muted"><time title="2015-08-31 16:29:00">August 31, 2015</time></p>
					
			<form>
			    <div class="form-group">
			      	<div class="input-group">
			        	<div class="input-group-addon"><i class="fa fa-search"></i></div>
			        	<input type="text" class="form-control" placeholder="Search" data-ng-model="searchTerm">
			      </div>      
			    </div>
			</form>
						  
			<table class="table table-bordered table-striped" >
				<!-- TODO: extract sorting icons in an AngularJS directive -->
				<thead>
					<tr>
						<th data-ng-click="sortType = 'name'; sortReverse = !sortReverse">
							First Name
							<span data-ng-show="sortType == 'name' && !sortReverse" class="fa fa-caret-down"></span>
        					<span data-ng-show="sortType == 'name' && sortReverse" class="fa fa-caret-up"></span>
						</th>
						<th data-ng-click="sortType = 'lastName'; sortReverse = !sortReverse">
							Last Name
							<span data-ng-show="sortType == 'lastName' && !sortReverse" class="fa fa-caret-down"></span>
        					<span data-ng-show="sortType == 'lastName' && sortReverse" class="fa fa-caret-up"></span>
						</th>
						<th data-ng-click="sortType = 'email'; sortReverse = !sortReverse">
							Email 
							<span data-ng-show="sortType == 'email' && !sortReverse" class="fa fa-caret-down"></span>
        					<span data-ng-show="sortType == 'email' && sortReverse" class="fa fa-caret-up"></span>
						</th>
						<th data-ng-click="sortType = 'dateOfBirth'; sortReverse = !sortReverse">
							Date Of Birth 
							<span data-ng-show="sortType == 'dateOfBirth' && !sortReverse" class="fa fa-caret-down"></span>
        					<span data-ng-show="sortType == 'dateOfBirth' && sortReverse" class="fa fa-caret-up"></span>
						</th>
						<th>
							Actions
						</th>
						
					</tr>
				</thead>
				
				<tbody>
					<tr data-ng-repeat="user in users | orderBy:sortColumn:sortReverse | filter : searchTerm ">
						<td>{{ user.name }}</td>
						<td>{{ user.lastName }}</td>
						<td>{{ user.email }}</td>
						<td>{{ user.dateOfBirth }}</td>
						<td>details, delete icon</td>
					</tr>
				</tbody>
				
			</table>
			
			<!-- bootstrap pagination directive -->
			 
		</section>
		
		<section id="design" data-ng-show="false">
			<h3>High level design</h3>
			<div class="row">
				<div class="col-xs-4">
					<p>Technology stack</p>
				</div>
				<div class="col-xs-4">
					<p>Component diagram</p>
				</div>
				<div class="col-xs-4">
					<p>Known issues</p>
				</div>
			</div>
		</section>
		
		<section id="about" data-ng-show="false">
			<h3>About</h3>
		</section>
		
		<!--
		<div class="container">
			<label for="name">Name:</label><input id="name" type="text" data-ng-model="user.name" />
			<label for="name">Email:</label><input id="name" type="email" data-ng-model="user.email" />
			<button ng-click="update()">Add User</button>
		</div>
		-->
		
		<footer>
			<div class="container">
				<ul class="nav navbar-nav navbar-right">
					<li>
						<address>
						  @2015 By <a href="mailto:daniel.velev@gmail.com">Daniel Velev</a>
						</address>
					</li>
				</ul>
			</div>
		</footer>

	</body>
</html>