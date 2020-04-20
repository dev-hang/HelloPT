package com.bit.hellopt.service.exercise;

import java.util.List;
import java.util.Map;

import com.bit.hellopt.vo.exercise.ExerciseInformationFileVO;
import com.bit.hellopt.vo.exercise.ExerciseInformationVO;

public interface ExerciseInformationService {
	//CRUD 기능 구현 메소드 정의
	void insertExerciseInformation(ExerciseInformationVO vo);
	void updateExerciseInformation(ExerciseInformationVO vo);
	void deleteExerciseInformation(ExerciseInformationVO vo);
	ExerciseInformationVO getExerciseInformation(ExerciseInformationVO vo);
	List<ExerciseInformationVO> selectFile(ExerciseInformationFileVO evo);
	public List<ExerciseInformationVO> getExerciseInformationSearch(Map<String, Object> map);
	List<ExerciseInformationVO> getExerciseInformationList(ExerciseInformationVO vo);
	void uploadFile(String exerciseInformationFileOname, String saveFileName, long fileSize, int exerciseIdx);
	List<ExerciseInformationFileVO> getFileList(int exerciseIdx);
	List<ExerciseInformationFileVO> getExerciseFileList(ExerciseInformationVO vo);
	int getExerciseTotalCount(Map<String, Object> map);
	
}
