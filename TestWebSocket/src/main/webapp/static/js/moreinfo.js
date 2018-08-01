$("#connectID").click(function() {
  	    var baseidd =parseInt($("#baseID").val());
  	    var numid = parseInt($("#numID").val()-1);
  	    var allidd =[];
 		var allws = [];
  	    for(var b=0;b<=numid;b++){
  	    	allidd.push(baseidd+b)
  	    }
		for(var i =0; i<allidd.length; i++){
	 		ws = new WebSocket(webSocketUrl+"/ws?tradeSn="+allidd[i]);
	  		allws.push(ws)
	  		
	  		
	  		
		}
		for(var n=0; n<allws.length; n++){
  			console.log(allws.length)
	  		allws[n].onopen = function(event){msgInnerhtml2("WebSocket连接成功");};
	  		allws[n].onmessage = function(event){
			msgInnerhtml2(event.data);
			};
			allws[n].onclose = function(event){msgInnerhtml2("WebSocket连接关闭");};
			allws[n].onerror = function(event){msgInnerhtml2("WebSocket异常");};
		}
		$("#send").click(function(){
		 	console.log(allws)
				for(var n=0; n<allws.length; n++){
					console.log(allws[n])
					allws[n].send($("#sendMsg").val());
					
				}
			})	
	});
  function msgInnerhtml2(msg){
		if($(".floor3 li").length == "0" ){
			$(".floor3 ul").append("<li>" + msg +"</li>")
		}else{
			$("<li>" + msg +"</li>").insertBefore('.floor3 li:first')
		}
	}