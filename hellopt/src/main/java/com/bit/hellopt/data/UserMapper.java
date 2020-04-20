package com.bit.hellopt.data;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.bit.hellopt.vo.user.User;

public interface UserMapper {
	@Select("SELECT * FROM users_tb WHERE user_id = #{userId}")
	public User selectUserById(String userId);
	
	@Insert("INSERT INTO users_tb (user_id, user_pw, user_role, user_name, user_gender"
			+ ", user_address, user_birth, user_job, user_root, user_height, user_weight, user_profile, user_email) "
			+ "VALUES (#{userId}, #{userPw}, #{userRole}, #{userName}, #{userGender}"
			+ ", #{userAddress}, TO_DATE(#{userBirth}, 'YYYY-MM-DD'), #{userJob}, #{userRoot}, #{userHeight}, #{userWeight}, #{userProfile}, #{userEmail})")
	public void insertUser(User user);
	
	@Select("SELECT count(*) FROM users_tb WHERE user_id = #{userId}")
	public int isUser(String userId);
	
	@Select("SELECT * FROM users_tb")
	public List<User> getUserList();
	
	@Update("UPDATE users_tb SET user_role = #{userRole}, user_name = #{userName}, "
			+ "user_gender = #{userGender}, user_address = #{userAddress}, user_birth = TO_DATE(#{userBirth}, 'YYYY-MM-DD'), "
			+ "user_job = #{userJob}, user_root = #{userRoot}, user_height = #{userHeight}, user_weight = #{userWeight}, user_profile = #{userProfile}, user_email = #{userEmail} WHERE user_id = #{userId}")
	public void updateUser(User user);
	
	@Delete("DELETE FROM users_tb WHERE user_id = #{userId}")
	public void deleteUser(User user);
	
	@Update("UPDATE users_tb SET user_enable = 0 WHERE user_id = #{userId}")
	public void disableUSer(User user);
}
