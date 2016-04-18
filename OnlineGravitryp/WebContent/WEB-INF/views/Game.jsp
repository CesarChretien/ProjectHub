<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="" %>
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

</head>

<body>

<% int i = 5; %>
<p>Java getal: <%= i %></p>

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
	
	window.onload = function() {
	    var img = document.getElementById("ship");
		ctx.drawImage(img, 10, 400);
		//websockets
	};
	
</script>

</body>
</html>