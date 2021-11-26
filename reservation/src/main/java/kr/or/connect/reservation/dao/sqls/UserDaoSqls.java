package kr.or.connect.reservation.dao.sqls;

public class UserDaoSqls {
	public static final String SELECT_ALL = "select id,name,password,email,phone,create_date,modify_date \r\n"
			+ "from user\r\n";
	public static final String BY_EMAIL= "where email=:email";
	
	public static final String SELECT_ID ="select id from user\r\n";
}
