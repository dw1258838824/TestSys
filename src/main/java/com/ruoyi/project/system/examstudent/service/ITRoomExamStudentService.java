package com.ruoyi.project.system.examstudent.service;

import java.util.List;
import java.util.Map;

import com.ruoyi.project.system.examstudent.domain.TQuestionRecord;
import com.ruoyi.project.system.examstudent.domain.TRoomExamStudent;

/**
 * 学员考试Service接口
 * 
 * @author ruoyi
 * @date 2020-06-18
 */
public interface ITRoomExamStudentService 
{
    /**
     * 查询学员考试
     * 
     * @param id 学员考试ID
     * @return 学员考试
     */
    public TRoomExamStudent selectTRoomExamStudentById(Long id);

    /**
     * 查询学员考试列表
     * 
     * @param tRoomExamStudent 学员考试
     * @return 学员考试集合
     */
    public List<TRoomExamStudent> selectTRoomExamStudentList(TRoomExamStudent tRoomExamStudent);

    /**
     * 查询学员考试列表
     * 
     * @param tRoomExamStudent 学员考试
     * @return 学员考试集合
     */
    public List<TRoomExamStudent> selectStudentExamList(TRoomExamStudent tRoomExamStudent);
    
    /**
     * 新增学员考试
     * 
     * @param tRoomExamStudent 学员考试
     * @return 结果
     */
    public int insertTRoomExamStudent(TRoomExamStudent tRoomExamStudent);

    /**
     * 修改学员考试
     * 
     * @param tRoomExamStudent 学员考试
     * @return 结果
     */
    public int updateTRoomExamStudent(TRoomExamStudent tRoomExamStudent);

    /**
     * 批量删除学员考试
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteTRoomExamStudentByIds(String ids);

    /**
     * 删除学员考试信息
     * 
     * @param id 学员考试ID
     * @return 结果
     */
    public int deleteTRoomExamStudentById(Long id);
    

    /**
     * 批量添加做题记录
     * @param qrlist
     * @return
     */
    public int insertQuestionRecords(List<TQuestionRecord> qrlist);

    /**
     * 查询学员题目列表
     * 
     */
    public List<TQuestionRecord> selectQuestionRecords(TQuestionRecord record);
    /**
     * 更新分数
     * @param record
     * @return
     */
    public int updateQuestionRecords(TQuestionRecord record);
    
    /**
     * 
     * 根据学生id与考试id删除考试记录
     * @param tRoomExamStudent
     * @return
     */
    public int deleteTRoomExamByStudentIdAndExamId(TRoomExamStudent tRoomExamStudent); /**
     * 删除学员答题内容
     * 
     * @param tRoomExamStudent 学员考试
     * @return 结果
     */
    public int deleteQuestionRecord(TQuestionRecord qr);
    /**
     * 查询学员考试分数
     * 
     */
    public Map<String, Object> selectStudentScore(TRoomExamStudent tRoomExamStudent);
}
