package com.bit.hellopt.service.meal;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bit.hellopt.data.MealMapper;
import com.bit.hellopt.vo.meal.MealVO;

@Service
public class MealServiceImpl implements MealService{
	
	@Autowired
	MealMapper mealMapper;
	
/*	@Override
	public List<MealVO> getMealList() {
		return mealMapper.getMeal();
	}*/

	@Override
	public List<MealVO> getMealList(String search, String searchtxt, int startRow, int endRow) {
		
		HashMap<String, Object> hm=new HashMap<>();
		hm.put("search", search);
		hm.put("searchtxt", searchtxt);
		hm.put("startRow", startRow);
		hm.put("endRow", endRow);
		
		return mealMapper.getMeal(hm);
		
	}

	@Override
	public int totalCount(String search, String searchtxt) {
		HashMap<String, Object> hm=new HashMap<>();	//search와 searchtxt를 넘겨줘야하는데 hashmap으로 묶어서넘겨주기위함
		hm.put("search", search);
		hm.put("searchtxt", searchtxt);
		
		return mealMapper.getCount(hm);
	}
}
