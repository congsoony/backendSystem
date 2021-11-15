package kr.or.connect.reservation.service;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import kr.or.connect.reservation.config.ApplicationConfig;
import kr.or.connect.reservation.dto.Category;
import kr.or.connect.reservation.dto.DisplayInfoImage;
import kr.or.connect.reservation.dto.Product;
import kr.or.connect.reservation.dto.ProductImage;
import kr.or.connect.reservation.dto.ProductPrice;
import kr.or.connect.reservation.dto.Promotion;
import kr.or.connect.reservation.dto.ReservationUserComment;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ApplicationConfig.class})
public class AllTest {
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private DisplayInfoImageService displayInfoImageService;
	@Autowired
	private ProductImageService productImageService;
	@Autowired
	private ProductPriceService productPriceService;
	@Autowired
	private ProductService productService;
	@Autowired
	private PromotionService promotionService;
	@Autowired
	private ReservationUserCommentService reservationUserCommentService;
	
	@Test
	public void getApiCategories() {
		List<Category> list = categoryService.getCategories();
		int size =list.size();
		Assert.assertEquals(size, 5);
	}
	@Test
	public void displayInfosOfCateogryIdTest() {
		List<Product> list = productService.getDisplayInfos(3, 0);
		Integer totalCount = productService.getDisplayCount(3);
		Integer cnt=16;
		
		Assert.assertEquals(totalCount, cnt);
		Assert.assertEquals(list.size(), 4);
		
	}
	@Test
	public void promotionTest() {
		List<Promotion> list = promotionService.getPromotions();
		Assert.assertNotNull(list);
	}
	
	@Test 
	public void apiDisplayIdTest() {
		Product p = productService.getDisplayInfo(1);
		List<ProductImage> productImages = productImageService.getDisplayInfos(1);
		List<DisplayInfoImage> displayImages = displayInfoImageService.getDisplayInfos(1);
		Integer avgScore = reservationUserCommentService.getAvgScore(1);
		List<ProductPrice> productPrices = productPriceService.getDisplayInfos(1);

		Assert.assertNotNull(p);
		Assert.assertNotNull(productImages);
		Assert.assertNotNull(displayImages);
		Integer exp=3;
		Assert.assertEquals(avgScore, exp);
		Assert.assertNotNull(productPrices);
	}
	
	@Test
	public void apiProductIdDisplayInfosTest() {
		int totalCount = reservationUserCommentService.getTotalCount(1);
		List<ReservationUserComment> list =reservationUserCommentService.getDisplayInfos(1, 0);
		
		Assert.assertEquals(totalCount, 15);
		Assert.assertEquals(list.size(), 5);
	}
	@Test
	public void ErrorTest() {
		try {
			//displayid가 없는값 주기
			Product p = productService.getDisplayInfo(100);
			Assert.fail();
		}catch(RuntimeException e) {
			Assert.assertTrue(true);
		}
	}
}
