package kr.or.connect.reservation.service;

import static kr.or.connect.reservation.dao.sqls.ProductDaoSqls.ALL_COUNT;
import static kr.or.connect.reservation.dao.sqls.ProductDaoSqls.SELECT_ALL;

import java.util.Collections;
import java.util.List;

import kr.or.connect.reservation.dto.Product;

public interface ProductService {
	public static final Integer LIMIT = 4;
	public List<Product> getDisplayInfos(int categoryId, int start);
	public Integer getAllCount();
	public Integer getDisplayCount(int categoryId);
	public Product getDisplayInfo(int displayId);
	public List<Product> getDisplayAllInfos(int start);
	
}
