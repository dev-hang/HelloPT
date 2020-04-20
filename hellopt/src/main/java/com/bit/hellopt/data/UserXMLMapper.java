package com.bit.hellopt.data;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bit.hellopt.vo.user.User;

public interface UserXMLMapper {
	public User selectUser(String userId);
	public List<User> pagingUserList(@Param("search")String search, 
			@Param("searchValue")String searchValue, @Param("page") int page);
	public int countUsers(@Param("search")String search, 
			@Param("searchValue")String searchValue);
	public void updateUser(User user);
	public void updateRole(User user);
}
