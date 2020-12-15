package com.ruoyi.project.system.studentchart.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.system.studentchart.mapper.ViewDeptStudentMapper;
import com.ruoyi.project.system.studentchart.domain.ViewDeptStudent;
import com.ruoyi.project.system.studentchart.service.IViewDeptStudentService;
import com.ruoyi.common.utils.text.Convert;
import com.ruoyi.framework.aspectj.lang.annotation.DataScope;

/**
 * 学员统计Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-07-27
 */
@Service
public class ViewDeptStudentServiceImpl implements IViewDeptStudentService 
{
    @Autowired
    private ViewDeptStudentMapper viewDeptStudentMapper;

    /**
     * 查询学员统计
     * 
     * @param deptId 学员统计ID
     * @return 学员统计
     */
    @Override
    public ViewDeptStudent selectViewDeptStudentById(Long deptId)
    {
        return viewDeptStudentMapper.selectViewDeptStudentById(deptId);
    }

    /**
     * 查询学员统计列表
     * 
     * @param viewDeptStudent 学员统计
     * @return 学员统计
     */
    @Override
    @DataScope(deptAlias = "view_dept_student")
    public List<ViewDeptStudent> selectViewDeptStudentList(ViewDeptStudent viewDeptStudent)
    {
        return viewDeptStudentMapper.selectViewDeptStudentList(viewDeptStudent);
    }

    /**
     * 新增学员统计
     * 
     * @param viewDeptStudent 学员统计
     * @return 结果
     */
    @Override
    public int insertViewDeptStudent(ViewDeptStudent viewDeptStudent)
    {
        return viewDeptStudentMapper.insertViewDeptStudent(viewDeptStudent);
    }

    /**
     * 修改学员统计
     * 
     * @param viewDeptStudent 学员统计
     * @return 结果
     */
    @Override
    public int updateViewDeptStudent(ViewDeptStudent viewDeptStudent)
    {
        return viewDeptStudentMapper.updateViewDeptStudent(viewDeptStudent);
    }

    /**
     * 删除学员统计对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteViewDeptStudentByIds(String ids)
    {
        return viewDeptStudentMapper.deleteViewDeptStudentByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除学员统计信息
     * 
     * @param deptId 学员统计ID
     * @return 结果
     */
    @Override
    public int deleteViewDeptStudentById(Long deptId)
    {
        return viewDeptStudentMapper.deleteViewDeptStudentById(deptId);
    }
}
