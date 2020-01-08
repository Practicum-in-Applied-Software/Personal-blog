package com.example.demo.service.impl;


import com.example.demo.domain.User;
import com.example.demo.mapper.ChangePersonalInfoMapper;
import com.example.demo.mapper.LoginMapper;
import com.example.demo.service.ChangePersonalInfoService;
import com.example.demo.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ChangePersonalInfoServiceImpl implements ChangePersonalInfoService {
    @Autowired
    private ChangePersonalInfoMapper changePersonalInfoMapper;
    @Override
    public void change_user_info(String password,String email,String sex,String phone,String username) {
        changePersonalInfoMapper.change_user_info(password,email,sex,phone,username);
    }
}
