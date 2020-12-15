package com.ruoyi.project.system.company.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;
import java.util.Date;

/**
 * 合作单位管理对象 t_company
 * 
 * @author ruoyi
 * @date 2020-07-16
 */
public class TCompany extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 合作单位编号 */
    private Long companyId;

    /** 合作单位名称 */
    @Excel(name = "合作单位名称")
    private String cname;

    /** 简介 */
    @Excel(name = "简介")
    private String synopsis;

    /** 联系人 */
    @Excel(name = "联系人")
    private String people;

    /** 联系电话 */
    @Excel(name = "联系电话")
    private String tel;

    /** 备注 */
    @Excel(name = "备注")
    private String remarks;

    /** 合作时间 */
    @Excel(name = "合作时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date ctime;

    public void setCompanyId(Long companyId) 
    {
        this.companyId = companyId;
    }

    public Long getCompanyId() 
    {
        return companyId;
    }
    public void setCname(String cname) 
    {
        this.cname = cname;
    }

    public String getCname() 
    {
        return cname;
    }
    public void setSynopsis(String synopsis) 
    {
        this.synopsis = synopsis;
    }

    public String getSynopsis() 
    {
        return synopsis;
    }
    public void setPeople(String people) 
    {
        this.people = people;
    }

    public String getPeople() 
    {
        return people;
    }
    public void setTel(String tel) 
    {
        this.tel = tel;
    }

    public String getTel() 
    {
        return tel;
    }
    public void setRemarks(String remarks) 
    {
        this.remarks = remarks;
    }

    public String getRemarks() 
    {
        return remarks;
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
            .append("companyId", getCompanyId())
            .append("cname", getCname())
            .append("synopsis", getSynopsis())
            .append("people", getPeople())
            .append("tel", getTel())
            .append("remarks", getRemarks())
            .append("ctime", getCtime())
            .toString();
    }
}
