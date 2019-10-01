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
    <script src="https://use.fontawesome.com/releases/v5.2.0/js/all.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.9/angular.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.9/angular-animate.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.8.0/Chart.bundle.js"></script>
    <!-- <script src="https://cdn.jsdelivr.net/npm/chart.js@2.8.0"></script> -->
    <script>
        angular.module("ngApp", [])
               .controller("ngCtrl", function($scope, $http) {
               
            	var annualLabels = []; 	var annualFoEs = [];  var annualHpEs = [];
            	var monthlyLabels = []; var monthlyFoEs = []; var monthlyHpEs = [];
            	var hourlyLabels = []; 	var hourlyFoEs = [];  var hourlyHpEs = [];
				$scope.plot = function() {
					$http({
						url: location.href + "/plot",
						method: "POST"
					}).then(function(res) {
						console.log(res.data);
						/* Annual Mean of F2 layer */
						$scope.avgEsYear = res.data.avgEsYear;
						for (var i = 0; i < $scope.avgEsYear.length; i++) {
							annualLabels.push($scope.avgEsYear[i].year);
							annualFoEs.push($scope.avgEsYear[i].frequency);
							annualHpEs.push($scope.avgEsYear[i].height);
						}
						console.log($scope.avgF2Year);
						/* Monthly Mean of F2 layer */
						$scope.avgEsMonth = res.data.avgEsMonth;
						for (var i = 0; i < $scope.avgEsMonth.length; i++) {
							monthlyLabels.push($scope.avgEsMonth[i].month);
							monthlyFoEs.push($scope.avgEsMonth[i].frequency);
							monthlyHpEs.push($scope.avgEsMonth[i].height);
						}
						
						/* Hourly Mean of F2 layer */
						$scope.avgEsHour = res.data.avgEsHour;
						for (var i = 0; i < $scope.avgEsHour.length; i++) {
							hourlyLabels.push($scope.avgEsHour[i].hh);
							hourlyFoEs.push($scope.avgEsHour[i].frequency);
							hourlyHpEs.push($scope.avgEsHour[i].height);
						}
						
						var chart_1 = new Chart(document.getElementById("annualMeanFoF2").getContext('2d'), {
							type: 'line',
							data: {
								labels: annualLabels,
								datasets: [{
									label: "Annual Mean foEs",
									backgroundColor: 'rgba(0,0,0,0)',
		        	    			borderColor: 'rgb(255, 99, 132)',
									data: annualFoEs
								}]
							},
							options: {
								scales: {
									xAxes: [{
										gridLines: {
											display: false
										}, 
										scaleLabel: {
											display: true,
											labelString: 'Year'
										}
									}],
									yAxes: [{
										gridLines: {
											display: false
										}, 
										scaleLabel: {
											display: true,
											labelString: 'foEs (MHz)'
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
									label: "Annual Mean h'Es",
									backgroundColor: 'rgba(0,0,0,0)',
									borderColor: 'rgb(0, 102, 255)',
									data: annualHpEs
								}]
							},
							options: {
								scales: {
									xAxes: [{
										gridLines: {
											display: false
										}, 
										scaleLabel: {
											display: true,
											labelString: 'Year'
										}
									}],
									yAxes: [{
										gridLines: {
											display: false
										}, 
										scaleLabel: {
											display: true,
											labelString: "h'Es (km)"
										}
									}]
								}
							}
						});
						
						var chart_3 = new Chart(document.getElementById("monthlyMeanFoF2").getContext('2d'), {
							type: 'line',
							data: {
								labels: monthlyLabels,
								datasets: [{
									label: "Monthly Mean foEs",
									backgroundColor: 'rgba(0,0,0,0)',
		        	    			borderColor: 'rgb(255, 99, 132)',
									data: monthlyFoEs
								}]
							},
							options: {
								scales: {
									xAxes: [{
										gridLines: {
											display: false
										}, 
										scaleLabel: {
											display: true,
											labelString: 'Month'
										}
									}],
									yAxes: [{
										gridLines: {
											display: false
										}, 
										scaleLabel: {
											display: true,
											labelString: 'foEs (MHz)'
										}
									}]
								}
							}
						});
						
						var chart_4 = new Chart(document.getElementById("monthlyMeanHmF2").getContext('2d'), {
							type: 'line',
							data: {
								labels: monthlyLabels,
								datasets: [{
									label: "Monthly Mean h'Es",
									backgroundColor: 'rgba(0,0,0,0)',
									borderColor: 'rgb(0, 102, 255)',
									data: monthlyHpEs
								}]
							},
							options: {
								scales: {
									xAxes: [{
										gridLines: {
											display: false
										}, 
										scaleLabel: {
											display: true,
											labelString: 'Month'
										}
									}],
									yAxes: [{
										gridLines: {
											display: false
										}, 
										scaleLabel: {
											display: true,
											labelString: "h'Es (km)"
										}
									}]
								}
							}
						});
						
						var chart_5 = new Chart(document.getElementById("hourlyMeanFoF2").getContext('2d'), {
							type: 'line',
							data: {
								labels: hourlyLabels,
								datasets: [{
									label: "Hourly Mean foEs",
									backgroundColor: 'rgba(0,0,0,0)',
		        	    			borderColor: 'rgb(255, 99, 132)',
									data: hourlyFoEs
								}]
							},
							options: {
								scales: {
									xAxes: [{
										gridLines: {
											display: false
										}, 
										scaleLabel: {
											display: true,
											labelString: 'UT'
										}
									}],
									yAxes: [{
										gridLines: {
											display: false
										}, 
										scaleLabel: {
											display: true,
											labelString: 'foEs (MHz)'
										}
									}]
								}
							}
						});
						
						var chart_6 = new Chart(document.getElementById("hourlyMeanHmF2").getContext('2d'), {
							type: 'line',
							data: {
								labels: hourlyLabels,
								datasets: [{
									label: "Hourly Mean h'Es",
									backgroundColor: 'rgba(0,0,0,0)',
									borderColor: 'rgb(0, 102, 255)',
									data: hourlyHpEs
								}]
							},
							options: {
								scales: {
									xAxes: [{
										gridLines: {
											display: false
										}, 
										scaleLabel: {
											display: true,
											labelString: 'UT'
										}
									}],
									yAxes: [{
										gridLines: {
											display: false
										}, 
										scaleLabel: {
											display: true,
											labelString: "h'Es (km)"
										}
									}]
								}
							}
						});
					});
					
				}
				$scope.plot();
				
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
    	<div class="plot" data-ng-show="true">
    		<canvas id="annualMeanFoF2" data-ng-click=detail()></canvas>
    	</div>
    	<div class="plot">
    		<canvas id="annualMeanHmF2" data-ng-click=detail()></canvas>
    	</div>
    	<div class="plot">
    		<canvas id="monthlyMeanFoF2" data-ng-click=detail()></canvas>
    	</div>
    	<div class="plot">
    		<canvas id="monthlyMeanHmF2" data-ng-click=detail()></canvas>
    	</div>
    	<div class="plot">
    		<canvas id="hourlyMeanFoF2" data-ng-click=detail()></canvas>
    	</div>
    	<div class="plot">
    		<canvas id="hourlyMeanHmF2" data-ng-click=detail()></canvas>
    	</div>
    </div>
</body>
</html>