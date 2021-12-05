package kr.or.connect.reservation.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.connect.reservation.dao.FileInfoDao;
import kr.or.connect.reservation.dao.ReservationInfoDao;
import kr.or.connect.reservation.dao.ReservationUserCommentDao;
import kr.or.connect.reservation.dao.ReservationUserCommentImageDao;
import kr.or.connect.reservation.dao.UserDao;
import kr.or.connect.reservation.dto.FileInfoTable;
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

	@Autowired
	private FileInfoDao fileInfoDao;
	@Autowired
	private ReservationUserCommentImageDao reservationUserCommentImageDao;

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
		int productId = reservationInfoDao.getProductId(reservationInfoId);
		dao.insertReservationUserComment(reservationInfoId, score, comment, productId, userId);
		return productId;
	}

	@Override
	@Transactional
	public Integer addCommentAndFile(int reservationInfoId, int score, String comment, String email,
			FileInfoTable fileData)throws IllegalArgumentException {
		int userId = userDao.getUserIdByEmail(email);
		if (reservationInfoDao.existReservationInfo(reservationInfoId, userId) == false)
			throw new IllegalArgumentException();

		int productId = reservationInfoDao.getProductId(reservationInfoId);
		int reservationUserCommentId = dao.insertReservationUserComment(reservationInfoId, score, comment, productId, userId);
		int fileId = fileInfoDao.insertFileInfo(fileData.getFileName(), fileData.getSaveFileName(), fileData.getContentType());
		reservationUserCommentImageDao.insertReservationUserCommentImage(reservationInfoId, reservationUserCommentId, fileId);
		return productId;
	}

	@Override
	@Transactional(readOnly = true)
	public List<UserComment> getComments(int productId, int start) {
		List<UserComment> list = dao.selectUserCommentByProductId(productId, start, LIMIT);
		for(UserComment d:list) {
			d.setReservationUserCommentImages(reservationUserCommentImageDao.getReservationUserCommentImages(d.getReservationInfoId()));
		}
		return list;
	}

	@Override
	@Transactional(readOnly = true)
	public List<UserComment> getAllComments(int start) {
		List<UserComment> list = dao.selectAllUserComment(start, LIMIT);
		for(UserComment d:list) {
			d.setReservationUserCommentImages(reservationUserCommentImageDao.getReservationUserCommentImages(d.getReservationInfoId()));
		}
		return list;
	}

	@Override
	@Transactional(readOnly = true)
	public Integer getAllTotalCount() {
		return dao.getAllTotalCount();
	}

	
	
	
}
