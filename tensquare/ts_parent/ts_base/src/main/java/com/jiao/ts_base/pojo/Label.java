package com.jiao.ts_base.pojo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created by jiao on 12/6/2018.
 */
@Entity
@Table(name = "tb_label")
public class Label implements Serializable{
    /**
     `id` varchar(20) NOT NULL COMMENT '标签ID',
     `labelname` varchar(100) DEFAULT NULL COMMENT '标签名称',
     `state` varchar(1) DEFAULT NULL COMMENT '状态',
     `count` bigint(20) DEFAULT NULL COMMENT '使用数量',
     `recommend` varchar(1) DEFAULT NULL COMMENT '是否推荐',
     `fans` bigint(20) DEFAULT NULL COMMENT '粉丝数',
     */
    @Id
    private String id;
    private String labelname;
    private String state;
    private long count;
    private String recommend;
    private long fans;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLabelname() {
        return labelname;
    }

    public void setLabelname(String labelname) {
        this.labelname = labelname;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public String getRecommend() {
        return recommend;
    }

    public void setRecommend(String recommend) {
        this.recommend = recommend;
    }

    public long getFans() {
        return fans;
    }

    public void setFans(long fans) {
        this.fans = fans;
    }
}
