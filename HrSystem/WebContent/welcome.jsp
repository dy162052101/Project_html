<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登录界面</title>
<style>
	body{background-color:#C0C0C0;}
	#d1{
		width:900px;
		height:500px;
		text-align:center;
		line-height:500px;
		margin:100px  auto;
		background-color:#B0B0B0;
		font-size:30px;
	}
</style>
<script type="text/javascript">

		function getTime() {
		
		    var dateObj = new Date();
		
		    var year = dateObj.getFullYear();//年
		
		    var month = dateObj.getMonth()+1;//月  (注意：月份+1)
		
		    var date = dateObj.getDate();//日
		
		    var day = dateObj.getDay();
		
		    var weeks = ["星期日","星期一","星期二","星期三","星期四","星期五","星期六"];
		
		    var week = weeks[day];//根据day值，获取星期数组中的星期数。
		
		    var hours = dateObj.getHours();//小时
		
		    var minutes = dateObj.getMinutes();//分钟
		
		    var seconds = dateObj.getSeconds();//秒
		
		    if(month<10){
		        month = "0"+month;
		    }
		    if(date<10){
		        date = "0"+date;
		    }
		    if(hours<10){
		        hours = "0"+hours;
		    }
		    if(minutes<10){
		        minutes = "0"+minutes;
		    }
		    if(seconds<10){
		        seconds = "0"+seconds;
		    }
		
		    var newDate = year+"年"+month+"月"+date+"日"+hours+":"+minutes+":"+seconds+"&nbsp &nbsp"+week;
		
		    document.getElementById("d1").innerHTML = newDate;//在div中写入时间
		
		    setTimeout('getTime()', 500);//每隔500ms执行一次getTime()
		
		}
</script>
</head>
<body onload="getTime()">
		<div id="d1">
		</div>
</body>
</html>