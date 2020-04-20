package com.bit.hellopt.data.exercisetip;

import java.util.List;

import com.bit.hellopt.vo.exercisetip.ExerciseInformationTipVO;

public interface ExerciseTipMapper {//이놈아가 DAO다.
	public List<ExerciseInformationTipVO> getExerciseInformationTipList(ExerciseInformationTipVO vo);
	public List<ExerciseInformationTipVO> getExerciseInformationTipList();
	public ExerciseInformationTipVO getExerciseInformationTip(ExerciseInformationTipVO vo);
	public void insertExerciseInformationTip(ExerciseInformationTipVO vo);
	public void updateExerciseInformationTip(ExerciseInformationTipVO vo);
	public void deleteExerciseInformationTip(ExerciseInformationTipVO vo);
	
	//ExerciseTipMapper 의 클래스 구현된것 찾아보고 없으면 추가
	
	
	
	
	
}
