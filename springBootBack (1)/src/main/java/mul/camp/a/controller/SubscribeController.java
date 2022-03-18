package mul.camp.a.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import mul.camp.a.dto.SubscribeDto;
import mul.camp.a.service.SubscribeService;

@RestController
public class SubscribeController {
	
	/* #21# [구독 Controller] */
	
	@Autowired
	SubscribeService service;
	
	/* #21# 동작 test용 */
	@RequestMapping(value = "/test", method = {RequestMethod.GET, RequestMethod.POST} )
	public String test() {
		return "동작중";
	}
	
	
	/* #21# 구독 회원정보 가져오기 */
	@RequestMapping(value = "/getSubInfo", method = {RequestMethod.GET, RequestMethod.POST} )
	public SubscribeDto getSubInfo(String id) {
		System.out.println("#21# SubscribeController getSubInfo() 동작");
		System.out.println("#21# Front에서 받아온 id 값: " + id);
		
		SubscribeDto subInfo = service.getSubInfo(id);
		
		// 방법_1) 구독 시간날짜만 출력 (Simple Date format 사용)
		/*
		String date = subInfo.getSubStartday();
        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");

        try{
            Date dt = sd.parse(date);
            String result = sd.format(dt);
            subInfo.setSubStartday(result);
        }
        catch(Exception e){
            e.printStackTrace();
        } */
		
		// 방법_2) 구독 시작날짜에서 시간 자르기 (String 자르기, split 사용)
		String[] dateArr = (subInfo.getSubStartday()).split(" ");
		subInfo.setSubStartday(dateArr[0]);
		
		
		System.out.println("#21# SubscribeController getSubInfo() 가져온 구독 회원정보 값 확인: " + subInfo.toString());
		
		return subInfo;
	}

}
