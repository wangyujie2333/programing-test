function ToggleConnectionClicked() {
		if($("#sendId").val() == "") {
			msgInnerhtml("请输入用户id");
			return false;
		}

		try {
//			ws = new WebSocket("ws://localhost:8080/TestWebSocket/ws?tradeSn="+$("#sendId").val());//连接服务器
			ws = new WebSocket(webSocketUrl+"/ws?tradeSn="+$("#sendId").val());//连接服务器
			ws.onopen = function(event){msgInnerhtml("WebSocket连接成功");};
			ws.onmessage = function(event){
				//$(".floor3 li:first").val()
				msgInnerhtml(event.data);
			};
			ws.onclose = function(event){msgInnerhtml("WebSocket连接关闭");};
			ws.onerror = function(event){msgInnerhtml("WebSocket异常");};
		} catch (ex) {
			alert(ex.message);
		}
	};
	function SendData() {
		try{
			ws.send($("#sendMsg").val());
		}catch(ex){
			alert(ex.message);
		}
	};
	 
	function seestate(){
		alert(ws.readyState);
	}
	function closeWebSocket() {
	    ws.close();
	    $("#sendId").attr({disabled: false});
	}
	function msgInnerhtml(msg){
		var msg2=$("#sendId").val();
		
		if($(".floor3 li").length == "0" ){
			$(".floor3 ul").append("<li>" + msg +"</li>")
		}else{
			$("<li><span class='idshow'>"+ msg2 +"：</span>" + msg +"</li>").insertBefore('.floor3 li:first')
		}
	}
	
	function getClientInfo(){
		var ys = new WebSocket("ws://localhost:8080/TestWebSocket/ys");//连接服务器
		ys.onmessage = function(event){
			var s = eval ("(" + event.data + ")");
			
			
			for(var i=0; i<s.length;i++)
			{
				console.log(s[i])
			}
		};
	}