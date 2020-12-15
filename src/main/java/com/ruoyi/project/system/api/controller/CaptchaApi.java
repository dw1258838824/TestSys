package com.ruoyi.project.system.api.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;
import com.testsys.common.Cache;
import com.testsys.common.SendMessage;

@RestController
@RequestMapping("/api/captcha")
public class CaptchaApi {

	@GetMapping("/code/{phone}")
	public Map<String,Object> getCaptcha(@PathVariable("phone")String phone){
		Map<String,Object> rest = new HashMap<>();
		String regex = "^((13[0-9])|(14[0-9])|(15[0-9])|(17[0-9])|(18[0-9]))\\d{8}$";
        if(phone.length() != 11){
        	rest.put("code", "500");
        	rest.put("msg","请输入11位手机号码");
        	return rest;
        }else{
            Pattern p = Pattern.compile(regex);
            Matcher m = p.matcher(phone);
            boolean isMatch = m.matches();
//            if(isMatch){
            	String authCode = SendMessage.sendMessage(phone);
            	if(StringUtils.isEmpty(authCode)) {
            		rest.put("code", "500");
            		rest.put("msg","发送失败");
            	}else {
            		//有效期15分钟
            		Cache.put(phone, authCode, 900000);
            		rest.put("code", "0");
            	}
//            } else {
//            	rest.put("code", "500");
//            	rest.put("msg","请输入正确的手机号码");
//            }
        }
		return rest;
	}

}
