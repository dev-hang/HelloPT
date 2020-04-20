package com.bit.hellopt.controller.exercisetip;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;

import com.bit.hellopt.service.exercisetip.ExerciseInformationTipService;
import com.bit.hellopt.vo.exercisetip.ExerciseInformationTipListVO;
import com.bit.hellopt.vo.exercisetip.ExerciseInformationTipVO;

@Controller
@SessionAttributes("exerciseinformationtip") //exerciseInformation 라는 이름의 Model이 있으면 session에 저장
public class ExerciseInformationTipController {
	@Autowired
	private ExerciseInformationTipService exerciseInformationTipService;
	@Autowired
	private ServletContext servletContext;
	
	//메소드에 선언된 @ModelAttribute 는 리턴된 데이터를 View에 전달
	//@ModelAttribute 선언된 메소드는 @RequestMapping 메소드보다 먼저 실행됨
	//뷰에 전달될때 설정된 명칭(예: conditionMap)으로 전달
	@ModelAttribute("conditionMap")
	public Map<String, String> searchConditionMap() {
		//key: 운동팁 제목, value: TIP_TITLE
		//key: 운동팁 내용, value: TIP_CONTENT
		Map<String, String> conditionMap = new HashMap<>();
		conditionMap.put("운동팁 제목", "TIP_TITLE");
		conditionMap.put("운동팁 내용", "TIP_CONTENT");
		
		return conditionMap;
	}

	//리턴타입 ModelAndView -> String 변경해서 리턴타입 통일
	//전달할 데이터 저장타입  ModelAndView -> Model
	@RequestMapping("/exerciseinfotiplist")
	//post인지 getMapping인지 써줘야함
	public String getExerciseInformationTipList(ExerciseInformationTipVO vo, Model model) {
		System.out.println(">>> 글 전체 목록 조회 처리-getExerciseInformationTipList()");
		System.out.println("condition : " + vo.getSearchCondition());
		System.out.println("keyword : " + vo.getSearchKeyword());
		
		//null 체크 후 초기값 설정
		if (vo.getSearchCondition() == null) {
			vo.setSearchCondition("TIP_TITLE");
		}
		if (vo.getSearchKeyword() == null) {
			vo.setSearchKeyword("");
		}
		System.out.println("null처리후 condition : " + vo.getSearchCondition());
		System.out.println("null처리후 keyword : -" + vo.getSearchKeyword() + "-");
		//List<ExerciseInformationTipVO> exerciseInformationTipList = exerciseInformationTipDAO.getExerciseInformationTipList();
		//List<ExerciseInformationTipVO> exerciseInformationTipList = exerciseInformationTipDAO.getExerciseInformationTipList(vo);
		List<ExerciseInformationTipVO> exerciseInformationTipList = exerciseInformationTipService.getExerciseInformationTipList(vo);
		model.addAttribute("exerciseInformationTipList", exerciseInformationTipList);
		
		return "exercise/exerciseInfoTipList";
	}
	
	//리턴타입 ModleAndView -> String 변경 통일
	//전달할 데이타 저장타입 : ModleAndView -> Model
	@RequestMapping("exerciseinfotip")
	public String getExerciseInformationTip(ExerciseInformationTipVO vo, Model model) {
		System.out.println(">>> 글 상세 조회 처리 - getExerciseInformationTip()");
		
		ExerciseInformationTipVO exerciseInformationTip = exerciseInformationTipService.getExerciseInformationTip(vo);
		//model.addAttribute(exerciseInformationTip); //exerciseInformationTipVO
		model.addAttribute("exerciseInformationTip", exerciseInformationTip); //데이터 저장
		System.out.println("> exerciseInformationTip : " + exerciseInformationTip);
		
		return "exercise/exerciseInfoTip";
	}
	
	@Value("${file.directory}")
	private String fileDirectory;
	
	@GetMapping("/exercise/tipform") 
	public String uploadForm() {
		return "exercise/insertExerciseInformationTip";
	}
	
	@GetMapping("insertexerciseinformationtipform")
	public String form() {
		return "exercise/insertExerciseInformationTipform";
	}
	
