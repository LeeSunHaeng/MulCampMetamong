package mul.camp.a.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import mul.camp.a.dto.SubTodayMealDto;
import mul.camp.a.dto.SubscribeDto;

@Mapper
@Repository
public interface SubscribeDao {
	
	/* #21# 구독 회원정보 가져오기 */
	public SubscribeDto getSubInfo(String id);
	
	/* #21# 구독 회원추가 + 멤버 구독값 수정 */
	public int subAdd(SubscribeDto dto);
	public void subAddEndday(SubscribeDto dto);
	public void subUpdateMember(SubscribeDto dto);
	
	/* #21# 구독 만료확인 */
	public SubscribeDto subEnddayCheck(SubscribeDto dto);
	public void subUpdateMemberEnd(SubscribeDto dto);
	public int subDeleteUser(SubscribeDto dto);
	
	/* #21# 오늘의 다이어트 식단 RANDOM SELECT (1개) */
	public SubTodayMealDto subRandomDietMeal(SubTodayMealDto dto);

}
