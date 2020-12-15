package com.ruoyi.project.system.computer.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.system.register.domain.TExamRegister;
import com.ruoyi.project.system.register.service.ITExamRegisterService;
import com.ruoyi.project.system.student.service.IStudentService;

@Controller
@RequestMapping("/student/register")
public class ExamRegisterApi extends BaseController {
	
	private String prefix = "system/computer";
	
	@Autowired
    private ITExamRegisterService tExamRegisterService;
	
    @GetMapping()
    public String register(ModelMap mmap){
    	mmap.put("userId", getSysUser().getUserId());
        return prefix + "/register";
    }
    
    @GetMapping("/face/verification/{examId}")
    public String faceVerification(@PathVariable("examId")Long examId) {
    	 return prefix + "/faceverification";
    }

    /**
     * 查询报名管理列表
     */
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(TExamRegister tExamRegister){
    	try {
			tExamRegister.setStudentId(ShiroUtils.getSysUser().getStudent().getStudentId());
		} catch (Exception e) {
			throw new RuntimeException("获取失败，请以学员的方式重新登录");
		}
        startPage();
        List<TExamRegister> list = tExamRegisterService.selectTExamRegisterList(tExamRegister);
        return getDataTable(list);
    }
}
