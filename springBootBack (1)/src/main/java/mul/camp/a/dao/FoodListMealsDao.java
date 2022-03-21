package mul.camp.a.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import mul.camp.a.dto.FoodListMealsDto;

@Mapper
@Repository
public interface FoodListMealsDao {

	public int writeFoodList(FoodListMealsDto dto); // 나의식단 쓰기
	
	public List<FoodListMealsDto> writeFoodSelect(); // 나의식단 리스트 부르기
}
