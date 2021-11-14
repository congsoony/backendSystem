package kr.or.connect.reservation.controller.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.or.connect.reservation.dto.Product;
import kr.or.connect.reservation.service.ProductService;

@RestController
@RequestMapping("/api")
public class DisplayInfoApiController {
	@Autowired
	private ProductService productService;

	@GetMapping("/displayinfos")
	public ResponseEntity<Map<String, Object>> getDisplayInfos(
			@RequestParam(name="categoryId", required=false, defaultValue="0") int categoryId,
			@RequestParam(name="start", required=false, defaultValue="0") int start) {
		Map<String, Object> map = new HashMap<>();
		List<Product> list;
		int totalCount;
		if(categoryId==0) {
			list=productService.getDisplayAllInfos(start);
			totalCount=productService.getAllCount();
		}
		else {
			list=productService.getDisplayInfos(categoryId, start);
			totalCount=productService.getDisplayCount(categoryId);
		}
		map.put("totalCount", totalCount);
		map.put("productCount", list.size());
		map.put("products", list);
		return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
	}
}
