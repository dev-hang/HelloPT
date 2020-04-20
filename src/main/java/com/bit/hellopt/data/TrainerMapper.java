package com.bit.hellopt.data;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.bit.hellopt.vo.trainer.TrainerVO;

public interface TrainerMapper {
	
	@Insert("INSERT INTO INTRODUCE_TRAINER_TB (TRAINER_IDX, "
			+ "TRAINER_NAME, TRAINER_NICKNAME, TRAINER_CAREER, TRAINER_TALK, TRAINER_MEDIA, FK_USER_ID, TRAINER_PROFILE) "
			+ "VALUES (INTRODUCE_TRAINER_TB_SEQ.NEXTVAL, "
			+ "#{trainerName}, #{trainerNickname}, #{trainerCareer}, #{trainerTalk}, #{trainerMedia}, #{fkUserId}, #{trainerProfile})")
	public void insertTrainer(com.bit.hellopt.vo.trainer.TrainerVO trainerVO);

	@Select("SELECT * FROM INTRODUCE_TRAINER_TB")
	public List<TrainerVO> getTrainer();
	
	@Select("SELECT * FROM INTRODUCE_TRAINER_TB WHERE TRAINER_IDX = #{trainerIdx}")
	public TrainerVO getTrainerDetail(int trainerIdx);

	@Delete("DELETE FROM INTRODUCE_TRAINER_TB WHERE TRAINER_IDX = #{trainerIdx}")
	public void deleteTrainer(int trainerIdx);
	
	@Update("UPDATE INTRODUCE_TRAINER_TB SET " +
	        "TRAINER_NAME = #{trainerName}, TRAINER_NICKNAME = #{trainerNickname}, " + 
	        "TRAINER_CAREER = #{trainerCareer}, TRAINER_TALK = #{trainerTalk}, TRAINER_MEDIA = #{trainerMedia}" +
	        "WHERE TRAINER_IDX = #{trainerIdx}")
	public void updateTrainer(com.bit.hellopt.vo.trainer.TrainerVO trainerVO);
}







