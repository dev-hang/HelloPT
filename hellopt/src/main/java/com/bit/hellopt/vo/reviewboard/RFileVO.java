package com.bit.hellopt.vo.reviewboard;

public class RFileVO {
	private int revFileIdx;
	private String revFileOname;
	private String revFileSname;
	private long revFileSize;
	private int revIdx;
	
	
	
	public int getRevFileIdx() {
		return revFileIdx;
	}
	public void setRevFileIdx(int revFileIdx) {
		this.revFileIdx = revFileIdx;
	}
	public String getRevFileOname() {
		return revFileOname;
	}
	public void setRevFileOname(String revFileOname) {
		this.revFileOname = revFileOname;
	}
	public String getRevFileSname() {
		return revFileSname;
	}
	public void setRevFileSname(String revFileSname) {
		this.revFileSname = revFileSname;
	}
	public long getRevFileSize() {
		return revFileSize;
	}
	public void setRevFileSize(long revFileSize) {
		this.revFileSize = revFileSize;
	}
	public int getRevIdx() {
		return revIdx;
	}
	public void setRevIdx(int revIdx) {
		this.revIdx = revIdx;
	}
	@Override
	public String toString() {
		return "RFileVO [revFileIdx=" + revFileIdx + ", revFileOname=" + revFileOname + ", revFileSname=" + revFileSname
				+ ", revFileSize=" + revFileSize + ", revIdx=" + revIdx + "]";
	}
	
	
	
	
	
	
}
