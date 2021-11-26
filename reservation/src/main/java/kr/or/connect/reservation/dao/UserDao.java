package kr.or.connect.reservation.dao;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import kr.or.connect.reservation.dto.User;
import static kr.or.connect.reservation.dao.sqls.UserDaoSqls.*;

import java.util.Collections;

@Repository
public class UserDao {
	private NamedParameterJdbcTemplate jdbc;
	private RowMapper<User> rowMapper = BeanPropertyRowMapper.newInstance(User.class);

	public UserDao(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
	}

	public User getUserByEmail(String email) {
		return jdbc.queryForObject(SELECT_ALL + BY_EMAIL, Collections.singletonMap("email", email), rowMapper);
	}
	public Integer getUserIdByEmail(String email) {
		return jdbc.queryForObject(SELECT_ID+BY_EMAIL, Collections.singletonMap("email", email), Integer.class);
	}
}
