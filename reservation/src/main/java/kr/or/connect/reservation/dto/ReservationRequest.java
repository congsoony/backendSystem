package kr.or.connect.reservation.dto;

import java.util.ArrayList;
import java.util.List;

public class ReservationRequest {
	private List<Price> prices;
	private Integer productId;
	private Integer displayInfoId;
	private String reservationYearMonthDay;
	private Integer userId;
	
	public ReservationRequest() {
	}
	public ReservationRequest(List<Price> prices, Integer productId, Integer displayInfoId,
			String reservationYearMonthDay, Integer userId) {
		this.prices = prices;
		this.productId = productId;
		this.displayInfoId = displayInfoId;
		this.reservationYearMonthDay = reservationYearMonthDay;
		this.userId = userId;
	}
	public List<Price> getPrices() {
		return prices;
	}
	public void setPrices(List<Price> prices) {
		this.prices = prices;
	}
	public Integer getProductId() {
		return productId;
	}
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	public Integer getDisplayInfoId() {
		return displayInfoId;
	}
	public void setDisplayInfoId(Integer displayInfoId) {
		this.displayInfoId = displayInfoId;
	}
	public String getReservationYearMonthDay() {
		return reservationYearMonthDay;
	}
	public void setReservationYearMonthDay(String reservationYearMonthDay) {
		this.reservationYearMonthDay = reservationYearMonthDay;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	@Override
	public String toString() {
		return "ReservationRequest [prices=" + prices + ", productId=" + productId + ", displayInfoId=" + displayInfoId
				+ ", reservationYearMonthDay=" + reservationYearMonthDay + ", userId=" + userId + "]";
	}
	
}
