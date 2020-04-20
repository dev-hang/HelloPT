package com.bit.hellopt.service.reviewboard;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.bit.hellopt.vo.reviewboard.RBoardVO;
import com.bit.hellopt.vo.reviewboard.RFileVO;
import com.bit.hellopt.vo.user.User;


public interface RBoardService {
	
	void insertBoard(RBoardVO vo);
	void updateBoard(RBoardVO vo);
	void deleteBoard(int revIdx);
	
	//	파일처리부분
	void uploadFile(String revFileOname, String saveFileName, long fileSize, int revIdx);
	void uploadFileDel(int revFileIdx);
	List<RBoardVO> getRBoardList();

	List<RFileVO> getFileList(int revIdx);
	List<User> selectUserId(String userId);
	User selectUser();
	List<RBoardVO> getProfilePic();
	
	
	//게시물 총 갯수
	int getTotalCount();
	//페이징 처리 게시글 조회
	List<RBoardVO> Join2(Map<String, Integer>map);
	RBoardVO Join3(int revIdx);
}
