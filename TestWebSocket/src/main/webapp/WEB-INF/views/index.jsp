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
		<div class="wrap_top">
			<div class="floo1 clearfix">
				<input type="text" class="sendid" id="sendId" placeholder="输入用户ID"><a onclick="ToggleConnectionClicked()" class="connect" id="connect">连接websochet</a>

			</div>
			<div class="floor2">
			<div class="msg_wrap"><textarea  id="sendMsg"></textarea></div>
				<div class="clearfix">
					<a href="${staticUrl}/demo/index.do?method=jumpShowInfo" class="connect" style="float:left" target="_blank">request param</a>
					<a onclick="SendData()" id="send" class="connect ml">发送信息</a>
					<a onclick="closeWebSocket()" id="close" class="connect">关闭连接</a>
				</div>
				
			</div>
		</div>
		<div class="floor3">
			<ul>
			</ul>
			<p onclick="seestate()" class="message" >show readyState</p>
		</div>
		
	</div>
<script type="text/javascript" src="${staticUrl}/static/js/base1.js"></script>

</body>
</html>