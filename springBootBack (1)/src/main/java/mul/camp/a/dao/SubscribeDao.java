package mul.camp.a.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import mul.camp.a.dto.SubscribeDto;

@Mapper
@Repository
public interface SubscribeDao {
	
	/* #21# 구독 회원정보 가져오기 */
	public SubscribeDto getSubInfo(String id);

}