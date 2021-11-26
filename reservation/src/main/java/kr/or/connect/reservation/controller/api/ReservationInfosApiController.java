package kr.or.connect.reservation.controller.api;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mysql.fabric.Response;

import kr.or.connect.reservation.dto.MyReservationInfo;
import kr.or.connect.reservation.dto.ReservationInfoResponse;
import kr.or.connect.reservation.dto.ReservationInfosRequest;
import kr.or.connect.reservation.service.ReservationInfosService;

@RestController
@RequestMapping("/api/reservationInfos")
public class ReservationInfosApiController {
	@Autowired
	private ReservationInfosService reservationInfosService;

	@GetMapping
	public ResponseEntity<Map<String, Object>> getReservationInfos(Principal principal) {
		Map<String, Object> map = new HashMap<>();
		List<MyReservationInfo> list = reservationInfosService.getMyReservationInfos(principal.getName());
		map.put("size", list.size());
		map.put("items", list);
		return new ResponseEntity<>(map, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<Object> makeReservation(@RequestBody ReservationInfosRequest reservationRequest,
			Principal principal) {
		String pattern = "^\\d{4}\\.(0[1-9]|1[012])\\.(0[1-9]|[12][0-9]|3[01])$";
		// 날짜형식 잘못된경우
		if (reservationRequest.getReservationYearMonthDay().matches(pattern) == false) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		ReservationInfoResponse data;
		try {
			data = reservationInfosService.makeReservation(reservationRequest, principal.getName());
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(data, HttpStatus.OK);
	}

	@PutMapping
	public ResponseEntity<Map<String, Object>> cancelReservation(@RequestBody Map<String, Object> req,
			Principal principal) {

		Integer id = (Integer) req.get("id");

		Map<String, Object> map = new HashMap<>();
		int result = reservationInfosService.cancelReservation(id, principal.getName());
		String res = (result >= 0 ? "success" : "fail");

		map.put("result", res);
		return new ResponseEntity<>(map, HttpStatus.OK);
	}
}
