package kr.or.connect.reservation.dao;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kr.or.connect.reservation.config.ApplicationConfig;
import kr.or.connect.reservation.dto.ReservationUserCommentImage;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ApplicationConfig.class})
public class ReservationUserCommentImageDaoTest {
	@Autowired
	private ReservationUserCommentImageDao dao;
	
	@Test
	public void selectTest() {
		List<ReservationUserCommentImage> list=dao.getReservationUserCommentImages(1);
	
		for(ReservationUserCommentImage i :list) {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
			Timestamp stamp = Timestamp.valueOf(i.getCreateDate());
			
			System.out.println(Timestamp.valueOf(i.getCreateDate()).getTime());
			System.out.println(i.getCreateDate().format(formatter));
			System.out.println(i);
		}
	}
}
