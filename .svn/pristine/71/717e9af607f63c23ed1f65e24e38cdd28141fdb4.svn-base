package com.ruoyi.project.system.register.controller;

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
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.project.system.dict.utils.DictUtils;
import com.ruoyi.project.system.register.domain.TExamRegister;
import com.ruoyi.project.system.register.service.ITExamRegisterService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 报名管理Controller
 * 
 * @author ruoyi
 * @date 2020-06-08
 */
@Controller
@RequestMapping("/system/register")
public class TExamRegisterController extends BaseController
{
    private String prefix = "system/register";

    @Autowired
    private ITExamRegisterService tExamRegisterService;

    @RequiresPermissions("system:register:view")
    @GetMapping()
    public String register()
    {
        return prefix + "/register";
    }

    /**
     * 查询报名管理列表
     */
    @RequiresPermissions("system:register:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(TExamRegister tExamRegister)
    {
        startPage();
        List<TExamRegister> list = tExamRegisterService.selectTExamRegisterList(tExamRegister);
        return getDataTable(list);
    }

    /**
     * 导出报名管理列表
     */
    @RequiresPermissions("system:register:export")
    @Log(title = "报名管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(TExamRegister tExamRegister)
    {
        List<TExamRegister> list = tExamRegisterService.selectTExamRegisterList(tExamRegister);
        for(TExamRegister eg : list) {
        	eg.setStudentName(eg.getParams().get("studentName").toString());
        	eg.setExamTitle(eg.getParams().get("examTitle").toString());
        	eg.setState(DictUtils.getDictCache("sys_register_state",eg.getState()));
        }
        ExcelUtil<TExamRegister> util = new ExcelUtil<TExamRegister>(TExamRegister.class);
        return util.exportExcel(list, "register");
    }

    /**
     * 新增报名管理
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存报名管理
     */
    @RequiresPermissions("system:register:add")
    @Log(title = "报名管理", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(TExamRegister tExamRegister)
    {
        return toAjax(tExamRegisterService.insertTExamRegister(tExamRegister));
    }

    /**
     * 修改报名管理
     */
    @GetMapping("/edit/{examRegisterId}")
    public String edit(@PathVariable("examRegisterId") Long examRegisterId, ModelMap mmap)
    {
        TExamRegister tExamRegister = tExamRegisterService.selectTExamRegisterById(examRegisterId);
        mmap.put("tExamRegister", tExamRegister);
        return prefix + "/edit";
    }

    /**
     * 修改保存报名管理
     */
    @RequiresPermissions("system:register:edit")
    @Log(title = "报名管理", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(TExamRegister tExamRegister)
    {
        return toAjax(tExamRegisterService.updateTExamRegister(tExamRegister));
    }

    /**
     * 删除报名管理
     */
    @RequiresPermissions("system:register:remove")
    @Log(title = "报名管理", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(tExamRegisterService.deleteTExamRegisterByIds(ids));
    }
}
