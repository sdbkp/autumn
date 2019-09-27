<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>home</title>
    <link rel="stylesheet" href="/resources/css/common.css">
    <script src="https://use.fontawesome.com/releases/v5.2.0/js/all.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.9/angular.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.9/angular-animate.js"></script>
    <script>
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
        })
    </script>
</head>
<body data-ng-app="ngApp", data-ng-controller="ngCtrl">
    <header><h1>Header</h1></header>
    
    <nav></nav>
    
    <div class="container">
        <button type="button" data-ng-click="update()">Update</button>
    </div>
</body>
</html>