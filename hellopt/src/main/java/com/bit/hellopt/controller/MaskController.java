package com.bit.hellopt.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MaskController {

	@GetMapping("/mask")
	public String mask() {
		return "mask/mask";
	}
}
