package com.bit.hellopt.data.exercise;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bit.hellopt.vo.exercise.ExerciseInformationFileVO;
import com.bit.hellopt.vo.exercise.ExerciseInformationVO;

public interface ExerciseMapper {//이놈아가 DAO다.
	public List<ExerciseInformationVO> getExerciseInformationList(ExerciseInformationVO vo);
	public List<ExerciseInformationVO> getExerciseInformationSearch(Map<String, Object> map);
	public ExerciseInformationVO getExerciseInformation(ExerciseInformationVO vo);
	public void insertExerciseInformation(ExerciseInformationVO vo);
	public void updateExerciseInformation(ExerciseInformationVO vo);
	public void deleteExerciseInformation(ExerciseInformationVO vo);
	int getExerciseTotalCount(Map<String, Object> map);
	public void uploadFile(HashMap<String, Object> hm);
	public List<ExerciseInformationFileVO> getExerciseFileList(ExerciseInformationVO vo);
	public int getExerciseCountlist(Map<String, Object> map);
	
}
