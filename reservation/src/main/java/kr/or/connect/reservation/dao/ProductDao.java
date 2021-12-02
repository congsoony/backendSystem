package kr.or.connect.reservation.dao;

import static kr.or.connect.reservation.dao.sqls.ProductDaoSqls.ALL_COUNT;
import static kr.or.connect.reservation.dao.sqls.ProductDaoSqls.BY_DISPLAY_INFO_ID;
import static kr.or.connect.reservation.dao.sqls.ProductDaoSqls.IS_EXIST_BY_DISPLAY_INFO_ID;
import static kr.or.connect.reservation.dao.sqls.ProductDaoSqls.LIMIT_BY_CATEGORY_ID;
import static kr.or.connect.reservation.dao.sqls.ProductDaoSqls.OF_CATEGORY;
import static kr.or.connect.reservation.dao.sqls.ProductDaoSqls.OF_LIMIT;
import static kr.or.connect.reservation.dao.sqls.ProductDaoSqls.SELECT_ALL;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import kr.or.connect.reservation.dto.Product;
@Repository
public class ProductDao {
	private NamedParameterJdbcTemplate jdbc;
	private SimpleJdbcInsert insertAction;
	private RowMapper<Product> rowMapper = BeanPropertyRowMapper.newInstance(Product.class);
	
	public ProductDao(DataSource dataSource) {
		this.jdbc=new NamedParameterJdbcTemplate(dataSource);
		this.insertAction = new SimpleJdbcInsert(dataSource).withTableName("product")
				.usingGeneratedKeyColumns("id");
	}
	public List<Product> selectAll(int start,int limit){
		Map<String, Integer> params = new HashMap<>();
		params.put("start", start);
		params.put("limit", limit);
		return jdbc.query(SELECT_ALL+OF_LIMIT,params,rowMapper);
	}
	public List<Product> selectByCateoryId(int categoryId,int start,int limit){
		Map<String, Integer> params = new HashMap<>();
		params.put("start", start);
		params.put("limit", limit);
		params.put("categoryId", categoryId);
		return jdbc.query(SELECT_ALL+LIMIT_BY_CATEGORY_ID, params,rowMapper);
	}
	public Integer getAllCount() {
		return jdbc.queryForObject(ALL_COUNT, Collections.emptyMap(),Integer.class);
	}
	public Integer getCategoryCount(int categoryId) {
		Map<String,Integer> params = Collections.singletonMap("categoryId", categoryId);
		return jdbc.queryForObject(ALL_COUNT+OF_CATEGORY, params, Integer.class);
	}
	
	public Product selectByDisplayInfoId(int displayId) {
		Map params = Collections.singletonMap("displayInfoId", displayId);
		return jdbc.queryForObject(SELECT_ALL+BY_DISPLAY_INFO_ID,params,rowMapper);
		
	}
	public Boolean isExistByDisplayInfoId(int displayId) {
		return jdbc.queryForObject(IS_EXIST_BY_DISPLAY_INFO_ID, Collections.singletonMap("displayInfoId", displayId),Boolean.class);
	}
	
}
