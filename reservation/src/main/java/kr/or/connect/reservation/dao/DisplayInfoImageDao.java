package kr.or.connect.reservation.dao;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import kr.or.connect.reservation.dto.DisplayInfoImage;
import static kr.or.connect.reservation.dao.sqls.DisplayInfoImageDaoSqls.*;

import java.util.Collections;
import java.util.Map;
@Repository
public class DisplayInfoImageDao {
	private NamedParameterJdbcTemplate jdbc;
	private RowMapper<DisplayInfoImage> rowMapper = BeanPropertyRowMapper.newInstance(DisplayInfoImage.class);
	
	public DisplayInfoImageDao(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
	}
	public DisplayInfoImage selectByDisplayInfoId(int displayId) {
		return jdbc.queryForObject(SELECT_DI_IMAGE+BY_DISPLAY_INFO_ID, Collections.singletonMap("displayInfoId",displayId), rowMapper);	
	}
}
