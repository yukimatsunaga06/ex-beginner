package com.example.controller;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/exam3")
public class Exam03Controller {
	@Autowired
	private ServletContext application;
	
	@RequestMapping("")
	public String index() {
		return "exam03";
	}
	@RequestMapping("/exam03")
	public String exam03(Integer item1,Integer item2,Integer item3) {
		application.setAttribute("item1",item1);
		application.setAttribute("item2",item2);
		application.setAttribute("item3",item3);
		application.setAttribute("answer", item1 + item2 + item3);
		application.setAttribute("tax",(item1 + item2 + item3)*1.1);
		return "exam03-result";
	}
}
