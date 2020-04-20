package com.bit.hellopt.data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bit.hellopt.vo.calendar.CalendarVO;

public interface CalendarMapper {
	
	public List<CalendarVO> getMonthList(Map<String, String> hm);
	
	public List<CalendarVO> getCalendar();
	
	public CalendarVO getOneCalendar(int calendarIdx);

	public void insertCalVideo(HashMap<String, Object> hm);
	
	public void updateCalVideo(HashMap<String, Object> hm);
	
	public void deleteCalVideo(int calendarIdx);
	
	/*
	public List<MeetingVO> getMeetingVO();
	
	public List<LocalVO> getLocalVO();
	
	public List<CategoryCodeVO> getCategoryCodeVO();

	public MeetingVO getMeetingOne(int meetingIdx);
	public List<MeetingFileVO> getMeetingOneFiles(int meetingIdx);
	
	public void insertMeeting(com.bit.hellopt.vo.meeting.MeetingVO meetingVO);
	public void insertMaxMeeting(com.bit.hellopt.vo.meeting.MeetingVO meetingVO);
	public void insertConsentYn(com.bit.hellopt.vo.meeting.MeetingVO meetingVO);
	public void insertMeetingFiles(HashMap<String, Object> HashMap);
	
	public void updateMeeting(com.bit.hellopt.vo.meeting.MeetingVO meetingVO);
	public void updateMaxMeeting(com.bit.hellopt.vo.meeting.MeetingVO meetingVO);
	
	
	public void deleteMeeting(int meetingIdx);
	*/
}
