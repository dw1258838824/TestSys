package com.ruoyi.project.system.api.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@RestController
@RequestMapping("/api/file")
public class FileApi {

	

	@Value("${ruoyi.scratchProfile}")
	private String scratchFilePath;

	@PostMapping("/upload")
	public void login(HttpServletRequest request ,@RequestParam MultipartFile f,@RequestParam String fileName) throws IOException {
		if(null!=f) {
			try {
				String name = f.getOriginalFilename();//直接返回文件的名字;
				File file=new File(scratchFilePath);
				if(!file.exists()){//目录不存在就创建
					file.mkdirs();
				}
				String subffix = name.substring(name.lastIndexOf(".") + 1, name.length());//我这里取得文件后缀
				File newFile = new File(file+"\\"+fileName+"."+subffix);
				f.transferTo(newFile);
			} catch (Exception e) {
				e.printStackTrace();
			}
		
		}
	}

    
}
