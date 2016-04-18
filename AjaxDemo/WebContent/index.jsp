<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>AJAX Demo</title>
</head>
<body>
	<section>
		Getal 1: <input type="text" id="a"><br>
		Getal 2: <input type="text" id="b"><br>
		<input type="button" value="plus" id="plus_btn"><br>
		<p style="display:hidden">Antwoord: <span id="antwoord"></span></p>
	</section>

	<section>
		<h4>History</h4>
		<ul id="history"></ul>
		<input type="button" value="refresh" id="refresh_btn">
	</section>

	<script src="https://code.jquery.com/jquery-2.1.4.min.js"></script>
	<script>
		// mix JSTL en JavaScript :)
		var root_url = '<c:url value="/" />';
		
		// AJAX GET zonder request data
		function refreshAjax(){
			var url = root_url + "history";
			$.get(url, function(data){
				$('#history').empty();
				$.each(data, function(i,val){
					var li = $('<li>' + val + '</li>');
					$('#history').append(li);
				});
			});
		}
		
		function plusAjax(){
			var a = $('#a').val(),
				b = $('#b').val();
			var url = root_url + "plus";
			$.get(url, {a: a, b: b}, function(data){
				console.log(data);
				$('#antwoord').text(data).show();
			});
		}
		

		
		$(document).ready(function(){
			$('#plus_btn').click(plusAjax);
			$('#refresh_btn').click(refreshAjax);
		});
	</script>
</body>
</html>