package com.example.demo.service.impl;

import com.example.demo.mapper.RegisterMapper;
import com.example.demo.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegisterServiceImpl implements RegisterService {
    @Autowired
    private RegisterMapper registerMapper;
    @Override
    public void user_insert(String username,String password,int privilege) {
        registerMapper.user_insert(username,password,privilege);
    }
}
