package kr.or.connect.reservation.dao;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import kr.or.connect.reservation.config.ApplicationConfig;
import kr.or.connect.reservation.dto.ReservationInfoPrice;
import kr.or.connect.reservation.dto.ReservationInfosRequest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ApplicationConfig.class})
public class ReservationInfosTest {
	@Autowired
	private ReservationInfoDao dao;
	@Autowired
	private ReservationInfoPriceDao reservationInfoPriceDao;

	@Test
	public void insertTest() {
		ReservationInfosRequest r =new ReservationInfosRequest();
		r.setDisplayInfoId(1);
		r.setProductId(1);
		r.setUserId(1);
		r.setReservationYearMonthDay("2020.01.02");
		int key = dao.insertReseravtionInfos(r);
		int key2 = reservationInfoPriceDao.insertReservationInfoPrice(2, 3, key);	
		System.out.println("reservationInfoId :"+key);
		System.out.println("reservationInfoPriceId : "+key2);
	}
	
	@Test
	public void printReservationInfo() {
		System.out.println(dao.getReservationInfo(1));	
	}
	
	@Test 
	public void printReservaionInfoPrice() {
		List<ReservationInfoPrice> list = reservationInfoPriceDao.getReservationInfoPrices(1);
		for(ReservationInfoPrice r :list)
			System.out.println(r);
	
	}
}
