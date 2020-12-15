package com.ruoyi.project.system.api.controller;

import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ruoyi.common.utils.IdcardUtils;
import com.ruoyi.framework.configure.StudentSession;
import com.ruoyi.framework.shiro.service.RegisterService;
import com.ruoyi.project.system.dept.domain.Dept;
import com.ruoyi.project.system.dept.service.IDeptService;
import com.ruoyi.project.system.student.domain.Student;
import com.ruoyi.project.system.student.service.IStudentService;
import com.ruoyi.project.system.user.domain.User;
import com.testsys.common.Cache;
import com.testsys.utils.ApiResult;

@RestController
@RequestMapping("/api/student")
public class StudentApi {

    @Autowired
    private IStudentService studentService;
    @Autowired
    private RegisterService registerService;
    @Autowired
    private IDeptService deptService;

    @Value("${wechat.domainName}")
    private String domainName;

    @GetMapping("/update")
    public ApiResult updateStudentInfo(String key, String phone,String phoneCode, HttpServletResponse response){
        String captcha = Cache.get(phone).toString();
        Map<String,Object> token = (Map)Cache.get(key);
        String weChatId = token.get("openid").toString();
        if(StringUtils.isEmpty(weChatId)){
            return new ApiResult(500,"微信登陆授权信息有误");
        }else if(StringUtils.isEmpty(captcha) || !captcha.equals(phoneCode)){
            return new ApiResult(500,"验证码错误");
        }
        Student student = studentService.selectStudentByMobile(phone);
        if(null == student){
            return new ApiResult(500,"用户不存在");
        }else if(StringUtils.isNotEmpty(student.getWechatId())){
            return new ApiResult(500,"当前账号已绑定其他微信账户");
        }
        student.setWechatId(weChatId);
        studentService.updateWeChatIdByStudent(student);
        Cache.remove(key);
        Cache.remove(phone);
        student = studentService.selectStudentByMobile(phone);
        StudentSession ss = new StudentSession();
        ss.setStudentId(student.getStudentId());
        ss.setWechatId(student.getWechatId());
        ss.createWebToken(domainName,response);
        return new ApiResult(200,"成功");
    }

    @PostMapping("/registered")
    public ApiResult registered(@RequestBody Student student, HttpServletResponse response){
    	User user = new User();
    	try {
			user.setDeptId(Long.parseLong(student.getParams().get("deptId").toString()));
			Dept d = deptService.selectDeptById(user.getDeptId());
			if(null==d || null==d.getDeptId()) {
				return new ApiResult(500,"机构号不正确");
			}
		} catch (Exception e) {
		}
        String key = student.getParams().get("key").toString();
        String password = student.getParams().get("password").toString();
        //查询用户是否已经存在
        Student sd = studentService.selectStudentByMobile(student.getMobile());
        if(null != sd){
            return new ApiResult(500,"用户已存在");
        }
        //从内存里面获取微信openid和短信验证码
        Map<String,Object> token = (Map) Cache.get(key);
        student.setWechatId(token.get("openid").toString());
        //校验
        if(StringUtils.isEmpty(token.get("openid").toString())){
            return new ApiResult(500,"微信登陆授权信息有误");
        }else if(StringUtils.isEmpty(password)){
            return new ApiResult(500,"请输入密码");
        }
        user.setLoginName(student.getMobile());
        user.setPassword(password);
        user.setPhonenumber(student.getMobile());
        user.setSex(student.getSex());
        user.setUserType("01");
        user.setStatus("0");
        user.setUserName(student.getStudentName());
        user.setPhoneCode(student.getParams().get("code").toString());
        user.setRemark("学员");
        user.setStudent(student);
    	
        String msg = registerService.register(user);
        if(StringUtils.isEmpty(msg)) {
            StudentSession ss = new StudentSession();
            ss.setStudentId(student.getStudentId());
            ss.setWechatId(student.getWechatId());
            ss.createWebToken(domainName,response);
            Cache.remove(key);
            return new ApiResult(200, "成功");
        }
        return new ApiResult(500, msg);
    }
    
    @PostMapping("/checkIdcardAndName")
    public ApiResult checkIdcardAndName(@RequestBody Student student, HttpServletResponse response){
    	//检验身份证和姓名一致性
		if(IdcardUtils.checkIdcardAndName(student.getIdcard(), student.getStudentName())) {
			return new ApiResult(200, "success");
		}
        return new ApiResult(500, "error");
    }
    
    
}
