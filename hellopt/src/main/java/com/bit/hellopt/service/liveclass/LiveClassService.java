package com.bit.hellopt.service.liveclass;

import java.util.List;

import com.bit.hellopt.vo.live.LiveClass;

public interface LiveClassService {
	//1. 입력
	void insertClass(LiveClass liveClass);
	
	//2. 수정
	void updateClass(LiveClass liveClass);
	
	//3. 삭제
	void deleteClass(int classIdx);
	
	//4. 조회
	//4-1. 전체 조회
	List<LiveClass> getLiveClass(String classType);
	
	//4-2. 하나만 조회
	LiveClass getClassDetail(int classIdx);
	
	//4-3. user id로 가입한 class들 조회
	/**
	 * user id로 가입한 class들 조회
	 * @author 홍성관
	 * @param userId
	 * @return 
	 */
	List<LiveClass> getClassesByUserId(String userId);
	// 4-4 스트리밍 강의 수강생 id로 class 조회
	/**
	 * @author 홍성관
	 * @param userId
	 * @return
	 */
	List<LiveClass> getViewerClassesByUserId(String userId);
}	
