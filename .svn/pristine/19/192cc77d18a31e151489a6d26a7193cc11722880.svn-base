package com.ruoyi.project.system.computer.controller;

import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.project.system.student.domain.Student;
import com.ruoyi.project.system.student.service.IStudentService;
import com.testsys.common.FaceRecognitionAuth;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;

@Api("人脸识别API")
@Controller
@RequestMapping("/student/faceauth")
public class FaceAuthApi {
	
	private String prefix = "system/computer";

	@Autowired
	private IStudentService studentService;

	/**
	 * 人脸设置
	 * @param faceImg
	 * @return
	 */
	@ApiOperation("新增人脸")
	@PostMapping("/set")
	@ResponseBody
	public Map<String,Object> setFaceAuth(String faceImg){
		Map<String, Object> rest = new HashMap<String, Object>();
		Student student = studentService.selectUserById(ShiroUtils.getUserId());
		String faceToken = FaceRecognitionAuth.faceSet("student", String.valueOf(student.getStudentId()), faceImg, "人脸识别");
		if("error".equals(faceToken)){
			rest.put("code","500");
			rest.put("msg", "人脸设置失败，请确认是否重复录入人脸，或调正姿态再试");
		}else{
			Student stu = new Student();
			stu.setStudentId(student.getStudentId());
			stu.setFaceToken(faceToken);
			stu.setFaceImg(faceImg);
			System.out.println(faceToken);
			studentService.updateFaceTokenByStudent(stu);
			rest.put("code","0");
			rest.put("msg", "成功");
		}
		return rest;
	}

	/**
	 * 人脸对比验证
	 * @param faceImg
	 * @return
	 */
	@ApiOperation("人脸校验")
	@PostMapping("/get")
	@ResponseBody
	public Map<String,Object> getFaceAuth(String faceImg){
		Map<String, Object> rest = new HashMap<String, Object>();
		Student student = studentService.selectUserById(ShiroUtils.getUserId());
		String status = FaceRecognitionAuth.faceMatch(faceImg,student.getFaceToken());
		if("success".equals(status)){
			rest.put("code","0");
			rest.put("msg", "成功");
		}else{
			rest.put("code","500");
			rest.put("msg", "失败");
		}
		return rest;
	}

}
