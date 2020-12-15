package com.ruoyi.project.system.register.service;

import java.util.List;
import com.ruoyi.project.system.register.domain.TExamRegister;

/**
 * 报名管理Service接口
 * 
 * @author ruoyi
 * @date 2020-06-08
 */
public interface ITExamRegisterService 
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
     * 批量删除报名管理
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteTExamRegisterByIds(String ids);

    /**
     * 删除报名管理信息
     * 
     * @param examRegisterId 报名管理ID
     * @return 结果
     */
    public int deleteTExamRegisterById(Long examRegisterId);
    
    /**
     * 查询报名
     * 
     * @return 报名管理
     */
    public TExamRegister selectTExamRegisterByStudentExam(TExamRegister register);

}
