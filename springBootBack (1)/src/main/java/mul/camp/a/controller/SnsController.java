package mul.camp.a.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import mul.camp.a.dto.MemberDto;
import mul.camp.a.dto.SnsDto;
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
		System.out.println("SnsDto 실행 성공");
		ArrayList<SnsDto> list = service.allSns();
		return list;
	}
	
}
