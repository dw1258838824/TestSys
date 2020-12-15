package com.testsys.common;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import com.testsys.wxpay.WXPayUtil;

/**
 * 微信支付
 * 
 * @author TestSys
 *
 */
public class UnionPayOfWXPay {
	// 统一下单URL
	private static final String UNIFIEDORDER_URL = "https://api.mch.weixin.qq.com/pay/unifiedorder";
	// API_KEY
	private static final String API_KEY = "shaoerzhinengshuipingkaoshi12345";
	// 公众号id
	private static final String APP_ID = "wx0287d79648e296bd";
	// 商户号
	private static final String MCH_ID = "1508704291";

	/**
	 * 统一下单
	 * 
	 * @param tranType       交易类型1-公众号支付（JSAPI）；2-二维码支付（NATIVE ）
	 * @param openid         openid（JSAPI支付方式必填项）
	 * @param proInfor       商品描述
	 * @param outTradeNo     商户订单号
	 * @param productId      商品ID（NATIVE支付方式必填项）
	 * @param totalFee       商品标价
	 * @param spbillCreateIp 终端IP地址
	 * @param notifyURL      回调URL
	 * @return
	 * @throws Exception
	 */
	public static Map<String, String> unifiedOrder(String tranType, String openid, String outTradeNo, String productId,
			String proInfor, String totalFee, String spbillCreateIp, String notifyURL,String timeExpire) {
		Map<String, String> repMap = new HashMap<String, String>();
		try {
			Map<String, String> configMap = new HashMap<String, String>();
			configMap.put("appid", APP_ID); // 公众账号ID
			configMap.put("body", proInfor); // 商品描述
			configMap.put("device_info", "WEB"); // 设备号
			configMap.put("mch_id", MCH_ID); // 商户号
			configMap.put("nonce_str", WXPayUtil.generateNonceStr()); // 随机字符串
			// String outTradeNo = UUID.randomUUID().toString().replaceAll("-", "");
			if ("1".equals(tranType)) {
				configMap.put("trade_type", "JSAPI"); // 交易类型，JSAPI -JSAPI支付、NATIVE -Native支付、APP -APP支付
				configMap.put("out_trade_no", outTradeNo); // 商户订单号
				configMap.put("openid", openid); // openid
			}
			if ("2".equals(tranType)) {
				configMap.put("trade_type", "NATIVE"); // 交易类型，JSAPI -JSAPI支付、NATIVE -Native支付、APP -APP支付
				configMap.put("out_trade_no", outTradeNo); // 商户订单号
				configMap.put("product_id", productId); // 商品ID
			}
			configMap.put("spbill_create_ip", spbillCreateIp); // 终端IP
			configMap.put("total_fee", totalFee); // 标价金额
			configMap.put("notify_url", notifyURL); // 通知地址
			configMap.put("time_expire", timeExpire); // 订单失效时间
			String signMD5 = WXPayUtil.generateSignature(configMap, API_KEY);
			configMap.put("sign", signMD5); // 签名
			String configStr = WXPayUtil.mapToXml(configMap);
			HttpHeaders requestHeader = new HttpHeaders();
			requestHeader.setContentType(MediaType.APPLICATION_XML);
			HttpEntity<String> requestEntity = new HttpEntity<>(configStr, requestHeader);
			RestTemplate restTemplate = new RestTemplate();
			restTemplate.getMessageConverters().set(1, new StringHttpMessageConverter(StandardCharsets.UTF_8));
			ResponseEntity<String> postForEntity = restTemplate.postForEntity(UNIFIEDORDER_URL, requestEntity,
					String.class);
			Map<String, String> resultMap = WXPayUtil.xmlToMap(postForEntity.getBody());
			if ("SUCCESS".equals(resultMap.get("return_code"))) {
				if ("SUCCESS".equals(resultMap.get("result_code"))) {
					// 创建订单成功，返回订单信息
					repMap.put("result_code", "SUCCESS");
					repMap.put("prepay_id", resultMap.get("prepay_id")); // 预支付交易会话ID
					repMap.put("code_url", resultMap.get("code_url")); // 二维码链接
				} else {
					// 创建订单失败，返回失败原因
					repMap.put("result_code", "FAIL");
					repMap.put("return_msg", resultMap.get("err_code_des"));
				}
			} else {
				// 请求失败，返回错误信息
				repMap.put("result_code", "FAIL");
				repMap.put("return_msg", resultMap.get("return_msg"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return repMap;
	}

}
