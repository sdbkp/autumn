angular.module("ngApp", [])
       .controller("ngCtrl", function($scope, $http) {
    	$scope.urls = location.href.split("/");
    	$scope.location = $scope.urls[3];
    	$scope.path = $scope.urls[4];
    	$scope.labels = []; 	$scope.foEs = [];  $scope.hpEs = []; $scope.list = [];
    	$scope.xLabelString = "";
    	var config_1; var config_2; var plot_1; var plot_2;
    	
    	if ($scope.path == "monthlyMeanEs") {
    		$scope.sv = true;
    		$scope.utv = false;
    	} else {
    		$scope.sv = false;
    		$scope.utv = true;
    	}
    	
    	$http({
    		url: "/getStations",
    		method: "POST"
		}).then(function(res) {
			$scope.stations = res.data.stations;
		});
    	
    	$scope.setColor = function(station) {
			if ($scope.location == station) {
				return {backgroundColor: "#EFF5FB"};
			}
		}
    	
    	$http({
			url: location.href,
			method: "POST"
		}).then(function(res) {
			$scope.data = res.data.result;
			$scope.detailData = res.data.detailResult;
			
			for (var i = 0; i < $scope.data.length; i++) {
				$scope.foEs.push($scope.data[i].frequency);
				$scope.hpEs.push($scope.data[i].height);
    			}
			
			
			switch ($scope.path) {
			case "annualMeanEs":
				for (var i = 0; i < $scope.data.length; i++) {
					$scope.labels.push($scope.data[i].year);
				}
				$scope.xLabelString = "Year";
				break;
				
			case "monthlyMeanEs":
				for (var i = 0; i < $scope.data.length; i++) {
					$scope.labels.push($scope.data[i].month);
				}
				$scope.xLabelString = "Month";
				
            			for (var i = 0; i < $scope.detailData.length; i++) {
            				if (!$scope.list.includes($scope.detailData[i].year)) {
            				$scope.list.push($scope.detailData[i].year);
            				}	
            			}
				break;
				
			case "hourlyMeanEs":
				for (var i = 0; i < $scope.data.length; i++) {
					$scope.labels.push($scope.data[i].hh);
				}
				$scope.xLabelString = "UT";
				
				for (var i = 0; i < $scope.detailData.length; i++) {
					if (!$scope.list.includes($scope.detailData[i].month)) {
						$scope.list.push($scope.detailData[i].month);
					}	
            			}
				break;
			}
			
			$scope.plot();
		})
		
		$scope.plot = function() {
 			config_1 = {
        		type: "line",
        		data: {
        			labels: $scope.labels,
        			datasets: [{
        				label: "foEs",
        				backgroundColor: "rgba(0, 0, 0, 0)",
						borderColor: "rgb(255, 99, 132)",
						data: $scope.foEs
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
								labelString: $scope.xLabelString
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
        					},
        					ticks: {
        						min: 2,
        						max: 7
        					}
        				}]
        			}
        		}
        	}
			 
			config_2 = {
        		type: "line",
        		data: {
        			labels: $scope.labels,
        			datasets: [{
        				label: "foEs",
        				backgroundColor: "rgba(0, 0, 0, 0)",
						borderColor: "rgb(0, 102, 255)",
						data: $scope.hpEs
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
        						labelString: $scope.xLabelString
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
        					},
        					ticks: {
        						min: 100,
        						max: 130
        					}
        				}]
        			}
        		}
        	}
 			
 			plot_1 = new Chart(document.getElementById("foEs").getContext('2d'), config_1);
			plot_2 = new Chart(document.getElementById("hpEs").getContext('2d'), config_2)
    	}
    	
    	$scope.detail = function(label) {
    		var newPlot = true;
    		var index;
    		
    		for (var i = 0; i < config_1.data.datasets.length; i++) {
    			if (config_1.data.datasets[i].label == label + " foEs") {
    				newPlot = false;
    				index = i;
    			} 
    		}
    		
    		if (newPlot) {
    			var c1 = Math.floor(Math.random() * 256);
        		var c2 = Math.floor(Math.random() * 256);
        		var c3 = Math.floor(Math.random() * 256);
        		
        		$scope.newFoEs = {
       				label: label + " foEs",
       				backgroundColor: "rgba(0, 0, 0, 0)",
       				borderColor: "rgb(" + c1 + "," + c2 + "," + c3 + ")",
       				data: []
        		}
        		
        		$scope.newHpEs = {
        			label: label + " hpEs",
        			backgroundColor: "rgba(0, 0, 0, 0)",
        			borderColor: "rgb(" + c1 + "," + c2 + "," + c3 + ")",
        			data: []
        		}
        		
        		if ($scope.path == "monthlyMeanEs") {
        			for (var i = 0; i < $scope.detailData.length; i++) {
            			if ($scope.detailData[i].year == label) {
            				$scope.newFoEs.data.push($scope.detailData[i].frequency);
            				$scope.newHpEs.data.push($scope.detailData[i].height);
            			}
            		}
        			console.log($scope.newFoEs.data);
        		} else if ($scope.path == "hourlyMeanEs") {
        			console.log("Bb");
        			for (var i = 0; i < $scope.detailData.length; i++) {
            			if ($scope.detailData[i].month == label) {
            				$scope.newFoEs.data.push($scope.detailData[i].frequency);
            				$scope.newHpEs.data.push($scope.detailData[i].height);
            			}
            		}
        		}
        		
        		config_1.data.datasets.push($scope.newFoEs);
        		config_2.data.datasets.push($scope.newHpEs);
        		
        		$scope.button = {
        				"background-color": "aquamarine"
        		}

    		}else {
        		config_1.data.datasets.splice(index, 1);
        		config_2.data.datasets.splice(index, 1);
        	}
    		
    		plot_1.update();
        	plot_2.update();
    		
    	} 
    	
    	
    	$scope.back = function() {
    		history.back();
    	}
    	
})
