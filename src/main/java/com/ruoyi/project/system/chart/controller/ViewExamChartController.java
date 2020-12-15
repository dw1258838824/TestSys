package com.ruoyi.project.system.chart.controller;

import java.util.List;
import java.util.Map;

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
import com.ruoyi.project.system.chart.domain.ViewExamChart;
import com.ruoyi.project.system.chart.service.IViewExamChartService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 考试统计Controller
 * 
 * @author ruoyi
 * @date 2020-07-19
 */
@Controller
@RequestMapping("/system/chart")
public class ViewExamChartController extends BaseController
{
    private String prefix = "system/chart";

    @Autowired
    private IViewExamChartService viewExamChartService;

    @RequiresPermissions("system:chart:view")
    @GetMapping()
    public String chart()
    {
        return prefix + "/chart";
    }

    /**
     * 查询考试统计列表
     */
    @RequiresPermissions("system:chart:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(ViewExamChart viewExamChart)
    {
        startPage();
        List<ViewExamChart> list = viewExamChartService.selectViewExamChartList(viewExamChart);
        return getDataTable(list);
    }
    @GetMapping("/questionChart/{examId}/{flag}/{regCount}")
    @ResponseBody
    public List<Map<String, Object>> questionChart(@PathVariable("examId") Long examId,@PathVariable("flag") int flag,@PathVariable("regCount") int regCount, ModelMap mmap)
    {
    	Map<String, Object> parm = Maps.newHashMap();
    	parm.put("examId", examId);
    	parm.put("flag", flag);
    	parm.put("regCount", regCount);
    	List<Map<String, Object>> list = viewExamChartService.selectQuestionChart(parm);
        return list;
    }

    /**
     * 导出考试统计列表
     */
    @RequiresPermissions("system:chart:export")
    @Log(title = "考试统计", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(ViewExamChart viewExamChart)
    {
        List<ViewExamChart> list = viewExamChartService.selectViewExamChartList(viewExamChart);
        ExcelUtil<ViewExamChart> util = new ExcelUtil<ViewExamChart>(ViewExamChart.class);
        return util.exportExcel(list, "chart");
    }

    /**
     * 新增考试统计
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存考试统计
     */
    @RequiresPermissions("system:chart:add")
    @Log(title = "考试统计", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(ViewExamChart viewExamChart)
    {
        return toAjax(viewExamChartService.insertViewExamChart(viewExamChart));
    }

    /**
     * 修改考试统计
     */
    @GetMapping("/edit/{examId}")
    public String edit(@PathVariable("examId") Long examId, ModelMap mmap)
    {
        ViewExamChart viewExamChart = viewExamChartService.selectViewExamChartById(examId);
        mmap.put("viewExamChart", viewExamChart);
        return prefix + "/edit";
    }

    /**
     * 修改保存考试统计
     */
    @RequiresPermissions("system:chart:edit")
    @Log(title = "考试统计", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(ViewExamChart viewExamChart)
    {
        return toAjax(viewExamChartService.updateViewExamChart(viewExamChart));
    }

    /**
     * 删除考试统计
     */
    @RequiresPermissions("system:chart:remove")
    @Log(title = "考试统计", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(viewExamChartService.deleteViewExamChartByIds(ids));
    }
}
