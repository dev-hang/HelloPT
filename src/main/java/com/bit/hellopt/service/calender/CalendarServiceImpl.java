package com.bit.hellopt.service.calender;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bit.hellopt.data.CalendarMapper;
import com.bit.hellopt.vo.calendar.CalendarVO;

@Service
public class CalendarServiceImpl implements CalendarService {

		
	@Autowired
	CalendarMapper calendarMapper;
	
	// 월뽑기
	@Override
	public List<CalendarVO> getMonthList(Map<String, String> hm) {
		return calendarMapper.getMonthList(hm);
	}
	
	@Override
	public List<CalendarVO> getCalendar() {
		return calendarMapper.getCalendar();
	}
	
	// 게시글 상세 조회
	@Override
	public CalendarVO getOneCalendar(int calendarIdx) {
		return calendarMapper.getOneCalendar(calendarIdx);
	}


	@Override
	public void insertCalVideo(HashMap<String, Object> hm) {
		calendarMapper.insertCalVideo(hm);
	}

	@Override
	public void updateCalVideo(HashMap<String, Object> hm) {
		calendarMapper.updateCalVideo(hm);
		
	}

	@Override
	public void deleteCalVideo(int calendarIdx) {
		calendarMapper.deleteCalVideo(calendarIdx);
		
	}


	
	


}
