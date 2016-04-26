<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %> 
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
<sec:csrfMetaTags />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Dis gon b gud.</title>

<script src="resources/Point.js"></script>
<script src="resources/Sprite.js"></script>
<script src="resources/Collision.js"></script>
<script src="resources/Planet.js"></script>
<script src="resources/Ship.js"></script>

<script src="https://code.jquery.com/jquery-2.1.4.min.js"></script>
<script>

//configureer JQuery om csrf-token mee te sturen
var csrfParameter = $("meta[name='_csrf_parameter']").attr("content");
var csrfHeader = $("meta[name='_csrf_header']").attr("content");
var csrfToken = $("meta[name='_csrf']").attr("content");
$(function () {
	var token = $("meta[name='_csrf']").attr("content");
	var header = $("meta[name='_csrf_header']").attr("content");
	$(document).ajaxSend(function(e, xhr, options) {
		xhr.setRequestHeader(header, token);
	});
}); 

var left = false;
var right = false;
var up = false;
var down = false;
var pause = false;
var gameover = false;
var collision = false;
var score = 0;
var bgnumber = 0;
var bg = "resources/Background";
var cordir = ["left", "right", "up", "down"];

var imgArray = new Array();
imgArray[0] = new Image();
imgArray[0].src = bg + (bgnumber++) + ".jpg";
imgArray[1] = new Image();
imgArray[1].src = bg + (bgnumber++) + ".jpg";
imgArray[2] = new Image();
imgArray[2].src = bg + (bgnumber++) + ".jpg";
imgArray[3] = new Image();
imgArray[3].src = bg + (bgnumber) + ".jpg";

var imgShipLeft = new Image();
imgShipLeft.src = "resources/rotateL.png";
var imgShipRight = new Image();
imgShipRight.src = "resources/rotateR.png";
var imgShipDefault = new Image();
imgShipDefault.src = "resources/default.png";

this.drawship = function(ship) {
	var h = ship.sprite.hitbox;
	this.beginPath();
	for(var i = 0; i < h.length; i++) {
		this.moveTo(h[i].x, h[i].y);
		this.lineTo(h[(i+1)%h.length].x, h[(i+1)%h.length].y);
		this.stroke();
	}
	this.strokeStyle = "white";
	this.closePath();
}

this.drawplanet = function(planet) {
	this.beginPath();
	this.arc(planet.sprite.centre.x, planet.sprite.centre.y, planet.radius, 0, 2*Math.PI);
	this.stroke();
	this.strokeStyle = "white";
	this.closePath();
}

$(document).keydown( function(event) {
	if(event.which == 32) {
		pause = !pause;
	}
	if(event.which == 37) {
		left = true;
	}
	if(event.which == 39) {
		right = true;
	}
	if(event.which == 40) {
		down = true;
	}
	if(event.which == 38) {
		up = true;
	}
	if(event.which == 81) {
		gameover = true;
	}
});

$(document).keyup( function(event) {
	if(event.which == 37) {
		left = false;
	}
	if(event.which == 39) {
		right = false;
	}
	if(event.which == 40) {
		down = false;
	}
	if(event.which == 38) {
		up = false;
	}
});
</script>

<style>

#endgame {
	display: none;
}

</style>

</head>

<body>

<canvas id="myCanvas" width=1280 height=720 style="border:1px solid #000000;"></canvas>
<form:form id="endgame" action="/OnlineGravitryp/Highscores" method="post">
<input type="text" name="score">
<input type="submit">
</form:form>

