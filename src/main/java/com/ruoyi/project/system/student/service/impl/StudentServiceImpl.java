package com.ruoyi.project.system.student.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruoyi.common.constant.UserConstants;
import com.ruoyi.common.utils.text.Convert;
import com.ruoyi.framework.aspectj.lang.annotation.DataScope;
import com.ruoyi.project.system.student.domain.Student;
import com.ruoyi.project.system.student.domain.StudentLevel;
import com.ruoyi.project.system.student.mapper.StudentMapper;
import com.ruoyi.project.system.student.service.IStudentService;

/**
 * 学员Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-06-05
 */
@Service
public class StudentServiceImpl implements IStudentService 
{
    @Autowired
    private StudentMapper studentMapper;

    /**
     * 查询学员
     * 
     * @param studentId 学员ID
     * @return 学员
     */
    @Override
    public Student selectStudentById(Long studentId)
    {
        return studentMapper.selectStudentById(studentId);
    }

    /**
     * 查询学员列表
     * 
     * @param student 学员
     * @return 学员
     */
    @Override
    @DataScope(deptAlias = "d", userAlias = "u")
    public List<Student> selectStudentList(Student student)
    {
        return studentMapper.selectStudentList(student);
    }

    /**
     * 新增学员
     * 
     * @param student 学员
     * @return 结果
     */
    @Override
    public int insertStudent(Student student)
    {
        return studentMapper.insertStudent(student);
    }

    /**
     * 修改学员
     * 
     * @param student 学员
     * @return 结果
     */
    @Override
    public int updateStudent(Student student)
    {
        return studentMapper.updateStudent(student);
    }

    /**
     * 删除学员对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteStudentByIds(String ids)
    {
        return studentMapper.deleteStudentByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除学员信息
     * 
     * @param studentId 学员ID
     * @return 结果
     */
    @Override
    public int deleteStudentById(Long studentId)
    {
        return studentMapper.deleteStudentById(studentId);
    }

    @Override
    public int updateWeChatIdByStudent(Student student) {
        return studentMapper.updateWeChatIdByStudent(student);
    }

    @Override
	public Student selectUserById(Long userId) {
		return studentMapper.selectUserById(userId);
	}
	
	public int updateFaceTokenByStudent(Student student) {
		return studentMapper.updateFaceTokenByStudent(student);
	}

    public Student selectStudentByWeChatId(Student student){
        return studentMapper.selectStudentByWeChatId(student);
    }

    public Student selectStudentByMobile(String mobile){
        return studentMapper.selectStudentByMobile(mobile);
    }

    public List<StudentLevel> selectStudentLevel(StudentLevel studentLevel){
        return studentMapper.selectStudentLevel(studentLevel);
    }
    

    /**
     * 查询学生科目等级信息
     * @param parm
     * @return
     */
    public List<Map<String, Object>> selectStudentLevelInfo(Map<String, Object> parm){
        return studentMapper.selectStudentLevelInfo(parm);
    }
    

    /**
     * 批量删除学员人脸
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteFaceStudentByIds(String studentIds) {
    	return studentMapper.deleteFaceStudentByIds(Convert.toStrArray(studentIds));
    }
    
    /**
     * 校验身份证
     */
    public String checkAnyThingUnique(Student stu) {
        if (studentMapper.checkAnyThingUnique(stu)>0){
            return UserConstants.USER_PHONE_NOT_UNIQUE;
        }
        return UserConstants.USER_PHONE_UNIQUE;
    }
    
    @Override
    public Student selectStudentByIdcard(String idcard) {
    	return studentMapper.selectStudentByIdcard(idcard);
    }
    
    /**
     * 修改学员科目等级
     * @param parm
     * @return
     */
    public int updateStudentLevel(Map<String, Object> parm) {
    	return studentMapper.updateStudentLevel(parm);
    }
}
