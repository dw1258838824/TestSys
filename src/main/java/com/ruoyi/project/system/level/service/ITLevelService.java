package com.ruoyi.project.system.level.service;

import java.util.List;
import com.ruoyi.project.system.level.domain.TLevel;

/**
 * 等级管理Service接口
 * 
 * @author ruoyi
 * @date 2020-05-25
 */
public interface ITLevelService 
{
    /**
     * 查询等级管理
     * 
     * @param levelId 等级管理ID
     * @return 等级管理
     */
    public TLevel selectTLevelById(Long levelId);

    /**
     * 查询等级管理列表
     * 
     * @param tLevel 等级管理
     * @return 等级管理集合
     */
    public List<TLevel> selectTLevelList(TLevel tLevel);

    /**
     * 新增等级管理
     * 
     * @param tLevel 等级管理
     * @return 结果
     */
    public int insertTLevel(TLevel tLevel);

    /**
     * 修改等级管理
     * 
     * @param tLevel 等级管理
     * @return 结果
     */
    public int updateTLevel(TLevel tLevel);

    /**
     * 批量删除等级管理
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteTLevelByIds(String ids);

    /**
     * 删除等级管理信息
     * 
     * @param levelId 等级管理ID
     * @return 结果
     */
    public int deleteTLevelById(Long levelId);
}
