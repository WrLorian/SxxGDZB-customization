function getRootPath(){
    var curWwwPath=window.document.location.href;
    var pathName=window.document.location.pathname;
    var pos=curWwwPath.indexOf(pathName);
    var localhostPaht=curWwwPath.substring(0, pos);
    return localhostPaht;
}
//弹框
function openPopUp(title,url,w,h){
	w = w || 50;
	h = h || 70;
	var index = layer.open({
        title: title,
        type: 2,
        shade: 0.2,
        maxmin:true,
        //shadeClose: true,
        area: [w + '%', h +'%'],
        content: url
    });
}
String.prototype.NoSpace = function () {
    return this.replace(/\s+/g, "");
};
/*返回数组下标*/
Array.prototype.indexOf = function(val) {
	for (var i = 0; i < this.length; i++) {
		if (this[i] == val)
			return i;
	}
	return -1;
};
/*根据下标删除数组内容*/
Array.prototype.remove = function(dx) {
	for (var i = 0, n = 0; i < this.length; i++) {
		if (this[i] != this[dx]) {
			this[n++] = this[i]
		}
	}
	this.length -= 1
}
//得到主页
var indexHome = function(elem,i) {
		if(typeof(i) != "undefined" || i != ''){
			i = i + 1;
		}
	   if(elem.index == 0 || i > 5){
			return elem;
	   }
	return indexHome(elem.parent,i);
}

//list ---> listTree
function convert(rows) {
	function exists(rows, parentId) {
		for (var i = 0; i < rows.length; i++) {
			if (rows[i].id == parentId)
				return true;
		}
		return false;
	}
	var nodes = [];
	for (var i = 0; i < rows.length; i++) {
		var row = rows[i];
		if (!exists(rows, row.parentId)) {
			nodes.push({
				id : row.id,
				name:row.name,
				parentId:row.parentId,
				title:row.name
			});
		}
	}
	var toDo = [];
	for (var i = 0; i < nodes.length; i++) {
		
		toDo.push(nodes[i]);
	}
	
	while (toDo.length) {
		var node = toDo.shift(); 
		for (var i = 0; i < rows.length; i++) {
			var row = rows[i];
			if (row.parentId == node.id) {//寻找子元素
				var child = {
					id : row.id,
					name:row.name,
					parentId:row.parentId,
					title:row.name
				};
				if (node.children) {
					
					node.children.push(child);
				} else {
					
					node.children = [ child ];
				}
				toDo.push(child);
			}
		}
	}
	return nodes;
}
//返回数据全局定义
var callBackData = function() {
	var data = {};
	return data;
};
//去重复
Array.prototype.setList = function(){  
    var arr=[];    //定义一个临时数组  
    for(var i = 0; i < this.length; i++){    //循环遍历当前数组  
        //判断当前数组下标为i的元素是否已经保存到临时数组  
        //如果已保存，则跳过，否则将此元素保存到临时数组中  
        if(arr.indexOf(this[i]) == -1){  
            arr.push(this[i]);  
        }  
    }  
    return arr;  
}

function JwtErr(elem){
	layer.open({
        type: 1
        ,area: [ '35%', '50%']
        ,id: 'JwtErr' //防止重复弹出
        ,content: '<div style="padding: 20px 100px;">JwtErr验证失败，请重新登录验证!!!!! </div>'
        ,btn: '关闭'
        ,btnAlign: 'r' //按钮居中
        ,shade: 0 //不显示遮罩
        ,shadeClose :true
        ,yes: function(){
          layer.closeAll();
          window.location.href = "/";
        },cancel: function(){ 
        	window.location.href = "/";
          }
      });
}
//禁止页面鼠标事件
function Unclickable() {
	$("body").css("pointer-events", "none");
}
//取消禁止页面鼠标事件
function Mayclickable() {
	$("body").css("pointer-events", "");
}
//缓存对象 --->待加入 jwt 身份验证 页面提示
var authorization = localStorage.getItem('Authorization');
var authUser = JSON.parse(localStorage.getItem('authUser'));
(function ($) {
    $.getUrlParam = function (name) {
        var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
        var r = window.location.search.substr(1).match(reg);
        if (r != null) return unescape(r[2]); return null;
    }
})(jQuery);
/**
 * 得到多少天前后的日期
 * @param a
 * @returns
 */
