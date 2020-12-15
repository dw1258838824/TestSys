package com.ruoyi.project.system.business.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.system.business.mapper.TBusinessMapper;
import com.ruoyi.project.system.business.domain.TBusiness;
import com.ruoyi.project.system.business.service.ITBusinessService;
import com.ruoyi.common.utils.text.Convert;

/**
 * 商务洽谈Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-07-16
 */
@Service
public class TBusinessServiceImpl implements ITBusinessService 
{
    @Autowired
    private TBusinessMapper tBusinessMapper;

    /**
     * 查询商务洽谈
     * 
     * @param businessId 商务洽谈ID
     * @return 商务洽谈
     */
    @Override
    public TBusiness selectTBusinessById(Long businessId)
    {
        return tBusinessMapper.selectTBusinessById(businessId);
    }

    /**
     * 查询商务洽谈列表
     * 
     * @param tBusiness 商务洽谈
     * @return 商务洽谈
     */
    @Override
    public List<TBusiness> selectTBusinessList(TBusiness tBusiness)
    {
        return tBusinessMapper.selectTBusinessList(tBusiness);
    }

    /**
     * 新增商务洽谈
     * 
     * @param tBusiness 商务洽谈
     * @return 结果
     */
    @Override
    public int insertTBusiness(TBusiness tBusiness)
    {
        return tBusinessMapper.insertTBusiness(tBusiness);
    }

    /**
     * 修改商务洽谈
     * 
     * @param tBusiness 商务洽谈
     * @return 结果
     */
    @Override
    public int updateTBusiness(TBusiness tBusiness)
    {
        return tBusinessMapper.updateTBusiness(tBusiness);
    }

    /**
     * 删除商务洽谈对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteTBusinessByIds(String ids)
    {
        return tBusinessMapper.deleteTBusinessByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除商务洽谈信息
     * 
     * @param businessId 商务洽谈ID
     * @return 结果
     */
    @Override
    public int deleteTBusinessById(Long businessId)
    {
        return tBusinessMapper.deleteTBusinessById(businessId);
    }
}
