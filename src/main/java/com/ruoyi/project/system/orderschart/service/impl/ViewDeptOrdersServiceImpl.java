package com.ruoyi.project.system.orderschart.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.system.orderschart.mapper.ViewDeptOrdersMapper;
import com.ruoyi.project.system.orderschart.domain.ViewDeptOrders;
import com.ruoyi.project.system.orderschart.service.IViewDeptOrdersService;
import com.ruoyi.common.utils.text.Convert;
import com.ruoyi.framework.aspectj.lang.annotation.DataScope;

/**
 * 订单统计Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-07-27
 */
@Service
public class ViewDeptOrdersServiceImpl implements IViewDeptOrdersService 
{
    @Autowired
    private ViewDeptOrdersMapper viewDeptOrdersMapper;

    /**
     * 查询订单统计
     * 
     * @param deptId 订单统计ID
     * @return 订单统计
     */
    @Override
    public ViewDeptOrders selectViewDeptOrdersById(Long deptId)
    {
        return viewDeptOrdersMapper.selectViewDeptOrdersById(deptId);
    }

    /**
     * 查询订单统计列表
     * 
     * @param viewDeptOrders 订单统计
     * @return 订单统计
     */
    @Override
    @DataScope(deptAlias = "view_dept_orders")
    public List<ViewDeptOrders> selectViewDeptOrdersList(ViewDeptOrders viewDeptOrders)
    {
        return viewDeptOrdersMapper.selectViewDeptOrdersList(viewDeptOrders);
    }

    /**
     * 新增订单统计
     * 
     * @param viewDeptOrders 订单统计
     * @return 结果
     */
    @Override
    public int insertViewDeptOrders(ViewDeptOrders viewDeptOrders)
    {
        return viewDeptOrdersMapper.insertViewDeptOrders(viewDeptOrders);
    }

    /**
     * 修改订单统计
     * 
     * @param viewDeptOrders 订单统计
     * @return 结果
     */
    @Override
    public int updateViewDeptOrders(ViewDeptOrders viewDeptOrders)
    {
        return viewDeptOrdersMapper.updateViewDeptOrders(viewDeptOrders);
    }

    /**
     * 删除订单统计对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteViewDeptOrdersByIds(String ids)
    {
        return viewDeptOrdersMapper.deleteViewDeptOrdersByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除订单统计信息
     * 
     * @param deptId 订单统计ID
     * @return 结果
     */
    @Override
    public int deleteViewDeptOrdersById(Long deptId)
    {
        return viewDeptOrdersMapper.deleteViewDeptOrdersById(deptId);
    }
}
