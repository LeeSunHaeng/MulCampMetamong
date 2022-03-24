package mul.camp.a.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import mul.camp.a.dto.FoodListMealsDto;
import mul.camp.a.service.FoodListMealsService;

@RestController
public class FoodListMealsController {
	@Autowired
	FoodListMealsService service;
	ServletContext servletContext;
	
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
	
	
	/*
	 * @RequestMapping(value = "/fileUpload",method = {RequestMethod.GET}) public
	 * String fileUpload( @RequestParam("uploadFile") MultipartFile uploadFile,
	 * HttpServletRequest req) { System.out.println("FileController fileUpload()");
	 * 
	 * //upload 폴더 생성 scr/main/webapp/upload에 String uploadPath =
	 * req.getServletContext().getRealPath("/upload");
	 * 
	 * 
	 * String filename = uploadFile.getOriginalFilename(); String filepath =
	 * uploadPath + File.separator + filename;
	 * 
	 * System.out.println("filepath:" + filepath);
	 * 
	 * try { BufferedOutputStream os = new BufferedOutputStream(new
	 * FileOutputStream(new File(filepath))); os.write(uploadFile.getBytes());
	 * os.close(); } catch (Exception e) { // TODO Auto-generated catch block
	 * e.printStackTrace();
	 * 
	 * return "file upload fail"; }
	 * 
	 * return "file upload success"; }
	 */
}




















