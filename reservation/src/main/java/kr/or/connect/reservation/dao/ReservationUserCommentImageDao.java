package kr.or.connect.reservation.dao;

import static kr.or.connect.reservation.dao.sqls.ReservationUserCommentImageDaoSqls.BY_RESERVATION_INFO_ID;
import static kr.or.connect.reservation.dao.sqls.ReservationUserCommentImageDaoSqls.SELECT_ALL;

import java.util.Collections;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import kr.or.connect.reservation.dto.ReservationUserCommentImage;
import kr.or.connect.reservation.dto.ReservationUserCommentImageTable;
import static kr.or.connect.reservation.dao.sqls.ReservationUserCommentImageDaoSqls.*;
@Repository
public class ReservationUserCommentImageDao {
	private NamedParameterJdbcTemplate jdbc;
	private RowMapper<ReservationUserCommentImage> rowMapper = BeanPropertyRowMapper.newInstance(ReservationUserCommentImage.class);
	public ReservationUserCommentImageDao(DataSource dataSource) {
		this.jdbc= new NamedParameterJdbcTemplate(dataSource);
	}
	public List<ReservationUserCommentImage> getReservationUserCommentImages(int reservationInfoId){
		return jdbc.query(SELECT_ALL+BY_RESERVATION_INFO_ID, Collections.singletonMap("reservationInfoId",reservationInfoId),rowMapper);
	}
	public Integer insertReservationUserCommentImage(int reservationInfoId,int reservationUserCommentId,int fileId) {
		ReservationUserCommentImageTable data =new ReservationUserCommentImageTable();
		data.setReservationInfoId(reservationInfoId);
		data.setReservationUserCommentId(reservationUserCommentId);
		data.setFileId(fileId);
		BeanPropertySqlParameterSource params =new BeanPropertySqlParameterSource(data);
		KeyHolder keyHolder =new GeneratedKeyHolder();
		jdbc.update(INSERT_USER_COMMENT_IMAGE, params,keyHolder);
		return keyHolder.getKey().intValue();
	}
}
