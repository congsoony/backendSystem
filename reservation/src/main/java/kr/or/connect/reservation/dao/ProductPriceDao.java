package kr.or.connect.reservation.dao;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import kr.or.connect.reservation.dto.ProductPrice;
import static kr.or.connect.reservation.dao.sqls.ProductPriceDaoSqls.*;

@Repository
public class ProductPriceDao {
	
	private NamedParameterJdbcTemplate jdbc;
	private SimpleJdbcInsert insertAction;
	private RowMapper<ProductPrice> rowMapper =BeanPropertyRowMapper.newInstance(ProductPrice.class);
	
	public ProductPriceDao (DataSource dataSource) {
		this.jdbc=new NamedParameterJdbcTemplate(dataSource);
		this.insertAction = new SimpleJdbcInsert(dataSource).withTableName("productprice").usingGeneratedKeyColumns("id");
	}
	
	public List<ProductPrice> selectByDisplayInfoId(int displayId) {
		return jdbc.query(SELECT_PRODUCT_PRICE+BY_DISPLAY_INFO_ID, Collections.singletonMap("displayInfoId", displayId),rowMapper);
	}
}
