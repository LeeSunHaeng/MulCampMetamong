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
	public int snsDelete(int seq);
	public int snsUpdate(SnsDto dto);
	public int snsImgUpdate(SnsDto dto);
	public MemberDto snsGetMmeber(String id);
	public ArrayList<SnsDto> allSns();
	public ArrayList<SnsCommentDto> allComment(int seq);
	public int snsLikeInsert(SnsLikeDto dto);
	public int snsLikeDelete(SnsLikeDto dto);
	public int snsLikeAllDelete(int seq);
	public int snsLikeCheck(SnsLikeDto dto);
	public int snsLikeCount(int seq);
	public int snsCommentCount(int seq);
	public int snsCommentInsert(SnsCommentDto dto);
	public int snsCommentAllDelete(int seq);
	public int snsCommentDelete(int cmtseq);
	public int snsCommentUpdate(SnsCommentDto dto);
	public int nextSeq();
	public int currSeq();
}
