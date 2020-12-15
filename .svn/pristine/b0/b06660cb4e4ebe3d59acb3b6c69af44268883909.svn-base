package com.ruoyi.project.monitor.job.task;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.collect.Maps;
import com.ruoyi.common.utils.ServletUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.project.system.register.domain.TExamRegister;
import com.ruoyi.project.system.register.service.ITExamRegisterService;
import com.testsys.common.SendMessage;

/**
 * 定时任务调度测试
 * 
 * @author ruoyi
 */
@Component("ryTask")
public class RyTask
{
    @Autowired
    private ITExamRegisterService tExamRegisterService;
    
    public void ryMultipleParams(String s, Boolean b, Long l, Double d, Integer i)
    {
        System.out.println(StringUtils.format("执行多参方法： 字符串类型{}，布尔类型{}，长整型{}，浮点型{}，整形{}", s, b, l, d, i));
    }

    public void ryParams(String params)
    {
        System.out.println("执行有参方法：" + params);
    }

    public void ryNoParams()
    {
        System.out.println("执行无参方法");
    }
    
    public void getMyIp(String execIp)
    {
        System.out.println("执行机器IP： " + ServletUtils.getMyIp());
        if(ServletUtils.getMyIp().equals(execIp)) {
        	TExamRegister tr = new TExamRegister();
        	tr.getParams().put("isYesterday", "1");
        	List<TExamRegister> tlist = tExamRegisterService.selectTExamRegisterList(tr);
        	System.out.println("明日考试学生数量："+tlist.size());
        	for(TExamRegister er : tlist) {
        		Object mobile = er.getParams().get("mobile");
        		if(StringUtils.isNotEmpty(mobile+"")) {
        			Map<String,String> parm = Maps.newHashMap();
        			parm.put("examTitle", er.getParams().get("examTitle")+"");
        			parm.put("beginTime", er.getParams().get("beginTime")+"");
        			SendMessage.sendExamMessage(mobile+"", parm);
        		}
        	}
        }
    }
}