function fun_day(a){
    var date1 = new Date(),
    time1 = date1.getFullYear()+"-"+(date1.getMonth()+1)+"-"+date1.getDate();
    var date2 = new Date(date1);
    date2.setDate(date1.getDate() + a);
    var time2 = date2.getFullYear()+"-"+(date2.getMonth()+1)+"-"+date2.getDate() + " " +date2.getHours() + ":" + date2.getMinutes() + ":" + date2.getSeconds();
    return time2;
}
function fun_month(a){
	var date1 = new Date(),
    time1 = date1.getFullYear()+"-"+(date1.getMonth()+1)+"-"+date1.getDate();
    var date2 = new Date(date1);
    date2.setMonth(date1.getMonth() + a);
    var time2 = date2.getFullYear()+"-"+(date2.getMonth()+1)+"-"+date2.getDate() + " " +date2.getHours() + ":" + date2.getMinutes() + ":" + date2.getSeconds();
    return time2;
}
function fun_hours(a){
	var date1 = new Date(),
    time1 = date1.getFullYear()+"-"+(date1.getMonth()+1)+"-"+date1.getDate();
    var date2 = new Date(date1);
    date2.setHours(date2.getHours() + a);
    var time2 = date2.getFullYear()+"-"+(date2.getMonth()+1)+"-"+date2.getDate() + " " +date2.getHours() + ":" + date2.getMinutes() + ":" + date2.getSeconds();
    return time2;
}
/**
 * 时间格式转化 DATE ---> String
 */
