package com.bit.hellopt.data;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.bit.hellopt.vo.user.ProfileVO;

public interface ProfileMapper {
	@Insert("INSERT INTO user_profile_tb (fk_user_id, original_file_name, stored_file_name) "
			+ "VALUES (#{fkUserId}, #{originalFileName}, #{storedFileName})")
	public void insertProfile(ProfileVO vo);
	@Select("SELECT * FROM user_profile_tb WHERE fk_user_id = #{fkUserId}")
	public ProfileVO selectProfile(String fkUserId);
	@Update("UPDATE user_profile_tb SET original_file_name = #{originalFileName}, stored_file_name = #{storedFileName}, uploaded_date = sysdate WHERE fk_user_id = #{fkUserId}")
	public void updateProfile(ProfileVO vo);
}
