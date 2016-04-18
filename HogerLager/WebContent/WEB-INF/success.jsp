<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>You Win</title>
</head>
<body>
	You found my secret number <strong>${gamestate.secret}</strong> in ${gamestate.tries} steps.

	Want to <a href="<c:url value="/index.html" />">try again</a>?
</body>
</html>