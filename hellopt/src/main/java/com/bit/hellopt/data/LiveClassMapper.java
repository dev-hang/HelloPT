package com.bit.hellopt.data;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.bit.hellopt.vo.live.LiveClass;

public interface LiveClassMapper {
	//강의 개설
	@Insert("INSERT INTO CLASS_TB (CLASS_IDX, "
			+ "FK_USER_ID, CLASS_TYPE, CLASS_NAME, TOTAL_MEMBERS, PRICE, CLASS_LENGTH, "
			+ "CLASS_START_DATE, CLASS_TIME, CLASS_DAY, LIVE_STATUS, TOTAL_VIEWER) "
			+ "VALUES (CLASS_TB_SEQ.NEXTVAL, "
			+ "#{fkUserId}, #{classType}, #{className}, #{totalMembers}, #{price}, #{classLength}, "
			+ "#{classStartDate}, #{classTime}, #{classDay}, '방송예정', 0)")
	public void insertClass(com.bit.hellopt.vo.live.LiveClass liveClass);
	
	//강의 유형별 강의 리스트 조회
	public List<LiveClass> getLiveClass(String classType);
	
	//강의 개설자별 강의 리스트 조회
	@Select("SELECT * FROM CLASS_TB WHERE FK_USER_ID = #{userId} ORDER BY CLASS_IDX")
	public List<LiveClass> getLiveClassesByUserId(String userId);
	
	//강의 신청자별 강의 리스트 조회
	@Select("select c.* from class_tb c, class_member_tb m where c.class_idx = m.fk_class_idx and m.fk_user_id = #{userId}")
	public List<LiveClass> getViewerClassesByUserId(String userId);
	
	//강의번호별 조회
	@Select("SELECT * FROM CLASS_TB WHERE CLASS_IDX = #{classIdx}")
	public LiveClass getClassDetail(int classIdx);
	
	//강의 삭제
	@Delete("DELETE FROM CLASS_TB WHERE CLASS_IDX = #{classIdx}")
	public void deleteClass(int classIdx);
	
	//강의 수정
	@Update("UPDATE CLASS_TB SET CLASS_TYPE = #{classType}, CLASS_NAME = #{className}, "
			+ " TOTAL_MEMBERS = #{totalMembers}, PRICE = #{price}, CLASS_LENGTH = #{classLength}, "
			+ " CLASS_START_DATE = #{classStartDate}, CLASS_TIME = #{classTime}, CLASS_DAY = #{classDay} "
			+ " WHERE CLASS_IDX = #{classIdx} ")
	public void updateClass(LiveClass liveClass);
}
