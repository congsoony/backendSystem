package kr.or.connect.reservation.service;

import static kr.or.connect.reservation.dao.sqls.ProductDaoSqls.ALL_COUNT;
import static kr.or.connect.reservation.dao.sqls.ProductDaoSqls.SELECT_ALL;

import java.util.Collections;
import java.util.List;

import kr.or.connect.reservation.dto.Product;

public interface ProductService {
	static final Integer LIMIT = 4;
	List<Product> getDisplayInfos(int categoryId, int start);
	Integer getAllCount();
	Integer getDisplayCount(int categoryId);
	Product getDisplayInfo(int displayId);
	List<Product> getDisplayAllInfos(int start);
	Boolean isExistDisplayInfo(int displayId);
}
