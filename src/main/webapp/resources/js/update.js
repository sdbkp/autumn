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
   				   $scope.updateInfo = res.data.result;
			   });
        	   
        	   $scope.updateList = [];
           	   $scope.check = function(info) {
           		   if (info.check) {
           			   $scope.updateList.push(info.station);
           		   } else {
           			   $scope.updateList.splice($scope.updateList.indexOf(info.station), 1);
           		   }
           		console.log($scope.updateList);
           	   }
           	   
           	   $scope.checkAll = function() {
           		   if ($scope.check.all) {
           			   $scope.updateInfo.forEach(function(element) {
           				   if ($scope.updateList.indexOf(element.station) == - 1) {
           					   $scope.updateList.push(element.station);
           				   }
           			   });
           		   } else {
           			   $scope.updateList = [];
           		   }
           		   console.log($scope.updateList);
           	   }
           	   
           	   $scope.update = function() {
           		   console.log($scope.updateList);
   				   $http({
   					   url: "/setData",
   					   method: "POST",
   					   params: {
   						   stations: $scope.updateList
   					   }
   				   });
   			   }
           	   
           	   $scope.search = function() {
           		   $http({
           			   url: "/updateInfo",
           			   method: "POST",
           			   params: {
           				   station: $scope.station
           			   }
           		   }).then(function(res) {
           			   $scope.updateInfo = res.data.result;
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