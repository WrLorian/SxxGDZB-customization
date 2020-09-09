<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<title>Insert title here</title>
<script src="<%=basePath%>static/js/jquery/jquery-3.3.1.js"></script>

<script src="<%=basePath%>static/js/layui/layui.js" charset="utf-8"></script>
<link href="<%=basePath%>static/js/layui/css/layui.css" rel="stylesheet" />
<script src="<%=basePath%>static/js/global.js"></script>
<style type="text/css">
html, body {
	width: 100%;
	height: 100%;
}
</style>
<script src="<%=basePath%>static/echarts/echarts.js" charset="utf-8"></script>
</head>
<body>
	<div class="layui-col-md12" style="font-size: 20px; top: 2%;"
		id="title" align="center">实时光照度曲线图</div>
		<button type="button" class="layui-btn layui-btn-primary"
				id="sssj" style="position: absolute;left: 5%">实时数据</button>
		<a type="button" href="<%=basePath%>return/aiming/qxt2.do" class="layui-btn layui-btn-primary"
				id="cut" style="position: absolute;right: 5%">切换</a>
	<div class="layui-col-md12" id="qxt" style="height: 70%;"></div>
	<div class="layui-col-md3" id="xs"
		style="height: 15%; font-size: 20px;" align="center"></div>
	<form class="layui-form layui-col-md9" action="" lay-filter="example">


		<div class="layui-inline" style="width: 100%">
			<div class="layui-inline">
				<div class="layui-input-inline">
					<input name="date" class="layui-input" id="date_01" type="text"
						placeholder="yyyy-MM-dd" autocomplete="off" lay-verify="date_01">
				</div>
				<div class="layui-input-inline">
					<input name="date" class="layui-input" id="date_02" type="text"
						placeholder="yyyy-MM-dd" autocomplete="off" lay-verify="date_02">
				</div>
			</div>
			<button type="button" class="layui-btn layui-btn-primary"
				id="confirm">确定</button>
			<div class="layui-inline" style="width: 100%"></div>
	</form>

	
			<div class="layui-inline" style="width: 100%"></div>
	</div>

</body>

<script type="text/javascript">
var Refresh_time = 60 * 1000;
var str = "";
var timer ;
var qxt ;
var option;
timeTwoInterval = 0;//0 查分钟  2 查秒
var type = 1;//显示
var arrDL = [];
var arrDY = [];
layui.use(['form', 'layedit', 'laydate'], function(){
	  var form = layui.form
	  ,layer = layui.layer
	  ,layedit = layui.layedit
	  ,laydate = layui.laydate;
	  //时间
	   laydate.render({
		    elem: '#date_01',
		    lang:'cn',
		    value:new Date(),
		    done:function(value,date){
		    	
		    }
	   })
	   laydate.render({
		    elem: '#date_02',
		    lang:'cn',
		    done:function(value,date){
		    	
		    }
	   })
});
$(function(){
	findInitLight();
	
	qxtEcharts();
	
	clickX();
})

