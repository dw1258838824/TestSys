package com.ruoyi.project.system.student.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.google.common.collect.Lists;
import com.ruoyi.common.constant.UserConstants;
import com.ruoyi.common.utils.IdcardUtils;
import com.ruoyi.common.utils.RequestUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.system.config.service.IConfigService;
import com.ruoyi.project.system.dept.domain.Dept;
import com.ruoyi.project.system.dept.service.IDeptService;
import com.ruoyi.project.system.level.service.ITLevelService;
import com.ruoyi.project.system.student.domain.Student;
import com.ruoyi.project.system.student.service.IStudentService;
import com.ruoyi.project.system.user.domain.User;
import com.ruoyi.project.system.user.domain.UserStudent;
import com.ruoyi.project.system.user.service.IUserService;

/**
 * 学员Controller
 * 
 * @author ruoyi
 * @date 2020-06-05
 */
@Controller
@RequestMapping("/system/student")
public class StudentController extends BaseController
{
    private String prefix = "system/student";

    @Autowired
    private IStudentService studentService;
    
    @Autowired
    private IUserService userService;
    @Autowired
    private ITLevelService levelService;
    
    @Autowired
    private IDeptService deptService;
    
    @Autowired
    private IConfigService configService;

    @RequiresPermissions("system:student:view")
    @GetMapping()
    public String student(ModelMap mmap,HttpServletRequest request)
    {
		mmap.put("levels", levelService.selectTLevelList(null));
		mmap.put("notExamId", request.getParameter("examId"));
        return prefix + "/student";
    }

