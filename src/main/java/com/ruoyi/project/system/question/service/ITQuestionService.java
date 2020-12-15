package com.ruoyi.project.system.question.service;

import java.util.List;
import com.ruoyi.project.system.question.domain.TQuestion;
import com.ruoyi.project.system.user.domain.User;

/**
 * 题库Service接口
 * 
 * @author ruoyi
 * @date 2020-05-27
 */
public interface ITQuestionService 
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
     * 修改题库
     * 
     * @param tQuestion 题库
     * @return 结果
     */
    public int updateTQuestion(TQuestion tQuestion);

    /**
     * 批量删除题库
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteTQuestionByIds(String ids);

    /**
     * 删除题库信息
     * 
     * @param questionId 题库ID
     * @return 结果
     */
    public int deleteTQuestionById(Long questionId);
    

    /**
     * 导入数据
     * @param 
     * @param 
     * @return 结果
     */
    public String importQuestion(List<TQuestion> list);
}
