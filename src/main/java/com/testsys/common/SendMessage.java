package com.testsys.common;

import java.util.HashMap;
import java.util.Map;

import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.sms.SmsClient;
import com.baidubce.services.sms.SmsClientConfiguration;
import com.baidubce.services.sms.model.SendMessageV2Request;
import com.baidubce.services.sms.model.SendMessageV2Response;
import com.baidubce.services.sms.model.SendMessageV3Request;
import com.baidubce.services.sms.model.SendMessageV3Response;

/**
 * 发送短信（by:百度简单消息服务SMS）
 * 
 * @author TestSys
 *
 */
public class SendMessage {

	// SMS服务域名，可根据环境选择具体域名
	public static final String endPoint = "http://sms.bj.baidubce.com";
	public static final String endPointV3 = "http://smsv3.bj.baidubce.com";
	// 发送账号安全认证的Access Key ID
	public static final String accessKeyId = "b2d51702bc2b4791b6b047625dfedfde";
	// 发送账号安全认证的Secret Access Key
	public static final String secretAccessKy = "e96e9e5b54c6482cafd8d5dbc4cd4cf9";

	public static String sendMessageOld(String phoneNumber) {
		// ak、sk等config
		SmsClientConfiguration config = new SmsClientConfiguration();
		config.setCredentials(new DefaultBceCredentials(accessKeyId, secretAccessKy));
		config.setEndpoint(endPoint);

		// 实例化发送客户端
		SmsClient smsClient = new SmsClient(config);

		// 定义请求参数
		String invokeId = "80V3tDbF-2w5b-05mJ"; // 发送使用签名的调用ID
		// String phoneNumber = "13302917698"; 	// 要发送的手机号码(只能填写一个手机号)
		String templateCode = "smsTpl:76759795-3408-42b9-8ef0-30837d511ef4"; // 本次发送使用的模板Code
		Map<String, String> vars = new HashMap<String, String>(); // 若模板内容为：您的验证码是${code},在${time}分钟内输入有效
		int authCode = (int) ((Math.random() * 9 + 1) * 100000);
		vars.put("code", String.valueOf(authCode));

		// 实例化请求对象
		SendMessageV2Request request = new SendMessageV2Request();
		request.withInvokeId(invokeId).withPhoneNumber(phoneNumber).withTemplateCode(templateCode).withContentVar(vars);
		@SuppressWarnings("deprecation")
		SendMessageV2Response response = smsClient.sendMessage(request);

		// 解析请求响应 response.isSuccess()为true 表示成功
		if (response != null && response.isSuccess()) {
			return String.valueOf(authCode);
		} else {
			return "";
		}
	}

	/**
	 * 发送模板消息
	 * @param phoneNumber 手机号
	 * @param signatureId 本次发送使用的签名Id
	 * @param templateCode 本次发送使用的模板Code
	 * @return
	 */
	public static String sendModalMessage(String phoneNumber,String signatureId,String templateCode,Map<String,String> parm) {
		SmsClientConfiguration config = new SmsClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(accessKeyId, secretAccessKy));
        config.setEndpoint(endPointV3);
        SmsClient client = new SmsClient(config);

        SendMessageV3Request request = new SendMessageV3Request();
        request.setMobile(phoneNumber);
        request.setSignatureId(signatureId);
        request.setTemplate(templateCode);
        request.setContentVar(parm);
        SendMessageV3Response response = client.sendMessage(request);
        // 解析请求响应 response.isSuccess()为true 表示成功
        if (response != null && response.isSuccess()) {
        	if(templateCode.equals("sms-tmpl-aofgFT33729")) {
        		return parm.get("code");
        	}
        	return "success";
        } else {
        	return "error";
        }
	}
	/**
	 * 发送验证码
	 * @param phone
	 * @param parm
	 * @return
	 */
	public static String sendMessage(String phone) {
		Map<String, String> vars = new HashMap<String, String>(); // 若模板内容为：您的验证码是${code},在${time}分钟内输入有效
		int authCode = (int) ((Math.random() * 9 + 1) * 100000);
		vars.put("code", String.valueOf(authCode));
		return sendModalMessage(phone,"sms-sign-XgObhj79638","sms-tmpl-aofgFT33729",vars);//sms-sign-AkuSgA20336
	}
	
	/**
	 * 发送考试提醒
	 * @param phone
	 * @param parm
	 * @return
	 */
	public static String sendExamMessage(String phone,Map<String,String> parm) {
		return sendModalMessage(phone,"sms-sign-XgObhj79638","sms-tmpl-YboqaX44569",parm);
	}
	
	public static void main(String[] args) {
//		Map<String,String> parm = Maps.newHashMap();
//		parm.put("examTitle", "考试标题");
//		parm.put("beginTime", "2020-07-30 20:12:00");
        System.out.println(sendMessage("13923871947"));
	}
}
