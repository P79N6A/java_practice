package com.alipay.api.response;

import com.alipay.api.internal.mapping.ApiField;

import com.alipay.api.AlipayResponse;

/**
 * ALIPAY API: alipay.social.base.chat.newmsg.send response.
 * 
 * @author auto create
 * @since 1.0, 2018-04-16 10:40:00
 */
public class AlipaySocialBaseChatNewmsgSendResponse extends AlipayResponse {

	private static final long serialVersionUID = 6465132253565477265L;

	/** 
	 * 消息索引号 会话ID_消息ID
	 */
	@ApiField("msg_index")
	private String msgIndex;

	public void setMsgIndex(String msgIndex) {
		this.msgIndex = msgIndex;
	}
	public String getMsgIndex( ) {
		return this.msgIndex;
	}

}
