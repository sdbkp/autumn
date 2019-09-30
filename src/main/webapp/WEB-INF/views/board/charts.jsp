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
    <link rel="stylesheet" href="/resources/css/charts.css">
    <script src="https://use.fontawesome.com/releases/v5.2.0/js/all.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.9/angular.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.9/angular-animate.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.8.0/Chart.bundle.js"></script>
    <!-- <script src="https://cdn.jsdelivr.net/npm/chart.js@2.8.0"></script> -->
    <script>
        angular.module("ngApp", [])
               .controller("ngCtrl", function($scope, $http) {
               
            	var annualLabels = [];
            	var annualFoF2 = [];
            	var annualHmF2 = [];
            	$scope.test = true;
				$scope.annual = function() {
					$http({
						url: location.href + "/annualMean",
						method: "POST"
					}).then(function(res) {
						$scope.avgF2 = res.data.avgF2;
						
						for (var i = 0; i < $scope.avgF2.length; i++) {
							annualLabels.push($scope.avgF2[i].year);
							annualFoF2.push($scope.avgF2[i].foF2);
							annualHmF2.push($scope.avgF2[i].hmF2);
						}
						
						var chart_1 = new Chart(document.getElementById("annualMeanFoF2").getContext('2d'), {
							type: 'line',
							data: {
								labels: annualLabels,
								datasets: [{
									label: "Annual Mean foF2",
									backgroundColor: 'rgba(0,0,0,0)',
		        	    			borderColor: 'rgb(255, 99, 132)',
									data: annualFoF2
								}]
							},
							options: {
								scales: {
									yAxes: [{
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
						
						var chart_2 = new Chart(document.getElementById("annualMeanHmF2").getContext('2d'), {
							type: 'line',
							data: {
								labels: annualLabels,
								datasets: [{
									label: "Annual Mean hmF2",
									backgroundColor: 'rgba(0,0,0,0)',
									borderColor: 'rgb(0, 102, 255)',
									data: annualHmF2
								}]
							},
							options: {
								scales: {
									yAxes: [{
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
               
				$scope.detail = function() {
					
				}
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
    	<div class="chart" data-ng-show="test">
    		<canvas id="annualMeanFoF2" data-ng-click=detail()></canvas>
    	</div>
    	<div class="chart">
    		<canvas id="annualMeanHmF2" data-ng-click=detail()></canvas>
    	</div>
    </div>
</body>
</html>