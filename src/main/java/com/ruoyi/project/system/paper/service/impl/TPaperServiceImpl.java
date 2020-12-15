package com.ruoyi.project.system.paper.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.common.utils.text.Convert;
import com.ruoyi.project.system.paper.domain.TPaper;
import com.ruoyi.project.system.paper.mapper.TPaperMapper;
import com.ruoyi.project.system.paper.service.ITPaperService;
import com.ruoyi.project.system.question.domain.TQuestion;
import com.ruoyi.project.system.question.mapper.TQuestionMapper;
import com.ruoyi.project.system.type.domain.TQuestionType;
import com.ruoyi.project.system.type.mapper.TQuestionTypeMapper;

/**
 * 试卷管理Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-06-02
 */
@Service
public class TPaperServiceImpl implements ITPaperService 
{
    @Autowired
    private TPaperMapper tPaperMapper;
    
    @Autowired
    private TQuestionTypeMapper tQuestionTypeMapper;

    @Autowired
    private TQuestionMapper tQuestionMapper;

    /**
     * 查询试卷管理
     * 
     * @param paperId 试卷管理ID
     * @return 试卷管理
     */
    @Override
    public TPaper selectTPaperById(Long paperId)
    {
        return tPaperMapper.selectTPaperById(paperId);
    }

    /**
     * 查询试卷管理列表
     * 
     * @param tPaper 试卷管理
     * @return 试卷管理
     */
    @Override
    public List<TPaper> selectTPaperList(TPaper tPaper)
    {
        return tPaperMapper.selectTPaperList(tPaper);
    }

    /**
     * 新增试卷管理
     * 
     * @param tPaper 试卷管理
     * @return 结果
     */
    @SuppressWarnings("all")
    @Transactional
	@Override
    public int insertTPaper(TPaper tPaper,Map<String,Object> parm)
    {
    	int row = 0;
		try {
			tPaper.setCreator(ShiroUtils.getLoginName());
			List<TQuestionType> typeList = tQuestionTypeMapper.selectTQuestionTypeList(null);
			int count = 0;
			for (TQuestionType t : typeList) {
				t.setDefaultCount(Integer.parseInt(parm.get("questionCount"+t.getQuestionTypeId()).toString()));
				t.setDefaultScore(Double.valueOf(parm.get("questionScore"+t.getQuestionTypeId()).toString()));
				count += t.getDefaultCount();
			}
			parm.put("typeList", typeList);
			List<TQuestion> questionList = tQuestionMapper.selectRandList(parm);
			if(null==questionList || questionList.size()!=count) {
				throw new RuntimeException("试卷生成失败！请确认题库是否缺少该类题目！");
			}
			parm.put("questionList", questionList);
			StringBuilder htmlsb = new StringBuilder();
			StringBuilder answerJson = new StringBuilder();
			htmlsb.append("<all>").append("<paperTitle>").append(tPaper.getPaperName()).append("</paperTitle>");
			htmlsb.append("<allScore>").append(tPaper.getAllScore()).append("</allScore>");
			htmlsb.append("<passScore>").append(tPaper.getPassScore()).append("</passScore>");
			answerJson.append("{\"answerList\":[");
			for(int i=0; i<questionList.size();i++) {
				String html = questionList.get(i).getQuestionHtml();
				html = html.replace("@score@", parm.get("questionScore"+questionList.get(i).getQuestionTypeId()).toString());
				htmlsb.append(html.replace("</question>", ""));
				htmlsb.append("<questionId>").append(questionList.get(i).getQuestionId()).append("</questionId>");
				htmlsb.append("<questionType>").append(questionList.get(i).getQuestionTypeName()).append("</questionType>");
				htmlsb.append("<questionCount>").append(parm.get("questionCount"+questionList.get(i).getQuestionTypeId()).toString()).append("</questionCount>");
				htmlsb.append("<typeMode>").append(questionList.get(i).getParams().get("typeMode")).append("</typeMode>");
				htmlsb.append("</question>");
				String after = "\"},";
				if(i==questionList.size()-1) {
					after = "\"}";
				}
				answerJson.append("{\"questionId\":\"").append(questionList.get(i).getQuestionId()).append("\",\"answer\":\"").append(questionList.get(i).getQuestionAnswer()).append("\",\"questionType\":\"").append(questionList.get(i).getQuestionTypeId()).append("\",\"score\":\"").append(parm.get("questionScore"+questionList.get(i).getQuestionTypeId()).toString()).append(after);
			}
			htmlsb.append("</all>");
			answerJson.append("]}");
			tPaper.setPaperDoc(htmlsb.toString());
			tPaper.setExamAnswerJson(answerJson.toString());
			
			row = tPaperMapper.insertTPaper(tPaper);
			parm.put("paperId", tPaper.getPaperId());
			int row2 = tPaperMapper.insertPaperQuestion(parm);
		} catch (Exception e) {
			throw new RuntimeException("试卷生成失败！请确认题库是否缺少该类题目！");
		}
        return row;
    }

    /**
     * 修改试卷管理
     * 
     * @param tPaper 试卷管理
     * @return 结果
     */
    @Override
    public int updateTPaper(TPaper tPaper)
    {
        return tPaperMapper.updateTPaper(tPaper);
    }

    /**
     * 删除试卷管理对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    @Transactional
    public int deleteTPaperByIds(String ids)
    {
    	int row = tPaperMapper.deleteTPaperByIds(Convert.toStrArray(ids));
    	if(row==0) {
    		throw new RuntimeException("试卷删除失败！该试卷已被使用！");
    	}
        return row;
    }

    /**
     * 删除试卷管理信息
     * 
     * @param paperId 试卷管理ID
     * @return 结果
     */
    @Override
    public int deleteTPaperById(Long paperId)
    {
        return tPaperMapper.deleteTPaperById(paperId);
    }
}
