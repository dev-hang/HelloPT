package com.bit.hellopt.data;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.bit.hellopt.vo.live.LiveClass;

public interface LiveMapper {
	//강의 제목 조회
	@Select("SELECT CLASS_NAME FROM CLASS_TB WHERE CLASS_IDX = #{classIdx}")
	public String getClassName(int classIdx);
	
	//더보기 기능 강의 조회
	public List<LiveClass> moreClassList(@Param("end") int end);
	
	//랜덤 강의 조회
	public LiveClass randomClass();
}
