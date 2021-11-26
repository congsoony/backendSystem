package kr.or.connect.reservation.dao;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import kr.or.connect.reservation.dto.ReservationInfoPrice;

import static kr.or.connect.reservation.dao.sqls.ReservationInfoPriceDaoSqls.*;

import java.util.Collections;
import java.util.List;

@Repository
public class ReservationInfoPriceDao {

	private NamedParameterJdbcTemplate jdbc;
	private RowMapper<ReservationInfoPrice> rowMapper=BeanPropertyRowMapper.newInstance(ReservationInfoPrice.class);

	public ReservationInfoPriceDao(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
	}

	public Integer insertReservationInfoPrice(int count, int productPriceId, int reservationInfoId) {
		ReservationInfoPrice rip = new ReservationInfoPrice();
		rip.setCount(count);
		rip.setProductPriceId(productPriceId);
		rip.setReservationInfoId(reservationInfoId);

		BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(rip);
		KeyHolder keyHolder = new GeneratedKeyHolder();

		jdbc.update(INSERT_RESERVATION_INFO_PRICE, params, keyHolder);
		return keyHolder.getKey().intValue();
	}

	public List<ReservationInfoPrice> getReservationInfoPrices(int reservationInfoId) {
		return jdbc.query(SELECT_RESERVATION_INFO_PRICE,
				Collections.singletonMap("reservationInfoId", reservationInfoId), rowMapper);
	}
	
}
