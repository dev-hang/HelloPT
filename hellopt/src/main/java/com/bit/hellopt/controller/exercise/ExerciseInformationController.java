 package com.bit.hellopt.controller.exercise;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.bit.hellopt.commons.utils.S3Utils;
import com.bit.hellopt.service.exercise.ExerciseInformationService;
import com.bit.hellopt.vo.exercise.ExerciseInformationListVO;
import com.bit.hellopt.vo.exercise.ExerciseInformationPaging;
import com.bit.hellopt.vo.exercise.ExerciseInformationVO;


@Controller
@SessionAttributes("exerciseinformation") //exerciseInformation 라는 이름의 Model이 있으면 session에 저장 html과 연관됨
public class ExerciseInformationController {
	@Autowired
	ExerciseInformationService exerciseInformationService;
	@Autowired
	S3Utils s3Utils;
	@Autowired
	ServletContext servletContext;
	
	public ExerciseInformationController(ExerciseInformationService exerciseInformationService) {
		this.exerciseInformationService = exerciseInformationService;
	}
	
	//메소드에 선언된 @ModelAttribute 는 리턴된 데이터를 View에 전달
	//@ModelAttribute 선언된 메소드는 @RequestMapping 메소드보다 먼저 실행됨
	//뷰에 전달될때 설정된 명칭(예: conditionMap)으로 전달
	@ModelAttribute("conditionMap")
	public Map<String, String> searchConditionMap() {
		//key: 운동이름, value: EXERCISE_NAME
		//key: 운동부위, value: EXERCISE_PARTS
		Map<String, String> conditionMap = new HashMap<>();
		conditionMap.put("운동이름", "EXERCISE_NAME");
		conditionMap.put("운동부위", "EXERCISE_PARTS");
		
		return conditionMap;
	}

	//리턴타입 ModelAndView -> String 변경해서 리턴타입 통일
	//전달할 데이터 저장타입  ModelAndView -> Model
	@RequestMapping("/exerciseinfolist")
	public String getExerciseInformationList(ExerciseInformationVO vo, Model model, 
			  @RequestParam(defaultValue="1") Integer cPage
			, @RequestParam(required = false) String searchKeyword
			, @RequestParam(required = false) String searchCondition
			
			) {
		
		Map<String, Object> formMap = new HashMap<String,Object>();
		
		formMap.put("searchCondition", searchCondition);
		formMap.put("searchKeyword", searchKeyword);
		
		//페이지 처리를 위한 Paging 객체 생성해서 값 설정
		ExerciseInformationPaging p = new ExerciseInformationPaging();
		
		//1. 전체 게시물의 수를 구하기
		p.setTotalRecord(exerciseInformationService.getExerciseTotalCount(formMap)); 
		p.setTotalPage(); //전체 페이지 갯수 구하기
		
		System.out.println(">전체 게시글 수 : " + p.getTotalRecord());
		System.out.println(">전체 페이지 수 : " + p.getTotalPage());
		
		//2. 현재 페이지 구하기(default : 1)
		
		if (cPage != null) {//넘겨받은 페이지 값이 있으면
			p.setNowPage(cPage);
		}
		
		//3. 현재페이지의 시작번호(begin)와 끝번호(end) 구하기
//		p.setEnd(p.getNowPage() * p.getNumPerPage());
//		p.setBegin(p.getEnd() - p.getNumPerPage() + 1);
		
		p.setBegin((p.getNowPage() -1 ) * p.getNumPerPage() + 1);
		p.setEnd((p.getBegin() - 1) + p.getNumPerPage());
		
		System.out.println("-------------");
		System.out.println(">>현재 페이지 : " + p.getNowPage());
		System.out.println(">>시작번호(begin) : " + p.getBegin());
		System.out.println(">>끝번호(end) : " + p.getEnd());
		
		//------- 블록(block) 계산하기 ----------
		//4. 블록의 시작페이지, 끝페이지 구하기(현재페이지 번호 사용)
		int nowPage = p.getNowPage();
		int beginPage = (nowPage - 1) / p.getPagePerBlock() * p.getPagePerBlock() + 1;
		p.setBeginPage(beginPage);
		p.setEndPage(p.getBeginPage() + p.getPagePerBlock() - 1);
		
		//4-1 끝페이지(endPage)가 전체 페이지 수(totalPage) 보다 크면
		if (p.getEndPage() > p.getTotalPage()) {
			p.setEndPage(p.getTotalPage());
		}

		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("begin", p.getBegin());
		map.put("end", p.getEnd());
		
		
		System.out.println(">>> 글 전체 목록 조회 처리-getExerciseInformationList()");
		System.out.println("condition : " + vo.getSearchCondition());
		System.out.println("keyword : " + vo.getSearchKeyword());
		
		//null 체크 후 초기값 설정
		if (vo.getSearchCondition() == null) {
			vo.setSearchCondition("EXERCISE_NAME");
		}
		if (vo.getSearchKeyword() == null) {
			vo.setSearchKeyword("");
		}
		System.out.println("null처리후 condition : " + vo.getSearchCondition());
		System.out.println("null처리후 keyword : -" + vo.getSearchKeyword() + "-");
		
		formMap.put("begin", p.getBegin());
		formMap.put("end", p.getEnd());
		
		List<ExerciseInformationVO> exerciseInformationList = exerciseInformationService.getExerciseInformationSearch(formMap);
		
		for(ExerciseInformationVO evo : exerciseInformationList) {
			evo.setExercisePictures(evo.getExercisePictures().split(",")[0]);
		}
		
		System.out.println("exerciseInformationList: " + exerciseInformationList.toString());
		model.addAttribute("exerciseInformationList", exerciseInformationList);
		model.addAttribute("pvo", p);
		model.addAttribute("cPage", cPage);
		
		
		/*ExerciseInformationVO exerciseInformation = exerciseInformationService.getExerciseInformation(vo);
		
		exerciseInformation.splitExercisePicturesList();
		for(String str : exerciseInformation.getExercisePicturesList()) {
			System.out.println("이미지: " + str);
		}
		
		model.addAttribute("exerciseInformation", exerciseInformation);
		System.out.println("exerciseInformation에서 가져온 첫번째 이미지 경로 -> exerciseInformationList : " + exerciseInformation);
		*/
		
		model.addAttribute("searchKeyword"  , formMap.get("searchKeyword"));
		model.addAttribute("searchCondition", formMap.get("searchCondition"));
		return "exercise/exerciseInfoList";
	}
	
