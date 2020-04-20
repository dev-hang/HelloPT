package com.bit.hellopt.service.liveclass;

import java.util.List;

import com.bit.hellopt.vo.live.LiveClass;

public interface LiveService {
	//강의 제목 불러오기
	public String getClassName(int classIdx);
	
	//강의 리스트 페이징
	public List<LiveClass> moreClassList(int end);
	
	//추천 강의 랜덤 선택
	public LiveClass randomClass();
}
