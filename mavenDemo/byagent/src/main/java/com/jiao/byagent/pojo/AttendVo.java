package com.jiao.byagent.pojo;

import com.jiao.proxy.pojo.Attend;
import com.jiao.proxy.pojo.Emp;
import com.jiao.proxy.pojo.Type;

/**
 * Created by jiao on 2018/10/25.
 */
public class AttendVo extends Attend{

    Type type;

    Emp emp;

    public Emp getEmp() {
        return emp;
    }

    public void setEmp(Emp emp) {
        this.emp = emp;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }


}
