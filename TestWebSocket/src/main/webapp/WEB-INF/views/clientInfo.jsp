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
			<div class="floo1 ">
				<div class="clearfix">
				<input onkeyup="value=value.replace(/[^\d]/g,'') " onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))" maxlength="8" id="baseID" NAME="baseID" class="sendid" placeholder="orderID" style="margin-right: 36px;"><input onkeyup="value=value.replace(/[^\d]/g,'') " onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))" maxlength="8" id="numID" class="sendid" placeholder="添加个数">
				<a class="connect" id="connectID">连接ws</a>
				</div>
			</div>
			<div class="floor2">
			<div class="msg_wrap"><textarea  id="sendMsg" placeholder="请输发送内容"></textarea></div>
				<div class="clearfix">
					<a href="${staticUrl}/demo/index.do?method=jumpShowInfo" class="connect" style="float:left" target="_blank">request param</a>
					<a  id="send" class="connect ml">发送信息</a>
					<a onclick="closeWebSocket()" id="close" class="connect">关闭连接</a>
				</div>
				
			</div>
		</div>
		<div class="floor3">
			<ul>
			</ul>
		</div>
		<p onclick="seestate()" class="message" >show readyState</p>
		
	</div>
	<input type="hidden" id="values">
<script type="text/javascript" src="${staticUrl}/static/js/base1.js"></script>
<script type="text/javascript" src="${staticUrl}/static/js/moreinfo.js"></script>
</body>
</html>