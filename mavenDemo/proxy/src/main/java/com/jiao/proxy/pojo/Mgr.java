package com.jiao.proxy.pojo;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Table(name = "mgr_table")
public class Mgr implements Serializable {
    @Id
    private Integer mgrId;

    private String deptName;

    public Integer getMgrId() {
        return mgrId;
    }

    public void setMgrId(Integer mgrId) {
        this.mgrId = mgrId;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName == null ? null : deptName.trim();
    }

    @Override
    public String toString() {
        return "Mgr{" +
                "mgrId=" + mgrId +
                ", deptName='" + deptName + '\'' +
                '}';
    }
}