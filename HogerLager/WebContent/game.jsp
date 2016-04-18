<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Hoger/Lager</title>
<%-- c:url fixt onze link --%>
<link rel="stylesheet" type="text/css" href="<c:url value="/style.css"/>">
</head>
<body>

	<%-- <p> 
		Tries: ${gamestate.tries}--%> <%-- Session Scope --%>
	<%-- </p> --%>

	<%-- c:if en Request Scope --%>
	<%--<c:if test="${!empty error}">
		<p class="error">${error}</p>
	</c:if> --%>

	<p>Hint: <span id="hint"></span></p>

	<form method="post">
		<label for="guess">Guess: </label><input type="text" name="guess" id="guess">
		<input type="button" value="guess!" id="guess_btn">
	</form>

	<script src="https://code.jquery.com/jquery-2.1.4.min.js"></script>
	<script>
	
		var root_url = '<c:url value="/" />';
		
		function tryGuess() {
			var guess = $('#guess').val();
			var url = root_url + "try";
			$.get(url, {guess: guess}, function(data) {
				$('#hint').text(data).show();
			});
		}
		
		$(document).ready(function(){
			$('#guess_btn').click(tryGuess);
		});
	
	</script>
	
</body>
</html>