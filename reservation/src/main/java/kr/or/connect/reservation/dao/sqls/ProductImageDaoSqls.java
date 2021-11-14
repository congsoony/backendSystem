package kr.or.connect.reservation.dao.sqls;

public class ProductImageDaoSqls {
	public static final String SELECT_PRODUCT_IMAGE = "select di.product_id as product_id, pi.id as product_image_id, \r\n"
			+ "pi.type as type, fi.id as file_info_id, fi.file_name as file_name,\r\n"
			+ "fi.save_file_name as save_file_name, fi.content_type as content_type, \r\n"
			+ "fi.delete_flag as delete_flag, di.create_date as create_date, di.modify_date as modify_date\r\n"
			+ "from product_image pi\r\n"
			+ "inner join display_info di on pi.product_id = di.product_id\r\n"
			+ "inner join file_info fi on fi.id = pi.file_id\r\n";
	public static final String BY_DISPLAY_INFO_ID = "where pi.type='ma' and di.id =:displayInfoId";
	
}
