package com.ruoyi.common.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.net.UnknownHostException;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;

/**
 * 处理身份证 姓名一致性
 * 
 * @author ruoyi
 */
public class IdcardUtils
{
	 public static String host = "https://idcardcert.market.alicloudapi.com"; //【1】 请求地址 支持http 和 https 及 WEBSOCKET
	 public static String path = "/idCardCert";// 【2】后缀
	 public static String appcode = "a0c865d7acbc44c3b80c0122426bcbd4"; //TODO 【3】开通服务后 买家中心-查看AppCode
	 public static Boolean checkIdcardAndName(String idCard,String name) {
		 	Boolean isPass = false;
	        try {
	            String urlSend = host + path + "?idCard=" + idCard + "&name="+ URLEncoder.encode(name, "UTF-8");// 【5】拼接请求链接
	            URL url = new URL(urlSend);
	            HttpURLConnection httpURLCon = (HttpURLConnection) url.openConnection();
	            httpURLCon.setRequestProperty("Authorization", "APPCODE " + appcode);// 格式Authorization:APPCODE (中间是英文空格)
	            int httpCode = httpURLCon.getResponseCode();
	            if (httpCode == 200) {
	                String json = read(httpURLCon.getInputStream());
	                System.out.println("正常请求计费(其他均不计费)");
	                System.out.println("获取返回的json：");
	                System.out.print(json);
	                JSONObject j = JSONObject.parseObject(json);
	                if(j.getString("status").equals("01")) {//如果通过
	                	isPass = true;
	                }
	            } else {
	                Map<String, List<String>> map = httpURLCon.getHeaderFields();
	                String error = map.get("X-Ca-Error-Message").get(0);
	                if (httpCode == 400 && error.equals("Invalid AppCode `not exists`")) {
	                    System.out.println("AppCode错误");
	                } else if (httpCode == 400 && error.equals("Invalid Url")) {
	                    System.out.println("请求的 Method、Path 或者环境错误");
	                } else if (httpCode == 400 && error.equals("Invalid Param Location")) {
	                    System.out.println("参数错误");
	                } else if (httpCode == 403 && error.equals("Unauthorized")) {
	                    System.out.println("服务未被授权（或URL和Path不正确）");
	                } else if (httpCode == 403 && error.equals("Quota Exhausted")) {
	                    System.out.println("套餐包次数用完 ");
	                } else {
	                    System.out.println("参数名错误 或 其他错误");
	                    System.out.println(error);
	                }
	            }

	        } catch (MalformedURLException e) {
	            System.out.println("URL格式错误");
	        } catch (UnknownHostException e) {
	            System.out.println("URL地址错误");
	        } catch (Exception e) {
	            // 打开注释查看详细报错异常信息
	            // e.printStackTrace();
	        }
	        return isPass;
	    }

	    /*
	     * 读取返回结果
	     */
	    private static String read(InputStream is) throws IOException {
	        StringBuffer sb = new StringBuffer();
	        BufferedReader br = new BufferedReader(new InputStreamReader(is));
	        String line = null;
	        while ((line = br.readLine()) != null) {
	            line = new String(line.getBytes(), "utf-8");
	            sb.append(line);
	        }
	        br.close();
	        return sb.toString();
	    }
}
