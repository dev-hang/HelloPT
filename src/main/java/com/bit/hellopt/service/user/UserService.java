package com.bit.hellopt.service.user;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.web.multipart.MultipartFile;

import com.bit.hellopt.vo.user.User;

public interface UserService {
	public void registerUser(User user);
	public void registerUser(User user, MultipartFile file);
	public int isUser(String userId);
	public List<User> getUserList();
	public User findUserById(String userId);
	
	/**
	 * 관리자가 유저 정보 업데이트
	 * @param user
	 */
	public void updateUser(User user);
	/**
	 * 일반 유저가 유저 정보 업데이트
	 * @param user
	 */
	public void updateNormalUser(User user);
	public void deleteUser(User user);
	public void disableUser(User user);
	public List<User> pagingUserList(String search, String searchValue, int page);
	public int getLastPage(String search, String searchValue, int page);
	public void generateTempPw(User user, String email);
	public void updateRole(User user, String role);
}
