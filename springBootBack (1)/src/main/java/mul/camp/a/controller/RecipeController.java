package mul.camp.a.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import mul.camp.a.dto.RecipeDto;
import mul.camp.a.service.RecipeService;

@RestController
public class RecipeController {

	@Autowired
	RecipeService service;

	@RequestMapping(value = "/test", method = { RequestMethod.GET, RequestMethod.POST })
	public String test() {
		System.out.println("동작하나?");

		return "동작중";
	}
	
	
	
	
	@RequestMapping(value = "/getRecipe", method = {RequestMethod.GET, RequestMethod.POST} ) 
	public List<RecipeDto> getRecipe(@RequestBody RecipeDto dto) {		
		System.out.println("Controller 이상 무!");
	  
		List<RecipeDto> list = service.getRecipe(dto);
//		System.out.println("list"+ list);
		return list;
	}
	  
}
