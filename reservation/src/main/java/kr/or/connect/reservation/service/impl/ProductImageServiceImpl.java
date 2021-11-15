package kr.or.connect.reservation.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.connect.reservation.dao.ProductImageDao;
import kr.or.connect.reservation.dto.ProductImage;
import kr.or.connect.reservation.service.ProductImageService;

@Service
public class ProductImageServiceImpl implements ProductImageService {
	@Autowired
	private ProductImageDao dao;

	@Override
	@Transactional(readOnly = true)
	public List<ProductImage> getDisplayInfos(int displayId) {
		return dao.selectByDisplayInfoId(displayId);
	}

}
