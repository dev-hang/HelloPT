package com.bit.hellopt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.bit.hellopt.service.user.UserProfileService;
import com.bit.hellopt.service.user.UserService;
import com.bit.hellopt.vo.user.ProfileVO;
import com.bit.hellopt.vo.user.User;

@Controller
public class AdminController {
	
	@Autowired
	UserService userService;
	@Autowired
	UserProfileService profileService;
	
	/**
	 * 유저 목록 보기
	 * @param model
	 * @param page 목록 중 보여줄 페이지
	 * @param search 검색 조건
	 * @return 유저 목록 view
	 */
	@GetMapping("/admin/user")
	public String adminUserManagement(Model model, 
			@RequestParam(name = "page", defaultValue = "1") int page, 
			@RequestParam(name = "search", defaultValue = "all")String search,
			@RequestParam(name = "searchValue", defaultValue = "")String searchValue) {
		model.addAttribute("userList", userService.pagingUserList(search, searchValue, page));
		model.addAttribute("lastPage", userService.getLastPage(search, searchValue, page));
		
		return "admin/adminUserManagement";
	}
	
	/**
	 * 특정 유저의 상세 정보 보기
	 * @param userId
	 * @param model
	 * @return 유저의 상세 정보 view
	 */
	@GetMapping("/admin/user/{userId}")
	public String adminUserManagementDetail(@PathVariable("userId") String userId, Model model) {
		User user = userService.findUserById(userId);

		ProfileVO profile = profileService.selectProfile(userId);
		
		if (profile != null) {
			user.setUserProfile(profile.getStoredFileName());
		}

		model.addAttribute("user", user);
		return "admin/adminUserManagementDetail";
	}
	
	/**
	 * 유저 상세 정보 업데이트
	 * @param user 유저 정보
	 * @param file 프로필 사진
	 * @return 유저목록 view
	 */
	@PostMapping("/admin/user/update")
	public String adminUserDetailUpdate(@ModelAttribute User user,  @RequestParam MultipartFile file) {
			userService.updateUser(user);
			if(!file.isEmpty()) {
				profileService.updateProfile(user, file);
			}
			
		return "redirect:/admin/user";
	}
	
	@GetMapping("admin/user/delete")
	public String adminDeleteUser(@RequestParam String userId) {
		User user = new User();
		user.setUserId(userId);
		//userService.deleteUser(user);
		userService.disableUser(user);
		return "redirect:/admin/user";
	}
	
}
