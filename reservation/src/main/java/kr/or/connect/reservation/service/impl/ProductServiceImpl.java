package kr.or.connect.reservation.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.connect.reservation.dao.ProductDao;
import kr.or.connect.reservation.dto.Product;
import kr.or.connect.reservation.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {
	@Autowired
	private ProductDao dao;	

	@Override
	public List<Product> getDisplayAllInfos(int start) {
		return dao.selectAll(start, LIMIT);
	}

	@Override
	@Transactional(readOnly = true)
	public Integer getAllCount() {
		return dao.getAllCount();
	}

	@Override
	@Transactional(readOnly = true)
	public List<Product> getDisplayInfos(int categoryId, int start) {
		return dao.selectByCateoryId(categoryId, start, LIMIT);
	}

	@Override
	@Transactional(readOnly = true)
	public Integer getDisplayCount(int categoryId) {
		return dao.getCategoryCount(categoryId);
	}

	@Override
	@Transactional(readOnly = true)
	public Product getDisplayInfo(int displayId) throws IllegalArgumentException{
		if(dao.isExistByDisplayInfoId(displayId)==false)
			throw new IllegalArgumentException("잘못된 요청입니다.");
		return dao.selectByDisplayInfoId(displayId);
	}

}
