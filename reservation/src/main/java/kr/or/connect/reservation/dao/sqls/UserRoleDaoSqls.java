package kr.or.connect.reservation.dao.sqls;

public class UserRoleDaoSqls {
	public static final String SELECT_ALL ="select ur.id as id, u.id as user_id,ur.role_name as role_name from user_role ur\r\n"
			+ "join user u on u.id =ur.user_id\r\n";
	public static final String BY_EMAIL="where u.email=:email";
}
