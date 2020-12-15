package com.ruoyi.project.system.question.domain;

import java.io.ByteArrayInputStream;
import java.util.Date;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.aspectj.lang.annotation.Excel.Type;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 题库对象 t_question
 * 
 * @author ruoyi
 * @date 2020-05-27
 */
public class TQuestion extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 题目编号 */
    private Long questionId;

    /** 题目标题 */
    @Excel(name = "题目标题")
    private String questionTitle;

    /** 题目内容 */
    private String questionHtml;
    
    @Excel(name = "题目选项")
    private String questionOptions;

    /** 题目答案 */
    @Excel(name = "题目答案")
    private String questionAnswer;
    
    /** 冗余字段 BEGIN  **/
    @Excel(name = "题目类型")
    private String questionTypeName;
	@Excel(name = "知识点")
    private String questionPointName;
    @Excel(name = "等级")
    private String levelName;
    @Excel(name = "科目")
    private String subjectName;
    /** 冗余字段 END  **/

    /** 答案解析 */
    private String questionAnswerAnalysis;

    /** 题目类型 */
    private Long questionTypeId;

    /** 等级 */
    private Long levelId;

    /** 科目 */
    private Long subjectId;

    /** 知识点 */
    private Long questionPointId;

    /** 题目关联文件路径 */
    private String questionFile;

    /** 创建时间 */
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm")
    private Date ctime;

    /** 创建者 */
    @Excel(name = "创建者")
    private String creator;

    /** 更新时间 */
    @Excel(name = "更新时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm")
    private Date utime;

    /** 更新人 */
    @Excel(name = "更新人")
    private String uperson;

    /** 是否删除 */
    private String isDelete;
    

	

    public void setQuestionId(Long questionId) 
    {
        this.questionId = questionId;
    }

    public Long getQuestionId() 
    {
        return questionId;
    }
    public void setQuestionTitle(String questionTitle) 
    {
        this.questionTitle = questionTitle;
    }

    public String getQuestionTitle() 
    {
        return questionTitle;
    }
    public void setQuestionHtml(String questionHtml) 
    {
        this.questionHtml = questionHtml;
    }

    public String getQuestionHtml() 
    {
        return questionHtml;
    }
    public void setQuestionAnswer(String questionAnswer) 
    {
        this.questionAnswer = questionAnswer;
    }

    public String getQuestionAnswer() 
    {
        return questionAnswer;
    }
    public void setQuestionAnswerAnalysis(String questionAnswerAnalysis) 
    {
        this.questionAnswerAnalysis = questionAnswerAnalysis;
    }

    public String getQuestionAnswerAnalysis() 
    {
        return questionAnswerAnalysis;
    }
    public void setQuestionTypeId(Long questionTypeId) 
    {
        this.questionTypeId = questionTypeId;
    }

    public Long getQuestionTypeId() 
    {
        return questionTypeId;
    }
    public void setLevelId(Long levelId) 
    {
        this.levelId = levelId;
    }

    public Long getLevelId() 
    {
        return levelId;
    }
    public void setSubjectId(Long subjectId) 
    {
        this.subjectId = subjectId;
    }

    public Long getSubjectId() 
    {
        return subjectId;
    }
    public void setQuestionPointId(Long questionPointId) 
    {
        this.questionPointId = questionPointId;
    }

    public Long getQuestionPointId() 
    {
        return questionPointId;
    }
    public void setQuestionFile(String questionFile) 
    {
        this.questionFile = questionFile;
    }

    public String getQuestionFile() 
    {
        return questionFile;
    }
    public void setCtime(Date ctime) 
    {
        this.ctime = ctime;
    }

    public Date getCtime() 
    {
        return ctime;
    }
    public void setCreator(String creator) 
    {
        this.creator = creator;
    }

    public String getCreator() 
    {
        return creator;
    }
    public void setUtime(Date utime) 
    {
        this.utime = utime;
    }

    public Date getUtime() 
    {
        return utime;
    }
    public void setUperson(String uperson) 
    {
        this.uperson = uperson;
    }

    public String getUperson() 
    {
        return uperson;
    }
    public void setIsDelete(String isDelete) 
    {
        this.isDelete = isDelete;
    }

    public String getIsDelete() 
    {
        return isDelete;
    }
    public String getQuestionTypeName() {
		return questionTypeName;
	}

	public void setQuestionTypeName(String questionTypeName) {
		this.questionTypeName = questionTypeName;
	}

	public String getQuestionPointName() {
		return questionPointName;
	}

	public void setQuestionPointName(String questionPointName) {
		this.questionPointName = questionPointName;
	}

	public String getLevelName() {
		return levelName;
	}

	public void setLevelName(String levelName) {
		this.levelName = levelName;
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}


	public String getQuestionOptionsStr() {
    	String xml = getQuestionHtml();
    	StringBuffer sb = new StringBuffer();
		try {
			DocumentBuilder newDocumentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			Document parse = newDocumentBuilder.parse(new ByteArrayInputStream(xml.getBytes()));
			NodeList list = parse.getElementsByTagName("options");
			if(list.getLength()>0) {
				for(int i=0 ;i<list.getLength();i++) {
					Node n = list.item(i);
					String code = n.getChildNodes().item(0).getFirstChild().getNodeValue();
					String option = "";
					try {
						option = n.getChildNodes().item(1).getFirstChild().getNodeValue();
					} catch (Exception e) {
					}
					sb.append(code).append(":").append(option).append("\n");
				}
			}
			
		} catch (Exception e) {
		
		}
		return sb.toString();
	}

	public String getQuestionOptions() {
		return  getQuestionOptionsStr();
	}
	public String getQuestionOptionsR() {
		return  questionOptions;
	}
	public void setQuestionOptions(String questionOptions) {
		this.questionOptions = questionOptions;
	}
	

	@Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("questionId", getQuestionId())
            .append("questionTitle", getQuestionTitle())
            .append("questionHtml", getQuestionHtml())
            .append("questionAnswer", getQuestionAnswer())
            .append("questionAnswerAnalysis", getQuestionAnswerAnalysis())
            .append("questionTypeId", getQuestionTypeId())
            .append("levelId", getLevelId())
            .append("subjectId", getSubjectId())
            .append("questionPointId", getQuestionPointId())
            .append("questionFile", getQuestionFile())
            .append("ctime", getCtime())
            .append("creator", getCreator())
            .append("utime", getUtime())
            .append("uperson", getUperson())
            .append("isDelete", getIsDelete())
            .append("questionTypeName", getQuestionTypeName())
            .append("questionPointName", getQuestionPointName())
            .append("levelName", getLevelName())
            .append("subjectName", getSubjectName())
            .toString();
    }
}
