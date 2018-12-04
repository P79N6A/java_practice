package com.jiao.order.cotroller;

import com.jiao.cart.service.CartService;
import com.jiao.common.utils.TaotaoResult;
import com.jiao.order.pojo.Item;
import com.jiao.order.pojo.OrderInfo;
import com.jiao.order.service.OrderService;
import com.jiao.pojo.TbItem;
import com.jiao.pojo.TbUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.jws.WebParam;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class OrderContoller {
    @Autowired
    private CartService cartService;
    @Autowired
    private OrderService orderService;

    /**
     *  去购物车结算时 如果没有登录
     *  跳到登录页面，让其登录
     *  检查cookie中有没有商品，如有则合并购物车，最后跳到结算页面
     *
     */
    @RequestMapping("/order/order-cart")
    public String orderOrderCart(HttpServletRequest request, ModelMap modelMap){
        /**
         *  进入方法 肯定已经登录  则从服务端取出数据 渲染
         */
        TbUser user = (TbUser) request.getAttribute("user");
        List<TbItem> cartFromServer = cartService.getCartFromServer(user.getId());
        List<Item> cartList = new ArrayList<>();
        for (TbItem t: cartFromServer
             ) {
            Item item = new Item(t);
            cartList.add(item);
        }
        modelMap.addAttribute("cartList",cartList);
        return "order-cart";
    }


    /**
     *  进入结算页面 提交订单
     */
    @RequestMapping("/order/create")
    public String orderCreate(OrderInfo orderInfo , ModelMap modelMap,HttpServletRequest request) {
        TbUser user = (TbUser) request.getAttribute("user");
        TaotaoResult order = orderService.createOrder(orderInfo);
        // 清空服务端购物车
        cartService.deleteCart(user.getId());
        modelMap.addAttribute("orderId",order.getData());
        modelMap.addAttribute("payment",orderInfo.getPayment());
        return "success";
    }

}
