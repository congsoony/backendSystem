package kr.or.connect.reservation.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.connect.reservation.dao.ReservationInfoDao;
import kr.or.connect.reservation.dao.ReservationUserCommentDao;
import kr.or.connect.reservation.dao.UserDao;
import kr.or.connect.reservation.dto.ReservationUserComment;
import kr.or.connect.reservation.dto.UserComment;
import kr.or.connect.reservation.service.ReservationUserCommentService;

@Service
public class ReservationUserCommentServiceImpl implements ReservationUserCommentService {
	@Autowired
	private ReservationUserCommentDao dao;
	@Autowired
	private UserDao userDao;
	@Autowired
	private ReservationInfoDao reservationInfoDao;

	@Override
	@Transactional(readOnly = true)
	public Integer getAvgScore(int displayId) {
		return dao.getAvgScoreByDisplayInfoId(displayId);
	}

	@Override
	@Transactional(readOnly = true)
	public List<ReservationUserComment> getDisplayInfos(int productId, int start) {
		return dao.selectByProductId(productId, start, LIMIT);
	}

	@Override
	@Transactional(readOnly = true)
	public Integer getTotalCount(int productId) {
		return dao.getTotalCountByProductId(productId);
	}

	@Override
	@Transactional
	public Integer postComment(int reservationInfoId, int score, String comment, String email)
			throws IllegalArgumentException {
		int userId = userDao.getUserIdByEmail(email);
		if (reservationInfoDao.existReservationInfo(reservationInfoId, userId) == false)
			throw new IllegalArgumentException();
		
		Integer productId = reservationInfoDao.getProductId(reservationInfoId);
		UserComment data = new UserComment();
		data.setReservationInfoId(reservationInfoId);
		data.setScore(score);
		data.setComment(comment);
		data.setProductId(productId);
		data.setUserId(userId);
		dao.insertReservationUserComment(data);
		return productId;
	}

}
