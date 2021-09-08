package com.sist.model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sist.controller.Controller;
import com.sist.controller.RequestMapping;

@Controller
public class PlayModel {
	@RequestMapping("play/play.do")
	public String main_main(HttpServletRequest request,HttpServletResponse response)
	{
		
		request.setAttribute("main_jsp","../play/play_main.jsp");
		return "../main/main.jsp";
	}
}
