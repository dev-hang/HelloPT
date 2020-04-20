package com.bit.hellopt.controller.reviewboard;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.bit.hellopt.commons.utils.S3Utils;
import com.bit.hellopt.service.reviewboard.RBoardService;
import com.bit.hellopt.service.reviewboard.RCommentService;
import com.bit.hellopt.service.user.UserProfileService;
//import com.bit.hellopt.vo.reviewboard.Pagination;
import com.bit.hellopt.vo.reviewboard.RBoardVO;
import com.bit.hellopt.vo.reviewboard.RCommentVO;
import com.bit.hellopt.vo.reviewboard.RFileVO;
import com.bit.hellopt.vo.reviewboard.RPagingVO;
import com.bit.hellopt.vo.user.CustomUserDetail;
import com.bit.hellopt.vo.user.User;
import com.google.gson.Gson;

@Controller
@SessionAttributes("rBoard")
public class RBoardController {
	private static final RBoardVO RBoard = null;
	@Autowired
	RBoardService rService;
	@Autowired
	UserProfileService profileService;
	@Autowired
	RCommentService rCmtService;
	
	@Autowired
	ServletContext servletContext;
	
	@Autowired
	S3Utils s3Utils;

	/*@Autowired
	S3Utils s3Utils;*/
	
	public RBoardController(RBoardService rService) {
		this.rService = rService;
	}
	
	
	@RequestMapping("/review")
	public String getRBoardList(RBoardVO vo,RPagingVO rvo, Model model, User uvo,  
			@AuthenticationPrincipal CustomUserDetail customUser,
			@RequestParam(defaultValue="1")Integer cPage) {
		System.out.println(">>글 전체 목록 조회 처리 -getRBoardList()");

		//paging///
		//페이지 처리를 위한 Paging 객체 생성해서 값 설정
		RPagingVO p = new RPagingVO();
		
		System.out.println("getTotal 전: " + rService.getTotalCount());
		
		//1.전체 게시물의 수를 구하기
		p.setTotalRecord(rService.getTotalCount());
		p.setTotalPage();//전체 페이지 갯수 구하기
		
		
		System.out.println("getTotal 후: " + rService.getTotalCount());
		
		System.out.println(">전체 게시글 수 : " + p.getTotalRecord());
		System.out.println(">전체 페이지 수 : " + p.getTotalPage());
		
		//2. 현재 페이지 구하기(default : 1)
		System.out.println("cPage:" + cPage);
		if(cPage != null) {
			p.setNowPage(cPage);
		}
		//3. 현재페이지의 시작번호(begin)와 끝번호(end) 구하기
		p.setEnd(p.getNowPage() * p.getNumPerPage());
		p.setBegin(p.getEnd() - p.getNumPerPage() + 1);
		
		System.out.println("---------------");
		System.out.println(">>현재 페이지 : " + p.getNowPage());
		System.out.println(">>시작번호(begin) : " + p.getBegin());
		System.out.println(">>끝 번호(end) : " + p.getEnd());
		
		//블록계산하기
		//4. 블록의 시작페이지, 끝페이지 구하기(현재페이지 번호 사용)
		int nowPage = p.getNowPage();
		int beginPage = (nowPage -1) / p.getPagePerBlock() * p.getPagePerBlock() + 1;
		p.setBeginPage(beginPage);
		p.setEndPage(p.getBeginPage() + p.getPagePerBlock() -1);
		
		//4-1 끝페이지(endPage)가 전체 페이지 수(totalPage) 보다 크면
		if (p.getEndPage() > p.getTotalPage()) {
			p.setEndPage(p.getTotalPage());
		}
		
		//-------------------------------------
		//현재페이지 기준으로 게시글 가져오기
		Map<String, Integer> map = new HashMap<>();
		map.put("begin", p.getBegin());
		map.put("end", p.getEnd());	
		
		System.out.println("map값:" +map);
		List<RBoardVO> userjoin = rService.Join2(map);
		
		//이미지 업로드한거 보여주기
		for(RBoardVO vo1 :userjoin) {
			vo1.setFilevo(rService.getFileList(vo1.getRevIdx()));
		}
		System.out.println("현재페이지 글목록(list): " + userjoin.toString());
		
		
		
		model.addAttribute("rBoardList", userjoin);
		model.addAttribute("pvo", p);
/*		List<RCommentVO> list = rCmtService.joinCmt(vo.getRevIdx());
		model.addAttribute("list", list);*/
		
		return "/review/reviewBoard";
	}
	
