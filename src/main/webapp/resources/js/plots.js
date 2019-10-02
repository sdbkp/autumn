angular.module("ngApp", [])
	   .controller("ngCtrl", function($scope, $http) {
		   var annualLabels = []; 	var annualFoEs = [];  var annualHpEs = [];
	    	var monthlyLabels = []; var monthlyFoEs = []; var monthlyHpEs = [];
	    	var hourlyLabels = []; 	var hourlyFoEs = [];  var hourlyHpEs = [];
			$scope.plot = function() {
				$http({
					url: location.href,
					method: "POST"
				}).then(function(res) {
					/* Annual Mean of F2 layer */
					$scope.avgEsYear = res.data.avgEsYear;
					for (var i = 0; i < $scope.avgEsYear.length; i++) {
						annualLabels.push($scope.avgEsYear[i].year);
						annualFoEs.push($scope.avgEsYear[i].frequency);
						annualHpEs.push($scope.avgEsYear[i].height);
					}
					
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
					
					var plot_1 = new Chart(document.getElementById("annualMeanEs").getContext('2d'), {
						type: "line",
						data: {
							labels: annualLabels,
							datasets: [{
								label: "foEs",
								yAxisID: "left",
								backgroundColor: "rgba(0, 0, 0, 0)",
								borderColor: "rgb(255, 99, 132)",
								data: annualFoEs
							}, {
								label: "h'Es",
								yAxisID: "right",
								backgroundColor: "rgba(0, 0, 0, 0)",
								borderColor: "rgb(0, 102, 255)",
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
									id: "left",
									type: "linear",
									position: "left",
									gridLines: {
										display: false
									},
									scaleLabel: {
										display: true,
										labelString: "foEs (MHz)"
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
									}
								}]	
							}
						}
					})
					
					var plot_2 = new Chart(document.getElementById("monthlyMeanEs").getContext('2d'), {
						type: 'line',
						data: {
							labels: monthlyLabels,
							datasets: [{
								label: "foEs",
								yAxisID: "left",
								backgroundColor: "rgba(0, 0, 0, 0)",
								borderColor: 'rgb(255, 99, 132)',
								data: monthlyFoEs
							}, {
								label: "h'Es",
								yAxisID: "right",
								backgroundColor: "rgba(0, 0, 0, 0)",
								borderColor: "rgb(0, 102, 255)",
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
									id: "left",
									type: "linear",
									position: "left",
									gridLines: {
										display: false
									},
									scaleLabel: {
										display: true,
										labelString: "foEs (MHz)"
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
									}
								}]	
							}
						}
					})
					
					var plot_3 = new Chart(document.getElementById("hourlyMeanEs").getContext('2d'), {
						type: 'line',
						data: {
							labels: hourlyLabels,
							datasets: [{
								label: "foEs",
								yAxisID: "left",
								backgroundColor: 'rgba(0, 0, 0, 0)',
								borderColor: 'rgb(255, 99, 132)',
								data: hourlyFoEs
							}, {
								label: "h'Es",
								yAxisID: "right",
								backgroundColor: "rgba(0, 0, 0, 0)",
								borderColor: "rgb(0, 102, 255)",
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
									id: "left",
									type: "linear",
									position: "left",
									gridLines: {
										display: false
									},
									scaleLabel: {
										display: true,
										labelString: "foEs (MHz)"
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
									}
								}]	
							}
						}
					})
				})
			}
			$scope.plot();
			
			$scope.detail = function(path) {
				location.href = location.href + "/" + path;
			}
   })