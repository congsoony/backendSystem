package kr.or.connect.reservation.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kr.or.connect.reservation.config.ApplicationConfig;

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
}