	@PostMapping("/insertrboard")
	public String insertRBoard(RBoardVO vo, MultipartHttpServletRequest multi,
			@AuthenticationPrincipal CustomUserDetail customUser) 
					throws IllegalStateException, IOException {
		System.out.println("글 vo1 " +vo);
		
		String userId = customUser.getUsername();
		String name = customUser.getName();
		vo.setUserId(userId);
		vo.setUserName(name);
		
		
		System.out.println(">>> 글 등록 처리 - insertBoard()");
		System.out.println("글 vo " +vo);
		rService.insertBoard(vo);
		
		String path = "C:/hellopt_file/";
	
		int revIdx = vo.getRevIdx();
		System.out.println("revIdx: " + revIdx);
		/*File dir = new File(path);
		if(!dir.isDirectory()) {
			dir.mkdirs();
		}*/
		
		//넘어온 파일을 리스트로 저장
		List<MultipartFile> fileList = multi.getFiles("file_0");
		if(fileList.size() == 1 && fileList.get(0).getOriginalFilename().equals("")) {
		
		}else {//for (MultipartFile filePart : fileList)
			for (int i = 0; i < fileList.size(); i++) {
				//원본파일명
				String revFileOname = fileList.get(i).getOriginalFilename();
				String FileExtension 
						= revFileOname.substring(revFileOname.lastIndexOf("."));
				//파일명  중복되지 않게 처리 한 저장될 이름
				String saveFileName 
						= UUID.randomUUID().toString().replaceAll("-","")+FileExtension; 
				
				String savePath = path + saveFileName; //저장될 파일 경로
				System.out.println("실제 파일 이름 : " + revFileOname);
				System.out.println("저장된 파일 이름 : " + saveFileName);
				long fileSize = fileList.get(i).getSize(); //파일사이즈
				System.out.println("저장된 파일 사이즈 : " + fileSize);
				//fileList.get(i).transferTo(new File(savePath)); //파일 저장
				System.out.println("저장된 파일 경로" + savePath);
				
				System.out.println("revFileOname, saveFileName, fileSize: "+ revFileOname+ saveFileName + fileSize + revIdx);
				
				s3Utils.uploadMultipart("review/", saveFileName, fileList.get(i));
				rService.uploadFile(revFileOname, saveFileName, fileSize, revIdx);
				
				System.out.println("글정보" + vo);

			}
		}
		return "redirect:/review";
	}
	
	
	
