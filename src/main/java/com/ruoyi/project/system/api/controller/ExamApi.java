package com.ruoyi.project.system.api.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ruoyi.framework.configure.StudentSession;
import com.ruoyi.project.system.bill.domain.Bill;
import com.ruoyi.project.system.bill.service.IBillService;
import com.ruoyi.project.system.exam.domain.TExam;
import com.ruoyi.project.system.exam.service.ITExamService;
import com.ruoyi.project.system.examstudent.domain.TRoomExamStudent;
import com.ruoyi.project.system.examstudent.service.ITRoomExamStudentService;
import com.ruoyi.project.system.student.domain.StudentLevel;
import com.ruoyi.project.system.student.service.IStudentService;
import com.testsys.utils.ApiResult;

@RestController
@RequestMapping("/api/exam")
public class ExamApi {

    @Autowired
    private ITRoomExamStudentService tRoomExamStudentService;
    @Autowired
    private ITExamService examService;
    @Autowired
    private IStudentService studentService;
    @Autowired
    private IBillService billService;

    @GetMapping("/list")
    public ApiResult list(TExam exam, HttpServletRequest request){
        //只允许查询报名中的
        exam.setState("1");
        StudentSession ss = StudentSession.getStudentSession(request);
        if(null == ss){
            return new ApiResult(200,"未登录");
        }
        //查询当前学生所有科目等级
        StudentLevel slp = new StudentLevel();
        slp.setStudentId(ss.getStudentId());
        List<StudentLevel> level = studentService.selectStudentLevel(slp);
        List<TExam> list = examService.apiList(exam);
        if(null != list && list.size() > 0){
            //循环过滤等级不够的考试
            for(int i = 0; i < list.size(); i++) {
                int examLevel = (Integer) list.get(i).getParams().get("levelValue");
                for (StudentLevel sl : level) {
                    if(examLevel > (sl.getLevelValue() + 1) && sl.getSubjectId().equals(list.get(i).getSubjectId())){
                        list.remove(i);
                        i--;
                        continue;
                    }
                }
            }
        }
        return new ApiResult(list);
    }

    @GetMapping("/get")
    public ApiResult get(TExam exam,HttpServletRequest request){
        StudentSession ss = StudentSession.getStudentSession(request);
        Long userId = null;
        if(null == ss){
            return new ApiResult(200,"未登录");
        }else{
            userId = studentService.selectStudentById(ss.getStudentId()).getUserId();
        }
        TExam tex = examService.apiGet(exam);
        Bill bill = new Bill();
        bill.setPayUserId(userId);
        bill.setPayState("1");
        bill.setBillName("1");
        bill.setBillType("1");
        bill.setRefId(exam.getExamId());
        List<Bill> billList = billService.selectBillList(bill);
        //判断当前考试是否已经报名
        if(null != billList && billList.size() > 0){
            tex.setPayState("1");
        }
        return new ApiResult(tex);
    }
    
    @GetMapping("/getScore")
    public ApiResult getScore(HttpServletRequest request){
        StudentSession ss = StudentSession.getStudentSession(request);
        if(null == ss){
            return new ApiResult(200,"未登录");
        }
        TRoomExamStudent tRoomExamStudent = new TRoomExamStudent();
        tRoomExamStudent.setStudentId(ss.getStudentId());
        tRoomExamStudent.setJudgeState("3");
        List<TRoomExamStudent> list = tRoomExamStudentService.selectTRoomExamStudentList(tRoomExamStudent);
        return new ApiResult(list);
    }

    @GetMapping("/getStudentScore")
    public ApiResult getStudentScore(TRoomExamStudent tRoomExamStudent,HttpServletRequest request){
        Map<String, Object> data = tRoomExamStudentService.selectStudentScore(tRoomExamStudent);
        return new ApiResult(data);
    }
}
