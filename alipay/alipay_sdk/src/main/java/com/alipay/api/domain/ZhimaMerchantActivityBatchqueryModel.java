package com.alipay.api.domain;

import java.util.List;

import com.alipay.api.AlipayObject;
import com.alipay.api.internal.mapping.ApiField;
import com.alipay.api.internal.mapping.ApiListField;

/**
 * 信用承诺消费活动分页查询接口
 *
 * @author auto create
 * @since 1.0, 2018-10-25 15:24:06
 */
public class ZhimaMerchantActivityBatchqueryModel extends AlipayObject {

	private static final long serialVersionUID = 8778788269932189192L;

	/**
	 * 当前页数，从1开始。
	 */
	@ApiField("page_no")
	private Long pageNo;

	/**
	 * 单页条目数，范围1-50之间。
	 */
	@ApiField("page_size")
	private Long pageSize;

	/**
	 * 分页查询条件，可空，可传入多个状态；如果不传默认查询该商户下所有合法活动。状态枚举：
NOT_STARTED - 未开始
AVAILABLE - 活动中
END - 活动结束
	 */
	@ApiListField("status_list")
	@ApiField("string")
	private List<String> statusList;

	public Long getPageNo() {
		return this.pageNo;
	}
	public void setPageNo(Long pageNo) {
		this.pageNo = pageNo;
	}

	public Long getPageSize() {
		return this.pageSize;
	}
	public void setPageSize(Long pageSize) {
		this.pageSize = pageSize;
	}

	public List<String> getStatusList() {
		return this.statusList;
	}
	public void setStatusList(List<String> statusList) {
		this.statusList = statusList;
	}

}
