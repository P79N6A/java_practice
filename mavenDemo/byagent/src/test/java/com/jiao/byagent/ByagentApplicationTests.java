package com.jiao.byagent;


import com.jiao.byagent.service.IUserDao;
import com.jiao.proxy.pojo.Person;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ByagentApplicationTests {
	@Autowired
	ApplicationContext applicationContext;

	@Test
	public void contextLoads() {
		IUserDao userDao = (IUserDao) applicationContext.getBean("userDao");
		System.out.println(userDao.getClass());//$Proxy001
		Person person = new Person();
		person.setName("abc");
		person.setSex("man");
		userDao.save(person);
	}

}
