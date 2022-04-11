package mul.camp.a.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import mul.camp.a.dto.MemberDto;

@Mapper
@Repository
public interface MemberDao {

	public int getId(MemberDto dto);	
	public int addmember(MemberDto dto);
	
	public MemberDto login(MemberDto dto);
	
	//아이디찾기
	public String searchId(MemberDto dto);
	
	//비밀번호 찾기
	public String searchPwd(MemberDto dto);
}




