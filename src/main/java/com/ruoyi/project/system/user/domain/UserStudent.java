package com.ruoyi.project.system.user.domain;

import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.aspectj.lang.annotation.Excel.Type;

/**
 * 用户对象 sys_user
 * 
 * @author ruoyi
 */
public class UserStudent 
{

    @Excel(name = "机构编号", type = Type.IMPORT)
    private Long deptId;

    @Excel(name = "学员姓名")
    private String studentName;
    
    @Excel(name = "身份证")
    private String idcard;

    @Excel(name = "监护人")
    private String parentName;
    
    @Excel(name = "监护人手机号")
    private String mobile;

    @Excel(name = "学员性别", readConverterExp = "0=男,1=女,2=未知")
    private String sex;

	public Long getDeptId() {
		return deptId;
	}

	public void setDeptId(Long deptId) {
		this.deptId = deptId;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	

	public String getIdcard() {
		return idcard;
	}

	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

    
}
