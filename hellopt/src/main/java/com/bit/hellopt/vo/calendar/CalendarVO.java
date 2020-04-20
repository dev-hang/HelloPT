package com.bit.hellopt.vo.calendar;

public class CalendarVO {
	

    // 캘린더 번호 
    private int calendarIdx;
    // 유저아이디 
    private String fkUserId;
    // 년월일 
    private String yymmdd;
    // 글내용 
    private String content;
    // 파일경로 
    private String filePath;
    // 원본동영상명 
    private String cOriVideo;

	// 변경동영상명 
    private String cSysVideo;
    
    
    public String getYymmdd() {
    	return yymmdd;
    }
    public void setYymmdd(String yymmdd) {
    	this.yymmdd = yymmdd;
    }
	public int getCalendarIdx() {
		return calendarIdx;
	}
	public void setCalendarIdx(int calendarIdx) {
		this.calendarIdx = calendarIdx;
	}
	public String getFkUserId() {
		return fkUserId;
	}
	public void setFkUserId(String fkUserId) {
		this.fkUserId = fkUserId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public String getcOriVideo() {
		return cOriVideo;
	}
	public void setcOriVideo(String cOriVideo) {
		this.cOriVideo = cOriVideo;
	}
	public String getcSysVideo() {
		return cSysVideo;
	}
	public void setcSysVideo(String cSysVideo) {
		this.cSysVideo = cSysVideo;
	}
	
	
	@Override
	public String toString() {
		return "CalendarVO [calendarIdx=" + calendarIdx + ", fkUserId=" + fkUserId + ", yymmdd=" + yymmdd + ", content="
				+ content + ", filePath=" + filePath + ", cOriVideo=" + cOriVideo + ", cSysVideo=" + cSysVideo + "]";
	}
    
    
	
	


 
    


}
