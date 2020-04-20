package com.bit.hellopt.service.trainer;

import java.util.List;

import com.bit.hellopt.vo.trainer.TrainerVO;

public interface TrainerService {
	//1. 입력
	void insertTrainer(TrainerVO trainerVO);
	
	//2. 수정
	//void updateTrainer(TrainerVO trainerVO);
	void updateTrainer(TrainerVO trainerVO);
	
	
	//3. 삭제
	void deleteTrainer(int trainerIdx);
	
	//4-1. 전체조회
	List<TrainerVO> getTrainerList();
	
	//4-2. 하나만 조회
	TrainerVO getTrainerDetail(int trainerIdx);


}
