package com.ruoyi.project.system.bill.domain;

import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 账单对象 t_bill
 * 
 * @author ruoyi
 * @date 2020-06-22
 */
public class Bill extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 账单序列 */
    private Long billId;

    /** 账单号 */
    @Excel(name = "账单号")
    private String billNo;

    /** 账单类型  1收入  2支出 */
    @Excel(name = "账单类型")
    private String billType;

    /** 账单名称  1报名费 2补考费 3.. */
    @Excel(name = "账单名称")
    private String billName;

    /** 账单备注 */
    @Excel(name = "账单备注")
    private String billRemark;

    /** 支付人编号 */
    private Long payUserId;
    @Excel(name = "支付人")
    private String payUserName;

    /** 收款人编号 */
    private Long takeUserId;
    @Excel(name = "收款人")
    private String takeUserName;

    /** 金额 */
    @Excel(name = "金额")
    private Double amount;

    /** 交易时间 */
    @Excel(name = "交易时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date payTime;

    /** 创建时间 */
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date ctime;

    /** 交易状态  0处理中 1成功 2异常 */
    @Excel(name = "交易状态")
    private String payState;

    /** 关联编号 */
    private Long refId;
    @Excel(name = "关联考试 ")
    private String refName;

    private String payNo;

    private Long deptId;

    public void setBillId(Long billId) 
    {
        this.billId = billId;
    }

    public Long getBillId() 
    {
        return billId;
    }
    public void setBillNo(String billNo) 
    {
        this.billNo = billNo;
    }

    public String getBillNo() 
    {
        return billNo;
    }
    public void setBillType(String billType) 
    {
        this.billType = billType;
    }

    public String getBillType() 
    {
        return billType;
    }
    public void setBillName(String billName) 
    {
        this.billName = billName;
    }

    public String getBillName() 
    {
        return billName;
    }
    public void setBillRemark(String billRemark) 
    {
        this.billRemark = billRemark;
    }

    public String getBillRemark() 
    {
        return billRemark;
    }
    public void setPayUserId(Long payUserId) 
    {
        this.payUserId = payUserId;
    }

    public Long getPayUserId() 
    {
        return payUserId;
    }
    public void setTakeUserId(Long takeUserId) 
    {
        this.takeUserId = takeUserId;
    }

    public Long getTakeUserId() 
    {
        return takeUserId;
    }
    public void setAmount(Double amount)
    {
        this.amount = amount;
    }

    public Double getAmount()
    {
        return amount;
    }
    public void setPayTime(Date payTime) 
    {
        this.payTime = payTime;
    }

    public Date getPayTime() 
    {
        return payTime;
    }
    public void setCtime(Date ctime) 
    {
        this.ctime = ctime;
    }

    public Date getCtime() 
    {
        return ctime;
    }
    public void setPayState(String payState) 
    {
        this.payState = payState;
    }

    public String getPayState() 
    {
        return payState;
    }
    public void setRefId(Long refId) 
    {
        this.refId = refId;
    }

    public Long getRefId() 
    {
        return refId;
    }

    public String getPayNo() {
        return payNo;
    }

    public void setPayNo(String payNo) {
        this.payNo = payNo;
    }

    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    public String getPayUserName() {
		return payUserName;
	}

	public void setPayUserName(String payUserName) {
		this.payUserName = payUserName;
	}

	public String getTakeUserName() {
		return takeUserName;
	}

	public void setTakeUserName(String takeUserName) {
		this.takeUserName = takeUserName;
	}

	public String getRefName() {
		return refName;
	}

	public void setRefName(String refName) {
		this.refName = refName;
	}

	@Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("billId", getBillId())
            .append("billNo", getBillNo())
            .append("billType", getBillType())
            .append("billName", getBillName())
            .append("billRemark", getBillRemark())
            .append("payUserId", getPayUserId())
            .append("takeUserId", getTakeUserId())
            .append("amount", getAmount())
            .append("payTime", getPayTime())
            .append("ctime", getCtime())
            .append("payState", getPayState())
            .append("refId", getRefId())
            .append("payNo", getPayNo())
            .append("deptId", getDeptId())
            .toString();
    }
}
