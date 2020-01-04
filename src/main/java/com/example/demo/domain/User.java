package com.example.demo.domain;

public class User {
    private String name;
    private String pwd;
    private int privilege;

    public User(String name, String pwd, int privilege) {
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

    public int getPrivilege() {
        return privilege;
    }

    public void setPrivilege(int privilege) {
        this.privilege = privilege;
    }
}
