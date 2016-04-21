<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Highscore pagina!</title>
</head>
<body>
Je hebt ${highscore.score} punten gehaald.<br>
Lijst van highscores: <br>

<ul>
	<c:forEach var="listValue" items="${list}">
		<li>${listValue}</li>
	</c:forEach>
</ul>
</body>
</html>