package com.bit.hellopt.controller;

import java.security.Principal;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.bit.hellopt.service.meeting.MeetingService;

@Controller
public class MainController {
	
	private static final Logger logger = LoggerFactory.getLogger(MainController.class);
	
	public static int progressCnt;
	
	@Autowired
	MeetingService service;
	
	//static을 선언하면 컴파일 할 때 맨 위로 올라가서 생성 됨 
	public static MeetingService mService;
	
	// 이 객체가 생성 될 때 여기에 service를 넣을거임 ( 서버 실행 > initialize 실행(생성자들보다 먼저 실행됨) > mService 대기 > service 실행 > mService 실행 )
	@PostConstruct
	private void initialize() {
		this.mService = service;
	}

	@GetMapping("/")
	public String home(Principal principal, Model model) {
	// 로그인 성공했을 때 인증된 유저 객체를 받을 수 있음(유저 아이디/비번)
	//public String home() {
		
		logger.info("main controller get mapping");
		return "main";
	}
	@GetMapping("/main")
	public String main(Principal principal, Model model) {
	// 로그인 성공했을 때 인증된 유저 객체를 받을 수 있음(유저 아이디/비번)
	//public String home() {
		progressCnt = service.progressCnt();
		
		//model.addAttribute("progressCnt", progressCnt);
		
		logger.info("main controller get mapping");
		return "main";
	}
	
}