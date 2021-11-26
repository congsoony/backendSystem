package kr.or.connect.reservation.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.connect.reservation.dao.ReservationInfoDao;
import kr.or.connect.reservation.dao.ReservationInfoPriceDao;
import kr.or.connect.reservation.dto.MyReservationInfo;
import kr.or.connect.reservation.dto.ReservationInfo;
import kr.or.connect.reservation.dto.ReservationInfoPrice;
import kr.or.connect.reservation.dto.ReservationInfoResponse;
import kr.or.connect.reservation.dto.ReservationInfosRequest;
import kr.or.connect.reservation.service.ReservationInfosService;

@Service
public class ReservationInfosServiceImpl implements ReservationInfosService {

	@Autowired
	private ReservationInfoDao reservationInfoDao;
	@Autowired
	private ReservationInfoPriceDao reservationInfoPriceDao;

	@Override
	@Transactional
	public ReservationInfoResponse makeReservation(ReservationInfosRequest data) {
		int reservationInfoId = reservationInfoDao.insertReseravtionInfos(data);
		for (ReservationInfoPrice prices : data.getPrices()) {
			reservationInfoPriceDao.insertReservationInfoPrice(prices.getCount(), prices.getProductPriceId(),
					reservationInfoId);
		}
		ReservationInfoResponse res = new ReservationInfoResponse();

		ReservationInfo info = reservationInfoDao.getReservationInfo(reservationInfoId);
		List<ReservationInfoPrice> list = reservationInfoPriceDao.getReservationInfoPrices(reservationInfoId);

		res.setReservationInfo(info);
		res.setPrices(list);

		return res;
	}

	@Override
	public ReservationInfo getReservationInfo(int id) {
		return reservationInfoDao.getReservationInfo(id);
	}

	@Override
	public List<MyReservationInfo> getMyReservationInfos(String email) {
		return reservationInfoDao.getMyReservationInfos(email);
	}

}
