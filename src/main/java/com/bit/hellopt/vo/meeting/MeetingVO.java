package com.bit.hellopt.vo.meeting;

import java.util.List;

public class MeetingVO {
	
    // 게시글_시퀀스 
    private int meetingIdx;
    // 유저아이디 
    private String fkUserId;
    // 개설자한마디 
    private String mComment;
    // 모임카테고리 
    private String fkMCategoryNo;
    // 지역코드 
    private String fkLocalNo;
    // 모임일자 
    private String mDate;
    // 모임제목 
    private String mSubject;
    // 요약정보 
    private String mMemo;
    // 상세정보 
    private String details;
    // 포함사항 
    private String include;
    // 모임위치 
    private String mLocation;
    // 모임위치메모 
    private String mLocationC;
    // 모임가격 
    private int mPrice;
    // 모임진행상태 
    private String progressSt;
    // 모임조회수 
    private int mCnt;
    // 등록일자 
    private String insDt;
    
    // 모임카테고리 
    private String mCategory;
    // 지역코드 
    private String local;
    // 유저이름
    private String userName;
    // 유저아이디
    private String userId;
    // 모임최대인원
    private int maxCount;
    // fk idx
    private int fkMeetingIdx;
    
    
    private List<MeetingFileVO> meetingFileVO;
    
    
    // getter / setter
	public int getMeetingIdx() {
		return meetingIdx;
	}
	public void setMeetingIdx(int meetingIdx) {
		this.meetingIdx = meetingIdx;
	}
	public String getFkUserId() {
		return fkUserId;
	}
	public void setFkUserId(String fkUserId) {
		this.fkUserId = fkUserId;
	}
	public String getmComment() {
		return mComment;
	}
	public void setmComment(String mComment) {
		this.mComment = mComment;
	}
	public String getFkMCategoryNo() {
		return fkMCategoryNo;
	}
	public void setFkMCategoryNo(String fkMCategoryNo) {
		this.fkMCategoryNo = fkMCategoryNo;
	}
	public String getFkLocalNo() {
		return fkLocalNo;
	}
	public void setFkLocalNo(String fkLocalNo) {
		this.fkLocalNo = fkLocalNo;
	}
	public String getmDate() {
		return mDate;
	}
	public void setmDate(String mDate) {
		this.mDate = mDate;
	}
	public String getmSubject() {
		return mSubject;
	}
	public void setmSubject(String mSubject) {
		this.mSubject = mSubject;
	}
	public String getmMemo() {
		return mMemo;
	}
	public void setmMemo(String mMemo) {
		this.mMemo = mMemo;
	}
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}
	public String getInclude() {
		return include;
	}
	public void setInclude(String include) {
		this.include = include;
	}
	public String getmLocation() {
		return mLocation;
	}
	public void setmLocation(String mLocation) {
		this.mLocation = mLocation;
	}
	public String getmLocationC() {
		return mLocationC;
	}
	public void setmLocationC(String mLocationC) {
		this.mLocationC = mLocationC;
	}
	public int getmPrice() {
		return mPrice;
	}
	public void setmPrice(int mPrice) {
		this.mPrice = mPrice;
	}
	public String getProgressSt() {
		return progressSt;
	}
	public void setProgressSt(String progressSt) {
		this.progressSt = progressSt;
	}
	public int getmCnt() {
		return mCnt;
	}
	public void setmCnt(int mCnt) {
		this.mCnt = mCnt;
	}
	public String getInsDt() {
		return insDt;
	}
	public void setInsDt(String insDt) {
		this.insDt = insDt;
	}
	
	
	
	
	
	
	
	public int getMaxCount() {
		return maxCount;
	}
	public void setMaxCount(int maxCount) {
		this.maxCount = maxCount;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getmCategory() {
		return mCategory;
	}
	public void setmCategory(String mCategory) {
		this.mCategory = mCategory;
	}
	public String getLocal() {
		return local;
	}
	public int getFkMeetingIdx() {
		return fkMeetingIdx;
	}
	public void setFkMeetingIdx(int fkMeetingIdx) {
		this.fkMeetingIdx = fkMeetingIdx;
	}
	public void setLocal(String local) {
		this.local = local;
	}
	public List<MeetingFileVO> getMeetingFileVO() {
		return meetingFileVO;
	}
	public void setMeetingFileVO(List<MeetingFileVO> meetingFileVO) {
		this.meetingFileVO = meetingFileVO;
	}
	@Override
	public String toString() {
		return "MeetingVO [meetingIdx=" + meetingIdx + ", fkUserId=" + fkUserId + ", mComment=" + mComment
				+ ", fkMCategoryNo=" + fkMCategoryNo + ", fkLocalNo=" + fkLocalNo + ", mDate=" + mDate + ", mSubject="
				+ mSubject + ", mMemo=" + mMemo + ", details=" + details + ", include=" + include + ", mLocation="
				+ mLocation + ", mLocationC=" + mLocationC + ", mPrice=" + mPrice + ", progressSt=" + progressSt
				+ ", mCnt=" + mCnt + ", insDt=" + insDt + ", mCategory=" + mCategory + ", local=" + local
				+ ", userName=" + userName + ", userId=" + userId + ", maxCount=" + maxCount + ", fkMeetingIdx="
				+ fkMeetingIdx + ", meetingFileVO=" + meetingFileVO + "]";
	}
    

 
    


}
