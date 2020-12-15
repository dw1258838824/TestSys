package com.testsys.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.testsys.utils.HttpUtil;

/**
 * 人脸识别认证（by：百度aip）
 * 
 * @author TestSys
 *
 */
public class FaceRecognitionAuth {

	// 应用APP_ID
	public static final String APP_ID = "20308087";
	// 应用API_KEY
	public static final String API_KEY = "u8OzevSro7akZHKoACTWNfma";
	// 应用SECRET_KEY
	public static final String SECRET_KEY = "eH7LtBg24RFcanreo6IwpdlDXqp80sqN";

	/**
	 * 人脸注册
	 * 
	 * @param groupId     用户组ID
	 * @param userId      用户id
	 * @param imageBase64 用户人脸照片
	 * @param remark      用户描述
	 * @return faceToken 人脸唯一标志码
	 */
	public static String faceSet(String groupId, String userId, String imageBase64, String remark) {
		// 请求url
		String url = "https://aip.baidubce.com/rest/2.0/face/v3/faceset/user/add";
		try {
			Map<String, Object> map = new HashMap<>();
			map.put("group_id", groupId);
			map.put("user_id", userId);
			map.put("image_type", "BASE64");
			map.put("image", imageBase64);
			map.put("user_info", remark);

			String param = JSON.toJSON(map).toString();

			String accessToken = AuthService.getAuth();

			String result = HttpUtil.post(url, accessToken, "application/json", param);
			JSONObject parseObject = JSON.parseObject(result);
			
			if(parseObject.getString("error_code").equals("223105")) { //人脸已存在 return
				return faceDetect(imageBase64); 
			}
			Map<String, Object> resultMap = (Map<String, Object>) parseObject.get("result");
			String faceToken = (String) resultMap.get("face_token");
			return faceToken;
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
	}

	/**
	 * 人脸比对
	 * 
	 * @param imageBase64 用户人脸照片
	 * @param faceToken   人脸唯一标志码
	 * @return
	 */
	public static String faceMatch(String imageBase64, String faceToken) {
		// 请求url
		String url = "https://aip.baidubce.com/rest/2.0/face/v3/match";
		try {
			Map<String, Object> map1 = new HashMap<>();
			map1.put("image", imageBase64);
			map1.put("image_type", "BASE64");

			Map<String, Object> map2 = new HashMap<>();
			map2.put("image", faceToken);
			map2.put("image_type", "FACE_TOKEN");

			ArrayList<Map> arrayList = new ArrayList<Map>();
			arrayList.add(map1);
			arrayList.add(map2);

			String param = JSON.toJSON(arrayList).toString();

			String accessToken = AuthService.getAuth();

			String result = HttpUtil.post(url, accessToken, "application/json", param);
			JSONObject parseObject = JSON.parseObject(result);
			Map<String, Object> resultMap = (Map<String, Object>) parseObject.get("result");
			if (resultMap.get("score") != null) {
				Float score = Float.valueOf(resultMap.get("score").toString());
				if (score < 80) {
					return "fail";
				}
				return "success";
			}
			return "success";
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}

	}

	/**
	 * 人脸检测
	 * 
	 * @param imageBase64
	 * @return
	 */
	public static String faceDetect(String imageBase64) {
		// 请求url
		String url = "https://aip.baidubce.com/rest/2.0/face/v3/detect";
		try {
			Map<String, Object> map = new HashMap<>();
			map.put("image", imageBase64);
			map.put("image_type", "BASE64");

			String param = JSON.toJSON(map).toString();

			String accessToken = AuthService.getAuth();

			String result = HttpUtil.post(url, accessToken, "application/json", param);

			JSONObject parseObject = JSON.parseObject(result);
			Map<String, Object> resultMap = (Map<String, Object>) parseObject.get("result");
			List<Object> faceList = (List<Object>) resultMap.get("face_list");
			String faceToken = ((JSONObject) faceList.get(0)).get("face_token").toString();
			return faceToken;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

}
