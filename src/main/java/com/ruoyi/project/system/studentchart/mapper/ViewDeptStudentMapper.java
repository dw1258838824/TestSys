package com.ruoyi.project.system.studentchart.mapper;

import java.util.List;
import com.ruoyi.project.system.studentchart.domain.ViewDeptStudent;

/**
 * 学员统计Mapper接口
 * 
 * @author ruoyi
 * @date 2020-07-27
 */
public interface ViewDeptStudentMapper 
{
    /**
     * 查询学员统计
     * 
     * @param deptId 学员统计ID
     * @return 学员统计
     */
    public ViewDeptStudent selectViewDeptStudentById(Long deptId);

    /**
     * 查询学员统计列表
     * 
     * @param viewDeptStudent 学员统计
     * @return 学员统计集合
     */
    public List<ViewDeptStudent> selectViewDeptStudentList(ViewDeptStudent viewDeptStudent);

    /**
     * 新增学员统计
     * 
     * @param viewDeptStudent 学员统计
     * @return 结果
     */
    public int insertViewDeptStudent(ViewDeptStudent viewDeptStudent);

    /**
     * 修改学员统计
     * 
     * @param viewDeptStudent 学员统计
     * @return 结果
     */
    public int updateViewDeptStudent(ViewDeptStudent viewDeptStudent);

    /**
     * 删除学员统计
     * 
     * @param deptId 学员统计ID
     * @return 结果
     */
    public int deleteViewDeptStudentById(Long deptId);

    /**
     * 批量删除学员统计
     * 
     * @param deptIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteViewDeptStudentByIds(String[] deptIds);
}
