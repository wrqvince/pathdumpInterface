<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!-- 用于显示所有的query -->
	<h1>
		Result <small style="color: gray; font-size: medium;">Registered
			queries<br>
		</small>
	</h1>
	<div class="panel panel-default">
		<!-- Table -->
		<table class="table table-hover" align="center" width="100%">
			<%
				//ArrayList<String> list = (ArrayList<String>) request.getSession().getAttribute("queryName");
				//if (list != null) {
					//Iterator<String> iter = list.iterator();
					//while (iter.hasNext()) {
			%>
			<tr>
				<th><%=
		//		iter.next()
				%></th>
				<th><a href="javascript:void(0)">remove</a></th>
			</tr>
			<%
				//}
				//}
			%>
		</table>
	</div>

</body>
</html>