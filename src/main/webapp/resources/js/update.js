angular.module("ngApp", [])
               .controller("ngCtrl", function($scope, $http) {
            
           	   $scope.update = function() {
   				   $http({
   					   url: "/setData",
   					   method: "POST"
   				   }).then(function() {
   					   console.log("setData()")
   				   })
   			   }
           	   
           	   $scope.updateInfo = function() {
           		   $http({
           			   url: "/setInfo",
           			   method: "POST"
           		   }).then(function(res) {
           			   $scope.updateList = res.data.result;
           		   })
           	   }
           	   $scope.updateInfo();
           	   
})