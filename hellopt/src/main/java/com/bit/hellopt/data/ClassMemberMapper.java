package com.bit.hellopt.data;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import com.bit.hellopt.vo.live.ClassMember;

public interface ClassMemberMapper {
	//강의 신청
	@Insert("INSERT INTO CLASS_MEMBER_TB (CLASS_MEMBER_IDX, FK_CLASS_IDX, FK_USER_ID, PAY_STATUS)"
			+ "VALUES(CLASS_MEMBER_TB_SEQ.NEXTVAL, #{fkClassIdx}, #{fkUserId}, '결제대기')")
	public void insertClassMember(ClassMember info);
	
	//강의번호와 아이디로 신청여부 조회
	@Select("SELECT CASE WHEN EXISTS (SELECT * FROM CLASS_MEMBER_TB "
			+ "WHERE FK_USER_ID = #{fkUserId} AND FK_CLASS_IDX = #{fkClassIdx}) "
			+ "THEN 1 ELSE 0 END FROM DUAL")
	public int getRegInfo(ClassMember info);

	//아이디로 조회
	@Select("SELECT * FROM CLASS_MEMBER_TB WHERE FK_USER_ID = #{fkUserId}")
	public List<ClassMember> getMyClass(ClassMember info);
	
	//강의번호로 조회
	@Select("SELECT * FROM CLASS_MEMBER_TB WHERE CLASS_MEMBER_IDX = #{classMemberIdx}")
	public ClassMember getOneClassInfo(int classMemberIdx);
	
	//강의 신청 취소
	@Delete("DELETE FROM CLASS_MEMBER_TB WHERE FK_CLASS_IDX = #{fkClassIdx} AND FK_USER_ID = #{fkUserId}")
	public void deleteClassMember(ClassMember info);
	
	//강의 신청자 수 조회
	@Select("SELECT COUNT(*) FROM CLASS_MEMBER_TB WHERE FK_CLASS_IDX = #{classIdx}")
	public int getMemberCnt(int classIdx);
}
