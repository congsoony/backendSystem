package kr.or.connect.reservation.dto;

public class ReservationInfo {
	private Integer id;
	private Integer productId;
	private Integer displayInfoId;
	private Integer userId;
	private String reservationDate;
	private Integer cancelFlag;
	private String createDate;
	private String modifyDate;
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
	public Integer getCancelFlag() {
		return cancelFlag;
	}
	public void setCancelFlag(Integer cancelFlag) {
		this.cancelFlag = cancelFlag;
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
	@Override
	public String toString() {
		return "ReservationInfo [id=" + id + ", productId=" + productId + ", displayInfoId=" + displayInfoId
				+ ", userId=" + userId + ", reservationDate=" + reservationDate + ", cancelFlag=" + cancelFlag
				+ ", createDate=" + createDate + ", modifyDate=" + modifyDate + "]";
	}
	
}
