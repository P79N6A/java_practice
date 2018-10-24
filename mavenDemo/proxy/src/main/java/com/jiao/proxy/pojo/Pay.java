package com.jiao.proxy.pojo;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Table(name = "pay_table")
public class Pay implements Serializable {
    @Id
    private Integer payId;

    private String payMonth;

    private Double payAmount;

    private Integer empId;

    public Integer getPayId() {
        return payId;
    }

    public void setPayId(Integer payId) {
        this.payId = payId;
    }

    public String getPayMonth() {
        return payMonth;
    }

    public void setPayMonth(String payMonth) {
        this.payMonth = payMonth == null ? null : payMonth.trim();
    }

    public Double getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(Double payAmount) {
        this.payAmount = payAmount;
    }

    public Integer getEmpId() {
        return empId;
    }

    public void setEmpId(Integer empId) {
        this.empId = empId;
    }

    @Override
    public String toString() {
        return "Pay{" +
                "payId=" + payId +
                ", payMonth='" + payMonth + '\'' +
                ", payAmount=" + payAmount +
                ", empId=" + empId +
                '}';
    }
}