angular.module("ngApp", [])
       .controller("ngCtrl", function($scope, $http) {
    	   
	   $http({
   		   url: "/getStations",
   		   method: "POST"
   	   }).then(function(res) {
   		  $scope.stations = res.data.stations; 
   	   });
	   
	   $http({
   		   url: "/getUpdateInfo",
   		   method: "POST"
	   }).then(function(res) {
		   $scope.updateInfo = res.data.result;
	   })
	   
	   $scope.updateList = [];
   	   $scope.check = function(info) {
   		   if (info.check) {
   			   $scope.updateList.push(info.station);
   		   } else {
   			   $scope.updateList.splice($scope.updateList.indexOf(info.station), 1);
   		   }
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
   	   }
   	   
   	   $scope.update = function() {
		   $http({
			   url: "/updateData",
			   method: "POST",
			   params: {
				   stations: $scope.updateList
			   }
		   })
	   }
   	   
   	   
   	   $scope.search = function() {
   		   $http({
   			   url: "/getUpdateInfo",
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
   			   url: "/updateStation",
   			   method: "POST",
   			   params: {
   				   stationName: $scope.station
   			   }
   		   }).then(function(res) {
			   alert(res.data.result);
   		   })
   	   }
   	   
})