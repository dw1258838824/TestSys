package com.ruoyi.project.system.bill.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.system.bill.mapper.BillMapper;
import com.ruoyi.project.system.bill.domain.Bill;
import com.ruoyi.project.system.bill.service.IBillService;
import com.ruoyi.common.utils.text.Convert;
import com.ruoyi.framework.aspectj.lang.annotation.DataScope;

/**
 * 账单Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-06-22
 */
@Service
public class BillServiceImpl implements IBillService 
{
    @Autowired
    private BillMapper billMapper;

    /**
     * 查询账单
     * 
     * @param billId 账单ID
     * @return 账单
     */
    @Override
    public Bill selectBillById(Long billId)
    {
        return billMapper.selectBillById(billId);
    }

    /**
     * 查询账单列表
     * 
     * @param bill 账单
     * @return 账单
     */
    @Override
    @DataScope(deptAlias = "d", userAlias = "u")
    public List<Bill> selectBillList(Bill bill)
    {
        return billMapper.selectBillList(bill);
    }

    /**
     * 新增账单
     * 
     * @param bill 账单
     * @return 结果
     */
    @Override
    public int insertBill(Bill bill)
    {
        return billMapper.insertBill(bill);
    }

    /**
     * 修改账单
     * 
     * @param bill 账单
     * @return 结果
     */
    @Override
    public int updateBill(Bill bill)
    {
        return billMapper.updateBill(bill);
    }

    /**
     * 删除账单对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteBillByIds(String ids)
    {
        return billMapper.deleteBillByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除账单信息
     * 
     * @param billId 账单ID
     * @return 结果
     */
    @Override
    public int deleteBillById(Long billId)
    {
        return billMapper.deleteBillById(billId);
    }


    public int updatePayStateByBillNo(Bill bill){
        return billMapper.updatePayStateByBillNo(bill);
    }

    public Bill selectBillByBillNo(String billNo){
        return billMapper.selectBillByBillNo(billNo);
    }
}
