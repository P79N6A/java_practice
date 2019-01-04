package com.alipay.api.domain;

import com.alipay.api.AlipayObject;
import com.alipay.api.internal.mapping.ApiField;

/**
 * 支付宝订单信息同步接口
 *
 * @author auto create
 * @since 1.0, 2018-11-22 11:32:17
 */
public class AlipayTradeOrderinfoSyncModel extends AlipayObject {

	private static final long serialVersionUID = 7829587182831648862L;

	/**
	 * 交易信息同步对应的业务类型，具体值与支付宝约定；
信用授权场景下传CREDIT_AUTH
信用代扣场景下传CREDIT_DEDUCT
	 */
	@ApiField("biz_type")
	private String bizType;

	/**
	 * 商户传入同步信息，具体值要和支付宝约定；用于芝麻信用租车、单次授权等信息同步场景，格式为json格式
	 */
	@ApiField("order_biz_info")
	private String orderBizInfo;

	/**
	 * 标识一笔交易多次请求，同一笔交易多次信息同步时需要保证唯一
	 */
	@ApiField("out_request_no")
	private String outRequestNo;

	/**
	 * 支付宝交易号，和商户订单号不能同时为空
	 */
	@ApiField("trade_no")
	private String tradeNo;

	public String getBizType() {
		return this.bizType;
	}
	public void setBizType(String bizType) {
		this.bizType = bizType;
	}

	public String getOrderBizInfo() {
		return this.orderBizInfo;
	}
	public void setOrderBizInfo(String orderBizInfo) {
		this.orderBizInfo = orderBizInfo;
	}

	public String getOutRequestNo() {
		return this.outRequestNo;
	}
	public void setOutRequestNo(String outRequestNo) {
		this.outRequestNo = outRequestNo;
	}

	public String getTradeNo() {
		return this.tradeNo;
	}
	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}

}
