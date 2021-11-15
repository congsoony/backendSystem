package kr.or.connect.reservation.service;

import java.util.List;

import kr.or.connect.reservation.dto.ReservationUserComment;

public interface ReservationUserCommentService {
	public static final Integer LIMIT = 5;
	public Integer getAvgScore(int displayId);

	public List<ReservationUserComment> getDisplayInfos(int productId, int start);

	public Integer getTotalCount(int productId);
}
