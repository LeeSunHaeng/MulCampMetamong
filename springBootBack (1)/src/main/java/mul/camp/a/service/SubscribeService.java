package mul.camp.a.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mul.camp.a.dao.SubscribeDao;
import mul.camp.a.dto.SubTodayMealDto;
import mul.camp.a.dto.SubscribeDto;

@Service
@Transactional
public class SubscribeService {
	
	@Autowired
	SubscribeDao dao;
	
	/* #21# 구독 회원정보 가져오기 */
	public SubscribeDto getSubInfo(String id) {
		System.out.println("#21# Dao 받은 id값 : " + id);
		return dao.getSubInfo(id);
	}

	
	/* #21# 구독 회원추가 + 멤버 구독값 수정 */
	// 1) 구독 추가 (구독 만료일자 제외)
	public boolean subAdd(SubscribeDto dto) {
		int result = dao.subAdd(dto);
		
		return result>0?true:false;
	}
	// 1-1) 구독 만료일자 추가
	public void subAddEndday(SubscribeDto dto) {
		dao.subAddEndday(dto);
	}
	// 2) 멤버 DB 내 구독값 수정
	public void subUpdateMember(SubscribeDto dto) {
		dao.subUpdateMember(dto);
	}
	
	
	/* #21# 구독 만료확인 */
	// 1) 구독 만료확인
	public SubscribeDto subEnddayCheck(SubscribeDto dto) {
		return dao.subEnddayCheck(dto);
	}
	// 2) 멤버DB 내 구독값 수정 
	public void subUpdateMemberEnd(SubscribeDto dto) {
		dao.subUpdateMemberEnd(dto);
	}
	// 2-1) 구독DB 내 사용자 삭제
	public boolean subDeleteUser(SubscribeDto dto) {
		int result = dao.subDeleteUser(dto);
		
		return result>0?true:false;
	}
	
	
	/* #21# 오늘의 다이어트 식단 RANDOM SELECT (1개) */
	public SubTodayMealDto subRandomDietMeal(SubTodayMealDto dto) {
		return dao.subRandomDietMeal(dto);
	}
}
