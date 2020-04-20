package com.bit.hellopt.vo.meeting;

public class MeetingFileVO {
	
	 // 파일번호 
    private int fileIdx;
    // 게시글_시퀀스 
    private int fkMeetingIdx;
    // 파일경로 
    private String filePath;
    // 원본이미지명 
    private String mOriImg;
    // 변경파일명이미지 
    private String mSysImg;
    
    
    
	public int getFileIdx() {
		return fileIdx;
	}
	public void setFileIdx(int fileIdx) {
		this.fileIdx = fileIdx;
	}
	public int getFkMeetingIdx() {
		return fkMeetingIdx;
	}
	public void setFkMeetingIdx(int fkMeetingIdx) {
		this.fkMeetingIdx = fkMeetingIdx;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public String getmOriImg() {
		return mOriImg;
	}
	public void setmOriImg(String mOriImg) {
		this.mOriImg = mOriImg;
	}
	public String getmSysImg() {
		return mSysImg;
	}
	public void setmSysImg(String mSysImg) {
		this.mSysImg = mSysImg;
	}
	
	
	@Override
	public String toString() {
		return "MeetingFileVO [fileIdx=" + fileIdx + ", fkMeetingIdx=" + fkMeetingIdx + ", filePath=" + filePath
				+ ", mOriImg=" + mOriImg + ", mSysImg=" + mSysImg + "]";
	}
	
    
	
}    