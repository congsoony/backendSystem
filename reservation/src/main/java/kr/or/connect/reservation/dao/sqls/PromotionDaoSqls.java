package kr.or.connect.reservation.dao.sqls;

public class PromotionDaoSqls {
	public static final String SELECT_ALL ="select pr.id as id, p.id as product_id, c.id as category_id,c.name as category_name,p.description as description,fi.id as file_id\r\n"
			+ "from  promotion pr\r\n"
			+ "inner join product p on p.id=pr.product_id\r\n"
			+ "inner join category c on p.category_id = c.id\r\n"
			+ "inner join product_image pi on pi.product_id=p.id\r\n"
			+ "inner join file_info fi on fi.id =pi.file_id\r\n"
			+ "where pi.type='ma'\r\n";
			
}
