package mul.camp.a.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mul.camp.a.dao.FoodMealsListDao;

@Service
@Transactional
public class FoodMealsListService {
	@Autowired
	FoodMealsListDao dao;
	
	
}
