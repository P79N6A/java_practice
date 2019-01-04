package com.alipay.api.domain;

import com.alipay.api.AlipayObject;
import com.alipay.api.internal.mapping.ApiField;

/**
 * 芝麻信用承诺消费用户已参加活动查询
 *
 * @author auto create
 * @since 1.0, 2018-10-25 15:24:23
 */
public class ZhimaMerchantActivityParticipatedQueryModel extends AlipayObject {

	private static final long serialVersionUID = 6688433621638313461L;

	/**
	 * 支付宝用户ID，唯一键标识用户身份。
	 */
	@ApiField("user_id")
	private String userId;

	public String getUserId() {
		return this.userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}

}
