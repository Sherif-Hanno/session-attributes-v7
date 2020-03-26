package com.company.sattr7.entity;

import com.haulmont.chile.core.annotations.NamePattern;
import com.haulmont.cuba.core.entity.StandardEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@NamePattern("%s|name")
@Table(name = "SATTR7_COMPANY")
@Entity(name = "sattr7_Company")
public class Company extends StandardEntity {
    private static final long serialVersionUID = 7239246082187789470L;

    @NotNull
    @Column(name = "NAME", nullable = false)
    protected String name;

    @Column(name = "LEGAL_NUMBER")
    protected String legalNum;

    @Lob
    @Column(name = "ADDRESS")
    protected String address;

    public String getLegalNum() {
        return legalNum;
    }

    public void setLegalNum(String legalNum) {
        this.legalNum = legalNum;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}