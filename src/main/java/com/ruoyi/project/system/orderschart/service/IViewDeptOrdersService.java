package com.ruoyi.project.system.orderschart.service;

import java.util.List;
import com.ruoyi.project.system.orderschart.domain.ViewDeptOrders;

/**
 * 订单统计Service接口
 * 
 * @author ruoyi
 * @date 2020-07-27
 */
public interface IViewDeptOrdersService 
{
    /**
     * 查询订单统计
     * 
     * @param deptId 订单统计ID
     * @return 订单统计
     */
    public ViewDeptOrders selectViewDeptOrdersById(Long deptId);

    /**
     * 查询订单统计列表
     * 
     * @param viewDeptOrders 订单统计
     * @return 订单统计集合
     */
    public List<ViewDeptOrders> selectViewDeptOrdersList(ViewDeptOrders viewDeptOrders);

    /**
     * 新增订单统计
     * 
     * @param viewDeptOrders 订单统计
     * @return 结果
     */
    public int insertViewDeptOrders(ViewDeptOrders viewDeptOrders);

    /**
     * 修改订单统计
     * 
     * @param viewDeptOrders 订单统计
     * @return 结果
     */
    public int updateViewDeptOrders(ViewDeptOrders viewDeptOrders);

    /**
     * 批量删除订单统计
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteViewDeptOrdersByIds(String ids);

    /**
     * 删除订单统计信息
     * 
     * @param deptId 订单统计ID
     * @return 结果
     */
    public int deleteViewDeptOrdersById(Long deptId);
}
