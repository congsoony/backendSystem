package kr.or.connect.reservation.dao;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import kr.or.connect.reservation.dto.ReservationUserComment;
import static kr.or.connect.reservation.dao.sqls.ReservationUserCommentDaoSqls.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ReservationUserCommentDao {
	private NamedParameterJdbcTemplate jdbc;
	private RowMapper<ReservationUserComment> rowMapper = BeanPropertyRowMapper.newInstance(ReservationUserComment.class);
	public ReservationUserCommentDao(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
	}
	public int getAvgScoreByDisplayInfoId(int displayId) {
		return jdbc.queryForObject(SELECT_AVG_SCORE+BY_DISPLAY_INFO_ID,Collections.singletonMap("displayInfoId", displayId), Integer.class);
	}
	public List<ReservationUserComment> selectByProductId(int productId,int start,int limit) {
		Map<String, Integer> params =new HashMap<>();
		params.put("start", start);
		params.put("limit", limit);
		params.put("productId", productId);
		return jdbc.query(SELECT_USER_COMMENT+LIMIT_BY_PRODUCT_ID,params,rowMapper);
	}
	public int getTotalCountByProductId(int productId) {
		return jdbc.queryForObject(SELECT_TOTAL_COUNT+BY_PRODUCT_ID,Collections.singletonMap("productId", productId), Integer.class);
	}
}
