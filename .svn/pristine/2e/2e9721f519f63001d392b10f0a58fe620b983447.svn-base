package com.ruoyi.project.system.api.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.ruoyi.common.utils.HttpUtils;
import com.ruoyi.common.utils.Md5Utils;

@Service
public class ClassInService {

	@Value("${classIn.SID}")
	private String SID;
	@Value("${classIn.SECRET}")
	private String SECRET;
	@Value("${classIn.URL}")
	private String URL;

	/**
	 * 调用classIn接口
	 * @param action register：注册  addCourse：添加课程    addCourseClass：添加课节
	 * @param params post提交参数
	 * @return
	 */
	public String classInHttp(String action,Map<String, String> params) {
		String json = "";
		try {
			String now = (System.currentTimeMillis()/1000)+"";
			params.put("SID", SID);
			params.put("safeKey", Md5Utils.toMD5(new StringBuffer(SECRET).append(now).toString()));
			params.put("timeStamp", now);
			System.out.println("调用classIn参数" + action + "-----------: " + JSONObject.toJSONString(params));
			json = HttpUtils.URLPost(URL + action, params, "UTF-8");
			System.out.println("调用classIn返回" + action + "-----------: " + json);
		} catch (Exception e) {
			System.out.println("调用classIn异常" + action + "-----------: ");
			e.printStackTrace();
		}
		return json;
	}
}
