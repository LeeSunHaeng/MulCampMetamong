package mul.camp.a.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mul.camp.a.dao.MemberDao;
import mul.camp.a.dto.MemberDto;

@Service
@Transactional
public class MemberService {
	
	@Autowired
	MemberDao dao;
	
	public boolean getId(MemberDto dto) {
		int n = dao.getId(dto);
	
		return n>0?true:false;
	}
	
	public boolean addmember(MemberDto dto) {
		int n = dao.addmember(dto);
		System.out.println(dto.toString());
		return n>0?true:false;
	}
	
	public MemberDto login(MemberDto dto) {
		
		return dao.login(dto);		
	}
	
	public String searchId(MemberDto dto) {
		
		return dao.searchId(dto);
		
	}
	public String searchPwd(MemberDto dto) {
		return dao.searchPwd(dto);
	}

}







