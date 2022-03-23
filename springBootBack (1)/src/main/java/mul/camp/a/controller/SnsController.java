package mul.camp.a.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
}
