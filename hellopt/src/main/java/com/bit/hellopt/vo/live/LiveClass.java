package com.bit.hellopt.vo.live;

import java.util.Date;

public class LiveClass {
	private String fkUserId, classType, className;
	private String classTime, classDay, liveStatus;
	private int classIdx, totalMembers, price, classLength, totalViewer;
	private Date classStartDate;
	
	public LiveClass() {}
	
	public String getFkUserId() {
		return fkUserId;
	}
	public void setFkUserId(String fkUserId) {
		this.fkUserId = fkUserId;
	}
	public String getClassType() {
		return classType;
	}
	public void setClassType(String classType) {
		this.classType = classType;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public String getClassTime() {
		return classTime;
	}
	public void setClassTime(String classTime) {
		this.classTime = classTime;
	}
	public String getClassDay() {
		return classDay;
	}
	public void setClassDay(String classDay) {
		this.classDay = classDay;
	}
	public String getLiveStatus() {
		return liveStatus;
	}
	public void setLiveStatus(String liveStatus) {
		this.liveStatus = liveStatus;
	}
	public int getClassIdx() {
		return classIdx;
	}
	public void setClassIdx(int classIdx) {
		this.classIdx = classIdx;
	}
	public int getTotalMembers() {
		return totalMembers;
	}
	public void setTotalMembers(int totalMembers) {
		this.totalMembers = totalMembers;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getClassLength() {
		return classLength;
	}
	public void setClassLength(int classLength) {
		this.classLength = classLength;
	}
	public int getTotalViewer() {
		return totalViewer;
	}
	public void setTotalViewer(int totalViewer) {
		this.totalViewer = totalViewer;
	}
	public Date getClassStartDate() {
		return classStartDate;
	}
	public void setClassStartDate(Date classStartDate) {
		this.classStartDate = classStartDate;
	}
	
	@Override
	public String toString() {
		return "Class [fkUserId=" + fkUserId + ", classType=" + classType + ", className=" + className + ", classTime="
				+ classTime + ", classDay=" + classDay + ", liveStatus=" + liveStatus + ", classIdx=" + classIdx
				+ ", totalMembers=" + totalMembers + ", price=" + price + ", classLength=" + classLength
				+ ", totalViewer=" + totalViewer + ", classStartDate=" + classStartDate + "]";
	}
}