function findInitLight(time_01,time_02){
	$.ajax({
		type : "post",
		url :"<%=basePath%>findInitLight.do",
	　　  dataType : "json",
	  cache:false,  
      async:false,
	    data:{"time_01":time_01,"time_02":time_02,"timeTwoInterval":timeTwoInterval},
		async:false,
	　　  success : function(msg){
		  arrDY = [];
		  arrDL = [];
		$.each(msg.data,function(index,xx){
			var newTime = new Date(msg.data[index].time); 
			arrDY.push(newTime.format('yyyy-MM-dd hh:mm'));
			arrDL.push([newTime.format('yyyy-MM-dd hh:mm:ss'),msg.data[index].lux]);
			//arrDL.push(msg.data[index].lux);
			//$("#xs").html("当前光照度：<span style='color: green;'>" + msg.data[index].lux + "</span>    (LX)");
		})
		}
	})
	console.log(arrDL)
}
	
	function qxtEcharts(){
	  echarts.dispose(document.getElementById("qxt")) 
      qxt = echarts.init(document.getElementById('qxt'));
	 option = {
		    color: ['#3398DB'],
		    tooltip : {
		        trigger: 'axis',
		         axisPointer : {            // 坐标轴指示器，坐标轴触发有效
			            type : 'line'        // 默认为直线，可选为：'line' | 'shadow'
			        } ,
			        
			        formatter: function (params) {
			            console.log(params[0].data)
			            return params[0].data[0] + '<br/> 光照度：' + params[0].data[1];
			        }
		    },
		    grid: {
		        left: '3%',
		        right: '4%',
		        
		        containLabel: true
		    },
		    dataZoom: [{
		    	show: true,    //只需要将这一项设置为0即可
		    	 start : 0,
		    	 end:100
	        }], 
		    xAxis : [//X轴
		        {
		    		show:true,
		            type : 'time',
		           // splitNumber:24,
		            splitLine:{show: false},//去除网格线
		            splitArea : {show : true},//保留网格区域
		            triggerEvent:true,//点击事件
		            data: (function (){
		                return arrDY;
		            })() 
		           /*  name : '时间(s)', */
		           /*  splitLine:{show: false},//去除网格线
		            splitArea : {show : true},//保留网格区域
		            triggerEvent:true,//点击事件
		            
		            data: (function (){
		                return arrDY;
		            })() */
		        }
		    ],
		    yAxis : [//Y轴
		        {
		            type : 'value',
		            name : '光照度(LX)'/* ,
		            	axisLabel: {                            
		            		textStyle: {  //文字样式                                
		            			color: '#62799C',                                
		            			fontSize: '12'},                         
		            			},                        
		            			// 控制网格线                        
		            			splitLine: {                            
		            				//  改变轴线颜色                            
		            			lineStyle: {color: '#2a2a2d'}
		            			,show: false  ////去除网格线                    
		            			},axisTick: {       //y轴刻度线不显示
		                            show: false
		                        },splitArea : {
		                        	show : true
		                        	}//保留网格区域 */
		        }
		    ],
		    series : [
		        {
		            name:'光照度',
		           type: 'line',
		           symbol:'none',  //这句就是去掉点的  
		           //smooth:true,  //这句就是让曲线变平滑的 
		           itemStyle: {
		        	   normal: {
		        		   color: 'rgba(62,139,222,1)'
		        		   //线颜色                            
		        		   }                        
		        	} ,
		        	areaStyle: {
		        		normal: {
		        			color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{ 
		        				//折线图颜色渐变                                    
		        				offset: 0,                                    
		        				color: 'rgba(62,139,222,0.6)'                                
		        				}, {
		        					offset: 1,
		        					color: 'rgba(62,139,222,0.01)'
		        					}])                            
		        	}                        
		        	} ,
		        	/* label: {//树状图上方显示
							show: true, //开启显示
							position: 'top', //在上方显示
							textStyle: { //数值样式
								//color: 'black',//默认与树状图的颜色一致
								fontSize: 12
							}
						}, */
		            barWidth: '60%',
		            data:arrDL
		        }
		    ]
		};
	 console.log(arrDL);
	 if(arrDL.lenght < 24 ){
		 option.xAxis[0].splitNumber=arrDL.length;
	 }else{
		 option.xAxis[0].splitNumber=24;
	 }
	qxt.setOption(option);
	
	
	}
	
	
	function clickX(){
		qxt.on('click', function (params) {  
			if(params.componentType == "xAxis"){
				console.log(option.xAxis[0].data.length)
				 // console.log(option)
					var date_01 = $("#date_01").val();
					var x1 =date_01.substring(0,4) + "-" + params.value.substring(6,11) + " " + params.value.substring(0,5);
					//alert(x1)
					var a = option.xAxis[0].data.indexOf(x1);
					//alert(a);
					//var date1 = new Date(msg.data[0].time).format('yyyy-MM-dd hh:mm:ss');
				}else{            
					alert("单击了"+params.name+"柱状图");       
					}    }); 
	}
	
	$("#confirm").click(function(){
		$("#xs").empty();
		timeTwoInterval = 0;
	 	Refresh_time = 60 * 1000;
	 	clearInterval(timer);
	 	type  =  1;
		var date_01 = $("#date_01").val();
		var start_01 = new Date(date_01.replace("-", "/").replace("-", "/"));
		var date_02 = $("#date_02").val();
		var start_02 = new Date(date_02.replace("-", "/").replace("-", "/"));
		var d = new Date();
		d.format('yyyy-MM-dd');
		if(start_01 == null || start_01 == ""){
			alert("第一个时间不能为空")
		}else if(start_02 > d || start_01 > d){
			alert("所选时间不能大于目前时间")
		}
		else if(start_01 > start_02 && start_02!=""){
			alert("第一个时间不能大于第二个时间!!")
		}else{
			//第二个是今天
			 if(start_02.format('yyyy-MM-dd') == d.format('yyyy-MM-dd')){
					findInitLight(date_01,date_02);
					qxtEcharts();
					clearInterval(timer);
					timeRef();
				$("#title").html(date_01 +" - " +date_02 + "记录曲线图")
			}
			//第一个是今天  第二个不选
			else if(start_01.format('yyyy-MM-dd') == d.format('yyyy-MM-dd') && date_02==""){
				findInitLight(date_01,date_02);
				qxtEcharts();
				clearInterval(timer);
				timeRef();
				$("#title").html(date_01  + "记录曲线图")
			}
			else{
				findInitLight(date_01,date_02);
				clearInterval(timer);
				qxtEcharts();
				$("#title").html(date_01 +"记录曲线图")
			}
		}
	});

