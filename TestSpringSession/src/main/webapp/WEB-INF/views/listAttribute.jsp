<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Home</title>
<link href="<%=request.getContextPath() %>/static/css/bootstrap.css" type="text/css"
	rel="stylesheet"></link>
<link href="<%=request.getContextPath() %>/static/css/bootstrap-responsive.css"
	type="text/css" rel="stylesheet"></link>
</head>
<body>
	<div class="content">
		<form class="form-inline" name="f"
			action="<%=request.getContextPath()%>/session.do?method=addAttribute"
			method="post">
			<fieldset>
				<legend>Add value to session</legend>
				<input type="text" id="key" name="key" placeholder="key" /> <input
					type="text" id="value" name="value" placeholder="value" />
				<button type="submit" class="btn btn-primary">Save</button>
			</fieldset>
		</form>
	</div>
	<div class="table-content">
		<table class="table table-bordered"
			style="table-layout: fixed; word-wrap: break-word;">
			<tr>
				<th>Attribute Name</th>
				<th>Attribute Value</th>
			</tr>
			<%
				Enumeration<String> attributes = request.getSession()
						.getAttributeNames();
				while (attributes.hasMoreElements()) {
					String attributeName = attributes.nextElement();
			%>
			<tr>
				<td><%=attributeName%></td>
				<td><%=request.getSession().getAttribute(attributeName)%></td>
			</tr>
			<%
				}
			%>
		</table>
	</div>
</body>
</html>