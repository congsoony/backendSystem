package kr.or.connect.reservation.controller.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.or.connect.reservation.dto.Category;
import kr.or.connect.reservation.service.CategoryService;

@RestController
@RequestMapping("/api")
public class CategoryApiController {
	@Autowired
	private CategoryService categoryService;
	
	@GetMapping("/categories")
	public ResponseEntity<Map<String,Object>> getCategories(){
		Map<String, Object> map =new HashMap<>();
		List<Category> items =categoryService.getCategories();
		map.put("size", items.size());
		map.put("items", items);
		return new ResponseEntity<>(map,HttpStatus.OK);
	}
}
