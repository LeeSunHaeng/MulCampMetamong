package mul.camp.a.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import mul.camp.a.dto.SnsDto;

@Mapper
@Repository
public interface SnsDao {
	public int snsInsert(SnsDto dto);
}