Date.prototype.Format = function (fmt) {
    var o = {
        "M+": this.getMonth() + 1,
        "d+": this.getDate(),
        "h+": this.getHours(),
        "m+": this.getMinutes(),
        "s+": this.getSeconds(),
        "q+": Math.floor((this.getMonth() + 3) / 3),
        "S": this.getMilliseconds()
    };
    if (/(y+)/.test(fmt))
        fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
        if (new RegExp("(" + k + ")").test(fmt))
            fmt = fmt.replace(RegExp.$1, (RegExp.$1.length === 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
}
/*OneNet 请求 设备批量命令下发，异步操作*/
function oneNet(e){
	$.ajax({
		url: "/onenet/issuedList",
	    type: "POST",
	    data:JSON.stringify(e),
	    dataType : "json",
	    cache:true, 
        async:false, 
        traditional:true,
　　		contentType: "application/json;charset=utf-8",
	    headers: { "Authorization": authorization ,"dz-usr": authUser.uid},//通过请求头来发送token，放弃了通过cookie的发送方式
	    success:function(data){
	    	console.log(data.result.data);
	    	if(data.result.data[e[0].imei] == 'read time out'){
	    		layer.msg('连接超时', {
	    			  icon: 2,
	    			  offset:'rt',
	    			  anim: 2,
	    			  time: 3000 //2秒关闭（如果不配置，默认是3秒）
	    		});
	    	}else if(data.result.data[e[0].imei].error == 'device not online') {
	    		layer.msg('设备掉线', {
	    			  icon: 2,
	    			  offset:'rt',
	    			  anim: 2,
	    			  time: 3000 //2秒关闭（如果不配置，默认是3秒）
	    			});
	    	}else if(data.result.data[e[0].imei].error == 'auth failed: not found'){
	    		layer.msg('auth failed: not found', {
	    			  icon: 2,
	    			  offset:'rt',
	    			  anim: 2,
	    			  time: 3000 //2秒关闭（如果不配置，默认是3秒）
	    			});
	    	}else{
	    		layer.msg(data.msg, {
	    			  icon: 1,
	    			  offset:'rt',
	    			  anim: 2,
	    			  time: 3000 //2秒关闭（如果不配置，默认是3秒）
	    			});
	    	}
	    }
	});
}
/*3:上报告警信息，4:上报系统参数*/
var onenetVariable = function(imei,eqptType){
	var e = {},r= {},array = new Array();
//	e.imei = imei;
//	r.reg_00 = 3;
//	e.register = r;
//	e.eqptType = eqptType;
//	array.push(e);
	var e = {},r= {};
	e.imei = imei;
	r.reg_00 = 4;
	e.register = r;
	e.eqptType = eqptType;
	array.push(e);
	return array;
}
/***
 * url ---> 路径
 * isLeft --> 是否刷新左菜单	
 * 
 * 
 ***/
function DELETE(url,isLeft){
	$.ajax({
		url: url,
		type : "DELETE",
　　		dataType : "json",
　　		contentType: "application/json;charset=utf-8",
	    headers: { "Authorization": authorization },//通过请求头来发送token，放弃了通过cookie的发送方式
	    success:function(data){
    	 	if(data.success){
    	 		if(isLeft){
    	 			indexHome(window).init();
    	 			flush();
    	 		}else{
    	 			flush(currentPage, jsonEntity);
    	 		}
    	 		layer.msg(data.msg,{icon:6, time: 2000});
			}else{
				layer.msg(data.msg,{icon: 5, time: 2000});
			}
	    }
	});
}

function UPDATE(url,parameterDate,isLeft){
	$.ajax({
		url: url,
	    type: "PUT",
	    data:JSON.stringify(parameterDate),
	    dataType : "json",
　　		contentType: "application/json;charset=utf-8",
	    headers: { "Authorization": authorization },//通过请求头来发送token，放弃了通过cookie的发送方式
	    success:function(data){
	    	 	if(data.success){
	    	 		layer.msg(data.msg, {
	    	 			icon: 6,
	    	 			time: 1000 //2秒关闭（如果不配置，默认是3秒）
    	 			}, function(){
    	 				var iframeIndex = parent.layer.getFrameIndex(window.name);
		    	 		parent.layer.close(iframeIndex);
    	 				if(isLeft){
    	 					indexHome(window).init();
    	 					window.parent.flush();
    	 				}else{
    	 					window.parent.flush(window.parent.currentPage,window.parent.jsonEntity);
    	 				}
    	 			});
				}else{
					layer.msg(data.msg,{icon: 5, time: 2000});
				}
	    }
	});
}

function ADD(url,parameterDate,isLeft){
	$.ajax({
			url: url,
		    type: "POST",
		    data:JSON.stringify(parameterDate),
		    dataType : "json",
	　　		contentType: "application/json;charset=utf-8",
		    headers: { "Authorization": authorization },//通过请求头来发送token，放弃了通过cookie的发送方式
		    success:function(data){
	    	 	if(data.success){
	    	 		layer.msg(data.msg, {
	    	 			  icon: 6,
	    	 			  time: 2000 //2秒关闭（如果不配置，默认是3秒）
	    	 			}, function(){
	    	 				var iframeIndex = parent.layer.getFrameIndex(window.name);
			    	 		parent.layer.close(iframeIndex);
	    	 				if(isLeft){
	    	 					indexHome(window).init();
	    	 					window.parent.flush();
	    	 				}else{
	    	 					window.parent.flush(window.parent.currentPage,window.parent.jsonEntity);
	    	 				}
	    	 			}); 
				}else{
					layer.msg(data.msg,{icon: 5, time: 2000});
				}
		    }
	});
}
/**
 * 字典表查询
 * @param type
 * @returns
 */
var _sysDict =  function(type){
	 var _data = null; 
	 $.ajax({
			url: "/dictionary/selectByType/" + type,
		    type: "GET",
		    dataType : "json",
		    cache:true, 
	        async:false, 
	　　		contentType: "application/json;charset=utf-8",
		    headers: { "Authorization": authorization },//通过请求头来发送token，放弃了通过cookie的发送方式
		    success:function(data){
		    	 	if(data.success){
		    	 		_data = data.data.data;
					}else{
						
					}
		    }
	})
	return _data;
}
/**
 * 获取数组中需要的 对象 --> 属性值
 */
var array_kv = function (array,k) {
	var v = null;
	array.forEach(function(item){
		//console.log(item);
    });
}