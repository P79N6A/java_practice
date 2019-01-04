package com.alipay.api.response;

import com.alipay.api.internal.mapping.ApiField;
import com.alipay.api.domain.ParamModel;

import com.alipay.api.AlipayResponse;

/**
 * ALIPAY API: koubei.catering.pos.param.query response.
 * 
 * @author auto create
 * @since 1.0, 2018-11-21 10:40:02
 */
public class KoubeiCateringPosParamQueryResponse extends AlipayResponse {

	private static final long serialVersionUID = 5456879367225918299L;

	/** 
	 * 门店参数model
	 */
	@ApiField("param_model")
	private ParamModel paramModel;

	public void setParamModel(ParamModel paramModel) {
		this.paramModel = paramModel;
	}
	public ParamModel getParamModel( ) {
		return this.paramModel;
	}

}
