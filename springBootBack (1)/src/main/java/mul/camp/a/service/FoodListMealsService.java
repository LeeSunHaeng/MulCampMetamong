package mul.camp.a.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mul.camp.a.dao.FoodListMealsDao;
import mul.camp.a.dto.FoodListMealsDto;
import mul.camp.a.dto.MemberDto;

@Service
@Transactional
public class FoodListMealsService {
	@Autowired
	FoodListMealsDao dao;
	
	public boolean writeFoodList(FoodListMealsDto dto) {
		int n = dao.writeFoodList(dto);
		return n>0?true:false;
	}

	public List<FoodListMealsDto> writeFoodSelect() {
		return dao.writeFoodSelect();
	}
	
	public boolean checkId(MemberDto dto) {
		int n = dao.checkId(dto);
		return n>0?true:false;
	}
	
	
	
	
}
