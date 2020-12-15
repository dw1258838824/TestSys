package com.ruoyi.project.system.business.controller;

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
import com.ruoyi.project.system.business.domain.TBusiness;
import com.ruoyi.project.system.business.service.ITBusinessService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 商务洽谈Controller
 * 
 * @author ruoyi
 * @date 2020-07-16
 */
@Controller
@RequestMapping("/system/business")
public class TBusinessController extends BaseController
{
    private String prefix = "system/business";

    @Autowired
    private ITBusinessService tBusinessService;

    @RequiresPermissions("system:business:view")
    @GetMapping()
    public String business()
    {
        return prefix + "/business";
    }

    /**
     * 查询商务洽谈列表
     */
    @RequiresPermissions("system:business:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(TBusiness tBusiness)
    {
        startPage();
        List<TBusiness> list = tBusinessService.selectTBusinessList(tBusiness);
        return getDataTable(list);
    }

    /**
     * 导出商务洽谈列表
     */
    @RequiresPermissions("system:business:export")
    @Log(title = "商务洽谈", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(TBusiness tBusiness)
    {
        List<TBusiness> list = tBusinessService.selectTBusinessList(tBusiness);
        ExcelUtil<TBusiness> util = new ExcelUtil<TBusiness>(TBusiness.class);
        return util.exportExcel(list, "business");
    }

    /**
     * 新增商务洽谈
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存商务洽谈
     */
    @RequiresPermissions("system:business:add")
    @Log(title = "商务洽谈", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(TBusiness tBusiness)
    {
        return toAjax(tBusinessService.insertTBusiness(tBusiness));
    }

    /**
     * 修改商务洽谈
     */
    @GetMapping("/edit/{businessId}")
    public String edit(@PathVariable("businessId") Long businessId, ModelMap mmap)
    {
        TBusiness tBusiness = tBusinessService.selectTBusinessById(businessId);
        mmap.put("tBusiness", tBusiness);
        return prefix + "/edit";
    }

    /**
     * 修改保存商务洽谈
     */
    @RequiresPermissions("system:business:edit")
    @Log(title = "商务洽谈", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(TBusiness tBusiness)
    {
        return toAjax(tBusinessService.updateTBusiness(tBusiness));
    }

    /**
     * 删除商务洽谈
     */
    @RequiresPermissions("system:business:remove")
    @Log(title = "商务洽谈", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(tBusinessService.deleteTBusinessByIds(ids));
    }
}
