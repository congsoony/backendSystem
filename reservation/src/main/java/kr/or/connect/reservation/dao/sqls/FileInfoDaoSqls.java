package kr.or.connect.reservation.dao.sqls;

public class FileInfoDaoSqls {
	public static final String INSERT_FILE_INFO = "insert into file_info(file_name,save_file_name,content_type,delete_flag,create_date,modify_date)\r\n"
			+ "values(:fileName,:saveFileName,:contentType,0,now(),now())";
	public static final String SELECT_ALL = "select id,file_name,save_file_name,content_type,delete_flag,create_date,modify_date \r\n"
			+ "from file_info\r\n";
	public static final String BY_ID = "where id = :id";
}
