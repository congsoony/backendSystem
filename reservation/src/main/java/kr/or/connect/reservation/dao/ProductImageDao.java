package kr.or.connect.reservation.dao;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import kr.or.connect.reservation.dto.ProductImage;

import static kr.or.connect.reservation.dao.sqls.ProductImageDaoSqls.*;

import java.util.Collections;
import java.util.Map;
@Repository
public class ProductImageDao {
	private NamedParameterJdbcTemplate jdbc;
	private RowMapper<ProductImage> rowMapper = BeanPropertyRowMapper.newInstance(ProductImage.class);
	
	public ProductImageDao(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
	}
	
	public ProductImage selectByDisplayInfoId(int displayId) {
		Map<String, Integer> params = Collections.singletonMap("displayInfoId",displayId);
		return jdbc.queryForObject(SELECT_PRODUCT_IMAGE+BY_DISPLAY_INFO_ID,params,rowMapper);
	}
}
