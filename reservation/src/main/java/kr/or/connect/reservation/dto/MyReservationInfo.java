package kr.or.connect.reservation.dto;

public class MyReservationInfo {
	private Integer id;
	private Integer productId;
	private Integer displayInfoId;
	private Integer cancelFlag;
	private String productDescription;
	private String productContent;
	private Integer userId;
	private Integer sumPrice;
	private String reservationDate;
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
	public Integer getCancelFlag() {
		return cancelFlag;
	}
	public void setCancelFlag(Integer cancelFlag) {
		this.cancelFlag = cancelFlag;
	}
	public String getProductDescription() {
		return productDescription;
	}
	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}
	public String getProductContent() {
		return productContent;
	}
	public void setProductContent(String productContent) {
		this.productContent = productContent;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getSumPrice() {
		return sumPrice;
	}
	public void setSumPrice(Integer sumPrice) {
		this.sumPrice = sumPrice;
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
	@Override
	public String toString() {
		return "MyReservationInfo [id=" + id + ", productId=" + productId + ", displayInfoId=" + displayInfoId
				+ ", cancelFlag=" + cancelFlag + ", productDescription=" + productDescription + ", productContent="
				+ productContent + ", userId=" + userId + ", sumPrice=" + sumPrice + ", reservationDate="
				+ reservationDate + ", createDate=" + createDate + ", modifyDate=" + modifyDate + "]";
	}
	
}
