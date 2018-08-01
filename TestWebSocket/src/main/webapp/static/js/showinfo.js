window.onload = function() {
	console.log(getLocalTime(1471592696174));
	var time = null;
	var ys = new WebSocket(webSocketUrl+"/ys");// 连接服务器
	ys.onmessage = function(event) {
		var obj = eval("(" + event.data + ")");
		for (var i = 0; i < obj.length; i++){
			$(".content")
					.append(
							"<li><span class='param1'>"
									+ obj[i].orderId
									+ "</span><span class='param2'>"
									+ getLocalTime(obj[i].time)
									+ "</span><div class='param3'><a class='closeBtn'>断开连接</a></div></li>");
		}
		$(".closeBtn").click(function() {
			var _this = this
			var a = $(this).parent().siblings(".param1").text();
			//console.log($(".content-title li").length)
			ys.send(a);
			ys.onmessage = function(event){
				//$(_this).parent().parent().remove();
				if(event.data == a){
					console.log("1")
					$(_this).parent().parent().remove();
				}
			}
			 
			
			
		});
		 
	};
};
function getLocalTime(nS) {     
   return new Date(parseInt(nS)).toLocaleString().replace(/:\d{1,2}$/,' ');     
}     


