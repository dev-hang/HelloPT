package com.bit.hellopt.service.exercisetip;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bit.hellopt.data.exercisetip.ExerciseTipMapper;
import com.bit.hellopt.vo.exercisetip.ExerciseInformationTipVO;


//@Service : @Component 상속받아 만든
//비즈니스 로직 처리 서비스 영역에 사용
@Service("exerciseInformationTipService")
public class ExerciseInformationTipServiceImpl implements ExerciseInformationTipService {
	
	@Autowired
	ExerciseTipMapper mapper;
	
	public ExerciseInformationTipServiceImpl() {
		System.out.println(">> ExerciseInformationTipServiceImpl() 실행");
	}
	@Override
	public void insertExerciseInformationTip(ExerciseInformationTipVO vo) {
		mapper.insertExerciseInformationTip(vo);
	}

	@Override
	public void updateExerciseInformationTip(ExerciseInformationTipVO vo) {
		mapper.updateExerciseInformationTip(vo);
	}

	@Override
	public void deleteExerciseInformationTip(ExerciseInformationTipVO vo) {
		mapper.deleteExerciseInformationTip(vo);
	}

	@Override
	public ExerciseInformationTipVO getExerciseInformationTip(ExerciseInformationTipVO vo) {
		return mapper.getExerciseInformationTip(vo);
	}

	@Override
	public List<ExerciseInformationTipVO> getExerciseInformationTipList() {
		return mapper.getExerciseInformationTipList();
	}

	@Override
	public List<ExerciseInformationTipVO> getExerciseInformationTipList(ExerciseInformationTipVO vo) {
		return mapper.getExerciseInformationTipList(vo);
	}
	
}
