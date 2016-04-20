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
var score = 0;

this.drawship = function(ship) {
	var h = ship.sprite.hitbox;
	this.beginPath();
	for(var i = 0; i < h.length; i++) {
		this.moveTo(h[i].x, h[i].y);
		this.lineTo(h[(i+1)%h.length].x, h[(i+1)%h.length].y);
		this.stroke();
	}
	this.closePath();
}

this.drawplanet = function(planet) {
	this.beginPath();
	this.arc(planet.sprite.centre.x, planet.sprite.centre.y, planet.radius, 0, 2*Math.PI);
	this.stroke();
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

</head>

<body>

<canvas id="myCanvas" width=1280 height=720 style="border:1px solid #000000;"></canvas>
<form:form id="endgame" action="/OnlineGravitryp/Highscores" method="post">
Name: <br>
<input type="text" id="name"> <br>
Highscore: <br>
<input type="text" id="highscore">
</form:form>

<script>
	var canvas = document.getElementById("myCanvas");
	var ctx = canvas.getContext("2d");
	var ship = new Ship(new Point(400, 400), "triangle", 64, 32, new Point(1, 0), new Point(0, 0));
	var planet = new Planet(new Point(600, 200), 50, 3);
	
	ctx.dp = drawplanet;
	ctx.dp(planet);
	
	ctx.ds = drawship;
	ctx.ds(ship);	
	
	function updateGame() {
		ctx.clearRect(0, 0, 1280, 720);
		if(gameover) {
			//$.post("/OnlineGravitryp/Highscores", {}, function(){});
			//return;
		}
		
		if(pause) {
			$('#highscore').val(score);
			$('#endgame').submit();
		}
		
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
			score++;
			ship.applyGravity([planet]);
			ship.update($('#myCanvas').width(), $('#myCanvas').height());
		}
		
		ctx.fillText("Score: " + score,100,100);
		ctx.dp(planet);
		ctx.ds(ship);
		
		window.requestAnimationFrame(updateGame);
	}
	
	updateGame();
</script>

</body>
</html>