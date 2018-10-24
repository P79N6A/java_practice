package com.jiao.proxy.pojo;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Table(name = "app_table")
public class App implements Serializable {
    @Id
    private Integer appId;

    private Integer attendId;

    private String appReason;

    private Boolean appResult;

    private Integer typeId;

    public Integer getAppId() {
        return appId;
    }

    public void setAppId(Integer appId) {
        this.appId = appId;
    }

    public Integer getAttendId() {
        return attendId;
    }

    public void setAttendId(Integer attendId) {
        this.attendId = attendId;
    }

    public String getAppReason() {
        return appReason;
    }

    public void setAppReason(String appReason) {
        this.appReason = appReason == null ? null : appReason.trim();
    }

    public Boolean getAppResult() {
        return appResult;
    }

    public void setAppResult(Boolean appResult) {
        this.appResult = appResult;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    @Override
    public String toString() {
        return "App{" +
                "appId=" + appId +
                ", attendId=" + attendId +
                ", appReason='" + appReason + '\'' +
                ", appResult=" + appResult +
                ", typeId=" + typeId +
                '}';
    }
}