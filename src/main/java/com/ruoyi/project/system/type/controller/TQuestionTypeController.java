package com.ruoyi.project.system.type.controller;

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
import com.ruoyi.project.system.type.domain.TQuestionType;
import com.ruoyi.project.system.type.service.ITQuestionTypeService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 题型管理Controller
 * 
 * @author ruoyi
 * @date 2020-05-27
 */
@Controller
@RequestMapping("/system/type")
public class TQuestionTypeController extends BaseController
{
    private String prefix = "system/type";

    @Autowired
    private ITQuestionTypeService tQuestionTypeService;

    @RequiresPermissions("system:type:view")
    @GetMapping()
    public String type()
    {
        return prefix + "/type";
    }

    /**
     * 查询题型管理列表
     */
    @RequiresPermissions("system:type:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(TQuestionType tQuestionType)
    {
        startPage();
        List<TQuestionType> list = tQuestionTypeService.selectTQuestionTypeList(tQuestionType);
        return getDataTable(list);
    }

    /**
     * 查询题型管理列表
     */
    @RequiresPermissions("system:type:getlist")
    @PostMapping("/getlist")
    @ResponseBody
    public List<TQuestionType> getlist(TQuestionType tQuestionType)
    {
        List<TQuestionType> list = tQuestionTypeService.selectTQuestionTypeList(tQuestionType);
        return list; 
    }

    /**
     * 导出题型管理列表
     */
    @RequiresPermissions("system:type:export")
    @Log(title = "题型管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(TQuestionType tQuestionType)
    {
        List<TQuestionType> list = tQuestionTypeService.selectTQuestionTypeList(tQuestionType);
        ExcelUtil<TQuestionType> util = new ExcelUtil<TQuestionType>(TQuestionType.class);
        return util.exportExcel(list, "type");
    }

    /**
     * 新增题型管理
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存题型管理
     */
    @RequiresPermissions("system:type:add")
    @Log(title = "题型管理", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(TQuestionType tQuestionType)
    {
        return toAjax(tQuestionTypeService.insertTQuestionType(tQuestionType));
    }

    /**
     * 修改题型管理
     */
    @GetMapping("/edit/{questionTypeId}")
    public String edit(@PathVariable("questionTypeId") Integer questionTypeId, ModelMap mmap)
    {
        TQuestionType tQuestionType = tQuestionTypeService.selectTQuestionTypeById(questionTypeId);
        mmap.put("tQuestionType", tQuestionType);
        return prefix + "/edit";
    }

    /**
     * 修改保存题型管理
     */
    @RequiresPermissions("system:type:edit")
    @Log(title = "题型管理", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(TQuestionType tQuestionType)
    {
        return toAjax(tQuestionTypeService.updateTQuestionType(tQuestionType));
    }

    /**
     * 删除题型管理
     */
    @RequiresPermissions("system:type:remove")
    @Log(title = "题型管理", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(tQuestionTypeService.deleteTQuestionTypeByIds(ids));
    }
}
