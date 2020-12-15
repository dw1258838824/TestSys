package com.ruoyi.project.system.register.mapper;

import java.util.List;
import com.ruoyi.project.system.register.domain.TExamRegister;

/**
 * 报名管理Mapper接口
 * 
 * @author ruoyi
 * @date 2020-06-08
 */
public interface TExamRegisterMapper 
{
    /**
     * 查询报名管理
     * 
     * @param examRegisterId 报名管理ID
     * @return 报名管理
     */
    public TExamRegister selectTExamRegisterById(Long examRegisterId);

    /**
     * 查询报名管理列表
     * 
     * @param tExamRegister 报名管理
     * @return 报名管理集合
     */
    public List<TExamRegister> selectTExamRegisterList(TExamRegister tExamRegister);

    /**
     * 新增报名管理
     * 
     * @param tExamRegister 报名管理
     * @return 结果
     */
    public int insertTExamRegister(TExamRegister tExamRegister);

    /**
     * 修改报名管理
     * 
     * @param tExamRegister 报名管理
     * @return 结果
     */
    public int updateTExamRegister(TExamRegister tExamRegister);

    /**
     * 删除报名管理
     * 
     * @param examRegisterId 报名管理ID
     * @return 结果
     */
    public int deleteTExamRegisterById(Long examRegisterId);

    /**
     * 批量删除报名管理
     * 
     * @param examRegisterIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteTExamRegisterByIds(String[] examRegisterIds);
    
    /**
     * 查询报名
     * 
     * @return 报名管理
     */
    public TExamRegister selectTExamRegisterByStudentExam(TExamRegister register);

}
