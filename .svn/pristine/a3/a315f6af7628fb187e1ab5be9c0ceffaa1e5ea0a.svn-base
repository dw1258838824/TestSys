package com.ruoyi.project.system.api.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.ruoyi.common.utils.HttpUtils;

import io.swagger.annotations.Api;

@Api
@Controller
@RequestMapping("/api/python")
public class PythonApi {
	@Value("${python_api.method}") 
	private  String method;
	@Value("${python_api.url}") 
	private  String url;
	@Value("${python_api.parms}") 
	private  String parms;
	@Value("${python_api.values}") 
	private  String values;
	@Value("${python_api.result_key}") 
	private  String result_key;
	

	@Value("${python3_api.url}") 
	private  String url3;
	
	/**
	 * 编译python接口
	 * @param code
	 * @return
	 */
	public String pythonRun3(String code) {
		Map<String, String> parm = Maps.newHashMap();
		parm.put("code",code);
		String result = "";
		try {
			result = HttpUtils.URLPost(url3, parm, "UTF-8"); //"https://tool.runoob.com/compile2.php"
			System.out.println("python3编译： "+result);
			if(result!="") {
				JSONObject obj = JSONObject.parseObject(result);
				if(obj.getString("code").equals("Success")) {
					result = obj.getString("output");
					result = result.replaceAll("\n","\r\n");
					result = result.substring(0,result.length()-2);
				}else {
					result = "";
				}
			}
		} catch (Exception e) {
			StringWriter sw = new StringWriter(); 
            e.printStackTrace(new PrintWriter(sw, true)); 
            result = sw.toString(); 
		}
		return result;
	}
	
	/**
	 * 编译python接口
	 * @param code
	 * @return
	 */
	public String pythonRun(String code) {
		String result = "";
		String [] parmsArray = parms.split("##");
		String [] valuesArray = values.split("##");
		try {
			Map<String, String> parm = Maps.newHashMap();
			for(int i=0;i<parmsArray.length;i++) {
				if(valuesArray[i].equals("$code")) {
					parm.put(parmsArray[i],code);
				}else {
					parm.put(parmsArray[i],valuesArray[i].equals("empty")?"":valuesArray[i]);
				}
			}
			if(method.equals("POST")) {
				result = HttpUtils.URLPost(url, parm, "UTF-8");
			}else {
				result = HttpUtils.URLGet(url, parm, "UTF-8");
			}
			if(result!="") {
				JSONObject obj = JSONObject.parseObject(result);
				result = obj.getString(result_key);
				result = result.replaceAll("\n","\r\n");
				result = result.substring(0,result.length()-2);
			}
		}catch (Exception e) {
			StringWriter sw = new StringWriter(); 
            e.printStackTrace(new PrintWriter(sw, true)); 
            result = sw.toString(); 
		}
		return result;
	}
	
	
	@GetMapping("/run")
	@ResponseBody
	public String compile(HttpServletRequest request){
		String result = "";
		try {
			result = pythonRun3(request.getParameter("code"));
			if(result.equals("")) {
				result = pythonRun(request.getParameter("code"));
			}
			System.out.println("javaApi请求结果："+result);
		} catch (Exception e) {
			StringWriter sw = new StringWriter(); 
            e.printStackTrace(new PrintWriter(sw, true)); 
            result = sw.toString(); 
		}
		return result;
	}
	 
	public static void main(String[] args) throws IOException {
		Map<String, String> parm = Maps.newHashMap();
		parm.put("code","turtle.screensize(800,600, 'green')");
		parm.put("fileName","python10012015");
		String result = "";
		try {
			result = HttpUtils.URLPost("http://123.57.153.89:5000/runTurtle", parm, "UTF-8"); //"https://tool.runoob.com/compile2.php"
			System.out.println(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
