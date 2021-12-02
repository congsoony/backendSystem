package kr.or.connect.reservation.service;

import java.util.List;

import kr.or.connect.reservation.dto.MyReservationInfo;
import kr.or.connect.reservation.dto.ReservationInfo;
import kr.or.connect.reservation.dto.ReservationInfoResponse;
import kr.or.connect.reservation.dto.ReservationInfosRequest;

public interface ReservationInfosService {
	ReservationInfoResponse makeReservation(ReservationInfosRequest data,String loginEmail);
	List<MyReservationInfo> getMyReservationInfos(String email);
	int cancelReservation(int reservationInfoId,String loginEmail);
}
