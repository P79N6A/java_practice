package com.alipay.api.domain;

import com.alipay.api.AlipayObject;
import com.alipay.api.internal.mapping.ApiField;

/**
 * 合并转账时的单笔转账单据详情
 *
 * @author auto create
 * @since 1.0, 2018-11-09 14:35:40
 */
public class TransOrderDetail extends AlipayObject {

	private static final long serialVersionUID = 1121396878128752178L;

	/**
	 * JSON格式，传递业务扩展参数，使用前请与支付宝工程师联系！
sub_biz_scene: 子业务场景，取值：BAOXIAO\TRANSFER\...
	 */
	@ApiField("business_params")
	private String businessParams;

	/**
	 * 转账订单的标题，用于在收银台和消费记录展示
	 */
	@ApiField("order_title")
	private String orderTitle;

	/**
	 * 商户订单号
	 */
	@ApiField("out_biz_no")
	private String outBizNo;

	/**
	 * 收款方信息
	 */
	@ApiField("payee_info")
	private Participant payeeInfo;

	/**
	 * 转账备注，收、付款方均可见，收款方如果是支付宝账号，会展示在收款方账单里。
	 */
	@ApiField("remark")
	private String remark;

	/**
	 * 转账金额
	 */
	@ApiField("trans_amount")
	private String transAmount;

	public String getBusinessParams() {
		return this.businessParams;
	}
	public void setBusinessParams(String businessParams) {
		this.businessParams = businessParams;
	}

	public String getOrderTitle() {
		return this.orderTitle;
	}
	public void setOrderTitle(String orderTitle) {
		this.orderTitle = orderTitle;
	}

	public String getOutBizNo() {
		return this.outBizNo;
	}
	public void setOutBizNo(String outBizNo) {
		this.outBizNo = outBizNo;
	}

	public Participant getPayeeInfo() {
		return this.payeeInfo;
	}
	public void setPayeeInfo(Participant payeeInfo) {
		this.payeeInfo = payeeInfo;
	}

	public String getRemark() {
		return this.remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getTransAmount() {
		return this.transAmount;
	}
	public void setTransAmount(String transAmount) {
		this.transAmount = transAmount;
	}

}
