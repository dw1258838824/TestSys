package com.ruoyi.project.system.subject.service;

import java.util.List;
import com.ruoyi.project.system.subject.domain.TSubject;

/**
 * 科目管理Service接口
 * 
 * @author ruoyi
 * @date 2020-05-27
 */
public interface ITSubjectService 
{
    /**
     * 查询科目管理
     * 
     * @param subjectId 科目管理ID
     * @return 科目管理
     */
    public TSubject selectTSubjectById(Integer subjectId);

    /**
     * 查询科目管理列表
     * 
     * @param tSubject 科目管理
     * @return 科目管理集合
     */
    public List<TSubject> selectTSubjectList(TSubject tSubject);

    /**
     * 新增科目管理
     * 
     * @param tSubject 科目管理
     * @return 结果
     */
    public int insertTSubject(TSubject tSubject);

    /**
     * 修改科目管理
     * 
     * @param tSubject 科目管理
     * @return 结果
     */
    public int updateTSubject(TSubject tSubject);

    /**
     * 批量删除科目管理
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteTSubjectByIds(String ids);

    /**
     * 删除科目管理信息
     * 
     * @param subjectId 科目管理ID
     * @return 结果
     */
    public int deleteTSubjectById(Integer subjectId);
}
