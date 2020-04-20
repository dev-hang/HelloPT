package com.bit.hellopt.service.reviewboard;

import java.util.List;

import com.bit.hellopt.vo.reviewboard.RCommentVO;

public interface RCommentService {
	
	//댓글 목록
	public List<RCommentVO> cmtList(int revIdx);
	
	//댓글 입력
	public void cmtCreate(RCommentVO vo);
	
	//댓글 수정
	public void cmtUpdate(RCommentVO vo);
	
	//댓글 삭제
	public void cmtDelete(int revCmtIdx);
	
	//조인
	List<RCommentVO> joinCmt(int revIdx);
}
