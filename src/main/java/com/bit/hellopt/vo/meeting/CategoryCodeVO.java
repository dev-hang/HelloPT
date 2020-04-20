package com.bit.hellopt.vo.meeting;

public class CategoryCodeVO {
	
	 // 모임카테고리 
    private String mCategoryNo;

    // 모임카테고리명 
    private String mCategory;
    
    
    // setter / getter
	public String getmCategoryNo() {
		return mCategoryNo;
	}

	public void setmCategoryNo(String mCategoryNo) {
		this.mCategoryNo = mCategoryNo;
	}

	public String getmCategory() {
		return mCategory;
	}

	public void setmCategory(String mCategory) {
		this.mCategory = mCategory;
	}

	@Override
	public String toString() {
		return "CategoryCodeVO [mCategoryNo=" + mCategoryNo + ", mCategory=" + mCategory + "]";
	}
    
    
	


 
    


}
