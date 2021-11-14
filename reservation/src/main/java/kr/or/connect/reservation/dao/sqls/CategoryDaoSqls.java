package kr.or.connect.reservation.dao.sqls;

public class CategoryDaoSqls {
	public static final String SELECT_ALL = "select c.id as id, c.name as name,count(*) as count \r\n"
			+ "from category c \r\n"
			+ "inner join product p on c.id = p.category_id \r\n"
			+ "inner join display_info di on p.id = di.product_id \r\n"
			+ "group by c.id\r\n";
}
