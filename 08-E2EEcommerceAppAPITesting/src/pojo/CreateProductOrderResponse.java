package pojo;

import java.util.Arrays;

public class CreateProductOrderResponse {
	private String[] orders;
	private String[] productOrderId;
	private String message;
	
	
	public String[] getOrders() {
		return orders;
	}
	public void setOrders(String[] orders) {
		this.orders = orders;
	}
	public String[] getProductOrderId() {
		return productOrderId;
	}
	public void setProductOrderId(String[] productOrderId) {
		this.productOrderId = productOrderId;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	@Override
	public String toString() {
		return "CreateProductOrderResponse [orders=" + Arrays.toString(orders) + ", productOrderId="
				+ Arrays.toString(productOrderId) + ", message=" + message + ", getOrders()="
				+ Arrays.toString(getOrders()) + ", getProductOrderId()=" + Arrays.toString(getProductOrderId())
				+ ", getMessage()=" + getMessage() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ ", toString()=" + super.toString() + "]";
	}
	
}
