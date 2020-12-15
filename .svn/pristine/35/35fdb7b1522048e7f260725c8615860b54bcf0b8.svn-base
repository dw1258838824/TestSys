package com.ruoyi.project.system.reg.controller;

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
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.project.system.reg.domain.TDeptReg;
import com.ruoyi.project.system.reg.service.ITDeptRegService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.domain.AjaxResult.Type;
import com.google.common.collect.Maps;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 机构批量报名Controller
 * 
 * @author ruoyi
 * @date 2020-07-31
 */
@Controller
@RequestMapping("/system/reg")
public class TDeptRegController extends BaseController
{
    private String prefix = "system/reg";

    @Autowired
    private ITDeptRegService tDeptRegService;

    @RequiresPermissions("system:reg:view")
    @GetMapping()
    public String reg()
    {
        return prefix + "/reg";
    }

    /**
     * 查询机构批量报名列表
     */
    @RequiresPermissions("system:reg:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(TDeptReg tDeptReg)
    {
        startPage();
        List<TDeptReg> list = tDeptRegService.selectTDeptRegList(tDeptReg);
        return getDataTable(list);
    }

    /**
     * 导出机构批量报名列表
     */
    @RequiresPermissions("system:reg:export")
    @Log(title = "机构批量报名", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(TDeptReg tDeptReg)
    {
        List<TDeptReg> list = tDeptRegService.selectTDeptRegList(tDeptReg);
        ExcelUtil<TDeptReg> util = new ExcelUtil<TDeptReg>(TDeptReg.class);
        return util.exportExcel(list, "reg");
    }

    /**
     * 新增机构批量报名
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存机构批量报名
     */
    @RequiresPermissions("system:reg:add")
    @Log(title = "机构批量报名", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(TDeptReg tDeptReg,HttpServletRequest request)
    {
    	tDeptReg.getParams().put("studentIds", request.getParameter("studentIdStrs").split(","));
    	tDeptReg.getParams().put("deptIds", request.getParameter("deptIds").split(","));
    	tDeptReg.setOperateUserId(getUserId());
        return new AjaxResult(Type.SUCCESS,"成功添加"+ tDeptRegService.insertTDeptReg(tDeptReg)+"条数据");
    }

    /**
     * 修改机构批量报名
     */
    @GetMapping("/edit/{deptRegId}")
    public String edit(@PathVariable("deptRegId") Long deptRegId, ModelMap mmap)
    {
        TDeptReg tDeptReg = tDeptRegService.selectTDeptRegById(deptRegId);
        mmap.put("tDeptReg", tDeptReg);
        return prefix + "/edit";
    }

    /**
     * 修改保存机构批量报名
     */
    @RequiresPermissions("system:reg:edit")
    @Log(title = "机构批量报名", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(TDeptReg tDeptReg)
    {
        return toAjax(tDeptRegService.updateTDeptReg(tDeptReg));
    }

    /**
     * 删除机构批量报名
     */
    @RequiresPermissions("system:reg:remove")
    @Log(title = "机构批量报名", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(tDeptRegService.deleteTDeptRegByIds(ids));
    }
}
