package com.alipay.api.response;

import com.alipay.api.internal.mapping.ApiField;

import com.alipay.api.AlipayResponse;

/**
 * ALIPAY API: koubei.catering.pos.practice.create response.
 * 
 * @author auto create
 * @since 1.0, 2018-11-16 17:19:43
 */
public class KoubeiCateringPosPracticeCreateResponse extends AlipayResponse {

	private static final long serialVersionUID = 2721117133294421591L;

	/** 
	 * 做法id
	 */
	@ApiField("id")
	private String id;

	public void setId(String id) {
		this.id = id;
	}
	public String getId( ) {
		return this.id;
	}

}
