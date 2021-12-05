package kr.or.connect.reservation.dao.sqls;

public class ReservationUserCommentImageDaoSqls {
	public static final String SELECT_ALL = "select ruci.id as id, ri.id as reservation_info_id, ruci.id as reservation_user_comment_id,\r\n"
			+ "fi.id as file_id, fi.file_name as file_name, fi.save_file_name as save_file_name,\r\n"
			+ "fi.content_type as content_type, fi.delete_flag as delete_flag, \r\n"
			+ "unix_timestamp(fi.create_date) as create_date, unix_timestamp(fi.modify_date) as modify_date\r\n"
			+ "from reservation_user_comment ruc\r\n"
			+ "inner join reservation_user_comment_image ruci on ruc.id =ruci.reservation_user_comment_id\r\n"
			+ "inner join reservation_info ri on ri.id = ruc.reservation_info_id\r\n"
			+ "inner join file_info fi on fi.id = ruci.file_id\r\n";
	public static final String BY_RESERVATION_INFO_ID = "where ri.id=:reservationInfoId\r\n";
	
	public static final String INSERT_USER_COMMENT_IMAGE="insert into reservation_user_comment_image(reservation_info_id, reservation_user_comment_id, file_id) \r\n"
			+ "values (:reservationInfoId, :reservationUserCommentId, :fileId)";
}
