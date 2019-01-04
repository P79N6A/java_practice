/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2018 All Rights Reserved.
 */
package com.alipay.api;

/**
 * �������ӿ�
 *
 * @author liuqun.lq
 * @version $Id: Decryptor.java, v 0.1 2018��07��03�� 11:44 liuqun.lq Exp $
 */
public interface Decryptor {

    /**
     * �����ݽ���
     *
     * @param encryptContent ��������
     * @param encryptType �����㷨���ͣ���AES
     * @param charset �ַ���
     * @return ���ܺ�����
     */
    String decrypt(String encryptContent, String encryptType, String charset);
}