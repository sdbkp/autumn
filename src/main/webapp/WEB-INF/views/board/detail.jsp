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
    <script>
        angular.module("ngApp", [])
               .controller("ngCtrl", function($scope, $http) {
            	var path = location.href.substring(location.href.lastIndexOf("/") + 1, location.href.length);
            	var labels = []; 	var foEs = [];  var hpEs = [];
            	var xLabelString = "";
            	
            	$scope.plot = function() {
            		$http({
            			url: location.href,
            			method: "POST"
            		}).then(function(res) {
            			$scope.data = res.data.result; 
            				
            			switch (path) {
            			case "annualMeanEs":
            				$scope.annual();
            				xLabelString = "Year";
            				break;
            				
            			case "monthlyMeanEs":
            				
            				break;
            				
            			case "hourlyMeanEs":
            				
            				break;
            			}
            			
            			var plot_1 = new Chart(document.getElementById("foEs").getContext('2d'), {
                    		type: "line",
                    		data: {
                    			labels: labels,
                    			datasets: [{
                    				label: "foEs",
                    				backgroundColor: "rgba(0, 0, 0, 0)",
        							borderColor: "rgb(255, 99, 132)",
        							data: foEs
                    			}]
                    		},
                    		options: {
                    			scales: {
                    				xAxes: [{
                    					gridLines: {
                    						display: false
                    					},
                    					scaleLabe: {
                    						display: true,
                    						labelString: xLabelString
                    					}
                    				}],
                    				yAxes: [{
                    					type: "linear",
                    					gridLines: {
                    						display: false
                    					},
                    					scaleLabel: {
                    						display: true,
                    						labelString: "foEs (MHz)"
                    					}
                    				}]
                    			}
                    		}
                    		
                    	})
            			
            			var plot_2 = new Chart(document.getElementById("hpEs").getContext('2d'), {
                    		type: "line",
                    		data: {
                    			labels: labels,
                    			datasets: [{
                    				label: "foEs",
                    				backgroundColor: "rgba(0, 0, 0, 0)",
        							borderColor: "rgb(0, 102, 255)",
        							data: hpEs
                    			}]
                    		},
                    		options: {
                    			scales: {
                    				xAxes: [{
                    					gridLines: {
                    						display: false
                    					},
                    					scaleLabe: {
                    						display: true,
                    						labelString: xLabelString
                    					}
                    				}],
                    				yAxes: [{
                    					type: "linear",
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
                    		
                    	})
            		})
            	}
            	$scope.plot();
            	
            	$scope.annual = function() {
            		for (var i = 0; i < $scope.data.length; i++) {
            			labels.push($scope.data[i].year);
            			foEs.push($scope.data[i].frequency);
            			hpEs.push($scope.data[i].height);
            		}
            	}
            	
            	$scope.back = function() {
            		history.back();
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
    	<button type="button" class="button" data-ng-click="back()">Back</button>
    	<div>
    		<div class="plot">
	    		<canvas id="foEs"></canvas>
	    	</div>
	    	
	    	<div class="plot">
	    		<canvas id="hpEs"></canvas>
	    	</div>
    	</div>
    	
    </div>
</body>
</html>