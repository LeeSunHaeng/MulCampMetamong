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
	
	@RequestMapping(value = "/snsDelete", method = {RequestMethod.GET, RequestMethod.POST} )
	public int snsDelete(int seq) {
		System.out.println("snsDelete 실행 성공");
		int n = service.snsDelete(seq);
		return n;
	}
	
	@RequestMapping(value = "/snsUpdate", method = {RequestMethod.GET, RequestMethod.POST} )
	public int snsUpdate(@RequestBody SnsDto dto) {
		System.out.println("snsUpdate 실행 성공");
		int num = service.snsUpdate(dto);
		return num;
	}
	
	@RequestMapping(value = "/snsCommentInsert", method = {RequestMethod.GET, RequestMethod.POST} )
	public int snsCommentInsert(@RequestBody SnsCommentDto dto) {
		System.out.println("snsCommentInsert 실행 성공");
		int num = service.snsCommentInsert(dto);
		return num;
	}
	
	
	@RequestMapping(value = "/snsGetMember", method = {RequestMethod.GET, RequestMethod.POST} )
	public MemberDto snsGetMember(String id) {
		System.out.println("snsGetMember 실행 성공");
		MemberDto dto = service.snsGetMmeber(id);
		System.out.println("#21# snsGetMember 쿼리 실행값 > " + dto.toString());
		return dto;
	}
	
	@RequestMapping(value = "/allSns", method = {RequestMethod.GET, RequestMethod.POST} )
	public ArrayList<SnsDto> allSns() {
		System.out.println("allSns 실행 성공");
		ArrayList<SnsDto> list = service.allSns();
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
	
	@RequestMapping(value = "/snsLikeAllDelete", method = {RequestMethod.GET, RequestMethod.POST} )
	public int snsLikeAllDelete(int seq) {
		System.out.println("snsLikeAllDelete 실행 성공");
		int num = service.snsLikeAllDelete(seq);
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
	
	@RequestMapping(value = "/snsCommentCount", method = {RequestMethod.GET, RequestMethod.POST} )
	public int snsCommentCount(int seq) {
		System.out.println("snsCommentCount 실행 성공");
		int num = service.snsCommentCount(seq);
		System.out.println("snsCommentCount num : "+num);
		return num;
	}
	
	@RequestMapping(value = "/snsCommentAllDelete", method = {RequestMethod.GET, RequestMethod.POST} )
	public int snsCommentAllDelete(int seq) {
		System.out.println("snsCommentAllDelete 실행 성공");
		int num = service.snsCommentAllDelete(seq);
		return num;
	}
	
	@RequestMapping(value = "/snsCommentDelete", method = {RequestMethod.GET, RequestMethod.POST} )
	public int snsCommentDelete(int cmtseq) {
		System.out.println("snsCommentDelete 실행 성공");
		int num = service.snsCommentDelete(cmtseq);
		return num;
	}
	
	@RequestMapping(value = "/nextSeq", method = {RequestMethod.GET, RequestMethod.POST} )
	public int nextSeq() {
		System.out.println("nextSeq 실행 성공");
		int num = service.nextSeq();
		return num;
	}
	
	@RequestMapping(value = "/currSeq", method = {RequestMethod.GET, RequestMethod.POST} )
	public int currSeq() {
		System.out.println("currSeq 실행 성공");
		int num = service.currSeq();
		return num;
	}
	
	
}
