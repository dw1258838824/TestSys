package com.ruoyi.common.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * HTTP工具类
 * 
 * 
 */
public class HttpUtils {

	private static Log log = LogFactory.getLog(HttpUtils.class);

	/**
	 * 定义编码格式 UTF-8
	 */
	public static final String URL_PARAM_DECODECHARSET_UTF8 = "UTF-8";

	/**
	 * 定义编码格式 GBK
	 */
	public static final String URL_PARAM_DECODECHARSET_GBK = "GBK";

	private static final String URL_PARAM_CONNECT_FLAG = "&";

	private static final String EMPTY = "";

	private static MultiThreadedHttpConnectionManager connectionManager = null;

	private static int connectionTimeOut = 25000;

	private static int socketTimeOut = 25000;

	private static int maxConnectionPerHost = 20;

	private static int maxTotalConnections = 20;

	private static HttpClient client;

	static {
		connectionManager = new MultiThreadedHttpConnectionManager();
		connectionManager.getParams().setConnectionTimeout(connectionTimeOut);
		connectionManager.getParams().setSoTimeout(socketTimeOut);
		connectionManager.getParams().setDefaultMaxConnectionsPerHost(
				maxConnectionPerHost);
		connectionManager.getParams().setMaxTotalConnections(
				maxTotalConnections);
		client = new HttpClient(connectionManager);
	}

	/**
	 * POST方式提交数据
	 * 
	 * @param url
	 *            待请求的URL
	 * @param params
	 *            要提交的数据
	 * @param enc
	 *            编码
	 * @return 响应结果
	 * @throws IOException
	 *             IO异常
	 */
	public static String URLPost(String url, Map<String, String> params,
			String enc) {

		String response = EMPTY;
		PostMethod postMethod = null;
		try {
			postMethod = new PostMethod(url);
			postMethod.setRequestHeader("Content-Type",
					"application/x-www-form-urlencoded;charset=" + enc);
			// 将表单的值放入postMethod中
			Set<String> keySet = params.keySet();
			for (String key : keySet) {
				String value = params.get(key);
				postMethod.addParameter(key, value);
			}
			// 执行postMethod
			int statusCode = client.executeMethod(postMethod);
			if (statusCode == HttpStatus.SC_OK) {
				
				response = postMethod.getResponseBodyAsString();
			} else {
				log.error("响应状态码 = " + postMethod.getStatusCode());
			}
		} catch (HttpException e) {
			log.error("发生致命的异常，可能是协议不对或者返回的内容有问题", e);
			e.printStackTrace();
		} catch (IOException e) {
			log.error("发生网络异常", e);
			e.printStackTrace();
		} finally {
			if (postMethod != null) {
				postMethod.releaseConnection();
				postMethod = null;
			}
		}

		return response;
	}

	/**
	 * GET方式提交数据
	 * 
	 * @param url
	 *            待请求的URL
	 * @param params
	 *            要提交的数据
	 * @param enc
	 *            编码
	 * @return 响应结果
	 * @throws IOException
	 *             IO异常
	 */
	public static String URLGet(String url, Map<String, String> params,
			String enc) {

		String response = EMPTY;
		GetMethod getMethod = null;
		StringBuffer strtTotalURL = new StringBuffer(EMPTY);

		if (strtTotalURL.indexOf("?") == -1) {
			strtTotalURL.append(url).append("?").append(getUrl(params, enc));
		} else {
			strtTotalURL.append(url).append("&").append(getUrl(params, enc));
		}
		log.debug("GET请求URL = \n" + strtTotalURL.toString());
		
		System.out.println(strtTotalURL);
		try {
			getMethod = new GetMethod(strtTotalURL.toString());
			getMethod.setRequestHeader("Content-Type",
					"application/x-www-form-urlencoded;charset=" + enc);
			// 执行getMethod
			int statusCode = client.executeMethod(getMethod);
			if (statusCode == HttpStatus.SC_OK) {
				response = getMethod.getResponseBodyAsString();
			} else {
				log.debug("响应状态码 = " + getMethod.getStatusCode());
			}
		} catch (HttpException e) {
			log.error("发生致命的异常，可能是协议不对或者返回的内容有问题", e);
			e.printStackTrace();
		} catch (IOException e) {
			log.error("发生网络异常", e);
			e.printStackTrace();
		} finally {
			if (getMethod != null) {
				getMethod.releaseConnection();
				getMethod = null;
			}
		}

		return response;
	}

	/**
	 * 据Map生成URL字符串
	 * 
	 * @param map
	 *            Map
	 * @param valueEnc
	 *            URL编码
	 * @return URL
	 */
	private static String getUrl(Map<String, String> map, String valueEnc) {

		if (null == map || map.keySet().size() == 0) {
			return (EMPTY);
		}
		StringBuffer url = new StringBuffer();
		Set<String> keys = map.keySet();
		for (Iterator<String> it = keys.iterator(); it.hasNext();) {
			String key = it.next();
			if (map.containsKey(key)) {
				String val = map.get(key);
				String str = val != null ? val : EMPTY;
				try {
					str = URLEncoder.encode(str, valueEnc);
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
				url.append(key).append("=").append(str)
						.append(URL_PARAM_CONNECT_FLAG);
			}
		}
		String strURL = EMPTY;
		strURL = url.toString();
		if (URL_PARAM_CONNECT_FLAG.equals(EMPTY
				+ strURL.charAt(strURL.length() - 1))) {
			strURL = strURL.substring(0, strURL.length() - 1);
		}

		return (strURL);
	}
	/**
	   * 向指定URL发送GET方法的请求
	   * 
	   * @param url
	   *            发送请求的URL
	   * @param param
	   *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
	   * @return URL 所代表远程资源的响应结果
	   */
	  public static String sendUrl(String url, String param) {
	      String result = "";
	      BufferedReader in = null;
	      try {
	          String urlNameString = url + "?" + param;
	          URL realUrl = new URL(urlNameString);
	          // 打开和URL之间的连接
	          URLConnection connection = realUrl.openConnection();
	          // 设置通用的请求属性
	          connection.setRequestProperty("accept", "*/*");
	          connection.setRequestProperty("connection", "Keep-Alive");
	          connection.setRequestProperty("user-agent",
	                  "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
	          // 建立实际的连接
	          connection.connect();
	          // 获取所有响应头字段
	          Map<String, List<String>> map = connection.getHeaderFields();
	          // 遍历所有的响应头字段
	          for (String key : map.keySet()) {
	              System.out.println(key + "--->" + map.get(key));
	          }
	          // 定义 BufferedReader输入流来读取URL的响应
	          in = new BufferedReader(new InputStreamReader(
	                  connection.getInputStream()));
	          String line;
	          while ((line = in.readLine()) != null) {
	              result += line;
	          }
	      } catch (Exception e) {
	          System.out.println("发送GET请求出现异常！" + e);
	          e.printStackTrace();
	      }
	      // 使用finally块来关闭输入流
	      finally {
	          try {
	              if (in != null) {
	                  in.close();
	              }
	          } catch (Exception e2) {
	              e2.printStackTrace();
	          }
	      }
	      return result;
	  }
}