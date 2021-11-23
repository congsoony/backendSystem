package kr.or.connect.reservation.controller.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.or.connect.reservation.dto.Product;
import kr.or.connect.reservation.dto.ReservationUserComment;
import kr.or.connect.reservation.service.DisplayInfoImageService;
import kr.or.connect.reservation.service.ProductImageService;
import kr.or.connect.reservation.service.ProductPriceService;
import kr.or.connect.reservation.service.ProductService;
import kr.or.connect.reservation.service.ReservationUserCommentService;

@RestController
@RequestMapping("/api")
public class DisplayInfoApiController {
	@Autowired
	private ProductService productService;
	@Autowired
	private ProductImageService productImageService;
	@Autowired
	private DisplayInfoImageService displayInfoImageService;
	@Autowired
	private ReservationUserCommentService reservationUserCommentService;
	@Autowired
	private ProductPriceService productPriceService;

	@GetMapping("/displayinfos")
	public ResponseEntity<Map<String, Object>> getDisplayInfos(
			@RequestParam(defaultValue = "0") int categoryId,
			@RequestParam(defaultValue = "0") int productId,
			@RequestParam(defaultValue = "0") int start) {
		// catgoryId와 productId 둘중 하나의 값만 올수있음
		if (categoryId != 0 && productId != 0) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		Map<String, Object> map = new HashMap<>();
		int totalCount;
		if (productId == 0) {
			List<Product> list;
			if (categoryId == 0) {
				list = productService.getDisplayAllInfos(start);
				totalCount = productService.getAllCount();
			}
			else {
				list = productService.getDisplayInfos(categoryId, start);
				totalCount = productService.getDisplayCount(categoryId);
			}
			map.put("totalCount", totalCount);
			map.put("productCount", list.size());
			map.put("products", list);
		}
		else {
			List<ReservationUserComment> list =reservationUserCommentService.getDisplayInfos(productId, start);
			map.put("totalCount", reservationUserCommentService.getTotalCount(productId));
			map.put("commentCount", list.size());
			map.put("reservationUserComments", list);
		}
		return new ResponseEntity<>(map, HttpStatus.OK);

	}

	@GetMapping("/displayinfos/{displayId}")
	public ResponseEntity<Map<String, Object>> getDisplayInfos(@PathVariable int displayId) {
		Map<String, Object> map = new HashMap<>();
		try {
			map.put("product", productService.getDisplayInfo(displayId));
			map.put("productImages", productImageService.getDisplayInfos(displayId));
			map.put("displayInfoImages", displayInfoImageService.getDisplayInfos(displayId));
			map.put("avgScore", reservationUserCommentService.getAvgScore(displayId));
			map.put("productPrices", productPriceService.getDisplayInfos(displayId));
		} catch (RuntimeException e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(map, HttpStatus.OK);
	}

}
