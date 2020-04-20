package com.bit.hellopt.data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bit.hellopt.vo.meeting.CategoryCodeVO;
import com.bit.hellopt.vo.meeting.LocalVO;
import com.bit.hellopt.vo.meeting.MeetingFileVO;
import com.bit.hellopt.vo.meeting.MeetingVO;

public interface MeetingMapper {
	public List<MeetingVO> getMeetingVO();
	public List<MeetingVO> getSearch(String searchKeyword);
	public List<MeetingVO> getMeetingCnt();
	
	public List<LocalVO> getLocalVO();
	
	public List<CategoryCodeVO> getCategoryCodeVO();

	public MeetingVO getMeetingOne(int meetingIdx);
	public int progressCnt();
	public MeetingVO resCount(int meetingIdx);
	public MeetingVO resUser(Map<String, Object> hm);
	public List<MeetingFileVO> getMeetingOneFiles(int meetingIdx);
	
	public void insertMeeting(com.bit.hellopt.vo.meeting.MeetingVO meetingVO);
	public void insertMaxMeeting(com.bit.hellopt.vo.meeting.MeetingVO meetingVO);
	public void insertConsentYn(com.bit.hellopt.vo.meeting.MeetingVO meetingVO);
	public void insertMeetingFiles(HashMap<String, Object> HashMap);
	public void insertReservationMeeting(com.bit.hellopt.vo.meeting.MeetingVO meetingVO);
	
	public void updateMeeting(com.bit.hellopt.vo.meeting.MeetingVO meetingVO);
	public void updateMaxMeeting(com.bit.hellopt.vo.meeting.MeetingVO meetingVO);
	public void clickCount(com.bit.hellopt.vo.meeting.MeetingVO meetingVO);
	
	public void updateProgressY(com.bit.hellopt.vo.meeting.MeetingVO meetingVO);
	public void updateProgressN(com.bit.hellopt.vo.meeting.MeetingVO meetingVO);
	
	public void deleteMeeting(int meetingIdx);
	public void resCancle(Map<String, Object> hm);
	
	public List<MeetingVO> getMeetingList(String userId);
	public List<MeetingVO> getParticipantMeetingList(String userId);
	
}

/*
	@Select("SELECT MEETING_IDX, FK_USER_ID, M_COMMENT, C.M_CATEGORY, L.LOCAL, M_DATE, M_SUBJECT, M_MEMO, DETAILS, INCLUDE, M_LOCATION, M_LOCATION_C, M_PRICE, PROGRESS_ST, M_CNT, INS_DT " + 
			"FROM MEETING_TB M, M_CATEGORY_CODE_TB C, LOCAL_CODE_TB L " + 
			"WHERE M.FK_M_CATEGORY_NO = C.M_CATEGORY_NO " + 
			"  AND M.FK_LOCAL_NO = L.LOCAL_NO ")
	@Select("SELECT * FROM LOCAL_CODE_TB")
	@Select("SELECT * FROM M_CATEGORY_CODE_TB")
	@Select("SELECT MEETING_IDX, FK_USER_ID, M_COMMENT, C.M_CATEGORY, L.LOCAL, M_DATE, M_SUBJECT, M_MEMO, DETAILS, INCLUDE, M_LOCATION, M_LOCATION_C, M_PRICE, PROGRESS_ST, M_CNT, INS_DT, U.USER_NAME " + 
			"FROM MEETING_TB M, M_CATEGORY_CODE_TB C, LOCAL_CODE_TB L, USERS_TB U " + 
			"WHERE M.FK_M_CATEGORY_NO = C.M_CATEGORY_NO " + 
			"  AND M.FK_LOCAL_NO = L.LOCAL_NO " +
			"  AND M.FK_USER_ID = U.USER_ID " +
			"  AND MEETING_IDX = #{meetingIdx}")
	@Insert("INSERT INTO MEETING_TB " + 
			"  (MEETING_IDX, FK_USER_ID, M_COMMENT, FK_M_CATEGORY_NO, FK_LOCAL_NO, M_DATE, M_SUBJECT, M_MEMO, DETAILS, INCLUDE, M_LOCATION, M_LOCATION_C, M_PRICE, PROGRESS_ST, M_CNT, INS_DT) " + 
			"VALUES " + 
			"  (MEETING_TB_SEQ.NEXTVAL, #{fkUserId}, #{mComment}, #{fkMCategoryNo}, #{fkLocalNo}, " + 
			"  #{mDate}, #{mSubject}, #{mMemo}, #{details}, #{include} , #{mLocation}, #{mLocationC}, #{mPrice}, '진행중', 0, SYSDATE ) " ) 
	@Insert("INSERT INTO MEETING_APPLY_TB " + 
			"  (FK_MEETING_IDX, FK_USER_ID, MAX_COUNT) " + 
			"VALUES " + 
			"  ( (SELECT MAX(MEETING_IDX) FROM MEETING_TB WHERE FK_USER_ID = #{fkUserId} ) , #{fkUserId}, #{maxCount} ) " ) 
	@Update("UPDATE MEETING_TB SET " + 
			"  M_COMMENT = #{mComment} , FK_M_CATEGORY_NO = #{fkMCategoryNo}, FK_LOCAL_NO = #{fkLocalNo} , " +
			"  M_DATE= #{mDate} , M_SUBJECT = #{mSubject}, M_MEMO = #{mMemo}, DETAILS = #{details}, INCLUDE = #{include}, " +
			"  M_LOCATION = #{mLocation}, M_LOCATION_C = #{mLocationC}, M_PRICE = #{mPrice}, INS_DT = SYSDATE " + 
			"  WHERE  MEETING_IDX = #{meetingIdx} " )
	@Delete("DELETE FROM MEETING_TB WHERE MEETING_IDX = #{meetingIdx} ")
	
 */