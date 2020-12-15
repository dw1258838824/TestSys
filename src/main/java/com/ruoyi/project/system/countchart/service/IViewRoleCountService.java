package com.ruoyi.project.system.countchart.service;

import java.util.List;
import com.ruoyi.project.system.countchart.domain.ViewRoleCount;

/**
 * 角色人员统计Service接口
 * 
 * @author ruoyi
 * @date 2020-07-27
 */
public interface IViewRoleCountService 
{
    /**
     * 查询角色人员统计
     * 
     * @param roleId 角色人员统计ID
     * @return 角色人员统计
     */
    public ViewRoleCount selectViewRoleCountById(Long roleId);

    /**
     * 查询角色人员统计列表
     * 
     * @param viewRoleCount 角色人员统计
     * @return 角色人员统计集合
     */
    public List<ViewRoleCount> selectViewRoleCountList(ViewRoleCount viewRoleCount);

    /**
     * 新增角色人员统计
     * 
     * @param viewRoleCount 角色人员统计
     * @return 结果
     */
    public int insertViewRoleCount(ViewRoleCount viewRoleCount);

    /**
     * 修改角色人员统计
     * 
     * @param viewRoleCount 角色人员统计
     * @return 结果
     */
    public int updateViewRoleCount(ViewRoleCount viewRoleCount);

    /**
     * 批量删除角色人员统计
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteViewRoleCountByIds(String ids);

    /**
     * 删除角色人员统计信息
     * 
     * @param roleId 角色人员统计ID
     * @return 结果
     */
    public int deleteViewRoleCountById(Long roleId);
}
