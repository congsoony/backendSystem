package kr.or.connect.reservation.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import kr.or.connect.reservation.dto.FileInfoTable;
import kr.or.connect.reservation.dto.ReservationUserComment;

public interface ReservationUserCommentService {
	Integer LIMIT = 5;
	Integer getAvgScore(int displayId);

	List<ReservationUserComment> getDisplayInfos(int productId, int start);

	Integer getTotalCount(int productId);
	Integer postComment(int reservationInfoId,int score,String comment,String email);
	Integer addCommentAndFile(int reservationInfoId, int score, String comment, String email,FileInfoTable fileData);
		
}
