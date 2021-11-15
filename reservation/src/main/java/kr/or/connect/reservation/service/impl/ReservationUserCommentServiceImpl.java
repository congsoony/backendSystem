package kr.or.connect.reservation.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.connect.reservation.dao.ReservationUserCommentDao;
import kr.or.connect.reservation.dto.ReservationUserComment;
import kr.or.connect.reservation.service.ReservationUserCommentService;

@Service
public class ReservationUserCommentServiceImpl implements ReservationUserCommentService{
	@Autowired
	private ReservationUserCommentDao dao;
	
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

}
