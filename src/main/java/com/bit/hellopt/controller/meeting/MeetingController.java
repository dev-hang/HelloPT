package com.bit.hellopt.controller.meeting;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.bit.hellopt.commons.utils.S3Utils;
import com.bit.hellopt.service.meeting.MeetingService;
import com.bit.hellopt.vo.meeting.CategoryCodeVO;
import com.bit.hellopt.vo.meeting.LocalVO;
import com.bit.hellopt.vo.meeting.MeetingFileVO;
import com.bit.hellopt.vo.meeting.MeetingVO;

@Controller
public class MeetingController {
	
	/*
	 로그 쓸 때
	private static final Logger logger = LoggerFactory.getLogger(MeetingController.class);
	logger.info("meeting controller get mapping");
	 */
	@Autowired
	MeetingService service;
	
	@Autowired
	S3Utils s3Utils;
	
	@RequestMapping("/meeting")
	public String meeting(Principal principal , Model model) {
		//              저장 할 이름
		List<MeetingVO> meetingList = service.getMeetingVO();
		System.out.println("getMeetingList 성공");
		
		for (MeetingVO vo : meetingList) {
			vo.setMeetingFileVO(service.getMeetingOneFiles(vo.getMeetingIdx()));
		}
		
		model.addAttribute("meetingList", meetingList);
		return "meeting/meeting";
	}
	
	@RequestMapping("/searchKeyword")
	@ResponseBody
	public List<MeetingVO> getSearch(Model model, String searchKeyword) {
		System.out.println("searchKeyword : " + searchKeyword);
		
		List<MeetingVO> getSearch = service.getSearch(searchKeyword);
		
		
		for (MeetingVO vo : getSearch) {
			vo.setMeetingFileVO(service.getMeetingOneFiles(vo.getMeetingIdx()));
		}
		
		System.out.println("getSearch : " + getSearch);
		model.addAttribute("getSearch",getSearch);
		
		return getSearch;
	}
	
	@RequestMapping("/meetingListAjax")
	@ResponseBody
	public List<MeetingVO> getSearch() {
		
		List<MeetingVO> meetingList = service.getMeetingVO();
		System.out.println("getMeetingList 성공");
		
		for (MeetingVO vo : meetingList) {
			vo.setMeetingFileVO(service.getMeetingOneFiles(vo.getMeetingIdx()));
		}
		
		return meetingList;
	}
	
	@RequestMapping("/admin/meetingAdmin")
	public String meetingAdmin(Principal principal , Model model) {
		//              저장 할 이름
		List<MeetingVO> meetingList = service.getMeetingVO();
		System.out.println("getMeetingAdminList 성공");
		
		model.addAttribute("meetingList", meetingList);
		return "meeting/meetingAdmin";
	}
	
