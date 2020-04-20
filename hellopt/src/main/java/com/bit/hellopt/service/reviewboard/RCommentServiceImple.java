package com.bit.hellopt.service.reviewboard;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bit.hellopt.data.RBoardMapper2;
import com.bit.hellopt.data.RCommentMapper;
import com.bit.hellopt.vo.reviewboard.RCommentVO;

@Service
public class RCommentServiceImple implements RCommentService {

	@Autowired
	RCommentMapper cmtMapper;
	
	@Autowired
	RBoardMapper2 mapper2;
	
	//댓글목록
	@Override
	public List<RCommentVO> cmtList(int revIdx) {
		return cmtMapper.cmtList(revIdx);
	}
	//댓글작성
	@Override
	public void cmtCreate(RCommentVO vo) {
		cmtMapper.cmtCreate(vo);
	}
	//댓글수정
	@Override
	public void cmtUpdate(RCommentVO vo) {
		cmtMapper.cmtUpdate(vo);
		
	}
	//댓글삭제
	@Override
	public void cmtDelete(int revCmtIdx) {
		cmtMapper.cmtDelete(revCmtIdx);
		
	}
	@Override
	public List<RCommentVO> joinCmt(int revIdx) {
		return mapper2.joinCmt(revIdx);
	}

}
