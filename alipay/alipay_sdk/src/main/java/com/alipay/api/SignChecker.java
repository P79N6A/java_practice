/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2018 All Rights Reserved.
 */
package com.alipay.api;

/**
 * ��ǩ���ӿ�
 *
 * @author liuqun.lq
 * @version $Id: SignChecker.java, v 0.1 2018��07��03�� 11:38 liuqun.lq Exp $
 */
public interface SignChecker {

    /**
     * ��ǩ��������ǩ
     *
     * @param sourceContent ��ǩ����
     * @param signature ǩ��ֵ
     * @param signType ǩ�����ͣ���RSA��RSA2
     * @param charset �ַ���
     * @return true ��ǩͨ����false ��ǩ��ͨ��
     */
    boolean check(String sourceContent, String signature, String signType, String charset);
}