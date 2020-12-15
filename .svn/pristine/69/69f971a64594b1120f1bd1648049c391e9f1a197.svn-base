package com.ruoyi.project.system.student.domain;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;
/**
 * 学员对象 t_student
 * 
 * @author ruoyi
 * @date 2020-06-05
 */
public class Student extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 学员编号 */
    private Long studentId;

    /** 学员名称 */
    @Excel(name = "学员名称")
    private String studentName;

    /** 等级 */
    private Long levelId;

    /** 账户编号 */
    @Excel(name = "账户编号")
    private Long userId;

    /** 学员生日 */
    @Excel(name = "学员生日", width = 30, dateFormat = "yyyy-MM-dd")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    private Date birthday;

    /** 性别 0男 1女 2未知 */
    @Excel(name = "性别 0男 1女 2未知")
    private String sex;

    /** 手机号 */
    @Excel(name = "监护人手机号")
    private String mobile;

    /** 绑定微信ID */
    private String wechatId;

    /** 身份证号码 */
    @Excel(name = "身份证号码")
    private String idcard;

    /** 身份证图片 */
    private String idcardImg;

    /** 证书图片 */
    private String certImg;

    /** 注册时间 */
    @Excel(name = "注册时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm")
    private Date ctime;

    private Long fromUserId;
    
    /** classIn Uid */
    private Long uid;
    
    private String faceToken;
    
    private Long deptId;
    
    private String faceImg;
    
    @Excel(name = "监护人姓名")
    private String parentName;
    

    public String getFaceToken() {
		return faceToken;
	}

	public void setFaceToken(String faceToken) {
		this.faceToken = faceToken;
	}

	public void setStudentId(Long studentId) 
    {
        this.studentId = studentId;
    }

    public Long getStudentId() 
    {
        return studentId;
    }
    public void setStudentName(String studentName) 
    {
        this.studentName = studentName;
    }

    public String getStudentName() 
    {
        return studentName;
    }
    public void setLevelId(Long levelId) 
    {
        this.levelId = levelId;
    }

    public Long getLevelId() 
    {
        return levelId;
    }
    public void setUserId(Long userId) 
    {
        this.userId = userId;
    }

    public Long getUserId() 
    {
        return userId;
    }
    public void setBirthday(Date birthday) 
    {
        this.birthday = birthday;
    }

    public Date getBirthday() 
    {
        return birthday;
    }
    public void setSex(String sex) 
    {
        this.sex = sex;
    }

    public String getSex() 
    {
        return sex;
    }
    public void setMobile(String mobile) 
    {
        this.mobile = mobile;
    }

    public String getMobile() 
    {
        return mobile;
    }
    public void setWechatId(String wechatId) 
    {
        this.wechatId = wechatId;
    }

    public String getWechatId() 
    {
        return wechatId;
    }
    public void setIdcard(String idcard) 
    {
        this.idcard = idcard;
    }

    public String getIdcard() 
    {
        return idcard;
    }
    public void setIdcardImg(String idcardImg) 
    {
        this.idcardImg = idcardImg;
    }

    public String getIdcardImg() 
    {
        return idcardImg;
    }
    public void setCertImg(String certImg) 
    {
        this.certImg = certImg;
    }

    public String getCertImg() 
    {
        return certImg;
    }
    public void setCtime(Date ctime) 
    {
        this.ctime = ctime;
    }

    public Date getCtime() 
    {
        return ctime;
    }
    public void setFromUserId(Long fromUserId) 
    {
        this.fromUserId = fromUserId;
    }

    public Long getFromUserId() 
    {
        return fromUserId;
    }

    public Long getUid() {
		return uid;
	}

	public void setUid(Long uid) {
		this.uid = uid;
	}

    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    public String getFaceImg() {
		return faceImg;
	}

	public void setFaceImg(String faceImg) {
		this.faceImg = faceImg;
	}

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	@Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("studentId", getStudentId())
            .append("studentName", getStudentName())
            .append("levelId", getLevelId())
            .append("userId", getUserId())
            .append("birthday", getBirthday())
            .append("sex", getSex())
            .append("mobile", getMobile())
            .append("wechatId", getWechatId())
            .append("idcard", getIdcard())
            .append("idcardImg", getIdcardImg())
            .append("certImg", getCertImg())
            .append("ctime", getCtime())
            .append("fromUserId", getFromUserId())
            .append("deptId", getDeptId())
            .toString();
    }
}
