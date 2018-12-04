package com.jiao.order.pojo;

import com.jiao.pojo.TbItem;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

public class Item extends TbItem {
    /*
        `id` bigint(20) NOT NULL COMMENT '商品id，同时也是商品编号',
        `title` varchar(100) NOT NULL COMMENT '商品标题',
        `sell_point` varchar(500) DEFAULT NULL COMMENT '商品卖点',
        `price` bigint(20) NOT NULL COMMENT '商品价格，单位为：分',
        `num` int(10) NOT NULL COMMENT '库存数量',
        `barcode` varchar(30) DEFAULT NULL COMMENT '商品条形码',
        `image` varchar(500) DEFAULT NULL COMMENT '商品图片',
        `cid` bigint(10) NOT NULL COMMENT '所属类目，叶子类目',
        `status` tinyint(4) NOT NULL DEFAULT '1' COMMENT '商品状态，1-正常，2-下架，3-删除',
        `created` datetime NOT NULL COMMENT '创建时间',
        `updated` datetime NOT NULL COMMENT '更新时间',
     */
    public Item(TbItem tbItem) {
        this.setId(tbItem.getId());
        this.setTitle(tbItem.getTitle());
        this.setSellPoint(tbItem.getSellPoint());
        this.setPrice(tbItem.getPrice());
        this.setNum(tbItem.getNum());
        this.setBarcode(tbItem.getBarcode());
        this.setImage(tbItem.getImage());
        this.setCid(tbItem.getCid());
        this.setStatus(tbItem.getStatus());
        this.setCreated(tbItem.getCreated());
        this.setUpdated(tbItem.getUpdated());
    }

    public String[] getImages(){
        if (StringUtils.isNotBlank(this.getImage())){
            return this.getImage().split(",");
        }
        return null;
    }
}
