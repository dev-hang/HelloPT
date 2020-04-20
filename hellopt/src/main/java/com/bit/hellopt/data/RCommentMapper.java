package com.bit.hellopt.data;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.bit.hellopt.vo.reviewboard.RCommentVO;

public interface RCommentMapper {
	
	//댓글 목록
	@Select("SELECT REV_CMT_IDX, USER_ID, USER_NAME, REV_CMT_COMMENT, TO_CHAR (SYSDATE, 'YYYY-MM-DD HH:mm:ss') "
			+ 		"FROM REVIEW_COMMENT_TB WHERE REV_IDX = #{revIdx} ORDER BY REV_CMT_REGDATE DESC ")
	public List<RCommentVO> cmtList(int revIdx);
	
	//댓글 작성
	@Insert("INSERT INTO REVIEW_COMMENT_TB (REV_CMT_IDX, USER_ID, USER_NAME, REV_CMT_COMMENT, REV_IDX)"
			+ "VALUES((SELECT NVL(MAX(REV_CMT_IDX), 0) + 1 FROM REVIEW_COMMENT_TB), #{userId}, #{userName}, #{revCmtComment}, #{revIdx})")
	public void cmtCreate(RCommentVO vo);
	
	//댓글 수정
	@Update("UPDATE REVIEW_COMMENT_TB SET\r\n" + 
			"		REV_CMT_COMMENT = #{revCmtComment},\r\n" + 
			"		REV_CMT_EDITDATE = SYSDATE\r\n" + 
			"		WHERE REV_CMT_IDX = #{revCmtIdx}")
	public void cmtUpdate(RCommentVO vo);
	
	//댓글 삭제
	@Delete("DELETE FROM REVIEW_COMMENT_TB WHERE REV_CMT_IDX= #{revCmtIdx}")
	public void cmtDelete(int revCmtIdx);
}
