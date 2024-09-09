package kr.co.iei.member.controller;

import java.io.Console;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

import jakarta.servlet.http.HttpSession;
import kr.co.iei.member.model.dto.MemberDTO;
import kr.co.iei.member.model.service.MemberService;
import kr.co.iei.util.EmailSender;

@Controller
@RequestMapping(value="/member")
public class MemberController {
	@Autowired
	private MemberService memberService;
	@Autowired
	private EmailSender emailSender;
	
	//회원가입 html 이동 
	@GetMapping(value="/joinFrm")
	public String JoinFrm() {
		return "/member/joinFrm";
	}
	
	//회원가입page 아이디 중복 체크 
	@ResponseBody
	@GetMapping(value="/checkId")
	public int checkId(MemberDTO member) {
		int result = memberService.check(member);
		return result;
	}
	
	//회원가입page 회원insert 
	@PostMapping(value="/join")
	public String insertMember(MemberDTO member, Model model) {
		System.out.println(member);
		int result = memberService.insertMember(member);
		if(result >0) {
			return "redirect:/";
		}else {
			model.addAttribute("title", "회원가입 실패");
			model.addAttribute("msg", "관리자에게 문의하세요.");
			model.addAttribute("icon", "warning");
			model.addAttribute("loc", "/");
			return "common/msg";
		}
	}
	
	//회원가입page 이메일중복체크 
	@ResponseBody
	@GetMapping(value="/checkEmail")
	public int checkEamil(MemberDTO member) {
		int result = memberService.check(member);
		return result;
		
	}
	
	//이메일 인증 코드전송 
	@ResponseBody
	@PostMapping(value="/sendCode")
	public String sendCode(String memberEmail) {
		//인증메일 제목 생성
		String emailTitle = "TABLE 인증메일입니다.";
		//인증메일 인증코드 생성
		Random r = new Random();
		StringBuffer sb = new StringBuffer();
		for(int i = 0; i < 6; i++) {	
		//0 ~ 9 : r.nextInt(10) 
		//A ~ Z : r.nextInt(26)+65
		//a ~ z : r.netxInt(26)+97;
					
		int flag = r.nextInt(3);//0,1,2 -> 숫자쓸지,대문자 쓸지,소문자 쓸지 결정
		if(flag == 0) {
			int randomCode = r.nextInt(10);
				sb.append(randomCode);
			}else if(flag == 1) {
				char randomCode = (char)(r.nextInt(26)+65);
				sb.append(randomCode);
			}else if(flag == 2) {
				char randomCode = (char)(r.nextInt(26)+97);
				sb.append(randomCode);
			}
		}
		String emailContent = "<h1>안녕하세요 . table 입니다.</h1>"
							+"<h3>인증번호는 [<span style='color:red;'>"
							+sb.toString()
							+"]</span>입니다.</h3>";
		emailSender.sendMail(emailTitle, memberEmail, emailContent);
		return sb.toString();	
	}
	
	//로그인 page 이동
	@GetMapping(value="/loginFrm")
	public String loginFrm() {
		return "/member/loginFrm";
	}
	
	//로그인 page 아이디+비밀번호 확인  
	@PostMapping(value="/login")
	public String login(MemberDTO member, Model model, HttpSession session ) {
		MemberDTO m = memberService.selectOneMember(member);
		if(m != null) {
			if(m.getMemberLevel() == 3) {
				model.addAttribute("title", "로그인 실패");
				model.addAttribute("msg", "탈퇴 회원입니다.");
				model.addAttribute("icon", "error");
				model.addAttribute("loc", "/member/loginFrm");
				return "common/msg";
			}else {
				session.setAttribute("member", m);
				//System.out.println(m);
				return "redirect:/";
			}
		}else {
			model.addAttribute("title", "로그인 실패");
			model.addAttribute("msg", "아이디 또는 비밀번호를 확인하세요");
			model.addAttribute("icon", "error");
			model.addAttribute("loc", "/member/loginFrm");
			return "common/msg";
		}
	}
	
	@GetMapping(value="/logout")
	public String logout(HttpSession session) {
		session.invalidate();//현재 세션에 저장된 멤버 파기 
		return "redirect:/";
	}
	
	@GetMapping(value = "/searchIdFrm")
	public String searchIdFrm(){
		return "/member/searchIdFrm";
	}
	
