package com.bit.hellopt.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bit.hellopt.service.liveclass.ClassMemberService;
import com.bit.hellopt.service.liveclass.LiveClassService;
import com.bit.hellopt.service.liveclass.LiveService;
import com.bit.hellopt.vo.live.ClassMember;
import com.bit.hellopt.vo.live.LiveClass;

@Controller
public class ClassController {
	
	@Autowired
	LiveClassService service;
	@Autowired
	ClassMemberService service2;
	@Autowired
	LiveService service3;
	
	//============ 강의 리스트 =============
	//강의 개설
	@PostMapping("/openclass")
	public String insertClass(LiveClass info) {
		service.insertClass(info);
		System.out.println("라이브 클래스 생성 성공!!");
		return "redirect:/classlist";
	}
	
	//강의 리스트 전체 조회
	@RequestMapping("/classlist")
	public String getLiveClass(Model model, ClassMember info, Principal principal, String classType) {
		
		//강의 유형 구분을 위해 타입(일대다/다대다) 저장
		model.addAttribute("classType", classType);
		
		//강의 리스트 전체 조회
		List<LiveClass> liveClassList = service.getLiveClass(classType);
		System.out.println("라이브 클래스 정보 가져오기 성공!!");
		model.addAttribute("liveClassList", liveClassList);
		
		info.setFkUserId(principal.getName());
		
		//강의 신청자에게만 버튼 생성하기 위해 로그인 아이디의 신청 강의 조회
		List<ClassMember> classMember = service2.getMyClass(info);
		System.out.println("클래스 멤버 정보 가져오기 성공!!");
		System.out.println("classMember : " + classMember);
		model.addAttribute("classMember", classMember);
		
		for(int i=0; i<classMember.size(); i++) {
			int classMemberIdx = classMember.get(i).getClassMemberIdx();
			System.out.println(classMemberIdx);
		}
		
		//강의 추천을 위해 랜덤으로 강의 조회
		LiveClass liveClass = service3.randomClass();
		model.addAttribute("liveClass", liveClass);
		System.out.println("liveClass: " + liveClass);
		
		System.out.println("classType: " + classType);
		System.out.println("..." + liveClassList);
		
		return "class/classList";
	}
	
	//============ 강의 상세정보 ============
	//하나의 강의 조회
	@RequestMapping("/classdetail")
	public String getClassDetail(int classIdx, Model model, Principal principal, ClassMember info) {
		
		//인덱스번호로 강의 상세정보 조회
		LiveClass classDetail = service.getClassDetail(classIdx);
		System.out.println("라이브 클래스 상세정보 가져오기 성공!!");
		model.addAttribute("classDetail", classDetail);
		
		info.setFkUserId(principal.getName());
		info.setFkClassIdx(classIdx);
		
		//강의 신청 여부 조회 - 이미 신청한 경우 : 1 / 신청하지 않은 경우 : 0
		int result = service2.getRegInfo(info);
		model.addAttribute("result", result);
		
		//강의 신청 인원 초과여부 조회
		//	- 정해진 인원
		int total = classDetail.getTotalMembers();
		model.addAttribute("total", total);
		//	- 신청한 인원
		int cnt = service2.getMemberCnt(classIdx);
		model.addAttribute("cnt", cnt);

		System.out.println("cnt : " + cnt);
		System.out.println("total : " + total);
		
		return "class/classDetail";
	}
	
	//강의 삭제
	@RequestMapping("/deleteclass")
	public String deleteClass(int classIdx) {
		service.deleteClass(classIdx);
		System.out.println("라이브 클래스 삭제 성공!!");
		return "redirect:/classlist";
	}
	
	//강의 수정 페이지 이동
	@RequestMapping("/updateclass")
	public String updateClass(int classIdx, Model model) {
		LiveClass classDetail = service.getClassDetail(classIdx);
		System.out.println("수정하기 위한 라이브 클래스 상세정보 가져오기 성공!!");
		model.addAttribute("classDetail", classDetail);
		return "class/classUpdate";
	}
	
	//강의 수정
	@RequestMapping("/updateclassok")
	public String classUpdate(LiveClass info) {
		service.updateClass(info);
		System.out.println("라이브 클래스 수정 성공!!");
		return "redirect:/classdetail?classIdx=" + info.getClassIdx();
	}
	
	//강의 신청
	@RequestMapping("/regclass")
	public String insertClassMember(ClassMember info, Principal principal) {
		//로그인 아이디를 VO에 setter로 저장
		info.setFkUserId(principal.getName());
		System.out.println(info.getFkUserId());
		
		service2.insertClassMember(info);
		System.out.println("강의 신청 성공!!");
		
		return "redirect:/classdetail?classIdx=" + info.getFkClassIdx();
	}
	
	//강의 신청 취소
	@RequestMapping("/dropclass")
	public String deleteClassMember(ClassMember info, Principal principal) {
		System.out.println("로그인 아이디 : " + principal.getName());
		info.setFkUserId(principal.getName());
		
		service2.deleteClassMember(info);
		System.out.println("강의 신청 취소 성공!!");
		
		return "redirect:/classdetail?classIdx=" + info.getFkClassIdx();
	}
	
	
	//========= 스트리밍 페이지에 강의 제목 가져오기 ==========
	@RequestMapping("/broadcaster")
	public String getClassName(int classIdx, Model model) {
		String className = service3.getClassName(classIdx);
		System.out.println("강의 제목 가져오기 성공^^");
		
		//서버에 룸 아이디로 전달할 인덱스 번호 저장
		model.addAttribute("classIdx", classIdx);
		model.addAttribute("className", className);
		return "class/broadcaster";
	}

	@RequestMapping("/viewer")
	public String getClassName2(int classIdx, Model model) {
		String className = service3.getClassName(classIdx);
		System.out.println("강의 제목 가져오기 성공^^");
		
		model.addAttribute("classIdx", classIdx);
		model.addAttribute("className", className);
		return "class/viewer";
	}
	
	@RequestMapping("/multi")
	public String getClassName3(int classIdx, Model model) {
		String className = service3.getClassName(classIdx);
		System.out.println("강의 제목 가져오기 성공^^");
		
		model.addAttribute("classIdx", classIdx);
		model.addAttribute("className", className);
		return "class/multi";
	}
	
	//============ 더보기 ============
	@RequestMapping("/moreclass")
	@ResponseBody
	public List<LiveClass> getClassList(Model model, ClassMember info, Principal principal,
			@RequestParam(name = "end", defaultValue = "3") int end) {
		
		//조회 범위를 나타내는 end값을 전달받아 end-2부터 end까지의 데이터를 조회
		System.out.println("end : " + end);
		List<LiveClass> liveList = service3.moreClassList(end);
		
		//강의 신청자에게만 버튼 생성하기 위해 로그인 아이디의 신청 강의 조회
		info.setFkUserId(principal.getName());
		List<ClassMember> classMember = service2.getMyClass(info);
		model.addAttribute("classMember", classMember);

		return liveList;
	}
	
	//=========== 관리자 페이지 ==============
	@RequestMapping("/manageclass")
	public String getLiveClass(Model model, String classType) {
		List<LiveClass> liveClassList = service.getLiveClass(classType);
		System.out.println("라이브 클래스 정보 가져오기 성공!!");
		model.addAttribute("liveClassList", liveClassList);
		return "class/manageClass";
	}
}
