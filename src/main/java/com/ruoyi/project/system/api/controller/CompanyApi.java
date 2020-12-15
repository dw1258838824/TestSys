package com.ruoyi.project.system.api.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ruoyi.project.system.company.domain.TCompany;
import com.ruoyi.project.system.company.service.ITCompanyService;

@RestController
@RequestMapping("/api/company")
public class CompanyApi {


    @Autowired
    private ITCompanyService tCompanyService;
    
    @GetMapping("/list")
    public List<TCompany> list(HttpServletRequest request, HttpServletResponse response,ModelMap mmap) {
    	return tCompanyService.selectTCompanyList(null);
    }
}
