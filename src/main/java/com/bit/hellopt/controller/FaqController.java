package com.bit.hellopt.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FaqController {
	
	@GetMapping("/faq1")
	public String faq1() {
		return "faq/faq1";
	}
	
	@GetMapping("/faq2")
	public String faq2() {
		return "faq/faq2";
	}
	
}
