package com.ruoyi.project.system.type.service;

import java.util.List;
import com.ruoyi.project.system.type.domain.TQuestionType;

/**
 * 题型管理Service接口
 * 
 * @author ruoyi
 * @date 2020-05-27
 */
public interface ITQuestionTypeService 
{
    /**
     * 查询题型管理
     * 
     * @param questionTypeId 题型管理ID
     * @return 题型管理
     */
    public TQuestionType selectTQuestionTypeById(Integer questionTypeId);

    /**
     * 查询题型管理列表
     * 
     * @param tQuestionType 题型管理
     * @return 题型管理集合
     */
    public List<TQuestionType> selectTQuestionTypeList(TQuestionType tQuestionType);

    /**
     * 新增题型管理
     * 
     * @param tQuestionType 题型管理
     * @return 结果
     */
    public int insertTQuestionType(TQuestionType tQuestionType);

    /**
     * 修改题型管理
     * 
     * @param tQuestionType 题型管理
     * @return 结果
     */
    public int updateTQuestionType(TQuestionType tQuestionType);

    /**
     * 批量删除题型管理
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteTQuestionTypeByIds(String ids);

    /**
     * 删除题型管理信息
     * 
     * @param questionTypeId 题型管理ID
     * @return 结果
     */
    public int deleteTQuestionTypeById(Integer questionTypeId);
}
