package com.ruoyi.project.system.teacherschart.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.system.teacherschart.mapper.ViewDeptTeachersMapper;
import com.ruoyi.project.system.teacherschart.domain.ViewDeptTeachers;
import com.ruoyi.project.system.teacherschart.service.IViewDeptTeachersService;
import com.ruoyi.common.utils.text.Convert;
import com.ruoyi.framework.aspectj.lang.annotation.DataScope;

/**
 * 老师统计Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-07-27
 */
@Service
public class ViewDeptTeachersServiceImpl implements IViewDeptTeachersService 
{
    @Autowired
    private ViewDeptTeachersMapper viewDeptTeachersMapper;

    /**
     * 查询老师统计
     * 
     * @param deptId 老师统计ID
     * @return 老师统计
     */
    @Override
    public ViewDeptTeachers selectViewDeptTeachersById(Long deptId)
    {
        return viewDeptTeachersMapper.selectViewDeptTeachersById(deptId);
    }

    /**
     * 查询老师统计列表
     * 
     * @param viewDeptTeachers 老师统计
     * @return 老师统计
     */
    @Override
    @DataScope(deptAlias = "view_dept_teachers")
    public List<ViewDeptTeachers> selectViewDeptTeachersList(ViewDeptTeachers viewDeptTeachers)
    {
        return viewDeptTeachersMapper.selectViewDeptTeachersList(viewDeptTeachers);
    }

    /**
     * 新增老师统计
     * 
     * @param viewDeptTeachers 老师统计
     * @return 结果
     */
    @Override
    public int insertViewDeptTeachers(ViewDeptTeachers viewDeptTeachers)
    {
        return viewDeptTeachersMapper.insertViewDeptTeachers(viewDeptTeachers);
    }

    /**
     * 修改老师统计
     * 
     * @param viewDeptTeachers 老师统计
     * @return 结果
     */
    @Override
    public int updateViewDeptTeachers(ViewDeptTeachers viewDeptTeachers)
    {
        return viewDeptTeachersMapper.updateViewDeptTeachers(viewDeptTeachers);
    }

    /**
     * 删除老师统计对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteViewDeptTeachersByIds(String ids)
    {
        return viewDeptTeachersMapper.deleteViewDeptTeachersByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除老师统计信息
     * 
     * @param deptId 老师统计ID
     * @return 结果
     */
    @Override
    public int deleteViewDeptTeachersById(Long deptId)
    {
        return viewDeptTeachersMapper.deleteViewDeptTeachersById(deptId);
    }
}
