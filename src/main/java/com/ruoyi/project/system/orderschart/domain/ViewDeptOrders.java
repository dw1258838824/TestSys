package com.ruoyi.project.system.orderschart.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 订单统计对象 view_dept_orders
 * 
 * @author ruoyi
 * @date 2020-07-27
 */
public class ViewDeptOrders extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 机构 */
    private Long deptId;
    @Excel(name = "机构")
    private String deptName;

    /** 订单量 */
    @Excel(name = "订单量")
    private Long counts;

    /** 订单金额 */
    @Excel(name = "订单金额")
    private Double amounts;

    public void setDeptId(Long deptId) 
    {
        this.deptId = deptId;
    }

    public Long getDeptId() 
    {
        return deptId;
    }
    public void setCounts(Long counts) 
    {
        this.counts = counts;
    }

    public Long getCounts() 
    {
        return counts;
    }
    public void setAmounts(Double amounts) 
    {
        this.amounts = amounts;
    }

    public Double getAmounts() 
    {
        return amounts;
    }

    public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	@Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("deptId", getDeptId())
            .append("counts", getCounts())
            .append("amounts", getAmounts())
            .toString();
    }
}
