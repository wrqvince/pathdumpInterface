<%@page import="net.sf.json.JSONArray"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Map.Entry"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.TreeMap"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>executeTopk</title>
<base href="http://localhost:8080/pathdump/">
<link href="static/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
		<h1>
			Result <small style="color: gray; font-size: medium;">paths violate a policy<br></small>
		</h1>
	<div class="panel panel-default">
		<!-- Table -->
		<table class="table table-hover" align="center" width="100%">
			<tr>
				<th>Query Name</th>
				<th>process</th>
				
			</tr>
			<tr>
				<th align="center">aaa.py</th>
				<th align="center"><a>remove</a></th>
			</tr>
		</table>
	</div>

	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
	<script src="static/js/jquery-3.2.1.min.js"></script>
	<!-- Include all compiled plugins (below), or include individual files as needed -->
	<script src="static/js/bootstrap.min.js"></script>
	<script src="static/js/jqPaginator.js"></script>

</body>
</html>