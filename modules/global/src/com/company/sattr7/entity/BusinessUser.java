package com.company.sattr7.entity;

import com.haulmont.chile.core.annotations.NamePattern;
import com.haulmont.cuba.core.entity.StandardEntity;
import com.haulmont.cuba.core.entity.annotation.Lookup;
import com.haulmont.cuba.core.entity.annotation.LookupType;
import com.haulmont.cuba.core.global.AppBeans;
import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.security.entity.User;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@NamePattern("%s|sysUser")
@Table(name = "SATTR7_BUSINESS_USER")
@Entity(name = "sattr7_BusinessUser")
public class BusinessUser extends StandardEntity {
    private static final long serialVersionUID = 4425082150417292342L;

    @Lookup(type = LookupType.DROPDOWN, actions = "lookup")
    @NotNull
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "SYS_USER_ID")
    protected User sysUser;

    @Lookup(type = LookupType.DROPDOWN, actions = {"lookup", "open", "clear"})
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "COMPANY_ID")
    protected Company company;

    public static BusinessUser ofUser(User user) {
        return AppBeans.get(DataManager.class)
                .load(BusinessUser.class)
                .query("select e from sattr7_BusinessUser e where e.sysUser = :user")
                .parameter("user", user)
                .optional()
                .orElse(null);
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public User getSysUser() {
        return sysUser;
    }

    public void setSysUser(User sysUser) {
        this.sysUser = sysUser;
    }
}