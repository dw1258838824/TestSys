package com.ruoyi.project.system.examstudent.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.system.api.service.ClassInService;
import com.ruoyi.project.system.examstudent.domain.TRoomExamStudent;
import com.ruoyi.project.system.examstudent.service.ITRoomExamStudentService;
import com.ruoyi.project.system.role.domain.Role;

/**
 * 学员考试Controller
 * 
 * @author ruoyi
 * @date 2020-06-18
 */
@Controller
@RequestMapping("/system/examstudent")
public class TRoomExamStudentController extends BaseController
{
    private String prefix = "system/examstudent";

	private static final Logger log = LoggerFactory.getLogger(TRoomExamStudentController.class);
	
    @Autowired
    private ITRoomExamStudentService tRoomExamStudentService;

    @Autowired
    private ClassInService classInService;
    

    @GetMapping()
    public String examstudent(ModelMap mmap,HttpServletRequest request)
    {
    	mmap.put("searchRoomId", request.getParameter("roomId"));
        return prefix + "/examstudent";
    }

    /**
     * 查询学员考试列表
     */
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(TRoomExamStudent tRoomExamStudent)
    {
        startPage();
        List<TRoomExamStudent> list = tRoomExamStudentService.selectTRoomExamStudentList(tRoomExamStudent);
        return getDataTable(list);
    }

    /**
     * 导出学员考试列表
     */
    @RequiresPermissions("system:examstudent:export")
    @Log(title = "学员考试", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(TRoomExamStudent tRoomExamStudent)
    {
        List<TRoomExamStudent> list = tRoomExamStudentService.selectTRoomExamStudentList(tRoomExamStudent);
        ExcelUtil<TRoomExamStudent> util = new ExcelUtil<TRoomExamStudent>(TRoomExamStudent.class);
        return util.exportExcel(list, "examstudent");
    }

    /**
     * 新增学员考试
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存学员考试
     */
    @RequiresPermissions("system:examstudent:add")
    @Log(title = "学员考试", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(TRoomExamStudent tRoomExamStudent)
    {
        return toAjax(tRoomExamStudentService.insertTRoomExamStudent(tRoomExamStudent));
    }

    /**
     * 修改学员考试
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        TRoomExamStudent tRoomExamStudent = tRoomExamStudentService.selectTRoomExamStudentById(id);
        mmap.put("tRoomExamStudent", tRoomExamStudent);
        return prefix + "/edit";
    }

    /**
     * 修改保存学员考试
     */
    @RequiresPermissions("system:examstudent:edit")
    @Log(title = "学员考试", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(TRoomExamStudent tRoomExamStudent)
    {
        return toAjax(tRoomExamStudentService.updateTRoomExamStudent(tRoomExamStudent));
    }

    /**
     * 删除学员考试
     */
    @RequiresPermissions("system:examstudent:remove")
    @Log(title = "学员考试", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(tRoomExamStudentService.deleteTRoomExamStudentByIds(ids));
    }
    

    /**
     * 查看回放
     */
    @GetMapping("/replay/{courseId}/{classId}")
    @ResponseBody
    public String replay(@PathVariable("courseId") String courseId,@PathVariable("classId") String classId)
    {
    	Map<String, String> params = Maps.newHashMap();
		params.put("courseId", courseId);
		params.put("classId",  classId);
    	String json = classInService.classInHttp("getWebcastUrl", params);//调用classIn回放
    	JSONObject obj = JSONObject.parseObject(json);
    	Integer errno = obj.getJSONObject("error_info").getInteger("errno");
    	if(null!=errno && errno==1) {
    		return obj.getString("data");
    	}else {
    		log.error("调用classIn查看回放失败",json);
    		return "error";
    	}
    }
}
