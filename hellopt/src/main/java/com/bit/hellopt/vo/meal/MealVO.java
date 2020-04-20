package com.bit.hellopt.vo.meal;

public class MealVO {
	private String mealName, mealUnit;
	private int mealNo, mealAmount, mealKcal, mealProtein, mealFat, mealCarb, mealSodium;
	
	public MealVO() {}

	public String getMealName() {
		return mealName;
	}

	public void setMealName(String mealName) {
		this.mealName = mealName;
	}

	public String getMealUnit() {
		return mealUnit;
	}

	public void setMealUnit(String mealUnit) {
		this.mealUnit = mealUnit;
	}

	public int getMealNo() {
		return mealNo;
	}

	public void setMealNo(int mealNo) {
		this.mealNo = mealNo;
	}

	public int getMealAmount() {
		return mealAmount;
	}

	public void setMealAmount(int mealAmount) {
		this.mealAmount = mealAmount;
	}

	public int getMealKcal() {
		return mealKcal;
	}

	public void setMealKcal(int mealKcal) {
		this.mealKcal = mealKcal;
	}

	public int getMealProtein() {
		return mealProtein;
	}

	public void setMealProtein(int mealProtein) {
		this.mealProtein = mealProtein;
	}

	public int getMealFat() {
		return mealFat;
	}

	public void setMealFat(int mealFat) {
		this.mealFat = mealFat;
	}

	public int getMealCarb() {
		return mealCarb;
	}

	public void setMealCarb(int mealCarb) {
		this.mealCarb = mealCarb;
	}

	public int getMealSodium() {
		return mealSodium;
	}

	public void setMealSodium(int mealSodium) {
		this.mealSodium = mealSodium;
	}

	@Override
	public String toString() {
		return "MealVO [mealName=" + mealName + ", mealUnit=" + mealUnit + ", mealNo=" + mealNo + ", mealAmount="
				+ mealAmount + ", mealKcal=" + mealKcal + ", mealProtein=" + mealProtein + ", mealFat=" + mealFat
				+ ", mealCarb=" + mealCarb + ", mealSodium=" + mealSodium + "]";
	}
	
	
}
