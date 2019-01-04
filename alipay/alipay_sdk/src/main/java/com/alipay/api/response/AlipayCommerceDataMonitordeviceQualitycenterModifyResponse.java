package com.alipay.api.response;

import com.alipay.api.internal.mapping.ApiField;

import com.alipay.api.AlipayResponse;

/**
 * ALIPAY API: alipay.commerce.data.monitordevice.qualitycenter.modify response.
 * 
 * @author auto create
 * @since 1.0, 2018-04-11 10:40:00
 */
public class AlipayCommerceDataMonitordeviceQualitycenterModifyResponse extends AlipayResponse {

	private static final long serialVersionUID = 5874678348739944957L;

	/** 
	 * error_detail，出错的情况下返回，包含具体信息。
	 */
	@ApiField("error_detail")
	private String errorDetail;

	public void setErrorDetail(String errorDetail) {
		this.errorDetail = errorDetail;
	}
	public String getErrorDetail( ) {
		return this.errorDetail;
	}

}
