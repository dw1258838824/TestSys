package com.ruoyi.project.system.user.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.Lists;
import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.constant.NoticeConstants;
import com.ruoyi.common.utils.CacheUtils;
import com.ruoyi.common.utils.ServletUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.domain.AjaxResult.Type;
import com.ruoyi.project.system.config.domain.Config;
import com.ruoyi.project.system.config.service.IConfigService;
import com.ruoyi.project.system.dept.domain.Dept;
import com.ruoyi.project.system.notice.domain.Notice;
import com.ruoyi.project.system.notice.service.INoticeService;
import com.ruoyi.project.system.student.domain.Student;
import com.ruoyi.project.system.student.service.IStudentService;
import com.ruoyi.project.system.user.domain.User;
import com.ruoyi.project.system.user.service.IUserService;
import com.testsys.common.Cache;

/**
 * 登录验证
 * 
 * @author ruoyi
 */
@Controller
@SuppressWarnings("unused")
public class LoginController extends BaseController
{

    @Autowired
    private IStudentService studentService;
    @Autowired
    private INoticeService noticeService;
    @Autowired
    private IConfigService configService;

    @Autowired
    private IUserService userService;

    @GetMapping("/login")
    public String login(HttpServletRequest request, HttpServletResponse response,ModelMap mmap)
    {
        // 如果是Ajax请求，返回Json字符串。
        if (ServletUtils.isAjaxRequest(request))
        {
            return ServletUtils.renderString(response, "{\"code\":\"1\",\"msg\":\"未登录或登录超时。请重新登录\"}");
        }
//        List<Notice> list = null;
//        if(null!=NoticeConstants.NOTICES) {
//        	list = NoticeConstants.NOTICES;
//        }else {
//        	list = noticeService.selectNoticeList(null);
//        	NoticeConstants.NOTICES = list;
//        }
//        if(list.size()>10) {
//        	list = list.subList(0, 10);
//        }
        //mmap.put("notices", list);
        //mmap.put("callMeInfo", CacheUtils.get(getConfigCacheName(), getConfigCacheKey("call_me_info")));
        //mmap.put("friendLines", CacheUtils.get(getConfigCacheName(), getConfigCacheKey("friend_lines")));
        //getLoginHTMLCache(mmap);
       	mmap.put("flag", request.getParameter("flag"));
        return "adminlogin";
    }
    
    
    public static void getLoginHTMLCache(ModelMap mmap) {
    	mmap.put("projectBlock",      CacheUtils.get(getConfigCacheName(), getConfigCacheKey("projectBlock")));
    	mmap.put("projectBlockText",  CacheUtils.get(getConfigCacheName(), getConfigCacheKey("projectBlockText")));
    	mmap.put("projectBlockName1", CacheUtils.get(getConfigCacheName(), getConfigCacheKey("projectBlockName1")));
    	mmap.put("projectBlockName2", CacheUtils.get(getConfigCacheName(), getConfigCacheKey("projectBlockName2")));
    	mmap.put("projectBlockName3", CacheUtils.get(getConfigCacheName(), getConfigCacheKey("projectBlockName3")));
    	mmap.put("projectBlockName4", CacheUtils.get(getConfigCacheName(), getConfigCacheKey("projectBlockName4")));
    	mmap.put("teacherAuth",       CacheUtils.get(getConfigCacheName(), getConfigCacheKey("teacherAuth")));
    	mmap.put("teacherAuthText",   CacheUtils.get(getConfigCacheName(), getConfigCacheKey("teacherAuthText")));
    	mmap.put("teacherAuthTitle1", CacheUtils.get(getConfigCacheName(), getConfigCacheKey("teacherAuthTitle1")));
    	mmap.put("teacherAuthTitle2", CacheUtils.get(getConfigCacheName(), getConfigCacheKey("teacherAuthTitle2")));
    	mmap.put("teacherAuthTitle3", CacheUtils.get(getConfigCacheName(), getConfigCacheKey("teacherAuthTitle3")));
    	mmap.put("teacherAuthTitle4", CacheUtils.get(getConfigCacheName(), getConfigCacheKey("teacherAuthTitle4")));
    	mmap.put("teacherAuthTitle5", CacheUtils.get(getConfigCacheName(), getConfigCacheKey("teacherAuthTitle5")));
    	mmap.put("teacherAuthTitle6", CacheUtils.get(getConfigCacheName(), getConfigCacheKey("teacherAuthTitle6")));
    	mmap.put("teacherAuthInfo1",  CacheUtils.get(getConfigCacheName(), getConfigCacheKey("teacherAuthInfo1")));
    	mmap.put("teacherAuthInfo2",  CacheUtils.get(getConfigCacheName(), getConfigCacheKey("teacherAuthInfo2")));
    	mmap.put("teacherAuthInfo3",  CacheUtils.get(getConfigCacheName(), getConfigCacheKey("teacherAuthInfo3")));
    	mmap.put("teacherAuthInfo4",  CacheUtils.get(getConfigCacheName(), getConfigCacheKey("teacherAuthInfo4")));
    	mmap.put("teacherAuthInfo5",  CacheUtils.get(getConfigCacheName(), getConfigCacheKey("teacherAuthInfo5")));
    	mmap.put("teacherAuthInfo6",  CacheUtils.get(getConfigCacheName(), getConfigCacheKey("teacherAuthInfo6")));
    	mmap.put("projectIng", 		  CacheUtils.get(getConfigCacheName(), getConfigCacheKey("projectIng")));
    	mmap.put("projectIngTitle1",  CacheUtils.get(getConfigCacheName(), getConfigCacheKey("projectIngTitle1")));
    	mmap.put("projectIngTitle2",  CacheUtils.get(getConfigCacheName(), getConfigCacheKey("projectIngTitle2")));
    	mmap.put("projectIngTitle3",  CacheUtils.get(getConfigCacheName(), getConfigCacheKey("projectIngTitle3")));
    	mmap.put("projectIngText1",   CacheUtils.get(getConfigCacheName(), getConfigCacheKey("projectIngText1")));
    	mmap.put("projectIngText2",   CacheUtils.get(getConfigCacheName(), getConfigCacheKey("projectIngText2")));
    	mmap.put("projectIngText3",   CacheUtils.get(getConfigCacheName(), getConfigCacheKey("projectIngText3")));
    }
    /**
     * 获取cache name
     * 
     * @return 缓存名
     */
	public static String getConfigCacheName()
    {
        return Constants.SYS_CONFIG_CACHE;
    }

