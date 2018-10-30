package com.jiao.byagent.pojo;

import com.jiao.proxy.pojo.Emp;
import com.jiao.proxy.pojo.Pay;

/**
 * Created by jiao on 2018/10/23.
 */
public class PayVO extends Pay{
    Emp emp;

    public Emp getEmp() {
        return emp;
    }

    public void setEmp(Emp emp) {
        this.emp = emp;
    }
}
