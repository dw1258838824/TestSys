package com.ruoyi.project.system.point.controller;

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
import com.ruoyi.project.system.point.domain.TQuestionPoint;
import com.ruoyi.project.system.point.service.ITQuestionPointService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.framework.web.domain.Ztree;

/**
 * 知识点分类Controller
 * 
 * @author ruoyi
 * @date 2020-05-25
 */
@Controller
@RequestMapping("/system/point")
public class TQuestionPointController extends BaseController
{
    private String prefix = "system/point";

    @Autowired
    private ITQuestionPointService tQuestionPointService;

    @RequiresPermissions("system:point:view")
    @GetMapping()
    public String point()
    {
        return prefix + "/point";
    }

    /**
     * 查询知识点分类树列表
     */
    @RequiresPermissions("system:point:list")
    @PostMapping("/list")
    @ResponseBody
    public List<TQuestionPoint> list(TQuestionPoint tQuestionPoint)
    {
        List<TQuestionPoint> list = tQuestionPointService.selectTQuestionPointList(tQuestionPoint);
        return list;
    }

    /**
     * 导出知识点分类列表
     */
    @RequiresPermissions("system:point:export")
    @Log(title = "知识点分类", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(TQuestionPoint tQuestionPoint)
    {
        List<TQuestionPoint> list = tQuestionPointService.selectTQuestionPointList(tQuestionPoint);
        ExcelUtil<TQuestionPoint> util = new ExcelUtil<TQuestionPoint>(TQuestionPoint.class);
        return util.exportExcel(list, "point");
    }

    /**
     * 新增知识点分类
     */
    @GetMapping(value = { "/add/{questionPointId}", "/add/" })
    public String add(@PathVariable(value = "questionPointId", required = false) Long questionPointId, ModelMap mmap)
    {
        if (StringUtils.isNotNull(questionPointId))
        {
            mmap.put("tQuestionPoint", tQuestionPointService.selectTQuestionPointById(questionPointId));
        }
        return prefix + "/add";
    }

    /**
     * 新增保存知识点分类
     */
    @RequiresPermissions("system:point:add")
    @Log(title = "知识点分类", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(TQuestionPoint tQuestionPoint)
    {
        return toAjax(tQuestionPointService.insertTQuestionPoint(tQuestionPoint));
    }

    /**
     * 修改知识点分类
     */
    @GetMapping("/edit/{questionPointId}")
    public String edit(@PathVariable("questionPointId") Long questionPointId, ModelMap mmap)
    {
        TQuestionPoint tQuestionPoint = tQuestionPointService.selectTQuestionPointById(questionPointId);
        mmap.put("tQuestionPoint", tQuestionPoint);
        return prefix + "/edit";
    }

    /**
     * 修改保存知识点分类
     */
    @RequiresPermissions("system:point:edit")
    @Log(title = "知识点分类", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(TQuestionPoint tQuestionPoint)
    {
        return toAjax(tQuestionPointService.updateTQuestionPoint(tQuestionPoint));
    }

    /**
     * 删除
     */
    @RequiresPermissions("system:point:remove")
    @Log(title = "知识点分类", businessType = BusinessType.DELETE)
    @GetMapping("/remove/{questionPointId}")
    @ResponseBody
    public AjaxResult remove(@PathVariable("questionPointId") Long questionPointId)
    {
        return toAjax(tQuestionPointService.deleteTQuestionPointById(questionPointId));
    }

    /**
     * 选择知识点分类树
     */
    @GetMapping(value = { "/selectPointTree/{questionPointId}", "/selectPointTree/" })
    public String selectPointTree(@PathVariable(value = "questionPointId", required = false) Long questionPointId, ModelMap mmap)
    {
        if (StringUtils.isNotNull(questionPointId))
        {
            mmap.put("tQuestionPoint", tQuestionPointService.selectTQuestionPointById(questionPointId));
        }
        return prefix + "/tree";
    }

    /**
     * 加载知识点分类树列表
     */
    @GetMapping("/treeData")
    @ResponseBody
    public List<Ztree> treeData()
    {
        List<Ztree> ztrees = tQuestionPointService.selectTQuestionPointTree();
        return ztrees;
    }
}
