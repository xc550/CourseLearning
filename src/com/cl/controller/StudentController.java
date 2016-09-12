package com.cl.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cl.entity.Student;
import com.cl.service.StudentService;

@Controller
@RequestMapping("/student")
public class StudentController {
	
	@Resource
	private StudentService stuService;
	
	@RequestMapping("/login")
	public String login(HttpServletRequest req, Student student) throws Exception {
		if (stuService.check(student.getLoginname(), student.getPassword()))
			return "student/welcome";
		return "error";
	}
	
	@RequestMapping("/logout")
	public String logout(HttpServletRequest req) throws Exception {
		req.getSession().invalidate();
		return "main/loginform";
	}
	
	@RequestMapping("/geteventinfo")
	public String getEventInfo(HttpServletRequest req) throws Exception {
		
		return "";
	}
	
}
