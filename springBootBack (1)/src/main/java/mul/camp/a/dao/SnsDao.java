package mul.camp.a.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import mul.camp.a.dto.MemberDto;
import mul.camp.a.dto.SnsDto;

@Mapper
@Repository
public interface SnsDao {
	public int snsInsert(SnsDto dto);
	public MemberDto snsGetMmeber(String id);
	public ArrayList<SnsDto> allSns();
}
