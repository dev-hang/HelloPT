package com.bit.hellopt.vo.exercise;

public class ExerciseInformationFileVO {
	private int exerciseInformationFileIdx;
	private String exerciseInformationFileOname;
	private String exerciseInformationFileSname;
	private long exerciseInformationFileSize;
	private int exerciseIdx;
	
	public int getExerciseInformationFileIdx() {
		return exerciseInformationFileIdx;
	}
	public void setExerciseInformationFileIdx(int exerciseInformationFileIdx) {
		this.exerciseInformationFileIdx = exerciseInformationFileIdx;
	}
	public String getExerciseInformationFileOname() {
		return exerciseInformationFileOname;
	}
	public void setExerciseInformationFileOname(String exerciseInformationFileOname) {
		this.exerciseInformationFileOname = exerciseInformationFileOname;
	}
	public String getExerciseInformationFileSname() {
		return exerciseInformationFileSname;
	}
	public void setExerciseInformationFileSname(String exerciseInformationFileSname) {
		this.exerciseInformationFileSname = exerciseInformationFileSname;
	}
	public long getExerciseInformationFileSize() {
		return exerciseInformationFileSize;
	}
	public void setExerciseInformationFileSize(long exerciseInformationFileSize) {
		this.exerciseInformationFileSize = exerciseInformationFileSize;
	}
	public int getExerciseIdx() {
		return exerciseIdx;
	}
	public void setExerciseIdx(int exerciseIdx) {
		this.exerciseIdx = exerciseIdx;
	}
	
	@Override
	public String toString() {
		return "ExerciseInformationFileVO [exerciseInformationFileIdx=" + exerciseInformationFileIdx
				+ ", exerciseInformationFileOname=" + exerciseInformationFileOname + ", exerciseInformationFileSname="
				+ exerciseInformationFileSname + ", exerciseInformationFileSize=" + exerciseInformationFileSize
				+ ", exerciseIdx=" + exerciseIdx + "]";
	}
	
}
