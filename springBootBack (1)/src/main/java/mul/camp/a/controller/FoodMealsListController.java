package mul.camp.a.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import mul.camp.a.service.FoodMealsListService;

@RestController
public class FoodMealsListController {

	
	@Autowired
	FoodMealsListService service;
}
