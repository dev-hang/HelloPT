package com.bit.hellopt.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuditionController {
	
	@GetMapping("/audition")
	public String audition() {
		return "audition/audition";
	}
	
	
}
