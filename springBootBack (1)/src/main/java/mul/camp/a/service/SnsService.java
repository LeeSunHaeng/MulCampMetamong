package mul.camp.a.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mul.camp.a.dao.SnsDao;
import mul.camp.a.dto.SnsDto;

@Service
@Transactional
public class SnsService {
	
	@Autowired
	SnsDao dao;
	
	public int snsInsert(SnsDto dto) {
		System.out.println("service 실행");
		return dao.snsInsert(dto);
	}
}
