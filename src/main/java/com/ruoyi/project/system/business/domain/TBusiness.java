package com.ruoyi.project.system.business.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;
import java.util.Date;

/**
 * 商务洽谈对象 t_business
 * 
 * @author ruoyi
 * @date 2020-07-16
 */
public class TBusiness extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 商务洽谈记录 */
    private Long businessId;

    /** 发起人 */
    @Excel(name = "发起人")
    private Long startUserId;

    /** 发起人姓名 */
    @Excel(name = "发起人姓名")
    private String startUserName;

    /** 洽谈内容 */
    @Excel(name = "洽谈内容")
    private String bsContext;

    /** 联系方式 */
    @Excel(name = "联系方式")
    private String bsTel;

    /** 接收人编号集合 */
    @Excel(name = "接收人编号集合")
    private String acceptUserIds;

    /** 接收角色编号集合 */
    @Excel(name = "接收角色编号集合")
    private String acceptRoleIds;

    /** 状态 */
    @Excel(name = "状态")
    private String state;

    /** 创建时间 */
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date ctime;

    public void setBusinessId(Long businessId) 
    {
        this.businessId = businessId;
    }

    public Long getBusinessId() 
    {
        return businessId;
    }
    public void setStartUserId(Long startUserId) 
    {
        this.startUserId = startUserId;
    }

    public Long getStartUserId() 
    {
        return startUserId;
    }
    public void setStartUserName(String startUserName) 
    {
        this.startUserName = startUserName;
    }

    public String getStartUserName() 
    {
        return startUserName;
    }
    public void setBsContext(String bsContext) 
    {
        this.bsContext = bsContext;
    }

    public String getBsContext() 
    {
        return bsContext;
    }
    public void setBsTel(String bsTel) 
    {
        this.bsTel = bsTel;
    }

    public String getBsTel() 
    {
        return bsTel;
    }
    public void setAcceptUserIds(String acceptUserIds) 
    {
        this.acceptUserIds = acceptUserIds;
    }

    public String getAcceptUserIds() 
    {
        return acceptUserIds;
    }
    public void setAcceptRoleIds(String acceptRoleIds) 
    {
        this.acceptRoleIds = acceptRoleIds;
    }

    public String getAcceptRoleIds() 
    {
        return acceptRoleIds;
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

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("businessId", getBusinessId())
            .append("startUserId", getStartUserId())
            .append("startUserName", getStartUserName())
            .append("bsContext", getBsContext())
            .append("bsTel", getBsTel())
            .append("acceptUserIds", getAcceptUserIds())
            .append("acceptRoleIds", getAcceptRoleIds())
            .append("state", getState())
            .append("ctime", getCtime())
            .toString();
    }
}
