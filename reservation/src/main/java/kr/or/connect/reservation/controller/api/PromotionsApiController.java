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

import kr.or.connect.reservation.dto.Promotion;
import kr.or.connect.reservation.service.PromotionService;

@RestController
@RequestMapping("/api")
public class PromotionsApiController {

	@Autowired
	private PromotionService promotionService;
	
	@GetMapping("/promotions")
	public ResponseEntity<Map<String,Object>> getPromotions(){
		Map<String, Object> map=new HashMap<>();
		List<Promotion> list = promotionService.getPromotions();
		map.put("size", list.size());
		map.put("items", list);
		return new ResponseEntity<>(map,HttpStatus.OK);
	}
}
