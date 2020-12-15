package com.ruoyi.project.system.subject.controller;

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

import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.system.subject.domain.TSubject;
import com.ruoyi.project.system.subject.service.ITSubjectService;

/**
 * 科目管理Controller
 * 
 * @author ruoyi
 * @date 2020-05-27
 */
@Controller
@RequestMapping("/system/subject")
public class TSubjectController extends BaseController
{
    private String prefix = "system/subject";

    @Autowired
    private ITSubjectService tSubjectService;

    @RequiresPermissions("system:subject:view")
    @GetMapping()
    public String subject()
    {
        return prefix + "/subject";
    }

    /**
     * 查询科目管理列表
     */
    @RequiresPermissions("system:subject:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(TSubject tSubject)
    {
        startPage();
        List<TSubject> list = tSubjectService.selectTSubjectList(tSubject);
        return getDataTable(list);
    }
    /**
     * 查询科目管理数据
     */
    @PostMapping("/getlist")
    @ResponseBody
    public List<TSubject> getlist(TSubject tSubject)
    {
        List<TSubject> list = tSubjectService.selectTSubjectList(tSubject);;
        return list; 
    }

    /**
     * 导出科目管理列表
     */
    @RequiresPermissions("system:subject:export")
    @Log(title = "科目管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(TSubject tSubject)
    {
        List<TSubject> list = tSubjectService.selectTSubjectList(tSubject);
        ExcelUtil<TSubject> util = new ExcelUtil<TSubject>(TSubject.class);
        return util.exportExcel(list, "subject");
    }

    /**
     * 新增科目管理
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存科目管理
     */
    @RequiresPermissions("system:subject:add")
    @Log(title = "科目管理", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(TSubject tSubject)
    {
        return toAjax(tSubjectService.insertTSubject(tSubject));
    }

    /**
     * 修改科目管理
     */
    @GetMapping("/edit/{subjectId}")
    public String edit(@PathVariable("subjectId") Integer subjectId, ModelMap mmap)
    {
        TSubject tSubject = tSubjectService.selectTSubjectById(subjectId);
        mmap.put("tSubject", tSubject);
        return prefix + "/edit";
    }

    /**
     * 修改保存科目管理
     */
    @RequiresPermissions("system:subject:edit")
    @Log(title = "科目管理", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(TSubject tSubject)
    {
        return toAjax(tSubjectService.updateTSubject(tSubject));
    }

    /**
     * 删除科目管理
     */
    @RequiresPermissions("system:subject:remove")
    @Log(title = "科目管理", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(tSubjectService.deleteTSubjectByIds(ids));
    }
}