	@RequestMapping("/admin/progressY")
	public String updateProgressY(MeetingVO meetingVO) {
		service.updateProgressY(meetingVO);
		System.out.println("progressY 성공");
		
		return "redirect:/admin/meetingAdmin";
	}
	@RequestMapping("/admin/progressN")
	public String updateProgressN(MeetingVO meetingVO) {
		service.updateProgressN(meetingVO);
		System.out.println("progressN 성공");
		
		return "redirect:/admin/meetingAdmin";
	}
	
	
	@RequestMapping("/downloadFile")
	public void downloadFile(MeetingFileVO meetingFileVO, HttpServletResponse response) throws Exception {
		// http://localhost:8000/hellopt/downloadFile?mSysImg=ff0ca98a-bfa0-4094-abae-8793a628c55d.jpg
		String mOriImg = meetingFileVO.getmOriImg();
		String mSysImg = meetingFileVO.getmSysImg();
		System.out.println("다운로드s:"+mSysImg);
		
		FileInputStream fileInputStream = null;
		ServletOutputStream servletOutputStream = null;
		//byte[] fileByte = FileUtils.readFileToByteArray(new File("C:\\hellopt_file\\"+mSysImg));
		
		//파일 응답 ---------
		response.setContentType("application/octet-stream"); //클릭했을 때 미디어 타입의 실행/다운 하게 해주는...
		//response.setContentLength(fileByte.length);  //파일 길이 뽑는거 
		response.setHeader("Content-Disposition", "attachment; fileName=\"" + URLEncoder.encode(mSysImg,"UTF-8")+"\";"); //해당 파일명 인코딩
		response.setHeader("Content-Transfer-Encoding", "binary"); //파일 인코딩 형식 = binary
		//response.getOutputStream().write(fileByte);
		
		//서버에 있는 파일 읽기-----------
		fileInputStream = new FileInputStream(new File("C:\\hellopt_file\\"+mSysImg)); // 내 하드의 이미지를 읽어 달라고 요청
		servletOutputStream = response.getOutputStream();

		byte b[] = new byte[1024];
		int data = 0;

		while ((data = (fileInputStream.read(b, 0, b.length))) != -1) { //date(길이,용랑으로 확인)가 있으면
			servletOutputStream.write(b, 0, data);                   //내보내 달라
		}
		
		servletOutputStream.flush();
		
		//닫기----------
		if (servletOutputStream != null) {
			try {
				servletOutputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if (fileInputStream != null) {
			try {
				fileInputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		
	}
	
	@RequestMapping("/meetingOne")
	public String meetingOne(Principal principal, Model model, int meetingIdx, String fkUserId, MeetingVO meetingVO) {
		//상세리스트
		MeetingVO meetingOne = service.getMeetingOne(meetingIdx);
		MeetingVO resCount = service.resCount(meetingIdx);
		
		Map<String, Object> hm = new HashMap<>();
		
		fkUserId = principal.getName();
		
		hm.put("fkUserId", fkUserId);
		hm.put("meetingIdx", meetingIdx);
		System.out.println(hm);
		MeetingVO resUser = service.resUser(hm);
		System.out.println("resUser : " + resUser);
		
		
		//상세리스트에서 파일 슬라이드
		List<MeetingFileVO> MeetingOneFile = service.getMeetingOneFiles(meetingIdx);
		meetingOne.setMeetingFileVO(MeetingOneFile);
		
		//상세리스트 하단 많이 본 모임
		List<MeetingVO> meetingCnt = service.getMeetingCnt();
		
		for (MeetingVO vo : meetingCnt) {
			// 파일없는 vo 안에 fileVO 셋팅 ( fileVO = list vo의 idx로 파일 이미지 전부 소환하기 )
			vo.setMeetingFileVO(service.getMeetingOneFiles(vo.getMeetingIdx()));
		}
		
		//클릭수 증가
		service.clickCount(meetingVO);
		
		System.out.println("getMeetingOne 성공");
		model.addAttribute("meetingOne", meetingOne);
		model.addAttribute("resCount", resCount);
		//model.addAttribute("meetingOneFile", MeetingOneFile);
		model.addAttribute("meetingCnt", meetingCnt);
		model.addAttribute("resUser", resUser);
		return "meeting/meetingOne";
	}
	

	@RequestMapping("/meetingWrite")
	public String meetingWrite(Principal principal,  Model model) {
		
		List<LocalVO> localList = service.getLocalVO();
		List<CategoryCodeVO> categoryList = service.getCategoryCodeVO();
		System.out.println("getMeetingWrite 성공");
		
		model.addAttribute("localList", localList);
		model.addAttribute("categoryList", categoryList);
		
		return "meeting/meetingWrite";
	}

	@PostMapping("/meetingWriteOk")
	public String meetingWriteOk(Principal principal, MeetingVO meetingVO, MeetingFileVO meetingFileVO, MultipartHttpServletRequest mhsq) throws Exception, IllegalStateException, IOException {
		
		service.insertMeeting(meetingVO);
		
		meetingVO.setMeetingIdx(meetingVO.getMeetingIdx());
		meetingFileVO.setFkMeetingIdx(meetingVO.getMeetingIdx());
		
		service.insertMaxMeeting(meetingVO);
		service.insertConsentYn(meetingVO);
		
		System.out.println("getMeetingOk 성공");
		System.out.println("업로드 전:"+meetingVO.getMeetingIdx());
		
		//다중파일 업로드 처리
		String realFolder = "C:/hellopt_file/";
		/* 폴더생성하는거
		File dir = new File(realFolder);
		if (!dir.isDirectory()) {
			dir.mkdirs();
		}
		*/
		//넘어온 파일을 리스트로 저장
		List<MultipartFile> mf = mhsq.getFiles("uploadFile");
		if (mf.size() == 1 && mf.get(0).getOriginalFilename().equals("")) {
		} else {
			for (int i = 0; i < mf.size(); i++) {
				//파일 중복명 처리
				String genId = UUID.randomUUID().toString();
				// 본래 파일명
				String mOriImg = mf.get(i).getOriginalFilename();
				//저장되는 파일 이름
				String mSysImg = genId + "." + FilenameUtils.getExtension(mOriImg);
				System.out.println("확장자 뽑기 : "+ FilenameUtils.getExtension(mOriImg));
				//저잘 될 파일 경로
				String filePath = realFolder + mSysImg;
				//파일 저장
				//mf.get(i).transferTo(new File(filePath));
				s3Utils.uploadMultipart("meeting/", mSysImg, mf.get(i));
				
				System.out.println("업로드 파일:"+meetingVO.getMeetingIdx());
				
				service.insertMeetingFiles(meetingFileVO.getFkMeetingIdx(), mOriImg, mSysImg, filePath);
				System.out.println("insertFiles 성공");
			}
		}
		
		
		
		return "redirect:/meeting";
	}
	
	@RequestMapping("/meetingRes")
	public void meetingUpdate(Principal principal, MeetingVO meetingVO, HttpServletResponse response) throws Exception {
		
		System.out.println("res : "+meetingVO);
		int idx = meetingVO.getMeetingIdx();
		service.insertReservationMeeting(meetingVO);
		
		response.sendRedirect("meetingOne?meetingIdx="+idx);
		
	//return "redirect:/meetingOne";
	
	}
	
	@RequestMapping("/meetingUpdate")
	public String meetingUpdate(Principal principal, Model model, int meetingIdx) {
		
		MeetingVO meetingOne = service.getMeetingOne(meetingIdx);
		List<MeetingFileVO> MeetingOneFile = service.getMeetingOneFiles(meetingIdx);
		System.out.println("getMeetingUpdate 성공");
	
		List<LocalVO> localList = service.getLocalVO();
		List<CategoryCodeVO> categoryList = service.getCategoryCodeVO();
		
		model.addAttribute("localList", localList);
		model.addAttribute("categoryList", categoryList);
		model.addAttribute("meetingOne", meetingOne);
		model.addAttribute("meetingOneFile", MeetingOneFile);				
	
	return "meeting/meetingUpdate";
	}
	
	@PostMapping("/meetingUpdateOk")
	public void meetingUpdateOk(Principal principal, MeetingVO meetingVO, int meetingIdx, HttpServletResponse response) throws Exception {
		String sessionId = principal.getName();
		int idx = service.getMeetingOne(meetingIdx).getMeetingIdx();
		String loginId = service.getMeetingOne(meetingIdx).getFkUserId();
		
		if ( sessionId.equals(loginId) ) {
		
		service.updateMeetingOk(meetingVO);
		service.updateMaxMeeting(meetingVO);
		System.out.println("getmeetingUpdateOk 성공");
		}  else {
		    response.setContentType("text/html; charset=UTF-8");
		    response.getWriter().println("<script>alert(' 삭제 권한이 없습니다 '); </script>");
			response.sendRedirect("meetingOne?meetingIdx="+idx);
			
			//return "redirect:/meetingOne?meetingIdx="+idx;
		}
		
		//return "redirect:/meeting";
	}
	
	@RequestMapping("/meetingDelete")
	public void meetingDelete(Principal principal, int meetingIdx, HttpServletResponse response ,HttpServletRequest request) throws Exception {
		
		String sessionId = principal.getName();
		int idx = service.getMeetingOne(meetingIdx).getMeetingIdx();
		String loginId = service.getMeetingOne(meetingIdx).getFkUserId();
		
		System.out.println(sessionId);
		System.out.println(loginId);
		
		if ( sessionId.equals(loginId) ) {
			service.deleteMeeting(meetingIdx);
			System.out.println("getMeetingDelete 성공");
			response.sendRedirect("/meeting");
		
		} else {
			
		    response.setContentType("text/html; charset=UTF-8");
		    response.getWriter().println("<script>alert(' 삭제 권한이 없습니다 '); </script>");
			response.sendRedirect("meetingOne?meetingIdx="+idx);
			
			//return "redirect:/meetingOne?meetingIdx="+idx;
		}
		//return "redirect:/meeting";
	}
	@RequestMapping("/resCancle")
	public void resCancle(Principal principal, int meetingIdx, String fkUserId, MeetingVO meetingVO, HttpServletResponse response) throws Exception {
		
		Map<String, Object> hm = new HashMap<>();
		
		fkUserId = principal.getName();
		int idx = service.getMeetingOne(meetingIdx).getMeetingIdx();
		
		hm.put("fkUserId", fkUserId);
		hm.put("meetingIdx", meetingIdx);
		System.out.println(hm);
		service.resCancle(hm);
		
		//return "redirect:/meetingOne";
		response.sendRedirect("meetingOne?meetingIdx="+idx);
	}
}