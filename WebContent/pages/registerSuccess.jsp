<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		String queryContent = (String) request.getAttribute("queryContent");
		String queryAggContent = (String) request.getAttribute("queryAggContent");
	%>
	<p>
		Register topk_query.py ----> <%=queryContent%><br>
		Register topk_query_agg.py ----> <%=queryAggContent%>
	</p>
</body>
</html>