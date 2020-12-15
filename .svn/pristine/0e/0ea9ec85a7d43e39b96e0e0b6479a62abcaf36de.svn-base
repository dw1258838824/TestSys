package com.ruoyi.project.system.bill.mapper;

import java.util.List;
import com.ruoyi.project.system.bill.domain.Bill;

/**
 * 账单Mapper接口
 * 
 * @author ruoyi
 * @date 2020-06-22
 */
public interface BillMapper 
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
     * 删除账单
     * 
     * @param billId 账单ID
     * @return 结果
     */
    public int deleteBillById(Long billId);

    /**
     * 批量删除账单
     * 
     * @param billIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteBillByIds(String[] billIds);

    public int updatePayStateByBillNo(Bill bill);

    public Bill selectBillByBillNo(String billNo);

}
