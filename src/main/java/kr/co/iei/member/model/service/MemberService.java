package kr.co.iei.member.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.iei.member.model.dao.MemberDao;
import kr.co.iei.member.model.dto.MemberDTO;

@Service
public class MemberService {
	@Autowired
	private MemberDao memberDao;
	@Autowired
	BCryptPasswordEncoder encoder;

	public int check(MemberDTO member) {
		int result = memberDao.check(member);
		return result;
	}
	@Transactional
	public int insertMember(MemberDTO member) {
		String encoderPw = encoder.encode(member.getMemberPw());
		member.setMemberPw(encoderPw);
		int result = memberDao.insertMember(member);
		return result;
	}
	public MemberDTO selectOneMember(MemberDTO member) {
		MemberDTO m = memberDao.selectOneMember(member);
		if(m != null) {
			//평문패스워드와 암호화 패스워드 일치하면 true 
			if(encoder.matches(member.getMemberPw(), m.getMemberPw())) {
				m.setMemberPw(null);
				return m;
			}else {
			//일치 하지 않으면 false 	
				return null;
			}
		}else {
			return null;			
		}
	}
	public MemberDTO searchId(MemberDTO member) {
		MemberDTO m = memberDao.searchId(member);
		return m;
	}
	
	public int searchMember(MemberDTO member) {
		int result = memberDao.searchMember(member);
		return result;
	}
	public int updatePw(MemberDTO member) {
		MemberDTO m = memberDao.selectOneMember(member);
		if(encoder.matches(member.getMemberPw(), m.getMemberPw())) {
			return -1;
		}else {
			String encPw = encoder.encode(member.getMemberPw());
			member.setMemberPw(encPw);
			return memberDao.updatePw(member);
		}
		
	}
	public MemberDTO checkEmail(String memberEmail) {
		MemberDTO member = memberDao.checkEmail(memberEmail);
		return member;
	}
	@Transactional
	public int deleteMember(String memberId) {
		int result = memberDao.deleteMember(memberId);
		return result;
	}
	@Transactional
	public int updateMember(MemberDTO member) {
		int result = memberDao.updateMember(member);
		return result;
	}
	public MemberDTO updateOneMember(MemberDTO member) {
		MemberDTO m = memberDao.selectOneMember(member);
		m.setMemberPw(null);
		return m;
	}
}