<script>
	var canvas = document.getElementById("myCanvas");
	$('#myCanvas').css("background-image", "url(" + imgArray[bgnumber].src + ")");
	var ctx = canvas.getContext("2d");
	var ship = new Ship(new Point(400, 400), "triangle", 43, 39, new Point(1, 0), new Point(0, 0));
	ship.col = hasCollisionWith;
	var earth = new Planet(new Point(600, 200), 50, 3);
	var mars = new Planet(new Point(1000, 600), 40, 2.5);
	var jupiter = new Planet(new Point(100, 700), 70, 3.5);
	var boom = true;
	var animD = 39;
	var animLR = 30;
	var dAngle = 0;
	
	ctx.dp = drawplanet;
	ctx.dp(earth);
	ctx.dp(mars);
	ctx.dp(jupiter);
	
	ctx.ds = drawship;
	ctx.ds(ship);	
	
	ctx.fillStyle = "white";
	function updateGame() {
		ctx.clearRect(0, 0, 1280, 720);
		
		//checks for collision
		collision = ship.col([earth, mars, jupiter]);
		
		if(collision) {
			$.get("/OnlineGravitryp/Highscores/Check", boom, function() {
				//magic.
			});
			$('input[name=score]').val(score);
			$('#endgame').submit();
			return;
		}
		
		//if unpaused game will flow
		if(!pause) {
			if(left) {
				ship.rotateLeft();
			}
			if(right) {
				ship.rotateRight();
			}
			if(down) {
				ship.slow();
			}
			if(up) {
				ship.move();
			}
			ship.applyGravity([earth, mars, jupiter]);
			
			
			
			var direction = ship.update($('#myCanvas').width(), $('#myCanvas').height());
			
			if(up || down) {
				if(left) {
					ctx.translate(ship.sprite.centre.x, ship.sprite.centre.y);
					ctx.rotate((dAngle = (360 + dAngle - ship.rotspd) % 360)*(Math.PI/180.0));
					ctx.drawImage(imgShipLeft,0,animLR,43,30,-(ship.sprite.xwidth/2 + 7), -ship.sprite.ywidth/2,43,30);
					ctx.rotate(-dAngle*(Math.PI/180.0));
					ctx.translate(-(ship.sprite.centre.x), -(ship.sprite.centre.y));
					animD = animD % 78 + 39;
					animLR = animLR % 60 + 30;
				}
				else if(right) {
					ctx.translate(ship.sprite.centre.x, ship.sprite.centre.y);
					ctx.rotate((dAngle = (360 + dAngle + ship.rotspd) % 360)*(Math.PI/180.0));
					ctx.drawImage(imgShipRight,0,animLR,43,30,-(ship.sprite.xwidth/2 + 7), -ship.sprite.ywidth/2,43,30);
					ctx.rotate(-dAngle*(Math.PI/180.0));
					ctx.translate(-(ship.sprite.centre.x), -(ship.sprite.centre.y));
					animD = animD % 78 + 39;
					animLR = animLR % 60 + 30;
				}
				else {
					ctx.translate(ship.sprite.centre.x, ship.sprite.centre.y);
					ctx.rotate(dAngle*(Math.PI/180.0));
					ctx.drawImage(imgShipDefault,0,animD,43,39,-(ship.sprite.xwidth/2 + 7), -ship.sprite.ywidth/2,43,39);
					ctx.rotate(-dAngle*(Math.PI/180.0));
					ctx.translate(-(ship.sprite.centre.x), -(ship.sprite.centre.y));
					animD = animD % 78 + 39;
					animLR = animLR % 60 + 30;
				}
			}
			else if (left) {
				ctx.translate(ship.sprite.centre.x, ship.sprite.centre.y);
				ctx.rotate((dAngle = (360 + dAngle - ship.rotspd) % 360)*(Math.PI/180.0));
				ctx.drawImage(imgShipLeft,0,0,43,30,-(ship.sprite.xwidth/2 + 7), -ship.sprite.ywidth/2,43,30);
				ctx.rotate(-dAngle*(Math.PI/180.0));
				ctx.translate(-(ship.sprite.centre.x), -(ship.sprite.centre.y));
				animD = animD % 78 + 39;
				animLR = animLR % 60 + 30;
			}
			else if (right) {
				ctx.translate(ship.sprite.centre.x, ship.sprite.centre.y);
				ctx.rotate((dAngle = (360 + dAngle + ship.rotspd) % 360)*(Math.PI/180.0));
				ctx.drawImage(imgShipRight,0,0,43,30,-(ship.sprite.xwidth/2 + 7), -ship.sprite.ywidth/2,43,30);
				ctx.rotate(-dAngle*(Math.PI/180.0));
				ctx.translate(-(ship.sprite.centre.x), -(ship.sprite.centre.y));
				animD = animD % 78 + 39;
				animLR = animLR % 60 + 30;
			}
			else {
				ctx.translate(ship.sprite.centre.x, ship.sprite.centre.y);
				ctx.rotate(dAngle*(Math.PI/180.0));
				ctx.drawImage(imgShipDefault,0,0,43,39,-(ship.sprite.xwidth/2 + 7), -ship.sprite.ywidth/2,43,39);
				ctx.rotate(-dAngle*(Math.PI/180.0));
				ctx.translate(-(ship.sprite.centre.x), -(ship.sprite.centre.y));
				animD = animD % 78 + 39;
				animLR = animLR % 60 + 30;
			}
			
			var rightway = cordir[Math.floor(Math.random() * 4)];
			
			if(!(direction === "stay")) {
				
				if(direction === "left") {
					earth.relocate(earth.radius + Math.random() * (1280 - 2*earth.radius - 100), earth.radius + Math.random() * (720 - 2*earth.radius));
					mars.relocate(mars.radius + Math.random() * (1280 - 2*mars.radius - 100), mars.radius + Math.random() * (720 - 2*mars.radius));
					jupiter.relocate(jupiter.radius + Math.random() * (1280 - 2*jupiter.radius - 100), jupiter.radius + Math.random() * (720 - 2*jupiter.radius));
				}
				
				if(direction === "right") {
					earth.relocate(earth.radius + Math.random() * (1280 - 2*earth.radius + 100), earth.radius + Math.random() * (720 - 2*earth.radius));
					mars.relocate(mars.radius + Math.random() * (1280 - 2*mars.radius + 100), mars.radius + Math.random() * (720 - 2*mars.radius));
					jupiter.relocate(jupiter.radius + Math.random() * (1280 - 2*jupiter.radius + 100), jupiter.radius + Math.random() * (720 - 2*jupiter.radius));
				}
				
				if(direction === "up") {
					earth.relocate(earth.radius + Math.random() * (1280 - 2*earth.radius), earth.radius + Math.random() * (720 - 2*earth.radius - 70));
					mars.relocate(mars.radius + Math.random() * (1280 - 2*mars.radius), mars.radius + Math.random() * (720 - 2*mars.radius - 70));
					jupiter.relocate(jupiter.radius + Math.random() * (1280 - 2*jupiter.radius), jupiter.radius + Math.random() * (720 - 2*jupiter.radius - 70));
				}
				
				if(direction === "down") {
					earth.relocate(earth.radius + Math.random() * (1280 - 2*earth.radius), earth.radius + Math.random() * (720 - 2*earth.radius + 70));
					mars.relocate(mars.radius + Math.random() * (1280 - 2*mars.radius), mars.radius + Math.random() * (720 - 2*mars.radius + 70));
					jupiter.relocate(jupiter.radius + Math.random() * (1280 - 2*jupiter.radius), jupiter.radius + Math.random() * (720 - 2*jupiter.radius + 70));
				}
				
				bgnumber = ++bgnumber % 4;
				$('#myCanvas').css("background-image", "url(" + imgArray[bgnumber].src + ")");
				
				if(direction === rightway) {
					score += 100;
				}
				else {
					score = Math.round(score/2);
				}
			}
		}
		
		ctx.fillText("Score: " + score,100,100);
		ctx.dp(earth);
		ctx.dp(mars);
		ctx.dp(jupiter);
		ctx.ds(ship);
		
		window.requestAnimationFrame(updateGame);
	}
	updateGame();
	
	window.onload = function() {
		$.get("/OnlineGravitryp/Game", function(){
			//magic happens
		});
	}
</script>

</body>
</html>