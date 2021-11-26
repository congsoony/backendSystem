package kr.or.connect.reservation.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.connect.reservation.dao.ReservationInfoDao;
import kr.or.connect.reservation.dao.ReservationInfoPriceDao;
import kr.or.connect.reservation.dao.UserDao;
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

	@Autowired
	private UserDao userDao;
	
	@Override
	@Transactional
	public ReservationInfoResponse makeReservation(ReservationInfosRequest data,String loginEmail) throws IllegalArgumentException{
		Integer reservationInfoId = reservationInfoDao.insertReseravtionInfos(data);
		Integer loginId = userDao.getUserIdByEmail(loginEmail);
		
		if(data.getUserId()!=loginId) {
			throw new IllegalArgumentException("잘못된 접근입니다.");
		}
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
	@Transactional(readOnly = true)
	public ReservationInfo getReservationInfo(int id) {
		return reservationInfoDao.getReservationInfo(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<MyReservationInfo> getMyReservationInfos(String email) {
		return reservationInfoDao.getMyReservationInfos(email);
	}
	
	@Override
	@Transactional
	public int cancelReservation(int reservationInfoId,String loginEmail) {
		int updateCnt=0;
		String email = reservationInfoDao.getReservationEmail(reservationInfoId);
		//로그인 이메일과 예약자 이메일이 같은지
		if(email.equals(loginEmail)==false)
			return -1;		
		try {
			updateCnt=reservationInfoDao.updateCancelFlag(reservationInfoId);	
		}catch (Exception e) {
			return -1;
		}
		return updateCnt;
	}

}
