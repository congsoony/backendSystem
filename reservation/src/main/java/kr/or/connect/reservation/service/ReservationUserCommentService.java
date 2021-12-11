package kr.or.connect.reservation.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import kr.or.connect.reservation.dto.FileInfoTable;
import kr.or.connect.reservation.dto.ReservationUserComment;
import kr.or.connect.reservation.dto.UserComment;

public interface ReservationUserCommentService {
	Integer LIMIT = 5;
	Integer getAvgScore(int displayId);

	List<ReservationUserComment> getDisplayInfos(int productId, int start);

	Integer getTotalCount(int productId);
	Integer postComment(int reservationInfoId,int score,String comment,String email,MultipartFile file);
	Integer addCommentAndFile(int reservationInfoId, int score, String comment, String email,FileInfoTable fileData);
	List<UserComment> getComments(int productId,int start);
	List<UserComment> getAllComments(int start);
	Integer getAllTotalCount();
	
}
