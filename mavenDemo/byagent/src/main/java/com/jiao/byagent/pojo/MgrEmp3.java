package com.jiao.byagent.pojo;

import com.jiao.proxy.pojo.Emp;

import java.io.Serializable;

/**
 * Created by jiao on 2018/10/22.
 */
public class MgrEmp3 extends Emp implements Serializable {

    private String deptName;

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    @Override
    public String toString() {
        return "MgrEmp3{" +
                "deptName='" + deptName + '\'' +
                "} " + super.toString();
    }
}
