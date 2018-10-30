package com.jiao.byagent.pojo;

import com.jiao.proxy.pojo.Emp;
import com.jiao.proxy.pojo.Mgr;

/**
 * Created by jiao on 2018/10/23.
 */
public class MgrVO {
    Emp emp;
    Mgr mgr;

    public Emp getEmp() {
        return emp;
    }

    public void setEmp(Emp emp) {
        this.emp = emp;
    }

    public Mgr getMgr() {
        return mgr;
    }

    public void setMgr(Mgr mgr) {
        this.mgr = mgr;
    }
}