	@RequestMapping("/review/updateform")
	public String updateBoardForm(Model model,RFileVO fvo,@RequestParam("revIdx")int revIdx) {
		System.out.println(">>> 글 수정 처리 - updateBoard()");
		
		RFileVO f = new RFileVO();
		RBoardVO userjoin = rService.Join3(revIdx);
		
		List<RFileVO> filevo = rService.getFileList(revIdx);
		//이미지 업로드한거 보여주기
		userjoin.setFilevo(filevo );

		model.addAttribute("img", f);
		model.addAttribute("rBoard", userjoin);
		model.addAttribute("fileList", filevo);
		System.out.println("수정페이지 글정보(list): " + userjoin.toString());
		
		return "review/revUpdateForm";
	}
	@PostMapping("/updaterboard")
	public String updateRBoard(@ModelAttribute("rBoard")RBoardVO vo, RFileVO fvo, Model model,
			MultipartHttpServletRequest multi,
			@AuthenticationPrincipal CustomUserDetail customUser ) 
					throws IllegalStateException, IOException{
		System.out.println(">>> 글 수정 처리 - updateBoard()");

		System.out.println("vo:" + vo.toString());
		model.addAttribute("img", fvo);

		rService.updateBoard(vo);

		String path = "C:/hellopt_file/";
		
		int revIdx = vo.getRevIdx();
		System.out.println("revIdx: " + revIdx);
		/*File dir = new File(path);
		if(!dir.isDirectory()) {
			dir.mkdirs();
		}*/

//넘어온 파일을 리스트로 저장
		List<MultipartFile> fileList = multi.getFiles("file_0");
		if(fileList.size() == 1 && fileList.get(0).getOriginalFilename().equals("")) {
		
		}else {//for (MultipartFile filePart : fileList)
			for (int i = 0; i < fileList.size(); i++) {
				//원본파일명
				String revFileOname = fileList.get(i).getOriginalFilename();
				String FileExtension 
						= revFileOname.substring(revFileOname.lastIndexOf("."));
				//파일명  중복되지 않게 처리 한 저장될 이름
				String saveFileName 
						= UUID.randomUUID().toString().replaceAll("-","")+FileExtension; 
				
				String savePath = path + saveFileName; //저장될 파일 경로
				System.out.println("실제 파일 이름 : " + revFileOname);
				System.out.println("저장된 파일 이름 : " + saveFileName);
				long fileSize = fileList.get(i).getSize(); //파일사이즈
				System.out.println("저장된 파일 사이즈 : " + fileSize);
				//fileList.get(i).transferTo(new File(savePath)); //파일 저장
				System.out.println("저장된 파일 경로" + savePath);
				
				System.out.println("revFileOname, saveFileName, fileSize: "+ revFileOname+ saveFileName + fileSize + revIdx);
				
				s3Utils.uploadMultipart("review/", saveFileName, fileList.get(i));
				rService.uploadFile(revFileOname, saveFileName, fileSize, revIdx);
				
				System.out.println("글정보" + vo);

			}
		}
		return "redirect:/review";
	}
	@RequestMapping("/deleterboard")
	public String deleteRBoard(int revIdx, Model model) {
		System.out.println(">>> 글 삭제 처리 - deleteBoard()");
		
		rService.deleteBoard(revIdx);
		return "redirect:/review";
	}
	
	@PostMapping("/imgupload")
	@ResponseBody
	public String upload(RBoardVO vo, MultipartHttpServletRequest multi) throws IllegalStateException, IOException {
		Gson gson = new Gson();
		//FolderSet set = new FolderSet();
		System.out.println(">>> 이미지 업로드 처리 - upload()");
		List<String> list = new ArrayList<String>();
		
		String path = "C:/hellopt_file/";
		File dir = new File(path);
		if(!dir.isDirectory()) {
			dir.mkdirs();
		}
		//넘어온 파일을 리스트로 저장
		List<MultipartFile> fileList = multi.getFiles("file_0");
		if(fileList.size() == 1 && fileList.get(0).getOriginalFilename().equals("")) {
		
		}else {//for (MultipartFile filePart : fileList)
			
			for (int i = 0; i < fileList.size(); i++) {
				//원본파일명
				String revFileOname = fileList.get(i).getOriginalFilename();
				String FileExtension = revFileOname.substring(revFileOname.lastIndexOf("."));
				//파일명  중복되지 않게 처리 한 저장될 이름
				String saveFileName = UUID.randomUUID().toString().replaceAll("-","")+FileExtension; 
				
				String savePath = path + saveFileName; //저장될 파일 경로
				System.out.println("실제 파일 이름 : " + revFileOname);
				System.out.println("저장된 파일 이름 : " + saveFileName);
				long fileSize = fileList.get(i).getSize(); //파일사이즈
				System.out.println("저장된 파일 사이즈 : " + fileSize);
				fileList.get(i).transferTo(new File(savePath)); //파일 저장
				System.out.println("저장된 파일 경로" + savePath);
				

			}
		}
		return "redirect:/review";
	}
	
	@PostMapping("/review/imgDel")
	@ResponseBody
	public int uploadFileDel(@RequestParam(value="revFileIdx", required = false)int revFileIdx,
			@ModelAttribute RFileVO fvo, RBoardVO vo, Model model) {
		System.out.println("컨트롤러이미지삭제:"+revFileIdx);
		rService.uploadFileDel(revFileIdx);
		
		return 1;
	}
	
	
	
}
