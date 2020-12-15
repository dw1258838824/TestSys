package com.ruoyi.common.utils;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;

@PropertySource("classpath:application.yml")//读取application.yml文件
public class PythonUtils {
	
	private static String method;

	private static String url;
	
	private static String parms;
	
	private static String values;

	private static String result_key;
	
	private static final Logger log = LoggerFactory.getLogger(PythonUtils.class);
	
	/**
	 * 编译python接口
	 * @param code
	 * @return
	 */
	public static String pythonRun(String code) {
		String result = "";
		String [] parmsArray = parms.split("|");
		String [] valuesArray = values.split("|");
		try {
			Map<String, String> parm = Maps.newHashMap();
			for(int i=0;i<parmsArray.length;i++) {
				if(valuesArray[i].equals("${code}")) {
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
			}
		}catch (Exception e) {
			log.error("python编译接口错误",e);
		}
		return result;
	}


	@Value("${python_api.method}") 
	public void setMethod(String method) {
		PythonUtils.method = method;
	}

	@Value("${python_api.url}") 
	public void setUrl(String url) {
		PythonUtils.url = url;
	}

	@Value("${python_api.parms}") 
	public void setParms(String parms) {
		PythonUtils.parms = parms;
	}

	@Value("${python_api.values}") 
	public void setValues(String values) {
		PythonUtils.values = values;
	}

	@Value("${python_api.result_key}") 
	public void setResult_key(String result_key) {
		PythonUtils.result_key = result_key;
	}
	
	
}
