package com.jiao.proxy;

public class Type {
    private Integer typeId;

    private String typeName;

    private Double amerceAmount;

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName == null ? null : typeName.trim();
    }

    public Double getAmerceAmount() {
        return amerceAmount;
    }

    public void setAmerceAmount(Double amerceAmount) {
        this.amerceAmount = amerceAmount;
    }
}