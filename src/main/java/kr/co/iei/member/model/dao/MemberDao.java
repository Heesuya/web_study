package kr.co.iei.member.model.dao;

import org.apache.ibatis.annotations.Mapper;

import kr.co.iei.member.model.dto.MemberDTO;

@Mapper
public interface MemberDao {

	int check(MemberDTO member);

	int insertMember(MemberDTO member);

	MemberDTO selectOneMember(MemberDTO member);

	MemberDTO searchId(MemberDTO member);

	int searchMember(MemberDTO member);

	int updatePw(MemberDTO member);

	MemberDTO checkEmail(String memberEmail);

	int deleteMember(String memberId);

	int updateMember(MemberDTO member);



}
