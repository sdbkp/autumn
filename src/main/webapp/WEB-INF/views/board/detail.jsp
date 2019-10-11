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
    <link rel="stylesheet" href="/resources/css/detail.css">
    <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.9/angular.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.8.0/Chart.bundle.js"></script>
    <script src="/resources/js/detail.js"></script>
</head>
<body data-ng-app="ngApp", data-ng-controller="ngCtrl">
    <header><h1>SSLAB</h1></header>
    
    <nav>
    	<ul>
            <a href="/"><li>Home</li></a>
            <a href="/update"><li>Update</li></a>
            <div data-ng-repeat="station in stations">
            	<a href="/{{station}}"><li data-ng-style="setColor(station)">{{station}}</li></a>
            </div>
        </ul>
    </nav>
    
    <div class="container">
    	<div class="inner-container-1">
    	<button type="button" class="button" data-ng-click="back()">Back</button><br>
    	
    		<div class="plot">
	    		<canvas id="foEs"></canvas>
	    	</div>
	    	
	    	<div class="plot">
	    		<canvas id="hpEs"></canvas>
	    	</div>
    	
   		<div class="inner-container-2" >
   			<div data-ng-repeat="label in list" style="margin: auto;">
   				<input type="checkbox" data-ng-click="detail(label)" style="margin: 5px">{{label}}
   			</div>
   		</div>
   		</div>
    </div>
</body>
</html>