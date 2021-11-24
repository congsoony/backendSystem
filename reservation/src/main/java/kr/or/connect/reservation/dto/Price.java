package kr.or.connect.reservation.dto;

public class Price {
	private Integer count;
	private Integer productPriceId;
	private Integer reservationInfoId;
	private Integer id;
	
	public Price() {
	
	}
	public Price(Integer count, Integer productPriceId, Integer reservationInfoId, Integer id) {
		this.count = count;
		this.productPriceId = productPriceId;
		this.reservationInfoId = reservationInfoId;
		this.id = id;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public Integer getProductPriceId() {
		return productPriceId;
	}
	public void setProductPriceId(Integer productPriceId) {
		this.productPriceId = productPriceId;
	}
	public Integer getReservationInfoId() {
		return reservationInfoId;
	}
	public void setReservationInfoId(Integer reservationInfoId) {
		this.reservationInfoId = reservationInfoId;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return "Price [count=" + count + ", productPriceId=" + productPriceId + ", reservationInfoId="
				+ reservationInfoId + ", id=" + id + "]";
	}
	
}
