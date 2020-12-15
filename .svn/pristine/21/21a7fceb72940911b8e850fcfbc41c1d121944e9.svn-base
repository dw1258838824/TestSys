package com.ruoyi.project.system.company.controller;

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
import com.ruoyi.project.system.company.domain.TCompany;
import com.ruoyi.project.system.company.service.ITCompanyService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 合作单位管理Controller
 * 
 * @author ruoyi
 * @date 2020-07-16
 */
@Controller
@RequestMapping("/system/company")
public class TCompanyController extends BaseController
{
    private String prefix = "system/company";

    @Autowired
    private ITCompanyService tCompanyService;

    @RequiresPermissions("system:company:view")
    @GetMapping()
    public String company()
    {
        return prefix + "/company";
    }

    /**
     * 查询合作单位管理列表
     */
    @RequiresPermissions("system:company:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(TCompany tCompany)
    {
        startPage();
        List<TCompany> list = tCompanyService.selectTCompanyList(tCompany);
        return getDataTable(list);
    }

    /**
     * 导出合作单位管理列表
     */
    @RequiresPermissions("system:company:export")
    @Log(title = "合作单位管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(TCompany tCompany)
    {
        List<TCompany> list = tCompanyService.selectTCompanyList(tCompany);
        ExcelUtil<TCompany> util = new ExcelUtil<TCompany>(TCompany.class);
        return util.exportExcel(list, "company");
    }

    /**
     * 新增合作单位管理
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存合作单位管理
     */
    @RequiresPermissions("system:company:add")
    @Log(title = "合作单位管理", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(TCompany tCompany)
    {
        return toAjax(tCompanyService.insertTCompany(tCompany));
    }

    /**
     * 修改合作单位管理
     */
    @GetMapping("/edit/{companyId}")
    public String edit(@PathVariable("companyId") Long companyId, ModelMap mmap)
    {
        TCompany tCompany = tCompanyService.selectTCompanyById(companyId);
        mmap.put("tCompany", tCompany);
        return prefix + "/edit";
    }

    /**
     * 修改保存合作单位管理
     */
    @RequiresPermissions("system:company:edit")
    @Log(title = "合作单位管理", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(TCompany tCompany)
    {
        return toAjax(tCompanyService.updateTCompany(tCompany));
    }

    /**
     * 删除合作单位管理
     */
    @RequiresPermissions("system:company:remove")
    @Log(title = "合作单位管理", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(tCompanyService.deleteTCompanyByIds(ids));
    }
}
