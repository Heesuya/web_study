package kr.co.iei.member.model.dto;

import org.apache.ibatis.type.Alias;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Alias(value="member")
public class MemberDTO {
	private int memberNo;
	private String memberId;
	private String memberPw;
	private String memberName;
	private String memberPhone;
	private String memberBirth;
	private String memberEmail;
	private String memberAddr1;
	private String memberAddr2;
	private String memberAddr3;
	private String enrollDate;
	private int memberLevel;
	private String delDate;
}
