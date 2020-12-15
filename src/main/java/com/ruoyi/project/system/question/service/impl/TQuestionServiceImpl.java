package com.ruoyi.project.system.question.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruoyi.common.exception.BusinessException;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.common.utils.text.Convert;
import com.ruoyi.project.system.level.domain.TLevel;
import com.ruoyi.project.system.level.mapper.TLevelMapper;
import com.ruoyi.project.system.point.domain.TQuestionPoint;
import com.ruoyi.project.system.point.mapper.TQuestionPointMapper;
import com.ruoyi.project.system.question.domain.TQuestion;
import com.ruoyi.project.system.question.mapper.TQuestionMapper;
import com.ruoyi.project.system.question.service.ITQuestionService;
import com.ruoyi.project.system.subject.domain.TSubject;
import com.ruoyi.project.system.subject.mapper.TSubjectMapper;
import com.ruoyi.project.system.type.domain.TQuestionType;
import com.ruoyi.project.system.type.mapper.TQuestionTypeMapper;

/**
 * 题库Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-05-27
 */
@Service
public class TQuestionServiceImpl implements ITQuestionService 
{
    @Autowired
    private TQuestionMapper tQuestionMapper;

    @Autowired
    private TQuestionTypeMapper tQuestionTypeMapper;
    @Autowired
    private TQuestionPointMapper tQuestionPointMapper;
    @Autowired
    private TLevelMapper tLevelMapper;
    @Autowired
    private TSubjectMapper tSubjectMapper;

    
    /**
     * 查询题库
     * 
     * @param questionId 题库ID
     * @return 题库
     */
    @Override
    public TQuestion selectTQuestionById(Long questionId)
    {
        return tQuestionMapper.selectTQuestionById(questionId);
    }

    /**
     * 查询题库列表
     * 
     * @param tQuestion 题库
     * @return 题库
     */
    @Override
    public List<TQuestion> selectTQuestionList(TQuestion tQuestion)
    {
        return tQuestionMapper.selectTQuestionList(tQuestion);
    }

    /**
     * 新增题库
     * 
     * @param tQuestion 题库
     * @return 结果
     */
    @Override
    public int insertTQuestion(TQuestion tQuestion)
    {
    	tQuestion.setCreator(ShiroUtils.getLoginName());
    	tQuestion.setUperson(ShiroUtils.getLoginName());
        return tQuestionMapper.insertTQuestion(tQuestion);
    }

    /**
     * 修改题库
     * 
     * @param tQuestion 题库
     * @return 结果
     */
    @Override
    public int updateTQuestion(TQuestion tQuestion)
    {
    	tQuestion.setUperson(ShiroUtils.getLoginName());
        return tQuestionMapper.updateTQuestion(tQuestion);
    }

    /**
     * 删除题库对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteTQuestionByIds(String ids)
    {
        return tQuestionMapper.deleteTQuestionByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除题库信息
     * 
     * @param questionId 题库ID
     * @return 结果
     */
    @Override
    public int deleteTQuestionById(Long questionId)
    {
        return tQuestionMapper.deleteTQuestionById(questionId);
    }
    
