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
<script src="resources/DrawShip.js"></script>
<script src="resources/Relocate.js"></script>

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

//actual game resolution
var canWidth = 1280;
var canHeight = 720;
//size of game screen
var scrWidth = Math.min(0.9*$(window).width(), canWidth);
var scrHeight = Math.min(0.9*$(window).height(), canHeight);

var left = false;
var right = false;
var up = false;
var down = false;
var pause = false;
var gameover = false;
var collision = false;
var boom = true;
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

this.drawplanets = function(planets) {
	for(var i = 0; i < planets.length; i++) {
		this.beginPath();
		this.arc(planets[i].sprite.centre.x, planets[i].sprite.centre.y, planets[i].radius, 0, 2*Math.PI);
		this.stroke();
		this.strokeStyle = "white";
		this.closePath();
	}
}

var ship = new Ship(new Point(400, 400), "triangle", 43, 39, new Point(1, 0), new Point(0, 0));
ship.col = hasCollisionWith;

var earth = new Planet(new Point(600, 200), 50, 3);
var mars = new Planet(new Point(1000, 600), 40, 2.5);
var jupiter = new Planet(new Point(100, 700), 70, 3.5);
var planets = [earth, mars, jupiter];
planets.relocate = Relocate;

var anim = {
		D: 39,
		LR: 30,
		ang: 0,		
	};

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

<canvas id="myCanvas" style="border:1px solid #000000;"></canvas>
<form:form id="endgame" action="/OnlineGravitryp/Highscores" method="post">
<input type="text" name="score">
<input type="submit">
</form:form>

<script>
	var canvas = document.getElementById("myCanvas");
	$('#myCanvas').attr({width:canWidth, height:canHeight}).css({width:scrWidth,height:scrHeight});
	$('#myCanvas').css("background-image", "url(" + imgArray[bgnumber].src + ")");
	var ctx = canvas.getContext("2d");

	ctx.dp = drawplanets;
	ctx.dp(planets);
	
	ctx.ds = drawship;
	ctx.ds(ship);	
	
	ctx.drawShip = DrawShip;
	function updateGame() {
		//clears the canvas
		ctx.clearRect(0, 0, canWidth, canHeight);
		
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
			ship.applyGravity(planets);
			
			var direction = ship.update(canWidth, canHeight);

			anim = ctx.drawShip(ship, up, down, left, right, anim);
			
			var rightway = cordir[Math.floor(Math.random() * 4)];
			
			if(!(direction === "stay")) {
				planets.relocate(direction, canWidth, canHeight);
				
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
		else {
			ctx.drawShip(ship, false, false, false, false, anim);
		}
		
		ctx.fillText("Score: " + score,100,100);
		ctx.dp(planets);
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