package com.ruoyi.project.system.studentchart.controller;

import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ruoyi.common.utils.ListDataUtil;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.system.dept.domain.Dept;
import com.ruoyi.project.system.dept.service.IDeptService;
import com.ruoyi.project.system.studentchart.domain.ViewDeptStudent;
import com.ruoyi.project.system.studentchart.service.IViewDeptStudentService;

/**
 * 学员统计Controller
 * 
 * @author ruoyi
 * @date 2020-07-27
 */
@Controller
@RequestMapping("/system/studentchart")
public class ViewDeptStudentController extends BaseController
{
    private String prefix = "system/studentchart";

    @Autowired
    private IViewDeptStudentService viewDeptStudentService;
    @Autowired
    private IDeptService deptService;

    @RequiresPermissions("system:studentchart:view")
    @GetMapping()
    public String studentchart(ModelMap mmap)
    {
    	mmap.put("depts", deptService.selectDeptList(new Dept()));
        return prefix + "/studentchart";
    }

    /**
     * 查询学员统计列表
     */
    @RequiresPermissions("system:studentchart:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(ViewDeptStudent viewDeptStudent,ModelMap mmap)
    {
        List<ViewDeptStudent> list = viewDeptStudentService.selectViewDeptStudentList(viewDeptStudent);
        return getDataTable(list);
    }

    /**
     * 导出学员统计列表
     */
    @RequiresPermissions("system:studentchart:export")
    @Log(title = "学员统计", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(ViewDeptStudent viewDeptStudent)
    {
        List<ViewDeptStudent> list = viewDeptStudentService.selectViewDeptStudentList(viewDeptStudent);
        List<Dept> dlist = deptService.selectDeptList(new Dept());
        for(ViewDeptStudent o : list) {
        	if(null!=o.getDeptId())
        		o.setDeptName(ListDataUtil.ListFindValue(dlist, "deptId", o.getDeptId()+"", "deptName").toString());
        	else 
        		o.setDeptName("无");
        }
        ExcelUtil<ViewDeptStudent> util = new ExcelUtil<ViewDeptStudent>(ViewDeptStudent.class);
        return util.exportExcel(list, "studentchart");
    }

    /**
     * 新增学员统计
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存学员统计
     */
    @RequiresPermissions("system:studentchart:add")
    @Log(title = "学员统计", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(ViewDeptStudent viewDeptStudent)
    {
        return toAjax(viewDeptStudentService.insertViewDeptStudent(viewDeptStudent));
    }

    /**
     * 修改学员统计
     */
    @GetMapping("/edit/{deptId}")
    public String edit(@PathVariable("deptId") Long deptId, ModelMap mmap)
    {
        ViewDeptStudent viewDeptStudent = viewDeptStudentService.selectViewDeptStudentById(deptId);
        mmap.put("viewDeptStudent", viewDeptStudent);
        return prefix + "/edit";
    }

    /**
     * 修改保存学员统计
     */
    @RequiresPermissions("system:studentchart:edit")
    @Log(title = "学员统计", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(ViewDeptStudent viewDeptStudent)
    {
        return toAjax(viewDeptStudentService.updateViewDeptStudent(viewDeptStudent));
    }

    /**
     * 删除学员统计
     */
    @RequiresPermissions("system:studentchart:remove")
    @Log(title = "学员统计", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(viewDeptStudentService.deleteViewDeptStudentByIds(ids));
    }
}
