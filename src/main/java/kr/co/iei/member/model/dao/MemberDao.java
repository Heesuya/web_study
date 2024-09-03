package kr.co.iei.member.model.dao;

import org.apache.ibatis.annotations.Mapper;

import kr.co.iei.member.model.dto.MemberDTO;

@Mapper
public interface MemberDao {

	int check(MemberDTO member);

	int insertMember(MemberDTO member);

	MemberDTO selecetOneMember(MemberDTO member);

	MemberDTO searchId(MemberDTO member);


}
