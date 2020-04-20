package com.bit.hellopt.vo.reviewboard;

import java.util.Date;

public class RCommentVO {

	private Integer revCmtIdx;	//댓글 번호
	private Integer revIdx;		//게시글 번호
	private String userId;
	private String userName;
	private String revCmtComment;
	private Date revCmtRegdate;
	private Date revCmtEditdate;
	public Integer getRevCmtIdx() {
		return revCmtIdx;
	}
	public void setRevCmtIdx(Integer revCmtIdx) {
		this.revCmtIdx = revCmtIdx;
	}
	public Integer getRevIdx() {
		return revIdx;
	}
	public void setRevIdx(Integer revIdx) {
		this.revIdx = revIdx;
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
	public String getRevCmtComment() {
		return revCmtComment;
	}
	public void setRevCmtComment(String revCmtComment) {
		this.revCmtComment = revCmtComment;
	}
	public Date getRevCmtRegdate() {
		return revCmtRegdate;
	}
	public void setRevCmtRegdate(Date revCmtRegdate) {
		this.revCmtRegdate = revCmtRegdate;
	}
	public Date getRevCmtEditdate() {
		return revCmtEditdate;
	}
	public void setRevCmtEditdate(Date revCmtEditdate) {
		this.revCmtEditdate = revCmtEditdate;
	}
	@Override
	public String toString() {
		return "RCommentVO [revCmtIdx=" + revCmtIdx + ", revIdx=" + revIdx + ", userId=" + userId + ", userName="
				+ userName + ", revCmtComment=" + revCmtComment + ", revCmtRegdate=" + revCmtRegdate
				+ ", revCmtEditdate=" + revCmtEditdate + "]";
	}
	
	
	
	
}
