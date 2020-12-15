package com.ruoyi.project.system.reg.service;

import java.util.List;
import com.ruoyi.project.system.reg.domain.TDeptReg;

/**
 * 机构批量报名Service接口
 * 
 * @author ruoyi
 * @date 2020-07-31
 */
public interface ITDeptRegService 
{
    /**
     * 查询机构批量报名
     * 
     * @param deptRegId 机构批量报名ID
     * @return 机构批量报名
     */
    public TDeptReg selectTDeptRegById(Long deptRegId);

    /**
     * 查询机构批量报名列表
     * 
     * @param tDeptReg 机构批量报名
     * @return 机构批量报名集合
     */
    public List<TDeptReg> selectTDeptRegList(TDeptReg tDeptReg);

    /**
     * 新增机构批量报名
     * 
     * @param tDeptReg 机构批量报名
     * @return 结果
     */
    public int insertTDeptReg(TDeptReg tDeptReg);

    /**
     * 修改机构批量报名
     * 
     * @param tDeptReg 机构批量报名
     * @return 结果
     */
    public int updateTDeptReg(TDeptReg tDeptReg);

    /**
     * 批量删除机构批量报名
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteTDeptRegByIds(String ids);

    /**
     * 删除机构批量报名信息
     * 
     * @param deptRegId 机构批量报名ID
     * @return 结果
     */
    public int deleteTDeptRegById(Long deptRegId);
}
