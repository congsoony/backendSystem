package kr.or.connect.reservation.controller.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import junit.framework.TestCase;
import kr.or.connect.reservation.config.ApplicationConfig;
import kr.or.connect.reservation.config.MvcConfig;
import kr.or.connect.reservation.dto.MyReservationInfo;
import kr.or.connect.reservation.dto.ReservationInfoPrice;
import kr.or.connect.reservation.dto.ReservationInfoResponse;
import kr.or.connect.reservation.dto.ReservationInfosRequest;
import kr.or.connect.reservation.service.ReservationInfosService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {MvcConfig.class, ApplicationConfig.class })
public class ReservationInfosApiControllerTest extends TestCase {
    @InjectMocks
    ReservationInfosApiController reservationInfosApiController;

    @Mock
    ReservationInfosService reservationInfosService;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(reservationInfosApiController).build();
    }

    @Test
    @WithMockUser(username = "carami@connect.co.kr", password = "1234", roles = {"USER","ADMIN"})
    public void testGetReservationInfos() throws Exception {
        // reservationInfosService.getMyReservationInfos 호출 return될 객체 임의로 생성
        List<MyReservationInfo> myReservationInfos = new ArrayList<>();

        MyReservationInfo myReservationInfo = new MyReservationInfo();
        myReservationInfo.setId(1);
        myReservationInfo.setProductId(1);
        myReservationInfo.setDisplayInfoId(1);
        myReservationInfo.setCancelFlag(0);
        myReservationInfo.setProductDescription("test");
        myReservationInfo.setProductContent("test");
        myReservationInfo.setUserId(1);
        myReservationInfo.setSumPrice(1000);
        myReservationInfo.setReservationDate("2021.12.01");
        myReservationInfo.setCreateDate("2021.11.30");
        myReservationInfo.setModifyDate("2021.11.30");

        myReservationInfos.add(myReservationInfo);

        // reservationInfosService.getMyReservationInfos 호출했을 때 return 객체 지정
        when(reservationInfosService.getMyReservationInfos(any())).thenReturn(myReservationInfos);

        // 해당 api를 호출정보 생성(애노테이션에 정의한 인증정보 요청에 set)
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/api/reservationInfos")
                .principal(SecurityContextHolder.getContext().getAuthentication());

        // 해당 api 호출하여 200나오는지 확인
        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andDo(print());

        // 해당 api가 호출되었을 때 해당 service 메소드 1번 호출 검증
        verify(reservationInfosService, times(1)).getMyReservationInfos(any());
    }

    @Test
    @WithMockUser(username = "carami@connect.co.kr", password = "1234", roles = {"USER","ADMIN"})
    public void testMakeReservation() throws Exception {
        // 요청 정보 생성
        ReservationInfosRequest reservationRequest = new ReservationInfosRequest();
        reservationRequest.setProductId(1);
        reservationRequest.setDisplayInfoId(1);
        reservationRequest.setReservationYearMonthDay("2021.12.01");
        reservationRequest.setUserId(1);

        ReservationInfoPrice reservationInfoPrice = new ReservationInfoPrice();
        reservationInfoPrice.setId(1);
        reservationInfoPrice.setReservationInfoId(1);
        reservationInfoPrice.setProductPriceId(1);
        reservationInfoPrice.setCount(3);

        reservationRequest.setPrices(Arrays.asList(reservationInfoPrice));

        // request body와 같이 json string으로 변환
        ObjectMapper objectMapper = new ObjectMapper();
        String requestBody = objectMapper.writeValueAsString(reservationRequest);


        //reservationInfosService.makeReservation return 객체 임의로 생성
        ReservationInfoResponse reservationInfoResponse = new ReservationInfoResponse();
        reservationInfoResponse.setId(1);
        reservationInfoResponse.setProductId(1);
        reservationInfoResponse.setCancelFlag(0);
        reservationInfoResponse.setDisplayInfoId(1);
        reservationInfoResponse.setUserId(1);
        reservationInfoResponse.setReservationDate("2021.12.01");
        reservationInfoResponse.setCreateDate("2021.11.30");
        reservationInfoResponse.setModifyDate("2021.11.30");

        // reservationInfosService.makeReservation 호출했을 때 return 객체 지정
        when(reservationInfosService.makeReservation(any(), anyString())).thenReturn(reservationInfoResponse);

        // 해당 api를 호출정보 생성(애노테이션에 정의한 인증정보 요청에 set, request body set)
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/api/reservationInfos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody)
                .principal(SecurityContextHolder.getContext().getAuthentication());

        // 해당 api 호출하여 200나오는지 확인
        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andDo(print());

        // 해당 api가 호출되었을 때 해당 service 메소드 1번 호출 검증
        verify(reservationInfosService, times(1)).makeReservation(any(), anyString());
    }

    @Test
    @WithMockUser(username = "carami@connect.co.kr", password = "1234", roles = {"USER","ADMIN"})
    public void testCancelReservation() throws Exception {
        // 삭제할 예약id가 1일 경우
        Map<String, Integer> req = new HashMap<>();
        req.put("id", 1);

        // request body와 같이 json string으로 변환
        ObjectMapper objectMapper = new ObjectMapper();
        String requestBody = objectMapper.writeValueAsString(req);

        // reservationInfosService.cancelReservation 호출했을 때 return 객체 지정
        when(reservationInfosService.cancelReservation(anyInt(), anyString())).thenReturn(1);


        // 해당 api를 호출정보 생성(애노테이션에 정의한 인증정보 요청에 set, request body set)
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .put("/api/reservationInfos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody)
                .principal(SecurityContextHolder.getContext().getAuthentication());

        // 해당 api 호출하여 200나오는지 확인
        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andDo(print());

        // 해당 api가 호출되었을 때 해당 service 메소드 1번 호출 검증
        verify(reservationInfosService, times(1)).cancelReservation(anyInt(), anyString());
    }
}