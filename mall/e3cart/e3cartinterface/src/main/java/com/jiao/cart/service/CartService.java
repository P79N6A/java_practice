package com.jiao.cart.service;

import com.jiao.common.utils.TaotaoResult;
import com.jiao.pojo.TbItem;

import java.util.List;

public interface CartService {
   /**
    * 在服务端添加购物车
    * @param userId
    * @param itemId
    * @param num
    * @return
    */
   TaotaoResult cartAdd(long userId ,   long itemId , int num);

   // 合并购物车数据到服务端
   TaotaoResult mergeCartToServer(long userId , List<TbItem> tbItems);

   // 从服务端获取购物车数据
   List<TbItem> getCartFromServer(long userId);

   // 更新服务端购物车商品数量
   TaotaoResult updateNum(long userId ,long itemId, int num);

   // 删除服务端商品
    TaotaoResult deleteNum(long userId , long itemId);

   // 删除用户购物车
   TaotaoResult deleteCart(long userId );
}
