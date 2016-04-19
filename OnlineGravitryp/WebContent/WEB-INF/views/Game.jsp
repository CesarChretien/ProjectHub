<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Dis gon b gud.</title>

<style>
#ship {
	display:none;
}
</style>
<script src="resources/Point.js"></script>
<script src="resources/Sprite.js"></script>
<script src="resources/Planet.js"></script>
<script src="resources/Ship.js"></script>

<script src="https://code.jquery.com/jquery-2.1.4.min.js"></script>
<script>

var test = ${ship.getHitbox()[0].getX()};

</script>

</head>

<body>

<img id="ship" width="200" height="200" src="<c:url value="/resources/SpaceShipStill.png"/>">
<canvas id="myCanvas" width="800" height="600"
style="border:1px solid #000000;">
</canvas>

<script>

	

	var canvas = document.getElementById("myCanvas");
	var ctx = canvas.getContext("2d");
	var grd=ctx.createRadialGradient(265,240,2,280,240,100);
	grd.addColorStop(0,"red");
	grd.addColorStop(1,"white");
	ctx.fillStyle = grd;
	ctx.fillRect(200,200,150,80);
	
	ctx.moveTo(100,100);
	ctx.lineTo(300,200);
	ctx.stroke();
	
	ctx.beginPath();
	ctx.arc(95,50,40,0,2*Math.PI);
	ctx.stroke();
	
	ctx.font = "30px Arial";
	ctx.fillStyle = "blue";
	ctx.fillText("Hello World",200,50);
	ctx.fillText(test,500,50);
	
	window.onload = function() {
	    var img = document.getElementById("ship");
		ctx.drawImage(img, 10, 400);
		//websockets
	};
	
</script>

</body>
</html>