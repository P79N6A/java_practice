/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2018 All Rights Reserved.
 */
package com.alipay.api;

import java.util.ArrayList;
import java.util.List;

import com.alipay.api.internal.mapping.ApiField;

/**
 * @author gongyi.tnj
 * @version $Id: BatchAlipayResponse.java, v 0.1 2018-07-18 ����10:29 gongyi.tnj Exp $
 */
public class BatchAlipayResponse extends AlipayResponse {

    private static final long                  serialVersionUID = -4636364816621782447L;

    /** �������ýӿڼ��� **/
    private List<AlipayResponse>               responseList;

    @ApiField("response_body")
    private String                             responseBody;

    public void addResponse(AlipayResponse response) {
        if (null == responseList) {
            responseList = new ArrayList<AlipayResponse>();
        }
        responseList.add(response);
    }

    /**
     * ��ȡ����������������Ӧ�б�
     * @return
     */
    public List<AlipayResponse> getResponseList() {
        return responseList;
    }

    /**
     * Getter method for property <tt>responseBody</tt>.
     *
     * @return property value of responseBody
     */
    public String getResponseBody() {
        return responseBody;
    }

    /**
     * Setter method for property <tt>responseBody </tt>.
     *
     * @param responseBody value to be assigned to property responseBody
     */
    public void setResponseBody(String responseBody) {
        this.responseBody = responseBody;
    }
}
