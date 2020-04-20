package com.bit.hellopt.vo.user;

import java.util.Date;

public class ProfileVO {
	private int userProfileIdx;
	private String fkUserId;
	private String originalFileName;
	private String storedFileName;
	private Date uploadedDate;
	
	public ProfileVO() {}

	public int getUserProfileIdx() {
		return userProfileIdx;
	}

	public void setUserProfileIdx(int userProfileIdx) {
		this.userProfileIdx = userProfileIdx;
	}

	public String getFkUserId() {
		return fkUserId;
	}

	public void setFkUserId(String fkUserId) {
		this.fkUserId = fkUserId;
	}

	public String getOriginalFileName() {
		return originalFileName;
	}

	public void setOriginalFileName(String originalFileName) {
		this.originalFileName = originalFileName;
	}

	public String getStoredFileName() {
		return storedFileName;
	}

	public void setStoredFileName(String storedFileName) {
		this.storedFileName = storedFileName;
	}

	public Date getUploadedDate() {
		return uploadedDate;
	}

	public void setUploadedDate(Date uploadedDate) {
		this.uploadedDate = uploadedDate;
	}
}
