package kr.or.connect.reservation.dao;

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
	
	public static final String SELECT_ALL = "select ruci.id as id, ri.id as reservation_info_id, ruci.id as reservation_user_comment_id,\r\n"
			+ "fi.id as file_id, fi.file_name as file_name, fi.save_file_name as save_file_name,\r\n"
			+ "fi.content_type as content_type, fi.delete_flag as delete_flag, \r\n"
			+ "fi.create_date as create_date, fi.modify_date as modify_date\r\n"
			+ "from reservation_user_comment ruc\r\n"
			+ "inner join reservation_user_comment_image ruci on ruc.id =ruci.reservation_user_comment_id\r\n"
			+ "inner join reservation_info ri on ri.id = ruc.reservation_info_id\r\n"
			+ "inner join file_info fi on fi.id = ruci.file_id\r\n";
	public static final String BY_RESERVATION_USER_COMMENT_ID = "where ruci.id = :reservationUserCommentId\r\n";
	@Test
	public void selectTest() {
		List<ReservationUserCommentImage> list=dao.getReservationUserCommentImages(1);
	
		for(ReservationUserCommentImage i :list) {
			System.out.println(i);
		}
	}
	@Test
	public void print() {
		System.out.println(SELECT_ALL + BY_RESERVATION_USER_COMMENT_ID);
		
	}
}
