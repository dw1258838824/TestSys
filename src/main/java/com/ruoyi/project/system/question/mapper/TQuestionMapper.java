package com.ruoyi.project.system.question.mapper;

import java.util.List;
import java.util.Map;

import com.ruoyi.project.system.question.domain.TQuestion;

/**
 * 题库Mapper接口
 * 
 * @author ruoyi
 * @date 2020-05-27
 */
public interface TQuestionMapper 
{
    /**
     * 查询题库
     * 
     * @param questionId 题库ID
     * @return 题库
     */
    public TQuestion selectTQuestionById(Long questionId);

    /**
     * 查询题库列表
     * 
     * @param tQuestion 题库
     * @return 题库集合
     */
    public List<TQuestion> selectTQuestionList(TQuestion tQuestion);

    /**
     * 新增题库
     * 
     * @param tQuestion 题库
     * @return 结果
     */
    public int insertTQuestion(TQuestion tQuestion);

    /**
     * 批量新增题库
     * 
     * @param tQuestion 题库
     * @return 结果
     */
    public int insertBatchTQuestion(List<TQuestion> list);

    /**
     * 修改题库
     * 
     * @param tQuestion 题库
     * @return 结果
     */
    public int updateTQuestion(TQuestion tQuestion);

    /**
     * 删除题库
     * 
     * @param questionId 题库ID
     * @return 结果
     */
    public int deleteTQuestionById(Long questionId);

    /**
     * 批量删除题库
     * 
     * @param questionIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteTQuestionByIds(String[] questionIds);
    
    /**
     * 获取随机抽取题目集合
     * @param parm
     * @return
     */
    public List<TQuestion> selectRandList(Map<String, Object> parm);
}
