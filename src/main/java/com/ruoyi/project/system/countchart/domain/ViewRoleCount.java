package com.ruoyi.project.system.countchart.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 角色人员统计对象 view_role_count
 * 
 * @author ruoyi
 * @date 2020-07-27
 */
public class ViewRoleCount extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 角色 */
    private Long roleId;
    @Excel(name = "角色")
    private String roleName;

    /** 人数 */
    @Excel(name = "人数")
    private Long counts;

    public void setRoleId(Long roleId) 
    {
        this.roleId = roleId;
    }

    public Long getRoleId() 
    {
        return roleId;
    }
    public void setCounts(Long counts) 
    {
        this.counts = counts;
    }

    public Long getCounts() 
    {
        return counts;
    }

    public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	@Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("roleId", getRoleId())
            .append("counts", getCounts())
            .toString();
    }
}
