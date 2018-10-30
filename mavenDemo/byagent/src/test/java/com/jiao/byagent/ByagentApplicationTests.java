package com.jiao.byagent;


import com.jiao.byagent.dao.*;
import com.jiao.byagent.pojo.*;
import com.jiao.byagent.service.EmpManage;
import com.jiao.byagent.service.IUserDao;
import com.jiao.byagent.service.MgrManage;
import com.jiao.proxy.pojo.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;
import tk.mybatis.mapper.entity.Condition;
import tk.mybatis.mapper.entity.Example;

import javax.sql.DataSource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ByagentApplicationTests {
	@Autowired
	ApplicationContext applicationContext;

	@Autowired
	MgrDaosMapper mgrDaosMapper;

	@Autowired
	MgrEmpMapper mgrEmpMapper;

	@Autowired
	DataSource dataSource;

	@Test
	public void contextLoads() throws Throwable{
		IUserDao userDao = (IUserDao) applicationContext.getBean("userDao");
		System.out.println(userDao.getClass());//$Proxy001
		Person person = new Person();
		person.setName("abc");
		person.setSex("man");
		userDao.save(person);
	}

	@Test
	public void contextLoads1() {
//		TypeDaoMapper typeDaoMapper = (TypeDaoMapper) applicationContext.getBean("typeDaoMapper");
//		List<Type> apps = typeDaoMapper.selectAll();
//		for (Type type : apps){
//			System.out.println(type.toString());
//		}
	}

	@Test
	public void contextLoads2() {
//		TypeDaoMapper typeDaoMapper = (TypeDaoMapper) applicationContext.getBean("typeDaoMapper");
		Type apps = mgrDaosMapper.findById2(1);
		System.out.println(apps.toString());
	}

	@Test
	public void contextLoads3() {
//		TypeDaoMapper typeDaoMapper = (TypeDaoMapper) applicationContext.getBean("typeDaoMapper");
		List<MgrEmp> apps = mgrDaosMapper.findById3();
		apps.forEach(t -> System.out.println(t.toString()));
	}

	@Test
	public void contextLoads4() {
//		TypeDaoMapper typeDaoMapper = (TypeDaoMapper) applicationContext.getBean("typeDaoMapper");
		List<MgrEmp3> apps = mgrEmpMapper.findById4();
		apps.forEach(t -> System.out.println(t.toString()));
	}
	@Test
	public void contextLoads5() {
//		TypeDaoMapper typeDaoMapper = (TypeDaoMapper) applicationContext.getBean("typeDaoMapper");
       for (int i =0 ;i< 10;i++) {
		   mgrEmpMapper.findById5();  //测试ehcache缓存使用
	   }

	}
	@Test
	public void contextLoads6() {
		TypeDaoMapper typeDaoMapper = (TypeDaoMapper) applicationContext.getBean("typeDaoMapper");
		Type type = new Type();
		type.setTypeId(17);
		type.setTypeName("haha");
		type.setAmerceAmount(-60.0);
		typeDaoMapper.insert(type);
		List<Type> types = typeDaoMapper.selectAll();
		types.forEach(t -> System.out.println(t.toString()));
	}

	@Test
	public void contextLoads9() throws Throwable{
		AttendDaoMapper attendDaoMapper = (AttendDaoMapper) applicationContext.getBean("attendDaoMapper");
		Emp emp = new Emp();
		emp.setEmpId(1);
		List<AttendVo> attendsByEmp = attendDaoMapper.findAttendsByEmp(emp, "2018-10");
	}

	@Test
	public void contextLoads10() throws Throwable{
		EmpDapMapper empDapMapper = (EmpDapMapper) applicationContext.getBean("empDapMapper");
		Mgr mgr = new Mgr();
		mgr.setMgrId(1);
		List<Emp> emp = empDapMapper.findByMgr(mgr);
		emp.forEach(t -> System.out.println(t.toString()));
	}
	@Test
	public void contextLoads11() throws Throwable{
		MgrDaoMapper mgrDaoMapper = (MgrDaoMapper) applicationContext.getBean("mgrDaoMapper");
//		MgrVO mgr = new MgrVO();
//		mgr.setMgrId(1);
		MgrVO mgr = mgrDaoMapper.findByName("oracle");
		System.out.println(mgr.toString());
	}
	@Test
	public void contextLoads12() throws Throwable{
		PayDaoMapper payDaoMapper = (PayDaoMapper) applicationContext.getBean("payDaoMapper");
		Emp emp = new Emp();
		emp.setEmpId(1);
		PayVO byEmpAndMonth = payDaoMapper.findByEmpAndMonth(emp,"100");
//		byEmp.forEach(t->System.out.println(t.toString()));
	}

	@Test
	public void contextLoads13() throws Throwable{
		TypeDaoMapper typeDaoMapper = (TypeDaoMapper) applicationContext.getBean("typeDaoMapper");
        Condition condition = new Condition(Type.class);
        Example.Criteria criteria = condition.createCriteria();
        criteria.andEqualTo("typeName","旷工");
        condition.and(criteria);
        List<Type> types = typeDaoMapper.selectByCondition(condition);
        types.forEach(t->System.out.println(t.toString()));
}


    @Test
    public void contextLoads14() throws Throwable{
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-mm-dd");
        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyy-mm-dd HH:MM:ss");
        String dutyday = simpleDateFormat.format(new Date());
        String str = simpleDateFormat1.format(new Date());
        System.out.println(str);
        System.out.println(dutyday);
    }

    @Test
    public void contextLoads15() throws Throwable{
        EmpManage empImpl = (EmpManage) applicationContext.getBean("empImpl");
        empImpl.punch("oracle","2018-10-25",true);

    }

	@Test
	public void contextLoads16() throws Throwable{
		MgrManage mgrImpl = (MgrManage) applicationContext.getBean("mgrImpl");
		List<PayVO> oracle = mgrImpl.getSalaryByMgr("oracle");
	}
}
