package com.example.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/ex1")
public class Exam1Controller {
	@Autowired
	private HttpSession session;
	@RequestMapping("")
	public String index() {
		return "exam01";
	}
	@RequestMapping("/exam-01")
	public String ex1(String name) {
		session.setAttribute("name", name);
		return "exam-01";
		
	}
}
