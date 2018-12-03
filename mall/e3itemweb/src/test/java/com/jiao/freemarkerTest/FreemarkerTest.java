package com.jiao.freemarkerTest;

import freemarker.template.Configuration;
import org.junit.Test;

import java.io.File;

public class FreemarkerTest {

    @Test
    public void freemarkerTest()throws Exception{
        //1、创建一个模板文件
        //2、创建一个Configuration对象
        Configuration configuration = new Configuration(Configuration.getVersion());
        //3、设置模板文件保存的目录
        configuration.setDirectoryForTemplateLoading(new File("D:/workspaces-itcast/JavaEE32/e3-item-web/src/main/webapp/WEB-INF/ftl"));
        //4、模板文件的编码格式，一般就是utf-8
        configuration.setDefaultEncoding("utf-8");
        //5、加载一个模板文件，创建一个模板对象。
//		Template template = configuration.getTemplate("hello.ftl");
        Template template = configuration.getTemplate("student.ftl");
        //6、创建一个数据集。可以是pojo也可以是map。推荐使用map
        Map data = new HashMap<>();
        data.put("hello", "hello freemarker!");


        //7、创建一个Writer对象，指定输出文件的路径及文件名。
//		Writer out = new FileWriter(new File("D:/temp/JavaEE32/freemarker/hello.txt"));
        Writer out = new FileWriter(new File("D:/temp/JavaEE32/freemarker/student.html"));
        //8、生成静态页面
        template.process(data, out);
        //9、关闭流
        out.close();
    }
}
