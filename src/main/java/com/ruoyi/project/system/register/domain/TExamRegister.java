package com.ruoyi.project.system.register.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;
import java.util.Date;

/**
 * 报名管理对象 t_exam_register
 * 
 * @author ruoyi
 * @date 2020-06-08
 */
public class TExamRegister extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 报名编号 */
    private Long examRegisterId;

    /** 学员 */
    private Long studentId;
    @Excel(name = "学员")
    private String studentName;

    /** 对应考试 */
    private Long examId;
    @Excel(name = "对应考试")
    private String examTitle;

    /** 准考状态 */
    @Excel(name = "准考状态")
    private String state;

    /** 准考证号 */
    @Excel(name = "准考证号")
    private String examCode;

    /** 报名时间 */
    @Excel(name = "报名时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm")
    private Date ctime;
    
    private Long uid;
    
    private String liveurl;
    
    private Long roomId;
    
    private Long subjectId;
    private Long levelId;

    public void setExamRegisterId(Long examRegisterId) 
    {
        this.examRegisterId = examRegisterId;
    }

    public Long getExamRegisterId() 
    {
        return examRegisterId;
    }
    public void setStudentId(Long studentId) 
    {
        this.studentId = studentId;
    }

    public Long getStudentId() 
    {
        return studentId;
    }
    public void setExamId(Long examId) 
    {
        this.examId = examId;
    }

    public Long getExamId() 
    {
        return examId;
    }
    public void setState(String state) 
    {
        this.state = state;
    }

    public String getState() 
    {
        return state;
    }
    public void setExamCode(String examCode) 
    {
        this.examCode = examCode;
    }

    public String getExamCode() 
    {
        return examCode;
    }
    public void setCtime(Date ctime) 
    {
        this.ctime = ctime;
    }

    public Date getCtime() 
    {
        return ctime;
    }

    public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public String getExamTitle() {
		return examTitle;
	}

	public void setExamTitle(String examTitle) {
		this.examTitle = examTitle;
	}

	public Long getUid() {
		return uid;
	}

	public void setUid(Long uid) {
		this.uid = uid;
	}

	public String getLiveurl() {
		return liveurl;
	}

	public void setLiveurl(String liveurl) {
		this.liveurl = liveurl;
	}

	public Long getRoomId() {
		return roomId;
	}

	public void setRoomId(Long roomId) {
		this.roomId = roomId;
	}

	public Long getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(Long subjectId) {
		this.subjectId = subjectId;
	}

	public Long getLevelId() {
		return levelId;
	}

	public void setLevelId(Long levelId) {
		this.levelId = levelId;
	}

	@Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("examRegisterId", getExamRegisterId())
            .append("studentId", getStudentId())
            .append("examId", getExamId())
            .append("state", getState())
            .append("remark", getRemark())
            .append("examCode", getExamCode())
            .append("ctime", getCtime())
            .toString();
    }
}
