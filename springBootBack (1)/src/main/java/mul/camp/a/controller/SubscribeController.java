package mul.camp.a.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import mul.camp.a.dto.SubTodayMealDto;
import mul.camp.a.dto.SubscribeDto;
import mul.camp.a.service.SubscribeService;

@RestController
public class SubscribeController {
	
	/* #21# [구독 Controller] */
	
	@Autowired
	SubscribeService service;
	
	/* #21# 동작 test용 */
	@RequestMapping(value = "/subTest", method = {RequestMethod.GET, RequestMethod.POST} )
	public String subTest() {
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
	
	
	/* #21# 구독 신청 */
   @RequestMapping(value = "/subAdd", method = {RequestMethod.GET, RequestMethod.POST} ) 
   public String subAdd(@RequestBody SubscribeDto dto) {
	   System.out.println("#21# SubscribeController subAdd() 동작");
	   System.out.println("#21# Front에서 받아온 dto 값(구독 신청정보): " + dto.toString());
  
	   // 1) 구독 신청 (구독회원 DB 내 추가) 
	   boolean result = service.subAdd(dto);
	   if(result) {
		   	// 2) 구독만료일 추가 + 멤버 DB 내 구독값 수정 (1번 성공 시 실행) 
		   	service.subAddEndday(dto);
		   	System.out.println("#21# SubscribeController subAddEndday() 동작 _구독 DB 내 만료일자 추가");
  
		   	service.subUpdateMember(dto); 
		   	System.out.println("#21# SubscribeController subUpdateMember() 동작 _멤버 DB 내 구독값 1으로 수정");
		   	
		   	return "Success"; 
	   } 
	   return "Fail";
   }
   
   
   /* #21# 구독 만료여부 확인 */
   @RequestMapping(value = "/subEnddayCheck", method = {RequestMethod.GET, RequestMethod.POST} ) 
   public String subEnddayCheck(@RequestBody SubscribeDto dto) {
	   System.out.println("#21# SubscribeController subEnddayCheck() 동작");
	   System.out.println("#21# Front에서 받아온 dto 값(현재 로그인한 사용자): " + dto.toString());
  
	   // 1) 구독 만료여부 판별
	   try {
		   SubscribeDto result = service.subEnddayCheck(dto);
		   
		   if(result == null) {						// 구독이 만료되었을 경우
			   service.subUpdateMemberEnd(dto);
			   System.out.println("#21# SubscribeController subUpdateMemberEnd() 동작 _멤버 DB 내 구독값 0으로 수정");
			   
			   boolean delUser = service.subDeleteUser(dto);
			   if(delUser) {
				   System.out.println("#21# SubscribeController subDeleteUser() 동작 _구독 DB 해당 사용자 행 삭제");
			   }
			   
			   return "SuccessEnd";
		   }
		   return "Success";
		   
	   } catch (Exception e) {
		   System.out.println("#21# SubscribeController subEnddayCheck() 동작 Error");
		   e.printStackTrace();
		   
		   return "Fail";
	   }
   }
   
   
   /* #21# 오늘의 다이어트 식단 RANDOM SELECT (1개) */
   @RequestMapping(value = "/subRandomDietMeal", method = {RequestMethod.GET, RequestMethod.POST} ) 
   public SubTodayMealDto subRandomDietMeal(@RequestBody SubTodayMealDto dto) {
	   System.out.println("#21# SubscribeController subRandomDietMeal() 동작");
	   System.out.println("#21# Front에서 받아온 시간(time, 아/점/저) 값: " + dto.toString());
  
	   SubTodayMealDto dietMeal = service.subRandomDietMeal(dto);
	   
	   return dietMeal;
   }
   
   

}
