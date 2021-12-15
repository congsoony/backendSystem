package kr.or.connect.reservation.service.impl;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

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
	public Integer postComment(String rootPath,int reservationInfoId, int score, String comment, String email, MultipartFile file)
			throws IllegalArgumentException {
		int userId = userDao.getUserIdByEmail(email);
		if (reservationInfoDao.existReservationInfo(reservationInfoId, userId) == false)
			throw new IllegalArgumentException();
		int productId = reservationInfoDao.getProductId(reservationInfoId);
		// 여기까지 유저커맨트 입력
		int reservationUserCommentId = dao.insertReservationUserComment(reservationInfoId, score, comment, productId,
				userId);

		if (file != null) {
			// 이미지 타입아닌경우
			if (file.getContentType().startsWith("image") == false) {
				throw new IllegalArgumentException("이미지타입이 아님");
			}
			LocalDateTime date = LocalDateTime.now();
			
			// 날짜별로 폴더
			
			String dir = rootPath + File.separator + "img" + File.separator + date.getYear() + File.separator
					+ date.getMonthValue() + File.separator + date.getDayOfMonth() + File.separator;
			File dirFile = new File(dir);
			dirFile.mkdirs();
			
			String originalName = file.getOriginalFilename();
			String name = originalName.substring(0, originalName.lastIndexOf('.'));
			String type = originalName.substring(originalName.lastIndexOf('.'));
			String tempName = "img" + File.separator + date.getYear() + File.separator
					+ date.getMonthValue() + File.separator + date.getDayOfMonth() + File.separator;
			String saveFileName = tempName+ name + type;

			String fileName = name + type;
			File efile = new File(rootPath+File.separator+saveFileName);
			int num = 1;
			while (efile.exists()) {
				saveFileName = tempName + name + "_" + num + type;
				num++;
				efile = new File(rootPath+File.separator+saveFileName);
			}

			try (FileOutputStream fos = new FileOutputStream(rootPath+File.separator+saveFileName);
					InputStream is = file.getInputStream();
					BufferedOutputStream bout = new BufferedOutputStream(fos);
					BufferedInputStream bis = new BufferedInputStream(is);) {
				int readCount = 0;
				byte[] buffer = new byte[1024];
				while ((readCount = bis.read(buffer)) != -1) {
					bout.write(buffer, 0, readCount);
				}
			} catch (Exception ex) {
				throw new RuntimeException("file Save Error");
			}

			FileInfoTable fileData = new FileInfoTable();
			fileData.setFileName(fileName);
			fileData.setContentType(file.getContentType());
			fileData.setSaveFileName(saveFileName);
			// 파일 데이터 인서트
			int fileId = fileInfoDao.insertFileInfo(fileData.getFileName(), fileData.getSaveFileName(),
					fileData.getContentType());
			reservationUserCommentImageDao.insertReservationUserCommentImage(reservationInfoId,
					reservationUserCommentId, fileId);
		}
		return productId;
	}

	@Override
	@Transactional
	public Integer addCommentAndFile(int reservationInfoId, int score, String comment, String email,
			FileInfoTable fileData) throws IllegalArgumentException {
		int userId = userDao.getUserIdByEmail(email);
		if (reservationInfoDao.existReservationInfo(reservationInfoId, userId) == false)
			throw new IllegalArgumentException();

		int productId = reservationInfoDao.getProductId(reservationInfoId);
		// 여기까지 유저커맨트 입력
		int reservationUserCommentId = dao.insertReservationUserComment(reservationInfoId, score, comment, productId,
				userId);

		return productId;
	}

	@Override
	@Transactional(readOnly = true)
	public List<UserComment> getComments(int productId, int start) {
		List<UserComment> list = dao.selectUserCommentByProductId(productId, start, LIMIT);
		for (UserComment d : list) {
			d.setReservationUserCommentImages(
					reservationUserCommentImageDao.getReservationUserCommentImages(d.getReservationInfoId()));
		}
		return list;
	}

	@Override
	@Transactional(readOnly = true)
	public List<UserComment> getAllComments(int start) {
		List<UserComment> list = dao.selectAllUserComment(start, LIMIT);
		for (UserComment d : list) {
			d.setReservationUserCommentImages(
					reservationUserCommentImageDao.getReservationUserCommentImages(d.getReservationInfoId()));
		}
		return list;
	}

	@Override
	@Transactional(readOnly = true)
	public Integer getAllTotalCount() {
		return dao.getAllTotalCount();
	}

}
