package com.example.demo.domain;

public class User {
    private String name;
    private String pwd;
    private String privilege;

    public User(String name, String pwd, String privilege) {
        this.name = name;
        this.pwd = pwd;
        this.privilege = privilege;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getPrivilege() {
        return privilege;
    }

    public void setPrivilege(String privilege) {
        this.privilege = privilege;
    }
}
