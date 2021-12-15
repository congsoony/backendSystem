package kr.or.connect.reservation.controller.api;

import java.security.Principal;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import kr.or.connect.reservation.dto.UserComment;
import kr.or.connect.reservation.service.ReservationUserCommentService;

@RestController
@RequestMapping("/api/comments")
public class CommentApiController {

	@Autowired
	private ReservationUserCommentService reservationUserCommentService;
	
	@Value("${img.path}")
	private String rootPath;
	
	@PostMapping
	public ResponseEntity<Map<String, Object>> postComment(@RequestParam int reservationInfoId, @RequestParam int score,
			@RequestParam String comment, @RequestParam(required = false) MultipartFile file, Principal principal) {
		int productId;
		Map<String, Object> map = new HashMap<>();
		try {
			productId = reservationUserCommentService.postComment(rootPath,reservationInfoId, score, comment,
					principal.getName(), file);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(Collections.singletonMap("result", "fail"), HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			return new ResponseEntity<>(Collections.singletonMap("result", "fail"), HttpStatus.BAD_REQUEST);
		}

		map.put("result", "success");
		map.put("productId", productId);
		return new ResponseEntity<>(map, HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<Map<String, Object>> getComments(@RequestParam(defaultValue = "0") int productId,
			@RequestParam(defaultValue = "0") int start) {
		Map<String, Object> map = new HashMap<>();
		List<UserComment> list;
		if (productId == 0) {
			list = reservationUserCommentService.getAllComments(start);
			map.put("totalCount", reservationUserCommentService.getAllTotalCount());
		} else {
			list = reservationUserCommentService.getComments(productId, start);
			map.put("totalCount", reservationUserCommentService.getTotalCount(productId));
		}
		map.put("commentCount", list.size());
		map.put("reservationUserComments", list);
		return new ResponseEntity<>(map, HttpStatus.OK);
	}
}
