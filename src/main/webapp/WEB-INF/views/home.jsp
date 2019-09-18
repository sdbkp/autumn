<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>SilverStar</title>
<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.9/angular.min.js"></script>
<script>
	angular.module("ngApp", [])
		   .controller("ngCtrl", function($scope, $http) {
			   
			   $scope.submit = function() {
				   $http({
					   url: "/submit",
					   method: "POST"
				   }).then(function() {
					   console.log("submit()")
				   })
			   }
			   
		   })
</script>
</head>
<body data-ng-app="ngApp" data-ng-controller="ngCtrl">
	Hello :D
	<br>
	<button type="button" data-ng-click="submit()">Submit</button>
</body>
</html>