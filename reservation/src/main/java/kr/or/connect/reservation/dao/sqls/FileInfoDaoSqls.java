package kr.or.connect.reservation.dao.sqls;

public class FileInfoDaoSqls {
	public static final String INSERT_FILE_INFO="insert into file_info(file_name,save_file_name,content_type,delete_flag,create_date,modify_date)\r\n"
			+ "values(:fileName,:saveFileName,:contentType,0,now(),now())";
	
}
