package com.ruoyi.project.system.countchart.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.system.countchart.mapper.ViewRoleCountMapper;
import com.ruoyi.project.system.countchart.domain.ViewRoleCount;
import com.ruoyi.project.system.countchart.service.IViewRoleCountService;
import com.ruoyi.common.utils.text.Convert;

/**
 * 角色人员统计Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-07-27
 */
@Service
public class ViewRoleCountServiceImpl implements IViewRoleCountService 
{
    @Autowired
    private ViewRoleCountMapper viewRoleCountMapper;

    /**
     * 查询角色人员统计
     * 
     * @param roleId 角色人员统计ID
     * @return 角色人员统计
     */
    @Override
    public ViewRoleCount selectViewRoleCountById(Long roleId)
    {
        return viewRoleCountMapper.selectViewRoleCountById(roleId);
    }

    /**
     * 查询角色人员统计列表
     * 
     * @param viewRoleCount 角色人员统计
     * @return 角色人员统计
     */
    @Override
    public List<ViewRoleCount> selectViewRoleCountList(ViewRoleCount viewRoleCount)
    {
        return viewRoleCountMapper.selectViewRoleCountList(viewRoleCount);
    }

    /**
     * 新增角色人员统计
     * 
     * @param viewRoleCount 角色人员统计
     * @return 结果
     */
    @Override
    public int insertViewRoleCount(ViewRoleCount viewRoleCount)
    {
        return viewRoleCountMapper.insertViewRoleCount(viewRoleCount);
    }

    /**
     * 修改角色人员统计
     * 
     * @param viewRoleCount 角色人员统计
     * @return 结果
     */
    @Override
    public int updateViewRoleCount(ViewRoleCount viewRoleCount)
    {
        return viewRoleCountMapper.updateViewRoleCount(viewRoleCount);
    }

    /**
     * 删除角色人员统计对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteViewRoleCountByIds(String ids)
    {
        return viewRoleCountMapper.deleteViewRoleCountByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除角色人员统计信息
     * 
     * @param roleId 角色人员统计ID
     * @return 结果
     */
    @Override
    public int deleteViewRoleCountById(Long roleId)
    {
        return viewRoleCountMapper.deleteViewRoleCountById(roleId);
    }
}
