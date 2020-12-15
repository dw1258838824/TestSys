package com.ruoyi.project.system.room.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;
import java.util.Date;

/**
 * 考场管理对象 t_room
 * 
 * @author ruoyi
 * @date 2020-07-17
 */
public class TRoom extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 考场编号 */
    private Long roomId;

    /** 考场名称 */
    @Excel(name = "考场名称")
    private String roomName;

    /** 考场类型 */
    @Excel(name = "考场类型")
    private String roomType;

    /** 创建时间 */
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date ctime;

    /** 监考老师 */
    @Excel(name = "监考老师")
    private Long teacherUserId;

    /** 关联考试 */
    @Excel(name = "关联考试")
    private Long examId;

    public void setRoomId(Long roomId) 
    {
        this.roomId = roomId;
    }

    public Long getRoomId() 
    {
        return roomId;
    }
    public void setRoomName(String roomName) 
    {
        this.roomName = roomName;
    }

    public String getRoomName() 
    {
        return roomName;
    }
    public void setRoomType(String roomType) 
    {
        this.roomType = roomType;
    }

    public String getRoomType() 
    {
        return roomType;
    }
    public void setCtime(Date ctime) 
    {
        this.ctime = ctime;
    }

    public Date getCtime() 
    {
        return ctime;
    }
    public void setTeacherUserId(Long teacherUserId) 
    {
        this.teacherUserId = teacherUserId;
    }

    public Long getTeacherUserId() 
    {
        return teacherUserId;
    }
    public void setExamId(Long examId) 
    {
        this.examId = examId;
    }

    public Long getExamId() 
    {
        return examId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("roomId", getRoomId())
            .append("roomName", getRoomName())
            .append("roomType", getRoomType())
            .append("ctime", getCtime())
            .append("teacherUserId", getTeacherUserId())
            .append("examId", getExamId())
            .toString();
    }
}
