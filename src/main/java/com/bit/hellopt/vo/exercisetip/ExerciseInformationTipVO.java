package com.bit.hellopt.vo.exercisetip;

import java.util.Date;

import org.springframework.web.multipart.MultipartFile;


/* USERS_TB Table Create SQL
CREATE TABLE EXCERCISE_INFORMATION_TB
(
    EXERCISE_IDX        NUMBER(5)        NOT NULL, 
    EXERCISE_NAME       VARCHAR2(20)     NOT NULL, 
    EXERCISE_PARTS      VARCHAR2(20)     NOT NULL, 
    EXERCISE_PICTURES    VARCHAR2(100)    NOT NULL, 
    EXERCISE_VIDEO      VARCHAR2(100)    NOT NULL, 
    CAUTION             VARCHAR2(500)    NOT NULL, 
    REPETITION          NUMBER(5)        NOT NULL, 
    SET_COUNT           NUMBER(5)        NOT NULL, 
    REST_TIME           NUMBER(5)        NOT NULL, 
    CONSTRAINT EXCERCISE_INFORMATION_TB_PK PRIMARY KEY (EXERCISE_IDX)
)
/

CREATE SEQUENCE EXCERCISE_INFORMATION_TB_SEQ
START WITH 1
INCREMENT BY 1;
/

CREATE OR REPLACE TRIGGER EXCERCISE_INFORMATION_T_AI_TRG
BEFORE INSERT ON EXCERCISE_INFORMATION_TB 
REFERENCING NEW AS NEW FOR EACH ROW 
BEGIN 
    SELECT EXCERCISE_INFORMATION_TB_SEQ.NEXTVAL
    INTO :NEW.EXERCISE_IDX
    FROM DUAL;
END;
*/

public class ExerciseInformationTipVO {
	private int tipIdx;
	private String tipTitle;
	private String tipContent;
	private String tipWriter;
	private String tipPictures;
	private Date regdate;
	private int hit;
	
	//검색조건용 필드 추가
	private String searchCondition;
	private String searchKeyword;
	
	//파일업로드
	private MultipartFile uploadFile;
	
	public ExerciseInformationTipVO() {
		//System.out.println(">> ExerciseInformationVO 객체 생성");
	}

	public int getTipIdx() {
		return tipIdx;
	}

	public void setTipIdx(int tipIdx) {
		this.tipIdx = tipIdx;
	}

	public String getTipTitle() {
		return tipTitle;
	}

	public void setTipTitle(String tipTitle) {
		this.tipTitle = tipTitle;
	}

	public String getTipContent() {
		return tipContent;
	}

	public void setTipContent(String tipContent) {
		this.tipContent = tipContent;
	}

	public String getTipWriter() {
		return tipWriter;
	}

	public void setTipWriter(String tipWriter) {
		this.tipWriter = tipWriter;
	}

	public String getTipPictures() {
		return tipPictures;
	}

	public void setTipPictures(String tipPictures) {
		this.tipPictures = tipPictures;
	}

	public Date getRegdate() {
		return regdate;
	}

	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}

	public int getHit() {
		return hit;
	}

	public void setHit(int hit) {
		this.hit = hit;
	}

	public String getSearchCondition() {
		return searchCondition;
	}

	public void setSearchCondition(String searchCondition) {
		this.searchCondition = searchCondition;
	}

	//===================
	//검색조건 관련
	public String getSearchKeyword() {
		return searchKeyword;
	}
	
	public void setSearchKeyword(String searchKeyword) {
		this.searchKeyword = searchKeyword;
	}

	//파일업로드 관련
	public MultipartFile getUploadFile() {
		return uploadFile;
	}

	public void setUploadFile(MultipartFile uploadFile) {
		this.uploadFile = uploadFile;
	}
	
}
