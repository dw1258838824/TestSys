package com.ruoyi.project.system.teacherschart.service;

import java.util.List;
import com.ruoyi.project.system.teacherschart.domain.ViewDeptTeachers;

/**
 * 老师统计Service接口
 * 
 * @author ruoyi
 * @date 2020-07-27
 */
public interface IViewDeptTeachersService 
{
    /**
     * 查询老师统计
     * 
     * @param deptId 老师统计ID
     * @return 老师统计
     */
    public ViewDeptTeachers selectViewDeptTeachersById(Long deptId);

    /**
     * 查询老师统计列表
     * 
     * @param viewDeptTeachers 老师统计
     * @return 老师统计集合
     */
    public List<ViewDeptTeachers> selectViewDeptTeachersList(ViewDeptTeachers viewDeptTeachers);

    /**
     * 新增老师统计
     * 
     * @param viewDeptTeachers 老师统计
     * @return 结果
     */
    public int insertViewDeptTeachers(ViewDeptTeachers viewDeptTeachers);

    /**
     * 修改老师统计
     * 
     * @param viewDeptTeachers 老师统计
     * @return 结果
     */
    public int updateViewDeptTeachers(ViewDeptTeachers viewDeptTeachers);

    /**
     * 批量删除老师统计
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteViewDeptTeachersByIds(String ids);

    /**
     * 删除老师统计信息
     * 
     * @param deptId 老师统计ID
     * @return 结果
     */
    public int deleteViewDeptTeachersById(Long deptId);
}
