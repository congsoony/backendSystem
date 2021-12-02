package kr.or.connect.reservation.service;

import java.util.List;

import kr.or.connect.reservation.dto.Product;

public interface ProductService {
	Integer LIMIT = 4;
	List<Product> getDisplayInfos(int categoryId, int start);
	Integer getAllCount();
	Integer getDisplayCount(int categoryId);
	Product getDisplayInfo(int displayId);
	List<Product> getDisplayAllInfos(int start);
}
