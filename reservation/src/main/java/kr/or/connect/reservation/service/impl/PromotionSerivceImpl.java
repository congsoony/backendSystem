package kr.or.connect.reservation.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.connect.reservation.dao.PromotionDao;
import kr.or.connect.reservation.dto.Promotion;
import kr.or.connect.reservation.service.PromotionService;

@Service
public class PromotionSerivceImpl implements PromotionService{
	@Autowired
	private PromotionDao dao;

	@Override
	@Transactional(readOnly = true)
	public List<Promotion> getPromotions() {
		return dao.selectAll();
	}
	
}
