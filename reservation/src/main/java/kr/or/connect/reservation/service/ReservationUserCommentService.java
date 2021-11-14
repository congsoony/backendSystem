package kr.or.connect.reservation.service;

import java.util.List;

import kr.or.connect.reservation.dto.ReservationUserComment;

public interface ReservationUserCommentService {
	public static final Integer LIMIT = 5;
	public int getAvgScore(int displayId);

	public List<ReservationUserComment> getDisplayInfos(int productId, int start);

	public int getTotalCount(int productId);
}
