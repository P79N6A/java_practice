package example;

import com.jiao.server.webservice.WebServiceTest;
import com.jiao.server.webservice.WebServiceTestService;

/**
 * Created by jiao on 2018/9/28.
 */
public class HelloWorldClient {
  public static void main(String[] argv) {
    WebServiceTest service = new WebServiceTestService().getWebServiceTestPort();
    //invoke business method
    String name = service.getName("haha");
    System.out.println(name);
  }
}
