package com.bit.hellopt.vo.live;

public class ClassMember {
	private String fkUserId, payStatus;
	private int classMemberIdx, fkClassIdx;
	
	public ClassMember() {}

	public String getFkUserId() {
		return fkUserId;
	}

	public void setFkUserId(String fkUserId) {
		this.fkUserId = fkUserId;
	}

	public String getPayStatus() {
		return payStatus;
	}

	public void setPayStatus(String payStatus) {
		this.payStatus = payStatus;
	}

	public int getClassMemberIdx() {
		return classMemberIdx;
	}

	public void setClassMemberIdx(int classMemberIdx) {
		this.classMemberIdx = classMemberIdx;
	}

	public int getFkClassIdx() {
		return fkClassIdx;
	}

	public void setFkClassIdx(int fkClassIdx) {
		this.fkClassIdx = fkClassIdx;
	}

	@Override
	public String toString() {
		return "ClassMember [fkUserId=" + fkUserId + ", payStatus=" + payStatus + ", classMemberIdx=" + classMemberIdx
				+ ", fkClassIdx=" + fkClassIdx + "]";
	}
	
}
