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
	
	/* #21# ID 중복체크 */
	// true면 ID 중복이 존재하는 것
	public boolean idCheck(String id) {
		int result = dao.idCheck(id);
		
		return result>0?true:false;
	}
	
	/* #21# 회원정보 수정 */
	public boolean userUpdate(MemberDto dto) {
		int result = dao.userUpdate(dto);
		
		return result>0?true:false;
	}
}







