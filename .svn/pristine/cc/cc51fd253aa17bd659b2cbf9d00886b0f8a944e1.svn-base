package com.ruoyi.project.system.chart.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 考试统计对象 view_exam_chart
 * 
 * @author ruoyi
 * @date 2020-07-19
 */
public class ViewExamChart extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 考试编号 */
    @Excel(name = "考试编号")
    private Long examId;

    /** 考试标题 */
    @Excel(name = "考试标题")
    private String examTitle;

    /** 平均分 */
    @Excel(name = "平均分")
    private Double avgScore;

    /** 考试人数 */
    @Excel(name = "考试人数")
    private Long peopleCount;

    /** 及格人数 */
    @Excel(name = "及格人数")
    private Long passCount;

    /** 及格率 */
    @Excel(name = "及格率")
    private String passLv;

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
    public void setAvgScore(Double avgScore) 
    {
        this.avgScore = avgScore;
    }

    public Double getAvgScore() 
    {
        return avgScore;
    }
    public void setPeopleCount(Long peopleCount) 
    {
        this.peopleCount = peopleCount;
    }

    public Long getPeopleCount() 
    {
        return peopleCount;
    }
    public void setPassCount(Long passCount) 
    {
        this.passCount = passCount;
    }

    public Long getPassCount() 
    {
        return passCount;
    }
    public void setPassLv(String passLv) 
    {
        this.passLv = passLv;
    }

    public String getPassLv() 
    {
        return passLv;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("examId", getExamId())
            .append("examTitle", getExamTitle())
            .append("avgScore", getAvgScore())
            .append("peopleCount", getPeopleCount())
            .append("passCount", getPassCount())
            .append("passLv", getPassLv())
            .toString();
    }
}
