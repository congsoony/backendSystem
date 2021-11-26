package kr.or.connect.reservation.service;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import junit.framework.Assert;
import kr.or.connect.reservation.config.ApplicationConfig;
import kr.or.connect.reservation.dto.MyReservationInfo;
import kr.or.connect.reservation.dto.ReservationInfoPrice;
import kr.or.connect.reservation.dto.ReservationInfoResponse;
import kr.or.connect.reservation.dto.ReservationInfosRequest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ApplicationConfig.class})
public class ReservationInfosTest {
	@Autowired
	private ReservationInfosService reservationInfosService;
	public static final String SELECT_RESERVATIONS="select ri.id as id, p.id as product_id,di.id as display_info_id,\r\n"
			+ "ri.cancel_flag as cancel_flag,p.description as product_description,\r\n"
			+ "p.content as product_content,u.id as user_id,sum(pp.price) as sum_price,\r\n"
			+ "ri.reservation_date as reservation_date,ri.create_date as create_date,\r\n"
			+ "ri.modify_date as modify_date from reservation_info ri\r\n"
			+ "inner join user u on u.id = ri.user_id\r\n"
			+ "inner join reservation_info_price rip on rip.reservation_info_id =ri.id\r\n"
			+ "inner join product_price pp on pp.id = rip.product_price_id\r\n"
			+ "inner join product p on p.id = pp.product_id\r\n"
			+ "inner join display_info di on di.product_id = p.id\r\n";
	public static final String BY_USER_EMAIL ="where u.email =:email\r\n"
			+ "group by ri.id";
	@Test
	public void makeReservation() {
		ReservationInfosRequest data = new ReservationInfosRequest();
		List<ReservationInfoPrice> list =new ArrayList<>();
		for(int i=0;i<3;i++) {
			ReservationInfoPrice price =new ReservationInfoPrice();
			price.setCount(2);
			price.setProductPriceId(3+i);
			list.add(price);
		}
		data.setProductId(1);
		data.setDisplayInfoId(1);
		data.setReservationYearMonthDay("2020.01.02");
		data.setUserId(1);	
		data.setPrices(list);
		String loginEmail = "carami@connect.co.kr";
		ReservationInfoResponse r= reservationInfosService.makeReservation(data,loginEmail);
		
		System.out.println(r);
		System.out.println("테스트 성공");
	}
	
	@Test
	public void printMyReservation() {
		String email="carami@connect.co.kr";
		List<MyReservationInfo> list =reservationInfosService.getMyReservationInfos(email);
		for(MyReservationInfo r:list)
			System.out.println(r);
	}
	
	@Test
	public void ReserveCancel() {
		String loginEmail ="carami@connect.co.kr";
		int res = reservationInfosService.cancelReservation(1, loginEmail);
		int errorRes = reservationInfosService.cancelReservation(2, loginEmail);
		System.out.println("Success : "+res);
		System.out.println("Fail : "+errorRes);
		assertEquals(-1, errorRes);
	}
	@Test
	public void Print() {
		System.out.println(SELECT_RESERVATIONS+BY_USER_EMAIL);
	}
}
