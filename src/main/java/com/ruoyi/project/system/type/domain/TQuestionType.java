package com.ruoyi.project.system.type.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 题型管理对象 t_question_type
 * 
 * @author ruoyi
 * @date 2020-05-27
 */
public class TQuestionType extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 题型编号 */
    @Excel(name = "题型编号")
    private Integer questionTypeId;

    /** 题型名称 */
    @Excel(name = "题型名称")
    private String questionTypeName;

    /** 题型模板 */
    @Excel(name = "题型模板")
    private String questionTypeHtml;

    /** 默认生成数量 */
    @Excel(name = "默认生成数量")
    private Integer defaultCount;
    
    /** 题型模板 */
    @Excel(name = "默认生成分数")
    private Double defaultScore;
    
    /**考试模式*/
    private String typeMode;
    

    public void setQuestionTypeId(Integer questionTypeId) 
    {
        this.questionTypeId = questionTypeId;
    }

    public Integer getQuestionTypeId() 
    {
        return questionTypeId;
    }
    public void setQuestionTypeName(String questionTypeName) 
    {
        this.questionTypeName = questionTypeName;
    }

    public String getQuestionTypeName() 
    {
        return questionTypeName;
    }
    public void setQuestionTypeHtml(String questionTypeHtml) 
    {
        this.questionTypeHtml = questionTypeHtml;
    }

    public String getQuestionTypeHtml() 
    {
        return questionTypeHtml;
    }

    public Integer getDefaultCount() {
		return defaultCount;
	}

	public void setDefaultCount(Integer defaultCount) {
		this.defaultCount = defaultCount;
	}

	public Double getDefaultScore() {
		return defaultScore;
	}

	public void setDefaultScore(Double defaultScore) {
		this.defaultScore = defaultScore;
	}

	public String getTypeMode() {
		return typeMode;
	}

	public void setTypeMode(String typeMode) {
		this.typeMode = typeMode;
	}

	@Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("questionTypeId", getQuestionTypeId())
            .append("questionTypeName", getQuestionTypeName())
            .append("questionTypeHtml", getQuestionTypeHtml())
            .toString();
    }
}
