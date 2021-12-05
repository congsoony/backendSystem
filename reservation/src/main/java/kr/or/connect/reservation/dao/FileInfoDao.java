package kr.or.connect.reservation.dao;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import kr.or.connect.reservation.dto.FileInfoTable;
import static kr.or.connect.reservation.dao.sqls.FileInfoDaoSqls.*;

import java.util.Collections;

@Repository
public class FileInfoDao {
	private NamedParameterJdbcTemplate jdbc;
	private RowMapper<FileInfoTable> rowMapper = BeanPropertyRowMapper.newInstance(FileInfoTable.class);

	public FileInfoDao(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
	}

	public Integer insertFileInfo(String fileName, String saveFileName, String contentType) {
		FileInfoTable data = new FileInfoTable();
		data.setFileName(fileName);
		data.setSaveFileName(saveFileName);
		data.setContentType(contentType);
		BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(data);
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbc.update(INSERT_FILE_INFO, params, keyHolder);
		return keyHolder.getKey().intValue();
	}
	
	public FileInfoTable selectFileInfo(int id) {
		return jdbc.queryForObject(SELECT_ALL+BY_ID, Collections.singletonMap("id", id), rowMapper);
	}
}
