package mul.camp.a.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mul.camp.a.dao.SnsDao;
import mul.camp.a.dto.MemberDto;
import mul.camp.a.dto.SnsCommentDto;
import mul.camp.a.dto.SnsDto;
import mul.camp.a.dto.SnsLikeDto;

@Service
@Transactional
public class SnsService {
	
	@Autowired
	SnsDao dao;
	
	public int snsInsert(SnsDto dto) {
		System.out.println("service 실행");
		return dao.snsInsert(dto);
	}
	
	public int snsCommentInsert(SnsCommentDto dto) {
		System.out.println("snsCommentInsert service 실행");
		return dao.snsCommentInsert(dto);
	}
	
	public MemberDto snsGetMmeber(String id) {
		System.out.println("snsGetMmeber service 실행");
		return dao.snsGetMmeber(id);
	}
	
	public ArrayList<SnsDto> allSns(){
		System.out.println("allSns service 실행");
		return dao.allSns();
	}
	
	public ArrayList<SnsCommentDto> allComment(int seq){
		System.out.println("allComment service 실행");
		return dao.allComment(seq);
	}
	
	public int snsLikeInsert(SnsLikeDto dto) {
		System.out.println("snsLikeInsert service 실행");
		return dao.snsLikeInsert(dto);
	}
	
	public int snsLikeDelete(SnsLikeDto dto) {
		System.out.println("snsLikeDelete service 실행");
		return dao.snsLikeDelete(dto);
	}
	
	public int snsLikeCheck(SnsLikeDto dto) {
		System.out.println("snsLikeCheck service 실행");
		return dao.snsLikeCheck(dto);
	}
	
	public int snsLikeCount(int seq) {
		System.out.println("snsLikeCount service 실행");
		return dao.snsLikeCount(seq);
	}
	public int snsCommentCount(int seq) {
		System.out.println("snsCommentCount service 실행");
		return dao.snsCommentCount(seq);
	}
}
