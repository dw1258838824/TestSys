package com.ruoyi.project.system.examstudent.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;
import java.util.Date;

/**
 * 学员考试对象 t_room_exam_student
 * 
 * @author ruoyi
 * @date 2020-06-18
 */
public class TRoomExamStudent extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 序号 */
    private Long id;

    /** 学员 */
    @Excel(name = "学员")
    private Long studentId;

    /** 教室 */
    @Excel(name = "教室")
    private Long roomId;

    /** 考试 */
    @Excel(name = "考试")
    private Long examId;

    /** 座位号 */
    @Excel(name = "座位号")
    private String examSeat;

    /** 学员答案json */
    @Excel(name = "学员答案json")
    private String studentAnswerJson;

    /** 考试得分 */
    @Excel(name = "考试得分")
    private Double score;

    /** 录屏文件地址 */
    @Excel(name = "录屏文件地址")
    private String screencapPath;

    /** 录像文件地址 */
    @Excel(name = "录像文件地址")
    private String videocapPath;

    /** 考卷状态 */
    @Excel(name = "考卷状态")
    private String judgeState;

    /** 作答时间 */
    @Excel(name = "笔试作答时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date beginTime;

    /** 交卷时间 */
    @Excel(name = "笔试提交时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date endTime;

    //开始实操时间
    private Date operateBeginTime;
    //结束实操时间
    private Date operateEndTime;
    //开始答辩时间
    private Date argueBeginTime;
    //结束答辩时间
    private Date argueEndTime;
    
    /** 创建时间 */
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date ctime;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setStudentId(Long studentId) 
    {
        this.studentId = studentId;
    }

    public Long getStudentId() 
    {
        return studentId;
    }
    public void setRoomId(Long roomId) 
    {
        this.roomId = roomId;
    }

    public Long getRoomId() 
    {
        return roomId;
    }
    public void setExamId(Long examId) 
    {
        this.examId = examId;
    }

    public Long getExamId() 
    {
        return examId;
    }
    public void setExamSeat(String examSeat) 
    {
        this.examSeat = examSeat;
    }

    public String getExamSeat() 
    {
        return examSeat;
    }
    public void setStudentAnswerJson(String studentAnswerJson) 
    {
        this.studentAnswerJson = studentAnswerJson;
    }

    public String getStudentAnswerJson() 
    {
        return studentAnswerJson;
    }
    public void setScore(Double score) 
    {
        this.score = score;
    }

    public Double getScore() 
    {
        return score;
    }
    public void setScreencapPath(String screencapPath) 
    {
        this.screencapPath = screencapPath;
    }

    public String getScreencapPath() 
    {
        return screencapPath;
    }
    public void setVideocapPath(String videocapPath) 
    {
        this.videocapPath = videocapPath;
    }

    public String getVideocapPath() 
    {
        return videocapPath;
    }
    public void setJudgeState(String judgeState) 
    {
        this.judgeState = judgeState;
    }

    public String getJudgeState() 
    {
        return judgeState;
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
    public void setCtime(Date ctime) 
    {
        this.ctime = ctime;
    }

    public Date getCtime() 
    {
        return ctime;
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

	@Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("studentId", getStudentId())
            .append("roomId", getRoomId())
            .append("examId", getExamId())
            .append("examSeat", getExamSeat())
            .append("studentAnswerJson", getStudentAnswerJson())
            .append("score", getScore())
            .append("screencapPath", getScreencapPath())
            .append("videocapPath", getVideocapPath())
            .append("judgeState", getJudgeState())
            .append("beginTime", getBeginTime())
            .append("endTime", getEndTime())
            .append("remark", getRemark())
            .append("ctime", getCtime())
            .toString();
    }
}
