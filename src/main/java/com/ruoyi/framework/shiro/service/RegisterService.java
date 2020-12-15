package com.ruoyi.framework.shiro.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.constant.UserConstants;
import com.ruoyi.common.utils.MessageUtils;
import com.ruoyi.framework.manager.AsyncManager;
import com.ruoyi.framework.manager.factory.AsyncFactory;
import com.ruoyi.project.system.api.service.ClassInService;
import com.ruoyi.project.system.dept.domain.Dept;
import com.ruoyi.project.system.dept.service.IDeptService;
import com.ruoyi.project.system.student.domain.Student;
import com.ruoyi.project.system.student.service.IStudentService;
import com.ruoyi.project.system.user.domain.User;
import com.ruoyi.project.system.user.domain.UserRole;
import com.ruoyi.project.system.user.mapper.UserRoleMapper;
import com.ruoyi.project.system.user.service.IUserService;
import com.testsys.common.Cache;
import com.testsys.utils.ApiResult;

/**
 * 注册校验方法
 * 
 * @author ruoyi
 */
@Component
@Service
public class RegisterService
{

	private static final Logger log = LoggerFactory.getLogger(RegisterService.class);
    @Autowired
    private IUserService userService;
    @Autowired
    private IStudentService studentService;
    @Autowired
    private UserRoleMapper userRoleMapper;
    @Autowired
    private ClassInService classInService;
    @Autowired
    private IDeptService deptService;

    @Value("${student.roleIds}")
    private String roleIds;
    
    /**
     * 注册
     */
    @Transactional
    public String register(User user)
    {
        String msg = "", username = user.getLoginName(), password = user.getPassword();
        String captcha = String.valueOf(Cache.get(user.getLoginName()));
        Dept d = deptService.selectDeptById(user.getDeptId());
        if(null == captcha) {
        	msg = "验证码已过期";
        }
        if(!captcha.equals(user.getPhoneCode())) {
        	msg = "验证码错误";
        }
		/*
		 * if
		 * (!StringUtils.isEmpty(ServletUtils.getRequest().getAttribute(ShiroConstants.
		 * CURRENT_CAPTCHA))) { msg = "验证码错误"; }
		 */
        else if (StringUtils.isEmpty(username))
        {
            msg = "用户名不能为空";
        }
        else if (StringUtils.isEmpty(password))
        {
            msg = "用户密码不能为空";
        }
        else if (password.length() < UserConstants.PASSWORD_MIN_LENGTH
                || password.length() > UserConstants.PASSWORD_MAX_LENGTH)
        {
            msg = "密码长度必须在5到20个字符之间";
        }
        else if (username.length() < UserConstants.USERNAME_MIN_LENGTH
                || username.length() > UserConstants.USERNAME_MAX_LENGTH)
        {
            msg = "账户长度必须在2到20个字符之间";
        }
        else if (UserConstants.USER_NAME_NOT_UNIQUE.equals(userService.checkLoginNameUnique(username)))
        {
            msg = "保存用户'" + username + "'失败，注册账号已存在";
        }else if(null==d || null==d.getDeptId()) {
        	msg = "机构号不正确";
		}
        else
        {
        	String pwd = user.getPassword();
            boolean regFlag = userService.registerUser(user);
            if (!regFlag)
            {
                msg = "注册失败,请联系系统管理人员";
            }
            else
            {
            	//添加用户
            	Student student = user.getStudent() == null ? new Student():user.getStudent();
            	student.setUserId(user.getUserId());
            	student.setMobile(user.getLoginName());
				try {
					Map<String, String> params = Maps.newHashMap();
					params.put("telephone", user.getLoginName());
					params.put("nickname",StringUtils.isEmpty(student.getStudentName())?user.getLoginName():student.getStudentName());
					params.put("password",pwd);
					params.put("addToSchoolMember","2");
					String json = classInService.classInHttp("register", params);//调用classIn注册
					JSONObject obj = JSONObject.parseObject(json);
					Integer errno = obj.getJSONObject("error_info").getInteger("errno");
					if(null!=errno && (errno==1 || errno==135)) {//注册成功或者已存在
						Long uid = obj.getLong("data");//添加用户获得uid
						student.setUid(uid);
						Map<String, String> tparams = Maps.newHashMap();
						tparams.put("teacherAccount", user.getLoginName());
						tparams.put("teacherName", StringUtils.isEmpty(student.getStudentName())?user.getLoginName():student.getStudentName());
						classInService.classInHttp("addTeacher", tparams);//调用classIn添加老师
					}else {
						log.error("调用classIn注册失败",json);
					}
				} catch (Exception e) {
					log.error("调用classIn注册失败",e);
				}

            	studentService.insertStudent(student);
            	// 新增用户与角色管理
            	if(com.ruoyi.common.utils.StringUtils.isNotEmpty(roleIds)) {
            		List<UserRole> list = new ArrayList<UserRole>();
            		String[] ro = roleIds.split(",");
                    for (String roleId : ro){
                        UserRole ur = new UserRole();
                        ur.setUserId(user.getUserId());
                        ur.setRoleId(Long.parseLong(roleId));
                        list.add(ur);
                    }
                    if (list.size() > 0){
                        userRoleMapper.batchUserRole(list);
                    }
            	}
            	//注册成功后删除验证码
            	Cache.remove(user.getLoginName());
                AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.REGISTER, MessageUtils.message("user.register.success")));
            }
        }
        return msg;
    }
}
