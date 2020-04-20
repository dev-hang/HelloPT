package com.bit.hellopt.data;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.bit.hellopt.vo.meal.MealVO;

@Mapper
public interface MealMapper {
	
	public List<MealVO> getMeal(HashMap<String, Object> hm);

	public int getCount(HashMap<String, Object> hm);

}
