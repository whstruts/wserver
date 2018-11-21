<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>系统参数配置</title>
	<script type="text/javascript" src="JS/jquery-1.7.2.min.js"></script>
	<script type="text/javascript" >
		function mSendGetQuest(log_type){
			
			var json_data={};
			var json_data_array=[json_data];
			
			var json_get={};
			json_get.table="inisysparam";
			json_get.type=log_type;
			json_get.data=json_data_array;
			
			var jsonPara={para01:JSON.stringify(json_get)}
			
			$.get("main_api?method=mode_sysfun",jsonPara,
					function(data) {
				        var obj = JSON.parse(data);				        
				        var obj_data=obj.Data[0];				        
				        $("#txt_db_oracle_name").val(obj_data.db_oracle_name);	
				        $("#txt_db_oracle_user").val(obj_data.db_oracle_user);
				        $("#txt_db_oracle_pwd").val(obj_data.db_oracle_password);
				        $("#txt_db_mysql_name").val(obj_data.db_mysql_name);	
				        $("#txt_db_mysql_user").val(obj_data.db_mysql_user);
				        $("#txt_db_mysql_pwd").val(obj_data.db_mysql_password);
				        $("#txt_logpath").val(obj_data.log_path);
				        $("#txt_redissvrip").val(obj_data.redis_svrip);
				        $("#txt_redissvrport").val(obj_data.redis_svrport);
				        $("#txt_sysurl").val(obj_data.sysurl);
				        
					}
			);
		}
		function mSendSaveQuest(log_type){
			
			if ($("#txt_db_oracle_name").val()==""||
				$("#txt_db_oracle_user").val()=="" ||
				$("#txt_db_oracle_pwd").val()=="" ||
				$("#txt_db_mysql_user").val()=="" ||
				$("#txt_db_mysql_name").val()=="" ||
				$("#txt_db_mysql_pwd").val()==""  ||
				$("#txt_logpath").val()==""       )
				return;
			
				
				
				
			
			var json_data={};
			json_data.db_oracle_name=$("#txt_db_oracle_name").val();
			json_data.db_oracle_user=$("#txt_db_oracle_user").val();
			json_data.db_oracle_password=$("#txt_db_oracle_pwd").val();
			
			json_data.db_mysql_name=$("#txt_db_mysql_name").val();
			json_data.db_mysql_user=$("#txt_db_mysql_user").val();
			json_data.db_mysql_password=$("#txt_db_mysql_pwd").val();
			
			json_data.log_path=$("#txt_logpath").val();
			json_data.redis_svrip=$("#txt_redissvrip").val();
			json_data.redis_svrport=$("#txt_redissvrport").val();
			json_data.sysurl=$("#txt_sysurl").val();
			
			
			var json_data_array=[json_data];
			
			var json_get={};
			json_get.table="inisysparam";
			json_get.type=log_type;
			json_get.data=json_data_array;
			
			var jsonPara={para01:JSON.stringify(json_get)}
			
			$.get("main_api?method=mode_sysfun",jsonPara);
		}
	</script>
	<style>
		.logButton{
		width:150px;height:50px;background-color:blue;color:white;font-size:18px;
		}
	</style>
</head>
<body>
  <table width="100%" height="100%" border="1" align="center" cellpadding="0" cellspacing="1" bgcolor="DimGray">
		 <tr height="50px" >
		      <td colspan="2" bgcolor="DarkGray" align="center" height="50px"  style="color:white;font-size:32px">
		      	系统参数配置
		      </td>
		 </tr>
		 <tr height="70%" valign="top">		 	  
		 	  <td width="8%" >
		 	  	<table bgcolor="#FFFFFF"  border="1" align="left" cellpadding="0" cellspacing="1">
		 	  		<tr><td><input class="logButton" type="button" name="bt_syslog"  value="刷新参数" onclick="mSendGetQuest('query')" /></td></tr>
		 	  		<tr><td><input class="logButton" type="button" name="bt_pdalog"  value="保存参数"  onclick="mSendSaveQuest('update')" /></td></tr>
		 	  		<tr><td><input class="logButton" type="button" name="bt_return"  value="返回主界面"   onclick="location.href='main.jsp'" /></td></tr>		 	  					
				</table>
		 	  </td>
	 	  	  <td width="80%">
	 	  	    <table id="tb_senddata" width="100%" bgcolor="#EFEFEF"  border="1" >
	 	  	    	<tr>
	 	  	    		<td>Oracle数据库名称:</td>
		     		    <td><input style="font-size:18px;width:550px;height:20px" type="text" id="txt_db_oracle_name"   value=""></td>
	 	  	    	<tr>
	 	  	    	<tr>
	 	  	    		<td>Oracle数据库密码:</td>
		     		    <td><input style="font-size:18px;width:550px;height:20px" type="password" id="txt_db_oracle_pwd"   value=""></td>
	 	  	    	<tr>
	 	  	    	<tr>
	 	  	    		<td>Oracle数据库用户:</td>
		     		    <td><input style="font-size:18px;width:550px;height:20px" type="text" id="txt_db_oracle_user"   value=""></td>
	 	  	    	<tr>
	 	  	    	
	 	  	    	<tr>
	 	  	    		<td>MySqL数据库名称:</td>
		     		    <td><input style="font-size:18px;width:550px;height:20px" type="text" id="txt_db_mysql_name"   value=""></td>
	 	  	    	<tr>
	 	  	    	<tr>
	 	  	    		<td>MySqL数据库密码:</td>
		     		    <td><input style="font-size:18px;width:550px;height:20px" type="password" id="txt_db_mysql_pwd"   value=""></td>
	 	  	    	<tr>
	 	  	    	<tr>
	 	  	    		<td>MySqL数据库用户:</td>
		     		    <td><input style="font-size:18px;width:550px;height:20px" type="text" id="txt_db_mysql_user"   value=""></td>
	 	  	    	<tr>
	 	  	    	
	 	  	    	<tr>
	 	  	    		<td>系统日志路径:</td>
		     		    <td><input style="font-size:18px;width:550px;height:20px" type="text" id="txt_logpath"   value=""></td>
	 	  	    	<tr>	
	 	  	    	 <tr>
					    <td>Redis服务IP:</td>
					    <td><input style="font-size:18px;width:550px;height:20px" type="text" id="txt_redissvrip"   value=""></td>
					 </tr>
					 <tr>
					    <td>Redis端口Port:</td>
					    <td><input style="font-size:18px;width:550px;height:20px" type="text" id="txt_redissvrport"   value=""></td>
					 </tr>	 
					 <tr>
					    <td>前端端口网址:</td>
					    <td><input style="font-size:18px;width:550px;height:20px" type="text" id="txt_sysurl"   value=""></td>
					 </tr>	
					 <tr>
						 <table id="tb_logd" width="100%" bgcolor="#EFEFEF"  border="1" >
			 	  	    	<tr>
			 	  	    		<td width="15%"> 时间 </td>
			 	  	    		<td width="85%"> 内容 </td>
			 	  	    	<tr>	 	  			
						</table>
		  		    	<div style="width:100%;height:600px;overflow:auto">
							<table id="tb_logdata"  bgcolor="#FFFFFF" width="100%" border="1" >
									 	  			
						    </table>
		  		    	</div>			
				</table>
			 </td>	 					
		 </tr>
		 
         <tr height="30px">
		 	<td  bgcolor="#EFEFEF" align="left" style="color:red">温馨提示</td>
		 	<td  bgcolor="#EFEFEF" align="left" ></td>
		 </tr>
	</table>
</body>
</html>