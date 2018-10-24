package com.jiao.byagent.service;


import com.jiao.proxy.pojo.Person;

/**
 * Created by jiao on 2018/10/19.
 */
public interface IUserDao {
    void save(Person person) throws Throwable;
}
