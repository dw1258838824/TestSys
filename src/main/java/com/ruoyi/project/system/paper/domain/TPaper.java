package com.ruoyi.project.system.paper.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;
import java.util.Date;

/**
 * 试卷管理对象 t_paper
 * 
 * @author ruoyi
 * @date 2020-06-02
 */
public class TPaper extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 试卷编号 */
    private Long paperId;

    /** 试卷名称 */
    @Excel(name = "试卷名称")
    private String paperName;

    /** 试卷类型 */
    @Excel(name = "试卷类型")
    private String paperType;

    /** 等级 */
    private Integer level;

    /** 试卷内容 */
    private String paperDoc;

    /** 科目 */
    private Integer subjectId;

    @Excel(name = "等级")
    private String levelName;
    @Excel(name = "科目")
    private String subjectName;
    

    /** 总分 */
    @Excel(name = "总分")
    private Double allScore;

    /** 及格分数 */
    @Excel(name = "及格分数")
    private Double passScore;

    /** 标准答案json */
    private String examAnswerJson;

    /** 考点模块时段json */
    private String modularTimeJson;
    
    /** 判卷方式 */
    private String judgeMode;

	/** 是否删除 */
    private String isDelete;

    /** 创建时间 */
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm")
    private Date ctime;

    /** 创建人 */
    @Excel(name = "创建人")
    private String creator;

    public void setPaperId(Long paperId) 
    {
        this.paperId = paperId;
    }

    public Long getPaperId() 
    {
        return paperId;
    }
    public void setPaperName(String paperName) 
    {
        this.paperName = paperName;
    }

    public String getPaperName() 
    {
        return paperName;
    }
    public void setPaperType(String paperType) 
    {
        this.paperType = paperType;
    }

    public String getPaperType() 
    {
        return paperType;
    }
    public void setLevel(Integer level) 
    {
        this.level = level;
    }

    public Integer getLevel() 
    {
        return level;
    }
    public void setPaperDoc(String paperDoc) 
    {
        this.paperDoc = paperDoc;
    }

    public String getPaperDoc() 
    {
        return paperDoc;
    }
    public void setSubjectId(Integer subjectId) 
    {
        this.subjectId = subjectId;
    }

    public Integer getSubjectId() 
    {
        return subjectId;
    }
    public void setAllScore(Double allScore) 
    {
        this.allScore = allScore;
    }

    public Double getAllScore() 
    {
        return allScore;
    }
    public void setPassScore(Double passScore) 
    {
        this.passScore = passScore;
    }

    public Double getPassScore() 
    {
        return passScore;
    }
    public void setExamAnswerJson(String examAnswerJson) 
    {
        this.examAnswerJson = examAnswerJson;
    }

    public String getExamAnswerJson() 
    {
        return examAnswerJson;
    }
    public void setModularTimeJson(String modularTimeJson) 
    {
        this.modularTimeJson = modularTimeJson;
    }

    public String getModularTimeJson() 
    {
        return modularTimeJson;
    }
    public void setIsDelete(String isDelete) 
    {
        this.isDelete = isDelete;
    }

    public String getIsDelete() 
    {
        return isDelete;
    }
    public void setCtime(Date ctime) 
    {
        this.ctime = ctime;
    }

    public Date getCtime() 
    {
        return ctime;
    }
    public void setCreator(String creator) 
    {
        this.creator = creator;
    }

    public String getCreator() 
    {
        return creator;
    }

    public String getLevelName() {
		return levelName;
	}

	public void setLevelName(String levelName) {
		this.levelName = levelName;
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}


    public String getJudgeMode() {
		return judgeMode;
	}

	public void setJudgeMode(String judgeMode) {
		this.judgeMode = judgeMode;
	}
	
	@Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("paperId", getPaperId())
            .append("paperName", getPaperName())
            .append("paperType", getPaperType())
            .append("level", getLevel())
            .append("paperDoc", getPaperDoc())
            .append("subjectId", getSubjectId())
            .append("allScore", getAllScore())
            .append("passScore", getPassScore())
            .append("examAnswerJson", getExamAnswerJson())
            .append("modularTimeJson", getModularTimeJson())
            .append("isDelete", getIsDelete())
            .append("ctime", getCtime())
            .append("creator", getCreator())
            .toString();
    }
}
