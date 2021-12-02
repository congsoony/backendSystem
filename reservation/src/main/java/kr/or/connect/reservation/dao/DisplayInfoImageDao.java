package kr.or.connect.reservation.dao;

import static kr.or.connect.reservation.dao.sqls.DisplayInfoImageDaoSqls.BY_DISPLAY_INFO_ID;
import static kr.or.connect.reservation.dao.sqls.DisplayInfoImageDaoSqls.SELECT_DI_IMAGE;

import java.util.Collections;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import kr.or.connect.reservation.dto.DisplayInfoImage;
@Repository
public class DisplayInfoImageDao {
	private NamedParameterJdbcTemplate jdbc;
	private RowMapper<DisplayInfoImage> rowMapper = BeanPropertyRowMapper.newInstance(DisplayInfoImage.class);
	
	public DisplayInfoImageDao(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
	}
	public List<DisplayInfoImage> selectByDisplayInfoId(int displayId) {
		return jdbc.query(SELECT_DI_IMAGE+BY_DISPLAY_INFO_ID, Collections.singletonMap("displayInfoId",displayId), rowMapper);	
	}
}
