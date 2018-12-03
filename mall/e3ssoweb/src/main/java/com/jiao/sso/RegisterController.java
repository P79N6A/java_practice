package com.jiao.sso;


import com.jiao.common.utils.CookieUtils;
import com.jiao.common.utils.JsonUtils;
import com.jiao.common.utils.TaotaoResult;
import com.jiao.pojo.TbUser;
import com.sso.service.RegisterService;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class RegisterController {

    @Autowired
    private RegisterService registerService;

    @Value("${TOKEN_KEY}")
    private String TOKEN_KEY;



    @RequestMapping("/page/{page}")
    public String showRegister(@PathVariable String page) {
        return page;
    }

    @RequestMapping("/user/check/{param}/{typeId}")
    @ResponseBody
    public TaotaoResult checkRegister(@PathVariable String param , @PathVariable int typeId){
        return registerService.checkData(param,typeId);
    }

    @RequestMapping(value = "/user/register" , method = RequestMethod.POST)
    @ResponseBody
    public TaotaoResult userRegister(TbUser tbUser){
        return registerService.userRegister(tbUser);
    }


    @RequestMapping(value = "/user/login" ,method = RequestMethod.POST)
    @ResponseBody
    public TaotaoResult userLogin(String username , String password, HttpServletRequest request, HttpServletResponse response){
        TaotaoResult taotaoResult = registerService.userLogin(username, password);
        //判断是否登录成功
        if(taotaoResult.getStatus() == 200) {
            String token = taotaoResult.getData().toString();
            //如果登录成功需要把token写入cookie
            CookieUtils.setCookie(request, response, TOKEN_KEY, token);
        }
        //返回结果
        return taotaoResult;
    }


    /**
     *  根据token返回session信息
     */
    @RequestMapping(value = "/user/token/{ticket}")
    @ResponseBody
    public Object userToken(@PathVariable String ticket,String callback){

        /**
         *  判断是不是jsonp请求，如果是就返回jsonp格式的请求，如果不是则正常请求
         */
        TaotaoResult taotaoResult = registerService.userToken(ticket);
        if (StringUtils.isNotBlank(callback)){
            return  callback + "(" + JsonUtils.objectToJson(taotaoResult)+");";
        }
        return JsonUtils.objectToJson(taotaoResult);
    }

}
