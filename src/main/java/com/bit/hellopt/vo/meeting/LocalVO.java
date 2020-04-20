package com.bit.hellopt.vo.meeting;

public class LocalVO {
	
	// 지역코드 
    private String localNo;

    // 지역코드명 
    private String local;

    
    // setter/getter
	public String getLocalNo() {
		return localNo;
	}

	public void setLocalNo(String localNo) {
		this.localNo = localNo;
	}

	public String getLocal() {
		return local;
	}

	public void setLocal(String local) {
		this.local = local;
	}

	@Override
	public String toString() {
		return "LocalVO [localNo=" + localNo + ", local=" + local + "]";
	}
    
	
    

	


 
    


}
