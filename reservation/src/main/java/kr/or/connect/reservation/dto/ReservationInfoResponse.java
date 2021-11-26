package kr.or.connect.reservation.dto;

import java.util.List;

public class ReservationInfoResponse {
	private Integer id;
	private Integer productId;
	private Integer cancelFlag;
	private Integer displayInfoId;
	private Integer userId;
	private String reservationDate;
	private String createDate;
	private String modifyDate;
	private List<ReservationInfoPrice> prices;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getProductId() {
		return productId;
	}
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	public Integer getCancelFlag() {
		return cancelFlag;
	}
	public void setCancelFlag(Integer cancelFlag) {
		this.cancelFlag = cancelFlag;
	}
	public Integer getDisplayInfoId() {
		return displayInfoId;
	}
	public void setDisplayInfoId(Integer displayInfoId) {
		this.displayInfoId = displayInfoId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getReservationDate() {
		return reservationDate;
	}
	public void setReservationDate(String reservationDate) {
		this.reservationDate = reservationDate;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public String getModifyDate() {
		return modifyDate;
	}
	public void setModifyDate(String modifyDate) {
		this.modifyDate = modifyDate;
	}
	public List<ReservationInfoPrice> getPrices() {
		return prices;
	}
	public void setPrices(List<ReservationInfoPrice> prices) {
		this.prices = prices;
	}
	
	public void setReservationInfo(ReservationInfo r) {
		this.id=r.getId();
		this.productId=r.getProductId();
		this.cancelFlag=r.getCancelFlag();
		this.displayInfoId=r.getDisplayInfoId();
		this.userId = r.getUserId();
		this.reservationDate = r.getReservationDate();
		this.createDate = r.getCreateDate();
		this.modifyDate = r.getModifyDate();
	}
	@Override
	public String toString() {
		return "ReservationInfoResponse [id=" + id + ", productId=" + productId + ", cancelFlag=" + cancelFlag
				+ ", displayInfoId=" + displayInfoId + ", userId=" + userId + ", reservationDate=" + reservationDate
				+ ", createDate=" + createDate + ", modifyDate=" + modifyDate + ", prices=" + prices + "]";
	}
	
}
