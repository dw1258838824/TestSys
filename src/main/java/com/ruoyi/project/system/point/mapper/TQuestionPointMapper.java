package com.ruoyi.project.system.point.mapper;

import java.util.List;
import com.ruoyi.project.system.point.domain.TQuestionPoint;

/**
 * 知识点分类Mapper接口
 * 
 * @author ruoyi
 * @date 2020-05-25
 */
public interface TQuestionPointMapper 
{
    /**
     * 查询知识点分类
     * 
     * @param questionPointId 知识点分类ID
     * @return 知识点分类
     */
    public TQuestionPoint selectTQuestionPointById(Long questionPointId);
    /**
     * 查询知识点分类
     * 
     * @param questionPointId 知识点分类名称
     * @return 知识点分类
     */
    public TQuestionPoint selectTQuestionPointByName(String questionPointName);
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
     * 删除知识点分类
     * 
     * @param questionPointId 知识点分类ID
     * @return 结果
     */
    public int deleteTQuestionPointById(Long questionPointId);

    /**
     * 批量删除知识点分类
     * 
     * @param questionPointIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteTQuestionPointByIds(String[] questionPointIds);
}
