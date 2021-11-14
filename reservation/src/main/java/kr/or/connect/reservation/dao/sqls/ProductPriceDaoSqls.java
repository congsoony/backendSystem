package kr.or.connect.reservation.dao.sqls;

public class ProductPriceDaoSqls {
	public static final String SELECT_PRODUCT_PRICE = "select pp.id as id, pp.product_id as product_id, pp.price_type_name as price_type_name,\r\n"
			+ "pp.price as price, pp.discount_rate as discount_rate,\r\n"
			+ "di.create_date as create_date,di.modify_date as modify_date\r\n"
			+ "from product_price as pp\r\n"
			+ "inner join display_info di on di.product_id = pp.product_id\r\n";
	public static final String BY_DISPLAY_INFO_ID = "where di.id = :displayInfoId order by pp.id desc";
}
