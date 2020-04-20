package com.bit.hellopt.service.meeting;

import java.util.List;
import java.util.Map;

import com.bit.hellopt.vo.meeting.CategoryCodeVO;
import com.bit.hellopt.vo.meeting.LocalVO;
import com.bit.hellopt.vo.meeting.MeetingFileVO;
import com.bit.hellopt.vo.meeting.MeetingVO;

public interface MeetingService {
	
	// 미팅 테이블 전체 조회
	List<MeetingVO> getMeetingVO();
	// 미팅 테이블 서치
	List<MeetingVO> getSearch(String searchKeyword);
	// 게시글 상세 조회 내에서 조회수 많은 게시글 목록
	List<MeetingVO> getMeetingCnt();
	
	// 지역 카테고리 조회
	List<LocalVO> getLocalVO();
	
	// 모임 카테고리 조회
	List<CategoryCodeVO> getCategoryCodeVO();
		
	// 게시글 상세 조회
	MeetingVO getMeetingOne(int meetingIdx);
	int progressCnt();
	MeetingVO resCount(int meetingIdx);
	MeetingVO resUser(Map<String, Object> hm);
	List<MeetingFileVO> getMeetingOneFiles(int meetingIdx);
	
	// 게시글 입력
	void insertMeeting(MeetingVO meetingVO);
	// 게시글 입력 > 최대인원수
	void insertMaxMeeting(MeetingVO meetingVO);
	// 게시글 입력 > 승인 테이블
	void insertConsentYn(MeetingVO meetingVO);
	// 게시글 입력 > 업롣 파일
	void insertMeetingFiles(int fkMeetingIdx, String mOriImg, String mSysImg, String filePath);
	// 모임 예약 인서트
	void insertReservationMeeting(MeetingVO meetingVO);
	
	// 게시글 수정
	void updateMeetingOk(MeetingVO meetingVO);
	// 게시글 수정 > 최대인원수
	void updateMaxMeeting(MeetingVO meetingVO);
	// 게시글 카운트 수 ++
	void clickCount(MeetingVO meetingVO);
	
	// 게시글 승낙y
	void updateProgressY(MeetingVO meetingVO);
	// 게시글 승낙n
	void updateProgressN(MeetingVO meetingVO);
	
	// 게시글 삭제
	void deleteMeeting(int meetingIdx);
	// 모임 에약 취소(일반유저)
	void resCancle(Map<String, Object> hm);
	
	public List<MeetingVO> getMeetingList(String userId);
	public List<MeetingVO> getParticipantMeetingList(String userId);
	
	

}
