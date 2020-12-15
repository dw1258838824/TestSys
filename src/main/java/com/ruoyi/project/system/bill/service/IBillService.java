package com.ruoyi.project.system.bill.service;

import java.util.List;
import com.ruoyi.project.system.bill.domain.Bill;

/**
 * 账单Service接口
 * 
 * @author ruoyi
 * @date 2020-06-22
 */
public interface IBillService 
{
    /**
     * 查询账单
     * 
     * @param billId 账单ID
     * @return 账单
     */
    public Bill selectBillById(Long billId);

    /**
     * 查询账单列表
     * 
     * @param bill 账单
     * @return 账单集合
     */
    public List<Bill> selectBillList(Bill bill);

    /**
     * 新增账单
     * 
     * @param bill 账单
     * @return 结果
     */
    public int insertBill(Bill bill);

    /**
     * 修改账单
     * 
     * @param bill 账单
     * @return 结果
     */
    public int updateBill(Bill bill);

    /**
     * 批量删除账单
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteBillByIds(String ids);

    /**
     * 删除账单信息
     * 
     * @param billId 账单ID
     * @return 结果
     */
    public int deleteBillById(Long billId);

    public int updatePayStateByBillNo(Bill bill);

    public Bill selectBillByBillNo(String billNo);

}
