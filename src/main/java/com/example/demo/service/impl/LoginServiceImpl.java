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
    public User User_query(String username) {
        return loginMapper.user_query(username);
    }

    @Override
    public List<String> query_username_according_to_privilege(int privilege){
        return loginMapper.query_username_according_to_privilege_mapper(privilege);
    }
}
