package com.ruoyi.project.system.register.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.system.register.mapper.TExamRegisterMapper;
import com.ruoyi.project.system.register.domain.TExamRegister;
import com.ruoyi.project.system.register.service.ITExamRegisterService;
import com.ruoyi.common.utils.text.Convert;
import com.ruoyi.framework.aspectj.lang.annotation.DataScope;

/**
 * 报名管理Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-06-08
 */
@Service
public class TExamRegisterServiceImpl implements ITExamRegisterService 
{
    @Autowired
    private TExamRegisterMapper tExamRegisterMapper;

    /**
     * 查询报名管理
     * 
     * @param examRegisterId 报名管理ID
     * @return 报名管理
     */
    @Override
    public TExamRegister selectTExamRegisterById(Long examRegisterId)
    {
        return tExamRegisterMapper.selectTExamRegisterById(examRegisterId);
    }

    /**
     * 查询报名管理列表
     * 
     * @param tExamRegister 报名管理
     * @return 报名管理
     */
    @Override
    @DataScope(deptAlias = "u")
    public List<TExamRegister> selectTExamRegisterList(TExamRegister tExamRegister)
    {
        return tExamRegisterMapper.selectTExamRegisterList(tExamRegister);
    }

    /**
     * 新增报名管理
     * 
     * @param tExamRegister 报名管理
     * @return 结果
     */
    @Override
    public int insertTExamRegister(TExamRegister tExamRegister)
    {
        return tExamRegisterMapper.insertTExamRegister(tExamRegister);
    }

    /**
     * 修改报名管理
     * 
     * @param tExamRegister 报名管理
     * @return 结果
     */
    @Override
    public int updateTExamRegister(TExamRegister tExamRegister)
    {
        return tExamRegisterMapper.updateTExamRegister(tExamRegister);
    }

    /**
     * 删除报名管理对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteTExamRegisterByIds(String ids)
    {
        return tExamRegisterMapper.deleteTExamRegisterByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除报名管理信息
     * 
     * @param examRegisterId 报名管理ID
     * @return 结果
     */
    @Override
    public int deleteTExamRegisterById(Long examRegisterId)
    {
        return tExamRegisterMapper.deleteTExamRegisterById(examRegisterId);
    }
    

    /**
     * 查询报名
     * 
     * @return 报名管理
     */
    public TExamRegister selectTExamRegisterByStudentExam(TExamRegister register) {
    	return tExamRegisterMapper.selectTExamRegisterByStudentExam(register);
    }
}
