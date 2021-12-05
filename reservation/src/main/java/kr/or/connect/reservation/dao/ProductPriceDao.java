package kr.or.connect.reservation.dao;

import static kr.or.connect.reservation.dao.sqls.ProductPriceDaoSqls.BY_DISPLAY_INFO_ID;
import static kr.or.connect.reservation.dao.sqls.ProductPriceDaoSqls.SELECT_PRODUCT_PRICE;

import java.util.Collections;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import kr.or.connect.reservation.dto.ProductPrice;

@Repository
public class ProductPriceDao {
	
	private NamedParameterJdbcTemplate jdbc;
	private RowMapper<ProductPrice> rowMapper =BeanPropertyRowMapper.newInstance(ProductPrice.class);
	
	public ProductPriceDao (DataSource dataSource) {
		this.jdbc=new NamedParameterJdbcTemplate(dataSource);
	}
	
	public List<ProductPrice> selectByDisplayInfoId(int displayId) {
		return jdbc.query(SELECT_PRODUCT_PRICE+BY_DISPLAY_INFO_ID, Collections.singletonMap("displayInfoId", displayId),rowMapper);
	}
}
