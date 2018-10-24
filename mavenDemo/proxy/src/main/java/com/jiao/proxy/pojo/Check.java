package com.jiao.proxy.pojo;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Table(name = "check_table")
public class Check implements Serializable {
    @Id
    private Integer checkId;

    private Integer appId;

    private Boolean checkResult;

    private String checkReason;

    private Integer mgrId;

    public Integer getCheckId() {
        return checkId;
    }

    public void setCheckId(Integer checkId) {
        this.checkId = checkId;
    }

    public Integer getAppId() {
        return appId;
    }

    public void setAppId(Integer appId) {
        this.appId = appId;
    }

    public Boolean getCheckResult() {
        return checkResult;
    }

    public void setCheckResult(Boolean checkResult) {
        this.checkResult = checkResult;
    }

    public String getCheckReason() {
        return checkReason;
    }

    public void setCheckReason(String checkReason) {
        this.checkReason = checkReason == null ? null : checkReason.trim();
    }

    public Integer getMgrId() {
        return mgrId;
    }

    public void setMgrId(Integer mgrId) {
        this.mgrId = mgrId;
    }

    @Override
    public String toString() {
        return "Check{" +
                "checkId=" + checkId +
                ", appId=" + appId +
                ", checkResult=" + checkResult +
                ", checkReason='" + checkReason + '\'' +
                ", mgrId=" + mgrId +
                '}';
    }
}