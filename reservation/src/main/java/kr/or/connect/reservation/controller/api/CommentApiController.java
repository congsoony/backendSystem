package kr.or.connect.reservation.controller.api;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.security.Principal;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import kr.or.connect.reservation.dto.FileInfoTable;
import kr.or.connect.reservation.dto.UserComment;
import kr.or.connect.reservation.service.ReservationUserCommentService;

@RestController
@RequestMapping("/api/comments")
public class CommentApiController {

	@Autowired
	private ReservationUserCommentService reservationUserCommentService;

	@PostMapping
	public ResponseEntity<Map<String, Object>> postComment(@RequestParam int reservationInfoId, @RequestParam int score,
			@RequestParam String comment, @RequestParam(required = false) MultipartFile file, Principal principal) {
		int productId;
		Map<String, Object> map = new HashMap<>();
		if (file == null || file.isEmpty()) {
			try {
				productId = reservationUserCommentService.postComment(reservationInfoId, score, comment,
						principal.getName());
			} catch (IllegalArgumentException e) {
				return new ResponseEntity<>(Collections.singletonMap("result", "fail"), HttpStatus.BAD_REQUEST);
			}

		} else {
			// 이미지 타입아닌경우
			if (file.getContentType().startsWith("image") == false) {
				return new ResponseEntity<>(Collections.singletonMap("result", "fail"), HttpStatus.BAD_REQUEST);
			}

			LocalDateTime date = LocalDateTime.now();
			String rootPath = System.getProperty("user.dir");
			// 날짜별로 폴더
			String dir = rootPath + File.separator + "img" + File.separator + date.getYear() + File.separator
					+ date.getMonthValue() + File.separator + date.getDayOfMonth() + File.separator;
			File dirFile = new File(dir);
			dirFile.mkdirs();

			String originalName = file.getOriginalFilename();
			String name = originalName.substring(0, originalName.lastIndexOf('.'));
			String type = originalName.substring(originalName.lastIndexOf('.'));
			String saveFileName = dir + name + type;
			String fileName = name + type;
			File efile = new File(saveFileName);
			int num = 1;
			while (efile.exists()) {
				saveFileName = dir + name + "_" + num + type;
				fileName = name + "_" + num + type;
				num++;
				efile = new File(saveFileName);
			}

			try (FileOutputStream fos = new FileOutputStream(saveFileName);
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

			try {
				FileInfoTable fileData = new FileInfoTable();
				fileData.setFileName(fileName);
				fileData.setContentType(file.getContentType());
				fileData.setSaveFileName(saveFileName);
				productId = reservationUserCommentService.addCommentAndFile(reservationInfoId, score, comment,
						principal.getName(), fileData);
			} catch (IllegalArgumentException e) {
				return new ResponseEntity<>(Collections.singletonMap("result", "fail"), HttpStatus.BAD_REQUEST);
			}
		}
		
		map.put("result", "success");
		map.put("productId", productId);
		return new ResponseEntity<>(map, HttpStatus.OK);
	}
	
	@GetMapping
	public ResponseEntity<Map<String, Object>> getComments(@RequestParam(defaultValue = "0") int productId,@RequestParam(defaultValue = "0") int start){
		Map<String,Object> map =new HashMap<>();
		List<UserComment> list;
		if(productId==0) {
			list = reservationUserCommentService.getAllComments(start);
			map.put("totalCount", reservationUserCommentService.getAllTotalCount());
		}
		else {
			list = reservationUserCommentService.getComments(productId, start);
			map.put("totalCount", reservationUserCommentService.getTotalCount(productId));		
		}		
		map.put("commentCount", list.size());
		map.put("reservationUserComments", list);
		return new ResponseEntity<>(map,HttpStatus.OK);
	}
}
