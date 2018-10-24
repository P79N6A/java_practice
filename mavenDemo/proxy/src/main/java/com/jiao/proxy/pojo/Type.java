package com.jiao.proxy.pojo;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Table(name = "type_table")
public class Type implements Serializable {
    @Id
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

    @Override
    public String toString() {
        return "Type{" +
                "typeId=" + typeId +
                ", typeName='" + typeName + '\'' +
                ", amerceAmount=" + amerceAmount +
                '}';
    }
}