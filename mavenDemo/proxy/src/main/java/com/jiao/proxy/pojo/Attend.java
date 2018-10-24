package com.jiao.proxy.pojo;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Table(name = "attend_table")
public class Attend implements Serializable {
    @Id
    private Integer attendId;

    private String dutyDay;

    private Date punchTime;

    private Boolean isCome;

    private Integer typeId;

    private Integer empId;

    public Integer getAttendId() {
        return attendId;
    }

    public void setAttendId(Integer attendId) {
        this.attendId = attendId;
    }

    public String getDutyDay() {
        return dutyDay;
    }

    public void setDutyDay(String dutyDay) {
        this.dutyDay = dutyDay == null ? null : dutyDay.trim();
    }

    public Date getPunchTime() {
        return punchTime;
    }

    public void setPunchTime(Date punchTime) {
        this.punchTime = punchTime;
    }

    public Boolean getIsCome() {
        return isCome;
    }

    public void setIsCome(Boolean isCome) {
        this.isCome = isCome;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public Integer getEmpId() {
        return empId;
    }

    public void setEmpId(Integer empId) {
        this.empId = empId;
    }

    @Override
    public String toString() {
        return "Attend{" +
                "attendId=" + attendId +
                ", dutyDay='" + dutyDay + '\'' +
                ", punchTime=" + punchTime +
                ", isCome=" + isCome +
                ", typeId=" + typeId +
                ", empId=" + empId +
                '}';
    }
}