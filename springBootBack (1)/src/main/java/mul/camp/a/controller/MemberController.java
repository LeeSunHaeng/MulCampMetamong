package mul.camp.a.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sun.tools.javac.util.List;

import mul.camp.a.dto.MemberDto;
import mul.camp.a.service.MemberService;

@RestController
public class MemberController {

	@Autowired
	MemberService service;
	
	@RequestMapping(value = "/getId", method = {RequestMethod.GET, RequestMethod.POST} )
	public String getId(MemberDto dto) {
		System.out.println("MemberController getId");
		System.out.println("HelloHelloHelloHelloHelloHello");
		boolean b = service.getId(dto);
		if(b) {
			return "NO";
		}
		return "OK";
	}
	
	@RequestMapping(value = "/addmember", method = {RequestMethod.GET, RequestMethod.POST} )
	public String addmember(@RequestBody MemberDto dto) {
		System.out.println("MemberController addmember");
		System.out.println(dto.toString());
		
		boolean b = service.addmember(dto);
		if(b) {
			return "YES";
		}
		return "NO";
	}
	
	@RequestMapping(value = "/login", method = {RequestMethod.GET, RequestMethod.POST} )
	public MemberDto login(@RequestBody MemberDto dto, HttpServletRequest req) {
		System.out.println("MemberController login");
		
		MemberDto mem = service.login(dto);
		
		// 이렇게도 사용할 수 있다
		req.getSession().setAttribute("login", mem);
		
		return mem;
	}
	
	//아이디찾기
	@RequestMapping(value="/searchId",method = {RequestMethod.POST})
	public String searchId(@RequestBody MemberDto dto) {
		
		System.out.println("MemberController searchId");
		System.out.println(dto.toString());
		
		String id = service.searchId(dto);
		System.out.println("id:" + id);
		/*
		 * if(id == null || id.equals("")) { System.out.println("잘못됨"); id = "";
		 * System.out.println("id:" + id); return id; } else { System.out.println("잘됨");
		 * System.out.println("id:" + id); return id; }
		 */
		return id;
	}
	//비밀번호 찾기
	@RequestMapping(value="/searchPwd",method = {RequestMethod.POST})
	public String searchPwd(@RequestBody MemberDto dto) {
		
		System.out.println("MemberController searchPwd");
		System.out.println(dto.toString());
		
		String pwd = service.searchPwd(dto);
		System.out.println(pwd);
	
		return pwd;
	}
}












