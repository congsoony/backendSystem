package kr.or.connect.reservation.controller.api;

import java.security.Principal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
		// 날짜형식 잘못된경우
		try {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd");
			LocalDate.parse(reservationRequest.getReservationYearMonthDay(), formatter);
		} catch (DateTimeParseException e) {
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
