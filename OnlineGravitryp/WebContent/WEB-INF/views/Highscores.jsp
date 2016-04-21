<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %> 
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
<sec:csrfMetaTags />
<title>Highscore pagina!</title>

<script src="https://code.jquery.com/jquery-2.1.4.min.js"></script>
<script type="text/javascript" language="javascript">

// configureer JQuery om csrf-token mee te sturen
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

function add_score_to_list(highscore){
	var element = $('<li>Naam: ' + highscore.name + '! heeft ' + highscore.score + ' punten gescoord!' +
					'</li>');
	$('ul').append(element);
}
	
function get_scores(){
	$.get("/OnlineGravitryp/Highscores/", function(data){
		for(var i=0; i < data.length; ++i){
			add_score_to_list(data[i]);
		}
	});
}

function add() {
	var highscore = {
		name: $('#name').val(),
		//let niet op de error.
		score: ${score},
	};
	$.post("/OnlineGravitryp/Highscores/Verstuur", highscore, function(data){
		get_scores();
		$('#lijst').show();
	});
}

function add_score_to_list(highscore){
	var element = $('<li>Naam: ' + highscore.name + ' heeft ' + highscore.score + ' punten gescoord!' +
					'</li>');
	$('ul').append(element);
}
	
function get_scores(){
	$.get("/OnlineGravitryp/Highscores/", function(data){
		for(var i=0; i < data.length; ++i){
			add_score_to_list(data[i]);
		}
	});
}

$(document).ready(function() {
	$('input[type=submit]').click(add);
})	
</script>
		
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</head>
<body>
Je hebt ${score} punten gehaald.<br>

Voer hier je naam in: <br>
<input type="text" id="name">
<input type="submit" value="Verstuur">
<div id="lijst" hidden=true>Toegevoegd! Lijst van highscores: <br>
<ul></ul></div>

<!-- <ul>
	<c:forEach var="listValue" items="${list}">
		<li>${listValue}</li>
	</c:forEach> 
</ul> -->
</body>
</html>