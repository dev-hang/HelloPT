package com.bit.hellopt.service.trainer;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bit.hellopt.data.TrainerMapper;
import com.bit.hellopt.service.user.UserService;
import com.bit.hellopt.vo.trainer.TrainerVO;
import com.bit.hellopt.vo.user.User;

@Transactional
@Service
public class TrainerServiceImpl implements TrainerService{
	
	public static final Logger logger = LoggerFactory.getLogger(TrainerServiceImpl.class);

	@Autowired
	TrainerMapper trainerMapper;
	@Autowired
	UserService userSerivce;
	
	@Override
	public void insertTrainer(TrainerVO trainerVO) {
		logger.info("insert Trainer", trainerVO);
		trainerMapper.insertTrainer(trainerVO);
		User user = new User();
		user.setUserId(trainerVO.getFkUserId());
		userSerivce.updateRole(user, "ROLE_TRAINER");
	}
	
	@Override
	public List<TrainerVO> getTrainerList() {
		return trainerMapper.getTrainer();
	}
	
	@Override
	public TrainerVO getTrainerDetail(int trainerIdx) {
		return trainerMapper.getTrainerDetail(trainerIdx);
	}
	
	@Override
	public void deleteTrainer(int trainerIdx) {
		TrainerVO trainerVO = trainerMapper.getTrainerDetail(trainerIdx);
		User user = new User();
		user.setUserId(trainerVO.getFkUserId());
		userSerivce.updateRole(user, "ROLE_USER");
		trainerMapper.deleteTrainer(trainerIdx);
	}
	
	@Override
	public void updateTrainer(TrainerVO trainerVO) {
		trainerMapper.updateTrainer(trainerVO);
	}
}




