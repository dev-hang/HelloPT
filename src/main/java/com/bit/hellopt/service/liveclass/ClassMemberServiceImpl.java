package com.bit.hellopt.service.liveclass;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bit.hellopt.data.ClassMemberMapper;
import com.bit.hellopt.vo.live.ClassMember;

@Service
public class ClassMemberServiceImpl implements ClassMemberService {
	
	@Autowired
	ClassMemberMapper classMemberMapper;
	
	//강의 신청
	@Override
	public void insertClassMember(ClassMember info) {
		classMemberMapper.insertClassMember(info);
	}

	//강의번호, 아이디로 조회
	@Override
	public int getRegInfo(ClassMember info) {
		return classMemberMapper.getRegInfo(info);
	}

	//아이디로 조회
	@Override
	public List<ClassMember> getMyClass(ClassMember info) {
		return classMemberMapper.getMyClass(info);
	}

	//강의번호로 조회
	@Override
	public ClassMember getOneClassInfo(int classMemberIdx) {
		return classMemberMapper.getOneClassInfo(classMemberIdx);
	}
	
	//강의 신청 취소
	@Override
	public void deleteClassMember(ClassMember info) {
		classMemberMapper.deleteClassMember(info);
	}
	
	//강의 신청자 수 조회
	@Override
	public int getMemberCnt(int classIdx) {
		return classMemberMapper.getMemberCnt(classIdx);
	}

}
