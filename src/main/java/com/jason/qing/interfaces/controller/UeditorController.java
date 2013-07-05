package com.jason.qing.interfaces.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.jason.framework.web.support.ControllerSupport;
import com.jason.qing.infrastruture.util.Uploader;

@Controller
@RequestMapping(value = "/ueditor")
public class UeditorController extends ControllerSupport {
	
	
	@RequestMapping(value = "/imageUp", method = RequestMethod.POST)
	public void imageUp( HttpServletRequest request,HttpServletResponse response, Model model) {
		 try {
			request.setCharacterEncoding("utf-8");
			response.setCharacterEncoding("utf-8");
		    Uploader up = new Uploader(request);
		    up.setSavePath("upload");
		    String[] fileType = {".gif" , ".png" , ".jpg" , ".jpeg" , ".bmp"};
		    up.setAllowFiles(fileType);
		    up.setMaxSize(10000); //单位KB
		    up.upload();
		    response.getWriter().print("{'original':'"+up.getOriginalName()+"','url':'"+up.getUrl()+"','title':'"+up.getTitle()+"','state':'"+up.getState()+"'}");
		    this.getLogger().info("{'original':'"+up.getOriginalName()+"','url':'"+up.getUrl()+"','title':'"+up.getTitle()+"','state':'"+up.getState()+"'}");
		} catch (Exception e) {
			this.getLogger().error("imageUp error ",e);
		}
			
	}
}
