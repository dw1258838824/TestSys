package com.ruoyi.project.system.api.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ruoyi.common.constant.NoticeConstants;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.project.system.notice.domain.Notice;
import com.ruoyi.project.system.notice.service.INoticeService;

@RestController
@RequestMapping("/api/notice")
public class NoticeApi {

   
    @Autowired
    private INoticeService noticeService;
    
    @GetMapping("/list")
    public List<Notice> list(HttpServletRequest request, HttpServletResponse response,ModelMap mmap) {
        	Notice notice = new Notice();
        	if(null==NoticeConstants.NOTICES) {
        		NoticeConstants.NOTICES = noticeService.selectNoticeList(notice);
        	}
        	if(StringUtils.isNotEmpty(request.getParameter("noticeType"))) {
        		notice.setNoticeType(request.getParameter("noticeType"));
        	}
        	if(StringUtils.isNotEmpty(request.getParameter("noticeId"))) {
        		notice.setNoticeId(Long.parseLong(request.getParameter("noticeId")+""));
        	}
        	List<Notice> list = noticeService.selectCacheNoticeList(notice);
        	return list;
    }
}
