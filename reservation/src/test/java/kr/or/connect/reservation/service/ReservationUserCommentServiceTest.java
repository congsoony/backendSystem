package kr.or.connect.reservation.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kr.or.connect.reservation.config.ApplicationConfig;
import kr.or.connect.reservation.dto.FileInfoTable;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ApplicationConfig.class})
public class ReservationUserCommentServiceTest {
	
	@Autowired
	private ReservationUserCommentService reservationUserCommentService;
	
	
	@Test
	public void postCommentTest() {
		String email= "carami@connect.co.kr";
		int productId = reservationUserCommentService.postComment(1, 3, "멋진곳이군", email);
		System.out.println(productId);
		System.out.println("성공");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void errorTest() {
		String email="kimjinsu@connect.co.kr";
		int productId = reservationUserCommentService.postComment(1, 3, "멋지다", email);
	}
	
	@Test
	public void addCommentAndFileTest() {
		String email= "carami@connect.co.kr";
		FileInfoTable fileData= new FileInfoTable();
		String fileName = "1_map_1.png";
		String saveFileName = "img_map/1_map_1.png";
		String contentType= "image/png";
		fileData.setFileName(fileName);
		fileData.setSaveFileName(saveFileName);
		fileData.setContentType(contentType);
		int productId = reservationUserCommentService.addCommentAndFile(1, 3, "멋진곳이군", email, fileData);
		
		System.out.println("productid : "+productId);
		System.out.println("성공");
	}
}
