<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Basketball Store</title>
<script src="javascript/slide.js"></script>
<link type="text/css" rel="stylesheet" href="css/main.css">


</head>
<style>
.mySlides {display:none;}
</style>


<body onload="slideShow();">


	<%@include file="/Header.jsp" %>
	<%@include file="/nav.jsp" %>
	

	<h1>BasketBall Store: Where amazing happens</h1>
	
	<div class="slideshow">

			<img src="img/banner.png" class="mySlides" name="mySlides"
				style="height: 400px; width: 100%;"> <img
				src="img/banner1.png" class="mySlides" name="mySlides"
				style="height: 400px; width: 100%;"> <img
				src="img/banner2.png" class="mySlides" name="mySlides"
				style="height: 400px; width: 100%;"> <img
				src="img/banner3.png" class="mySlides" name="mySlides"
				style="height: 400px; width: 100%;">


		</div>
		
		
	

	<div id="media">

	<div class="video" align="right">
	<video muted="1" controls src="img/intro.mp4" autoplay="autoplay" ></video>
	</div>
	
	<div  align="center" class="news">
		<h3>Tieniti aggiornato sul mondo della Pallacanestro:</h3>
		
		<a href="http://sport.sky.it/nba/home.html?gr=www">SkyNBA</a><br>
		<a href="http://www.gazzetta.it/Nba/">GazzettaNBA</a><br>
		<a href="http://www.legapallacanestro.com/">LegaItalianaPallacanestro</a><br>
	</div>

	</div>
	
    
    <%@include file="/Footer.jsp" %>
    
</body>
</html>