<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>WCS日志</title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<script type="text/javascript" src="JS/jquery-1.7.2.min.js"></script>
	<script type="text/javascript" >
	
	
	
		function mSendGetQuest(log_type){
			$.get("srvt_writelog?method="+log_type,
				function(data) {	
					var jsonLog = JSON.parse(data);
					var tb = document.getElementById("tb_logdata");
				    var rowNum=tb.rows.length;
					rowNum=tb.rows.length;
					for (i=0;i<rowNum;i++){
			          tb.deleteRow(i);
			          rowNum=rowNum-1;
			          i=i-1;
				    }
					for(var i=0;i<jsonLog.length;i++){						
					   var row=document.createElement("tr");  
				       row.id=i;
				       for(var j=1;j<3;j++){
				           var cell=document.createElement("td"); 
				           if (j==1)
				           	   cell.width="15%";
				           else
				        	   cell.width="85%";
				           if(j==1)
				        	   cell.innerHTML=jsonLog[i].startTime +"<br>" +jsonLog[i].endTime +"<br>" +jsonLog[i].useTime;
				           else
				               cell.innerHTML=jsonLog[i].funName+"<br>"+jsonLog[i].inParam +"<br>"+jsonLog[i].outParam;
				           row.appendChild(cell);  
				       }
				       tb.appendChild(row);  
					}					
				}
			);
		}
	</script>
	<style>
		.logButton{
		width:150px;height:50px;background-color:blue;color:white;font-size:18px;
		}
	</style>
</head>
<body bgcolor="DarkGray">
	<table width="100%" height="100%" border="1" align="center" cellpadding="0" cellspacing="1" bgcolor="DimGray">
		 <tr height="50px" >
		      <td colspan="2" bgcolor="DarkGray" align="center" height="50px"  style="color:white;font-size:32px">
		      	日志查看
		      </td>
		 </tr>
		 <tr height="70%" valign="top">		 	  
		 	  
		 	  <td width="8%" >
		 	  	<table bgcolor="#FFFFFF"  border="1" align="left" cellpadding="0" cellspacing="1">
		 	  		<tr><td><input class="logButton" type="button" name="bt_syslog"  value="系统运行日志" onclick="mSendGetQuest('syslog')" /></td></tr>
		 	  		<tr><td><input class="logButton" type="button" name="bt_pdalog"  value="温湿数据日志"  onclick="mSendGetQuest('pdalog')" /></td></tr>
		 	  		<tr><td><input class="logButton" type="button" name="bt_mcslog"  value="MCS运行日志"  /></td></tr>
		 	  		<tr><td><input class="logButton" type="button" name="bt_ecslog"  value="ECS运行日志"  /></td></tr>
		 	  		<tr><td><input class="logButton" type="button" name="bt_wcpslog" value="WCPS运行日志" /></td></tr>
		 	  		<tr><td><input class="logButton" type="button" name="bt_errlog"  value="系统异常日志" onclick="mSendGetQuest('errlog')"/></td></tr>
		 	  		<tr><td><input class="logButton" type="button" name="bt_return"  value="返回主界面"   onclick="location.href='main.jsp'" /></td></tr>		 	  					
				</table>
		 	  </td>
	 	  	  <td width="80%">
	 	  	    <table id="tb_logd" width="100%" bgcolor="#EFEFEF"  border="1" >
	 	  	    	<tr>
	 	  	    		<td width="15%"> 时间 </td>
	 	  	    		<td width="85%"> 内容 </td>
	 	  	    	<tr>	 	  			
				</table>
  		    	<div style="width:100%;height:700px;overflow:auto">
					<table id="tb_logdata"  bgcolor="#FFFFFF" width="100%" border="1"  >
							 	  			
				    </table>
  		    	</div>
		     
		 	  </td>	
		 </tr>
           <tr height="30px">
		 	<td  bgcolor="#EFEFEF" align="left" style="color:red">温馨提示</td>
		 	<td  bgcolor="#EFEFEF" align="left" ></td>
		 </tr>
	</table>
</body>
</html>