    /**
     * 导入用户数据
     * 
     * @param userList 用户数据列表
     * @param isUpdateSupport 是否更新支持，如果已存在，则进行更新数据
     * @return 结果
     */
    @Override
    public String importQuestion(List<TQuestion> list)
    {
        if (StringUtils.isNull(list) || list.size() == 0)
        {
            throw new BusinessException("导入数据不能为空！");
        }
        int successNum = 0;
        int failureNum = 0;
        StringBuilder successMsg = new StringBuilder();
        StringBuilder failureMsg = new StringBuilder();
        String operName = ShiroUtils.getLoginName();
        for (TQuestion tQuestion : list)
        {
            try
            {
            	TQuestionType t = tQuestionTypeMapper.selectTQuestionTypeByName(tQuestion.getQuestionTypeName());
            	tQuestion.setQuestionTypeId(t!=null?t.getQuestionTypeId().longValue():null);
            	TQuestionPoint p = tQuestionPointMapper.selectTQuestionPointByName(tQuestion.getQuestionPointName());
            	tQuestion.setQuestionPointId(p!=null?p.getQuestionPointId():null);
            	TLevel l = tLevelMapper.selectTLevelByName(tQuestion.getLevelName());
            	tQuestion.setLevelId(l!=null?l.getLevelId():null);
            	TSubject s = tSubjectMapper.selectTSubjectByName(tQuestion.getSubjectName());
            	tQuestion.setSubjectId(s!=null?s.getSubjectId().longValue():null);
            	tQuestion.setQuestionHtml(getQuestionHtmlByOptions(tQuestion));
            	tQuestion.setQuestionTitle(tQuestion.getQuestionTitle().split("_images:")[0]);
            	tQuestion.setCreator(operName);
            	tQuestion.setUperson(operName);
                successNum ++ ;
            }
            catch (Exception e)
            {
            	failureNum++;
                String msg = "<br/>" + failureNum + "、题目： " + tQuestion.getQuestionTitle() + " 导入失败：";
                failureMsg.append(msg + e.getMessage());
                e.printStackTrace();
            }
        }
        if (failureNum > 0)
        {
            failureMsg.insert(0, "很抱歉，导入失败！共 " + failureNum + " 条数据格式不正确，错误如下：");
            throw new BusinessException(failureMsg.toString());
        }
        else
        {
            insertBatchTQuestion(list);
            successMsg.insert(0, "恭喜您，数据已全部导入成功！共 " + successNum + " 条");
        }
        return successMsg.toString();
    }
    /**
     * 批量插入
     * @param list
     * @return
     */
    public int insertBatchTQuestion(List<TQuestion> list) {
    	if(list.size()>100) {
    		List<List<TQuestion>> llist = splitList(list,100);
    		for(List<TQuestion> l: llist) {
    			tQuestionMapper.insertBatchTQuestion(l);
    		}
    		return list.size();
    	}else {
    		return tQuestionMapper.insertBatchTQuestion(list);
    	}
    }
    /**
     * 根据options获取HTML
     * @param t
     * @return
     */
    public String getQuestionHtmlByOptions(TQuestion t) {
    	StringBuilder sb = new StringBuilder();
    	String [] questionTitle_Img = t.getQuestionTitle().split("_images:");
    	sb.append("<question><questionTitle>").append(questionTitle_Img[0]).append("</questionTitle>");
    	//标题图片base64
    	sb.append("<titleFileBase64>").append(questionTitle_Img.length>1?questionTitle_Img[1]:"").append("</titleFileBase64>");
    	String [] options = null;
    	String [] optionsImg = null;
    	if(!StringUtils.isEmpty(t.getQuestionOptionsR())) {
    		options = t.getQuestionOptionsR().split("_images:")[0].split("\n");
    		if(t.getQuestionOptionsR().split("_images:").length>1 && null!=t.getQuestionOptionsR().split("_images:")[1]) {
    			optionsImg = t.getQuestionOptionsR().split("_images:")[1].split("\n");
    		}
    	}
    	if((t.getQuestionTypeId() == 1 || t.getQuestionTypeId() == 3) ){//单选题、判断题
    		sb.append("<questionRadio>");
    		//选项图片base64
    		int idx = 0;
    		if(options!=null)
    		for(String option:options) {
    			sb.append("<options><code>").append(option.split(":")[0]).append("</code>");
    			sb.append("<context>").append(option.substring(2, option.length())).append("</context>");
    			sb.append("<optionimg>").append(null!=optionsImg && null!=optionsImg[idx]?optionsImg[idx]:"").append("</optionimg></options>");
    			idx ++;
    		}
        	sb.append("</questionRadio>");
    	}else if(t.getQuestionTypeId() == 2 ){//多选题
    		sb.append("<questionCheckBox>");
        	//选项图片base64
    		int idx = 0;
    		if(options!=null)
    		for(String option:options) {
    			sb.append("<options><code>").append(option.split(":")[0]).append("</code>");
    			sb.append("<context>").append(option.substring(2, option.length())).append("</context>");
    			sb.append("<optionimg>").append(null!=optionsImg && null!=optionsImg[idx]?optionsImg[idx]:"").append("</optionimg></options>");
    			idx ++ ;
    		}
        	sb.append("</questionCheckBox>");
    	}else if(t.getQuestionTypeId() == 6){
    		//TODO 导入语音答辩
    		sb.append("<questionFile>"+""+"</questionFile>");
    	}
    	sb.append("<typeId>").append(t.getQuestionTypeId()).append("</typeId>");
    	sb.append("<answer>").append(t.getQuestionAnswer()).append("</answer><answerAnalysis>").append("").append("</answerAnalysis><score>@score@</score></question>");
    	return sb.toString();
    }
    
    private static List<List<TQuestion>> splitList(List<TQuestion> list , int groupSize){
        int length = list.size();
        // 计算可以分成多少组
        int num = ( length + groupSize - 1 )/groupSize ; // TODO 
        List<List<TQuestion>> newList = new ArrayList<>(num);
        for (int i = 0; i < num; i++) {
            // 开始位置
            int fromIndex = i * groupSize;
            // 结束位置
            int toIndex = (i+1) * groupSize < length ? ( i+1 ) * groupSize : length ;
            newList.add(list.subList(fromIndex,toIndex)) ;
        }
        return  newList ;
    }
}
