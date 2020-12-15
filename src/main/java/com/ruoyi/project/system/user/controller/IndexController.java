package com.ruoyi.project.system.user.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import com.ruoyi.common.constant.NoticeConstants;
import com.ruoyi.framework.config.RuoYiConfig;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.project.system.config.service.IConfigService;
import com.ruoyi.project.system.menu.domain.Menu;
import com.ruoyi.project.system.menu.service.IMenuService;
import com.ruoyi.project.system.notice.domain.Notice;
import com.ruoyi.project.system.notice.service.INoticeService;
import com.ruoyi.project.system.role.domain.Role;
import com.ruoyi.project.system.student.domain.Student;
import com.ruoyi.project.system.student.service.IStudentService;
import com.ruoyi.project.system.user.domain.User;

/**
 * 首页 业务处理
 * 
 * @author ruoyi
 */
@Controller
public class IndexController extends BaseController
{
    @Autowired
    private IMenuService menuService;

    @Autowired
    private IConfigService configService;

    @Autowired
    private RuoYiConfig ruoYiConfig;
    
    @Autowired
    private IStudentService studentService;
    @Autowired
    private INoticeService noticeService;

    // 系统首页
    @GetMapping("/index")
    public String index(ModelMap mmap,HttpServletRequest request)
    {
        // 取身份信息
        User user = getSysUser();
        //如果是注册用户(即学生)
        if(user.getUserType().equals("01")){
        	Student student = studentService.selectUserById(user.getUserId());
        	if(null==student || null==student.getStudentId()) {
        		return "adminlogin";
        	}
        	//如果未设置人脸-跳转至设置人脸
        	if(null==student.getFaceToken() || StringUtils.isEmpty(student.getFaceToken())) {
        		return "system/computer/faceAuth";
        	}
        	//如果没有填写完整信息--跳转填写
        	if(null==student.getIdcard() || StringUtils.isEmpty(student.getIdcard())
        			|| StringUtils.isEmpty(student.getSex())
                    || StringUtils.isEmpty(student.getStudentName())
                    || StringUtils.isEmpty(student.getMobile())
                    || StringUtils.isEmpty(student.getParentName())
                    || StringUtils.isEmpty(student.getIdcardImg())
        			|| null == student.getBirthday()) {
        		user.setStudent(student);
        		mmap.put("user", user);
        		mmap.addAttribute("isRefresh",false);
        		return "system/user/profile/profileStudent";
        	}
        }
     // 根据用户id取出菜单
        List<Menu> menus = menuService.selectMenusByUser(user);
        mmap.put("menus", menus);
        mmap.put("user", user);
        mmap.put("sideTheme", configService.selectConfigByKey("sys.index.sideTheme"));
        mmap.put("skinName", configService.selectConfigByKey("sys.index.skinName"));
        mmap.put("copyrightYear", ruoYiConfig.getCopyrightYear());
        mmap.put("demoEnabled", ruoYiConfig.isDemoEnabled());
        List<Role> r = user.getRoles();
        String rolesStr = "";
        for(Role role : r) {
        	rolesStr += "|"+role.getRoleName();
        }
        mmap.put("rolesStr", rolesStr);
        mmap.put("flag", request.getParameter("flag"));
        return "index";
    }

    // 切换主题
    @GetMapping("/system/switchSkin")
    public String switchSkin(ModelMap mmap)
    {
        return "skin";
    }

    // 系统介绍
    @GetMapping("/system/main")
    public String main(ModelMap mmap)
    {
        mmap.put("version", ruoYiConfig.getVersion());
        List<Notice> list = null;
        if(null!=NoticeConstants.NOTICES) {
        	list = NoticeConstants.NOTICES;
        }else {
        	list = noticeService.selectNoticeList(null);
        	NoticeConstants.NOTICES = list;
        }
        mmap.put("notices", list);
        return "main";
    }
}
