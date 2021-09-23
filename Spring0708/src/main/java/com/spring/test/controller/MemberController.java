package com.spring.test.controller;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.spring.test.service.MemberService;

@Controller
@RequestMapping(value = "/member")
public class MemberController {
	
	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	
	@Autowired
	private MemberService service;
	
	
	@RequestMapping(value = "/join", method = RequestMethod.GET)
	public String join() {
		logger.info("join 메소드 실행(GET)");
		return "member/join";
	}
	
	@RequestMapping(value = "/join", method = RequestMethod.POST)
	public String join(String memberId,String memberPw) {
		logger.info("join 메소드 실행(POST)");
		
		logger.info("memberId: {}",memberId);
		logger.info("memberPw: {}",memberPw);		
		
		String returnUrl;
		boolean result = service.join(memberId,memberPw);
		if(result) {
			logger.info("회원가입 성공");
			//return"redirect:/";
			returnUrl = "redirect:/";
		} else { 
			logger.info("회원가입 실패");
			//return "member/join";
			returnUrl = "member/join";
		}
		return returnUrl;
	}
	
	@RequestMapping(value="/idCheck", method = RequestMethod.GET)
	public String idCheck() {
		logger.info("idCheck 메소드 실행(GET)");
		return "member/idCheck";
	}
	
	@RequestMapping(value="/idCheck", method = RequestMethod.POST)
	public String idCheck(String idCheck, Model model) {
		logger.info("idCheck 메소드 실행(POST)");
		
		logger.info("idCheck: {}",idCheck);
		
		/* boolean result = service.login(idCheck); */
		String result = service.selectMember(idCheck, null);
		if(result == null) 
			model.addAttribute("result",true);
		else 
			model.addAttribute("result",false);
		model.addAttribute("idCheck",idCheck);			
		return "member/idCheck";
	}
	
	@RequestMapping(value="/login", method = RequestMethod.GET)
	public String login() {
		logger.info("login 메소드 실행(GET)");
		return"member/login";
	}
	
	@RequestMapping(value="/login", method = RequestMethod.POST)
	public String login(HttpSession session, String memberId,String memberPw) {
		logger.info("login 메소드 실행(POST)");
		
		logger.info("memberId : {} ",memberId);
		logger.info("memberPw : {} ",memberPw);
		
		String loginId = service.selectMember(memberId,memberPw);
		logger.info("loginId: {}",loginId);
		String returnUrl = null;
		if(loginId == null) {
			logger.info("로그인 실패");
			returnUrl = "member/login";
		}else {
			logger.info("로그인 성공");
			session.setAttribute("memberId", memberId);
			returnUrl = "redirect:/";
		}		
		return returnUrl;
	}
	
	@RequestMapping(value="/logout", method = RequestMethod.GET)
	public String logout(HttpSession session) {
		logger.info("logout 메소드 실행(GET)");
		
		session.removeAttribute("memberId");
		//session.invalidate();
		return "redirect:/";
	}
	
}
