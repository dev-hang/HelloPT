package com.bit.hellopt.service.liveclass;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bit.hellopt.data.LiveClassMapper;
import com.bit.hellopt.vo.live.LiveClass;

@Service
public class LiveClassServiceImpl implements LiveClassService {
	
	@Autowired
	LiveClassMapper liveClassMapper;
	
	//강의 개설
	@Override
	public void insertClass(LiveClass liveClass) {
		liveClassMapper.insertClass(liveClass);
	}

	//강의 유형별 강의 리스트 조회
	@Override
	public List<LiveClass> getLiveClass(String classType) {
		return liveClassMapper.getLiveClass(classType);
	}

	//강의 개설자별 강의 조회
	@Override
	public List<LiveClass> getClassesByUserId(String userId) {
		return liveClassMapper.getLiveClassesByUserId(userId);
	}
	
	//강의 신청자별  조회
	@Override
	public List<LiveClass> getViewerClassesByUserId(String userId) {
		return liveClassMapper.getViewerClassesByUserId(userId);
	}
	
	//강의 상세정보 조회
	@Override
	public LiveClass getClassDetail(int classIdx) {
		return liveClassMapper.getClassDetail(classIdx);
	}
	
	//강의 삭제
	@Override
	public void deleteClass(int classIdx) {
		liveClassMapper.deleteClass(classIdx);
	}

	//강의 수정
	@Override
	public void updateClass(LiveClass liveClass) {
		liveClassMapper.updateClass(liveClass);
	}

}
