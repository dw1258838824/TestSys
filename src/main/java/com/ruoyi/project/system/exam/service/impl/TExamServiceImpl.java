package com.ruoyi.project.system.exam.service.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.common.utils.text.Convert;
import com.ruoyi.project.system.api.service.ClassInService;
import com.ruoyi.project.system.exam.domain.TExam;
import com.ruoyi.project.system.exam.mapper.TExamMapper;
import com.ruoyi.project.system.exam.service.ITExamService;

/**
 * 考试管理Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-06-04
 */
@Service
public class TExamServiceImpl implements ITExamService 
{
	private static final Logger log = LoggerFactory.getLogger(TExamServiceImpl.class);
    @Autowired
    private TExamMapper tExamMapper;

    @Autowired
    private ClassInService classInService;
    /**
     * 查询考试管理
     * 
     * @param examId 考试管理ID
     * @return 考试管理
     */
    @Override
    public TExam selectTExamById(Long examId)
    {
        return tExamMapper.selectTExamById(examId);
    }

    /**
     * 查询考试管理列表
     * 
     * @param tExam 考试管理
     * @return 考试管理
     */
    @Override
    public List<TExam> selectTExamList(TExam tExam)
    {
        return tExamMapper.selectTExamList(tExam);
    }

    /**
     * 新增考试管理
     * 
     * @param tExam 考试管理
     * @return 结果
     */
    @Override
    public int insertTExam(TExam tExam)
    {
    	tExam.setCreator(ShiroUtils.getLoginName());
    	tExam.setOperateBeginTime(tExam.getEndTime());
    	tExam.setArgueBeginTime(tExam.getOperateEndTime());
		try {
			Map<String, String> params = Maps.newHashMap();
			params.put("courseName", tExam.getExamTitle());
//			params.put("expiryTime", (tExam.getArgueEndTime().getTime()/1000)+""); 课程过期时间
			
			String json = classInService.classInHttp("addCourse", params);//调用classIn加课程
			JSONObject obj = JSONObject.parseObject(json);
			Integer errno = obj.getJSONObject("error_info").getInteger("errno");
			if(null!=errno && errno==1) {
				Long uid = obj.getLong("data");//获取课程ID
				tExam.setUid(uid);
			}else {
				log.error("调用classIn添加课程失败",json);
			}
		} catch (Exception e) {
			log.error("调用classIn添加课程失败",e);
		}
		int result = tExamMapper.insertTExam(tExam);
        return result;
    }

    /**
     * 修改考试管理
     * 
     * @param tExam 考试管理
     * @return 结果
     */
    @Override
    public int updateTExam(TExam tExam)
    {
    	tExam.setOperateBeginTime(tExam.getEndTime());
    	tExam.setArgueBeginTime(tExam.getOperateEndTime());
        return tExamMapper.updateTExam(tExam);
    }

    /**
     * 删除考试管理对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteTExamByIds(String ids)
    {
        return tExamMapper.deleteTExamByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除考试管理信息
     * 
     * @param examId 考试管理ID
     * @return 结果
     */
    @Override
    public int deleteTExamById(Long examId)
    {
        return tExamMapper.deleteTExamById(examId);
    }

    @Override
    public TExam apiGet(TExam exam) {
        return tExamMapper.apiGet(exam);
    }

    @Override
    public List<TExam> apiList(TExam exam) {
        return tExamMapper.apiList(exam);
    }

}
