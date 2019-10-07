angular.module("ngApp", [])
               .controller("ngCtrl", function($scope, $http) {
            	   
        	   $http({
           		   url: "/getStations",
           		   method: "POST"
           	   }).then(function(res) {
           		  $scope.stations = res.data.stations; 
           	   });
        	   
        	   $http({
           		   url: "/updateInfo",
           		   method: "POST"
   			   }).then(function(res) {
   				   $scope.updateList = res.data.result;
			   });
        	   
           	   $scope.update = function() {
   				   $http({
   					   url: "/setData",
   					   method: "POST"
   				   }).then(function() {
   					   console.log("setData()")
   				   })
   			   }
           	   
           	   $scope.test = function(row) {
           		   console.log(row);
           	   }
           	   
           	   $scope.search = function() {
           		   $http({
           			   url: "/updateInfo",
           			   method: "POST",
           			   params: {
           				   station: $scope.station
           			   }
           		   }).then(function(res) {
           			   $scope.updateList = res.data.result;
           		   });
           	   }
           	   
           	   $scope.add = function() {
           		   $http({
           			   url: "/addStation",
           			   method: "POST",
           			   params: {
           				   stationName: $scope.station
           			   }
           		   }).then(function(res) {
           			   alert(res.data.result);
           		   })
           	   }
})