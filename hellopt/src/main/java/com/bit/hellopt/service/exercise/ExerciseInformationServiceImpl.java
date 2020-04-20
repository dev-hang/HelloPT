package com.bit.hellopt.service.exercise;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bit.hellopt.data.exercise.ExerciseMapper;
import com.bit.hellopt.vo.exercise.ExerciseInformationFileVO;
import com.bit.hellopt.vo.exercise.ExerciseInformationPaging;
import com.bit.hellopt.vo.exercise.ExerciseInformationVO;


//@Service : @Component 상속받아 만든
//비즈니스 로직 처리 서비스 영역에 사용
@Service("exerciseInformationService")
public class ExerciseInformationServiceImpl implements ExerciseInformationService {
	
	@Autowired
	ExerciseMapper mapper;
	
	public ExerciseInformationServiceImpl() {
		System.out.println(">> ExerciseInformationServiceImpl() 실행");
	}
	@Override
	public void insertExerciseInformation(ExerciseInformationVO vo) {
		System.out.println("----------------------------------------------");
		System.out.println(vo.getExercisePictures());
		mapper.insertExerciseInformation(vo);
	}

	@Override
	public void updateExerciseInformation(ExerciseInformationVO vo) {
		mapper.updateExerciseInformation(vo);
	}

	@Override
	public void deleteExerciseInformation(ExerciseInformationVO vo) {
		mapper.deleteExerciseInformation(vo);
	}

	@Override
	public List<ExerciseInformationVO> getExerciseInformationSearch(Map<String, Object> map) {
		System.out.println("서비스 임플까진 먹습니다.["+map+"]");
		return mapper.getExerciseInformationSearch(map);
	}

	@Override
	public List<ExerciseInformationVO> getExerciseInformationList(ExerciseInformationVO vo) {
		return mapper.getExerciseInformationList(vo);
	}
	@Override
	public List<ExerciseInformationVO> selectFile(ExerciseInformationFileVO evo) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void uploadFile(String exerciseInformationFileOname, String saveFileName, long fileSize, int exerciseIdx) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public List<ExerciseInformationFileVO> getFileList(int exerciseIdx) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<ExerciseInformationFileVO> getExerciseFileList(ExerciseInformationVO vo) {
		
		return mapper.getExerciseFileList(vo);
	}
	@Override
	public int getExerciseTotalCount(Map<String, Object> map) {
		return mapper.getExerciseTotalCount(map);
	}
	@Override
	public ExerciseInformationVO getExerciseInformation(ExerciseInformationVO vo) {
		return mapper.getExerciseInformation(vo);
	}
	
}
