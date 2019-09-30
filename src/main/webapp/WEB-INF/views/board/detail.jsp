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
    <link rel="stylesheet" href="/resources/css/chart.css">
    <script src="https://use.fontawesome.com/releases/v5.2.0/js/all.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.9/angular.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.9/angular-animate.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.8.0/Chart.bundle.js"></script>
    <!-- <script src="https://cdn.jsdelivr.net/npm/chart.js@2.8.0"></script> -->
    <script>
        angular.module("ngApp", [])
               .controller("ngCtrl", function($scope, $http) {
               
            	var annualLabels = [];
            	var annualfoF2 = [];
            	var annualhmF2 = [];
            	
				$scope.annual = function() {
					$http({
						url: location.href + "/annualMean",
						method: "POST"
					}).then(function(res) {
						$scope.annualData = res.data.annualMean;
						
						for (var i = 0; i < $scope.annualData.length; i++) {
							annualLabels.push($scope.annualData[i].year);
							annualfoF2.push($scope.annualData[i].foF2);
							annualhmF2.push($scope.annualData[i].hmF2);
						}
						
						var chart_1 = new Chart(document.getElementById("annualMean").getContext('2d'), {
							type: 'line',
							data: {
								labels: annualLabels,
								datasets: [{
									label: "Annual Mean foF2",
									yAxisID: "foF2",
									backgroundColor: 'rgba(0,0,0,0)',
		        	    			borderColor: 'rgb(255, 99, 132)',
									data: annualfoF2
								} , {
									label: "Annual Mean hmF2",
									yAxisID: "hmF2",
									backgroundColor: 'rgba(0,0,0,0)',
									borderColor: 'rgb(0, 102, 255)',
									data: annualhmF2
								}]
							},
							options: {
								scales: {
									yAxes: [{
										id: 'foF2',
										type: "linear",
										position: "left",
										gridLines: {
											display: false
										}
									}, {
										id: "hmF2",
										type: "linear",
										position: "right",
										gridLines: {
											display: false
										}
										
									}],
									xAxes: [{
										gridLines: {
											display: false
										}
									}]
								}
							}
						});
						
					});
					
				}
				$scope.annual();
               
        })
    </script>
</head>
<body data-ng-app="ngApp", data-ng-controller="ngCtrl">
    <header><h1>SSLAB</h1></header>
    
    <nav>
    	<ul>
            <a href="/"><li>Home</li></a>
            <li style="background-color: #EFF5FB;">IC437</li>
            <a href="/update"><li>Data Update</li></a>
        </ul>
    </nav>
    
    <div class="container">
    	<div class="chart-1">
    		<canvas id="annualMean"></canvas>
    	</div>
    </div>
</body>
</html>