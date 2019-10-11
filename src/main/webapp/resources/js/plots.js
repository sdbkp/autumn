angular.module("ngApp", [])
	   .controller("ngCtrl", function($scope, $http) {
		    $scope.annualLabels = []; 	$scope.annualFoEs = [];  $scope.annualHpEs = [];
		    $scope.monthlyLabels = []; $scope.monthlyFoEs = []; $scope.monthlyHpEs = [];
		    $scope. hourlyLabels = []; 	$scope.hourlyFoEs = [];  $scope.hourlyHpEs = [];
		    $scope.yAxes = [{
				id: "left",
				type: "linear",
				position: "left",
				gridLines: {
					display: false
				},
				scaleLabel: {
					display: true,
					labelString: "foEs (MHz)"
				},
				ticks: {
					min: 2,
					max: 5.5
				}
			}, {
				id: "right",
				type: "linear",
				position: "right",
				gridLines: {
					display: false
				},
				scaleLabel: {
					display: true,
					labelString: "h'Es (km)"
				},
				ticks: {
					min: 100,
					max: 130
				}
			}]
	    	
		    $http({
        		   url: "/getStations",
        		   method: "POST"
        	   }).then(function(res) {
        		  $scope.stations = res.data.stations; 
        	   });
		    
		    $scope.path = location.href.substring(location.href.lastIndexOf("/") + 1, location.href.length); 
		    $scope.setColor = function(station) {
				if ($scope.path == station) {
					return {backgroundColor: "#EFF5FB"};
				}
			}
		    
	    	$http({
				url: location.href,
				method: "POST"
			}).then(function(res) {
				/* Annual Mean of F2 layer */
				$scope.avgEsYear = res.data.avgEsYear;
				for (var i = 0; i < $scope.avgEsYear.length; i++) {
					$scope.annualLabels.push($scope.avgEsYear[i].year);
					$scope.annualFoEs.push($scope.avgEsYear[i].frequency);
					$scope.annualHpEs.push($scope.avgEsYear[i].height);
				}
				
				/* Monthly Mean of F2 layer */
				$scope.avgEsMonth = res.data.avgEsMonth;
				for (var i = 0; i < $scope.avgEsMonth.length; i++) {
					$scope.monthlyLabels.push($scope.avgEsMonth[i].month);
					$scope.monthlyFoEs.push($scope.avgEsMonth[i].frequency);
					$scope.monthlyHpEs.push($scope.avgEsMonth[i].height);
				}
				
				/* Hourly Mean of F2 layer */
				$scope.avgEsHour = res.data.avgEsHour;
				for (var i = 0; i < $scope.avgEsHour.length; i++) {
					$scope.hourlyLabels.push($scope.avgEsHour[i].hh);
					$scope.hourlyFoEs.push($scope.avgEsHour[i].frequency);
					$scope.hourlyHpEs.push($scope.avgEsHour[i].height);
				}
				
				$scope.plot();
			})
			
			$scope.plot = function() {
					var plot_1 = new Chart(document.getElementById("annualMeanEs").getContext('2d'), {
						type: "line",
						data: {
							labels: $scope.annualLabels,
							datasets: [{
								label: "foEs",
								yAxisID: "left",
								backgroundColor: "rgba(0, 0, 0, 0)",
								borderColor: "rgb(255, 99, 132)",
								data: $scope.annualFoEs
							}, {
								label: "h'Es",
								yAxisID: "right",
								backgroundColor: "rgba(0, 0, 0, 0)",
								borderColor: "rgb(0, 102, 255)",
								data: $scope.annualHpEs
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
								yAxes: $scope.yAxes
							}
						}
					})
					
					var plot_2 = new Chart(document.getElementById("monthlyMeanEs").getContext('2d'), {
						type: 'line',
						data: {
							labels: $scope.monthlyLabels,
							datasets: [{
								label: "foEs",
								yAxisID: "left",
								backgroundColor: "rgba(0, 0, 0, 0)",
								borderColor: 'rgb(255, 99, 132)',
								data: $scope.monthlyFoEs
							}, {
								label: "h'Es",
								yAxisID: "right",
								backgroundColor: "rgba(0, 0, 0, 0)",
								borderColor: "rgb(0, 102, 255)",
								data: $scope.monthlyHpEs
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
								yAxes: $scope.yAxes
							}
						}
					})
					
					var plot_3 = new Chart(document.getElementById("hourlyMeanEs").getContext('2d'), {
						type: 'line',
						data: {
							labels: $scope.hourlyLabels,
							datasets: [{
								label: "foEs",
								yAxisID: "left",
								backgroundColor: 'rgba(0, 0, 0, 0)',
								borderColor: 'rgb(255, 99, 132)',
								data: $scope.hourlyFoEs
							}, {
								label: "h'Es",
								yAxisID: "right",
								backgroundColor: "rgba(0, 0, 0, 0)",
								borderColor: "rgb(0, 102, 255)",
								data: $scope.hourlyHpEs
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
								yAxes: $scope.yAxes
							}
						}
					})
			}
			
			$scope.detail = function(path) {
				location.href = location.href + "/" + path;
			}
			
			
   })