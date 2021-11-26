package kr.or.connect.reservation.dto;

import java.util.ArrayList;
import java.util.List;

public class ReservationInfosRequest {
	private List<ReservationInfoPrice> prices;
	private Integer productId;
	private Integer displayInfoId;
	private String reservationYearMonthDay;
	private Integer userId;

	public List<ReservationInfoPrice> getPrices() {
		return prices;
	}

	public void setPrices(List<ReservationInfoPrice> prices) {
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
		return "ReservationInfosRequest [prices=" + prices + ", productId=" + productId + ", displayInfoId="
				+ displayInfoId + ", reservationYearMonthDay=" + reservationYearMonthDay + ", userId=" + userId + "]";
	}

}
