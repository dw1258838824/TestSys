package com.ruoyi.project.system.student.mapper;

import java.util.List;
import java.util.Map;

import com.ruoyi.project.system.student.domain.Student;
import com.ruoyi.project.system.student.domain.StudentLevel;

/**
 * 学员Mapper接口
 * 
 * @author ruoyi
 * @date 2020-06-05
 */
public interface StudentMapper 
{
    /**
     * 查询学员
     * 
     * @param studentId 学员ID
     * @return 学员
     */
    public Student selectStudentById(Long studentId);

    public Student selectStudentByMobile(String mobile);
    
    /**
     * 工具系统用户ID查询学员
     * @param userId 系统用户id
     * @return
     */
    public Student selectUserById(Long userId);
    public Student selectStudentByIdcard(String idcard);
    
    /**
     * 查询学员列表
     * 
     * @param student 学员
     * @return 学员集合
     */
    public List<Student> selectStudentList(Student student);

    /**
     * 新增学员
     * 
     * @param student 学员
     * @return 结果
     */
    public int insertStudent(Student student);

    /**
     * 修改学员
     * 
     * @param student 学员
     * @return 结果
     */
    public int updateStudent(Student student);

    /**
     * 删除学员
     * 
     * @param studentId 学员ID
     * @return 结果
     */
    public int deleteStudentById(Long studentId);

    /**
     * 批量删除学员
     * 
     * @param studentIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteStudentByIds(String[] studentIds);

    public int updateWeChatIdByStudent(Student student);

    public int updateFaceTokenByStudent(Student student);

    public Student selectStudentByWeChatId(Student student);

    public List<StudentLevel> selectStudentLevel(StudentLevel studentLevel);
    /**
     * 查询学生科目等级信息
     * @param parm
     * @return
     */
    public List<Map<String, Object>> selectStudentLevelInfo(Map<String, Object> parm);
    
    /**
     * 批量删除学员人脸
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteFaceStudentByIds(String[] studentIds);
    
    /**
     * 检测学生某个自动是否已存在 如果传入student_id则忽略该条
     * @param stu
     * @return
     */
    public int checkAnyThingUnique(Student stu);
    
    /**
     * 修改学员科目等级
     * @param parm
     * @return
     */
    public int updateStudentLevel(Map<String, Object> parm);
}