	@ResponseBody
	@PostMapping(value = "/searchId")
	public String searchId(MemberDTO member) {
		MemberDTO m = memberService.searchId(member);
		if(m == null) {
			return null;
		}else{
			//인증메일 제목 생성
			String emailTitle = "TABLE 인증메일입니다.";
			//인증메일 인증코드 생성
			Random r = new Random();
			StringBuffer sb = new StringBuffer();
			for(int i = 0; i < 6; i++) {	
						
			int flag = r.nextInt(3);//0,1,2 -> 숫자쓸지,대문자 쓸지,소문자 쓸지 결정
			if(flag == 0) {
				int randomCode = r.nextInt(10);
					sb.append(randomCode);
				}else if(flag == 1) {
					char randomCode = (char)(r.nextInt(26)+65);
					sb.append(randomCode);
				}else if(flag == 2) {
					char randomCode = (char)(r.nextInt(26)+97);
					sb.append(randomCode);
				}
			}
			String emailContent = "<h1>안녕하세요 . table 입니다.</h1>"
								+"<h3>인증번호는 [<span style='color:red;'>"
								+sb.toString()
								+"]</span>입니다.</h3>";
			emailSender.sendMail(emailTitle, member.getMemberEmail(), emailContent);
			return sb.toString();	
		}

	}
	@ResponseBody
	@PostMapping(value = "/sendId")
	public String sendId(MemberDTO member) {
		MemberDTO m = memberService.searchId(member);
		return m.getMemberId();
	}
	@GetMapping(value = "/mypage")
	public String mypage() {
		return "/member/mypage";
	}
	@GetMapping(value ="/searchPwFrm")
	public String searchPwFrm() {
		return "/member/searchPwFrm";
	}
	@ResponseBody
	@PostMapping(value ="/searchMember")
	public String searchMember(MemberDTO member) {
		int result  = memberService.searchMember(member);
		if(result == 1) {
			String emailTitle ="비밀번호 재발급 인증 번호입니다.";
			Random r = new Random();
			StringBuffer sb = new StringBuffer();
			for(int i = 0; i < 6; i++) {
				int flag = r.nextInt(3);
				if(flag == 0) {
					int randomCode = r.nextInt(10);
					sb.append(randomCode);
				}else if(flag == 1) {
					char randomCode = (char)(r.nextInt(26)+65);
					sb.append(randomCode);
				}else if(flag == 2) {
					char randomCode = (char)(r.nextInt(26)+97);
					sb.append(randomCode);
				}
			}	
			String emailContent = "<h1>안녕하세요 . table 입니다.</h1>"
					+"<h3>인증번호는 [<span style='color:red;'>"
					+sb.toString()
					+"</span>]입니다.</h3>";
			emailSender.sendMail(emailTitle, member.getMemberEmail(), emailContent);
			return sb.toString();	
			
		}
		return "";
	}
	@ResponseBody
	@PostMapping(value = "/updatePw")
	public int updatePw(MemberDTO member) {
		//System.out.println(member);
		int result = memberService.updatePw(member);
		return result;
	}
	
	@ResponseBody
	@PostMapping(value = "/checkEmail")
	public int checkEmail(String memberEmail) {
		MemberDTO member = memberService.checkEmail(memberEmail);
		if(member != null) {
			return 1;
		}else {
			return 0;
		}
	}
	@GetMapping(value = "/delete")
	public String deleteMember(@SessionAttribute MemberDTO member, Model model, HttpSession session) {
		int result = memberService.deleteMember(member.getMemberId());
		if(result > 0) {
			model.addAttribute("title", "탈퇴완료");
			model.addAttribute("msg", "회원 탈퇴 되었습니다.");
			model.addAttribute("icon", "success");
			model.addAttribute("loc", "/");
			session.invalidate();
		}else {
			model.addAttribute("title", "탈퇴실패");
			model.addAttribute("msg", "서버 오류로 인해관리자에게 문의해주세요.");
			model.addAttribute("icon", "error");
			model.addAttribute("loc", "/member/mypage");
		}
		return "common/msg";
	}
	@PostMapping(value = "/update")
	public String updateMember(MemberDTO member, Model model, HttpSession session) {
		int result = memberService.updateMember(member);
		if(result > 0) {
			MemberDTO m = memberService.updateOneMember(member);
			session.setAttribute("member", m);
			model.addAttribute("msg","회원 정보가 수정되었습니다.");
			model.addAttribute("icon", "success");
			model.addAttribute("loc", "/member/mypage");
		}else {
			model.addAttribute("title", "수정실패");
			model.addAttribute("msg", "서버 오류로 인해관리자에게 문의해주세요.");
			model.addAttribute("icon", "error");
			model.addAttribute("loc", "/member/mypage");	
		}
		return "common/msg";
	}
}
