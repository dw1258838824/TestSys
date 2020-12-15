package com.ruoyi.project.system.exam.domain;

import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 考试管理对象 t_exam
 * 
 * @author ruoyi
 * @date 2020-06-04
 */
public class TExam extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 考试编号 */
    private Long examId;

    /** 考试标题 */
    @Excel(name = "考试标题")
    private String examTitle;

    /** 等级 */
    private Long levelId;
    @Excel(name = "等级")
    private String levelName;

    /** 考试模式 */
    @Excel(name = "考试模式")
    private String examMode;

    /** 科目 */
    private Long subjectId;
    @Excel(name = "科目")
    private String subjectName;

    /** 考试要求 */
    private String examClaim;

    /** 笔试开始时间 */
    @Excel(name = "考试开始时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm")
    private Date beginTime;

    /** 笔试结束时间 */
    private Date endTime;

    /** 实操开始时间 */
    private Date operateBeginTime;

    /** 实操结束时间 */
    private Date operateEndTime;
    

    /** 答辩开始时间 */
    private Date argueBeginTime;

    /** 答辩结束时间 */
    @Excel(name = "考试结束时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm")
    private Date argueEndTime;
    
    /** 报名开始时间 */
    @Excel(name = "报名开始时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm")
    private Date rignBeginTime;

    /** 报名结束时间 */
    @Excel(name = "报名结束时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm")
    private Date rignEndTime;

    /** 试卷 */
    private Long paperId;
    @Excel(name = "试卷")
    private String paperName;

    /** 考试状态 */
    @Excel(name = "考试状态")
    private String state;

    /** 创建时间 */
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date ctime;

    /** 创建人 */
    @Excel(name = "创建人")
    private String creator;

    /** 是否删除 */
    @Excel(name = "是否删除")
    private String isDelete;

    @Excel(name = "考试费用")
    private Double price;
    
    //classIn uid
    private Long uid;

    private String payState;

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setExamId(Long examId)
    {
        this.examId = examId;
    }

    public Long getExamId() 
    {
        return examId;
    }
    public void setExamTitle(String examTitle) 
    {
        this.examTitle = examTitle;
    }

    public String getExamTitle() 
    {
        return examTitle;
    }
    public void setLevelId(Long levelId) 
    {
        this.levelId = levelId;
    }

    public Long getLevelId() 
    {
        return levelId;
    }
    public void setExamMode(String examMode) 
    {
        this.examMode = examMode;
    }

    public String getExamMode() 
    {
        return examMode;
    }
    public void setSubjectId(Long subjectId) 
    {
        this.subjectId = subjectId;
    }

    public Long getSubjectId() 
    {
        return subjectId;
    }
    public void setExamClaim(String examClaim) 
    {
        this.examClaim = examClaim;
    }

    public String getExamClaim() 
    {
        return examClaim;
    }
    public void setBeginTime(Date beginTime) 
    {
        this.beginTime = beginTime;
    }

    public Date getBeginTime() 
    {
        return beginTime;
    }
    public void setEndTime(Date endTime) 
    {
        this.endTime = endTime;
    }

    public Date getEndTime() 
    {
        return endTime;
    }
    public void setRignBeginTime(Date rignBeginTime) 
    {
        this.rignBeginTime = rignBeginTime;
    }

    public Date getRignBeginTime() 
    {
        return rignBeginTime;
    }
    public void setRignEndTime(Date rignEndTime) 
    {
        this.rignEndTime = rignEndTime;
    }

    public Date getRignEndTime() 
    {
        return rignEndTime;
    }
    public void setPaperId(Long paperId) 
    {
        this.paperId = paperId;
    }

    public Long getPaperId() 
    {
        return paperId;
    }
    public void setState(String state) 
    {
        this.state = state;
    }

    public String getState() 
    {
        return state;
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
    public void setIsDelete(String isDelete) 
    {
        this.isDelete = isDelete;
    }

    public String getIsDelete() 
    {
        return isDelete;
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

	public String getPaperName() {
		return paperName;
	}

	public void setPaperName(String paperName) {
		this.paperName = paperName;
	}

	public Date getOperateBeginTime() {
		return operateBeginTime;
	}

	public void setOperateBeginTime(Date operateBeginTime) {
		this.operateBeginTime = operateBeginTime;
	}

	public Date getOperateEndTime() {
		return operateEndTime;
	}

	public void setOperateEndTime(Date operateEndTime) {
		this.operateEndTime = operateEndTime;
	}

	public Date getArgueBeginTime() {
		return argueBeginTime;
	}

	public void setArgueBeginTime(Date argueBeginTime) {
		this.argueBeginTime = argueBeginTime;
	}

	public Date getArgueEndTime() {
		return argueEndTime;
	}

	public void setArgueEndTime(Date argueEndTime) {
		this.argueEndTime = argueEndTime;
	}

    public String getPayState() {
        return payState;
    }

    public void setPayState(String payState) {
        this.payState = payState;
    }

    public Long getUid() {
		return uid;
	}

	public void setUid(Long uid) {
		this.uid = uid;
	}

	@Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("examId", getExamId())
            .append("examTitle", getExamTitle())
            .append("levelId", getLevelId())
            .append("examMode", getExamMode())
            .append("subjectId", getSubjectId())
            .append("examClaim", getExamClaim())
            .append("beginTime", getBeginTime())
            .append("endTime", getEndTime())
            .append("rignBeginTime", getRignBeginTime())
            .append("rignEndTime", getRignEndTime())
            .append("paperId", getPaperId())
            .append("state", getState())
            .append("ctime", getCtime())
            .append("creator", getCreator())
            .append("isDelete", getIsDelete())
            .toString();
    }
}
