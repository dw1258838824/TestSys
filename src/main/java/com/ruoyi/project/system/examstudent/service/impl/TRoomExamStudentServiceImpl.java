package com.ruoyi.project.system.examstudent.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruoyi.common.utils.text.Convert;
import com.ruoyi.framework.aspectj.lang.annotation.DataScope;
import com.ruoyi.project.system.examstudent.domain.TQuestionRecord;
import com.ruoyi.project.system.examstudent.domain.TRoomExamStudent;
import com.ruoyi.project.system.examstudent.mapper.TRoomExamStudentMapper;
import com.ruoyi.project.system.examstudent.service.ITRoomExamStudentService;

/**
 * 学员考试Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-06-18
 */
@Service
public class TRoomExamStudentServiceImpl implements ITRoomExamStudentService 
{
    @Autowired
    private TRoomExamStudentMapper tRoomExamStudentMapper;

    /**
     * 查询学员考试
     * 
     * @param id 学员考试ID
     * @return 学员考试
     */
    @Override
    public TRoomExamStudent selectTRoomExamStudentById(Long id)
    {
        return tRoomExamStudentMapper.selectTRoomExamStudentById(id);
    }

    /**
     * 查询学员考试列表
     * 
     * @param tRoomExamStudent 学员考试
     * @return 学员考试
     */
    @Override
    @DataScope(deptAlias = "u")
    public List<TRoomExamStudent> selectTRoomExamStudentList(TRoomExamStudent tRoomExamStudent)
    {
        return tRoomExamStudentMapper.selectTRoomExamStudentList(tRoomExamStudent);
    }

    /**
     * 查询学员考试列表
     * 
     * @param tRoomExamStudent 学员考试
     * @return 学员考试集合
     */
    public List<TRoomExamStudent> selectStudentExamList(TRoomExamStudent tRoomExamStudent){
    	 return tRoomExamStudentMapper.selectStudentExamList(tRoomExamStudent);
    }
    /**
     * 新增学员考试
     * 
     * @param tRoomExamStudent 学员考试
     * @return 结果
     */
    @Override
    public int insertTRoomExamStudent(TRoomExamStudent tRoomExamStudent)
    {
        return tRoomExamStudentMapper.insertTRoomExamStudent(tRoomExamStudent);
    }

    /**
     * 修改学员考试
     * 
     * @param tRoomExamStudent 学员考试
     * @return 结果
     */
    @Override
    public int updateTRoomExamStudent(TRoomExamStudent tRoomExamStudent)
    {
        return tRoomExamStudentMapper.updateTRoomExamStudent(tRoomExamStudent);
    }

    /**
     * 删除学员考试对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteTRoomExamStudentByIds(String ids)
    {
        return tRoomExamStudentMapper.deleteTRoomExamStudentByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除学员考试信息
     * 
     * @param id 学员考试ID
     * @return 结果
     */
    @Override
    public int deleteTRoomExamStudentById(Long id)
    {
        return tRoomExamStudentMapper.deleteTRoomExamStudentById(id);
    }
    

    /**
     * 批量添加做题记录
     * @param qrlist
     * @return
     */
    public int insertQuestionRecords(List<TQuestionRecord> qrlist) {
    	 return tRoomExamStudentMapper.insertQuestionRecords(qrlist);
    }
    

    /**
     * 查询学员题目列表
     * 
     */
    public List<TQuestionRecord> selectQuestionRecords(TQuestionRecord record){
   	 	return tRoomExamStudentMapper.selectQuestionRecords(record);
    }
    
    /**
     * 更新分数
     * @param record
     * @return
     */
    public int updateQuestionRecords(TQuestionRecord record) {
    	return tRoomExamStudentMapper.updateQuestionRecords(record);
    }
    /**
     * 
     * 根据学生id与考试id删除考试记录
     * @param tRoomExamStudent
     * @return
     */
    public int deleteTRoomExamByStudentIdAndExamId(TRoomExamStudent tRoomExamStudent) {
    	return tRoomExamStudentMapper.deleteTRoomExamByStudentIdAndExamId(tRoomExamStudent);
    }
    
    /**
     * 删除学员答题内容
     * 
     * @param tRoomExamStudent 学员考试
     * @return 结果
     */
    public int deleteQuestionRecord(TQuestionRecord qr) {
    	return tRoomExamStudentMapper.deleteQuestionRecord(qr);
    }

    /**
     * 查询学员考试分数
     * 
     */
    public Map<String, Object> selectStudentScore(TRoomExamStudent tRoomExamStudent){
    	return tRoomExamStudentMapper.selectStudentScore(tRoomExamStudent);
    }
}
