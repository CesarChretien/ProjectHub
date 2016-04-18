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

	<p>
		Tries: ${gamestate.tries} <%-- Session Scope --%>
	</p>

	<%-- c:if en Request Scope --%>
	<c:if test="${!empty error}">
		<p class="error">${error}</p>
	</c:if>

	<p class="hint">
		${hint}
	</p>

	<form method="post">
		<label for="guess">Guess: </label><input type="text" name="guess">
		<input type="submit">
	</form>


</body>
</html>