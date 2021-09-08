package com.sist.model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sist.controller.Controller;
import com.sist.controller.RequestMapping;

@Controller
public class StayModel {
	@RequestMapping("stay/stay.do")
	public String main_main(HttpServletRequest request,HttpServletResponse response)
	{
		
		request.setAttribute("main_jsp","../stay/stay_main.jsp");
		return "../main/main.jsp";
	}
}