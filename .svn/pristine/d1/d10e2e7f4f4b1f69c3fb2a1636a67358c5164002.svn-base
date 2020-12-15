package com.ruoyi.project.system.point.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.TreeEntity;

/**
 * 知识点分类对象 t_question_point
 * 
 * @author ruoyi
 * @date 2020-05-25
 */
public class TQuestionPoint extends TreeEntity
{
    private static final long serialVersionUID = 1L;

    /** 知识点编号 */
    private Long questionPointId;

    /** 知识点 */
    @Excel(name = "知识点")
    private String questionPointName;

    /** 知识点说明 */
    @Excel(name = "知识点说明")
    private String questionPointRemark;

    /** 父级节点 */
    @Excel(name = "父级节点")
    private Long questionPointParentId;

    public void setQuestionPointId(Long questionPointId) 
    {
        this.questionPointId = questionPointId;
    }

    public Long getQuestionPointId() 
    {
        return questionPointId;
    }
    public void setQuestionPointName(String questionPointName) 
    {
        this.questionPointName = questionPointName;
    }

    public String getQuestionPointName() 
    {
        return questionPointName;
    }
    public void setQuestionPointRemark(String questionPointRemark) 
    {
        this.questionPointRemark = questionPointRemark;
    }

    public String getQuestionPointRemark() 
    {
        return questionPointRemark;
    }
    public void setQuestionPointParentId(Long questionPointParentId) 
    {
        this.questionPointParentId = questionPointParentId;
    }

    public Long getQuestionPointParentId() 
    {
        return questionPointParentId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("questionPointId", getQuestionPointId())
            .append("questionPointName", getQuestionPointName())
            .append("questionPointRemark", getQuestionPointRemark())
            .append("questionPointParentId", getQuestionPointParentId())
            .toString();
    }
}
