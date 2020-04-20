package com.bit.hellopt.service.liveclass;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bit.hellopt.data.LiveMapper;
import com.bit.hellopt.vo.live.LiveClass;

@Service
public class LiveServiceImpl implements LiveService {
	
	@Autowired
	LiveMapper liveMapper;
	
	//강의 제목 조회
	@Override
	public String getClassName(int classIdx) {
		return liveMapper.getClassName(classIdx);
	}

	//더보기
	@Override
	public List<LiveClass> moreClassList(int end) {
		return liveMapper.moreClassList(end);
	}

	//랜덤 강의 조회
	@Override
	public LiveClass randomClass() {
		return liveMapper.randomClass();
	}
}
