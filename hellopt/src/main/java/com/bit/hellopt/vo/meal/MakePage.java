package com.bit.hellopt.vo.meal;

public class MakePage {
	private int currPage;
	private int totalCount;	//외부에서 받아야하는 값
	private int pageSize;
	private int blockSize;
	
	private int startRow;
	private int endRow;
	
	private int startBlock;
	private int endBlock;
	
	private boolean prev;
	private boolean next;
	
	public MakePage(int currPage, int totalCount, int pageSize, int blockSize) {
		this.currPage=currPage;
		this.totalCount=totalCount;
		this.pageSize=pageSize;
		this.blockSize=blockSize;
		calData();
	}
	

	private void calData() {
		startRow=(currPage-1)*pageSize+1;
		endRow=startRow+pageSize-1;
		if(endRow>totalCount) {
			endRow=totalCount;
		}
		
		int totalpage=(int)Math.ceil(totalCount/(float)pageSize);
		
		startBlock=((currPage-1)/blockSize)*blockSize+1;
		endBlock=startBlock+blockSize-1;
		
		if(endBlock>totalpage) {
			endBlock=totalpage;
		}
		
		prev=(startBlock==1)? false:true;
		next=(endBlock<totalpage)? true:false;
		
		
		System.out.println("startBlock: "+startBlock);
		System.out.println("endBlock: "+endBlock);
		System.out.println("currPage: "+currPage);
	}
	
	public int getCurrPage() {
		return currPage;
	}

	public int getStartRow() {
		return startRow;
	}

	public int getEndRow() {
		return endRow;
	}

	public int getStartBlock() {
		return startBlock;
	}

	public int getEndBlock() {
		return endBlock;
	}

	public boolean isPrev() {
		return prev;
	}

	public boolean isNext() {
		return next;
	}
}
