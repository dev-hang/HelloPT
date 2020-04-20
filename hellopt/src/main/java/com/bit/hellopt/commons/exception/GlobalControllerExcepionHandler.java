package com.bit.hellopt.commons.exception;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

//@ControllerAdvice
public class GlobalControllerExcepionHandler {

	@ExceptionHandler(Exception.class)
	public String handleException(Exception err, Model model) {
		model.addAttribute("err", err);
		return "exception";
	}
	
}
