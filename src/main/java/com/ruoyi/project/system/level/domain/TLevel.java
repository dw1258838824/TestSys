package com.ruoyi.project.system.level.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 等级管理对象 t_level
 * 
 * @author ruoyi
 * @date 2020-05-25
 */
public class TLevel extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 等级编号 */
    private Long levelId;

    /** 等级名称 */
    @Excel(name = "等级名称")
    private String levelName;

    /** 等级别称 */
    @Excel(name = "等级别称")
    private String levelFullName;

    /** 等级说明 */
    @Excel(name = "等级说明")
    private String levelRemark;
    
    /** 等级值 **/
    @Excel(name = "等级值")
    private Integer levelValue;

    public void setLevelId(Long levelId) 
    {
        this.levelId = levelId;
    }

    public Long getLevelId() 
    {
        return levelId;
    }
    public void setLevelName(String levelName) 
    {
        this.levelName = levelName;
    }

    public String getLevelName() 
    {
        return levelName;
    }
    public void setLevelFullName(String levelFullName) 
    {
        this.levelFullName = levelFullName;
    }

    public String getLevelFullName() 
    {
        return levelFullName;
    }
    public void setLevelRemark(String levelRemark) 
    {
        this.levelRemark = levelRemark;
    }

    public String getLevelRemark() 
    {
        return levelRemark;
    }

    public Integer getLevelValue() {
		return levelValue;
	}

	public void setLevelValue(Integer levelValue) {
		this.levelValue = levelValue;
	}

	@Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("levelId", getLevelId())
            .append("levelName", getLevelName())
            .append("levelFullName", getLevelFullName())
            .append("levelRemark", getLevelRemark())
            .toString();
    }
}
