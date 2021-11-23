package kr.or.connect.reservation.service;

import java.util.List;

import kr.or.connect.reservation.dto.ReservationUserComment;

public interface ReservationUserCommentService {
	Integer LIMIT = 5;
	Integer getAvgScore(int displayId);

	List<ReservationUserComment> getDisplayInfos(int productId, int start);

	Integer getTotalCount(int productId);
}
