package com.ruoyi.project.system.type.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.system.type.mapper.TQuestionTypeMapper;
import com.ruoyi.project.system.type.domain.TQuestionType;
import com.ruoyi.project.system.type.service.ITQuestionTypeService;
import com.ruoyi.common.utils.text.Convert;

/**
 * 题型管理Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-05-27
 */
@Service
public class TQuestionTypeServiceImpl implements ITQuestionTypeService 
{
    @Autowired
    private TQuestionTypeMapper tQuestionTypeMapper;

    /**
     * 查询题型管理
     * 
     * @param questionTypeId 题型管理ID
     * @return 题型管理
     */
    @Override
    public TQuestionType selectTQuestionTypeById(Integer questionTypeId)
    {
        return tQuestionTypeMapper.selectTQuestionTypeById(questionTypeId);
    }

    /**
     * 查询题型管理列表
     * 
     * @param tQuestionType 题型管理
     * @return 题型管理
     */
    @Override
    public List<TQuestionType> selectTQuestionTypeList(TQuestionType tQuestionType)
    {
        return tQuestionTypeMapper.selectTQuestionTypeList(tQuestionType);
    }

    /**
     * 新增题型管理
     * 
     * @param tQuestionType 题型管理
     * @return 结果
     */
    @Override
    public int insertTQuestionType(TQuestionType tQuestionType)
    {
        return tQuestionTypeMapper.insertTQuestionType(tQuestionType);
    }

    /**
     * 修改题型管理
     * 
     * @param tQuestionType 题型管理
     * @return 结果
     */
    @Override
    public int updateTQuestionType(TQuestionType tQuestionType)
    {
        return tQuestionTypeMapper.updateTQuestionType(tQuestionType);
    }

    /**
     * 删除题型管理对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteTQuestionTypeByIds(String ids)
    {
        return tQuestionTypeMapper.deleteTQuestionTypeByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除题型管理信息
     * 
     * @param questionTypeId 题型管理ID
     * @return 结果
     */
    @Override
    public int deleteTQuestionTypeById(Integer questionTypeId)
    {
        return tQuestionTypeMapper.deleteTQuestionTypeById(questionTypeId);
    }
}
