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
    <link rel="stylesheet" href="/resources/css/home.css">
    <script src="https://use.fontawesome.com/releases/v5.2.0/js/all.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.9/angular.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.9/angular-animate.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.8.0/Chart.bundle.js"></script>
    <script src="https://code.jquery.com/jquery-1.9.1.min.js"></script>
    <!-- <script src="https://cdn.jsdelivr.net/npm/chart.js@2.8.0"></script> -->
    <script>
        angular.module("ngApp", [])
               .controller("ngCtrl", function($scope, $http) {
               
           	   $http({
           		   url: "/getStations",
           		   method: "POST"
           	   }).then(function(res) {
           		  $scope.stations = res.data.stations; 
           	   });
           	   
        })
    </script>
</head>
<body data-ng-app="ngApp", data-ng-controller="ngCtrl">
    <header><h1>SSLAB</h1></header>
    
    <nav>
    	<ul>
            <li style="background-color: #EFF5FB;">Introduction</li>
            <a href="/data"><li>Data</li></a>
            <div data-ng-repeat="station in stations">
            	<a href="/{{station}}"><li>{{station}}</li></a>
            </div>
        </ul>
    </nav>
    
    <div class="container">
    	<div class="inner-container-1">
    		<img src="/resources/img/upperAtmosphere.jpg">
    		<p>Typical profiles of neutral atmospheric temperature and ionospheric plasma density with the various layers [Kelley 2009].</p>
    	</div>
    	<div class="inner-container-2" style="text-align: justify;">
    		<p>	&emsp;&emsp;The Earth’s atmosphere is divided into the troposphere (0 – 12 km), stratosphere (12 – 50 km), 
    			mesosphere (50 – 90 km), thermosphere (90 – 500 km), and exosphere according to the change of 
    			temperature with altitude as shown in Figure 2.1. In the troposphere, the temperature decreases 
    			away from the surface heated by solar radiation. The tropospheric temperature decreases as the 
    			altitude increases from the surface heated by solar radiation. On the other hand, in the stratosphere, 
    			the ozone layer absorbs the solar ultraviolet, so that the temperature of stratosphere increases 
    			with increase of altitude. As the ozone density decreases, the temperature decreases again above 
    			stratosphere, that is in the mesosphere. The atmospheric temperature increases again in the thermosphere 
    			as altitude increases because the atomic oxygen absorbs the solar extreme ultraviolet. Finally, 
    			in the exosphere, the temperature is constant due to extremely low atmospheric density.
    			</p>
    			
    		<p>	&emsp;&emsp;The ionosphere is classified according to atmospheric composition. In Figure 2.1, the ionosphere 
	    		is divided into D, E and F layers from the surface according to distribution of the electron density 
	    		and the F layer can be subdivided as F1 and F2 layers. The D region at an altitude of 60 – 90 km has 
	    		a relatively less ionization and lower electron density than the other layers, therefore it does not 
	    		have a significant effect on the propagation of the HF. However, when the solar flare occurs, the electron 
	    		density of the D layer increases, so that the Dellinger phenomenon can appear. The ionospheric E region 
	    		(altitude of 90 – 140 km) reflects radio waves that the frequency is lower than 10 MHz and absorbs radio 
	    		waves which the frequency is higher than 10 MHz. The E layer reflects the radio waves only during the 
	    		daytime because it disappears at night. The F region (140 – 1,000 km) is divided into F1 region (140 – 200 km) 
	    		and F2 region (200 – 1,000 km) during daytime and then the F1 layer disappears in the nighttime.
    		</p>
    		
    		<p>	&emsp;&emsp;The electron density of F2 layer is highest. The reason that the electron density of the ionosphere 
    			shows different characteristics depending on the altitude is because the solar radiation is selectively 
    			absorbed and the composition and density of the atmosphere also differ with altitude. Thus, the 4 
    			separated ionospheric regions represent not only the altitudinal difference but also that each layer 
    			is governed by a different physical process.
    		</p>
    	</div>
    	
    </div>
</body>
</html>