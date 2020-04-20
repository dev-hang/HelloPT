package com.bit.hellopt.service.exercisetip;

import java.util.List;

import com.bit.hellopt.vo.exercisetip.ExerciseInformationTipVO;

public interface ExerciseInformationTipService {
	//CRUD 기능 구현 메소드 정의
	void insertExerciseInformationTip(ExerciseInformationTipVO vo);
	void updateExerciseInformationTip(ExerciseInformationTipVO vo);
	void deleteExerciseInformationTip(ExerciseInformationTipVO vo);
	ExerciseInformationTipVO getExerciseInformationTip(ExerciseInformationTipVO vo);
	List<ExerciseInformationTipVO> getExerciseInformationTipList();
	List<ExerciseInformationTipVO> getExerciseInformationTipList(ExerciseInformationTipVO vo);
}
