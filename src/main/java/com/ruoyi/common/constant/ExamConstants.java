package com.ruoyi.common.constant;

import java.util.List;
import java.util.Map;

import com.google.common.collect.Maps;
import com.ruoyi.project.system.exam.domain.TExam;

/**
 * 考试静态变量信息
 * 
 * @author ruoyi
 */
public class ExamConstants
{
	public static Map<String,List<TExam>> examMap = Maps.newConcurrentMap();
}
