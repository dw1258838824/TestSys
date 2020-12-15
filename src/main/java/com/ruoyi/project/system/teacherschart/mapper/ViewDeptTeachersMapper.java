package com.ruoyi.project.system.teacherschart.mapper;

import java.util.List;
import com.ruoyi.project.system.teacherschart.domain.ViewDeptTeachers;

/**
 * 老师统计Mapper接口
 * 
 * @author ruoyi
 * @date 2020-07-27
 */
public interface ViewDeptTeachersMapper 
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
     * 删除老师统计
     * 
     * @param deptId 老师统计ID
     * @return 结果
     */
    public int deleteViewDeptTeachersById(Long deptId);

    /**
     * 批量删除老师统计
     * 
     * @param deptIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteViewDeptTeachersByIds(String[] deptIds);
}
