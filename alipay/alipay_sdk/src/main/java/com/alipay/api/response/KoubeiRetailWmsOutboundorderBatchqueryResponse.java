package com.alipay.api.response;

import java.util.List;
import com.alipay.api.internal.mapping.ApiField;
import com.alipay.api.internal.mapping.ApiListField;
import com.alipay.api.domain.OutboundOrderVO;

import com.alipay.api.AlipayResponse;

/**
 * ALIPAY API: koubei.retail.wms.outboundorder.batchquery response.
 * 
 * @author auto create
 * @since 1.0, 2018-04-26 16:33:35
 */
public class KoubeiRetailWmsOutboundorderBatchqueryResponse extends AlipayResponse {

	private static final long serialVersionUID = 6395341383265871942L;

	/** 
	 * 出库通知单列表
	 */
	@ApiListField("outbound_order_vo_list")
	@ApiField("outbound_order_v_o")
	private List<OutboundOrderVO> outboundOrderVoList;

	public void setOutboundOrderVoList(List<OutboundOrderVO> outboundOrderVoList) {
		this.outboundOrderVoList = outboundOrderVoList;
	}
	public List<OutboundOrderVO> getOutboundOrderVoList( ) {
		return this.outboundOrderVoList;
	}

}
