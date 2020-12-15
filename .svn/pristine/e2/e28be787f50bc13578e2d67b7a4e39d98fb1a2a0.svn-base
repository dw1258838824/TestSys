package com.ruoyi.project.system.question.controller;

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
import org.springframework.web.multipart.MultipartFile;

import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.system.question.domain.TQuestion;
import com.ruoyi.project.system.question.service.ITQuestionService;

/**
 * 题库Controller
 * 
 * @author ruoyi
 * @date 2020-05-27
 */
@Controller
@RequestMapping("/system/question")
public class TQuestionController extends BaseController
{
    private String prefix = "system/question";

    @Autowired
    private ITQuestionService tQuestionService;

    @RequiresPermissions("system:question:view")
    @GetMapping()
    public String question()
    {
        return prefix + "/question";
    }
    
    @GetMapping("/python")
    public String pythonRun()
    {
        return prefix + "/pythonRun";
    }


    /**
     * 查询题库列表
     */
    @RequiresPermissions("system:question:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(TQuestion tQuestion)
    {
        startPage();
        List<TQuestion> list = tQuestionService.selectTQuestionList(tQuestion);
        return getDataTable(list);
    }

    /**
     * 导出题库列表
     */
    @RequiresPermissions("system:question:export")
    @Log(title = "题库", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(TQuestion tQuestion)
    {
        List<TQuestion> list = tQuestionService.selectTQuestionList(tQuestion);
        for(TQuestion obj : list) {
        	obj.setQuestionOptions(obj.getQuestionOptions());
        }
        ExcelUtil<TQuestion> util = new ExcelUtil<TQuestion>(TQuestion.class);
        return util.exportExcel(list, "question");
    }

    /**
     * 新增题库
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存题库
     */
    @RequiresPermissions("system:question:add")
    @Log(title = "题库", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(TQuestion tQuestion)
    {
        return toAjax(tQuestionService.insertTQuestion(tQuestion));
    }

    /**
     * 修改题库
     */
    @GetMapping("/edit/{questionId}")
    public String edit(@PathVariable("questionId") Long questionId, ModelMap mmap)
    {
        TQuestion tQuestion = tQuestionService.selectTQuestionById(questionId);
        mmap.put("tQuestion", tQuestion);
        return prefix + "/edit";
    }

    /**
     * 修改保存题库
     */
    @RequiresPermissions("system:question:edit")
    @Log(title = "题库", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(TQuestion tQuestion)
    {
        return toAjax(tQuestionService.updateTQuestion(tQuestion));
    }

    /**
     * 删除题库
     */
    @RequiresPermissions("system:question:remove")
    @Log(title = "题库", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(tQuestionService.deleteTQuestionByIds(ids));
    }
    
    /**
     * 导入题目
     * @return
     */
    @RequiresPermissions("system:question:view")
    @GetMapping("/importTemplate")
    @ResponseBody
    public AjaxResult importTemplate()
    {
        ExcelUtil<TQuestion> util = new ExcelUtil<TQuestion>(TQuestion.class);
        return util.importTemplateExcel("题目数据");
    }

    @Log(title = "题库管理", businessType = BusinessType.IMPORT)
    @RequiresPermissions("system:question:import")
    @PostMapping("/importData")
    @ResponseBody
    public AjaxResult importData(MultipartFile file, boolean updateSupport) throws Exception
    {
        ExcelUtil<TQuestion> util = new ExcelUtil<TQuestion>(TQuestion.class);
        List<TQuestion> list = util.importQuestionExcel("",file.getInputStream());
        String message = tQuestionService.importQuestion(list);
        return AjaxResult.success(message);
    }
}
