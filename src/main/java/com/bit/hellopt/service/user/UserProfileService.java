package com.bit.hellopt.service.user;

import org.springframework.web.multipart.MultipartFile;

import com.bit.hellopt.vo.user.ProfileVO;
import com.bit.hellopt.vo.user.User;

public interface UserProfileService {
	public String insertProfile(User user, MultipartFile file);
	public ProfileVO selectProfile(String userId);
	public String updateProfile(User user, MultipartFile file);
}
