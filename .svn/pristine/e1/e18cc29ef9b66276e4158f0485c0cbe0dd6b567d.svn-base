package com.ruoyi.project.system.company.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.system.company.mapper.TCompanyMapper;
import com.ruoyi.project.system.company.domain.TCompany;
import com.ruoyi.project.system.company.service.ITCompanyService;
import com.ruoyi.common.utils.text.Convert;

/**
 * 合作单位管理Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-07-16
 */
@Service
public class TCompanyServiceImpl implements ITCompanyService 
{
    @Autowired
    private TCompanyMapper tCompanyMapper;

    /**
     * 查询合作单位管理
     * 
     * @param companyId 合作单位管理ID
     * @return 合作单位管理
     */
    @Override
    public TCompany selectTCompanyById(Long companyId)
    {
        return tCompanyMapper.selectTCompanyById(companyId);
    }

    /**
     * 查询合作单位管理列表
     * 
     * @param tCompany 合作单位管理
     * @return 合作单位管理
     */
    @Override
    public List<TCompany> selectTCompanyList(TCompany tCompany)
    {
        return tCompanyMapper.selectTCompanyList(tCompany);
    }

    /**
     * 新增合作单位管理
     * 
     * @param tCompany 合作单位管理
     * @return 结果
     */
    @Override
    public int insertTCompany(TCompany tCompany)
    {
        return tCompanyMapper.insertTCompany(tCompany);
    }

    /**
     * 修改合作单位管理
     * 
     * @param tCompany 合作单位管理
     * @return 结果
     */
    @Override
    public int updateTCompany(TCompany tCompany)
    {
        return tCompanyMapper.updateTCompany(tCompany);
    }

    /**
     * 删除合作单位管理对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteTCompanyByIds(String ids)
    {
        return tCompanyMapper.deleteTCompanyByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除合作单位管理信息
     * 
     * @param companyId 合作单位管理ID
     * @return 结果
     */
    @Override
    public int deleteTCompanyById(Long companyId)
    {
        return tCompanyMapper.deleteTCompanyById(companyId);
    }
}
