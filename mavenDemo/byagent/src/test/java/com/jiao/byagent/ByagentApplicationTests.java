package com.jiao.byagent;


import com.jiao.byagent.dao.AppDao;
import com.jiao.byagent.dao.TypeDao;
import com.jiao.byagent.service.IUserDao;
import com.jiao.proxy.pojo.Person;
import com.jiao.proxy.pojo.Type;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ByagentApplicationTests {
	@Autowired
	ApplicationContext applicationContext;

	AppDao appDao;

	@Test
	public void contextLoads() {
		IUserDao userDao = (IUserDao) applicationContext.getBean("userDao");
		System.out.println(userDao.getClass());//$Proxy001
		Person person = new Person();
		person.setName("abc");
		person.setSex("man");
		userDao.save(person);
	}

	@Test
	public void contextLoads1() {
		TypeDao typeDao = (TypeDao) applicationContext.getBean("typeDao");
		List<Type> apps = typeDao.selectAll();
		System.out.println(apps.toString());
	}

}
