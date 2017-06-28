<%@ page language="java" contentType="text/html; charset=UTF-8" 
    pageEncoding="UTF-8"%> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>pathdump</title>
<base href="http://localhost:8080/pathdump/">  

</head>
<body>
	<form action="RegisterServlet">
		<input type="hidden" id="filename" name="filename" value="topk_query.py&topk_query_agg.py">
		<input type="submit" value="registerTopk">
	</form>
	<br>
	<form action="ExecuteServlet">
		Number of query:<input type="text" name="num_of_query">
		<input type="submit" value="executeTopk">
	</form>
	
</body>
</html>