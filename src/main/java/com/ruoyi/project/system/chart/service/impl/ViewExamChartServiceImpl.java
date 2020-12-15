package com.ruoyi.project.system.chart.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.system.chart.mapper.ViewExamChartMapper;
import com.ruoyi.project.system.chart.domain.ViewExamChart;
import com.ruoyi.project.system.chart.service.IViewExamChartService;
import com.ruoyi.common.utils.text.Convert;

/**
 * 考试统计Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-07-19
 */
@Service
public class ViewExamChartServiceImpl implements IViewExamChartService 
{
    @Autowired
    private ViewExamChartMapper viewExamChartMapper;

    /**
     * 查询考试统计
     * 
     * @param examId 考试统计ID
     * @return 考试统计
     */
    @Override
    public ViewExamChart selectViewExamChartById(Long examId)
    {
        return viewExamChartMapper.selectViewExamChartById(examId);
    }

    /**
     * 查询考试统计列表
     * 
     * @param viewExamChart 考试统计
     * @return 考试统计
     */
    @Override
    public List<ViewExamChart> selectViewExamChartList(ViewExamChart viewExamChart)
    {
        return viewExamChartMapper.selectViewExamChartList(viewExamChart);
    }

    /**
     * 新增考试统计
     * 
     * @param viewExamChart 考试统计
     * @return 结果
     */
    @Override
    public int insertViewExamChart(ViewExamChart viewExamChart)
    {
        return viewExamChartMapper.insertViewExamChart(viewExamChart);
    }

    /**
     * 修改考试统计
     * 
     * @param viewExamChart 考试统计
     * @return 结果
     */
    @Override
    public int updateViewExamChart(ViewExamChart viewExamChart)
    {
        return viewExamChartMapper.updateViewExamChart(viewExamChart);
    }

    /**
     * 删除考试统计对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteViewExamChartByIds(String ids)
    {
        return viewExamChartMapper.deleteViewExamChartByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除考试统计信息
     * 
     * @param examId 考试统计ID
     * @return 结果
     */
    @Override
    public int deleteViewExamChartById(Long examId)
    {
        return viewExamChartMapper.deleteViewExamChartById(examId);
    }
    

    /**
     * 获取考题排行
     * 
     * @param examId 考试统计ID
     * @return 结果
     */
    public List<Map<String, Object>> selectQuestionChart(Map<String, Object> parm){
    	return viewExamChartMapper.selectQuestionChart(parm);
    }
}
