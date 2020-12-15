package com.ruoyi.project.system.subject.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 科目管理对象 t_subject
 * 
 * @author ruoyi
 * @date 2020-05-27
 */
public class TSubject extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 科目编号 */
    @Excel(name = "科目编号")
    private Integer subjectId;

    /** 科目名称 */
    @Excel(name = "科目名称")
    private String subjectName;

    /** 科目说明 */
    @Excel(name = "科目说明")
    private String subjectRemark;

    public void setSubjectId(Integer subjectId) 
    {
        this.subjectId = subjectId;
    }

    public Integer getSubjectId() 
    {
        return subjectId;
    }
    public void setSubjectName(String subjectName) 
    {
        this.subjectName = subjectName;
    }

    public String getSubjectName() 
    {
        return subjectName;
    }
    public void setSubjectRemark(String subjectRemark) 
    {
        this.subjectRemark = subjectRemark;
    }

    public String getSubjectRemark() 
    {
        return subjectRemark;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("subjectId", getSubjectId())
            .append("subjectName", getSubjectName())
            .append("subjectRemark", getSubjectRemark())
            .toString();
    }
}
