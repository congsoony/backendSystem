package kr.or.connect.reservation.dao.sqls;

public class ProductDaoSqls {
	public static final String SELECT_ALL = "select p.id as id, c.id as category_id, di.id as display_info_id, c.name as name,p.description as description, p.content as content, p.event as event, di.opening_hours as opening_hours, di.place_name as place_name\r\n"
			+ ",di.place_lot as place_lot, di.place_street as place_street, di.tel as tel, di.homepage as homepage, di.email as email, p.create_date as create_date, p.modify_date as modify_date,fi.id as file_id\r\n"
			+ "from product p\r\n"
			+ "inner join category c on p.category_id = c.id\r\n"
			+ "inner join display_info di on p.id =di.product_id\r\n"
			+ "inner join display_info_image dii on di.id = dii.display_info_id\r\n"
			+ "inner join file_info fi on fi.id = dii.file_id\r\n";
	public static final String OF_LIMIT =  "limit :start,:limit";
	public static final String LIMIT_BY_CATEGORY_ID = "where c.id =:categoryId\r\n"
			+ "limit :start,:limit";
	public static final String ALL_COUNT = "select count(*) \r\n"
			+ "from product p\r\n"
			+ "inner join category c on p.category_id = c.id\r\n"
			+ "inner join display_info di on p.id = di.product_id\r\n";
	public static final String OF_CATEGORY = "where c.id =:categoryId\r\n";
	
	public static final String BY_DISPLAY_INFO_ID = "where di.id = :displayInfoId";
	
}
