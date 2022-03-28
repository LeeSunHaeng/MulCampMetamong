package mul.camp.a.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import mul.camp.a.dto.MemberDto;
import mul.camp.a.dto.SnsCommentDto;
import mul.camp.a.dto.SnsDto;
import mul.camp.a.dto.SnsLikeDto;
import mul.camp.a.service.SnsService;

@RestController
public class SnsController {

	
	@Autowired
	SnsService service;
	
	/* #21# 동작 test용 */
	@RequestMapping(value = "/snsInsert", method = {RequestMethod.GET, RequestMethod.POST} )
	public int snsInsert(@RequestBody SnsDto dto) {
		System.out.println("snsInsert 실행 성공");
		int num = service.snsInsert(dto);
		return num;
	}
	
	@RequestMapping(value = "/snsGetMember", method = {RequestMethod.GET, RequestMethod.POST} )
	public MemberDto snsGetMember(String id) {
		System.out.println("snsGetMember 실행 성공");
		MemberDto dto = service.snsGetMmeber(id);
		return dto;
	}
	
	@RequestMapping(value = "/allSns", method = {RequestMethod.GET, RequestMethod.POST} )
	public ArrayList<SnsDto> allSns() {
		System.out.println("allSns 실행 성공");
		ArrayList<SnsDto> list = service.allSns();
		System.out.println(list.get(0));
		return list;
	}
	
	@RequestMapping(value = "/allComment", method = {RequestMethod.GET, RequestMethod.POST} )
	public ArrayList<SnsCommentDto> allComment(int seq) {
		System.out.println("allComment 실행 성공");
		ArrayList<SnsCommentDto> list = service.allComment(seq);
		return list;
	}
	
	@RequestMapping(value = "/snsLikeInsert", method = {RequestMethod.GET, RequestMethod.POST} )
	public int snsLikeInsert(@RequestBody SnsLikeDto dto) {
		System.out.println("snsLikeInsert 실행 성공");
		int num = service.snsLikeInsert(dto);
		System.out.println("snsLikeInsert num : "+num);
		return num;
	}
	
	@RequestMapping(value = "/snsLikeDelete", method = {RequestMethod.GET, RequestMethod.POST} )
	public int snsLikeDelete(@RequestBody SnsLikeDto dto) {
		System.out.println("snsLikeDelete 실행 성공");
		int num = service.snsLikeDelete(dto);
		System.out.println("snsLikeDelete num : "+num);
		return num;
	}
	
	@RequestMapping(value = "/snsLikeCheck", method = {RequestMethod.GET, RequestMethod.POST} )
	public int snsLikeCheck(@RequestBody SnsLikeDto dto) {
		System.out.println("snsLikeCheck 실행 성공");
		int num = service.snsLikeCheck(dto);
		System.out.println("snsLikeCheck num : "+num);
		return num;
	}
	
	@RequestMapping(value = "/snsLikeCount", method = {RequestMethod.GET, RequestMethod.POST} )
	public int snsLikeCount(int seq) {
		System.out.println("snsLikeCount 실행 성공");
		int num = service.snsLikeCount(seq);
		System.out.println("snsLikeCount num : "+num);
		return num;
	}
	
}
