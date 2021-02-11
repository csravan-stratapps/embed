package com.xamplify.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.xamplify.service.VideoService;

@Controller
public class HomeController {
	
	@Autowired
    private VideoService videoService;
	
	private static final String VIDEO = "video";
    
    @RequestMapping(value = { "/" }, method = { RequestMethod.GET })
    public String home() {
        return "share";
    }
    
    @RequestMapping(value = { "/{shortenUrlAlias}/{titleAlias}" }, method = { RequestMethod.GET })
    public String getVideo(@PathVariable final String shortenUrlAlias, @PathVariable final String titleAlias, final Model model) throws Exception {
        model.addAttribute(VIDEO, (Object)this.videoService.getVideo(shortenUrlAlias, titleAlias));
        return VIDEO;
    }
    
    @RequestMapping(value = { "/{shortenUrlAlias}" }, method = { RequestMethod.GET })
    public String getVideoWithOutTitleAlias(@PathVariable final String shortenUrlAlias, final Model model,HttpServletRequest request) throws Exception {
    	String url = String.valueOf(request.getRequestURL());
    	String clientUrl = "";
    	if(url.contains("localhost")) {
    		clientUrl = "http://localhost:4200";
    	}else if(url.contains("aravindu.com")) {
    		clientUrl = "https://xamplify.co";
    	}else if(url.contains("xamp.io")) {
    		clientUrl = "https://xamplify.io";
    	}
        model.addAttribute(VIDEO, (Object)this.videoService.getVideo(shortenUrlAlias));
        model.addAttribute("clientUrl",clientUrl);
        return VIDEO;
    }

}
