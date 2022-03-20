package mul.camp.a.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mul.camp.a.dao.SubscribeDao;
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
	public boolean subAdd(SubscribeDto dto) {
		int result = dao.subAdd(dto);
		
		return result>0?true:false;
	}
	public void subUpdateMember(SubscribeDto dto) {
		dao.subUpdateMember(dto);
	}
}
