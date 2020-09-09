function getRootPath(){
    var curWwwPath=window.document.location.href;
    var pathName=window.document.location.pathname;
    var pos=curWwwPath.indexOf(pathName);
    var localhostPaht=curWwwPath.substring(0, pos);
    return localhostPaht;
}
//弹框
function openPopUp(title,url,w,h){
	console.log(w)
	console.log(h);
	w = w || 50;
	h = h || 70;
	console.log("-----------" + w);
	console.log("-----------" + h);
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
function fun_date(a){
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