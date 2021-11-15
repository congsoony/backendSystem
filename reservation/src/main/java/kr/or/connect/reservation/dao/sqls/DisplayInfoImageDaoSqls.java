package kr.or.connect.reservation.dao.sqls;

public class DisplayInfoImageDaoSqls {
	public static final String SELECT_DI_IMAGE="select dii.id as id ,di.id as display_info_id,\r\n"
			+ "fi.id as file_id, fi.file_name as file_name,\r\n"
			+ "fi.save_file_name as save_file_name,\r\n"
			+ "fi.content_type as content_type,\r\n"
			+ "fi.delete_flag as delete_flag,\r\n"
			+ "di.create_date as create_date,\r\n"
			+ "di.modify_date as modify_date\r\n"
			+ "from display_info_image dii\r\n"
			+ "inner join display_info di on dii.display_info_id = di.id\r\n"
			+ "inner join file_info fi on dii.file_id = fi.id\r\n";
	public static final String BY_DISPLAY_INFO_ID ="where di.id = :displayInfoId";
}
