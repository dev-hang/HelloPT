package com.bit.hellopt.vo.exercise;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

public class ExerciseInformationVO {
	private int exerciseIdx;
	private String exerciseName;
	private String exerciseEName;
	private String exerciseParts;
	private String howtoExercise;
	private String howtoExercise2;
	private String exercisePictures;
	private String exerciseVideo;
	private String caution;
	private int repetition;
	private int setCount;
	private int restTime;
	private List<ExerciseInformationFileVO> filevo;
	
	//검색조건용 필드 추가
	private String searchCondition;
	private String searchKeyword;
	
	//파일업로드
	private MultipartFile uploadFile;
	
	//임시
	private List<String> exercisePicturesList = new ArrayList<>();
	
	public List<String> getExercisePicturesList() {
		return exercisePicturesList;
	}

	public void setExercisePicturesList(List<String> exercisePicturesList) {
		this.exercisePicturesList = exercisePicturesList;
	}

	public void splitExercisePicturesList() {
		exercisePicturesList= Arrays.asList( exercisePictures.split(",") );
		
		/*
		for(int i=0; i<exercisePicturesList.size(); i++) {
			exercisePicturesList.set(i, "C:\\hellopt_file\\"+exercisePicturesList.get(i));
		}*/
	}
	
	public ExerciseInformationVO() {
		//System.out.println(">> ExerciseInformationVO 객체 생성");
	}

	public int getExerciseIdx() {
		return exerciseIdx;
	}

	public void setExerciseIdx(int exerciseIdx) {
		this.exerciseIdx = exerciseIdx;
	}

	public String getExerciseName() {
		return exerciseName;
	}

	public void setExerciseName(String exerciseName) {
		this.exerciseName = exerciseName;
	}
	
	public String getExerciseEName() {
		return exerciseEName;
	}

	public void setExerciseEName(String exerciseEName) {
		this.exerciseEName = exerciseEName;
	}

	public String getExerciseParts() {
		return exerciseParts;
	}

	public void setExerciseParts(String exerciseParts) {
		this.exerciseParts = exerciseParts;
	}
	
	public String getHowtoExercise() {
		return howtoExercise;
	}

	public void setHowtoExercise(String howtoExercise) {
		this.howtoExercise = howtoExercise;
	}

	public String getHowtoExercise2() {
		return howtoExercise2;
	}

	public void setHowtoExercise2(String howtoExercise2) {
		this.howtoExercise2 = howtoExercise2;
	}

	public String getExercisePictures() {
		return exercisePictures;
	}

	public void setExercisePictures(String exercisePictures) {
		this.exercisePictures = exercisePictures;
	}

	public String getExerciseVideo() {
		return exerciseVideo;
	}

	public void setExerciseVideo(String exerciseVideo) {
		this.exerciseVideo = exerciseVideo;
	}

	public String getCaution() {
		return caution;
	}

	public void setCaution(String caution) {
		this.caution = caution;
	}

	public int getRepetition() {
		return repetition;
	}

	public void setRepetition(int repetition) {
		this.repetition = repetition;
	}

	public int getSetCount() {
		return setCount;
	}

	public void setSetCount(int setCount) {
		this.setCount = setCount;
	}

	public int getRestTime() {
		return restTime;
	}

	public void setRestTime(int restTime) {
		this.restTime = restTime;
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

	//파일업로드를 위한 생성자
	public List<ExerciseInformationFileVO> getFilevo() {
		return filevo;
	}

	public void setFilevo(List<ExerciseInformationFileVO> filevo) {
		this.filevo = filevo;
	}

	@Override
	public String toString() {
		return "ExerciseInformationVO [exerciseIdx=" + exerciseIdx + ", exerciseName=" + exerciseName
				+ ", exerciseEName=" + exerciseEName + ", exerciseParts=" + exerciseParts + ", howtoExercise="
				+ howtoExercise + ", howtoExercise2=" + howtoExercise2 + ", exercisePictures=" + exercisePictures
				+ ", exerciseVideo=" + exerciseVideo + ", caution=" + caution + ", repetition=" + repetition
				+ ", setCount=" + setCount + ", restTime=" + restTime + ", filevo=" + filevo + ", searchCondition="
				+ searchCondition + ", searchKeyword=" + searchKeyword + ", uploadFile=" + uploadFile
				+ ", exercisePicturesList=" + exercisePicturesList + "]";
	}

}
