<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="${staticUrl}/static/js/jquery-1.11.0.min.js"></script>
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="${staticUrl}/static/css/base.css">
<script type="text/javascript">
	var webSocketUrl = "${webSocketUrl}";
</script>
</head>
<body>
	<div class="wrap">
		<div class="page">
			<h1 class="title">参数显示</h1>
			<div class="content-title clearfix">
				<span class="param1">orderId</span>
				<span class="param2">信息</span>
				<span class="param3">断开连接</span>
			</div>
			<ul class="content"></ul>
		</div>
	</div>
<script type="text/javascript" src="${staticUrl}/static/js/showinfo.js"></script>

</body>
</html>