timeRef();
function timeRef(){
	 timer = setInterval(function (){
		 axisData = (new Date()).toLocaleString('chinese', { hour12: false }).split(/[\s\n]/)[1];//新时间
		 var data0 = option.series[0].data;
		 var a=0 ;
		 var date_ ;
			$.ajax({
				type : "post",
				url :"<%=basePath%>findOneLight.do",
				data:{"timeInterval":Refresh_time},
			　　    dataType : "json",
			    async:false,
			　　    success : function(msg){
			　　    	a = msg.data[0].lux;
			　　    	//date_ =  new Date(msg.data[0].time).format('yyyy-MM-dd hh:mm:ss').split(/[\s\n]/)[1];
			　　    	date_ =  new Date(msg.data[0].time).format('yyyy-MM-dd hh:mm:ss');
				}
			})
		//更新X轴  使X轴坐标个数保持唯一
		if(type == 1){
     		 //data0.push(a);//获取一个随机数 添加至数组当中
     		//option.xAxis[0].data.push(date_);
     		data0.push([date_,a]);
     		if(data0.lenght > 60){
     			option.xAxis[0].splitNumber=24;
     		}
     		
     		qxt.setOption(option);
     	}else{
     	 if(option.xAxis[0].data.length == 20){
     		data0.shift();//shift()方法用于把数组的第一个元素从其中删除，并返回第一个元素的值  此处只需删除第一个元素
     		option.xAxis[0].data.shift();//先删除第一个元素（最早的时间）
     	 }
     	 data0.push(a);//获取一个随机数 添加至数组当中
		 option.xAxis[0].data.push(axisData);//添加一个新的元素（最新的时间）
		 qxt.setOption(option);
   		 $("#xs").html("当前光照度：<span style='color: green;'>" + a + "</span>    (LX)");
     	}
	}, Refresh_time);
}

$("#sssj").click(function(){
	type = 2;
	Refresh_time = 1000 * 5;
	$.ajax({
		type : "post",
		url :"<%=basePath%>sssj.do",
	　　  dataType : "json",
	  cache:false,
      async:false,
	　　  success : function(msg){
		 arrDY = [];
		 arrDL = [];
		$.each(msg.data,function(index,xx){
			var newTime = new Date(msg.data[index].time); 
			arrDY.push(newTime.format('yyyy-MM-dd hh:mm:ss').split(/[\s\n]/)[1]);
			arrDL.push(msg.data[index].lux);
		})
		}
	})
	//alert(arrDY.length);
	if(arrDY.length > 0){
		qxtEcharts2();
		timeRef();
		
	}else{
		 $("#xs").html("<span style='color: red;'>设备中断、请检查！！！</span>");
	}
	
})
function qxtEcharts2(){
	echarts.dispose(document.getElementById("qxt")) 
    qxt = echarts.init(document.getElementById('qxt'));
	
	 option = {
		    color: ['#3398DB'],
		    tooltip : {
		        trigger: 'axis',
		         axisPointer : {            // 坐标轴指示器，坐标轴触发有效
			            type : 'line'        // 默认为直线，可选为：'line' | 'shadow'
			        } 
		    },
		    grid: {
		        left: '3%',
		        right: '4%',
		        containLabel: true
		    },
		    xAxis : [//X轴
		        {
		    		show:true,
		            type : 'category',
		            splitLine:{show: false},//去除网格线
		            splitArea : {show : true},//保留网格区域
		            triggerEvent:true,//点击事件
		            data: (function (){
		                return arrDY;
		            })() ,axisLabel:{
	                    interval:0
	                }
		          
		        }
		    ],
		    yAxis : [//Y轴
		        {
		            type : 'value',
		            name : '光照度(LX)'
		        }
		    ],
		    series : [
		        {
		            name:'光照度',
		           type: 'line',
		           symbol:'none',  //这句就是去掉点的  
		           smooth:true,  //这句就是让曲线变平滑的 
		           itemStyle: {
		        	   normal: {
		        		   color: 'rgba(62,139,222,1)'
		        		   //线颜色                            
		        		   }                        
		        	} ,
		        	areaStyle: {
		        		normal: {
		        			color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{ 
		        				//折线图颜色渐变                                    
		        				offset: 0,                                    
		        				color: 'rgba(62,139,222,0.6)'                                
		        				}, {
		        					offset: 1,
		        					color: 'rgba(62,139,222,0.01)'
		        					}])                            
		        	}                        
		        	} ,
		        	/* label: {//树状图上方显示
							show: true, //开启显示
							position: 'top', //在上方显示
							textStyle: { //数值样式
								//color: 'black',//默认与树状图的颜色一致
								fontSize: 12
							}
						}, */
		            barWidth: '60%',
		            data:arrDL
		        }
		    ]
		};
	qxt.setOption(option);
	}
	
</script>
</html>