package com.alipay.api.response;

import com.alipay.api.internal.mapping.ApiField;

import com.alipay.api.AlipayResponse;

/**
 * ALIPAY API: alipay.ebpp.invoice.file.output.query response.
 * 
 * @author auto create
 * @since 1.0, 2018-08-07 15:45:00
 */
public class AlipayEbppInvoiceFileOutputQueryResponse extends AlipayResponse {

	private static final long serialVersionUID = 7466189665277848997L;

	/** 
	 * 发票文件内容,需要先base64解码，获取原始byte[]类型文件流
	 */
	@ApiField("invoice_file_content")
	private String invoiceFileContent;

	public void setInvoiceFileContent(String invoiceFileContent) {
		this.invoiceFileContent = invoiceFileContent;
	}
	public String getInvoiceFileContent( ) {
		return this.invoiceFileContent;
	}

}
