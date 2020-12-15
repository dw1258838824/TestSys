package com.ruoyi.project.system.point.service;

import java.util.List;
import com.ruoyi.project.system.point.domain.TQuestionPoint;
import com.ruoyi.framework.web.domain.Ztree;

/**
 * 知识点分类Service接口
 * 
 * @author ruoyi
 * @date 2020-05-25
 */
public interface ITQuestionPointService 
{
    /**
     * 查询知识点分类
     * 
     * @param questionPointId 知识点分类ID
     * @return 知识点分类
     */
    public TQuestionPoint selectTQuestionPointById(Long questionPointId);

    /**
     * 查询知识点分类列表
     * 
     * @param tQuestionPoint 知识点分类
     * @return 知识点分类集合
     */
    public List<TQuestionPoint> selectTQuestionPointList(TQuestionPoint tQuestionPoint);

    /**
     * 新增知识点分类
     * 
     * @param tQuestionPoint 知识点分类
     * @return 结果
     */
    public int insertTQuestionPoint(TQuestionPoint tQuestionPoint);

    /**
     * 修改知识点分类
     * 
     * @param tQuestionPoint 知识点分类
     * @return 结果
     */
    public int updateTQuestionPoint(TQuestionPoint tQuestionPoint);

    /**
     * 批量删除知识点分类
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteTQuestionPointByIds(String ids);

    /**
     * 删除知识点分类信息
     * 
     * @param questionPointId 知识点分类ID
     * @return 结果
     */
    public int deleteTQuestionPointById(Long questionPointId);

    /**
     * 查询知识点分类树列表
     * 
     * @return 所有知识点分类信息
     */
    public List<Ztree> selectTQuestionPointTree();
}
