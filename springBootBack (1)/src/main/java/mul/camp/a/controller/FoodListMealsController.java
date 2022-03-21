package mul.camp.a.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import mul.camp.a.dto.FoodListMealsDto;
import mul.camp.a.service.FoodListMealsService;

@RestController
public class FoodListMealsController {
	@Autowired
	FoodListMealsService service;
	
	//넘어오는지 확인
	@RequestMapping(value = "/FoodListTest" , method = {RequestMethod.GET, RequestMethod.POST})
	public String FoodListTest(@RequestBody FoodListMealsDto dto) {
		System.out.println("FoodMealsListController test");
		System.out.println(dto.toString());
		
		boolean a = service.writeFoodList(dto);
		if(a) {
			return "잘넘어옴";
		}else return "안넘어옴";
	}
	
	@RequestMapping(value="/FoodListSelect", method = {RequestMethod.GET})
	public List<FoodListMealsDto> writeFoodSelect(){
		System.out.println("FoodListMealsService writeFoodList " );
		List<FoodListMealsDto> list = service.writeFoodSelect();
		System.out.println(list.toString());
		return list;
	}
}
