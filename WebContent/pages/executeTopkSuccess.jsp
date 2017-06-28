<%@page import="java.util.Map.Entry"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.TreeMap"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		TreeMap<Long, HashMap<String,Object>> output = (TreeMap<Long, HashMap<String,Object>>) request.getAttribute("output");
	    Integer num = (Integer)request.getAttribute("num_of_query");
	%>
	<p>
		top<font color="red"><%=num %></font> query:<br>
	</p>
	<table border="1">
		<tr>
			<th>bytes</th>
			<th>dport</th>
			<th>sip</th>
			<th>dip</th>
			<th>sport</th>
			<th>proto</th>
			<th>path</th>
		</tr>
		<% Iterator iter = output.entrySet().iterator(); 
			int bytes=0;
			HashMap<String, Object> value = null;
			while(iter.hasNext()){
				Entry<Integer, HashMap<String, Object>> entry = (Entry<Integer, HashMap<String, Object>>)iter.next();
				bytes = (Integer)entry.getKey();
				value = (HashMap<String, Object>)entry.getValue();
				HashMap<String,String> flowid = (HashMap<String,String>)value.get("flowid");
		%>
		<tr>
			<th><%=bytes %></th>
			<th><%=flowid.get("dport")  %></th>
			<th><%=flowid.get("sip")  %></th>
			<th><%=flowid.get("dip")  %></th>
			<th><%=flowid.get("sport")  %></th>
			<th><%=flowid.get("proto")  %></th>
			<th><%= value.get("path")%></th>
		</tr>
	<%} %>
	</table>
</body>
</html>