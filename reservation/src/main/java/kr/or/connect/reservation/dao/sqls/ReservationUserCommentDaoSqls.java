package kr.or.connect.reservation.dao.sqls;

public class ReservationUserCommentDaoSqls {
	public static final String SELECT_AVG_SCORE="select ifnull(floor(avg(ruc.score)),0) as avg_score from reservation_user_comment ruc\r\n"
			+ "inner join display_info di on ruc.product_id = di.product_id\r\n";
	public static final String BY_DISPLAY_INFO_ID ="where di.id = :displayInfoId";
	
	public static final String SELECT_USER_COMMENT = "select ruc.id as id, ruc.product_id as product_id, ri.id as reservation_info_id,\r\n"
			+ "ruc.score as score,u.email as reservation_email , ruc.comment as comment, ruc.create_date as create_date, ruc.modify_date as modify_date\r\n"
			+ "from reservation_user_comment ruc \r\n"
			+ "inner join reservation_info ri on ri.id = ruc.reservation_info_id\r\n"
			+ "inner join user u on u.id = ruc.user_id\r\n";
	public static final String LIMIT_BY_PRODUCT_ID = "where ruc.product_id =:productId\r\n"
			+ "order by ruc.id desc limit :start,:limit";
	
	public static final String SELECT_TOTAL_COUNT="select count(*) as total_count\r\n"
			+ "from reservation_user_comment ruc \r\n"
			+ "inner join reservation_info ri on ri.id = ruc.reservation_info_id\r\n"
			+ "inner join user u on u.id = ruc.user_id\r\n";
	public static final String BY_PRODUCT_ID = "where ruc.product_id =:productId\r\n";
}
