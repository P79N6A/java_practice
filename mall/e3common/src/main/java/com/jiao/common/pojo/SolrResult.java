package com.jiao.common.pojo;

import java.io.Serializable;

/**
 * Created by jiao on 11/20/2018.
 */
public class SolrResult  implements Serializable{
    private String id;
    private String title;
    private long price;
    private String sell_point;
    private String  catgory_name;
    private String image;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public String getSell_point() {
        return sell_point;
    }

    public void setSell_point(String sell_point) {
        this.sell_point = sell_point;
    }

    public String getCatgory_name() {
        return catgory_name;
    }

    public void setCatgory_name(String catgory_name) {
        this.catgory_name = catgory_name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }


    public String[] getImages(){
        if (image!=null &&!"".equals(image)){
            return image.split(",");
        }
        return null;
    }
}
