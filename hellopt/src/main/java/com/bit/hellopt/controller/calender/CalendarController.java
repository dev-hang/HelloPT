package com.bit.hellopt.controller.calender;

import java.io.IOException;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

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
import com.bit.hellopt.service.calender.CalendarService;
import com.bit.hellopt.vo.calendar.CalendarVO;

@Controller
public class CalendarController {
	
	@Autowired
	CalendarService service;
	
	@Autowired
	S3Utils s3Utils;
	
	@RequestMapping("/calender")
	public String calender(Principal principal, Model model, CalendarVO calendarVO) {
		
		List<CalendarVO> calendarList = service.getCalendar();
		
		System.out.println("calList : " + calendarList);
		
		model.addAttribute("calendarList", calendarList);
		return "calender/calender";
	}
	
	@RequestMapping("/calendarOne")
	public String calenderRead(Principal principal, Model model, CalendarVO calendarVO, int calendarIdx) {
		
		CalendarVO oneCalendar = service.getOneCalendar(calendarIdx);
		
		model.addAttribute("oneCalendar", oneCalendar);
		return "calender/calendarOne";
	}
	
	
	@RequestMapping("/calenderWrite")
	public String calenderWrite(Principal principal) {
		
		return "calender/calenderWrite";
	}
	
	
	@PostMapping("/calenderWriteOk")
	public String calenderWriteOk(Principal principal, String fkUserId, String content, MultipartHttpServletRequest mhsq) throws Exception, IllegalStateException, IOException {
		System.out.println("캘린더 컨트롤러 탐");
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
				String cOriVideo = mf.get(i).getOriginalFilename();
				//저장되는 파일 이름
				String cSysVideo = genId + "." + FilenameUtils.getExtension(cOriVideo);
				System.out.println("확장자 뽑기 : "+ FilenameUtils.getExtension(cOriVideo));
				//저잘 될 파일 경로
				String filePath = realFolder + cSysVideo;
				//파일 저장
				//mf.get(i).transferTo(new File(filePath));
				s3Utils.uploadMultipart("calendar/", cSysVideo, mf.get(i));
				
				System.out.println("캘린더 멀티파트 탐");
				
				HashMap<String, Object> hm = new HashMap<>();
				hm.put("fkUserId", fkUserId);
				hm.put("content", content);
				hm.put("cOriVideo", cOriVideo);
				hm.put("cSysVideo", cSysVideo);
				hm.put("filePath", filePath);
				service.insertCalVideo(hm);
				
				System.out.println("캘린더 입력 : " + fkUserId + "/" +  content + "/" + cOriVideo + "/" + cSysVideo + "/" + filePath);
			}
		}
				
		
		return "calender/calender";
	}
	
	
	@RequestMapping("/calendarUpdate")
	public String calenderUpdate(Principal principal,Model model, CalendarVO calendarVO,int calendarIdx) {
		
		CalendarVO oneCalendar = service.getOneCalendar(calendarIdx);
		
		
		model.addAttribute("oneCalendar", oneCalendar);
		return "calender/calenderUpdate";
	}
	
	@PostMapping("/calendarUpdateOk")
	public String calenderUpdateOk(Principal principal,Model model, int calendarIdx, String content, MultipartHttpServletRequest mhsq) throws Exception, IllegalStateException, IOException {
		
		
		String realFolder = "C:/hellopt_file/";
		
		List<MultipartFile> mf = mhsq.getFiles("uploadFile");
		if (mf.size() == 1 && mf.get(0).getOriginalFilename().equals("")) {
		} else {
			for (int i = 0; i < mf.size(); i++) {
				//파일 중복명 처리
				String genId = UUID.randomUUID().toString();
				// 본래 파일명
				String cOriVideo = mf.get(i).getOriginalFilename();
				//저장되는 파일 이름
				String cSysVideo = genId + "." + FilenameUtils.getExtension(cOriVideo);
				System.out.println("확장자 뽑기 : "+ FilenameUtils.getExtension(cOriVideo));
				//저잘 될 파일 경로
				String filePath = realFolder + cSysVideo;
				//파일 저장
				//mf.get(i).transferTo(new File(filePath));
				s3Utils.uploadMultipart("calendar/", cSysVideo, mf.get(i));
				
				System.out.println("캘린더 멀티파트 탐");
				
				HashMap<String, Object> hm = new HashMap<>();
				hm.put("calendarIdx", calendarIdx);
				hm.put("content", content);
				hm.put("cOriVideo", cOriVideo);
				hm.put("cSysVideo", cSysVideo);
				hm.put("filePath", filePath);
				service.updateCalVideo(hm);
				
				System.out.println("캘린더 수정 : " +   content + "/" + cOriVideo + "/" + cSysVideo + "/" + filePath);
			}
		}
		
		return "calender/calender";
	}
	
	@RequestMapping("/calendarDelete")
	public String calendarDelete(int calendarIdx) {
		
		service.deleteCalVideo(calendarIdx);
		
		return "calender/calender";
	}
	
	@RequestMapping("/selectMonth")
	@ResponseBody // @ResponseBody 객체의 몸체(body)에 데이터 전달 (크롬브라우저만 가능)
	public List<CalendarVO> selectMonth(String month, String fkUserId) {
		
		Map<String, String> hm = new HashMap<>();
		
		hm.put("month", month);
		hm.put("fkUserId", fkUserId);
		
		List<CalendarVO> calenderList = service.getMonthList(hm);
		
		return calenderList;
	}
}
