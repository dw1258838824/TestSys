package com.ruoyi.project.system.exam.mapper;

import java.util.List;
import java.util.Map;

import com.ruoyi.project.system.exam.domain.TExam;

/**
 * 考试管理Mapper接口
 * 
 * @author ruoyi
 * @date 2020-06-04
 */
public interface TExamMapper 
{
    /**
     * 查询考试管理
     * 
     * @param examId 考试管理ID
     * @return 考试管理
     */
    public TExam selectTExamById(Long examId);

    /**
     * 查询考试管理列表
     * 
     * @param tExam 考试管理
     * @return 考试管理集合
     */
    public List<TExam> selectTExamList(TExam tExam);

    /**
     * 查询考试数据统计
     * 
     */
    public List<Map<String, Object>> selectTExamChart(TExam tExam);
    /**
     * 新增考试管理
     * 
     * @param tExam 考试管理
     * @return 结果
     */
    public int insertTExam(TExam tExam);

    /**
     * 修改考试管理
     * 
     * @param tExam 考试管理
     * @return 结果
     */
    public int updateTExam(TExam tExam);

    /**
     * 删除考试管理
     * 
     * @param examId 考试管理ID
     * @return 结果
     */
    public int deleteTExamById(Long examId);

    /**
     * 批量删除考试管理
     * 
     * @param examIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteTExamByIds(String[] examIds);


    public TExam apiGet(TExam exam);

    public List<TExam> apiList(TExam exam);
}
