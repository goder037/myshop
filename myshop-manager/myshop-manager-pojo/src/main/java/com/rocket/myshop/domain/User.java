package com.rocket.myshop.domain;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column shop_user.userid_
     *
     * @mbg.generated
     */
    private String userid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column shop_user.username_
     *
     * @mbg.generated
     */
    private String username;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column shop_user.password_
     *
     * @mbg.generated
     */
    private String password;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column shop_user.op_date_
     *
     * @mbg.generated
     */
    private Date opDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table shop_user
     *
     * @mbg.generated
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column shop_user.userid_
     *
     * @return the value of shop_user.userid_
     *
     * @mbg.generated
     */
    public String getUserid() {
        return userid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column shop_user.userid_
     *
     * @param userid the value for shop_user.userid_
     *
     * @mbg.generated
     */
    public void setUserid(String userid) {
        this.userid = userid == null ? null : userid.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column shop_user.username_
     *
     * @return the value of shop_user.username_
     *
     * @mbg.generated
     */
    public String getUsername() {
        return username;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column shop_user.username_
     *
     * @param username the value for shop_user.username_
     *
     * @mbg.generated
     */
    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column shop_user.password_
     *
     * @return the value of shop_user.password_
     *
     * @mbg.generated
     */
    public String getPassword() {
        return password;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column shop_user.password_
     *
     * @param password the value for shop_user.password_
     *
     * @mbg.generated
     */
    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column shop_user.op_date_
     *
     * @return the value of shop_user.op_date_
     *
     * @mbg.generated
     */
    public Date getOpDate() {
        return opDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column shop_user.op_date_
     *
     * @param opDate the value for shop_user.op_date_
     *
     * @mbg.generated
     */
    public void setOpDate(Date opDate) {
        this.opDate = opDate;
    }
}