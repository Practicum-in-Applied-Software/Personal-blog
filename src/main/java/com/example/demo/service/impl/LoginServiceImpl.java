package com.example.demo.service.impl;


import com.example.demo.domain.User;
import com.example.demo.mapper.LoginMapper;
import com.example.demo.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    private LoginMapper loginMapper;
    @Override
    public List<User> User_query(String username) {
        return loginMapper.user_query(username);
    }
}
