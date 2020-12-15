package com.ruoyi.project.system.reg.domain;

import java.util.Date;
import java.util.Map;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 机构批量报名对象 t_dept_reg
 * 
 * @author ruoyi
 * @date 2020-07-31
 */
public class TDeptReg extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 机构报名编号 */
    private Long deptRegId;

    /** 对应考试 */
    @Excel(name = "对应考试")
    private Long examId;

    /** 报名学生 */
    @Excel(name = "报名学生")
    private Long studentId;

    /** 所属机构 */
    @Excel(name = "所属机构")
    private Long deptId;

    /** 申请人 */
    @Excel(name = "申请人")
    private Long operateUserId;

    /** 申请时间 */
    @Excel(name = "申请时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date operateTime;

    /** 申请备注 */
    @Excel(name = "申请备注")
    private String operateMark;

    /** 申请状态 */
    @Excel(name = "申请状态")
    private String operateState;

    /** 审核人 */
    @Excel(name = "审核人")
    private Long replayUserId;

    /** 审核时间 */
    @Excel(name = "审核时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date replayTime;

    /** 审核意见 */
    private String replayMark;
   

    /** 批量报名申请 */
    private Long [] ids;

    public void setDeptRegId(Long deptRegId) 
    {
        this.deptRegId = deptRegId;
    }

    public Long getDeptRegId() 
    {
        return deptRegId;
    }
    public void setExamId(Long examId) 
    {
        this.examId = examId;
    }

    public Long getExamId() 
    {
        return examId;
    }
    public void setStudentId(Long studentId) 
    {
        this.studentId = studentId;
    }

    public Long getStudentId() 
    {
        return studentId;
    }
    public void setDeptId(Long deptId) 
    {
        this.deptId = deptId;
    }

    public Long getDeptId() 
    {
        return deptId;
    }
    public void setOperateUserId(Long operateUserId) 
    {
        this.operateUserId = operateUserId;
    }

    public Long getOperateUserId() 
    {
        return operateUserId;
    }
    public void setOperateTime(Date operateTime) 
    {
        this.operateTime = operateTime;
    }

    public Date getOperateTime() 
    {
        return operateTime;
    }
    public void setOperateMark(String operateMark) 
    {
        this.operateMark = operateMark;
    }

    public String getOperateMark() 
    {
        return operateMark;
    }
    public void setOperateState(String operateState) 
    {
        this.operateState = operateState;
    }

    public String getOperateState() 
    {
        return operateState;
    }
    public void setReplayUserId(Long replayUserId) 
    {
        this.replayUserId = replayUserId;
    }

    public Long getReplayUserId() 
    {
        return replayUserId;
    }
    public void setReplayTime(Date replayTime) 
    {
        this.replayTime = replayTime;
    }

    public Date getReplayTime() 
    {
        return replayTime;
    }
    public void setReplayMark(String replayMark) 
    {
        this.replayMark = replayMark;
    }

    public String getReplayMark() 
    {
        return replayMark;
    }

	public Long[] getIds() {
		return ids;
	}

	public void setIds(Long[] ids) {
		this.ids = ids;
	}

	@Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("deptRegId", getDeptRegId())
            .append("examId", getExamId())
            .append("studentId", getStudentId())
            .append("deptId", getDeptId())
            .append("operateUserId", getOperateUserId())
            .append("operateTime", getOperateTime())
            .append("operateMark", getOperateMark())
            .append("operateState", getOperateState())
            .append("replayUserId", getReplayUserId())
            .append("replayTime", getReplayTime())
            .append("replayMark", getReplayMark())
            .toString();
    }
}
