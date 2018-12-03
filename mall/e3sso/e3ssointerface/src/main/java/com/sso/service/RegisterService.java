package com.sso.service;

import com.jiao.common.utils.TaotaoResult;
import com.jiao.pojo.TbItem;
import com.jiao.pojo.TbUser;

public interface RegisterService {
    // 验证数据安全合法性
    TaotaoResult checkData(String param, int type);

    // 提交注册
    TaotaoResult userRegister(TbUser tbUser);

    // 用户登录

    /**
     *   1、判断用户和密码是否正确
     *   2、如果不正确，返回登录失败
     *   3、如果正确生成token。
     *   4、把用户信息写入redis，key：token value：用户信息
     *   5、设置Session的过期时间
     *   6、把token返回
     */
    TaotaoResult userLogin(String username ,String password);


    /**
     *  根据token取用户信息
     */
    TaotaoResult userToken(String ticket);
}
