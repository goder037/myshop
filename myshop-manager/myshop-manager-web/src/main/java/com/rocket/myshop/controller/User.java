package com.rocket.myshop.controller;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;


public class User {

	@NotNull
    private String name;
	@Length(max=6)
    private String username;

    public User(){}

    public User(String name, String username) {
        this.username = username;
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