	@RequestMapping("insertexerciseinformationtip")
	public String insertExerciseInformationTip(ExerciseInformationTipVO vo, @RequestParam("tipPictures") MultipartFile file, HttpServletRequest request,
			Model model) throws IllegalStateException, IOException {
		System.out.println(">>> 글 등록 처리 - insertExerciseInformationTip()");
		
		//@RequestParam("file")이었었음
		
		/* *** 파일 업로드 처리 ********
		 * MultipartFile 인터페이스 주요 메소드
		 * String getOriginalFilename() : 업로드한 파일명 찾기
		 * void transferTo(File destFile) : 업로드한 파일을 destFile에 저장
		 * boolean isEmpty() : 업로드한 파일의 존재여부(없으면 true 리턴)
		 */
		String rootDirectory = request.getSession().getServletContext().getRealPath("/");
		Path path = Paths.get(rootDirectory + fileDirectory + file.getOriginalFilename());
		
			try {
				file.transferTo(new File(servletContext.getRealPath("/resources/images/") + file.getOriginalFilename())); //여기 바꿔봐
				System.out.println("filepath" + servletContext.getRealPath("resources/images/") + file.getName() + file.getContentType());
				//file.transferTo(new File(rootDirectory + "/resources/images")); //여기 바꿔봐
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException("File saving failed", e);
			}
			System.out.println("파일업로드테스트 : " + file.getOriginalFilename());
			model.addAttribute("filename", file.getOriginalFilename());
			vo.setTipPictures(file.getOriginalFilename());
			
			exerciseInformationTipService.insertExerciseInformationTip(vo);
			return "redirect:/exerciseInfoTipList";

			//여기서 UTF-8설정을 해줘야 글씨가 안깨짐~!
		}
		
//		MultipartFile uploadFile = vo.getUploadFile();
//		System.out.println("uploadFile : " + uploadFile);
//		System.out.println("request : " + request.getParameter("exerciseName"));
//		
//		if (uploadFile == null) {
//			return "insertExerciseInformation";
//		}
//		
//		if (!uploadFile.isEmpty()) {//파일이 있으면(비어있지 않으면)
//			String fileName = uploadFile.getOriginalFilename();
//				uploadFile.transferTo(new File("c:/MyStudy/temp/" + fileName));
//		}
//		exerciseInformationService.insertExerciseInformation(vo);
//		return "getExerciseInformationList";
//			
		//여기 내용 수정할것~!~!!!!
		//일단 정보넣는것 먼저 하고 나서 파일 넣는 순서로 진행되어야함
	
	//@ModelAttribute("exerciseInformation") : Model에 exerciseInformation 라는 속성명의 객체 있으면 사용
	//    없으면 exerciseInformation라는 이름으로 새로 생성 
	@RequestMapping("/updateexerciseinfotip")
	public String updateExerciseInformationTip(@ModelAttribute("exerciseInformationTip") ExerciseInformationTipVO vo) {
		System.out.println(">>> 글 수정 처리 - updateExerciseInformationTip()");
		System.out.println("> exerciseInformationTip vo : " + vo);
		
		exerciseInformationTipService.updateExerciseInformationTip(vo);
		return "exercise/exerciseInfoTipList";
	}
	
	@RequestMapping("/deleteexerciseinformationtip")
	public String deleteExerciseInformationTip(ExerciseInformationTipVO vo) {
		System.out.println(">>> 글 삭제 처리 - deleteExerciseInformationTip()"); 
		
		exerciseInformationTipService.deleteExerciseInformationTip(vo);
		return "exercise/exerciseInfoTipList";
	}
	
	//---------------------------------
	@RequestMapping("/datatransform/tipform")
	@ResponseBody //응답객체의 몸체 담아서 전달
	public ExerciseInformationTipListVO dataTransform(ExerciseInformationTipVO vo) {
		System.out.println(">>> dataTransform() 실행(XML)");
		vo.setSearchCondition("TIP_TITLE");
		vo.setSearchKeyword("");
		
		List<ExerciseInformationTipVO> exerciseInformationTipList = exerciseInformationTipService.getExerciseInformationTipList(vo); //여기까진 맞음
		ExerciseInformationTipListVO exerciseInformationTipListVO = new ExerciseInformationTipListVO();
		exerciseInformationTipListVO.setExerciseInformationTipList(exerciseInformationTipList);
	
		return exerciseInformationTipListVO;
	}
	
	
}
