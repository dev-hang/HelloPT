package com.bit.hellopt.service.meal;

import java.util.List;

import com.bit.hellopt.vo.meal.MealVO;

public interface MealService {
	//전체조회
	List<MealVO> getMealList(String search, String searchtxt, int startRow, int endRow);

	int totalCount(String search, String searchtxt);
}
