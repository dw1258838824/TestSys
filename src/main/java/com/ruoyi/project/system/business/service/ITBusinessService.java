package com.ruoyi.project.system.business.service;

import java.util.List;
import com.ruoyi.project.system.business.domain.TBusiness;

/**
 * 商务洽谈Service接口
 * 
 * @author ruoyi
 * @date 2020-07-16
 */
public interface ITBusinessService 
{
    /**
     * 查询商务洽谈
     * 
     * @param businessId 商务洽谈ID
     * @return 商务洽谈
     */
    public TBusiness selectTBusinessById(Long businessId);

    /**
     * 查询商务洽谈列表
     * 
     * @param tBusiness 商务洽谈
     * @return 商务洽谈集合
     */
    public List<TBusiness> selectTBusinessList(TBusiness tBusiness);

    /**
     * 新增商务洽谈
     * 
     * @param tBusiness 商务洽谈
     * @return 结果
     */
    public int insertTBusiness(TBusiness tBusiness);

    /**
     * 修改商务洽谈
     * 
     * @param tBusiness 商务洽谈
     * @return 结果
     */
    public int updateTBusiness(TBusiness tBusiness);

    /**
     * 批量删除商务洽谈
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteTBusinessByIds(String ids);

    /**
     * 删除商务洽谈信息
     * 
     * @param businessId 商务洽谈ID
     * @return 结果
     */
    public int deleteTBusinessById(Long businessId);
}
