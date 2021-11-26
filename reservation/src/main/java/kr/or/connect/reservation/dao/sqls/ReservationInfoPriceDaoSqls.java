package kr.or.connect.reservation.dao.sqls;

public class ReservationInfoPriceDaoSqls {
	public static final String SELECT_RESERVATION_INFO_PRICE="select id, reservation_info_id, product_price_id,count \r\n"
			+ "from reservation_info_price rip\r\n"
			+ "where rip.reservation_info_id=:reservationInfoId\r\n";
	
	public static final String INSERT_RESERVATION_INFO_PRICE="insert into reservation_info_price(reservation_info_id,product_price_id,count)\r\n"
			+ "values(:reservationInfoId,:productPriceId,:count)";
}
