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
    <link rel="stylesheet" href="/resources/css/plots.css">
    <link rel="stylesheet" href="/resources/css/detail.css">
    <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.9/angular.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.8.0/Chart.bundle.js"></script>
    <script src="/resources/js/detail.js"></script>
</head>
<body data-ng-app="ngApp", data-ng-controller="ngCtrl">
    <header><h1>SSLAB</h1></header>
    
    <nav>
    	<ul>
            <a href="/"><li>Introduction</li></a>
            <a href="/data"><li>Data</li></a>
            <div data-ng-repeat="station in stations">
            	<a href="/{{station}}"><li data-ng-style="setColor(station)">{{station}}</li></a>
            </div>
        </ul>
    </nav>
    
    <div class="container">
    	<div class="inner-container-1">
    		<button type="button" class="button" data-ng-click="back()">Back</button><br>
    	
    		<div class="plot">
	    		<canvas id="foEs"></canvas>
	    	</div>
	    	
	    	<div class="plot">
	    		<canvas id="hpEs"></canvas>
	    	</div>
    	
	   		<div class="inner-container-2" >
	   			<div data-ng-repeat="label in list" style="margin: auto;">
	   				<input type="checkbox" data-ng-click="detail(label)" style="margin: 5px">{{label}}
	   			</div>
	   		</div>
   		
   			<div style="text-align: justify; padding: 0 50px;">
	    		<p data-ng-show="sv">	
	    			&emsp;&emsp;The magnitude of foEs have the peak values in summer season in consistent with their known seasonal pattern in midlatitudes (Haldoupis 2012). 
	    			This behavior is related to the wind shear and meteor influx in summer (Haldoupis et al. 2007; Arras et al. 2008; Chu et al. 2014; Yeh et al. 2014). 
	    			However, the virtual height of Es (h’Es) shows the semiannual variation with two peaks in equinoxes. 
	    			Meteor radar measurements have reported that peak altitudes of meteors vary in a similar manner around the mesopause altitude (Lee et al. 2016). 
					The meteor peak variation is interpreted as a consequence of seasonal geopotential height change. 
					The seasonal variation of h’Es may be related to the meteor peak height variation since the atmospheric change around the mesopause will affect the background atmosphere at the Es layer. 
					Further investigation is needed to test the relation between variation of h’Es and meteor peak heights. 
	    		</p>
	    		
	    		<p data-ng-show="utv">	
	    			&emsp;&emsp;The local time variations of the h’Es shows the semidiurnal modulation during equinox months and summer months, 
	    			which is consistent with the results in previous studies (e.g. Haldoupis et al. 2006; Oikonomou et al. 2014). 
	    			However, the semi-diurnal variation is not obvious in winter. 
	    			Our calculation of the vertical ion velocity using HWM14, IGRF12, and NRLMSISE-00 models support the association of the semidiurnal variation 
	    			of h’Es with the semidiurnal variation of tidal horizontal wind.
	    		</p>
    		</div>
   		</div>
    </div>
</body>
</html>