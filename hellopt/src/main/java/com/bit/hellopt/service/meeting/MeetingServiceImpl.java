package com.bit.hellopt.service.meeting;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bit.hellopt.data.MeetingMapper;
import com.bit.hellopt.vo.meeting.CategoryCodeVO;
import com.bit.hellopt.vo.meeting.LocalVO;
import com.bit.hellopt.vo.meeting.MeetingFileVO;
import com.bit.hellopt.vo.meeting.MeetingVO;

@Service
public class MeetingServiceImpl implements MeetingService {
	
	//매퍼 주입
	@Autowired
	MeetingMapper meetingMapper;

	// 미팅TB 조회
	@Override
	public List<MeetingVO> getMeetingVO() {
		return meetingMapper.getMeetingVO();
	}
	// 미팅TB 검색
	@Override
	public List<MeetingVO> getSearch(String searchKeyword) {
		return meetingMapper.getSearch(searchKeyword);
	}
	
	// 미팅 상세 리스트 cnt로 가져오기
	@Override
	public List<MeetingVO> getMeetingCnt() {
		return meetingMapper.getMeetingCnt();
	}
	
	// 지역TB조회
	@Override
	public List<LocalVO> getLocalVO() {
		return meetingMapper.getLocalVO() ;
	}
	
	// 모임유형TB 조회
	@Override
	public List<CategoryCodeVO> getCategoryCodeVO() {
		return meetingMapper.getCategoryCodeVO();
	}
	
	// 미팅 상세 리스트 조회
	@Override
	public MeetingVO getMeetingOne(int meetingIdx) {
		return meetingMapper.getMeetingOne(meetingIdx);
	}
	// 미팅 상세리스트 파일 조회
	@Override
	public List<MeetingFileVO> getMeetingOneFiles(int meetingIdx) {
		return meetingMapper.getMeetingOneFiles(meetingIdx);
	}
	
	@Override
	public int progressCnt() {
		return meetingMapper.progressCnt();
	}
	
	@Override
	public MeetingVO resCount(int meetingIdx) {
		return meetingMapper.resCount(meetingIdx);
	}
	
	@Override
	public MeetingVO resUser(Map<String, Object> hm) {
		return meetingMapper.resUser(hm);
	}

	// 모임 개설 인서트
	@Override
	public void insertMeeting(MeetingVO meetingVO) {
		meetingMapper.insertMeeting(meetingVO);
	}
	// 모임 최대 인원수
	@Override
	public void insertMaxMeeting(MeetingVO meetingVO) {
		meetingMapper.insertMaxMeeting(meetingVO);
	}
	// 모임 승낙 테이블
	@Override
	public void insertConsentYn(MeetingVO meetingVO) {
		meetingMapper.insertConsentYn(meetingVO);
	}
	// 파일 업로드
	@Override
	public void insertMeetingFiles(int fkMeetingIdx, String mOriImg, String mSysImg, String filePath) {
		HashMap<String, Object> hm = new HashMap<>();
		hm.put("fkMeetingIdx", fkMeetingIdx);
		hm.put("mOriImg", mOriImg);
		hm.put("mSysImg", mSysImg);
		hm.put("filePath", filePath);
		
		meetingMapper.insertMeetingFiles(hm);
	}
	// 모임 예약
	@Override
	public void insertReservationMeeting(MeetingVO meetingVO) {
		meetingMapper.insertReservationMeeting(meetingVO);
	}

	
	// 모임 수정 
	@Override
	public void updateMeetingOk(MeetingVO meetingVO) {
		meetingMapper.updateMeeting(meetingVO);
	}
	// 모임 최대 인원수
	@Override
	public void updateMaxMeeting(MeetingVO meetingVO) {
		meetingMapper.updateMaxMeeting(meetingVO);
	}
	// 모임 글 조회수
	@Override
	public void clickCount(MeetingVO meetingVO) {
		meetingMapper.clickCount(meetingVO);
	}
	
	//모임 승낙y
	@Override
	public void updateProgressY(MeetingVO meetingVO) {
		meetingMapper.updateProgressY(meetingVO);
	}
	//모임 승낙n
	@Override
	public void updateProgressN(MeetingVO meetingVO) {
		meetingMapper.updateProgressN(meetingVO);
	}
	
	// 모임 삭제
	@Override
	public void deleteMeeting(int meetingIdx) {
		meetingMapper.deleteMeeting(meetingIdx);
		
	}
	
	// 모임 예약 취소 (유저)
	@Override
	public void resCancle(Map<String, Object> hm) {
		meetingMapper.resCancle(hm);
	}
	@Override
	public List<MeetingVO> getMeetingList(String userId) {
		return meetingMapper.getMeetingList(userId);
	}
	@Override
	public List<MeetingVO> getParticipantMeetingList(String userId) {
		return meetingMapper.getParticipantMeetingList(userId);
	}
	
	
	



}
