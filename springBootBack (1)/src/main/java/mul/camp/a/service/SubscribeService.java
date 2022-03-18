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

}
