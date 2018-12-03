package com.jiao.cart.controller;

import com.jiao.cart.service.CartService;
import com.jiao.common.utils.CookieUtils;
import com.jiao.common.utils.JsonUtils;
import com.jiao.common.utils.TaotaoResult;
import com.jiao.pojo.TbItem;
import com.jiao.pojo.TbUser;
import com.jiao.service.ItemService;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@Controller
public class CartWebController {

    @Autowired
    private ItemService itemService;

    @Value("${COOKIE_CART_EXPIRE}")
    private int COOKIE_CART_EXPIRE;

    @Autowired
    private CartService cartService;

    /**
     *  http://localhost:8089/cart/add/1231490.html?num=1
     *  添加购物车方法
     *  未登陆情况下，使用cookie保存商品数据，使用tbitem作为一条商品的记录
     *  检查商品是否存在
     *  如果存在 更新数量
     *  如果不存在 则根据itemid查询数据库并添加商品记录到cookie中
     *  在登陆的情况下 直接将添加的商品添加到redis中
     */
    @RequestMapping("/cart/add/{itemId}")
    public String cartAdd(@PathVariable long itemId , @RequestParam(defaultValue = "1") int num, HttpServletRequest request , HttpServletResponse response){
        TbUser user = (TbUser) request.getAttribute("user");
        // 如果登陆了  则保存到服务端
        if (user != null){
            cartService.cartAdd(user.getId(),itemId,num);
            return "cartSuccess";
        }

        List<TbItem> tbItems = getCartFromCookie(request);
        // 找到该商品的标志
        boolean flag = false;
        for (TbItem t : tbItems){
            if (t.getId().longValue() == itemId){
                // 更新flag
                flag = true;
                t.setNum(t.getNum() + num);
                break;
            }
        }
        // 如果在cookie中没有找到对应的商品
        if(!flag){
            TbItem tbItem = itemService.getItemById(itemId);
            tbItem.setNum(num);
            //取一张图片
            if(StringUtils.isNotBlank(tbItem.getImage())){
                tbItem.setImage(tbItem.getImage().split(",")[0]);
            }
            tbItems.add(tbItem);
        }
        //写入cookie
        CookieUtils.setCookie(request, response, "cart", JsonUtils.objectToJson(tbItems), COOKIE_CART_EXPIRE, true);
        return "cartSuccess";
    }


    /**
     *  从cookie中获取购物车数据 转成list返回
     */
    public List<TbItem> getCartFromCookie(HttpServletRequest request){
        String json = CookieUtils.getCookieValue(request, "cart", true);
        if (StringUtils.isBlank(json)){
            return  new ArrayList<>();
        }
        List<TbItem> tbItems = JsonUtils.jsonToList(json, TbItem.class);
        return tbItems;
    }


    /**
     *  去购物车结算
     *  未登陆情况下从cookie中获取购物车数据
     *  登陆的情况下从服务端获取购物车数据 并将cookie中的购物车数据同步到服务端
     *
     */
    @RequestMapping("cart/cart")
    public String goToCart(HttpServletRequest request, Model model ,HttpServletResponse response){
        TbUser user = (TbUser) request.getAttribute("user");
        List<TbItem> tbItems = getCartFromCookie(request);
        // 判断是否时登录状态
        if (user != null){
            //  合并购物车数据到服务端
            cartService.mergeCartToServer(user.getId(),tbItems) ;
            //  将cookie中的数据删除
            CookieUtils.deleteCookie(request,response,"cart");
            //  从服务获取购物车数据
            tbItems = cartService.getCartFromServer(user.getId());

        }
        model.addAttribute("cartList",tbItems);
        return "cart";
    }

    /**
     *  更新购物车商品数量
     *  从cookie中查找数据 然后更新数量
     *  登陆情况下  则更新服务端的数量
     */
    @RequestMapping("/cart/update/num/{itemId}/{num}")
    @ResponseBody
    public  TaotaoResult updateCartNum(@PathVariable long itemId ,@PathVariable int num,HttpServletRequest request, HttpServletResponse response){
        // 判断是否登陆
        TbUser user = (TbUser) request.getAttribute("user");
        // 判断是否时登录状态
        if (user != null){
        // 如果是登录状态  则更新服务端数据
         cartService.updateNum(user.getId(), itemId, num);
         return  TaotaoResult.ok();
        }
        List<TbItem> tbItems = getCartFromCookie(request);
        for (TbItem t : tbItems) {
            if(t.getId().longValue() == itemId){
                t.setNum(num);
                break;
            }
        }
        //写入cookie
        CookieUtils.setCookie(request, response, "cart", JsonUtils.objectToJson(tbItems), COOKIE_CART_EXPIRE, true);
        return  TaotaoResult.ok();
    }

    /**
     * 删除购物车商品
     */
    @RequestMapping("/cart/delete/{itemId}")
    public String deleteCartItem(@PathVariable Long itemId, HttpServletRequest request,
                                 HttpServletResponse response) {
        /**
         *  先判断是否登陆
         *  登录情况下删除服务端数据
         *  未登录下 删除cookie中数据
         *  遍历状态删除list中的数据  最好直接break  否则出错
         */
        TbUser user = (TbUser) request.getAttribute("user");
        if (user != null){
            cartService.deleteNum(user.getId(),itemId);
            return "redirect:/cart/cart.html";
        }
        // 未登录下则遍历cookie中的数据 删除指定商品
        List<TbItem> tbItems = getCartFromCookie(request);
        for (TbItem tbItem : tbItems){
            if(tbItem.getId().longValue() == itemId){
                tbItems.remove(tbItem);
                break;
            }
        }
        //把购物车列表写入cookie
        CookieUtils.setCookie(request, response, "cart", JsonUtils.objectToJson(tbItems), COOKIE_CART_EXPIRE, true);
        //返回逻辑视图
        return "redirect:/cart/cart.html";
    }

}
