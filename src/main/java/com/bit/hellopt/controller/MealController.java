package com.bit.hellopt.controller;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bit.hellopt.service.meal.MealService;
import com.bit.hellopt.vo.meal.MakePage;
import com.bit.hellopt.vo.meal.MealVO;

@Controller
public class MealController {
	
	@Autowired
	MealService service;
	
	@RequestMapping("/meal")
	public String getMeal(
			@RequestParam(required=false, defaultValue="1") int currpage
			, @RequestParam(required=false, defaultValue="") String search
			, @RequestParam(required=false, defaultValue="") String searchtxt
			, @RequestParam(required=false, defaultValue="0") int tempmode
			, Model model) {
		
		System.out.println(searchtxt);
		
		Pattern da=Pattern.compile("(^[가-힣]*$)");
	      if(search=="MEAL_NAME"||"MEAL_NAME".equals(search)) {
	         Matcher m=da.matcher(searchtxt);
	         if(!m.find()) {
	            System.out.println(true);
	            searchtxt="";
	            model.addAttribute("searchtxt", "");
	         }else {
	            System.out.println(false);
	            model.addAttribute("searchtxt", searchtxt);
	         }
	      }
	      Pattern d=Pattern.compile("(^[0-9]*$)");
	      if(search=="MEAL_KCAL"||"MEAL_KCAL".equals(search)) {
	         Matcher m=d.matcher(searchtxt);
	         if(!m.find()) {
	            searchtxt="";
	            model.addAttribute("searchtxt", "");
	         }else {
	            model.addAttribute("searchtxt", searchtxt);
	         }
	      }
		
		int totalCount=service.totalCount(search, searchtxt);
		int pageSize=10;
		int blockSize=5;
		
		MakePage page=new MakePage(currpage, totalCount, pageSize, blockSize);
		
		List<MealVO> mealList = service.getMealList(search, searchtxt, page.getStartRow(), page.getEndRow());
		System.out.println("음식리스트 가져오기 성공!");
		model.addAttribute("mealList", mealList);
		model.addAttribute("page", page);
		model.addAttribute("search", search);
		model.addAttribute("searchtxt", searchtxt);
		model.addAttribute("tempmode", tempmode);
		return "meal/meal";
	}
}
