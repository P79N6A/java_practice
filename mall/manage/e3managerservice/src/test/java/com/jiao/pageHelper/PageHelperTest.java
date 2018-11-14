package com.jiao.pageHelper;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jiao.mapper.TbItemMapper;
import com.jiao.pojo.TbItem;
import com.jiao.pojo.TbItemExample;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * Created by jiao on 2018/11/8.
 */

public class PageHelperTest {

    @Test
    public void  testPageHelper() throws Exception{
        ApplicationContext applicationContext  = new ClassPathXmlApplicationContext("spring/applicationContext-dao.xml");

        TbItemMapper bean = applicationContext.getBean(TbItemMapper.class);

        PageHelper.startPage(1,10);

        TbItemExample example = new TbItemExample();

        List<TbItem> tbItems = bean.selectByExample(example);

        PageInfo<TbItem> tbItemPageInfo = new PageInfo<>(tbItems);

       System.out.println(tbItemPageInfo.getPages());


    }
}
