package com.bit.hellopt.controller.reviewboard;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.bit.hellopt.service.reviewboard.RBoardService;
import com.bit.hellopt.service.reviewboard.RCommentService;
import com.bit.hellopt.service.user.UserProfileService;
import com.bit.hellopt.service.user.UserService;
import com.bit.hellopt.vo.reviewboard.RBoardVO;
import com.bit.hellopt.vo.reviewboard.RCommentVO;
import com.bit.hellopt.vo.reviewboard.RFileVO;
import com.bit.hellopt.vo.user.CustomUserDetail;
import com.bit.hellopt.vo.user.ProfileVO;
import com.bit.hellopt.vo.user.User;

@RestController
@SessionAttributes("rReply")
public class RBoardCommentController {
	
	@Autowired
	RCommentService rCmtService;
	@Autowired
	ServletContext servletContext;
	
	public RBoardCommentController(RCommentService rCmtService) {
		this.rCmtService = rCmtService;
	}

	//댓글 입력
	@RequestMapping("/reply/insert")
	public void insert(@RequestParam(value="revIdx", required = false)int revIdx, @ModelAttribute RCommentVO cvo, RBoardVO vo,Model model,  
			@AuthenticationPrincipal CustomUserDetail customUser) 
					throws IllegalStateException, IOException{
		
		System.out.println("댓글쓰기");
		System.out.println("댓글vo:" +cvo);
		
		String userId = customUser.getUsername();
		String name = customUser.getName();
		cvo.setUserId(userId);
		cvo.setUserName(name);

		System.out.println("댓글vo2:" +cvo);
		
		rCmtService.cmtCreate(cvo);
		
	}
	//댓글 수정
	@RequestMapping("/reply/update")
	@ResponseBody
	public Map<String, Object>updateReply(@RequestBody RCommentVO paramData)
					throws IllegalStateException, IOException{
		System.out.println("댓수정");
		System.out.println("cvo: "+ paramData);
		Map<String, Object> result = new HashMap<>();
		System.out.println("result: "+ result);
		
		try {
			rCmtService.cmtUpdate(paramData);
			result.put("status","OK");
		} catch(Exception e) {
			e.printStackTrace();
			result.put("status", "False");
		}
		
		return result;
		
	}
	//댓글 삭제
	@RequestMapping("/reply/delete")
	public void delete(@RequestParam(value="revCmtIdx", required = false)int revCmtIdx, 
			@ModelAttribute RCommentVO cvo, RBoardVO vo,Model model,  
			@AuthenticationPrincipal CustomUserDetail customUser) 
					throws IllegalStateException, IOException{
		System.out.println("cvo"+cvo);
		System.out.println("댓삭");
		rCmtService.cmtDelete(revCmtIdx);
		
	}
	//댓글 목록 (@controller방식 : view 화면을 리턴)
	@RequestMapping("/review/replylist")
	@ResponseBody
	public String list(@RequestParam int revIdx, Model model) {
		System.out.println("뷰화면리턴");
		List<RCommentVO> list = rCmtService.joinCmt(revIdx);
		model.addAttribute("list", list);
		System.out.println("된건가");
		return "/review/reviewCmtList";
	}
	
	
	//댓글 목록(@RestController Json 방식으로 처리 : 데이터를 리턴)
	@RequestMapping("/review/replyjson")
	@ResponseBody //리턴데이터를 json으로 변환(생략가능)
	public List<RCommentVO> listJson(@RequestParam int revIdx){
		System.out.println("댓글쓰기");
		List<RCommentVO> list = rCmtService.joinCmt(revIdx);
		return list;
	}
	
}
