package com.ruoyi.project.system.exam.controller;

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

import com.ruoyi.common.utils.ListDataUtil;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.system.dict.utils.DictUtils;
import com.ruoyi.project.system.exam.domain.TExam;
import com.ruoyi.project.system.exam.service.ITExamService;
import com.ruoyi.project.system.level.domain.TLevel;
import com.ruoyi.project.system.level.service.ITLevelService;
import com.ruoyi.project.system.paper.domain.TPaper;
import com.ruoyi.project.system.paper.service.ITPaperService;
import com.ruoyi.project.system.subject.domain.TSubject;
import com.ruoyi.project.system.subject.service.ITSubjectService;

/**
 * 考试管理Controller
 * 
 * @author ruoyi
 * @date 2020-06-04
 */
@Controller
@RequestMapping("/system/exam")
public class TExamController extends BaseController
{
    private String prefix = "system/exam";

    @Autowired
    private ITExamService tExamService;
    @Autowired
    private ITLevelService levelService;
    @Autowired
    private ITSubjectService subjectService;
    @Autowired
    private ITPaperService paperService;

    @RequiresPermissions("system:exam:view")
    @GetMapping()
    public String exam()
    {
        return prefix + "/exam";
    }

    /**
     * 查询考试管理列表
     */
    @RequiresPermissions("system:exam:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(TExam tExam)
    {
        startPage();
        List<TExam> list = tExamService.selectTExamList(tExam);
        if(list.size()>0) {
        	List<TLevel> lvlist = levelService.selectTLevelList(new TLevel());
        	List<TSubject> sulist = subjectService.selectTSubjectList(new TSubject());
        	List<TPaper> pplist = paperService.selectTPaperList(new TPaper());
	        for(TExam t: list) {
	        	t.getParams().put("levelName", (ListDataUtil.ListFindValue(lvlist,"levelId",t.getLevelId()+"","levelName").toString()));
	        	t.getParams().put("levelValue", (ListDataUtil.ListFindValue(lvlist,"levelId",t.getLevelId()+"","levelValue").toString()));
	        	t.getParams().put("subjectName", (ListDataUtil.ListFindValue(sulist,"subjectId",t.getSubjectId()+"","subjectName").toString()));
	        	t.getParams().put("paperName", (ListDataUtil.ListFindValue(pplist,"paperId",t.getPaperId()+"","paperName").toString()));
	        	t.getParams().put("paperDoc", (ListDataUtil.ListFindValue(pplist,"paperId",t.getPaperId()+"","paperDoc").toString()));
	        }
        }
        return getDataTable(list);
    }

    /**
     * 导出考试管理列表
     */
    @RequiresPermissions("system:exam:export")
    @Log(title = "考试管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(TExam tExam)
    {
    	List<TLevel> lvlist = levelService.selectTLevelList(new TLevel());
    	List<TSubject> sulist = subjectService.selectTSubjectList(new TSubject());
    	List<TPaper> pplist = paperService.selectTPaperList(new TPaper());
        List<TExam> list = tExamService.selectTExamList(tExam);
        for (TExam te : list) {
        	te.setExamMode(DictUtils.getDictCache("sys_exam_mode",te.getExamMode()));
        	te.setState(DictUtils.getDictCache("sys_exam_state",te.getState()));
        	te.getParams().put("levelName", (ListDataUtil.ListFindValue(lvlist,"levelId",te.getLevelId()+"","levelName").toString()));
        	te.getParams().put("subjectName", (ListDataUtil.ListFindValue(sulist,"subjectId",te.getSubjectId()+"","subjectName").toString()));
        	te.getParams().put("paperName", (ListDataUtil.ListFindValue(pplist,"paperId",te.getPaperId()+"","paperName").toString()));
        	te.setPaperName(te.getParams().get("paperName").toString());
        	te.setLevelName(te.getParams().get("levelName").toString());
        	te.setSubjectName(te.getParams().get("subjectName").toString());
		}
        ExcelUtil<TExam> util = new ExcelUtil<TExam>(TExam.class);
        return util.exportExcel(list, "exam");
    }

    /**
     * 新增考试管理
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存考试管理
     */
    @RequiresPermissions("system:exam:add")
    @Log(title = "考试管理", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(TExam tExam)
    {
        return toAjax(tExamService.insertTExam(tExam));
    }

    /**
     * 修改考试管理
     */
    @GetMapping("/edit/{examId}")
    public String edit(@PathVariable("examId") Long examId, ModelMap mmap)
    {
        TExam tExam = tExamService.selectTExamById(examId);
        tExam.getParams().put("paperName", paperService.selectTPaperById(tExam.getPaperId()).getPaperName());
        mmap.put("tExam", tExam);
        return prefix + "/edit";
    }

    /**
     * 修改保存考试管理
     */
    @RequiresPermissions("system:exam:edit")
    @Log(title = "考试管理", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(TExam tExam)
    {
        return toAjax(tExamService.updateTExam(tExam));
    }

    /**
     * 删除考试管理
     */
    @RequiresPermissions("system:exam:remove")
    @Log(title = "考试管理", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(tExamService.deleteTExamByIds(ids));
    }
}
