package mul.camp.a.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import mul.camp.a.dto.MemberDto;
import mul.camp.a.dto.SnsCommentDto;
import mul.camp.a.dto.SnsDto;
import mul.camp.a.dto.SnsLikeDto;

@Mapper
@Repository
public interface SnsDao {
	public int snsInsert(SnsDto dto);
	public MemberDto snsGetMmeber(String id);
	public ArrayList<SnsDto> allSns();
	public ArrayList<SnsCommentDto> allComment(int seq);
	public int snsLikeInsert(SnsLikeDto dto);
	public int snsLikeDelete(SnsLikeDto dto);
	public int snsLikeCheck(SnsLikeDto dto);
	public int snsLikeCount(int seq);
	public int snsCommentInsert(SnsCommentDto dto);
}
