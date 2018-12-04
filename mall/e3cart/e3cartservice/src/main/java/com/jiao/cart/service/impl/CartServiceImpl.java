package com.jiao.cart.service.impl;

import com.jiao.cart.service.CartService;
import com.jiao.common.jedis.JedisClient;
import com.jiao.common.utils.JsonUtils;
import com.jiao.common.utils.TaotaoResult;
import com.jiao.mapper.TbItemMapper;
import com.jiao.pojo.TbItem;
import com.jiao.pojo.TbUser;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartServiceImpl implements CartService{

    @Autowired
    private JedisClient jedisClient;

    @Value("${REDIS_CART_PRE}")
    private  String REDIS_CART_PRE;

    @Autowired
    private TbItemMapper tbItemMapper;

    /**
     *   登陆状态下 将商品添加到服务端
     * @param itemId
     * @param num
     * @return
     */
    @Override
    public TaotaoResult cartAdd(long userId ,  long itemId, int num) {
        /**
         *  用hash保存 购物车
         *  userid--  cartlist
         *  itemId--  TbItem
         *  先查找redis中有没有对应的商品 ，有更新数量
         *  没有查数据库，将商品设置数量后  添加到redis中
         *  返回ok
         */
        //数据类型是hash key：用户id field：商品id value：商品信息
        //判断商品是否存在
        Boolean hexists = jedisClient.hexists(REDIS_CART_PRE + ":" + userId, itemId + "");
        //如果存在数量相加
        if (hexists) {
            String json = jedisClient.hget(REDIS_CART_PRE + ":" + userId, itemId + "");
            //把json转换成TbItem
            TbItem item = JsonUtils.jsonToPojo(json, TbItem.class);
            item.setNum(item.getNum() + num);
            //写回redis
            jedisClient.hset(REDIS_CART_PRE + ":" + userId, itemId + "", JsonUtils.objectToJson(item));
            return TaotaoResult.ok();
        }
        // 如果不存在 则查找数据库
        TbItem tbItem = tbItemMapper.selectByPrimaryKey(itemId);
        tbItem.setNum(num);
        String images = tbItem.getImage();
        if (StringUtils.isNotBlank(images)){
            tbItem.setImage(images.split(",")[0]);
        }
        //写回redis
        jedisClient.hset(REDIS_CART_PRE + ":" + userId, itemId + "", JsonUtils.objectToJson(tbItem));
        return TaotaoResult.ok();
    }

    @Override
    public TaotaoResult mergeCartToServer(long userId, List<TbItem> tbItems) {
        // 遍历购物车列表 将数据插入redis中
        for (TbItem tbItem :tbItems){
            cartAdd(userId,tbItem.getId(),tbItem.getNum());
        }
        return TaotaoResult.ok();
    }

    @Override
    public List<TbItem> getCartFromServer(long userId) {
        List<String> jsons = jedisClient.hvals(REDIS_CART_PRE + ":" + userId);
        List<TbItem> tbItems = new ArrayList<>();
        for (String s : jsons){
            TbItem tbItem = JsonUtils.jsonToPojo(s, TbItem.class);
            tbItems.add(tbItem);
        }

        return tbItems;
    }

    @Override
    public   TaotaoResult updateNum(long userId, long itemId, int num) {
        /**
         *  从服务端获取数据，修改数据，插入数据
         */
        //从redis中取商品信息
        String json = jedisClient.hget(REDIS_CART_PRE + ":" + userId, itemId + "");
        //更新商品数量
        TbItem tbItem = JsonUtils.jsonToPojo(json, TbItem.class);
        tbItem.setNum(num);
        //写入redis
        jedisClient.hset(REDIS_CART_PRE + ":" + userId, itemId + "", JsonUtils.objectToJson(tbItem));
        return TaotaoResult.ok();
    }

    @Override
    public TaotaoResult deleteNum(long userId, long itemId) {
        jedisClient.hdel(REDIS_CART_PRE + ":" + userId, itemId + "");
        return TaotaoResult.ok();
    }

    @Override
    public TaotaoResult deleteCart(long userId) {
         jedisClient.del( REDIS_CART_PRE + ":" + userId);

        return TaotaoResult.ok();
    }


}
