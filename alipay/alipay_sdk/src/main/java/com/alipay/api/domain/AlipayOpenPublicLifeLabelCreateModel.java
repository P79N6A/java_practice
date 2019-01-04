package com.alipay.api.domain;

import com.alipay.api.AlipayObject;
import com.alipay.api.internal.mapping.ApiField;

/**
 * 创建标签接口
 *
 * @author auto create
 * @since 1.0, 2018-07-25 13:52:34
 */
public class AlipayOpenPublicLifeLabelCreateModel extends AlipayObject {

	private static final long serialVersionUID = 4625282459967817963L;

	/**
	 * 标签值类型，目前只支持string（字符串类型），不传默认为"string"
	 */
	@ApiField("data_type")
	private String dataType;

	/**
	 * 自定义标签名
	 */
	@ApiField("label_name")
	private String labelName;

	public String getDataType() {
		return this.dataType;
	}
	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	public String getLabelName() {
		return this.labelName;
	}
	public void setLabelName(String labelName) {
		this.labelName = labelName;
	}

}
