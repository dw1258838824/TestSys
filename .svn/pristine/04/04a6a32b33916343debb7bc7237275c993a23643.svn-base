package com.ruoyi.project.system.chart.service;

import java.util.List;
import java.util.Map;

import com.ruoyi.project.system.chart.domain.ViewExamChart;

/**
 * 考试统计Service接口
 * 
 * @author ruoyi
 * @date 2020-07-19
 */
public interface IViewExamChartService 
{
    /**
     * 查询考试统计
     * 
     * @param examId 考试统计ID
     * @return 考试统计
     */
    public ViewExamChart selectViewExamChartById(Long examId);

    /**
     * 查询考试统计列表
     * 
     * @param viewExamChart 考试统计
     * @return 考试统计集合
     */
    public List<ViewExamChart> selectViewExamChartList(ViewExamChart viewExamChart);

    /**
     * 新增考试统计
     * 
     * @param viewExamChart 考试统计
     * @return 结果
     */
    public int insertViewExamChart(ViewExamChart viewExamChart);

    /**
     * 修改考试统计
     * 
     * @param viewExamChart 考试统计
     * @return 结果
     */
    public int updateViewExamChart(ViewExamChart viewExamChart);

    /**
     * 批量删除考试统计
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteViewExamChartByIds(String ids);

    /**
     * 删除考试统计信息
     * 
     * @param examId 考试统计ID
     * @return 结果
     */
    public int deleteViewExamChartById(Long examId);
    

    /**
     * 获取考题排行
     * 
     * @param examId 考试统计ID
     * @return 结果
     */
    public List<Map<String, Object>> selectQuestionChart(Map<String, Object> parm);
}
