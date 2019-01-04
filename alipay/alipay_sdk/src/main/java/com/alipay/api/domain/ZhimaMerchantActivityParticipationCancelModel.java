package com.alipay.api.domain;

import com.alipay.api.AlipayObject;
import com.alipay.api.internal.mapping.ApiField;

/**
 * 芝麻信用承诺消费活动免除退回优惠接口
 *
 * @author auto create
 * @since 1.0, 2018-10-25 15:23:42
 */
public class ZhimaMerchantActivityParticipationCancelModel extends AlipayObject {

	private static final long serialVersionUID = 5232683878699255829L;

	/**
	 * 承诺消费合约号
	 */
	@ApiField("contract_no")
	private String contractNo;

	public String getContractNo() {
		return this.contractNo;
	}
	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
	}

}
