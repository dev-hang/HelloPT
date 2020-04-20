package com.bit.hellopt.vo.reviewboard;

import java.util.Date;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.bit.hellopt.vo.user.User;


public class RBoardVO extends RPagingVO {

	private int revIdx;
	private String userId;
	private String userName;
	private String userFileName;
	private String revContent;
	private Date revRegdate;
	private String revStar;
	private String revCategory;
	private List<RFileVO> filevo;
	private int cmtCnt; //게시글 댓글의 수 
	
	public int getCmtCnt() {
		return cmtCnt;
	}



	public void setCmtCnt(int cmtCnt) {
		this.cmtCnt = cmtCnt;
	}



	public String getUserFileName() {
		return userFileName;
	}



	public void setUserFileName(String userFileName) {
		this.userFileName = userFileName;
	}



	public int getRevIdx() {
		return revIdx;
	}



	public void setRevIdx(int revIdx) {
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

	public String getRevContent() {
		return revContent;
	}



	public void setRevContent(String revContent) {
		this.revContent = revContent;
	}



	public Date getRevRegdate() {
		return revRegdate;
	}



	public void setRevRegdate(Date revRegdate) {
		this.revRegdate = revRegdate;
	}



	public String getRevStar() {
		return revStar;
	}



	public void setRevStar(String revStar) {
		this.revStar = revStar;
	}



	public String getRevCategory() {
		return revCategory;
	}



	public void setRevCategory(String revCategory) {
		this.revCategory = revCategory;
	}



	public List<RFileVO> getFilevo() {
		return filevo;
	}



	public void setFilevo(List<RFileVO> filevo) {
		this.filevo = filevo;
	}



	@Override
	public String toString() {
		return "RBoardVO [revIdx=" + revIdx + ", userId=" + userId + ", userName=" + userName + ", userFileName="
				+ userFileName + ", revContent=" + revContent + ", revRegdate=" + revRegdate + ", revStar=" + revStar
				+ ", revCategory=" + revCategory + ", filevo=" + filevo + ", cmtCnt=" + cmtCnt + "]";
	}

	
}