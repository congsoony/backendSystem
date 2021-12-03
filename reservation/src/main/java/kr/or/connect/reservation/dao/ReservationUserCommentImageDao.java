package kr.or.connect.reservation.dao;

import java.util.Collections;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import kr.or.connect.reservation.dto.ReservationUserCommentImage;
import static kr.or.connect.reservation.dao.sqls.ReservationUserCommentImageDaoSqls.*;
@Repository
public class ReservationUserCommentImageDao {
	private NamedParameterJdbcTemplate jdbc;
	private RowMapper<ReservationUserCommentImage> rowMapper = BeanPropertyRowMapper.newInstance(ReservationUserCommentImage.class);
	public ReservationUserCommentImageDao(DataSource dataSource) {
		this.jdbc= new NamedParameterJdbcTemplate(dataSource);
	}
	List<ReservationUserCommentImage> getReservationUserCommentImages(int reservationUserCommentId){
		return jdbc.query(SELECT_ALL+BY_RESERVATION_USER_COMMENT_ID, Collections.singletonMap("reservationUserCommentId",reservationUserCommentId),rowMapper);
	}
}