    /**
     * 查询学员列表
     */
    @RequiresPermissions("system:student:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(Student student)
    {
        startPage();
        List<Student> list = studentService.selectStudentList(student);
        return getDataTable(list);
    }

    /**
     * 导出学员列表
     */
    @RequiresPermissions("system:student:export")
    @Log(title = "学员", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Student student)
    {
        List<Student> list = studentService.selectStudentList(student);
        ExcelUtil<Student> util = new ExcelUtil<Student>(Student.class);
        return util.exportExcel(list, "student");
    }

    /**
     * 新增学员
     */
    @GetMapping("/add")
    public String add(ModelMap mmap)
    {
    	mmap.put("user", getSysUser());
        return prefix + "/add";
    }

    /**
     * 新增保存学员
     */
    @RequiresPermissions("system:student:add")
    @Log(title = "学员", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(Student student,User user)
    {
    	 user.setLoginName(student.getMobile());
    	 user.setPhonenumber(student.getMobile());
    	 user.setUserName(student.getStudentName());
    	 user.setRoleIds(new Long[] {100L});
    	 user.setSex(student.getSex());
    	 user.getParams().put("birthday", student.getBirthday());
    	 user.getParams().put("idcard", student.getIdcard());
    	 user.getParams().put("parentName", student.getParentName());
    	 user.getParams().put("idcardImg", student.getIdcardImg());
    	 
    	 Dept d = deptService.selectDeptById(user.getDeptId());
    	 Student stu = new Student();
    	 stu.setIdcard(student.getIdcard());
    	 Student stu2 = new Student();
    	 stu2.setIdcard(student.getMobile());
    	 if (UserConstants.USER_PHONE_NOT_UNIQUE.equals(userService.checkLoginNameUnique(student.getMobile())))  {
             return error("新增学员失败，手机号已存在");
         }else if (UserConstants.USER_PHONE_NOT_UNIQUE.equals(userService.checkPhoneUnique(user))) {
             return error("新增学员失败，手机号已存在");
         }else if (studentService.checkAnyThingUnique(stu).equals("1")) {
             return error("新增学员失败，身份证已存在");
         }else if(studentService.checkAnyThingUnique(stu2).equals("1")) {
        	 return error("新增学员失败，手机号已存在");
         }else if(null==d || null==d.getDeptId()) {
        	 return error("机构号不正确");
		 }else if(!IdcardUtils.checkIdcardAndName(student.getIdcard(), student.getStudentName())) {
        	 return error("新增学员失败，身份证与姓名不匹配");
         }
         return toAjax(userService.insertUserStudent(user));
    }

    /**
     * 修改学员
     */
    @GetMapping("/edit/{studentId}")
    public String edit(@PathVariable("studentId") Long studentId, ModelMap mmap)
    {
        Student student = studentService.selectStudentById(studentId);
        mmap.put("student", student);
        mmap.put("user", userService.selectUserById(student.getUserId()));
        return prefix + "/edit";
    }

    /**
     * 修改保存学员
     */
    @RequiresPermissions("system:student:edit")
    @Log(title = "学员", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(Student student,HttpServletRequest request)
    {
    	if(StringUtils.isNotEmpty(request.getParameter("deptId"))) {
	    	User user = new User();
	    	user.setUserId(student.getUserId());
	    	user.setDeptId(Long.parseLong(request.getParameter("deptId").toString()));
	    	userService.updateUserInfo(user);
    	}
        return toAjax(studentService.updateStudent(student));
    }

    /**
     * 删除学员
     */
    @RequiresPermissions("system:student:remove")
    @Log(title = "学员", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(studentService.deleteStudentByIds(ids));
    }

    /**
     * 删除学员人脸识别
     */
    @Log(title = "学员", businessType = BusinessType.UPDATE)
    @PostMapping( "/removeFace")
    @ResponseBody
    public AjaxResult removeFace(String ids)
    {
        return toAjax(studentService.deleteFaceStudentByIds(ids));
    }
    
    @SuppressWarnings("unchecked")
	@GetMapping("/showlevel")
    @ResponseBody
    public List<Map<String, Object>> showlevel(HttpServletRequest request, ModelMap mmap)
    {
        return studentService.selectStudentLevelInfo(RequestUtils.getRequestMap(request));
    }
    
    /**
     * 校验字段是否存在
     */
    @PostMapping("/checkAnyThingUnique")
    @ResponseBody
    public String checkAnyThingUnique(Student stu)
    {
        return studentService.checkAnyThingUnique(stu);
    }

    /**
     * 校验身份证 姓名是否一致
     */
    @GetMapping("/checkIdcardAndName")
    @ResponseBody
    public String checkIdcardAndName(HttpServletRequest request)
    {
		//检验身份证和姓名一致性
		if(IdcardUtils.checkIdcardAndName(request.getParameter("idcard"), request.getParameter("studentName"))) {
			return "success";
		}
        return "error";
    }
    

    /**
     * 修改学员科目等级
     */
    @SuppressWarnings("unchecked")
	@GetMapping("/changeLevel")
    @ResponseBody
    @RequiresPermissions("system:student:changeLevel")
    public String changeLevel(HttpServletRequest request)
    {
    	int i = studentService.updateStudentLevel(RequestUtils.getRequestMap(request));
		if(i > 0) {
			return "success";
		}
        return "error";
    }
    
    @Log(title = "学员导入", businessType = BusinessType.IMPORT)
    @RequiresPermissions("system:student:import")
    @PostMapping("/importData")
    @ResponseBody
    public AjaxResult importData(MultipartFile file, boolean updateSupport) throws Exception
    {
        ExcelUtil<UserStudent> util = new ExcelUtil<UserStudent>(UserStudent.class);
        List<UserStudent> stuList = util.importExcel(file.getInputStream());
        List<User> userList = Lists.newArrayList();
        Long [] roleIds = {100L};
        for(UserStudent s: stuList) {
        	User u = new User();
        	try {
				u.setDeptId(s.getDeptId());
				u.setRoleIds(roleIds);
				u.setLoginName(s.getMobile());
				u.setPhonenumber(s.getMobile());
				u.setUserName(s.getStudentName());
				u.setPassword(configService.selectConfigByKey("sys.user.initPassword"));
				u.setSex(s.getSex());
				u.getParams().put("idcard", s.getIdcard());
				u.getParams().put("parentName", s.getParentName());
				u.getParams().put("birthday", s.getIdcard().substring(6,10) + "-" + s.getIdcard().substring(10,12) + "-" + s.getIdcard().substring(12,14));
			} catch (Exception e) {
				e.printStackTrace();
			}
        	userList.add(u);
        }
        String message = userService.importUser(userList, updateSupport , 1);
        return AjaxResult.success(message);
    }
    
    @RequiresPermissions("system:student:view")
    @GetMapping("/importTemplate")
    @ResponseBody
    public AjaxResult importTemplate()
    {
        ExcelUtil<UserStudent> util = new ExcelUtil<UserStudent>(UserStudent.class);
        return util.importTemplateExcel("学生数据");
    }
}
