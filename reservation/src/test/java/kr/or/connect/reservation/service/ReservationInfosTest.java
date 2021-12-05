package kr.or.connect.reservation.service;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

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
}