	//리턴타입 ModleAndView -> String 변경 통일
	//전달할 데이타 저장타입 : ModleAndView -> Model
	@RequestMapping("exerciseinfo")
	public String getExerciseInformation(ExerciseInformationVO vo, Model model) {
		System.out.println(">>> 글 상세 조회 처리 - getExerciseInformation()");
		
		ExerciseInformationVO exerciseInformation = exerciseInformationService.getExerciseInformation(vo);
		
		exerciseInformation.splitExercisePicturesList();
		for(String str : exerciseInformation.getExercisePicturesList()) {
			System.out.println("이미지: " + str);
		}
		
		//테이블 쓸것 같으면 100행에 있는것을 사용할것~!
		/*
		List<ExerciseInformationFileVO> exerciseFileList = exerciseInformationService.getExerciseFileList(vo);
		
		for(ExerciseInformationFileVO v : exerciseFileList) {
			System.out.println(v);
		}
		model.addAttribute("파일 리스트목록 사용할이름", exerciseFileList);
		*/
		//model.addAttribute(exerciseInformation); //exerciseInformationVO
		model.addAttribute("exerciseInformation", exerciseInformation); //데이터 저장
		System.out.println("> exerciseInformation : " + exerciseInformation);
		
		return "exercise/exerciseInfo";
	}
	
	@Value("${file.directory}")
	private String fileDirectory;
	
	@GetMapping("/exercise")
	public String uploadForm() {
		return "exercise/insertexerciseinformation";
	}
	
	@GetMapping("/insertexerciseinformationform")
	public String form() {	
		return "exercise/insertexerciseinformationform";
	}
	
	@GetMapping("insertexerciseinformation")
	public String insertExerciseInformation(ExerciseInformationVO vo, Model model) throws IllegalStateException, IOException {
		
		return "redirect:/exerciseinfo";
	}
	
