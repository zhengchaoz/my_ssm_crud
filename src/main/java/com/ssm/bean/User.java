package com.ssm.bean;

import java.util.StringJoiner;

/**
 * @user 郑超
 * @date 2021/4/25
 */
public class User {

    private Integer id;
    private Long mobileNo;
    private String password;
    private String loginToken;

    public User() {
    }

    public User(Long mobileNo, String password, String loginToken) {
        this.mobileNo = mobileNo;
        this.password = password;
        this.loginToken = loginToken;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(Long mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLoginToken() {
        return loginToken;
    }

    public void setLoginToken(String loginToken) {
        this.loginToken = loginToken;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", User.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("mobileNo=" + mobileNo)
                .add("password='" + password + "'")
                .add("loginToken='" + loginToken + "'")
                .toString();
    }
}
