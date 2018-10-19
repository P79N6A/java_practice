package com.jiao.proxy.service.serviceimpl;

import com.jiao.proxy.pojo.Person;
import com.jiao.proxy.service.IUserDao;
import org.springframework.stereotype.Service;

/**
 * Created by jiao on 2018/10/19.
 */
@Service
public class UserDao implements IUserDao{
    @Override
    public void save(Person person) {
        System.out.println("-----核心业务：保存！！！------");
    }
}
