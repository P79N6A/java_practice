package com.alipay.api.response;

import com.alipay.api.internal.mapping.ApiField;

import com.alipay.api.AlipayResponse;

/**
 * ALIPAY API: alipay.social.gift.order.refund response.
 * 
 * @author auto create
 * @since 1.0, 2018-10-16 11:54:01
 */
public class AlipaySocialGiftOrderRefundResponse extends AlipayResponse {

	private static final long serialVersionUID = 8373159662131755586L;

	/** 
	 * 本次操作的订单id，与该接口入参order_id一致
	 */
	@ApiField("order_id")
	private String orderId;

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getOrderId( ) {
		return this.orderId;
	}

}
