package com.alipay.api.response;

import com.alipay.api.internal.mapping.ApiField;

import com.alipay.api.AlipayResponse;

/**
 * ALIPAY API: alipay.open.mini.inneraccount.pid.query response.
 * 
 * @author auto create
 * @since 1.0, 2018-11-01 17:44:14
 */
public class AlipayOpenMiniInneraccountPidQueryResponse extends AlipayResponse {

	private static final long serialVersionUID = 6325789665695854321L;

	/** 
	 * 虚拟PID
	 */
	@ApiField("pid")
	private String pid;

	public void setPid(String pid) {
		this.pid = pid;
	}
	public String getPid( ) {
		return this.pid;
	}

}
