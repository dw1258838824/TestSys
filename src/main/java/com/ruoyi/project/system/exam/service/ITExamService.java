package com.ruoyi.project.system.exam.service;

import java.util.List;
import com.ruoyi.project.system.exam.domain.TExam;

/**
 * 考试管理Service接口
 * 
 * @author ruoyi
 * @date 2020-06-04
 */
public interface ITExamService 
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
     * 批量删除考试管理
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteTExamByIds(String ids);

    /**
     * 删除考试管理信息
     * 
     * @param examId 考试管理ID
     * @return 结果
     */
    public int deleteTExamById(Long examId);

    public TExam apiGet(TExam exam);

    public List<TExam> apiList(TExam exam);

}
