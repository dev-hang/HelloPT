package com.bit.hellopt.vo.exercise;

import java.util.List;

public class ExerciseInformationListVO {

	private List<ExerciseInformationVO> exerciseInformationList;
	
	private List<ExerciseInformationVO> exerciseInformationSearch;
	
	public List<ExerciseInformationVO> getExerciseInformationList() {
		return exerciseInformationList;
	}
	
	public void setExerciseInformationList(List<ExerciseInformationVO> exerciseInformationList) {
		this.exerciseInformationList = exerciseInformationList;
	}

	public List<ExerciseInformationVO> getExerciseInformationSearch() {
		return exerciseInformationSearch;
	}

	public void setExerciseInformationSearch(List<ExerciseInformationVO> exerciseInformationSearch) {
		this.exerciseInformationSearch = exerciseInformationSearch;
	}
	
}
