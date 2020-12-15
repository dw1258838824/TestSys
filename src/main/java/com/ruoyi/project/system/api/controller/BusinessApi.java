package com.ruoyi.project.system.api.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ruoyi.project.system.business.domain.TBusiness;
import com.ruoyi.project.system.business.service.ITBusinessService;

@RestController
@RequestMapping("/api/business")
public class BusinessApi {

   
    @Autowired
    private ITBusinessService businessService;
    
    @PostMapping("/addBusiness")
    public String addBusiness(HttpServletRequest request,TBusiness tBusiness) {
    	if(businessService.insertTBusiness(tBusiness)>0) {
    		return "success";
    	}
    	return "error";
    }
}
