package com.ruoyi.project.system.level.controller;

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
import com.ruoyi.project.system.level.domain.TLevel;
import com.ruoyi.project.system.level.service.ITLevelService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 等级管理Controller
 * 
 * @author ruoyi
 * @date 2020-05-25
 */
@Controller
@RequestMapping("/system/level")
public class TLevelController extends BaseController
{
    private String prefix = "system/level";

    @Autowired
    private ITLevelService tLevelService;

    @RequiresPermissions("system:level:view")
    @GetMapping()
    public String level()
    {
        return prefix + "/level";
    }

    /**
     * 查询等级管理列表
     */
    @RequiresPermissions("system:level:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(TLevel tLevel)
    {
        startPage();
        List<TLevel> list = tLevelService.selectTLevelList(tLevel);
        return getDataTable(list);
    }

    /**
     * 查询等级数据
     */
    @PostMapping("/getlist")
    @ResponseBody
    public List<TLevel> getlist(TLevel tLevel)
    {
        List<TLevel> list = tLevelService.selectTLevelList(tLevel);
        return list;
    }
    
    /**
     * 导出等级管理列表
     */
    @RequiresPermissions("system:level:export")
    @Log(title = "等级管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(TLevel tLevel)
    {
        List<TLevel> list = tLevelService.selectTLevelList(tLevel);
        ExcelUtil<TLevel> util = new ExcelUtil<TLevel>(TLevel.class);
        return util.exportExcel(list, "level");
    }

    /**
     * 新增等级管理
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存等级管理
     */
    @RequiresPermissions("system:level:add")
    @Log(title = "等级管理", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(TLevel tLevel)
    {
        return toAjax(tLevelService.insertTLevel(tLevel));
    }

    /**
     * 修改等级管理
     */
    @GetMapping("/edit/{levelId}")
    public String edit(@PathVariable("levelId") Long levelId, ModelMap mmap)
    {
        TLevel tLevel = tLevelService.selectTLevelById(levelId);
        mmap.put("tLevel", tLevel);
        return prefix + "/edit";
    }

    /**
     * 修改保存等级管理
     */
    @RequiresPermissions("system:level:edit")
    @Log(title = "等级管理", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(TLevel tLevel)
    {
        return toAjax(tLevelService.updateTLevel(tLevel));
    }

    /**
     * 删除等级管理
     */
    @RequiresPermissions("system:level:remove")
    @Log(title = "等级管理", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(tLevelService.deleteTLevelByIds(ids));
    }
}
