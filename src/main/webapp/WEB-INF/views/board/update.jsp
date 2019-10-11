<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>home</title>
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Raleway">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
    <link rel="stylesheet" href="/resources/css/common.css">
    <link rel="stylesheet" href="/resources/css/update.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.9/angular.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.9/angular-animate.js"></script>
    <script src="/resources/js/update.js"></script>
</head>
<body data-ng-app="ngApp", data-ng-controller="ngCtrl">
    <header><h1>SSLAB</h1></header>
    
    <nav>
    	<ul>
            <a href="/"><li>Home</li></a>
            <a><li style="background-color: #EFF5FB;">Update List</li></a>
            <div data-ng-repeat="station in stations">
            	<a href="/{{station}}"><li>{{station}}</li></a>
            </div>
        </ul>
    </nav>
    
    <div class="container">
    	<div class="inner-container">
    		<label ><p>Station: </p></label>
    		<input type="text" class="form-control" data-ng-model="station">
    		<button type="button" class="button" data-ng-click="search()">Search</button>
    		<button type="button" class="btn button" data-ng-disabled="!${sessionScope.admin}" data-ng-click="add()">Addition</button>    	
    	</div>
    	
        <div class="inner-container" style="text-align: right;">
        	<button type="button" class="btn button" data-ng-disabled="!${sessionScope.admin}" data-ng-click="update()">Data Update</button>
        	<table class="table table-striped" style="margin-top: 30px;">
	        	<thead>
	        		<tr>
	        			<th>Station</th>
	        			<th>Last Updated Time</th>
	        			<th>File Name</th>
	        			<th><input type="checkbox" data-ng-model="check.all" data-ng-click="checkAll()"></th>
	        		</tr>
	        	</thead>
	        	<tbody>
	        		<tr data-ng-repeat="info in updateInfo">
	        			<th>{{info.station}}</th>
	        			<th>{{info.setTime}}</th>
	        			<th>{{info.fileName}}</th>
	        			<th><input type="checkbox" data-ng-checked="check.all" data-ng-model="info.check" data-ng-click="check(info)"></th>
	        		</tr>
	        	</tbody>
	        </table>
        </div>
    </div>
</body>
</html>