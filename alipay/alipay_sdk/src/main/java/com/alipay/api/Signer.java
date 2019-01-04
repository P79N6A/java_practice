/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2018 All Rights Reserved.
 */
package com.alipay.api;

/**
 * ��ǩ���ӿ�
 *
 * @author liuqun.lq
 * @version $Id: Signer.java, v 0.1 2018��07��03�� 11:36 liuqun.lq Exp $
 */
public interface Signer {

    /**
     * �����ݼ�ǩ
     *
     * @param sourceContent ����ǩ����
     * @param signType ǩ�����ͣ���RSA��RSA2
     * @param charset �ַ���
     * @return ǩ��ֵ
     */
    String sign(String sourceContent, String signType, String charset);
}