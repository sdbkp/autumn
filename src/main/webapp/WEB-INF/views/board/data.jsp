<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>home</title>
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Raleway">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
    <link rel="stylesheet" href="/resources/css/common.css">
    <link rel="stylesheet" href="/resources/css/update.css">
    <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.9/angular.min.js"></script>
    <script src="/resources/js/update.js"></script>
</head>
<body data-ng-app="ngApp", data-ng-controller="ngCtrl">
    <header><h1>SSLAB</h1></header>
    
    <nav>
    	<ul>
            <a href="/"><li>Introduction</li></a>
            <a><li style="background-color: #EFF5FB;">Data</li></a>
            <div data-ng-repeat="station in stations">
            	<a href="/{{station}}"><li>{{station}}</li></a>
            </div>
        </ul>
    </nav>
    
    <div class="container">
    	<div class="inner-container">
    		<div style="margin-bottom: 50px;">
	        	<p>	&emsp;&emsp;Ionosondes measure the critical frequencies of ionized layers in ionosphere and their virtual heights routinely day and night. 
	        		The sporadic E data used in this project were obtained from the ionosonde measurements at Icheon (37.14°N, 127.54°E, IC) and Jeju (33.4°N, 126.30°E, JJ) in 2010–2019. 
	        		The IC and JJ ionosondes are a type of the digisonde, Digisonde Portable Sounder 4D (DPS4D). The observations of the ionosondes are analyzed by 
	        		a software of Automatic Real-Time Ionogram Scaling with True analysis (ARTIST-5) and then saved as a format of Standard Archiving Output (SAO). 
	        		The auto-scaled data can be downloaded at the website of the National Oceanic and Atmospheric Administration. 
	        	</p>
	        </div>
	        
    		<label ><p>Station:&nbsp;</p></label>
    		<input type="text" class="form-control" data-ng-model="station">
    		<button type="button" class="button" data-ng-click="search()">Search</button>
    		<button type="button" class="btn button" data-ng-disabled="!${sessionScope.admin}" data-ng-click="add()">Add</button>    	
    	
        	<div style="margin-top: 20px;">
	        	<label style="display: inline-block;"><p>More Data:&nbsp;<a href="ftp://ftp.ngdc.noaa.gov/ionosonde/data/">Click here :D</a></p></label>
	        	<button type="button" style="float: right;" class="btn button" data-ng-disabled="!${sessionScope.admin}" data-ng-click="update()">Update</button>
        	</div>
        	
        	<table class="table table-striped" style="margin-top: 30px;">
	        	<thead>
	        		<tr>
	        			<th>Station</th>
	        			<th>Last Updated Time</th>
	        			<th>File Name</th>
	        			<th><input type="checkbox" data-ng-model="check.all" data-ng-disabled="!${sessionScope.admin}" data-ng-click="checkAll()"></th>
	        		</tr>
	        	</thead>
	        	<tbody>
	        		<tr data-ng-repeat="info in updateInfo">
	        			<th>{{info.station}}</th>
	        			<th>{{info.setTime}}</th>
	        			<th>{{info.fileName}}</th>
	        			<th><input type="checkbox" data-ng-checked="check.all" data-ng-disabled="!${sessionScope.admin}" data-ng-model="info.check" data-ng-click="check(info)"></th>
	        		</tr>
	        	</tbody>
	        </table>
        </div>
    </div>
</body>
</html>