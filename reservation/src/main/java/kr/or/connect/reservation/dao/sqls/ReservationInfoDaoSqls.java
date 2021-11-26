package kr.or.connect.reservation.dao.sqls;

public class ReservationInfoDaoSqls {
	public static final String SELECT_RESERVATIONS = "select ri.id as id, p.id as product_id,di.id as display_info_id,\r\n"
			+ "ri.cancel_flag as cancel_flag,p.description as product_description,\r\n"
			+ "p.content as product_content,u.id as user_id,sum(pp.price*rip.count) as sum_price,\r\n"
			+ "ri.reservation_date as reservation_date,ri.create_date as create_date,\r\n"
			+ "ri.modify_date as modify_date from reservation_info ri\r\n"
			+ "inner join user u on u.id = ri.user_id\r\n"
			+ "inner join reservation_info_price rip on rip.reservation_info_id =ri.id\r\n"
			+ "inner join product_price pp on pp.id = rip.product_price_id\r\n"
			+ "inner join product p on p.id = pp.product_id\r\n"
			+ "inner join display_info di on di.product_id = p.id\r\n";
	public static final String BY_USER_EMAIL = "where u.email =:email\r\n" + 
			"group by ri.id\r\n"
			+"order by ri.id desc\r\n";
	public static final String INSERT_RESERVATION_INFO = "insert into reservation_info(product_id,display_info_id,user_id,reservation_date,create_date,modify_date)\r\n"
			+ "values(:productId,:displayInfoId,:userId,:reservationDate,now(),now())\r\n";

	public static final String SELECT_RESERVATION_INFO = "select id,product_id , cancel_flag, display_info_id, user_id,\r\n"
			+ "reservation_date, create_date, modify_date from reservation_info ri\r\n" + "where ri.id=:id\r\n";

	public static final String UPDATE_CANCLE_FLAG_BY_ID = "update reservation_info set cancel_flag =1,modify_date = now() where id =:id";

	public static final String SELECT_RESERVATION_EMAIL = "select u.email as email from reservation_info ri\r\n"
			+ "inner join user u on u.id = ri.user_id\r\n" + "where ri.id =:id";
}
