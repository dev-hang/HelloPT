package com.bit.hellopt.data;

import java.util.List;
import java.util.Map;

import com.bit.hellopt.vo.reviewboard.RBoardVO;
import com.bit.hellopt.vo.reviewboard.RCommentVO;
import com.bit.hellopt.vo.reviewboard.RFileVO;
import com.bit.hellopt.vo.user.User;

//조인용 매퍼
public interface RBoardMapper2 {
	public User join1(String userId);
	public List<RBoardVO> join2(Map<String, Integer> map);
	public RBoardVO join3(int revIdx);
	public List<RBoardVO> getProfilePic();
	
	public List<RCommentVO> joinCmt(int revIdx);
}
