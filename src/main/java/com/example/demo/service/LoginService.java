package com.example.demo.service;

import com.example.demo.domain.User;

import java.util.List;

public interface LoginService {
    public List<User> User_query(String username);

}