	@PostMapping("insertexerciseinformation")
	public String insertExerciseInformation(ExerciseInformationVO vo,/* @RequestParam("exercisePictures")*/ MultipartFile file, MultipartHttpServletRequest mtfRequest,
			Model model) throws IllegalStateException, IOException {
		System.out.println(">>> 글 등록 처리 - insertExerciseInformation()");
		System.out.println("글 vo " + vo);
		
		
		String path = "C:/hellopt_file/";
		
		int exerciseIdx = vo.getExerciseIdx();
		System.out.println("exerciseIdx: " + exerciseIdx);
		File dir = new File(path);
		if(!dir.isDirectory()) {
			dir.mkdirs();
		}
		vo.setExercisePictures("");
		List<MultipartFile> fileList = mtfRequest.getFiles("exerciseFile");
		if(fileList.size() == 1 && fileList.get(0).getOriginalFilename().equals("")) {
			
		} else {//for (MultipartFile filePart : fileList)
			for (int i = 0; i < fileList.size(); i++) {
				//원본파일명
				String exerciseInformationFileOname = fileList.get(i).getOriginalFilename();
				String FileExtension
						= exerciseInformationFileOname.substring(exerciseInformationFileOname.lastIndexOf("."));
				//파알명 중복되지 않게 처리 한 저장될 이름
				String saveFileName
						= UUID.randomUUID().toString().replaceAll("-", "")+FileExtension;
				String savePath = path + saveFileName; //저장된 파일 경로
				System.out.println("실제 파일 이름 : " + exerciseInformationFileOname);
				System.out.println("저장된 파일 이름 : " + saveFileName);
				long fileSize = fileList.get(i).getSize(); //파일 사이즈
				System.out.println("저장된 파일 사이즈 : " + fileSize);
				s3Utils.uploadMultipart("exercise/", saveFileName, fileList.get(i));
				//fileList.get(i).transferTo(new File(savePath)); //파일 저장
				System.out.println("저장된 파일 경로 : " + savePath);
				//
				vo.setExercisePictures( vo.getExercisePictures()+ saveFileName+",");
				System.out.println("exerciseInformationFileOname, saveFileName, fileSize: " + exerciseInformationFileOname + saveFileName + fileSize + exerciseIdx);
				exerciseInformationService.uploadFile(exerciseInformationFileOname, saveFileName, fileSize, exerciseIdx);
				
				System.out.println("글정보" + vo);
				
			}
		}
		if(vo.getExercisePictures().length()>0) {
			vo.setExercisePictures(vo.getExercisePictures().substring(0, vo.getExercisePictures().length()-1 ) );
		} exerciseInformationService.insertExerciseInformation(vo);
		
		return "redirect:/exerciseinfolist";
	}
	
		/* *** 파일 업로드 처리 ********
		 * MultipartFile 인터페이스 주요 메소드
		 * String getOriginalFilename() : 업로드한 파일명 찾기
		 * void transferTo(File destFile) : 업로드한 파일을 destFile에 저장
		 * boolean isEmpty() : 업로드한 파일의 존재여부(없으면 true 리턴)
		 */
		
		
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
	@RequestMapping("/updateexerciseinfo")
	public String updateExerciseInformation(@ModelAttribute("exerciseInformation") ExerciseInformationVO vo) {
		System.out.println(">>> 글 수정 처리 - updateExerciseInformation()");
		System.out.println("> exerciseInformation vo : " + vo);
		
		exerciseInformationService.updateExerciseInformation(vo);
		return "redirect:/exerciseinfolist";
	}
	
	@RequestMapping("/deleteexerciseinfo")
	public String deleteExerciseInformation(ExerciseInformationVO vo) {
		System.out.println(">>> 글 삭제 처리 - deleteExerciseInformation()"); 
		
		exerciseInformationService.deleteExerciseInformation(vo);
		return "redirect:/exerciseinfolist";
	}
	
	//---------------------------------
	@RequestMapping("/dataTransform")
	@ResponseBody //응답객체의 몸체 담아서 전달
	public ExerciseInformationListVO dataTransform(ExerciseInformationVO vo) {
		System.out.println(">>> dataTransform() 실행(XML)");
		vo.setSearchCondition("EXERCISE_NAME");
		vo.setSearchKeyword("");
		
		List<ExerciseInformationVO> exerciseInformationList = exerciseInformationService.getExerciseInformationList(vo); //여기까진 맞음
		ExerciseInformationListVO exerciseInformationListVO = new ExerciseInformationListVO();
		exerciseInformationListVO.setExerciseInformationList(exerciseInformationList);
	
		return exerciseInformationListVO;
	}
	
}