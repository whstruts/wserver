package jzt.wcs.wserver.controller;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import jzt.wcs.wserver.modefun.clsAccDBFun;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

@RestController
public class WController {

	@Autowired
	private HttpServletRequest myHttpRequest;
	@RequestMapping(path = {"/WServer"})
	public String WServerSpring () {
		System.out.println("WServer spring boot");
		return "WServer spring boot";
	}
	@RequestMapping(path = {"/WServer_api/main_api"})
	public String WServerAPI(){
		String smethod = "";
		JSONArray rlt_json_array = null;
		JSONObject _json_outPram = new JSONObject();
		try{
			StringBuilder sb = new StringBuilder();
			InputStream inputStream = myHttpRequest.getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					inputStream, Charset.forName("UTF-8")));
			String line = null;
			while ((line = reader.readLine()) != null) {
				sb.append(line);
			}
			String jsonInputStr = sb.toString();

			JSONObject json_main = JSONObject.parseObject(jsonInputStr);
	//		JSONArray rlt_json_array = null;
			smethod = json_main.getString("method").toString();
			clsAccDBFun _clsDb = new clsAccDBFun();

			rlt_json_array = _clsDb.mAccDBFunction(smethod, jsonInputStr);
			
			_json_outPram.put("Flag", "true");
			_json_outPram.put("Data", rlt_json_array);
			_json_outPram.put("ErrInfo", "ok");
		}
        catch (Exception e)
		{
           System.out.println(e.getMessage());
		}
		System.out.println("main_api"+smethod);
		return _json_outPram.toJSONString();
	}
}
