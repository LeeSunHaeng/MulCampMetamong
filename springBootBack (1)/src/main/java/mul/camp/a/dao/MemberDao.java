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
}




