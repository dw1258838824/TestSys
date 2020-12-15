package com.ruoyi.project.system.paper.mapper;

import java.util.List;
import java.util.Map;

import com.ruoyi.project.system.paper.domain.TPaper;

/**
 * 试卷管理Mapper接口
 * 
 * @author ruoyi
 * @date 2020-06-02
 */
public interface TPaperMapper 
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
    public int insertTPaper(TPaper tPaper);

    /**
     * 修改试卷管理
     * 
     * @param tPaper 试卷管理
     * @return 结果
     */
    public int updateTPaper(TPaper tPaper);

    /**
     * 删除试卷管理
     * 
     * @param paperId 试卷管理ID
     * @return 结果
     */
    public int deleteTPaperById(Long paperId);

    /**
     * 批量删除试卷管理
     * 
     * @param paperIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteTPaperByIds(String[] paperIds);
    
    /**
     * 批量新增试卷题目关联
     */
    public int insertPaperQuestion(Map<String, Object> parm);
}