    /**
     * 设置cache key
     * 
     * @param configKey 参数键
     * @return 缓存键key
     */
	public static String getConfigCacheKey(String configKey)
    {
        return Constants.SYS_CONFIG_KEY + configKey;
    }
    
    @PostMapping("/login")
    @ResponseBody
    public AjaxResult ajaxLogin(String flag , String username, String password, Boolean rememberMe)
    {
    	if(username.length()==18) {//身份证登录
    		username = studentService.selectStudentByIdcard(username).getMobile();
    	}
        UsernamePasswordToken token = new UsernamePasswordToken(username, password, rememberMe);
        Subject subject = SecurityUtils.getSubject();
        try
        {
        	subject.login(token);
        	User user = getSysUser();
        	if(flag.equals("") && "01".equals(user.getUserType())) {//学生登录
        		Student student = studentService.selectUserById(getUserId());
        		user.setStudent(student);
        		setSysUser(user);
        		return success();
        	}else if(flag.equals("root") && "00".equals(user.getUserType())) {
        		setSysUser(user);
        		return success();
        	}else {
        		String msg = "学员请用学生入口登录，后台请用后台入口登录";
        		return error(Type.WARN,msg);
        	}
	            
        }
        catch (AuthenticationException e)
        {
            String msg = "用户或密码错误";
            if (StringUtils.isNotEmpty(e.getMessage()))
            {
                msg = e.getMessage();
            }
            return error(msg);
        }
    }

    @GetMapping("/unauth")
    public String unauth()
    {
        return "error/unauth";
    }
    

    @GetMapping("/forget")
    public String forget()
    {
        return "forget";
    }
    
    @PostMapping("/forget")
    @ResponseBody
    public AjaxResult ajaxRegister(User user)
    {
    	String msg = null;
    	try {
	    	User ouser = userService.selectUserByLoginName(user.getLoginName());
	    	if(null==ouser || null==ouser.getUserId())
	    		msg = "该手机号未注册";
	    	else {
	    		String captcha = String.valueOf(Cache.get(user.getLoginName()));
	            if(null == captcha) {
	            	msg = "验证码已过期";
	            }else if(!captcha.equals(user.getPhoneCode())) {
	            	msg = "验证码错误";
	            }else {
			    	ouser.setPassword(user.getPassword());
					int row = userService.resetUserPwd(ouser);
	            }
	    	}
    	} catch (Exception e) {
			e.printStackTrace();
			msg = "重置失败";
		}
        return StringUtils.isEmpty(msg) ? success() : error(msg);
    }
    
    public static void main(String[] args) {
		List<String> arrayList = Lists.newArrayList();
		arrayList.add("a");
		arrayList.add("b");
		arrayList.add("c");
		arrayList.add("d");
		arrayList = arrayList.subList(0, 2);
		System.out.println(arrayList);
	}
}
