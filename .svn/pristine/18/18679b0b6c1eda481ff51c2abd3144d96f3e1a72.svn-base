package com.testsys.utils;

import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.ruoyi.common.utils.HttpUtils;
import com.ruoyi.common.utils.Md5Utils;

public class ClassInUtils {
	
	
	public static void main(String[] args) {
		Map<String, String> params = Maps.newHashMap();
		String now = (System.currentTimeMillis()/1000)+"";
		params.put("SID", "23071866");
		params.put("safeKey", Md5Utils.toMD5(new StringBuffer("6tfokB8e").append(now).toString()));
		params.put("timeStamp", now);
		params.put("courseId", "86670907");
		params.put("className", "空");
		params.put("beginTime", "1594018800");
		params.put("endTime", "1594021800");
		params.put("teacherUid", "20735718");
		params.put("record", "1");
		params.put("live", "1");
		params.put("replay", "1");
		String json=  HttpUtils.URLPost("https://api.eeo.cn/partner/api/course.api.php?action=addCourseClass", params, "UTF-8");
		System.out.println(json);
		JSONObject obj = JSONObject.parseObject(json);
    	Integer errno = obj.getJSONObject("error_info").getInteger("errno");
    	if(null!=errno && (errno==1 || errno==135)) {//注册成功或者已存在
    		Long uid = obj.getLong("data");//添加老师获得老师uid
    		System.out.println(uid);
    	}else {
    		System.out.println("错误");
    	}
	}
}
