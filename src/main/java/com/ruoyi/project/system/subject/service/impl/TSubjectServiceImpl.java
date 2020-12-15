package com.ruoyi.project.system.subject.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.system.subject.mapper.TSubjectMapper;
import com.ruoyi.project.system.subject.domain.TSubject;
import com.ruoyi.project.system.subject.service.ITSubjectService;
import com.ruoyi.common.utils.text.Convert;

/**
 * 科目管理Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-05-27
 */
@Service
public class TSubjectServiceImpl implements ITSubjectService 
{
    @Autowired
    private TSubjectMapper tSubjectMapper;

    /**
     * 查询科目管理
     * 
     * @param subjectId 科目管理ID
     * @return 科目管理
     */
    @Override
    public TSubject selectTSubjectById(Integer subjectId)
    {
        return tSubjectMapper.selectTSubjectById(subjectId);
    }

    /**
     * 查询科目管理列表
     * 
     * @param tSubject 科目管理
     * @return 科目管理
     */
    @Override
    public List<TSubject> selectTSubjectList(TSubject tSubject)
    {
        return tSubjectMapper.selectTSubjectList(tSubject);
    }

    /**
     * 新增科目管理
     * 
     * @param tSubject 科目管理
     * @return 结果
     */
    @Override
    public int insertTSubject(TSubject tSubject)
    {
        return tSubjectMapper.insertTSubject(tSubject);
    }

    /**
     * 修改科目管理
     * 
     * @param tSubject 科目管理
     * @return 结果
     */
    @Override
    public int updateTSubject(TSubject tSubject)
    {
        return tSubjectMapper.updateTSubject(tSubject);
    }

    /**
     * 删除科目管理对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteTSubjectByIds(String ids)
    {
        return tSubjectMapper.deleteTSubjectByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除科目管理信息
     * 
     * @param subjectId 科目管理ID
     * @return 结果
     */
    @Override
    public int deleteTSubjectById(Integer subjectId)
    {
        return tSubjectMapper.deleteTSubjectById(subjectId);
    }
}
