package kr.or.connect.reservation.controller.api;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import junit.framework.TestCase;
import kr.or.connect.reservation.config.ApplicationConfig;
import kr.or.connect.reservation.config.MvcConfig;
import kr.or.connect.reservation.dto.ReservationUserCommentImage;
import kr.or.connect.reservation.dto.UserComment;
import kr.or.connect.reservation.service.ReservationUserCommentService;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = { MvcConfig.class, ApplicationConfig.class })
public class CommentApiControllerTest extends TestCase {

	@InjectMocks
	CommentApiController commentApiController;

	@Mock
	ReservationUserCommentService reservationUserCommentService;

	private MockMvc mockMvc;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(commentApiController).build();
	}

	@Test
	@WithMockUser(username = "carami@connect.co.kr", password = "1234", roles = { "USER", "ADMIN" })
	public void getCommentsTest() throws Exception {
		List<UserComment> list = new ArrayList<>();
		List<ReservationUserCommentImage> list2 = new ArrayList<>();

		UserComment userComment = new UserComment();
		userComment.setId(1);
		userComment.setProductId(1);
		userComment.setReservationInfoId(1);
		userComment.setScore(3);
		userComment.setUserId(1);
		userComment.setComment("댓글저장");

		ReservationUserCommentImage image = new ReservationUserCommentImage();
		image.setId(1);
		image.setReservationInfoId(1);
		image.setReservationUserCommentId(1);
		image.setFileId(1);
		image.setFileName("abc.png");
		image.setSaveFileName("sij/abc.png");
		image.setContentType("image/png");
		image.setDeleteFlag(0);
		image.setCreateDate(123456789L);
		image.setCreateDate(123456789L);
		list2.add(image);
		userComment.setReservationUserCommentImages(list2);

		list.add(userComment);
		when(reservationUserCommentService.getComments(anyInt(), anyInt())).thenReturn(list);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/comments");

		mockMvc.perform(requestBuilder).andExpect(status().isOk()).andDo(print());
		verify(reservationUserCommentService, times(0)).getComments(anyInt(), anyInt());

	}

}
