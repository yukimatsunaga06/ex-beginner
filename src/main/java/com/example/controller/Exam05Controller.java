package com.example.controller;

	import java.util.List;

	import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.stereotype.Controller;
	import org.springframework.ui.Model;
	import org.springframework.web.bind.annotation.RequestMapping;

	import com.example.domain.Member;
	import com.example.repository.MemberRepository;

	@Controller
	@RequestMapping("/exam05")
	public class Exam05Controller {
		
		@Autowired
		private MemberRepository memberRepository;
		

		@RequestMapping("")
		public String index() {
			return "exam05";
		}
		

		@RequestMapping("/search")
		public String search(String name, Model model) {
			
			List<Member> memberList = memberRepository.findByLikeName(name);
			model.addAttribute("memberList", memberList);
			
			return "exam05-result";
		}
	}
