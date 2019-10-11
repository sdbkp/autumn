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
    <link rel="stylesheet" href="/resources/css/common.css">
    <link rel="stylesheet" href="/resources/css/plots.css">
    <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.9/angular.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.8.0/Chart.bundle.js"></script>
    <script src="/resources/js/plots.js"></script>
</head>
<body data-ng-app="ngApp", data-ng-controller="ngCtrl">
    <header><h1>SSLAB</h1></header>
    
    <nav>
    	<ul>
            <a href="/"><li>Home</li></a>
            <a href="/update"><li>Update List</li></a>
            <div data-ng-repeat="station in stations">
            	<a href="/{{station}}"><li>{{station}}</li></a>
            </div>
        </ul>
    </nav>
    
    <div class="container">
    	<div class="plot">
    		<canvas id="annualMeanEs" data-ng-click="detail('annualMeanEs')"></canvas>
    	</div>
    	
    	<div class="plot"></div>
    	
    	<div class="plot">
    		<canvas id="monthlyMeanEs" data-ng-click="detail('monthlyMeanEs')"></canvas>
    	</div>
    	
    	<div class="plot">
    	</div>
    	
    	<div class="plot">
    		<canvas id="hourlyMeanEs" data-ng-click="detail('hourlyMeanEs')"></canvas>
    	</div>
    	
    	<div class="plot"></div>
    </div>
</body>
</html>