package com.ruoyi.project.system.api.controller;

import java.io.IOException;
import java.util.Map;
import java.util.UUID;
import com.testsys.common.Cache;
import com.testsys.utils.ApiResult;
import com.ruoyi.framework.configure.StudentSession;
import com.ruoyi.project.system.api.service.WeChatService;
import com.ruoyi.project.system.student.domain.Student;
import com.ruoyi.project.system.student.service.IStudentService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/wechat")
public class WeChatApi {

	@Autowired
	private WeChatService weChatService;
	@Autowired
	private IStudentService studentService;
	@Value("${wechat.domainName}")
	private String domainName;
	@Value("${wechat.successUrl}")
	private String successUrl;
	@Value("${wechat.accountInfo}")
	private String accountInfo;
	@Value("${wechat.errorUrl}")
	private String errorUrl;

	@GetMapping("/login")
	public void login(HttpServletResponse response) throws IOException {
		response.sendRedirect(weChatService.login());
	}

    @GetMapping("/loginCallBack")
	public void LoginCallBack(String code, String state,HttpServletResponse response) throws IOException{
		Map<String,Object> token = weChatService.getWeChatUserInfo(code);
		//如果获取到微信用户信息
		if(null != token){
			Student stu = new Student();
			stu.setWechatId(token.get("openid").toString());
			Student student = studentService.selectStudentByWeChatId(stu);
			//如果当前微信用户没有绑定账号跳转至绑定账号
			if(null == student){
				//有效期30分钟
				String key = UUID.randomUUID().toString();
				Cache.put(key, token,1800000);
				response.sendRedirect(accountInfo + "/" + key);
				return;
			}
			//已绑定账号直接跳转至页面
			StudentSession studentSession = new StudentSession();
			studentSession.setStudentId(student.getStudentId());
			studentSession.setWechatId(student.getWechatId());
			studentSession.createWebToken(domainName,response);
			response.sendRedirect(successUrl);
		}else{
			response.sendRedirect(errorUrl);
		}
    }

    @GetMapping("/isLogin")
    public ApiResult isLogin(HttpServletRequest request){
		StudentSession ss = StudentSession.getStudentSession(request);
		if(null == ss || StringUtils.isEmpty(ss.getWechatId()) || null == ss.getStudentId()){
			return new ApiResult(403,"未登录");
		}
		return new ApiResult(200,"已登录",studentService.selectStudentById(ss.getStudentId()));
	}
}
