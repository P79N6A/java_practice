package com.alipay.api.response;

import java.util.List;
import com.alipay.api.internal.mapping.ApiField;
import com.alipay.api.internal.mapping.ApiListField;
import com.alipay.api.domain.JFExportInputFieldModel;

import com.alipay.api.AlipayResponse;

/**
 * ALIPAY API: alipay.ebpp.jfexport.inputfield.query response.
 * 
 * @author auto create
 * @since 1.0, 2017-09-19 12:38:11
 */
public class AlipayEbppJfexportInputfieldQueryResponse extends AlipayResponse {

	private static final long serialVersionUID = 7714781492372445297L;

	/** 
	 * 输出的输入域列表
	 */
	@ApiListField("input_fields")
	@ApiField("j_f_export_input_field_model")
	private List<JFExportInputFieldModel> inputFields;

	public void setInputFields(List<JFExportInputFieldModel> inputFields) {
		this.inputFields = inputFields;
	}
	public List<JFExportInputFieldModel> getInputFields( ) {
		return this.inputFields;
	}

}
