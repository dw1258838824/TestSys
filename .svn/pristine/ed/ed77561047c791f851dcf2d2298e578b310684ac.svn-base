package com.ruoyi.project.system.paper.service;

import java.util.List;
import java.util.Map;

import com.ruoyi.project.system.paper.domain.TPaper;

/**
 * 试卷管理Service接口
 * 
 * @author ruoyi
 * @date 2020-06-02
 */
public interface ITPaperService 
{
    /**
     * 查询试卷管理
     * 
     * @param paperId 试卷管理ID
     * @return 试卷管理
     */
    public TPaper selectTPaperById(Long paperId);

    /**
     * 查询试卷管理列表
     * 
     * @param tPaper 试卷管理
     * @return 试卷管理集合
     */
    public List<TPaper> selectTPaperList(TPaper tPaper);

    /**
     * 新增试卷管理
     * 
     * @param tPaper 试卷管理
     * @return 结果
     */
    public int insertTPaper(TPaper tPaper,Map<String,Object> parm);

    /**
     * 修改试卷管理
     * 
     * @param tPaper 试卷管理
     * @return 结果
     */
    public int updateTPaper(TPaper tPaper);

    /**
     * 批量删除试卷管理
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteTPaperByIds(String ids);

    /**
     * 删除试卷管理信息
     * 
     * @param paperId 试卷管理ID
     * @return 结果
     */
    public int deleteTPaperById(Long paperId);
}
