package com.bit.hellopt.service.reviewboard;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.bit.hellopt.data.RBoardMapper1;
import com.bit.hellopt.data.RBoardMapper2;
import com.bit.hellopt.vo.reviewboard.RBoardVO;
import com.bit.hellopt.vo.reviewboard.RFileVO;
import com.bit.hellopt.vo.user.User;


@Service
public class RBoardServiceImpl implements RBoardService {
	
	@Autowired
	RBoardMapper1 mapper;
	@Autowired
	RBoardMapper2 mapper2;
	
	
	
	@Override
	public void insertBoard(RBoardVO vo) {
		mapper.insertRBoard(vo);
	}

	@Override
	public void updateBoard(RBoardVO vo) {
		mapper.updateRBoard(vo);
		
	}

	@Override
	public void deleteBoard(int revIdx) {
		mapper.deleteRBoard(revIdx);
		
	}

	@Override
	public List<RBoardVO> getRBoardList() {
		return mapper.getRBoardList();
	}

	@Override
	public void uploadFile(String revFileOname, String saveFileName, long fileSize, int revIdx) {
		HashMap<String, Object> hm = new HashMap<>();
		hm.put("revFileOname", revFileOname);
		hm.put("revFileSname", saveFileName);
		hm.put("revFileSize", fileSize);
		hm.put("revIdx", revIdx);
		System.out.println(hm);
		mapper.uploadFile(hm);
	}


	@Override
	public List<RFileVO> getFileList(int revIdx) {
		return mapper.getFileList(revIdx);
	}

	@Override
	public User selectUser() {
		
		return mapper.selectUser();
	}
	@Override
	public List<User> selectUserId(String userId) {
		
		return mapper.selectUserId(userId);
	}

	@Override
	public List<RBoardVO> getProfilePic() {
		
		return mapper2.getProfilePic();
	}

	@Override
	public List<RBoardVO> Join2(Map<String, Integer>map) {
		return mapper2.join2(map);
	}

	@Override
	public int getTotalCount() {
		return mapper.getTotalCount();
	}

	@Override
	public RBoardVO Join3(int revIdx) {
		
		return mapper2.join3(revIdx);
	}

	@Override
	public void uploadFileDel(int revFileIdx) {
		mapper.uploadFileDel(revFileIdx);
	}

	





}
