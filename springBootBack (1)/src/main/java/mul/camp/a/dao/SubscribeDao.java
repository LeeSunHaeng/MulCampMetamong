package mul.camp.a.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

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

}
