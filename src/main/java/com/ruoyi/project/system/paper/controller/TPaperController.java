package com.ruoyi.project.system.paper.controller;

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

import com.ruoyi.common.utils.RequestUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.system.dict.domain.DictData;
import com.ruoyi.project.system.dict.utils.DictUtils;
import com.ruoyi.project.system.paper.domain.TPaper;
import com.ruoyi.project.system.paper.service.ITPaperService;

/**
 * 试卷管理Controller
 * 
 * @author ruoyi
 * @date 2020-06-02
 */
@SuppressWarnings("all")
@Controller
@RequestMapping("/system/paper")
public class TPaperController extends BaseController
{
    private String prefix = "system/paper";

    @Autowired
    private ITPaperService tPaperService;

    @RequiresPermissions("system:paper:view")
    @GetMapping()
    public String paper()
    {
        return prefix + "/paper";
    }

    /**
     * 查询试卷管理列表
     */
    @RequiresPermissions("system:paper:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(TPaper tPaper)
    {
        startPage();
        List<TPaper> list = tPaperService.selectTPaperList(tPaper);
        return getDataTable(list);
    }

    /**
     * 导出试卷管理列表
     */
    @RequiresPermissions("system:paper:export")
    @Log(title = "试卷管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(TPaper tPaper)
    {
        List<TPaper> list = tPaperService.selectTPaperList(tPaper);
		for (TPaper tp : list) {
			tp.setPaperType(DictUtils.getDictCache("sys_paper_type",tp.getPaperType()));
		}
        ExcelUtil<TPaper> util = new ExcelUtil<TPaper>(TPaper.class);
        return util.exportExcel(list, "paper");
    }

    /**
     * 新增试卷管理
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存试卷管理
     */
	@RequiresPermissions("system:paper:add")
    @Log(title = "试卷管理", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(TPaper tPaper,HttpServletRequest request)
    {
    	Map<String,Object> parm = RequestUtils.getRequestMap(request);
        return toAjax(tPaperService.insertTPaper(tPaper,parm));
    }

    /**
     * 修改试卷管理
     */
    @GetMapping("/edit/{paperId}")
    public String edit(@PathVariable("paperId") Long paperId, ModelMap mmap)
    {
        TPaper tPaper = tPaperService.selectTPaperById(paperId);
        mmap.put("tPaper", tPaper);
        return prefix + "/edit";
    }

    /**
     * 修改保存试卷管理
     */
    @RequiresPermissions("system:paper:edit")
    @Log(title = "试卷管理", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(TPaper tPaper)
    {
        return toAjax(tPaperService.updateTPaper(tPaper));
    }

    /**
     * 删除试卷管理
     */
    @RequiresPermissions("system:paper:remove")
    @Log(title = "试卷管理", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(tPaperService.deleteTPaperByIds(ids));
    }
}
