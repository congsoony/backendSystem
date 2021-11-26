package kr.or.connect.reservation.dao;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import kr.or.connect.reservation.dto.MyReservationInfo;
import kr.or.connect.reservation.dto.ReservationInfo;
import kr.or.connect.reservation.dto.ReservationInfosRequest;

import static kr.or.connect.reservation.dao.sqls.ReservationInfoDaoSqls.*;

import java.util.Collections;
import java.util.List;

@Repository
public class ReservationInfoDao {

	private NamedParameterJdbcTemplate jdbc;
	private RowMapper<ReservationInfo> rowMapper = BeanPropertyRowMapper.newInstance(ReservationInfo.class);
	private RowMapper<MyReservationInfo> myRowMapper = BeanPropertyRowMapper.newInstance(MyReservationInfo.class);

	public ReservationInfoDao(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
	}

	public Integer insertReseravtionInfos(ReservationInfosRequest data) {
		ReservationInfo r = new ReservationInfo();

		r.setProductId(data.getProductId());
		r.setDisplayInfoId(data.getDisplayInfoId());
		r.setReservationDate(data.getReservationYearMonthDay());
		r.setUserId(data.getUserId());

		BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(r);
		KeyHolder keyHolder = new GeneratedKeyHolder();

		jdbc.update(INSERT_RESERVATION_INFO, params, keyHolder);
		return keyHolder.getKey().intValue();
	}

	public List<MyReservationInfo> getMyReservationInfos(String email) {
		return jdbc.query(SELECT_RESERVATIONS + BY_USER_EMAIL, Collections.singletonMap("email", email),
				myRowMapper);
	}

	public ReservationInfo getReservationInfo(int id) {
		return jdbc.queryForObject(SELECT_RESERVATION_INFO, Collections.singletonMap("id", id), rowMapper);
	}

	public int deleteReservationInfo(int id) {
		return jdbc.update(DELETE_BY_ID, Collections.singletonMap("id", id));
	}
	public int updateCancelFlag(int id) {
		return jdbc.update(UPDATE_CANCLE_FLAG_BY_ID, Collections.singletonMap("id", id));
	}
	
	public String getReservationEmail(int id) {
		return jdbc.queryForObject(SELECT_RESERVATION_EMAIL, Collections.singletonMap("id", id), String.class);
	}
}
