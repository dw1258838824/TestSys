package com.ruoyi.project.system.point.service.impl;

import java.util.List;
import java.util.ArrayList;
import com.ruoyi.framework.web.domain.Ztree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.system.point.mapper.TQuestionPointMapper;
import com.ruoyi.project.system.point.domain.TQuestionPoint;
import com.ruoyi.project.system.point.service.ITQuestionPointService;
import com.ruoyi.common.utils.text.Convert;

/**
 * 知识点分类Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-05-25
 */
@Service
public class TQuestionPointServiceImpl implements ITQuestionPointService 
{
    @Autowired
    private TQuestionPointMapper tQuestionPointMapper;

    /**
     * 查询知识点分类
     * 
     * @param questionPointId 知识点分类ID
     * @return 知识点分类
     */
    @Override
    public TQuestionPoint selectTQuestionPointById(Long questionPointId)
    {
        return tQuestionPointMapper.selectTQuestionPointById(questionPointId);
    }

    /**
     * 查询知识点分类列表
     * 
     * @param tQuestionPoint 知识点分类
     * @return 知识点分类
     */
    @Override
    public List<TQuestionPoint> selectTQuestionPointList(TQuestionPoint tQuestionPoint)
    {
        return tQuestionPointMapper.selectTQuestionPointList(tQuestionPoint);
    }

    /**
     * 新增知识点分类
     * 
     * @param tQuestionPoint 知识点分类
     * @return 结果
     */
    @Override
    public int insertTQuestionPoint(TQuestionPoint tQuestionPoint)
    {
        return tQuestionPointMapper.insertTQuestionPoint(tQuestionPoint);
    }

    /**
     * 修改知识点分类
     * 
     * @param tQuestionPoint 知识点分类
     * @return 结果
     */
    @Override
    public int updateTQuestionPoint(TQuestionPoint tQuestionPoint)
    {
        return tQuestionPointMapper.updateTQuestionPoint(tQuestionPoint);
    }

    /**
     * 删除知识点分类对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteTQuestionPointByIds(String ids)
    {
        return tQuestionPointMapper.deleteTQuestionPointByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除知识点分类信息
     * 
     * @param questionPointId 知识点分类ID
     * @return 结果
     */
    @Override
    public int deleteTQuestionPointById(Long questionPointId)
    {
        return tQuestionPointMapper.deleteTQuestionPointById(questionPointId);
    }

    /**
     * 查询知识点分类树列表
     * 
     * @return 所有知识点分类信息
     */
    @Override
    public List<Ztree> selectTQuestionPointTree()
    {
        List<TQuestionPoint> tQuestionPointList = tQuestionPointMapper.selectTQuestionPointList(new TQuestionPoint());
        List<Ztree> ztrees = new ArrayList<Ztree>();
        for (TQuestionPoint tQuestionPoint : tQuestionPointList)
        {
            Ztree ztree = new Ztree();
            ztree.setId(tQuestionPoint.getQuestionPointId());
            ztree.setpId(tQuestionPoint.getQuestionPointParentId());
            ztree.setName(tQuestionPoint.getQuestionPointName());
            ztree.setTitle(tQuestionPoint.getQuestionPointName());
            ztrees.add(ztree);
        }
        return ztrees;
    }
}
