package com.ruoyi.project.system.teacherschart.controller;

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
import com.ruoyi.project.system.teacherschart.domain.ViewDeptTeachers;
import com.ruoyi.project.system.teacherschart.service.IViewDeptTeachersService;

/**
 * 老师统计Controller
 * 
 * @author ruoyi
 * @date 2020-07-27
 */
@Controller
@RequestMapping("/system/teacherschart")
public class ViewDeptTeachersController extends BaseController
{
    private String prefix = "system/teacherschart";

    @Autowired
    private IViewDeptTeachersService viewDeptTeachersService;
    @Autowired
    private IDeptService deptService;

    @RequiresPermissions("system:teacherschart:view")
    @GetMapping()
    public String teacherschart(ModelMap mmap)
    {
    	mmap.put("depts", deptService.selectDeptList(new Dept()));
        return prefix + "/teacherschart";
    }

    /**
     * 查询老师统计列表
     */
    @RequiresPermissions("system:teacherschart:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(ViewDeptTeachers viewDeptTeachers,ModelMap mmap)
    {
        List<ViewDeptTeachers> list = viewDeptTeachersService.selectViewDeptTeachersList(viewDeptTeachers);
        return getDataTable(list);
    }

    /**
     * 导出老师统计列表
     */
    @RequiresPermissions("system:teacherschart:export")
    @Log(title = "老师统计", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(ViewDeptTeachers viewDeptTeachers)
    {
        List<ViewDeptTeachers> list = viewDeptTeachersService.selectViewDeptTeachersList(viewDeptTeachers);
        List<Dept> dlist = deptService.selectDeptList(new Dept());
        for(ViewDeptTeachers o : list) {
        	if(null!=o.getDeptId())
        		o.setDeptName(ListDataUtil.ListFindValue(dlist, "deptId", o.getDeptId()+"", "deptName").toString());
        	else 
        		o.setDeptName("无");
        }
        ExcelUtil<ViewDeptTeachers> util = new ExcelUtil<ViewDeptTeachers>(ViewDeptTeachers.class);
        return util.exportExcel(list, "teacherschart");
    }

    /**
     * 新增老师统计
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存老师统计
     */
    @RequiresPermissions("system:teacherschart:add")
    @Log(title = "老师统计", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(ViewDeptTeachers viewDeptTeachers)
    {
        return toAjax(viewDeptTeachersService.insertViewDeptTeachers(viewDeptTeachers));
    }

    /**
     * 修改老师统计
     */
    @GetMapping("/edit/{deptId}")
    public String edit(@PathVariable("deptId") Long deptId, ModelMap mmap)
    {
        ViewDeptTeachers viewDeptTeachers = viewDeptTeachersService.selectViewDeptTeachersById(deptId);
        mmap.put("viewDeptTeachers", viewDeptTeachers);
        return prefix + "/edit";
    }

    /**
     * 修改保存老师统计
     */
    @RequiresPermissions("system:teacherschart:edit")
    @Log(title = "老师统计", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(ViewDeptTeachers viewDeptTeachers)
    {
        return toAjax(viewDeptTeachersService.updateViewDeptTeachers(viewDeptTeachers));
    }

    /**
     * 删除老师统计
     */
    @RequiresPermissions("system:teacherschart:remove")
    @Log(title = "老师统计", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(viewDeptTeachersService.deleteViewDeptTeachersByIds(ids));
    }
}
