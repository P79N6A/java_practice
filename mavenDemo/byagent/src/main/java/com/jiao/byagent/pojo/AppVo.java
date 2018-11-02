package com.jiao.byagent.pojo;

import com.jiao.proxy.pojo.App;
import com.jiao.proxy.pojo.Attend;
import com.jiao.proxy.pojo.Type;

/**
 * Created by jiao on 2018/10/31.
 */
public class AppVo extends App {
    AttendVo attend;

    Type type;

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Attend getAttend() {
        return attend;
    }

    public void setAttend(AttendVo attend) {
        this.attend = attend;
    }
}
